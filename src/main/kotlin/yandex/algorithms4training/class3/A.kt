package yandex.algorithms4training.class3

/**
 * A. Дейкстра //Dijkstra
 *
 * Дан ориентированный взвешенный граф. Найдите кратчайшее расстояние от одной заданной вершины до другой.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import yandex.*
import java.util.PriorityQueue

fun main(args: Array<String>) {
    //dijkstra()
    runTest("dijkstra", examplesA, ::dijkstra, 2)
}

private val examplesA = arrayOf(
    Pair("3 2 1\n" +
            "0 1 1\n" +
            "4 0 1\n" +
            "2 1 0",
        "3"),
    Pair("1 1 1\n" +
            "0",
        "0"),
    Pair("4 1 4\n" +
            "0 4 1 -1\n" +
            "-1 0 -1 1\n" +
            "-1 1 0 6\n" +
            "-1 -1 -1 -1",
        "3"),
    Pair("4 1 4\n" +
            "0 4 1 -1\n" +
            "-1 0 -1 -1\n" +
            "-1 1 0 -1\n" +
            "-1 -1 -1 -1",
        "-1"),
    Pair("4 1 4\n" +
            "0 4 1 -1\n" +
            "-1 0 -1 -1\n" +
            "-1 1 0 -1\n" +
            "-1 -1 -1 -1",
        "-1"),
    Pair("10 4 4\n" +
            "0 -1 -1 -1 -1 -1 -1 -1 -1 5 \n" +
            "-1 0 5 -1 -1 -1 -1 3 -1 -1 \n" +
            "-1 -1 0 -1 -1 5 -1 -1 -1 3 \n" +
            "-1 -1 -1 0 -1 -1 -1 -1 -1 -1 \n" +
            "-1 -1 -1 -1 0 -1 -1 -1 10 -1 \n" +
            "-1 -1 -1 -1 -1 0 -1 -1 -1 5 \n" +
            "-1 -1 -1 -1 -1 10 0 -1 -1 -1 \n" +
            "-1 -1 -1 -1 -1 -1 -1 0 -1 -1 \n" +
            "-1 -1 -1 -1 -1 -1 -1 2 0 -1 \n" +
            "-1 -1 -1 -1 -1 -1 -1 -1 2 0 ",
        "0"),
    Pair("2 1 2 \n" +
            "0 -1\n" +
            "-1 0",
        "-1"),
    //Pair("12", "2"),
)

// 4:25 - (перерыв 10мин) - 6:00 (1ч25мин) > OK  (311ms, 18.49Mb)
private fun dijkstra(){
    val input = BufferedReader(FileReader("input.txt"))
    val (n, start, finish) = input.readLine().trim().split(" ").map { it.toInt() }

    val ans =
        if (start == finish) "0"
        else moving(n, start, finish, input)


    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

private fun moving(n: Int, start: Int, finish: Int, input: BufferedReader) : String {

    val arr = Array(n + 1){IntArray(n + 1)}
    arr[0] = IntArray(n + 1) {-1}

    repeat(n) { item ->
        arr[item + 1] = "-1 ${input.readLine()}".trim().split(" ").map { it.toInt() }.toIntArray()
    }

    val arrNum = IntArray(n + 1) { -1 }
    arrNum[start] = 0

    val pq = PriorityQueue<Int>{ a, b -> a - b}
    pq.add(start)


    while (pq.isNotEmpty()) {
        val vertex = pq.poll()
        val vertexValue = arrNum[vertex]


        arr[vertex].forEachIndexed { vertexNext, i ->

            if (i != -1 && vertexNext != vertex && vertexNext != start) {
                val sum = i + vertexValue
                //println("- v: $vertex($vertexValue), vI: $vertexNext($i), sum: $sum")

                if (arrNum[vertexNext] > sum || arrNum[vertexNext] == -1) {
                    arrNum[vertexNext] = sum

                    if (vertexNext != finish) pq.add(vertexNext)
                }
            }
        }
    }

    return arrNum[finish].toString()
}


