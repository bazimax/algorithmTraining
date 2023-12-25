package tinkoff.eternalcontest

/**
 * 3 задание
 *
 * У Кати насыщенный день на работе. Ей надо передать n разных договоров коллегам. Все встречи происходят на разных
 * этажах, а между этажами можно перемещаться только по лестничным пролетам — считается, что это улучшает физическую
 * форму сотрудников. Прохождение каждого пролета занимает ровно 1 минуту.
 *
 * Сейчас Катя на парковочном этаже, планирует свой маршрут. Коллег можно посетить в любом порядке, но один из них
 * покинет офис через t минут. С парковочного этажа лестницы нет — только лифт, на котором можно подняться на
 * любой этаж.
 */

//import tinkoff.runTest
import java.util.*
import kotlin.math.min

fun main(args: Array<String>) {
    elevator()
    //runTest("elevator", examplesC, ::elevatorForTesting, )
}

private val examplesC = arrayOf(
    Pair("5  5\n" +
            "1  4  9  16  25\n" +
            "2",
        "24"), // 1
    Pair("6  4\n" +
            "1  2  3  6  8  25\n" +
            "5",
        "31"), // 2
    Pair("2  2\n" +
            "1  2\n" +
            "2",
        "1"),
    Pair("2  1\n" +
            "1  2\n" +
            "2",
        "1"),
    Pair("2  1\n" +
            "1  5\n" +
            "2",
        "4"),
    //Pair("12", "2"),
)

//0:00 - 0:00 (0ч0мин) > notOK частичное решение (285ms, 20.96Mb, не прошел тест 19)
//0:00 - 0:00 (0мин) поправил > OK (274ms, 21.00Mb, 6 баллов)
private fun elevator(){
    val scan = Scanner(System.`in`)
    val (n, t) = scan.nextLine().split("  ").map { it.toInt() }
    val floors = scan.nextLine().split("  ").map { it.toInt() }
    val employeeT = scan.nextInt()

    val employeeTFloor = floors[employeeT - 1]

    val answer = if (t >= floors[floors.lastIndex] - employeeTFloor || t >= employeeTFloor - floors.first()) {
        floors[floors.lastIndex] - floors.first()
    }
    else {
        val temp = min(floors[floors.lastIndex] - employeeTFloor, employeeTFloor - floors.first())
        floors[floors.lastIndex] - floors.first() + temp
    }

    println(answer)
}

// BACKUP with LOGS for TEST
private fun elevatorForTesting(str: String) : String {
    /*val scan = Scanner(System.`in`)
    val (n, t) = scan.nextLine().split("  ").map { it.toInt() }
    val floors = scan.nextLine().split("  ").map { it.toInt() }
    val employeeT = scan.nextInt()*/

    val input = str.split("\n")
    val (n, t) = input[0].split("  ").map { it.toInt() }
    val floors = input[1].split("  ").map { it.toInt() }
    val employeeT = input[2].toInt()

    //println("n: $n, t: $t")
    //println(floors)
    //println("${floors.first()} | ${floors[0]} - ${floors[floors.lastIndex]}")
    //println(employeeT)

    val employeeTFloor = floors[employeeT - 1]
    //println("eT: $employeeTFloor")

    val answer = if (t >= floors[floors.lastIndex] - employeeTFloor || t >= employeeTFloor - floors.first()) {
        floors[floors.lastIndex] - floors.first()
    }
    else {
        val temp = min(floors[floors.lastIndex] - employeeTFloor, employeeTFloor - floors.first())
        floors[floors.lastIndex] - floors.first() + temp
    }

    //println(answer)
    return answer.toString()
}