package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFun(testAsk)
}

//IN WORK
//15. 3Sum - Medium - https://leetcode.com/problems/3sum/
private val testAsk = arrayOf(
    Pair(intArrayOf(-1,0,1,2,-1,-4), listOf(listOf(-1,-1,2),listOf(-1,0,1))),
    Pair(intArrayOf(0,1,1), listOf()),
    Pair(intArrayOf(0,0,0), listOf(listOf(0,0,0))),
    Pair(intArrayOf(-2,0,1,1,2), listOf(listOf(-2,0,2),listOf(-2,1,1))),
    //Pair(intArrayOf(0), listOf(listOf(0))),
    //Triple(intArrayOf(), ,)
)
private fun testFun(ask: Array<Pair<IntArray, List<List<Int>>>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = threeSum15Clear(i.first)
            println("${answer == i.second} :: in: [${i.first.joinToString(",")}], " +
                    "correctAnswer: ${i.second}, ans: $answer") //contentEquals - Array
            //println("${answer == i.second} :: in: [${i.first.joinToString(",")}], target: ${i.second}, " +
            //        "correctAnswer: ${i.third}, ans: $answer") //contentEquals - Array
        }
        println("time: $time")
    }
}

//15 > 20:45 - 22:40 - notОК +медленное решение O(n^2)
private fun threeSum15Clear(nums: IntArray): List<List<Int>> {
    val map = HashMap<Int, Int>(nums.size)//!! проверить скорость и место
    val resultSet = HashSet<List<Int>>()

    nums.forEach {
        var mapValue = 0
        if (map[it] == null) {
            mapValue = 0
        } else if (map[it] != null) {
            mapValue = map[it]!!
        }
        map[it] = mapValue + 1
    }

    for (index in 0..nums.size - 2) {
        val item1 = nums[index]

        for (j in index + 1 until nums.size) {
            val item2 = nums[j]
            val item3 = -(item1 + item2)

            if (item3 in map) {
                /*if (item1 == item2 && item2 == item3) { //все 3 значения одинаковы
                    if (map[item3]!! >= 3) {
                        resultSet.add(listOf(item3, item3, item3))
                    }
                }*/
                if (item1 == item3 || item2 == item3) { //2 значения
                    if (map[item3]!! >= 2) {
                        resultSet.add(listOf(item1, item2, item3).sorted())
                    }
                }
                else if (item1 != item2) {
                    resultSet.add(listOf(item1, item2, item3).sorted())
                }
            }
        }
    }
    return resultSet.toList()
}

//15v2 backup
private fun threeSum15v2(nums: IntArray): List<List<Int>> {
    val map = HashMap<Int, Int>(nums.size)//!! проверить скорость и место
    val resultSet = HashSet<List<Int>>()
    //val map = nums.associateBy {it to 0}
    //val map2 = nums.associateBy {item, value -> item to if (value)}

    nums.forEach {
        var mapValue = 0
        if (map[it] == null) {
            mapValue = 0
        } else if (map[it] != null) {
            mapValue = map[it]!!
        }
        map[it] = mapValue + 1
    }

    //map.forEach { println("${it.key}-${it.value}") }

    for (index in 0..nums.size - 2) {
        val item1 = nums[index]
        //val item2 = nums[index + 1]
        //val item3 = -(item1 + item2)

        for (j in index + 1 until nums.size) {
            val item2 = nums[j]
            val item3 = -(item1 + item2)
            //println("item1: $item1, item2; $item2, item3: $item3")

            if (item3 in map) {
                //println("if")
                //item1 == item2 && item2 == item3 && item3 == item1
                if (item1 == item2 && item2 == item3) { //все 3 значения одинаковы
                    //println("if - 1")
                    if (map[item3]!! >= 3) {
                        //println("if - 1 - 1")
                        resultSet.add(listOf(item3, item3, item3))
                        //resultList.add(listOf(item3, item3, item3))
                    }
                }
                //(item1 == item2 && item2 != item3) || (item1 != item2 && item2 == item3)
                else if (item1 == item3 || item2 == item3) { //2 значения
                    //println("if - 2")
                    if (map[item3]!! >= 2) {
                        resultSet.add(listOf(item1, item2, item3).sorted())
                        //resultList.add(listOf(item1, item2, item3).sorted())
                    }
                }
                //item1 != item2 && item2 != item3 && item1 != item3
                else if (item1 != item2) {
                    //println("if - 3")
                    resultSet.add(listOf(item1, item2, item3).sorted())
                }
                //item1 == item2 && item1 != item3
                //else println("Error")
            }
        }

        /*if (item3 in map) {
            //item1 == item2 && item2 == item3 && item3 == item1
            if (item1 == item2 && item2 == item3) { //все 3 значения одинаковы
                if (map[item3]!! >= 3) {
                    resultSet.add(listOf(item3, item3, item3))
                    //resultList.add(listOf(item3, item3, item3))
                }
            }
            //(item1 == item2 && item2 != item3) || (item1 != item2 && item2 == item3)
            else if (item1 == item3 || item2 == item3) { //2 значения
                if (map[item3]!! >= 2) {
                    resultSet.add(listOf(item1, item2, item3).sorted())
                    //resultList.add(listOf(item1, item2, item3).sorted())
                }
            }
            //item1 != item2 && item2 != item3
            else if (item1 != item2) {
                resultSet.add(listOf(item1, item2, item3).sorted())
            } else println("Error")
        }*/
    }

    //println("set")
    //resultSet.forEach { println("$it") }
    //val resultList = mutableListOf<List<Int>>()
    return if (resultSet.size == 0) mutableListOf(listOf()) else resultSet.toList()
}

//the right decision, but not mine
//15v3 > не мое решение - компиляция уз двух быстрейших
private fun threeSum15FastV3(nums: IntArray): List<List<Int>> {
    nums.sort()
    val result = arrayListOf<List<Int>>()
    val n = nums.size

    var i = 0
    while(i < n-1) {
        var l = i + 1
        var r = n - 1
        while (l < r) {
            val s = nums[i] + nums[l] + nums[r]
            when  {
                s > 0 -> r--
                s < 0 -> l++
                else -> {
                    result.add(arrayListOf(nums[i], nums[l], nums[r]))
                    val tempIndex1 = l
                    val tempIndex2 = r
                    while (l < r && nums[l] == nums[tempIndex1]) l++
                    while (l < r && nums[r] == nums[tempIndex2]) r--
                }
            }
        }
        while (i + 1 < n && nums[i] == nums[i + 1]) i++
        i++
    }
    return result
}