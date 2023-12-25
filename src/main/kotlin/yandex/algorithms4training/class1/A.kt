package yandex.algorithms4training.class1

/**
 * A. Partition
 *
 * Базовым алгоритмом для быстрой сортировки является алгоритм partition, который разбивает набор элементов на две
 * части относительно заданного предиката. По сути элементы массива просто меняются местами так, что левее
 * некоторой точки в нем после этой операции лежат элементы, удовлетворяющие заданному предикату, а справа —
 * не удовлетворяющие ему.
 * Например, при сортировке можно использовать предикат «меньше опорного», что при оптимальном выборе опорного элемента
 * может разбить массив на две примерно равные части.
 *
 * Напишите алгоритм partition в качестве первого шага для написания быстрой сортировки.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    partition()
}


// 0:40 - 2:07 (1ч27мин) > OK (1.045s, 116.28Mb)
fun partition(){
    //версия для быстрой сортировки (не устойчивая)

    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().split(" ").map { it.toInt() }[0] //number of array elements //0 ≤ n ≤ 10^6

    var answer = ""

    if (n == 0) answer = "0\n0"
    else {
        val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray() //-10^9 ≤ arr ≤ 10^9
        val x = input.readLine().split(" ").map { it.toInt() }[0] //predicate //-10^9 ≤ x ≤ 10^9

        var equalsX = -1
        var moreThanX = -1
        arr.forEachIndexed { index, i ->
            when {
                //i < x && equalsX == -1 && moreThanX == -1 -> {} //nothing do
                i < x && equalsX == -1 && moreThanX != -1 -> {
                    arr[index] = arr[moreThanX]
                    arr[moreThanX] = i
                    moreThanX++
                }
                i < x && equalsX != -1 && moreThanX == -1 -> {
                    arr[index] = arr[equalsX]
                    arr[equalsX] = i
                    equalsX++
                }
                i < x && equalsX != -1 && moreThanX != -1 -> {
                    arr[index] = arr[moreThanX]
                    arr[moreThanX] = arr[equalsX]
                    arr[equalsX] = i
                    moreThanX++
                    equalsX++
                }
                i == x && equalsX == -1 && moreThanX == -1 -> {
                    equalsX = index
                }
                i == x && equalsX == -1 && moreThanX != -1 -> {
                    arr[index] = arr[moreThanX]
                    arr[moreThanX] = i
                    equalsX = moreThanX
                    moreThanX++
                }
                i == x && equalsX != -1 && moreThanX != -1 -> {
                    arr[index] = arr[moreThanX]
                    arr[moreThanX] = i
                    moreThanX++
                }
                i > x && equalsX == -1 && moreThanX == -1 -> {
                    moreThanX = index
                }
                i > x && equalsX != -1 && moreThanX == -1 -> {
                    moreThanX = index
                }
                //i > x && equalsX != -1 && moreThanX != -1 -> {} //nothing do
            }
            //println("${arr.joinToString(" ")} | e: $equalsX, g: $moreThanX, n: $index")
        }

        answer = when {
            equalsX == -1 && moreThanX == -1 -> "$n\n0"
            equalsX != -1 && moreThanX == -1 -> "$equalsX\n${n - equalsX}"
            equalsX == -1 && moreThanX != -1 -> "$moreThanX\n${n - moreThanX}"
            else -> "$equalsX\n${n - equalsX}" //equalsX != -1 && moreThanX != -1
        }
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

// 2:10 - 2:45 (35мин) > OK (1.301s, 134.63Mb)
fun partitionV2(){
    //версия для сортировки слиянием (устойчивая)

    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().split(" ").map { it.toInt() }[0] //number of array elements //0 ≤ n ≤ 10^6
    val sortedArr = IntArray(n + 1) //последний элемент массива для индекса разделителя (predicate)

    var answer = ""

    if (n == 0) answer = "0\n0"
    else {
        val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray() //-10^9 ≤ arr[i] ≤ 10^9
        val x = input.readLine().split(" ").map { it.toInt() }[0] //predicate //-10^9 ≤ x ≤ 10^9

        var lassThanX = 0 //left pointer
        var moreThanX = n //right pointer

        arr.forEach { i ->
            if (i < x) {
                sortedArr[lassThanX] = i
                lassThanX++
            }
            else {
                sortedArr[moreThanX] = i
                moreThanX--
            }

            sortedArr[n] = moreThanX //индекс разделителя (predicate)

            answer = "$moreThanX\n${n - moreThanX}"

            //println("${arr.joinToString(" ")} | e: $equalsX, g: $moreThanX, n: $index")
        }
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

// OK - быстрое, но лукавое решение - нет сортировки, только подсчет (0.995s, 115.90Mb)
fun partitionNotTrue(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().split(" ").map { it.toInt() }[0] //0 ≤ n ≤ 10^6

    var answer = ""

    answer = if (n == 0) {
        "0\n0"
    }
    else {
        val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray() //-10^9 ≤ arr ≤ 10^9
        val x = input.readLine().split(" ").map { it.toInt() }[0] //predicate //-10^9 ≤ x ≤ 10^9

        var count = 0

        arr.forEach {
            if (it < x) count++
        }

        "$count\n${n - count}"
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

