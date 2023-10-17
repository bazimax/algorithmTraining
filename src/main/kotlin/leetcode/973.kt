package leetcode

import java.util.*

fun main(args: Array<String>) {
    println(arrayToString973(
        kClosest973(stringToArray973("[[1,3],[-2,2]]"), 1)
    ))
    println(arrayToString973(
        kClosest973(stringToArray973("[[3,3],[5,-1],[-2,4]]"), 2)
    ))
}

//973. K Closest Points to Origin - Medium - https://leetcode.com/problems/k-closest-points-to-origin/description/

//973 > 19:00 - 19:50 (~50мин) > OK (fast 17%, memory 94%)
private fun kClosest973(points: Array<IntArray>, k: Int): Array<IntArray> {
    val distances = mutableListOf<Pair<IntArray, Int>>()

    for (i in points.indices) {
        val distance = ( points[i][0] * points[i][0] ) + ( points[i][1] * points[i][1] )
        distances.add(points[i] to distance)
    }

    distances.sortBy { it.second }

    val ans = Array(k) {
        distances.first().first
        distances.removeFirst().first
    }

    return ans
}
private fun stringToArray973(stringArray: String): Array<IntArray>{
    val str = stringArray.replace("],[", ".").drop(2).dropLast(2).split(".")
    var arr: Array<IntArray> = Array(str.size){ IntArray(2){0} }

    if (stringArray != "[]") {
        str.forEachIndexed { index, it ->
            val a = it.split(",")
            arr[index] = intArrayOf(a[0].toInt(),a[1].toInt())
        }
    } else arr = emptyArray()

    return arr
}
private fun arrayToString973(array: Array<IntArray>): String{
    var arrToString = ""

    array.forEach {
        arrToString = if (arrToString == "") "[${it.joinToString(",")}]"
        else "$arrToString,[${it.joinToString(",")}]"
    }
    return arrToString
}

//973v2 > 19:56 - 20:08 (~12мин) > OK (fast 100%, memory 100%)
private fun kClosest973v2(points: Array<IntArray>, k: Int): Array<IntArray> {
    val pq = PriorityQueue<IntArray>(){a,b -> distance(a) - distance(b)} //not mine decision

    //for (i in points.indices) { pq.add(points[i]) } //(fast 86%, memory 94%)
    points.forEach { pq.add(it) } //(fast 100%, memory 100%)

    return Array(k) { pq.poll() }
}
private fun distance(point: IntArray): Int{
    //not mine decision
    return point[0] * point[0] + point[1] * point[1]
}