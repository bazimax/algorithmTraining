package leetcode

fun main(args: Array<String>) {
    println(summaryRangesWhile(nums))
}

//228. Summary Ranges - Easy - https://leetcode.com/problems/summary-ranges/description/
private val nums = intArrayOf(0,1,2,4,5,7)//(0,1,2,4,5,7) //(0,2,3,4,6,8,9) //(-1)
//Вывод: ["0->2","4->5","7"]

//228 > OK (fast 49%, memory 99%)
private fun summaryRangesWhile(nums: IntArray): List<String> {
    val list: MutableList<String> = mutableListOf()
    //println("${nums.size}")
    var r = 0
    var l = 0
    var i = 0

    while (i <= nums.size - 1) {
        if (nums[i] == nums[r] || nums[i] - 1 == nums[l]) {
            l = i
            i++
        }
        else {//else if (nums[i] - 1 != nums[l]) {
            if (nums[r] == nums[l]) list.add("${nums[r]}")
            else list.add("${nums[r]}->${nums[l]}") //else if (nums[r] != nums[l])
            //else println("ELSE Error")
            l = i
            r = i
            i++
        }
        //else println("Error")
        //println("list: $list")
    }
    if (nums.isNotEmpty()) {
        if (nums[r] == nums[l]) list.add("${nums[r]}")
        else list.add("${nums[r]}->${nums[l]}") //else if (nums[r] != nums[l])
        //else println("ELSE Error")
    }


    //println("answer: $list")
    return list
}

//228v2 ??
private fun summaryRangesWhile2(nums: IntArray): List<String> {
    if (nums.size == 1) {
        println("${nums[0]}")
        return listOf("${nums[0]}")
    }

    val list = mutableListOf<String>()
    var l = 0
    var r = 1

    while (r <= nums.size) {
        if (r == nums.size || nums[r] - 1 != nums[r - 1]) {   //
            if (r - l == 1) list.add("${nums[l]}")
            else list.add("${nums[l]}->${nums[r - 1]}")
            l = r
        }
        r++
        //println("list: $list")
    }

    //println("answer: $list")
    return list
}

//228v3 > OK (fast 91%, memory 74%)
private fun summaryRangesEach(nums: IntArray): List<String> {
    val list: MutableList<String> = mutableListOf()
    println("${nums.size}")
    var r = 0
    var l = 0

    nums.forEachIndexed { index, i ->
        //println("i: $i, index: $index, r: ${nums[r]}, l: ${nums[l]}")
        if (index != nums.size - 1) {
            //println("item ${index + 1}")
            if (i == nums[l]) {}//ничего не делаем
            else if (i - 1 == nums[l]) l = index //продолжаем двигаться
            else { //нашли разрыв, записываем в лист
                if (nums[r] == nums[l]) list.add("${nums[r]}")
                else list.add("${nums[r]}->${nums[l]}")
                r = index
                l = index
                //println("update r: ${nums[r]}, l: ${nums[l]}")
            }
        }
        else if (nums.size == 1) list.add("$i")
        else {
            //println("last item")
            when (i - 1) {
                nums[l] -> list.add("${nums[r]}->$i")
                else -> {
                    if (nums[r] != nums[l]) {
                        list.add("${nums[r]}->${nums[l]}")
                        list.add("$i")
                    }
                    else {
                        list.add("${nums[r]}")
                        list.add("$i")
                    }
                }
            }
        }
        //println("list: $list")
    }

    //println("answer: $list")
    return list
}