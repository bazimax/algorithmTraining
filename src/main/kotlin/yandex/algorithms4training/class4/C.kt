package yandex.algorithms4training.class4

/**
 * C. Максимальный разрез
 *
 * Взвешенный неориентированный граф без петель задан матрицей смежности. Распределите вершины по двум долям так,
 * чтобы сумма весов рёбер, соединяющих вершины из разных долей, была максимальна.
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    //maximalCut()
    runTest("maximalCut", examplesC, ::maximalCut, )
    runOneTest("maximalCut", ::maximalCut, "y_4_4_C_13_input.txt", "y_4_4_C_13_correctAns.txt")
}

private val examplesC = arrayOf(
    Pair("2\n" +
            "0 1\n" +
            "1 0",
        "1\n" +
                "2 1"), //1
    Pair("3\n" +
            "0 1 2\n" +
            "1 0 2\n" +
            "2 2 0",
        "4\n" +
                "2 2 1"), //2
    Pair("4\n" +
            "0 10 3 0\n" +
            "10 0 7 2\n" +
            "3 7 0 9\n" +
            "0 2 9 0",
        "26\n" +
                "2 1 2 1"), //3
    Pair("2\n" +
            "0 0\n" +
            "0 0",
        "0\n" +
                "2 1"),
    Pair("5\n" +
            "0 1 1 1 1\n" +
            "1 0 1 1 1\n" +
            "1 1 0 1 1\n" +
            "1 1 1 0 1\n" +
            "1 1 1 1 0\n",
        "6\n" +
                "2 2 1 1 1"),
    //Pair("12", "2"),
)

// 21:50 - 1:45 (3ч55мин) > OK (0.695s, 38.97Mb)
private fun maximalCut(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] // 2 ≤ N ≤ 20

    val matrix = Array(n + 1){ IntArray(n + 1) }
    repeat(n) { item ->
        matrix[item + 1] = ("0 " + input.readLine()).trim().split(" ").map { it.toInt() }.toIntArray()
    }


    val half0 = mutableListOf<Int>()
    val half1 = List(n){it + 1}.toMutableList()

    val ans = nextSet(half0, half1, 1, matrix, 0)


    val arrGroup = IntArray(n)
    ans.first.forEach { arrGroup[it - 1] = 2 }
    ans.second.forEach { arrGroup[it - 1] = 1 }

    val answer = "${ans.third}\n" + arrGroup.joinToString(" ")

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

private fun nextSet(half0: MutableList<Int>, half1: MutableList<Int>, num: Int, matrix: Array<IntArray>, sum : Int): Triple<MutableList<Int>, MutableList<Int>, Int>{

    val half0Next = half0.toMutableList()
    val half1Next = half1.toMutableList()
    val newSet = swap(half0Next, half1Next, num, matrix, sum)

    var bestSet = newSet

    if (newSet.second.size > 1) {

        newSet.second.forEach { newNum ->

            if (newNum > num) {
                val nextSet = nextSet(newSet.first, newSet.second, newNum, matrix, newSet.third)

                if (nextSet.third > bestSet.third) {
                    bestSet = Triple(nextSet.first, nextSet.second, nextSet.third)
                }
            }
        }
    }

    return Triple(bestSet.first, bestSet.second, bestSet.third)
}

private fun swap(half0: MutableList<Int>, half1: MutableList<Int>, num: Int, matrix: Array<IntArray>, sum : Int): Triple<MutableList<Int>, MutableList<Int>, Int> {

    //считаем новую сумму после перестановки num из half1 в half0
    var sumNext = sum

    half0.forEach { sumNext -= matrix[num][it] }
    half1.forEach { sumNext += matrix[num][it] }

    //переносим num из одной группы в другую
    half0.add(num)
    half1.remove(num)

    return Triple(half0, half1, sumNext)
}

// BACKUP with LOGS for TEST
/*
//matrix.forEach { println(it.joinToString(" ")) }
//println("= ${ans.first} - ${ans.second} - ${ans.third}")

private fun nextSet(half0: MutableList<Int>, half1: MutableList<Int>, num: Int, matrix: Array<IntArray>, sum : Int): Triple<MutableList<Int>, MutableList<Int>, Int>{

    //println("nextSet:$num  $sum   $half0 - $half1")
    val half0Next = half0.toMutableList()
    val half1Next = half1.toMutableList()
    val newSet = swap(half0Next, half1Next, num, matrix, sum)

    var bestSet = newSet

    if (newSet.second.size > 1) {

        //println("if: $num ${newSet.third}        ${newSet.first} - ${newSet.second}   | ${newSet.second.size}")

        val size = newSet.second.size - 1 //half1
        val vertexes = newSet.second.toList()

        newSet.second.forEach { newNum ->
            //println(" ".repeat(num) + "eachI: $num $i")
            //println(" ".repeat(num) + "each:  $num ${vertexes[i]}")

            if (newNum > num) {
                val nextSet = nextSet(newSet.first, newSet.second, newNum, matrix, newSet.third)

                if (nextSet.third > bestSet.third) {
                    //println("!!! if swap  ${bestSet.third} / ${nextSet.third}   ${bestSet.first} - ${bestSet.second} | ${nextSet.first} - ${nextSet.second}  |  nn: $newNum, n: $num")
                    bestSet = Triple(nextSet.first.toMutableList(), nextSet.second.toMutableList(), nextSet.third)
                }
            }

            //println(" ".repeat(num) + "eachE: $num")
        }
        //println("end Foreach: $num")

    }

    //println("nextSetE:$num, ${bestSet.third}  ${bestSet.first} - ${bestSet.second}")
    return Triple(bestSet.first, bestSet.second, bestSet.third)
}

private fun swap(half0: MutableList<Int>, half1: MutableList<Int>, num: Int, matrix: Array<IntArray>, sum : Int): Triple<MutableList<Int>, MutableList<Int>, Int> {
    //println(" swap:  $num, $sum   $half0 - $half1")

    //считаем новую сумму после перестановки num из half1 в half0
    var sumNext = sum

    half0.forEach {
        sumNext -= matrix[num][it]
        //println("  -$it ${matrix[num][it]}")
    }
    half1.forEach {
        sumNext += matrix[num][it]
        //println("  +$it ${matrix[num][it]}")
    }
    //переносим num из одной группы в другую
    half0.add(num)
    half1.remove(num)
    //println(" swapE: $num, $sumNext   $half0 - $half1")
    return Triple(half0, half1, sumNext)
}*/
