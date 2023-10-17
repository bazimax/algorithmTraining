package leetcode

//217 (347?). Contains Duplicate - Easy - https://leetcode.com/problems/contains-duplicate/description/
//18:10 - 18:30 (~20мин) > OK (fast 12%, memory 27%)
private fun containsDuplicate(nums: IntArray): Boolean {
    val set = mutableSetOf(nums[0])

    for (i in 1 until nums.size) {
        if (!set.add(nums[i])) return true

    }
    return false
}