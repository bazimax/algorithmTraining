package leetcode

fun main(args: Array<String>) {
    numsArr.forEach {
        val a = findMinV2(it.first)
        val str: String = it.first.map { i -> i.toString() }.reduce { x, y -> "$x, $y" }
        println("$a, ${it.second}, ${a == it.second}, ($str)")
    }
}

//153. Find Minimum in Rotated Sorted Array - Medium - https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
private val numsArr = listOf(
    intArrayOf(11,13,15,17) to 11,
    intArrayOf(4,5,6,7,0,1,2) to 0,
    intArrayOf(3,4,5,1,2) to 1,
    intArrayOf(0,1,2,4,5,6,7) to 0,
    intArrayOf(3,1,2) to 1,
    intArrayOf(2,1) to 1,
    intArrayOf(2,3,1) to 1,
    intArrayOf(2,3,4,5,1) to 1,
    intArrayOf(1,2) to 1,
)

//153v3 > OK (fast 23%, memory 6%)
private fun findMinV3(nums: IntArray): Int {
    var l = 0
    var r = nums.size - 1

    if (nums[l] <= nums[r]) return nums.first() //nums[l]
    while (l != r) {
        val binaryItem = (r + l + 1) / 2
        if (nums[binaryItem] >= nums[r]) {
            l = binaryItem
        }
        else r = binaryItem
        if (l + 1 == r) return minOf(nums[l], nums[r])
    }

    return nums[l]
}

//153v2 > 0:50 - 1:30 (~40мин) > OK (fast 5%, memory 25%)
private fun findMinV2(nums: IntArray): Int {

    var l = 0
    var r = nums.size - 1

    if (nums[l] <= nums[r]) return nums.first() //nums[l]
    if (nums.size == 2) return minOf(nums.first(), nums.last())

    while (l != r) {
        if (nums[(r + l + 1) / 2] >= nums[r]) {
            l = (r + l + 1) / 2
        }
        else r = (r + l + 1) / 2

        if (l + 1 == r) return minOf(nums[l], nums[r])
    }

    return nums[l]
}


//153 - https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
//val nums = intArrayOf(3,4,5,1,2) //(4,5,6,7,0,1,2) 0 //(0,1,2,4,5,6,7) 0 //(3,4,5,1,2) 1 //(11,13,15,17) 11 //(3,1,2) 1 //(2,1) 1 //(2,3,1) 1 //(2,3,4,5,1) 1
//153 - 23:00 - 0:44 (~1ч44мин) > notOK - не получилось
private fun findMinLong(nums: IntArray): Int {
    //var min = Int.MIN_VALUE
    var l = 0
    var r = nums.size - 1
    var binaryItem = ((r - l) / 2)
    var count = 0

    if (nums[l] <= nums[r]) return nums.first() //nums[l]
    if (nums.size == 2) return minOf(nums.first(), nums.last())

    while (l != r && count < 5) {
        println("while l: $l=${nums[l]}, r:$r=${nums[r]}, binary:$binaryItem=${nums[binaryItem]}")
        //println("while l:${nums[binaryItem - 1]}, binary:${nums[binaryItem]}, r:${nums[binaryItem + 1]}")
        //println(nums[binaryItem] <= nums[binaryItem - 1])
        //println(nums[binaryItem] <= nums[binaryItem + 1])
        if (binaryItem - 1 > 0 && binaryItem + 1 <= nums.size - 1) {
            println("if if")
            if (nums[binaryItem] <= nums[binaryItem - 1] && nums[binaryItem] <= nums[binaryItem + 1]) {
                //println("return")
                return nums[binaryItem]
            }
            else if (nums[binaryItem] >= nums[r] && nums[binaryItem] >= nums[l]) {
                l = binaryItem
                println("if l: $l=${nums[l]}")
            }
            else if (nums[binaryItem] <= nums[r] && nums[binaryItem] <= nums[l]) {
                r = binaryItem
                println("else r: $r=${nums[r]}")
            }

            if (binaryItem != l && binaryItem != r) binaryItem = ((r - l) / 2) + l + 1
            else return minOf(nums[l], nums[r])

        }
        //else println("while ERROR l:${nums[binaryItem - 1]}, binary:${nums[binaryItem]}, r:${nums[binaryItem + 1]}")


        count++
    }
    return nums[l]
}