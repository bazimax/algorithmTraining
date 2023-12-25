package yandex.algorithms4training.class4

/**
 * D. Простая задача коммивояжера //A simple salesman's problem
 *
 * Неориентированный взвешенный граф задан матрицей смежности. Найдите кратчайший цикл, который начинается и
 * заканчивается в вершине номер 1 и проходит через все вершины по одному разу.
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.min

fun main(args: Array<String>) {
    // aSimpleSalesmanProblem()
    runTest("aSimpleSalesmanProblem", examplesD, ::aSimpleSalesmanProblem, )
}

private val examplesD = arrayOf(
    Pair("1\n" +
            "0",
        "0"), //1
    Pair("2\n" +
            "0 1\n" +
            "1 0",
        "2"), //2
    Pair("2\n" +
            "0 85 \n" +
            "85 0 ",
        "170"), //3
    Pair("2\n" +
            "0 0 \n" +
            "0 0 ",
        "-1"),
    Pair("3\n" +
            "0 0 2\n" +
            "0 0 4\n" +
            "2 4 0",
        "-1"),
    Pair("3\n" +
            "0 1 3\n" +
            "1 0 2\n" +
            "3 2 0",
        "6"),
    Pair("4\n" +
            "0 1 2 4\n" +
            "1 0 1 2\n" +
            "2 1 0 2\n" +
            "4 2 2 0",
        "7"),
    Pair("8\n" +
            "0 441 390 166 110 326 146 324 \n" +
            "441 0 256 348 269 168 185 270 \n" +
            "390 256 0 27 16 310 99 23 \n" +
            "166 348 27 0 194 202 419 301 \n" +
            "110 269 16 194 0 162 93 25 \n" +
            "326 168 310 202 162 0 215 388 \n" +
            "146 185 99 419 93 215 0 444 \n" +
            "324 270 23 301 25 388 444 0 ",
        "2"),
    Pair("8\n" +
            "0 1 1 1 1 1 1 1 \n" +
            "1 0 1 1 1 1 1 1 \n" +
            "1 1 0 1 1 1 1 1 \n" +
            "1 1 1 0 1 1 1 1 \n" +
            "1 1 1 1 0 1 1 1 \n" +
            "1 1 1 1 1 0 1 1 \n" +
            "1 1 1 1 1 1 0 1 \n" +
            "1 1 1 1 1 1 1 0 ",
        "2"),
    //Pair("12", "2"),
)

// ~14:00 - 17:30 (3ч30мин) > OK (0.731s, 39.58Mb)
private fun aSimpleSalesmanProblem(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] //N ≤ 10 //количество вершин графа

    val ans = move(input, n).toString()

    println(" ans: $ans")

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

private fun move(input: BufferedReader, n: Int) : Int{
    if (n == 1) return 0

    val matrix = Array(n + 1){ IntArray(n + 1)}
    repeat(n) { r ->
        matrix[r + 1] = "0 ${input.readLine().trim()}".split(" ").map { it.toInt() }.toIntArray()
    }

    //Жадный алгоритм для поиска best-пути и потом отсечением всего что потенциально больше

    //вершины для посещения
    val setOfVisits = HashSet<Int>()
    repeat(n) {setOfVisits.add(it + 1)}

    //очередь проверок вершин
    val vertexQueue = Stack<SalesmanVertex>()
    vertexQueue.add(SalesmanVertex(1, 0)) //стартовая вершина

    var bestPath = Int.MAX_VALUE

    //проверяем все варианты
    loop@ while (vertexQueue.isNotEmpty()) {
        val vertex = vertexQueue.peek()


        if (setOfVisits.contains(vertex.name)) { //&& vertex.first != 1
            //если еще не посещали эту вершину и не проверяли её и соседние, то работаем
            setOfVisits.remove(vertex.name)
        }
        else {
            //иначе мы уже все проверили и она не подходит, удаляем из очереди
            setOfVisits.add(vertex.name)
            vertexQueue.pop()
            continue@loop
        }

        if (setOfVisits.isEmpty()) {
            //если сет пуст, то мы проверили все вершины и должны вернуться в вершину 1


            if (matrix[vertex.name][1] > 0) {
                //если путь в вершину 1 есть, мы нашли нужный нам путь
                bestPath = min(bestPath, vertex.totalPathLength + matrix[vertex.name][1])
            }
            else {
                //иначе этот путь не ведет обратно в вершину 1 и нам не подходит
                vertexQueue.pop()
            }

        }
        else {
            //иначе добавляем новые вершины в стек на проверку

            //добавляем все пути ведущие в соседние вершины ранжируя по расстоянию до них, чтобы в последствии
            //первым брать ближайшую вершину
            val pqTemp = PriorityQueue<SalesmanVertex> { a, b ->  a.totalPathLength - b.totalPathLength}
            repeat(n) { r ->
                val lengthPath = matrix[vertex.name][r + 1]

                //если путь есть (!0), ведет не в начало (1), потенциально меньше BEST-path и мы не посещали эту
                //вершину ранее, то записываем в стек и добавляем предыдущий пройденный путь (не сортированный)
                if (
                    lengthPath > 0 &&
                    r + 1 != 1 &&
                    lengthPath + vertex.totalPathLength < bestPath &&
                    setOfVisits.contains(r + 1)
                ) {
                    pqTemp.add(
                        SalesmanVertex(
                        r + 1,
                        lengthPath + vertex.totalPathLength
                        )
                    )
                }
            }
            //добавляем в стек все найденные соседние вершины
            while (pqTemp.isNotEmpty()) vertexQueue.add(pqTemp.poll())
        }
        //println( "-vQ ${ vertexQueue.joinToString(" | ") {"${it.name},${it.totalPathLength}"} }" )
    }

    //println("best: $bestPath")
    //println("provenPaths: ${provenPaths.joinToString(", ")}")

    if (bestPath == Int.MAX_VALUE) bestPath = -1

    return bestPath
}

private class SalesmanVertex(val name: Int, val totalPathLength: Int)

