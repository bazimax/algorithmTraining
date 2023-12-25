package tinkoff.winter2024

/**
 * 4 задание
 */

import java.util.*
import kotlin.collections.HashSet
import kotlin.math.min

fun main(args: Array<String>) {
    allStocks()
}
/*

5 2
A
B
0 1 A
1 2 A
1 2 B
1 1 B
4 2 A
output 3

4 2
A
B
0 1 A
1 1 B
2 1 A
3 3 B
output 4

4 3
A
B
c
0 1 A
1 1 b
1 1 a
1 5 c
output 8
*/


//18:16 - 20:06 (1ч50мин) > ?
private fun allStocks(){
    val scan = Scanner(System.`in`)
    val (n, k) = scan.nextLine().split(" ").map { it.toInt() } // 1 ≤ n ≤ 3*10^5, 1 ≤ k ≤ 30

    // записываем имена компаний
    val companySet = HashSet<String>()
    repeat(k) {
        val str = scan.nextLine().toString().lowercase() // str ≤ 10
        companySet.add(str)
    }
    val companyMaxSetSize = companySet.size

    // записываем вершины
    val vertexList = mutableListOf<Vertex>()
    repeat(n) {
        val str = scan.nextLine().split(" ") // 0 ≤ pi ≤ n,0 ≤ ai ≤ 10^4

        val index = it + 1
        val company = str[2].lowercase()
        val stockPrice = str[1].toInt()
        val parent = str[0].toInt() - 1
        val vertex = Vertex(index = index, _company = company, stockPrice = stockPrice, parent = parent)

        vertexList.add(vertex)

        if (parent != -1) {
            vertexList[parent].childrenList.add(vertex)
            vertexList[parent].childrenCount = vertexList[parent].childrenList.size
        }
    }

    // добавляем в очередь в зависимости от количества детей (будем начинать работать с родителями у которых нет детей
    // и подниматься выше и выше увеличивая итоговую стоимость родителя\поддерева)
    val pq = PriorityQueue<Vertex> { a, b -> a.childrenCount - b.childrenCount}
    vertexList.forEach { pq.add(it) }

    var bestPrice = Long.MAX_VALUE

    while (pq.isNotEmpty()) {
        val vertex = pq.poll()

        if (vertex.childrenCount > 0) {
            // если мы ранее обновляли этот элемент, то заново закинем его в очередь
            pq.add(vertex)
        }
        else {
            if (vertex.parent == -1 && vertex.companySet.size < companyMaxSetSize) bestPrice = -1
            else if(vertex.parent == -1) {
                bestPrice = min(bestPrice, vertex.totalStockPrice)
            }
            else if (vertex.companySet.size < companyMaxSetSize) {
                // В этом поддереве ещё не все акции, значит нам не подходит - добавляем его данные
                // (акции и их суммарную цену) выше родителю
                vertexList[vertex.parent].companySet = (vertexList[vertex.parent].companySet + vertex.companySet).toHashSet()
                vertexList[vertex.parent].totalStockPrice = vertexList[vertex.parent].totalStockPrice + vertex.totalStockPrice
                vertexList[vertex.parent].childrenCount = vertexList[vertex.parent].childrenCount - 1
            }
            else {
                // мы нашли подходящий вариант, но могут быть и другие
                bestPrice = min(bestPrice, vertex.totalStockPrice)

                //после этого добавляем его данные (акции и их суммарную цену) выше родителю
                vertexList[vertex.parent].companySet = vertex.companySet
                vertexList[vertex.parent].totalStockPrice = vertexList[vertex.parent].totalStockPrice + vertex.totalStockPrice
                vertexList[vertex.parent].childrenCount = vertexList[vertex.parent].childrenCount - 1
            }
        }
    }
    println(if (bestPrice != Long.MAX_VALUE) bestPrice else -1)
}

class Vertex (val index: Int, _company: String, val stockPrice: Int, val parent: Int) {

    var companySet: HashSet<String> = HashSet()
    var totalStockPrice: Long = stockPrice.toLong()
    var childrenList = mutableSetOf<Vertex>()
    var childrenCount: Int = 0
    private val company = _company.lowercase()

