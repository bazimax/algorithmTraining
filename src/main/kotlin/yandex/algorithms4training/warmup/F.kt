package yandex.algorithms4training.warmup

/**
 * F. Лифт //Elevator
 *
 * Тридцатого декабря все сотрудники известной IT-компании отправляются праздновать Новый год! На парковке офиса
 * сотрудников уже ожидают автобусы, чтобы отвезти их в ресторан. Известно, что на i-м этаже работает ai сотрудников,
 * а парковка расположена на нулевом этаже. Изначально лифт расположен на этаже с парковкой.
 * Какое минимальное количество времени лифт будет перевозить всех людей на парковку? Известно, что лифт движется
 * со скоростью один этаж в секунду, а посадка и высадка происходит мгновенно.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    elevatorV3()
}

//18:30 - (10мин перерыв) - 20:15 (1ч45мин) > notOK (218ms, 15.18Mb, не прошел тест 3)
fun elevator(){
    val input = BufferedReader(FileReader("input.txt"))

    val elevatorCapacity = input.readLine().split(" ").map { it.toLong() }[0] //10^9
    val nFloors = input.readLine().split(" ").map { it.toInt() }[0] //number of floors in the building //10^5

    var total = 0L //ответ - общее количество секунд на все перевозки

    var employeesInElevator = 0L //количество людей в лифте на данный момент

    val arrRemainingEmployees = LongArray(nFloors){0L} //оставшиеся сотрудники после "первого прохода"


    //Первый проход. Делаем полные перевозки, а оставшихся сотрудников заберем потом
    repeat(nFloors) { floor ->
        val employees = input.readLine().split(" ").map { it.toLong() }[0] //количество людей на этаже //10^9

        //количество полных перевозок с этого этажа на -1 этаж (парковку)
        val deliverCounter = employees / elevatorCapacity

        total += (deliverCounter * (floor + 1)) * 2 //временные затраты (timeCosts)

        //если есть оставшиеся сотрудники записываем отдельно <этаж, количество сотрудников>
        if (employees % elevatorCapacity != 0L) arrRemainingEmployees[floor] = employees % elevatorCapacity

    }

    //Второй проход. Работаем пока не заберем всех оставшихся
    var timeCosts = 0 //временные затраты на каждый подъем (значение не может быть больше 10^5)

    //движемся по одному этажу, если есть сотрудники забираем и поднимаемся дальше, если лифт заполнен спускаемся
    //в самый низ, и запоминаем если ещё остались сотрудники
    arrRemainingEmployees.forEachIndexed { floor, employees ->
        //поднялись на этаж (floor)

        timeCosts++

        if (employeesInElevator + employees < elevatorCapacity) {
            //лифт еще не заполнен, поднимаемся выше на следующий этаж
            employeesInElevator += employees
        }
        else {
            //иначе лифт заполнен, спускаемся на -1 этаж (парковку)
            total += timeCosts + (floor + 1)

            //возвращаемся на этаж (floor)
            timeCosts = floor + 1

            //забираем оставшихся сотрудников и поднимаемся выше на следующий этаж
            employeesInElevator = (employeesInElevator + employees) - elevatorCapacity
        }

        //Если это последний этаж и в лифте есть сотрудники, то спускаемся. Иначе последний подъем был не нужен.
        if (floor == nFloors - 1 && employeesInElevator != 0L) {
            total += timeCosts + (floor + 1)
        }
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(total.toString())
    output.flush()
}

//14:00 - 14:35 (35мин) > notOK (218ms, 15.19Mb, не прошел тест 3)
fun elevatorV2(){
    val input = BufferedReader(FileReader("input.txt"))

    val elevatorCapacity = input.readLine().split(" ").map { it.toLong() }[0] //10^9
    val nFloors = input.readLine().split(" ").map { it.toInt() }[0] //number of floors in the building //10^5

    var total = 0L //ответ - общее количество секунд на все перевозки

    var employeesInElevator = 0L //количество людей в лифте на данный момент

    //var elevatorLevel = -1 //этаж на котором сейчас расположен лифт

    var lastFloorWithEmployees = 0

    repeat(nFloors) { floor ->
        val employees = input.readLine().split(" ").map { it.toLong() }[0] //количество людей на этаже //10^9

        if (employees != 0L) {
            lastFloorWithEmployees = floor + 1
        }

        //проверяем заполнился ли лифт, и есть ли еще сотрудники на этаже
        val remainingEmployees = employees - (elevatorCapacity - employeesInElevator)

        // 1. поднялись на нынешний этаж (floor), по пути набрав людей до заполнения лифта, после вернулись на -1
        if (employees + employeesInElevator >= elevatorCapacity) {
            total += (floor + 1) * 2
            employeesInElevator = 0L
        }
        else {
            //иначе лифт ещё заполнен не полностью и едет на следующий этаж
            employeesInElevator += employees
        }

        // 2. Дополнительные полные перевозки с -1 этажа на этот и обратно на -1
        if (remainingEmployees > 0) {
            //количество дополнительных полных перевозок
            val deliverCounter = remainingEmployees / elevatorCapacity

            //временные затраты (timeCosts)
            total += deliverCounter * ((floor + 1) * 2)

            //лифт заполнен не полностью и едет на следующий этаж
            employeesInElevator = remainingEmployees % elevatorCapacity
        }
    }

    //если лифт не пустой значит делаем последний рейс
    if (employeesInElevator != 0L) total += lastFloorWithEmployees * 2


    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(total.toString())
    output.flush()
}

//14:50 - 16:30 (1ч40мин) > OK (0.513s, 34.52Mb) Long-версия не прошла 21 тест, а ULong прошел
fun elevatorV3(){
    //ULong
    //сверху вниз
    val input = BufferedReader(FileReader("input.txt"))

    val elevatorCapacity = input.readLine().split(" ").map { it.toULong() }[0] //10^9
    val nFloors = input.readLine().split(" ").map { it.toInt() }[0] //number of floors in the building //10^5
    val arr = LongArray(nFloors)

    repeat(nFloors) { item ->
        arr[item] = input.readLine().split(" ").map { it.toLong() }[0]
    }

    var total = 0UL //ответ - общее количество секунд на все перевозки
    var employeesInElevator = 0UL //количество людей в лифте на данный момент
    var lastFloorWithEmployees = 0 //

    //забираем сотрудников начиная с последнего этажа на котором есть сотрудники
    for (floor in nFloors downTo 1) {

        //количество людей на этаже //10^9
        var employees = arr[floor - 1].toULong()

        //если сотрудников нет - пропускаем этот этаж
        if (employees == 0UL) {
            continue
        }

        //количество людей на этаже + те что были в лифте
        employees += employeesInElevator


        if (employees >= elevatorCapacity) {
            if (lastFloorWithEmployees == 0) lastFloorWithEmployees = floor

            //количество дополнительных полных перевозок
            val deliverCounter = employees / elevatorCapacity
            total += lastFloorWithEmployees.toULong() * 2UL
            total += if (deliverCounter > 1UL) (deliverCounter - 1UL) * floor.toULong() * 2UL else 0UL

            //если остались сотрудники заполняем лифт и едем ниже
            employeesInElevator = employees % elevatorCapacity

            //записываем этот этаж
            lastFloorWithEmployees = if (employees % elevatorCapacity > 0UL) floor else 0

        }
        else {
            employeesInElevator = employees
            if (lastFloorWithEmployees == 0) lastFloorWithEmployees = floor
        }
    }


    //если лифт не пустой значит делаем последний рейс
    if (employeesInElevator > 0UL) total += (lastFloorWithEmployees * 2).toULong()

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(total.toString())
    output.flush()
}

//backup
/*
fun elevator(){
    val input = BufferedReader(FileReader("input.txt"))

    val elevatorCapacity = input.readLine().split(" ").map { it.toLong() }[0] //10^9
    val nFloors = input.readLine().split(" ").map { it.toInt() }[0] //number of floors in the building //10^5

    //println("elevatorCapacity: $elevatorCapacity, nFloors: $nFloors")

    var total = 0L //ответ - общее количество секунд на все перевозки

    var employeesInElevator = 0L //количество людей в лифте на данный момент

    val arrRemainingEmployees = LongArray(nFloors){0L} //оставшиеся сотрудники после "первого прохода"


    //Первый проход. Делаем полные перевозки, а оставшихся сотрудников заберем потом
    repeat(nFloors) { floor ->
        val employees = input.readLine().split(" ").map { it.toLong() }[0] //количество людей на этаже //10^9

        //количество полных перевозок с этого этажа на -1 этаж (парковку)
        val deliverCounter = employees / elevatorCapacity

        total += (deliverCounter * (floor + 1)) * 2 //временные затраты (timeCosts)

        //если есть оставшиеся сотрудники записываем отдельно <этаж, количество сотрудников>
        if (employees % elevatorCapacity != 0L) arrRemainingEmployees[floor] = employees % elevatorCapacity

        //println("floor: $floor, employees: $employees deliverCounter: $deliverCounter, total: $total, remaining: ${employees % elevatorCapacity}")
    }

    //Второй проход. Работаем пока не заберем всех оставшихся
    var timeCosts = 0 //временные затраты на каждый подъем (значение не может быть больше 10^5)

    //движемся по одному этажу, если есть сотрудники забираем и поднимаемся дальше, если лифт заполнен спускаемся
    //в самый низ, и запоминаем если ещё остались сотрудники
    arrRemainingEmployees.forEachIndexed { floor, employees ->
        //поднялись на этаж (floor)

        timeCosts++
        //println("- floor: $floor, employees: $employees timeCosts: $timeCosts, total: $total")

        if (employeesInElevator + employees < elevatorCapacity) {
            //println("- if > timeCosts: $timeCosts, total: $total")
            //лифт еще не заполнен, поднимаемся выше на следующий этаж
            employeesInElevator += employees
        }
        else {
            //иначе лифт заполнен, спускаемся на -1 этаж (парковку)
            total += timeCosts + (floor + 1)

            //возвращаемся на этаж (floor)
            timeCosts = floor + 1
            //println("- else > timeCosts: $timeCosts, total: $total")
            //забираем оставшихся сотрудников и поднимаемся выше на следующий этаж
            employeesInElevator = 0
        }

        //Если это последний этаж и в лифте есть сотрудники, то спускаемся. Иначе последний подъем был не нужен.
        if (floor == nFloors - 1 && employeesInElevator != 0L) {
            //println("final floor = ${timeCosts + (floor + 1)}")
            total += timeCosts + (floor + 1)
        }
        //println("floor: $floor, employees: $employees timeCosts: $timeCosts, total: $total")
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(total.toString())
    output.flush()
}*/
