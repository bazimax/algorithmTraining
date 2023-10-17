package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFun238(testAsk238)
}

//238. Product of Array Except Self - Medium - https://leetcode.com/problems/product-of-array-except-self/
private val testAsk238 = arrayOf(
    Pair(intArrayOf(1,2,3,4), intArrayOf(24,12,8,6)),
    Pair(intArrayOf(-1,1,0,-3,3), intArrayOf(0,0,9,0,0)),
    Pair(intArrayOf(0,2,3,4), intArrayOf(24,0,0,0))
    //Pair(arrayOf(), arrayOf())
)
private fun testFun238(ask: Array<Pair<IntArray, IntArray>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = productExceptSelfV3(i.first)
            println("${answer contentEquals i.second} :: in: [${i.first.joinToString(",")}], " +
                    "correctAnswer: [${i.second.joinToString(",")}], ans: [${answer.joinToString(",")}]")
        }
        println("time: $time")
    }
}

//238v2 > медленное решение O(n^2), не прошло по времени
private fun productExceptSelfV2(nums: IntArray): IntArray {
    val answer: IntArray = IntArray(nums.size)
    for ((indexI, _) in nums.withIndex()) {
        //println("i: $indexI, nums: ${nums[indexI]}")
        var value = 1
        for ((indexJ, _) in nums.withIndex()) {
            if (indexJ != indexI) {
                value *= nums[indexJ]
            }
        }
        answer[indexI] = value
    }
    return answer
}

//238v3 > ~2часа - решение побыстрее > OK (fast 7%, memory 88%)
private fun productExceptSelfV3(nums: IntArray): IntArray {
    val answer = IntArray(nums.size)
    var countZero = 0
    var coordinateZero = 0

    nums.forEachIndexed { index, i ->
        if (i == 0) {
            countZero++
            coordinateZero = index
        }
    }

    if (countZero < 2) {
        if (countZero == 1) {
            //есть один ноль
            answer[coordinateZero] = nums.foldIndexed(1){ index, sum, int -> if (nums[index] != 0) sum*int else sum } //v1
            //answer[coordinateZero] = nums.reduceIndexed { index, acc, int -> if (nums[index]!= 0) acc*int else acc } //v2

        } else {
            //нулей нет
            val multiSum = nums.fold(1){ sum, element -> sum*element } //v1
            //val multiSum = nums.reduce { acc, int -> acc*int } //v2
            nums.forEachIndexed  { index, i ->
                answer[index] = multiSum / i
            }
        }
    } //else - все нули
    return answer
}


//238 - https://leetcode.com/problems/product-of-array-except-self/\
//238 - не мое решение
private fun productExceptSelf(nums: IntArray): IntArray {
    val ans = IntArray(nums.size)
    var n = 1
    for(i in nums.indices) //(i in 0..nums.size - 1)
    {
        ans[i] = n
        n *= nums[i]
    }
    ans.forEach {
        println("it: $it")
    }
    n = 1
    for(i in nums.size - 1 downTo 0)
    {
        ans[i] = n * ans[i]
        n *= nums[i]
        println("ans i: ${ans[i]}")
    }
    ans.forEach {
        println("it: $it")
    }

    return ans
}

//238 - 18:30-19:30 (reduce непонятно работает)
private fun productExceptSelfReduce(nums: IntArray): IntArray {

    val result = IntArray(nums.size)
    println("last: ${nums.last()}, first; ${nums.first()}, numsSize: ${nums.size - 1}")
    nums.forEachIndexed { index, i ->
        println(index)
        if (index == 0) {
            result[0] = (nums[1]..nums.last()).reduce { acc, i ->  acc * i }//(Int::times)
            println("res 0: ${result[index]}")
        }
        else if (index > 0 && index < nums.size - 1) {
            println("res")
            println("first > index: ${nums.first()} > ${nums[index]}")
            val a = (nums.first()..nums[index - 1]).reduceOrNull(Int::times) ?: 0 //.fold(0) { sum, element -> sum * element }
            println("res a: $a")
            val b = (nums[index + 1]..nums.last()).reduceOrNull(Int::times) ?: 0
            println("res b: $b")
            result[index] = a * b
            println("res: ${result[index]}")

        }
        else if (index == nums.size - 1) {
            println("res end")
            result[result.size - 1] = (nums.first()until nums[nums.size - 1]).reduce(Int::times)
            println("res end: ${result[index]}")

        }
        else println("ERROR")

    }
    result.forEach {
        println("it: $it")
    }
    return result
}