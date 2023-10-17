package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFun(testAsk)
}

//152. Maximum Product Subarray - Medium - https://leetcode.com/problems/maximum-product-subarray/
private val testAsk = arrayOf(
    Pair(intArrayOf(-2,0,-1), 0),
    Pair(intArrayOf(2,3,-2,4), 6),
    Pair(intArrayOf(3,-1,4), 4),
    Pair(intArrayOf(2,3,0,-2,-2,-1,4), 8),
    Pair(intArrayOf(-1,-2,-3,0), 6),
    Pair(intArrayOf(0,2,-1,3,4,-1,-2,8), 192),
    Pair(intArrayOf(0,2,-1,3,4,-1,0,-2,8), 24),
    Pair(intArrayOf(-1,0,-2,2), 2)
    //Pair(intArrayOf(), )
)
private fun testFun(ask: Array<Pair<IntArray, Int>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = maxProductV2(i.first)
            println("${answer == i.second} :: in: [${i.first.joinToString(",")}], " +
                    "correctAnswer: ${i.second}, ans: $answer")
        }
        println("time: $time")
    }
}

//152v2 > 21:20 - 22:30 (~1ч10мин) > OK (fast 45%, memory 39%) -
private fun maxProductV2(nums: IntArray): Int {
    var multiplier = 1
    var countElementMinus = 0
    var max = nums[0]

    var lMinus = 0
    var rMinus = 0
    var count = 0

    for ((index, value) in nums.withIndex()) {

        if (value == 0) {
            //мы в 0 - отрезок закончился - считаем, и готовимся дальше считать как с начала
            if (max < 0) max = 0

            //if (countElementMinus == 0) //все записано
            if (countElementMinus == 1 && count > 1) {
                multiplier /= lMinus
                if (multiplier > max) max = multiplier
            }
            else if (countElementMinus >= 3) {
                val tempMax = maxOf(multiplier / lMinus, multiplier / rMinus)
                if (tempMax > max) max = tempMax
            }

            lMinus = 0
            rMinus = 0
            count = 0
            countElementMinus = 0
            multiplier = 1
            continue
        }

        else if (value < 0) {
            countElementMinus++
            if (countElementMinus == 1) lMinus = multiplier * value
            else if (countElementMinus > 1 && countElementMinus % 2 != 0) rMinus = value // (3/5/7..)
            /*else {
                //countElementMinus <= 0 || countElementMinus % 2 == 0 (2/4/6..)
            }*/
        }
        else { //value > 0

        }
        multiplier *= value
        rMinus *= value
        if (multiplier > max) max = multiplier

        count++

        if (index == nums.size - 1) {
            //мы в последней клетке - отрезок закончился - считаем
            //if (countElementMinus == 0) //все записано
            if (countElementMinus == 1 && count > 1) {
                multiplier /= lMinus
                if (multiplier > max) max = multiplier
            }
            else if (countElementMinus >= 3) {
                val tempMax = maxOf(multiplier / lMinus, multiplier / rMinus)
                if (tempMax > max) max = tempMax
            }
            break
        }

    }
    return max
}


//152 - https://leetcode.com/problems/maximum-product-subarray/
//val nums = intArrayOf(-2,0,-1) //(2,3,-2,4) 6 //(-2,0,-1) 0 //(3,-1,4) 4 //(2,3,0,-2,-2,-1,4) 8 //(-1,-2,-3,0) 6
//152 > 21:00 - 21:20 быстрый вариант, но есть ошибки, 21:20 - 23:00 - не получилось
private fun maxProduct(nums: IntArray): Int {
    println("lastIndex: ${nums.lastIndex}")
    var max = Int.MIN_VALUE//nums[0]
    var current = 1

    var leftMinus = 0//nums[0]
    var rightMinus = 0

    nums.forEachIndexed { index, it ->
        println("it: $it")
        println("left: $leftMinus, right: $rightMinus")
        if (it != 0 && index != nums.lastIndex) {
            //если НЕ 0 или НЕ конец массива
            println("if: $index")
            current *= it

            if (it < 0) {
                rightMinus = it
                if (leftMinus == 0) leftMinus = current
            }
            else if (rightMinus != 0) rightMinus *= it //(it > 0 && rightMinus != 0)
            max = maxOf(max, current)
        }
        else {
            //если 0 или конец массива подсчитываем max
            println("else $index")
            //current *= it
            if (index == 0) max = current * it //если это первый элемент
            else {
                if (it != 0) current *= it
                println("else 2")
                if (leftMinus != 0 && current < 0) {
                    println("else leftMinus")
                    if (it < 0) {
                        rightMinus = it
                    }
                    else if (it > 0 && rightMinus != 0) rightMinus *= it
                    current /= maxOf(leftMinus, rightMinus)
                    println("current: $current, minOf: ${minOf(leftMinus, rightMinus)}")

                }
            }
            max = maxOf(max, current)
            leftMinus = 0
            rightMinus = 0
            current = 1
        }
        println("left: $leftMinus, right: $rightMinus")
        println("each max: $max, current: $current")
    }

    /*//быстрый, но не верный вариант
    var max = nums[0]
    var current = 1
    nums.forEach {
        max = maxOf(max, current * it)
        if (it != 0) {
            current *= it
        }
        else {
            current = 1
        }
    }*/
    return max
}