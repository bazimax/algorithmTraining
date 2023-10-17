package leetcode

fun main(args: Array<String>) {
    println(majorityElement169(arrayOf(3,2,3).toIntArray()))
    println(majorityElement169(arrayOf(2,2,1,1,1,2,2).toIntArray()))
    println(majorityElement169(arrayOf(1).toIntArray()))
}

//169. Majority Element - Easy - https://leetcode.com/problems/majority-element/description/

//169 > 17:03 - 17:16 (~13мин) > OK (fast 16%, memory 63%) О(n), а надо О(1)
private fun majorityElement169(nums: IntArray): Int {
    if (nums.size == 1) return nums[0]
    val map = java.util.HashMap<Int, Int>()

    var count = 0

    for (i in nums) {
        if (map[i] != null) {
            map[i] = map[i]!! + 1
            if (map[i]!! > count) count = map[i]!!
            if (count > nums.size / 2) return i
        }
        else map[i] = 1
    }
    return count
}