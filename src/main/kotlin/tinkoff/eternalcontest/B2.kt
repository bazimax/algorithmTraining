package tinkoff.eternalcontest

/**
 * 2 задание
 * Ваня принес на кухню рулет, который он хочет разделить с коллегами. Для этого он хочет разрезать рулет на N равных
 * частей. Разумеется, рулет можно резать только поперек. Соответственно, Костя сделает N - 1 разрез ножом через
 * равные промежутки.
 *
 * По возвращению с кофе-брейка Ваня задумался — а можно ли было обойтись меньшим числом движений, будь нож Вани
 * бесконечно длинным (иначе говоря, если он мог бы сделать сколько угодно разрезов за раз, если эти разрезы лежат на
 * одной прямой)? Считается, что места для разрезов намечены заранее, и все разрезы делаются с ювелирной точностью.
 *
 * Оказывается, что можно. Например, если Ваня хотел бы разделить рулет на 4 части, он мог бы обойтись двумя
 * разрезами — сначала он разделил бы рулет на две половинки, а потом совместил бы две половинки и разрезал
 * обе пополам одновременно.
 *
 * Вам дано число N, требуется сказать, каким минимальным числом разрезов можно обойтись.
 */

import tinkoff.runTest
import java.util.*

fun main(args: Array<String>) {
    cut()
    //runTest("cut", examplesB, ::cutTested)
}

private val examplesB = arrayOf(
    Pair("1", "0"),
    Pair("2", "1"),
    Pair("3", "2"),
    Pair("4", "2"),
    Pair("5", "3"),
    Pair("6", "3"),
    Pair("6", "3"),
    Pair("8", "3"),
    Pair("9", "4"),
    Pair("20", "5"),
    //Pair("12", "2"),
)

private fun cut() {
    val scan = Scanner(System.`in`)
    var n = scan.nextInt() // количество частей

    var cutCount = 0
    var cutRest = 0

    while (n / 2 >= 1) {

        if (n % 2 != 0) {
            cutRest = 1
            n--
        }

        n /= 2
        cutCount++
    }
    cutCount += cutRest

    println(cutCount)
}

// BACKUP with LOGS for TEST
private fun cutTested(str: String) : String {
    val testInput = str.toInt()
    var n = testInput

    //val scan = Scanner(System.`in`)
    //var n = scan.nextInt() // количество частей

    var cutCount = 0
    var cutRest = 0

    while (n / 2 >= 1) {

        if (n % 2 != 0) {
            //cutCount++
            cutRest = 1
            n--
        }

        n /= 2
        cutCount++
    }
    cutCount += cutRest

    //println(cutCount)
    return cutCount.toString()
}