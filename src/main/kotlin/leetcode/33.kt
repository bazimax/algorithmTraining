package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFun(testAsk)
}

//33. Search in Rotated Sorted Array - Medium - https://leetcode.com/problems/search-in-rotated-sorted-array/
private val testAsk = arrayOf(
    Triple(intArrayOf(4,5,6,7,0,1,2), 0, 4),
    Triple(intArrayOf(4,5,6,7,0,1,2), 7, 3),
    Triple(intArrayOf(4,5,6,7,0,1,2), 3, -1),
    Triple(intArrayOf(5,6,0,1,2,3,4), 0, 2),
    Triple(intArrayOf(0,1,2,3,4,5,6), 0, 0),
    Triple(intArrayOf(0,1,2,6), 2, 2),
    Triple(intArrayOf(6,0,1,2), 1, 2),
    Triple(intArrayOf(1), 0, -1),
    Triple(intArrayOf(1), 1, 0),
    Triple(intArrayOf(1,0), 0, 1),
    Triple(intArrayOf(1,3), 0, -1),
    Triple(intArrayOf(4,5,6,7,0,1,2), 8, -1)
    //Triple(intArrayOf(), ,)
)
private fun testFun(ask: Array<Triple<IntArray, Int, Int>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = search33V3(i.first, i.second)
            println("${answer == i.third} :: in: [${i.first.joinToString(",")}], target: ${i.second}, " +
                    "correctAnswer: ${i.third}, ans: $answer")
        }
        println("time: $time")
    }
}


//33v3 > 16:25 - 16:35 (~1ч45мин) > OK (fast 69%, memory 63%)
private fun search33V3(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.size - 1

    if (nums.size <= 3) {
        val mid = (r) / 2
        return when (target) {
            nums[l] -> 0
            nums[r] -> r
            nums[mid] -> mid
            else -> -1
        }
    }

    while (l != r) { // && count < 5
        val mid = (r + l) / 2

        when (target) {
            nums[l] -> return l
            nums[r] -> return r
            nums[mid] -> return mid
            //else -> println("norm")
        }
        if (nums[l] <= nums[mid] && target in nums[l]..nums[mid]) r = mid // ->     //if (target in nums[l]..nums[mid]) r = mid
        else if (nums[l] >= nums[mid] && (target > nums[l] || target < nums[mid])) r = mid // <- if (target > nums[l] || target < nums[mid]) r = mid
        else if (nums[mid] <= nums[r] && target in nums[mid]..nums[r]) l = mid // -> //else if (target in nums[mid]..nums[r]) l = mid
        else if (nums[mid] >= nums[r] && (target > nums[mid] || target < nums[r])) l = mid // <- else if (target > nums[mid] || target < nums[r]) l = mid
        else return -1

    }
    return -1
}


//33v2 > 15:55 - 16:25 - не прошло по времени
private fun search33V2(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.size - 1

    if (nums.size <= 3) {
        val mid = (r) / 2
        return when (target) {
            nums[l] -> 0
            nums[r] -> r
            nums[mid] -> mid
            else -> -1
        }
    }

    while (l != r) { // && count < 5
        val mid = (r + l) / 2

        when (target) {
            nums[l] -> return l
            nums[r] -> return r
            nums[mid] -> return mid
        }

        if (nums[l] <= nums[mid] && nums[mid]<= nums[r]) {
            //-> -> //правильная-правильная последовательность
            if (target in nums[l]..nums[mid]) r = mid
            else if (target in nums[mid]..nums[r]) l = mid
            else return -1
        }
        else if (nums[l] <= nums[mid] && nums[mid] >= nums[r]) {
            //-> <- //правильная-Неправильная последовательность
            if (target in nums[l]..nums[mid]) r = mid
            else if (target > nums[mid] || target < nums[r]) l = mid
            else return -1

        }
        else if (nums[l] >= nums[mid] && nums[mid] <= nums[r]) {
            //<- -> //Неправильная-правильная последовательность
            if (target > nums[l] || target < nums[mid]) r = mid
            else if (target in nums[mid]..nums[r]) l = mid
            else return -1
        }
    }
    return -1
}


//33 > 14:55 - 15:55 - работает но не всегда, надо поправить логику
private fun search33(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.size - 1
    var count = 0

    //if (nums.size == 1 && target == nums[0]) return 0
    //if (nums.size == 1 ) return -1

    if (nums.size <= 3) {
        val mid = (r) / 2
        return when (target) {
            nums[l] -> 0
            nums[r] -> r
            nums[mid] -> mid
            else -> -1
        }
    }

    var check = 4

    while (l != r && count < 5) {
        val mid = (r + l) / 2

        //println("l: $l-${nums[l]}, mid: $mid-${nums[mid]}, r: $r-${nums[r]}")

        when (target) {
            nums[l] -> return l
            nums[r] -> return r
            nums[mid] -> return mid
            //else -> println("norm")
        }
        //if (target < nums[l] && target > nums[r]) return -1

        if (nums[l] < nums[mid]) {
            //правильная последовательность
            if (target in nums[l]..nums[mid]) r = mid
            else check--
        }
        else if (nums[l] > nums[mid]) { //(nums[mid] < nums[l])
            //Неправильная последовательность
            if (target > nums[l] || target < nums[mid]) r = mid
            else check--
        }
        else if (nums[mid] == nums[l]) l = mid
        //else println("1 - Error??")

        if (nums[mid] < nums[r]) {
            //правильная последовательность
            if (target in nums[mid]..nums[r]) l = mid
            else check--
        }
        else if (nums[mid] > nums[r]) {
            //Неправильная последовательность
            //println("r-No")
            if (target > nums[mid] || target < nums[r]) l = mid
            else check--
        }
        else if (nums[mid] == nums[r]) r = mid
        //else println("2 - Error??")
        //Triple(intArrayOf(4,5,6,7,0,1,2), 3, -1),
        if (check != 4) return -1
        else check = 4
        count++
    }
    return -1
}
//33 > 14:50 - 14:55 - составление тестовых запросов