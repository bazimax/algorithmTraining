package yandex.algorithms4training.warmup

/**
 * J. Групповой проект
 *
 * Всего студентов по направлению «Мировая культура» — n человек. Преподаватель дал задание — групповой проект.
 * Для выполнения этого задания студенты должны разбиться на группы численностью от a до b человек. Скажите, можно
 * ли разбить всех студентов на группы для выполнения проекта или преподаватель что-то перепутал.
 */


import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    groupProjectV3()
}

//18:30 - 19:10 (40мин) > notOK частичное решение (234ms, 15.21Mb, не прошел тест 2)
fun groupProject(){
    val input = BufferedReader(FileReader("input.txt"))
    val t = input.readLine().split(" ").map { it.toInt() }[0] //(1 ≤ t ≤ 100)


    var answer = ""

    repeat(t){
        var tempAns = ""
        //students, a и b (1 ≤ n ≤ 10^9, 1 ≤ a ≤ b ≤ n)
        val (students, a, b) = input.readLine().split(" ").map { it.toInt() }

        var remainder = students % b
        if (remainder == 0 || remainder in a .. b) tempAns = "YES"
        else {
            var bigGroups = students / b

            loop@ while (bigGroups > -1) {

                remainder = if (bigGroups != 0) students - (bigGroups * b) else students
                remainder %= a
                if (remainder == 0 || remainder in a..b) {
                    tempAns = "YES"
                    break@loop
                }
                else tempAns = "NO"
                bigGroups--
            }
        }
        //println("n: $students, a: $a, b: $b ans: $tempAns")
        answer = "$answer$tempAns\n"
    }

    //println(answer)
    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

//17:10 -
fun groupProjectV2(){
    val input = BufferedReader(FileReader("input.txt"))
    val t = input.readLine().split(" ").map { it.toInt() }[0] //(1 ≤ t ≤ 100)

    var answer = ""

    repeat(t){
        //students, a и b (1 ≤ n ≤ 10^9, 1 ≤ a ≤ b ≤ n)
        val (students, a, b) = input.readLine().split(" ").map { it.toInt() }

        val minGroup = min(a, b)
        val maxGroup = max(a, b)

        val divider = (students % minGroup) / (students / minGroup)

        val tempAns = if (minGroup + divider <= maxGroup) "YES" else "NO"

        answer = "$answer$tempAns\n"
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

//OK (243ms, 15.76Mb)
fun groupProjectV3(){
    val input = BufferedReader(FileReader("input.txt"))
    val t = input.readLine().split(" ").map { it.toInt() }[0] //(1 ≤ t ≤ 100)

    var answer = ""

    repeat(t){
        //students:n, a и b (1 ≤ n ≤ 10^9, 1 ≤ a ≤ b ≤ n)
        val (students, a, b) = input.readLine().split(" ").map { it.toULong() }

        val tempAns = if (students % a <= (students / a) * (b - a)) "YES" else "NO"

        answer = "$answer$tempAns\n"
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}