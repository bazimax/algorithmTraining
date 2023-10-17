package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFun(testAsk)
}

//70 - Easy - https://leetcode.com/problems/climbing-stairs/description/

private val testAsk = arrayOf(
    Pair(1,1),
    Pair(2,2),
    Pair(3,3),
    Pair(4,5),
    Pair(5,8),
    Pair(6,13),
    Pair(7,21)
)
private fun testFun(ask: Array<Pair<Int, Int>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = climbStairs70(i.first)
            println("${answer == i.second} :: in: [${i.first}], " +
                    "correctAnswer: ${i.second}, ans: $answer") //contentEquals - Array
            //println("${answer == i.second} :: in: [${i.first.joinToString(",")}], target: ${i.second}, " +
            //        "correctAnswer: ${i.third}, ans: $answer") //contentEquals - Array
        }
        println("time: $time")
    }
}

//70 > 00:00 - 01:00 (~1ч) > OK (fast 13%, memory 93%) - тут не прогрессия, а числа Фибоначчи (формулы нет, нужен обычный цикл),
//если бы сразу понял, то решил бы минут за 10
private fun climbStairs70(n: Int): Int {
    var i = 0
    var n1 = 0
    var nPlus = 1
    while (i < n) {
        val nPlusTemp = n1 + nPlus
        n1 = nPlus
        nPlus = nPlusTemp
        i++
    }
    return nPlus
}