package yandex.algorithms4training.warmup

/**
 * H. Результаты контеста
 *
 * Чтобы оценить качество обучения программированию, в каждой группы студентов подсчитывается один параметр — суммарное
 * количество решенных студентами задач. Известно, что в первой группе суммарное количество решенных на контесте задач
 * равно a, а во второй — b. Всего на контесте было предложено n задач, а также известно, что каждый студент решил
 * не менее одной (и не более n) задач.
 *
 * По заданным a, b и n определите, могло ли в первой группе быть строго больше студентов, чем во второй.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    contestResultsV2()
}

// 17:00 - 17:05 (5мин) > OK (220ms, 15.14Mb)
fun contestResultsV2(){
    val input = BufferedReader(FileReader("input.txt"))

    //a, b, n (1 ≤ a, b, n ≤ 10000)
    val a = input.readLine().split(" ").map { it.toInt() }[0] //total number of solved tasks - group A
    val b = input.readLine().split(" ").map { it.toInt() }[0] //total number of solved tasks - group B
    val n = input.readLine().split(" ").map { it.toInt() }[0] //total tasks

    val minStudentsB = if (b % n == 0) b / n else (b / n) + 1

    val answer = if (a > minStudentsB) "Yes" else "No"

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

// 14:55 - 15:15 (20мин) > notOK частичное решение (212ms, 15.21Mb, не прошел тест 11)
fun contestResults(){
    val input = BufferedReader(FileReader("input.txt"))

    //a, b, n (1 ≤ a, b, n ≤ 10000)
    val a = input.readLine().split(" ").map { it.toInt() }[0] //total number of solved tasks - group A
    val b = input.readLine().split(" ").map { it.toInt() }[0] //total number of solved tasks - group B
    val n = input.readLine().split(" ").map { it.toInt() }[0] //total tasks

    val minStudentsA = if (a % n == 0) a / n else (a / n) + 1
    val minStudentsB = if (b % n == 0) b / n else (b / n) + 1

    val answer = if (minStudentsA > minStudentsB) "Yes" else "No"

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}


