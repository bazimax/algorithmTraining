package yandex.algorithms4training.class3

/**
 * E. На санях
 *
 * В начале XVIII века еще не было самолетов, поездов и автомобилей, поэтому все междугородние зимние поездки
 * совершались на санях. Как известно, с дорогами в России тогда было даже больше проблем, чем сейчас, а именно
 * на N существовавших тогда городов имелась N-1 дорога, каждая из которых соединяла два города. Из каждого города
 * можно было добраться в любой другой (возможно, через промежуточные города). В каждом городе была
 * почтовая станция («ям»), на которой можно было пересесть в другие сани. При этом ямщики могли долго запрягать
 * (для каждого города известно время, которое ямщики в этом городе тратят на подготовку саней к поездке) и быстро
 * ехать (также для каждого города известна скорость, с которой ездят ямщики из него). Можно считать, что количество
 * ямщиков в каждом городе не ограничено.
 *
 * Если бы олимпиада проводилась 300 лет назад, то путь участников занимал бы гораздо большее время, чем сейчас.
 * Допустим, из каждого города в Москву выезжает участник олимпиады и хочет добраться до Москвы за наименьшее время
 * (необязательно по кратчайшему пути: он может заезжать в любые города, через один и тот же город можно проезжать
 * несколько раз). Сначала он едет с ямщиком из своего города. Приехав в любой город, он может либо сразу ехать дальше,
 * либо пересесть. В первом случае он едет с той же скоростью, с какой ехал раньше. Если сменить ямщика, он сначала
 * ждет, пока ямщик подготовит сани, и только потом едет с ним (с той скоростью, с которой ездит этот ямщик).
 * В пути можно делать сколько угодно пересадок.
 *
 * Определите, какое время необходимо, чтобы все участники олимпиады доехали из своего города в Москву 300 лет назад.
 * Все участники выезжают из своих городов одновременно.
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    //onASleighV4()
    runTest("onASleigh", examplesE, ::onASleighV4, printAnswer = true)
    //runOneTest("onASleigh", ::onASleighV4, "y_4_3_E_6_input.txt", "y_4_3_E_6_correctAns.txt", printAnswer = true)
    //runOneTest("onASleigh", ::onASleighV4, "y_4_3_E_24_input.txt", "", printAnswer = true) // тяжелые варианты
    //runOneTest("onASleigh", ::onASleighV4, "y_4_3_E_25_input.txt", "", printAnswer = true) // тяжелые варианты
}

private val examplesE = arrayOf(
    // 1
    Pair("4\n" +
            "1 1\n" +
            "10 30\n" +
            "5 40\n" +
            "1 10\n" +
            "1 2 300\n" +
            "1 3 400\n" +
            "2 4 100",
        "31.0\n" +
                "4 2 1"),
    // 2
    Pair("3\n" +
            "1 1\n" +
            "0 10\n" +
            "0 55\n" +
            "1 2 100\n" +
            "2 3 10",
        "3.0\n" +
                "2 3 1"),
    Pair("5\n" +
            "3 4\n" +
            "3 3\n" +
            "4 2\n" +
            "0 8\n" +
            "0 9\n" +
            "2 4 6\n" +
            "5 4 70\n" +
            "5 1 8\n" +
            "3 1 86",
        "47.0\n" +
                "3 1"),
    // 6
    Pair("20\n" +
            "5 60\n" +
            "65 96\n" +
            "39 55\n" +
            "7 35\n" +
            "58 71\n" +
            "68 5\n" +
            "68 71\n" +
            "94 100\n" +
            "41 12\n" +
            "41 14\n" +
            "5 100\n" +
            "96 11\n" +
            "75 88\n" +
            "12 86\n" +
            "43 59\n" +
            "0 10\n" +
            "16 70\n" +
            "27 79\n" +
            "13 79\n" +
            "66 80\n" +
            "3 7 668\n" +
            "3 16 993\n" +
            "18 16 958\n" +
            "11 18 340\n" +
            "11 19 390\n" +
            "8 19 733\n" +
            "10 8 193\n" +
            "10 17 797\n" +
            "17 14 27\n" +
            "14 5 616\n" +
            "6 5 994\n" +
            "6 20 459\n" +
            "1 20 755\n" +
            "1 4 719\n" +
            "15 4 412\n" +
            "2 15 733\n" +
            "9 2 426\n" +
            "9 13 201\n" +
            "13 12 476",
        "242.57954545454547\n" +
                "12 13 1"),
    Pair("5\n" +
            "1 1\n" +
            "0 100\n" +
            "0 50\n" +
            "10 50\n" +
            "0 1\n" +
            "1 2 1000\n" +
            "2 3 100\n" +
            "3 4 1\n" +
            "4 5 1",
        "22.02\n" +
                "4 2 1"),
    // 23
    Pair("5\n" +
            "1 1\n" +
            "0 100\n" +
            "0 25\n" +
            "0 29\n" +
            "0 25\n" +
            "1 5 100\n" +
            "5 3 25\n" +
            "5 4 50\n" +
            "4 2 10\n",
        "4.944827586206896\n" +
                "3 4 2 1"),
    //Pair("12", "2"),
)


// В сумме со всеми попытками ~20ч > OK (2.566s, 68.04Mb)
private fun onASleighV4(){
    val input = BufferedReader(FileReader("input.txt"))

    // количество городов, соединенных дорогами N < 2000
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0]

    // города (index = cityName)
    val arrCities: Array<City> = Array(n + 1){ City(name = it, runUp = Int.MAX_VALUE, speed = Int.MAX_VALUE) }

    // Расстояния между городами. Будем заполнять таблицу постепенно
    val matrixOfDistancesBetweenCities: Array<IntArray> = Array(arrCities.size){ IntArray(arrCities.size){-2} }

    matrixOfDistancesBetweenCities[0] = IntArray(arrCities.size) {-1}
    repeat(arrCities.size) {
        matrixOfDistancesBetweenCities[it][0] = -1
    }

    repeat(n) { city ->
        // добавляем города, заполняя имена, подготовку и скорость саней
        // 0 ≤ run-Up ≤ 100, 1 ≤ speed ≤ 100
        val (runUp, speed) = input.readLine().trim().split(" ").map { it.toInt() }
        arrCities[city + 1] = City(city + 1, runUp, speed)
        arrCities[city + 1].pathToTheCapital.add(city + 1)
        arrCities[city + 1].pathToTheCapitalFast.add(city + 1)

        matrixOfDistancesBetweenCities[city + 1][city + 1] = 0
    }

    repeat(n - 1) { _ ->
        // добавляем дороги, A и B — номера соединенных городов, а S — расстояние между ними в километрах
        // 1 ≤ A ≤ N, 1 ≤ B ≤ N, A ≠ B, 1 ≤ S ≤ 10000
        val (cityA, cityB, roadLength) = input.readLine().trim().split(" ").map { it.toInt() }
        arrCities[cityA].listOfNeighboringCities.add(Road(cityB, roadLength))
        arrCities[cityB].listOfNeighboringCities.add(Road(cityA, roadLength))

        // Добавляем расстояние между городами в таблицу расстояний
        matrixOfDistancesBetweenCities[cityA][cityB] = roadLength
        matrixOfDistancesBetweenCities[cityB][cityA] = roadLength
    }

    // Первый проход: от столицы до остальных городов - ищем кратчайшие пути и записываем маршрут

    // очередь городов на проверку удаленности от столицы
    val pqDistance = PriorityQueue<City> { a, b -> a.minDistanceToTheCapital - b.minDistanceToTheCapital}

    // обновляем данные столицы
    val cityCapital = arrCities[1].asCopy()
    cityCapital.minDistanceToTheCapital = 0
    cityCapital.minTravelTimeToTheCapital = 0.0
    pqDistance.add(cityCapital)

    while (pqDistance.isNotEmpty()) {
        val cityA = pqDistance.poll()

        // проверка на уже пройденные города
        if (cityA.minDistanceToTheCapital < arrCities[cityA.name].minDistanceToTheCapital) {
            // маршрут более короткий, обновляем нынешнее значение
            arrCities[cityA.name] = cityA.asCopy()
            matrixOfDistancesBetweenCities[cityA.name][1] = cityA.minDistanceToTheCapital
            matrixOfDistancesBetweenCities[1][cityA.name] = cityA.minDistanceToTheCapital

            cityA.listOfNeighboringCities.forEach { road ->
                val cityB = arrCities[road.nameCityB].asCopy()

                // если этот путь меньше уже известных - обновляем CityB
                if (comparisonDistance(cityA, cityB, road)) pqDistance.add(cityB)
            }
        }
    }
    
    // Второй проход. Проверяем расстояния между всеми оставшимися городами. Дейкстра для каждого города.

    for (cityI in arrCities) {
        if (cityI.name == 0 || cityI.name == 1) continue

        val distances = LinkedList<Pair<City, Int>>()
        distances.add(cityI to 0)

        val visitedCities = HashSet<City>()


        // Проверяем все пути ведущие из нынешнего cityI во все другие города
        while (distances.isNotEmpty()) {
            val cityMove = distances.poll()
            visitedCities.add(cityMove.first)

            cityMove.first.listOfNeighboringCities.forEach { road ->
                val cityNeighbor = arrCities[road.nameCityB]

                if (!visitedCities.contains(cityNeighbor) && road.nameCityB != 1) {

                    val distanceCityIToCityNeighbor = cityMove.second + road.lengthAB
                    distances.add(cityNeighbor to distanceCityIToCityNeighbor)

                    if (matrixOfDistancesBetweenCities[cityI.name][cityNeighbor.name] == -2) {
                        matrixOfDistancesBetweenCities[cityI.name][cityNeighbor.name] = distanceCityIToCityNeighbor
                    }
                }
            }
        }

        // Отдельно все непроверенные города ведущие через столицу помечаем как не имеющие пути из cityI (-1)
        distances.add(cityCapital to 0)
        while (distances.isNotEmpty()) {
            val cityMove = distances.poll()
            visitedCities.add(cityMove.first)

            cityMove.first.listOfNeighboringCities.forEach { road ->
                val cityNeighbor = arrCities[road.nameCityB]

                if (!visitedCities.contains(cityNeighbor)) {
                    distances.add(cityNeighbor to 0)

                    if (matrixOfDistancesBetweenCities[cityI.name][cityNeighbor.name] == -2) {
                        matrixOfDistancesBetweenCities[cityI.name][cityNeighbor.name] = -1
                    }
                }
            }
        }
    }

    // Третий проход. Проверяем все города начиная с самого меньшего, по принципу: если есть города быстрее нынешнего,
    // то можем ли ускориться добравшись до них.
    val pqTime = PriorityQueue<City>{ a, b -> (a.minTravelTimeToTheCapital - b.minTravelTimeToTheCapital).toInt()}
    arrCities.forEach { pqTime.add(it) }
    pqTime.poll() // удаляем столицу - она нам не понадобиться

    val fasterCities = mutableListOf<City>()

    val fastestCity = pqTime.poll()
    fasterCities.add(fastestCity) // добавляем самый быстрый город (он все равно не может быть ускорен)


    while (pqTime.isNotEmpty()) {
        val cityA = pqTime.poll() // Проверяемый город.



        if (cityA.name == 0) continue

        // Проверяем каждый город который быстрее нынешнего, можем ли за его счёт добраться до столицы быстрее
        val minCityA = cityA.asCopy()
        fasterCities.forEach { fasterCity ->
            val distanceBetweenCities = matrixOfDistancesBetweenCities[cityA.name][fasterCity.name]
            if (
                distanceBetweenCities > 0 &&
                fasterCity.minTravelTimeToTheCapital < minCityA.minTravelTimeToTheCapital
            ) {
                // если этот город быстрее "Проверяемого города", тогда проверяем можем ли до быстро него добраться
                // иначе ничего не делаем

                val travelTimeCityAToFasterCity: Double = cityA.runUp + (distanceBetweenCities.toDouble() / cityA.speed)
                val newTravelTimeCityAToCapital = travelTimeCityAToFasterCity + fasterCity.minTravelTimeToTheCapital

                if (newTravelTimeCityAToCapital < minCityA.minTravelTimeToTheCapital){
                    //если можем ускориться и это будет быстрее уже найденного minCityA, то обновляем его
                    minCityA.minTravelTimeToTheCapital = newTravelTimeCityAToCapital
                    minCityA.pathToTheCapitalFast =
                        (listOf(minCityA.name) + fasterCity.pathToTheCapitalFast).toMutableList()

                }
            }
        }

        if (minCityA.minTravelTimeToTheCapital < cityA.minTravelTimeToTheCapital) {
            // Если ускориться удалось, обновляем маршрут
            cityA.minTravelTimeToTheCapital = minCityA.minTravelTimeToTheCapital
            cityA.pathToTheCapitalFast = minCityA.pathToTheCapitalFast
            fasterCities.add(cityA)
        }
        else {
            // Иначе едем прямиком в столицу
            //cityA.pathToTheCapitalFast = (listOf(cityA.name) + 1).toMutableList()
            fasterCities.add(cityA)
        }

        // эта доп проверка сильно тормозит
        fasterCities.forEachIndexed { index, city ->
            val distanceBetweenCities = matrixOfDistancesBetweenCities[cityA.name][city.name]
            if (
                distanceBetweenCities > 0 &&
                cityA.minTravelTimeToTheCapital < city.minTravelTimeToTheCapital
            ) {
                // Если после проверки cityA остались города медленнее cityA, тогда проверяем их

                val travelTimeCityToCityA: Double = city.runUp + (distanceBetweenCities.toDouble() / city.speed)
                val newTravelTimeCityToCapital = travelTimeCityToCityA + cityA.minTravelTimeToTheCapital

                if (newTravelTimeCityToCapital < city.minTravelTimeToTheCapital){
                    //если можем ускориться и это будет быстрее уже найденного, то обновляем
                    city.minTravelTimeToTheCapital = newTravelTimeCityToCapital
                    city.pathToTheCapitalFast =
                        (listOf(city.name) + cityA.pathToTheCapitalFast).toMutableList()
                }
            }
        }
    }


    var answer = -1.0 to mutableListOf<Int>()
    for (i in 1 .. arrCities.lastIndex) {
        val city = arrCities[i]
        if (city.minTravelTimeToTheCapital > answer.first) {
            answer = city.minTravelTimeToTheCapital to city.pathToTheCapitalFast //newPath.toMutableList()
        }
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write("${answer.first}\n" + answer.second.joinToString(" "))
    output.flush()
}

private fun comparisonDistance(cityA: City, cityB: City, road: Road) : Boolean {

    return if (road.lengthAB + cityA.minDistanceToTheCapital < cityB.minDistanceToTheCapital) {

        cityB.minDistanceToTheCapital = road.lengthAB + cityA.minDistanceToTheCapital
        cityB.minTravelTimeToTheCapital = (cityB.minDistanceToTheCapital.toDouble() / cityB.speed) + cityB.runUp

        // Записываем маршрут. Маршрут начинается от столицы
        cityB.pathToTheCapital = (listOf(cityB.name) + cityA.pathToTheCapital).toMutableList()// listRoads
        cityB.pathToTheCapitalFast = (listOf(cityB.name) + 1).toMutableList()

        true
    }
    else false
}

private class Road (val nameCityB: Int, val lengthAB: Int)
private class City(val name: Int,
                   val runUp: Int,
                   val speed: Int,
                   var minDistanceToTheCapital: Int = Int.MAX_VALUE,
                   var minTravelTimeToTheCapital: Double = Double.MAX_VALUE,
                   var listOfNeighboringCities: MutableList<Road> = mutableListOf(),
                   var pathToTheCapital: MutableList<Int> = mutableListOf(),
                   var pathToTheCapitalFast: MutableList<Int> = mutableListOf()
) {
    fun asCopy(): City {
        return City(
            name = name,
            runUp = runUp,
            speed = speed,
            minDistanceToTheCapital = minDistanceToTheCapital,
            minTravelTimeToTheCapital = minTravelTimeToTheCapital,
            listOfNeighboringCities = listOfNeighboringCities.toMutableList(),
            pathToTheCapital = pathToTheCapital.toMutableList(),
            pathToTheCapitalFast = pathToTheCapitalFast.toMutableList())
    }
}


// Вывел эти функции из классов, чтобы они там не мешались, при отправке на проверку
private fun printCity(city: City, tab: String = " :"){
    val listN: Array<String> = Array(city.listOfNeighboringCities.size){""}
    city.listOfNeighboringCities.forEachIndexed { index, it ->
        listN[index] = "n:${it.nameCityB} l:${it.lengthAB}"
    }

    println(
        "$tab name:${city.name}, r:${city.runUp}, s:${city.speed} | " +
                "d:${city.minDistanceToTheCapital} - t:${city.minTravelTimeToTheCapital} | " +
                "pC:${city.pathToTheCapital.joinToString(" ")} :: " +
                "pCF:${city.pathToTheCapitalFast.joinToString(" ")} :: " +
                "listN: [ ${listN.joinToString(", ")} ]")
}
private fun printRoad(road: Road, tab: String = " :"){
    println("$tab nameCityB:${road.nameCityB}, lengthAB:${road.lengthAB} ")
}


// BACKUP with LOGS for TEST
private fun onASleighV4WithLogs(){
    val input = BufferedReader(FileReader("input.txt"))

    // количество городов, соединенных дорогами N < 2000
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0]

    // города (index = cityName)
    val arrCities: Array<City> = Array(n + 1){ City(name = it, runUp = Int.MAX_VALUE, speed = Int.MAX_VALUE) }

    // Расстояния между городами. Будем заполнять таблицу постепенно
    val matrixOfDistancesBetweenCities: Array<IntArray> = Array(arrCities.size){ IntArray(arrCities.size){-2} }

    //!!
    matrixOfDistancesBetweenCities.forEach { it ->
        println(it.joinToString())
    }

    matrixOfDistancesBetweenCities[0] = IntArray(arrCities.size) {-1}
    repeat(arrCities.size) {
        matrixOfDistancesBetweenCities[it][0] = -1
    }

    repeat(n) { city ->
        // добавляем города, заполняя имена, подготовку и скорость саней
        // 0 ≤ run-Up ≤ 100, 1 ≤ speed ≤ 100
        val (runUp, speed) = input.readLine().trim().split(" ").map { it.toInt() }
        arrCities[city + 1] = City(city + 1, runUp, speed)
        arrCities[city + 1].pathToTheCapital.add(city + 1)
        arrCities[city + 1].pathToTheCapitalFast.add(city + 1)

        matrixOfDistancesBetweenCities[city + 1][city + 1] = 0
    }

    repeat(n - 1) { _ ->
        // добавляем дороги, A и B — номера соединенных городов, а S — расстояние между ними в километрах
        // 1 ≤ A ≤ N, 1 ≤ B ≤ N, A ≠ B, 1 ≤ S ≤ 10000
        val (cityA, cityB, roadLength) = input.readLine().trim().split(" ").map { it.toInt() }
        arrCities[cityA].listOfNeighboringCities.add(Road(cityB, roadLength))
        arrCities[cityB].listOfNeighboringCities.add(Road(cityA, roadLength))

        // Добавляем расстояние между городами в таблицу расстояний
        matrixOfDistancesBetweenCities[cityA][cityB] = roadLength
        matrixOfDistancesBetweenCities[cityB][cityA] = roadLength
    }

    // Первый проход: от столицы до остальных городов - ищем кратчайшие пути и записываем маршрут
    println("Первый проход") //!!

    // очередь городов на проверку удаленности от столицы
    val pqDistance = PriorityQueue<City> { a, b -> a.minDistanceToTheCapital - b.minDistanceToTheCapital}

    // обновляем данные столицы
    val cityCapital = arrCities[1].asCopy()
    cityCapital.minDistanceToTheCapital = 0
    cityCapital.minTravelTimeToTheCapital = 0.0
    pqDistance.add(cityCapital)
    println("=== ${cityCapital.pathToTheCapital}")

    while (pqDistance.isNotEmpty()) {
        val cityA = pqDistance.poll()

        // проверка на уже пройденные города
        if (cityA.minDistanceToTheCapital < arrCities[cityA.name].minDistanceToTheCapital) {
            // маршрут более короткий, обновляем нынешнее значение
            arrCities[cityA.name] = cityA.asCopy()
            matrixOfDistancesBetweenCities[cityA.name][1] = cityA.minDistanceToTheCapital
            matrixOfDistancesBetweenCities[1][cityA.name] = cityA.minDistanceToTheCapital

            cityA.listOfNeighboringCities.forEach { road ->
                val cityB = arrCities[road.nameCityB].asCopy()

                // если этот путь меньше уже известных - обновляем CityB
                if (comparisonDistance(cityA, cityB, road)) pqDistance.add(cityB)
            }
        }
    }

    arrCities.forEach { printCity(it) } //!!

    //!!
    matrixOfDistancesBetweenCities.forEach { it ->
        println(it.joinToString())
    }

    // Backup >
    // Второй проход. Проверяем расстояния между всеми оставшимися городами. Путь до других городов проверяем через
    // общие города / узлы / хабы в pathToTheCapital. Если "общий город" это столица, то не учитываем

    // Медленные варианты
    /*arrCities.forEach { cityA ->

        arrCities.forEach { cityB ->

            if (matrixOfDistancesBetweenCities[cityA.name][cityB.name] == -2) {
                // Если эти города еще не проверяли, то считаем дистанцию и записываем значение обоим городам.
                // Иначе пропускаем.
                val cityAPath = cityA.pathToTheCapital.toMutableList() // mutableListOf(cityA.name)
                //cityAPath += cityA.pathToTheCapital.toMutableList()

                val cityBPath = cityB.pathToTheCapital.toMutableList() // mutableListOf(cityB.name)
                //cityBPath += cityB.pathToTheCapital.toMutableList()



                var hubCity = cityAPath.last() // у всех путей есть как минимум один общий/узловой город - столица

                while (cityAPath.isNotEmpty()
                    && cityBPath.isNotEmpty()
                    && cityAPath.last() == cityBPath.last()
                ) {
                    hubCity = cityAPath.last()
                    cityAPath.removeAt(cityAPath.lastIndex)
                    cityBPath.removeAt(cityBPath.lastIndex)
                }
                cityAPath.add(hubCity)
                cityBPath.add(hubCity)

                if (hubCity == 1) {
                    // Если хаб (ближайший узловой город) - столица, то других путей нет
                    matrixOfDistancesBetweenCities[cityA.name][cityB.name] = -1
                    matrixOfDistancesBetweenCities[cityB.name][cityA.name] = -1
                }
                else {
                    // если хаб не столица, значит есть общий город и на пути к столице - работаем

                    // Считаем расстояние от города-А до хаба, и от города-Б до хаба.
                    var cityAPathDistance = 0
                    var city = 0
                    var nextCity = 1
                    while (nextCity < cityAPath.size) {
                        cityAPathDistance += matrixOfDistancesBetweenCities[cityAPath[city]][cityAPath[nextCity]]
                        city++
                        nextCity++
                    }

                    var cityBPathDistance = 0
                    city = 0
                    nextCity = 1
                    while (nextCity < cityBPath.size) {
                        cityBPathDistance += matrixOfDistancesBetweenCities[cityBPath[city]][cityBPath[nextCity]]
                        city++
                        nextCity++
                    }

                    val distanceSum = cityAPathDistance + cityBPathDistance

                    matrixOfDistancesBetweenCities[cityA.name][cityB.name] = distanceSum
                    matrixOfDistancesBetweenCities[cityB.name][cityA.name] = distanceSum

                }
            }
        }
    }*/

    /*matrixOfDistancesBetweenCities.forEachIndexed { indexA, cityAArray ->
        cityAArray.forEachIndexed { indexB, distanceBetweenCities ->
            if (distanceBetweenCities == -2) {
                // Если эти города еще не проверяли, то считаем дистанцию и записываем значение обоим городам.
                // Иначе пропускаем.
                val cityAPath = arrCities[indexA].pathToTheCapital.toMutableList() // mutableListOf(cityA.name)
                //cityAPath += cityA.pathToTheCapital.toMutableList()

                val cityBPath = arrCities[indexB].pathToTheCapital.toMutableList() // mutableListOf(cityB.name)
                //cityBPath += cityB.pathToTheCapital.toMutableList()



                var hubCity = cityAPath.last() // у всех путей есть как минимум один общий/узловой город - столица

                while (cityAPath.isNotEmpty()
                    && cityBPath.isNotEmpty()
                    && cityAPath.last() == cityBPath.last()
                ) {
                    hubCity = cityAPath.last()
                    cityAPath.removeAt(cityAPath.lastIndex)
                    cityBPath.removeAt(cityBPath.lastIndex)
                }
                cityAPath.add(hubCity)
                cityBPath.add(hubCity)

                if (hubCity == 1) {
                    // Если хаб (ближайший узловой город) - столица, то других путей нет
                    matrixOfDistancesBetweenCities[indexA][indexB] = -1
                    matrixOfDistancesBetweenCities[indexB][indexA] = -1
                }
                else {
                    // если хаб не столица, значит есть общий город и на пути к столице - работаем

                    // Считаем расстояние от города-А до хаба, и от города-Б до хаба.
                    var cityAPathDistance = 0
                    var city = 0
                    var nextCity = 1
                    while (nextCity < cityAPath.size) {
                        cityAPathDistance += matrixOfDistancesBetweenCities[cityAPath[city]][cityAPath[nextCity]]
                        city++
                        nextCity++
                    }

                    var cityBPathDistance = 0
                    city = 0
                    nextCity = 1
                    while (nextCity < cityBPath.size) {
                        cityBPathDistance += matrixOfDistancesBetweenCities[cityBPath[city]][cityBPath[nextCity]]
                        city++
                        nextCity++
                    }

                    val distanceSum = cityAPathDistance + cityBPathDistance

                    matrixOfDistancesBetweenCities[indexA][indexB] = distanceSum
                    matrixOfDistancesBetweenCities[indexB][indexA] = distanceSum

                }
            }
        }
    }*/
    // Backup ^

    // Второй проход. Проверяем расстояния между всеми оставшимися городами. Путь до других городов проверяем через
    // общие города / узлы / хабы в pathToTheCapital. Если "общий город" это столица, то не учитываем
    println("Второй проход") //!!

    for (cityI in arrCities) {
        if (cityI.name == 0 || cityI.name == 1) continue

        val distances = LinkedList<Pair<City, Int>>()
        distances.add(cityI to 0)

        val visitedCities = HashSet<City>()


        // Проверяем все пути ведущие из нынешнего cityI во все другие города
        while (distances.isNotEmpty()) {
            val cityMove = distances.poll()
            visitedCities.add(cityMove.first)

            cityMove.first.listOfNeighboringCities.forEach { road ->
                val cityNeighbor = arrCities[road.nameCityB]

                if (!visitedCities.contains(cityNeighbor) && road.nameCityB != 1) {

                    val distanceCityIToCityNeighbor = cityMove.second + road.lengthAB
                    distances.add(cityNeighbor to distanceCityIToCityNeighbor)

                    if (matrixOfDistancesBetweenCities[cityI.name][cityNeighbor.name] == -2) {
                        matrixOfDistancesBetweenCities[cityI.name][cityNeighbor.name] = distanceCityIToCityNeighbor
                    }
                }
            }
        }

        // Отдельно все непроверенные города ведущие через столицу помечаем как не имеющие пути из cityI (-1)
        distances.add(cityCapital to 0)
        while (distances.isNotEmpty()) {
            val cityMove = distances.poll()
            visitedCities.add(cityMove.first)

            cityMove.first.listOfNeighboringCities.forEach { road ->
                val cityNeighbor = arrCities[road.nameCityB]

                if (!visitedCities.contains(cityNeighbor)) {
                    distances.add(cityNeighbor to 0)

                    if (matrixOfDistancesBetweenCities[cityI.name][cityNeighbor.name] == -2) {
                        matrixOfDistancesBetweenCities[cityI.name][cityNeighbor.name] = -1
                    }
                }
            }
        }
    }

    //!!
    matrixOfDistancesBetweenCities.forEach { it ->
        println(it.joinToString())
    }

    arrCities.forEach { printCity(it) } //!!



    // Третий проход. Проверяем все города начиная с самого меньшего, по принципу: если есть города быстрее нынешнего,
    // то можем ли ускориться добравшись до них.
    println("Третий проход") //!!
    val pqTime = PriorityQueue<City>{ a, b -> (a.minTravelTimeToTheCapital - b.minTravelTimeToTheCapital).toInt()}
    arrCities.forEach { pqTime.add(it) }
    pqTime.poll() // удаляем столицу - она нам не понадобиться


    val fasterCities = mutableListOf<City>()//PriorityQueue<City>{ a, b -> (a.minTravelTimeToTheCapital - b.minTravelTimeToTheCapital).toInt()}
    //val fastList: MutableList<City> = mutableListOf()

    val fastestCity = pqTime.poll()
    println("fastestCity: ${fastestCity.pathToTheCapitalFast}") //!!
    //fastestCity.pathToTheCapitalFast = (listOf(fastestCity.name) + 1).toMutableList()
    //fastestCity.pathToTheCapitalFast = (listOf(fastestCity.name) + fastestCity.pathToTheCapitalFast).toMutableList()
    fasterCities.add(fastestCity) // добавляем самый быстрый город (он все равно не может быть ускорен)
    println("fastestCity: ${fastestCity.pathToTheCapitalFast}, ${arrCities[fastestCity.name].pathToTheCapitalFast}") //!!

    pqTime.forEach { println(it.name) } //!!


    while (pqTime.isNotEmpty()) {
        val cityA = pqTime.poll() // Проверяемый город.



        println(" cityA: ${cityA.name}") //!!
        if (cityA.name == 0) continue

        // Проверяем каждый город который быстрее нынешнего, можем ли за его счёт добраться до столицы быстрее
        val minCityA = cityA.asCopy()
        fasterCities.forEach { fasterCity ->
            println(" - fCity: ${fasterCity.name}") //!!
            val distanceBetweenCities = matrixOfDistancesBetweenCities[cityA.name][fasterCity.name]
            if (distanceBetweenCities > 0 &&
                fasterCity.minTravelTimeToTheCapital < minCityA.minTravelTimeToTheCapital) {
                // если этот город быстрее "Проверяемого города", тогда проверяем можем ли до быстро него добраться
                // иначе ничего не делаем

                val travelTimeCityAToFasterCity: Double = cityA.runUp + (distanceBetweenCities.toDouble() / cityA.speed)
                val newTravelTimeCityAToCapital = travelTimeCityAToFasterCity + fasterCity.minTravelTimeToTheCapital

                if (newTravelTimeCityAToCapital < minCityA.minTravelTimeToTheCapital){
                    //если можем ускориться и это будет быстрее уже найденного minCityA, то обновляем его
                    minCityA.minTravelTimeToTheCapital = newTravelTimeCityAToCapital
                    minCityA.pathToTheCapitalFast = (listOf(minCityA.name) + fasterCity.pathToTheCapitalFast).toMutableList()

                    println("minCityA: mT: ${minCityA.minTravelTimeToTheCapital}, pF: ${minCityA.pathToTheCapitalFast}") //!!
                }
            }
            else println("-1") //!!
        }

        if (minCityA.minTravelTimeToTheCapital < cityA.minTravelTimeToTheCapital) {
            // Если ускориться удалось, обновляем маршрут
            cityA.minTravelTimeToTheCapital = minCityA.minTravelTimeToTheCapital
            cityA.pathToTheCapitalFast = minCityA.pathToTheCapitalFast
            fasterCities.add(cityA)
        }
        else {
            // Иначе едем прямиком в столицу
            //cityA.pathToTheCapitalFast = (listOf(cityA.name) + 1).toMutableList()
            fasterCities.add(cityA)
        }

        // эта доп проверка сильно тормозит
        //val updateFasterCity = mutableListOf<City>()
        fasterCities.forEachIndexed { index, city ->
            val distanceBetweenCities = matrixOfDistancesBetweenCities[cityA.name][city.name]
            if (
                distanceBetweenCities > 0 &&
                cityA.minTravelTimeToTheCapital < city.minTravelTimeToTheCapital
            ) {

                // если этот город быстрее "Проверяемого города", тогда проверяем можем ли до быстро него добраться
                // иначе ничего не делаем

                val travelTimeCityToCityA: Double = city.runUp + (distanceBetweenCities.toDouble() / city.speed)
                val newTravelTimeCityToCapital = travelTimeCityToCityA + cityA.minTravelTimeToTheCapital

                if (newTravelTimeCityToCapital < city.minTravelTimeToTheCapital){
                    //если можем ускориться и это будет быстрее уже найденного, то обновляем
                    city.minTravelTimeToTheCapital = newTravelTimeCityToCapital
                    city.pathToTheCapitalFast =
                        (listOf(city.name) + cityA.pathToTheCapitalFast).toMutableList()
                }

                // Если есть города медленнее cityA, отправляем их на новую проверку
                //pqTime.add(fasterCities[index]) // На новый заход
            }
            else {
                //updateFasterCity.add(fasterCities[index])
            }
        }
        //fasterCities = updateFasterCity
    }

    arrCities.forEach { printCity(it) } //!!

    var answer = -1.0 to mutableListOf<Int>()
    for (i in 1 .. arrCities.lastIndex) {
        val city = arrCities[i]
        if (city.minTravelTimeToTheCapital > answer.first) {
            //val newPath = mutableListOf(city.name) + city.pathToTheCapitalFast

            answer = city.minTravelTimeToTheCapital to city.pathToTheCapitalFast //newPath.toMutableList()
        }
    }
    /*println("${answer.first}\n" +
            answer.second.joinToString(" ")
    )*/

    //val roundOff = String.format("%.10f", answer.first).replace(',', '.')
    //println(roundOff)
    //println(answer.second.joinToString(" "))

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write("${answer.first}\n" + answer.second.joinToString(" "))
    output.flush()
}
