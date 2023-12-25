package yandex.algorithms4training.class3

/**
 * B. Дейкстра с восстановлением пути
 *
 * Дан ориентированный взвешенный граф. Найдите кратчайший путь от одной заданной вершины до другой.
 */
import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.PriorityQueue

fun main(args: Array<String>) {
    // dijkstraWithPathRestoration()
    runTest("dijkstraWithPathRestoration", examplesB, ::dijkstraWithPathRestoration, )
}

private val examplesB = arrayOf(
    Pair("3 2 1\n" +
            "0 1 1\n" +
            "4 0 1\n" +
            "2 1 0",
        "2 3 1"),
    Pair("1 1 1\n" +
            "0",
        "1"),
    Pair("4 1 4\n" +
            "0 4 1 -1\n" +
            "-1 0 -1 1\n" +
            "-1 1 0 6\n" +
            "-1 -1 -1 -1",
        "1 3 2 4"),
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
        "4"),
    Pair("2 1 2 \n" +
            "0 -1\n" +
            "-1 0",
        "-1"),
    //Pair("12", "2"),
)

// 6:05 - 7:00 (55мин) > OK (325ms, 18.39Mb)
private fun dijkstraWithPathRestoration(){
    val input = BufferedReader(FileReader("input.txt"))
    val (n, start, finish) = input.readLine().trim().split(" ").map { it.toInt() }

    val ans =
        if (start == finish) start.toString()
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

    val arrNum = Array(n + 1) { -1 to -1 } //значение ячейки - предыдущая ячейка, если нет то -1 (у старта её нет)
    arrNum[start] = 0 to -1

    val pq = PriorityQueue<Int>{ a, b -> a - b}
    pq.add(start)

    while (pq.isNotEmpty()) {
        val vertex = pq.poll()
        val vertexValue = arrNum[vertex]


        arr[vertex].forEachIndexed { vertexNext, i ->

            if (i != -1 && vertexNext != vertex && vertexNext != start) {
                val sum = i + vertexValue.first
                //println("- v: $vertex($vertexValue), vI: $vertexNext($i), sum: $sum")

                if (arrNum[vertexNext].first > sum || arrNum[vertexNext].first == -1) {
                    arrNum[vertexNext] = sum to vertex
                    //println(" - $vertexNext = $sum to $vertex")

                    if (vertexNext != finish) pq.add(vertexNext)
                }
            }
        }
    }

    val listTemp = mutableListOf<Int>()
    var next = finish


    do {
        listTemp.add(next)
        next = arrNum[next].second
    }
    while (next != -1)


    val list = listTemp.reversed()
    return if (list.first() != start) "-1" else list.joinToString(" ")
}

