package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFunOld53v2(testAskOld53v2)
}

//53. Maximum Subarray - Medium - https://leetcode.com/problems/maximum-subarray/description/

private val testAskOld53v2 = arrayOf(
    Pair(intArrayOf(-2,1,-3,4,-1,2,1,-5,4), 6),
    Pair(intArrayOf(1), 1),
    Pair(intArrayOf(5,4,-1,7,8), 23),
    Pair(intArrayOf(0,3,-1), 3)
)

private fun testFunOld53v2(ask: Array<Pair<IntArray, Int>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = maxSubArray53v2(i.first)//sortedSquares977(i.first)
            println("${answer == i.second} :: in: [${i.first.joinToString(",")}], " + //target: ${i.second},
                    "correctAnswer: ${i.second}, ans: $answer") //contentEquals - Array
        }
        //println("time: $time")
    }
}

//53v2 > 9:58 - 10:22 (~24мин) > OK (fast 47%, memory 52%)
private fun maxSubArray53v2(nums: IntArray): Int {
    var max = Int.MIN_VALUE
    var maxTemp = max

    for (i in nums) {
        if (i > maxTemp && maxTemp <= 0) {
            maxTemp = i
            if (maxTemp > max) max = maxTemp
        }
        else if (maxTemp + i > maxTemp && maxTemp > 0) {
            maxTemp += i
            if (maxTemp> max) max = maxTemp
        }
        else maxTemp += i
    }
    return max
}


//val nums = intArrayOf(-1) //(-3,-2,0,-1) 0 //(-1) -1 //(-2,-1) -1 //(5,4,-1,7,8) 23 //(-3,-2,1,2,2,0,1,0) 6
//53 - 19:40 - 20:00 быстрый, но не всегда верный вариант // 20:10 - 20:40 переписал на рабочий вариант
private fun maxSubArray(nums: IntArray): Int {


    var max = nums[0]
    var tempMax = 0

    nums.forEach{
        if (max < 0) {
            if (max < it) {
                max = it
                tempMax = it
            }
        }
        else {
            if (tempMax + it > 0) {
                tempMax += it
                if (tempMax > max) max = tempMax
            }
            else {
                tempMax = 0
            }
        }
        println("max: $max, tempMax: $tempMax")
    }
    return max
}