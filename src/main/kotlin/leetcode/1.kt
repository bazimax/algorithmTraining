package leetcode

fun main(args: Array<String>) {
    twoSumNotSorted(intArray, 6) //6 //9*/
}

//1. Two Sum - Easy - https://leetcode.com/problems/two-sum/description/
private val intArray: IntArray = intArrayOf(3,2,4) //(-1,0) //(2,7,11,15) //(2,3,4)


//1 > OK (fast 43%, memory 38%)
private fun twoSumNotSorted(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    var answer = intArrayOf(0, 0)

    nums.forEachIndexed { index, i ->
        val a = target - i
        if (map[a] != null) {
            answer = intArrayOf(map[a]!!, index)
        }
        map[i] = index
    }
    println("answer: ${answer[0]}, ${answer[1]}")
    return answer
}

//notOK
private fun twoSumSorted(numbers: IntArray, target: Int): IntArray {
    var answer = intArrayOf(0, 0)
    var r = 0
    var l = numbers.size - 1

    do {
        if (numbers[r] + numbers[l] < target) r++
        if (numbers[r] + numbers[l] > target) l--
        if (numbers[r] + numbers[l] == target) answer = intArrayOf(r + 1, l + 1)
    } while (r != l && numbers[r] + numbers[l] != target)

    println("answer: ${answer[0]}, ${answer[1]}")
    return answer
}

//backup
private fun twoSumSortedZapas(nums: IntArray, target: Int): IntArray {
    var answer = intArrayOf(0, 0)
    var r = 0
    var l = nums.size - 1
    println("start > r: $r, l: $l, nums[r]: ${nums[r]}, nums[l]: ${nums[l]}")
    var count = 0

    do {
        println("while > r: $r, l: $l, nums[r]: ${nums[r]}, nums[l]: ${nums[l]}")
        println("nums = ${nums[r] + nums[l]}")
        if (nums[r] + nums[l] < target) {
            r++
            println("r++")
        }
        if (nums[r] + nums[l] > target) {
            l--
            println("l--")
        }
        if (nums[r] + nums[l] == target) {
            answer = intArrayOf(r + 1, l + 1)
            println("bingo")
        }
        println("while > r: $r, l: $l")
        count++
    } while (r != l && nums[r] + nums[l] != target && count < 5)

    println("answer: ${answer[0]}, ${answer[1]}")
    return answer
}

