package yandex.algorithms4training.final

/**
 * B. Зеркальная z-функция //Mirror z-function
 *
 * Строка S состоит из N букв. Зеркальная z-функция определяется для индекса i как максимально возможное k, такое что:
 * S[1] + S[2] + S[3] + … + S[k] = S[i] + S[i–1] + S[i–2] + ... + S[i–k+1]
 *
 * Определите значение зеркальной z-функции для всех i от 1 до N.
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    //mirrorZFunction()
    runTest("mirrorZFunction", examplesB, ::mirrorZFunction, )
}

private val examplesB = arrayOf(
    Pair("5\n" +
            "BBABB",
        "1 2 0 1 5"), //1
    Pair("49\n" +
            "burannarubabyrrybaglipspiritmatankollokvzumbboyus",
        "1 0 0 0 0 0 0 0 0 10 0 1 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0"), //2
    Pair("10\n" +
            "burannarub",
        "1 0 0 0 0 0 0 0 0 10"),
    Pair("5\n" +
            "BLACK",
        "1 0 0 0 0"),
    //Pair("12", "2"),
)

// OK (0.775s, 53.16Mb)
private fun mirrorZFunction(){
    val input = BufferedReader(FileReader("input.txt"))
    input.readLine() //1 ≤ N ≤ 200000
    val s = input.readLine().trim() //input.readLine() // 1 ≤ S ≤ 200000

    val s1 = mutableListOf<String>()
    s.forEach { s1.add("$it") }

    val sNormal = listOf(" ") + s1
    val sRev = listOf(" ") + s1.asReversed()

    //заранее рассчитываем хеши подстрок (и зеркальной версии)
    val size = sNormal.size
    val p = 1000000007//powIa(10, 9) + 7 //10.0.pow(9) + 7
    val x = 257

    val hNormal = LongArray(size) {0}
    val hRev = LongArray(size) {0}
    val xN = LongArray(size) {0}
    xN[0] = 1L
    for (i in 1 until size) {
        hNormal[i] = (hNormal[i - 1] * x + sNormal[i][0].code) % p
        hRev[i] = (hRev[i - 1] * x + sRev[i][0].code) % p
        xN[i] = (xN[i - 1] * x) % p
    }

    //через бинарный поиск
    val ansArr = IntArray(sNormal.size - 1) {0}

    for (i in 1..sNormal.lastIndex) {
        val len = if(sNormal[i] == sNormal[1]) checkLen(i, hNormal, hRev, xN, p) else 0
        ansArr[i - 1] = len
    }

    val answer = ansArr.joinToString(" ")

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}
private fun checkLen(i: Int, hNormal: LongArray, hRev: LongArray, xN: LongArray, p: Int) : Int {

    var len = 0
    val stringStartB = hNormal.lastIndex - i + 1
    var left = 1
    var right = i + 1

    while (left < right) {
        val mid = (left + right) / 2

        if (compareStrRev(stringStartB,mid - 1, hNormal, hRev, xN, p)) {
            len = mid
            left = mid + 1
        }
        else {
            right = mid
        }
    }
    return len
}
private fun compareStrRev(stringStartB: Int, len: Int, hNormal: LongArray, hRev: LongArray, xN: LongArray, p: Int) : Boolean {
    val stringStartA = 1
    val a = (hNormal[stringStartA + len] + hRev[stringStartB] * xN[len]) % p
    val b = (hRev[stringStartB + len] + hNormal[stringStartA] * xN[len]) % p

    return a == b
}


// BACKUP with LOGS for TEST
private fun mirrorZFunctionWithLog(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] //1 ≤ N ≤ 200000
    val s = input.readLine().trim() //input.readLine() // 1 ≤ S ≤ 200000


    val s1 = mutableListOf<String>()
    s.forEach { s1.add("$it") }
    println("s1: $s1")

    val sNormal = listOf(" ") + s1
    val sRev = listOf(" ") + s1.asReversed()
    println("sN: $sNormal \nsR: $sRev")

    //заранее рассчитываем хеши подстрок (и зеркальной версии)
    val size = sNormal.size
    val p = powIa(10, 9) + 7 //10.0.pow(9) + 7
    val x = 257

    val hNormal = LongArray(size) {0}
    val hRev = LongArray(size) {0}
    val xN = LongArray(size) {0}
    xN[0] = 1L
    for (i in 1 until size) {
        hNormal[i] = (hNormal[i - 1] * x + sNormal[i][0].code) % p
        hRev[i] = (hRev[i - 1] * x + sRev[i][0].code) % p
        xN[i] = (xN[i - 1] * x) % p
    }

    println(hNormal.joinToString())
    println(hRev.joinToString())
    println(xN.joinToString())

    //через бинарный поиск
    val ansArr = IntArray(sNormal.size - 1) {0}

    for (i in 1..sNormal.lastIndex) {

        val len = if(sNormal[i] == sNormal[1]) checkLenWithLog(i, hNormal, hRev, xN, p) else 0

        ansArr[i - 1] = len
        println("ansArr[${i - 1}] = $len")
    }

    val answer = ansArr.joinToString(" ")
    println("answer: $answer")

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}
private fun checkLenWithLog(i: Int, hNormal: LongArray, hRev: LongArray, xN: LongArray, p: Int) : Int {

    println(" i: $i")
    var len = 0

    val stringStartB = hNormal.lastIndex - i + 1
    var left = 1
    var right = i + 1

    while (left < right) {
        val mid = (left + right) / 2
        println(" stringStartB: $stringStartB, l: $left, r: $right, mid: $mid")

        if (compareStrRevWithLog(stringStartB,mid - 1, hNormal, hRev, xN, p)) {
            len = mid
            left = mid + 1
        }
        else {
            right = mid
        }
        //println(" mid: $mid")
    }

    println("return: $len")
    return len
}
private fun compareStrRevWithLog(stringStartB: Int, len: Int, hNormal: LongArray, hRev: LongArray, xN: LongArray, p: Int) : Boolean {

    val stringStartA = 1
    val a = (hNormal[stringStartA + len] + hRev[stringStartB] * xN[len]) % p
    val b = (hRev[stringStartB + len] + hNormal[stringStartA] * xN[len]) % p
    //println(" len: $len, ssB = $stringStartB")
    //println(" ${hNormal[stringStartA + len]} + ${hRev[stringStartB]} * ${xN[len]} = $a")
    //println(" ${hRev[stringStartB + len]} + ${hNormal[stringStartA]} * ${xN[len]} = $b ${a == b}")
    return a == b
}
private fun powIa(num: Int, degree: Int) : Int {
    if (degree == 0) return 1

    var ans = 1
    repeat(degree) { ans *= num }

    return ans
}