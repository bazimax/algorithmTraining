package yandex.algorithms4training.class3

/**
 * D. Автобусы в Васюках //Buses in Vasyuki
 *
 * Между некоторыми деревнями края Васюки ходят автобусы. Поскольку пассажиропотоки здесь не очень большие,
 * то автобусы ходят всего несколько раз в день. Марии Ивановне требуется добраться из деревни d в деревню v как
 * можно быстрее (считается, что в момент времени 0 она находится в деревне d).
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.PriorityQueue

fun main(args: Array<String>) {
    //busesInVasyuki()
    timeMillisRun(::busesInVasyuki)
    runTest("busesInVasyuki", examplesD, ::busesInVasyuki)
}

private val examplesD = arrayOf(
    Pair("3\n" +
            "1 3\n" +
            "4\n" +
            "1 0 2 5\n" +
            "1 1 2 3\n" +
            "2 3 3 5\n" +
            "1 1 3 10",
        "5"),
    Pair("4\n" +
            "1 3\n" +
            "7\n" +
            "1 0 2 5\n" +
            "1 1 2 3\n" +
            "2 3 3 5\n" +
            "1 1 3 10\n" +
            "1 0 4 1\n" +
            "4 1 2 2\n" +
            "2 2 3 4",
        "4"),
    //Pair("12", "2"),
)

// 10:35 - 12:10 (1ч35мин) > OK (422ms, 24.08Mb)
private fun busesInVasyuki(){
    val input = BufferedReader(FileReader("input.txt"))

    //общее число деревень (1 <= N <= 100)
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0]

    //из деревни d в деревню v
    val (d, v) = input.readLine().trim().split(" ").map { it.toInt() }

    //минимальное время прибытия ~10^8
    val ans =
        if (d == v) "0"
        else moving(n, d, v, input)


    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

private fun moving(n: Int, d: Int, v: Int, input: BufferedReader) : String{
    //количество автобусных рейсов (0 <= R <= 10000)
    val r = input.readLine().trim().split(" ").map { it.toInt() }[0]

    //список всех маршрутов по месту отправления (index = departureVillageName)
    val routeList: Array<MutableList<Route>> = Array(r){ mutableListOf() }

    repeat(r) {
        val (departureVillage, departureTime, destinationVillage, arrivalTime) =
            input.readLine().trim().split(" ").map { it.toInt() }

        routeList[departureVillage].add(Route(departureVillage, departureTime, destinationVillage, arrivalTime))
    }

    //список деревень
    val arrVillage = IntArray(n + 1) {Int.MAX_VALUE}

    //очередь деревень на проверку
    val pq = PriorityQueue<Village>{ a, b -> a.minDepartureTime - b.minDepartureTime}

    //стартовая деревня
    pq.add(Village(d, 0))

    while (pq.isNotEmpty()) {
        val village = pq.poll()

        //проверка на уже пройденные деревни
        if (village.minDepartureTime <= arrVillage[village.name]) {

            //маршрут более короткий, обновляем нынешнее значение
            arrVillage[village.name] = village.minDepartureTime

            //проверяем каждый маршрут исходящий из данной деревни
            routeList[village.name].forEach { route ->

                //println("d: ${route.departureVillage}, dT: ${route.departureTime}, v: ${route.destinationVillage}, aT:${route.arrivalTime}")

                //если успеваем на рейс
                if (village.minDepartureTime <= route.departureTime) {

                    //если рейс более быстрый обновляем значение минимального времени отправления из проверяемой деревни
                    //и добавляем её в очередь на проверку
                    if (route.arrivalTime < arrVillage[route.destinationVillage]) {

                        arrVillage[route.destinationVillage] = route.arrivalTime

                        //если не пункт назначения, то добавляем в очередь на проверку
                        if (route.destinationVillage != v) {
                            pq.add(Village(route.destinationVillage, route.arrivalTime))
                        }

                    }
                }
            }
        }


        //println(" * ${arrVillage.joinToString(" ")}")
    }

    //println(arrVillage[v])

    return if (arrVillage[v] < Int.MAX_VALUE) arrVillage[v].toString() else "-1"
}

private class Route(val departureVillage: Int,
                    val departureTime: Int,
                    val destinationVillage: Int,
                    val arrivalTime: Int)

private class Village(val name: Int, var minDepartureTime: Int)