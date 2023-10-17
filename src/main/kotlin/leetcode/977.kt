package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFunOld977(testAskOld977)
}

//977. Squares of a Sorted Array - Easy - https://leetcode.com/problems/squares-of-a-sorted-array/

private val testAskOld977 = arrayOf(
    Pair(intArrayOf(-4,-1,0,3,10), intArrayOf(0,1,9,16,100)),
    Pair(intArrayOf(-7,-3,2,3,11), intArrayOf(4,9,9,49,121)),
    Pair(intArrayOf(1), intArrayOf(1))
    //Pair(intArrayOf(), intArrayOf())
)
private fun testFunOld977(ask: Array<Pair<IntArray, IntArray>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = sortedSquares977(i.first)
            println("${answer contentEquals i.second} :: in: ${i.first.joinToString()}, " +
                    "correctAnswer: ${i.second.joinToString()}, ans: ${answer.joinToString()}") //contentEquals - Array
        }
        println("time: $time")
    }
}

//977 > 20:05 - 20:40 (~35мин) > OK (fast 15%, memory 75%) - работает (но быстрее через -> for (i in nums.indices.reversed()))
private fun sortedSquares977(nums: IntArray): IntArray {

    var l = 0
    val numsSize = nums.size - 1
    var r = numsSize
    var step = numsSize
    val resultArray = IntArray(nums.size) {0}


    while (l != r) {
        if (kotlin.math.abs(nums[l]) > kotlin.math.abs(nums[r])) {
            resultArray[step] = nums[l] * nums[l]
            l++
        }
        else if (kotlin.math.abs(nums[l]) <= kotlin.math.abs(nums[r])) {
            resultArray[step] = nums[r] * nums[r]
            r--
        }
        //else println("Error")
        step--
    }
    resultArray[step] = nums[r] * nums[r]
    return resultArray
}