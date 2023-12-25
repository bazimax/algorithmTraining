package yandex.algorithms4training.class3

/**
 * C. Быстрый алгоритм Дейкстры //Dijkstra's fast algorithm
 *
 * Вам дано описание дорожной сети страны. Ваша задача – найти длину кратчайшего пути между городами А и B.
 */
import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.PriorityQueue

fun main(args: Array<String>) {
    fastAlgorithmDijkstra()
    //runTest("fastAlgorithmDijkstra", examplesC, ::fastAlgorithmDijkstra)
}

private val examplesC = arrayOf(
    Pair("6 4\n" +
            "1 2 7\n" +
            "2 4 8\n" +
            "4 5 1\n" +
            "4 3 100\n" +
            "3 1",
        "115"),
    Pair("8 8\n" +
            "1 2 7\n" +
            "2 4 8\n" +
            "4 5 1\n" +
            "3 4 100\n" +
            "1 6 9\n" +
            "6 4 1\n" +
            "2 7 1\n" +
            "7 4 3\n" +
            "1 3",
        "110"),
    Pair("6 4\n" +
            "1 2 7\n" +
            "2 4 8\n" +
            "4 5 1\n" +
            "4 3 1000000\n" +
            "3 1",
        "1000015"),
    //Pair("12", "2"),
)

// 7:50 - 10:00 (2ч10мин) > OK  (2.16s, 103.56Mb)
private fun fastAlgorithmDijkstra(){
    val input = BufferedReader(FileReader("input.txt"))
    //n - максимальное количество городов 1 ≤ N ≤ 100000
    //k - количество дорог с двусторонним движением 0 ≤ K ≤ 300000
    val (n, k) = input.readLine().trim().split(" ").map { it.toInt() }

    val arrOfCities: Array<MutableList<Pair<Int, Long>>> = Array(n + 1){ mutableListOf()}

    repeat(k) {
        //(1 ≤ a(i), b(i) ≤ N, 1 ≤ l(i) ≤ 10^6)
        val (cityA, cityB, roadLength) = input.readLine().trim().split(" ").map { it.toInt() }
        arrOfCities[cityA].add(cityB to roadLength.toLong())
        arrOfCities[cityB].add(cityA to roadLength.toLong())
    }
    //arrOfCities.forEach { println(it) }

    val (start, finish) = input.readLine().trim().split(" ").map { it.toInt() } //(1 ≤ A, B ≤ N)


    //расстояние между начальным и конечным городом ~10^11
    val ans =
        if (start == finish) "0"
        else moving2(n, start, finish, arrOfCities)


    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

private fun moving2(n: Int, start: Int, finish: Int, arrOfCities: Array<MutableList<Pair<Int, Long>>>) : String {
    //минимальное расстояние до города (index массива = название города)
    val arrNum = LongArray(n + 1) { Long.MAX_VALUE }

    //очередь городов на проверку с расстояниями до них: cityName to roadLength
    val pq = PriorityQueue<Pair<Int, Long>>{ a, b -> (a.second - b.second).toInt()}
    pq.add(start to 0)

    while (pq.isNotEmpty()) {
        val cityA = pq.poll()
        //println("- $cityA")
        //println(" * ${arrNum.joinToString(" ")}")

        if (cityA.second <= arrNum[cityA.first]) {
            arrNum[cityA.first] = cityA.second
           // println(" * ${arrNum.joinToString(" ")}")

            //проверяем все соседние города
            arrOfCities[cityA.first].forEach { cityB ->
                //println("nextCityName: ${cityB.first} roadLength: ${cityB.second}")

                val newLength = cityB.second + arrNum[cityA.first]
                //println("newLength: $newLength")

                //если расстояние до cityB от cityA меньше известного добавляем в очередь на проверку и обновляем arrNum
                if (newLength < arrNum[cityB.first]) {
                    //обновляем минимальное расстояние до этого города
                    arrNum[cityB.first] = newLength

                    //если город не пункт назначения, то добавляем в очередь на проверку
                    if (cityB.first != finish) pq.add(cityB.first to newLength)
                }
            }
        }
    }

    return if (arrNum[finish] < Long.MAX_VALUE) arrNum[finish].toString() else "-1"
}