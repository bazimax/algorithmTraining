package leetcode

fun main(args: Array<String>) {
}

//704. Binary Search - Easy - https://leetcode.com/problems/binary-search/description/

//704 > 5:20 - 5:40 (~20мин) > OK (fast 88%, memory 54%), решал без рабочей среды IDE (но были ошибки val\var и типа "nums.count" вместо "nums.count()")
private fun search704(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.count() - 1
    while (l < r) {
        val half = (l + r) / 2
        if (target <= nums[half]) r = half
        else l = half + 1
    }
    return if (nums[l] == target) l else -1
}