    init {
        companySet.add(_company.lowercase())
    }

    /*
    //!! BACKUP for allStocksWithLogs()
    fun print(){
        println("  i: $index, comp: $company, price: $stockPrice, parent: $parent   ::  " +
                "setSize: ${companySet.size}, totalPrice: $totalStockPrice, chCount: $childrenCount")
    }*/
}


/*
// BACKUP
private fun allStocksWithLogs(){
    val scan = Scanner(System.`in`)
    val (n, k) = scan.nextLine().split(" ").map { it.toInt() } // 1 ≤ n ≤ 3*10^5, 1 ≤ k ≤ 30
    println("n: $n, k: $k")

    // записываем имена компаний
    val companySet = HashSet<String>()
    repeat(k) {
        val str = scan.nextLine().toString().lowercase() // str ≤ 10
        companySet.add(str)
    }
    val companyMaxSetSize = companySet.size
    println(companySet)
    println(companyMaxSetSize)

    // записываем вершины
    val vertexList = mutableListOf<Vertex>()
    repeat(n) {
        val str = scan.nextLine().split(" ") // 0 ≤ pi ≤ n,0 ≤ ai ≤ 10^4
        println(str)

        val index = it + 1
        val company = str[2].lowercase()
        val stockPrice = str[1].toInt()
        val parent = str[0].toInt() - 1
        val vertex = Vertex(index = index, _company = company, stockPrice = stockPrice, parent = parent)

        vertexList.add(vertex)

        if (parent != -1) {
            vertexList[parent].childrenList.add(vertex)
            vertexList[parent].childrenCount = vertexList[parent].childrenList.size
        }

        vertex.print()
    }

    println("-".repeat(10))
    vertexList.forEach { it.print() }
    println("-".repeat(10))

    // добавляем в очередь в зависимости от количества детей (будем начинать работать с родителями у которых нет детей
    // и подниматься выше и выше увеличивая итоговую стоимость родителя\поддерева)
    var pq = PriorityQueue<Vertex> { a, b -> a.childrenCount - b.childrenCount}
    vertexList.forEach { pq.add(it) }



    var bestPrice = Long.MAX_VALUE

    while (pq.isNotEmpty()) {
        val vertex = pq.poll()
        vertex.print()

        if (vertex.childrenCount > 0) {
            // если мы ранее обновляли этот элемент, то заново закинем его в очередь
            println("if > 0")
            pq.add(vertex)
        }
        else {
            if (vertex.parent == -1 && vertex.companySet.size < companyMaxSetSize) bestPrice = -1
            else if(vertex.parent == -1) {
                bestPrice = min(bestPrice, vertex.totalStockPrice)
            }
            else if (vertex.companySet.size < companyMaxSetSize) {
                // В этом поддереве ещё не все акции, значит нам не подходит - добавляем его данные
                // (акции и их суммарную цену) выше родителю
                vertexList[vertex.parent].companySet = (vertexList[vertex.parent].companySet + vertex.companySet).toHashSet()
                vertexList[vertex.parent].totalStockPrice = vertexList[vertex.parent].totalStockPrice + vertex.totalStockPrice
                vertexList[vertex.parent].childrenCount = vertexList[vertex.parent].childrenCount - 1
            }
            else {
                if (vertex.childrenList.isEmpty()) {

                }
                // мы нашли подходящий вариант, но могут быть и другие
                bestPrice = min(bestPrice, vertex.totalStockPrice)
                println("bestPrice: $bestPrice")

                //после этого добавляем его данные (акции и их суммарную цену) выше родителю
                vertexList[vertex.parent].companySet = vertex.companySet
                vertexList[vertex.parent].totalStockPrice = vertexList[vertex.parent].totalStockPrice + vertex.totalStockPrice
                vertexList[vertex.parent].childrenCount = vertexList[vertex.parent].childrenCount - 1
            }

            if(vertex.parent >= 0) {
                print("p ")
                vertexList[vertex.parent].print()
            }
        }
    }
    println(if (bestPrice != Long.MAX_VALUE) bestPrice else -1)
}*/
