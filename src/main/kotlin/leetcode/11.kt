package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFun(testAsk)
}

//11. Container With Most Water - Medium - https://leetcode.com/problems/container-with-most-water/
private val testAsk = arrayOf(
    Pair(intArrayOf(1,8,6,2,5,4,8,3,7), 49),
    Pair(intArrayOf(1,1), 1),
    Pair(intArrayOf(1,4,5,8,6,2,5,4,8,5,3,7), 56),
    Pair(intArrayOf(0,0), 0),
    Pair(intArrayOf(0,1), 0)
)
private fun testFun(ask: Array<Pair<IntArray, Int>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = maxArea11(i.first)
            println("${answer == i.second} :: in: [${i.first.joinToString(",")}], " +
                    "correctAnswer: ${i.second}, ans: $answer")
        }
        println("time: $time")
    }
}

//11 > 23:30 - 23:55 (~25мин) > OK (fast 7%, memory 71%)
private fun maxArea11(height: IntArray): Int {
    var l = 0
    var r = height.size - 1

    var water = 0

    while (l < r) {
        val waterTemp = minOf(height[l], height[r]) * (r - l)
        if (waterTemp > water) water = waterTemp

        val tempL = l
        val tempR = r

        if (height[l] < height[r]) {
            while (l < r && height[l] == height[tempL]) l++
        }
        else { //if (height[l] > height[r])
            while (l < r && height[r] == height[tempR]) r--
        }
        //else if (height[l] == height[r]) {}
    }

    return water
}