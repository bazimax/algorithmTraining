package yandex.algorithms4training.class2

/**
 * B. Основание строки //String base
 *
 * Строка S была записана много раз подряд, после чего от получившейся строки взяли префикс и дали вам.
 * Ваша задача определить минимально возможную длину исходной строки S.
 */

import yandex.runTest
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    stringBaseV3()
    //runTest("stringBase", examplesB, ::stringBaseV3)
}

private val examplesB = arrayOf(
    Pair("zzz", "1"), //1
    Pair("bcabcab", "3"), //2
    Pair("zbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzz", "113"), //3
    Pair("", "0"),
    Pair("acabaca", "4"),
    Pair("acaaca", "3"),
    //Pair("12", "2"),
)

// OK (257ms, 15.52Mb)
private fun stringBaseV3(){

    val input = BufferedReader(FileReader("input.txt"))

    val prefix = input.readLine() // S <= 50000

    var ans = "0"

    if (prefix != null) {

        val prefix1 = " $prefix"
        val n = prefix1.length
        val p = powIa(10, 9) + 7 //10.0.pow(9) + 7
        val x = 257
        val arrH = LongArray(n) {0}
        val arrX = LongArray(n) {0}
        arrX[0] = 1L

        for (i in 1 until n) {
            arrH[i] = (arrH[i - 1] * x + prefix1[i].code) % p
            arrX[i] = (arrX[i - 1] * x) % p
        }

        var len = 1
        loop@ while (len < prefix1.length) {

            val numberOfPossibleSites = (prefix1.length - 1) / len
            var repeat = 0
            var equal = true

            //проверяем участки длинной len столько раз сколько поместятся
            while (equal && repeat++ < numberOfPossibleSites - 1) {

                val stringStartB = len * repeat
                equal = compareStr(len, stringStartB, arrH, arrX, p)
                //println(" len: $len, startB: $stringStartB r: $repeat, equal: $equal")

            }
            //если проверенные участки совпали, проверяем оставшийся кусочек
            val remainderLen = prefix.length % len
            if (equal && remainderLen != 0) {
                equal = compareStr(remainderLen, len * numberOfPossibleSites, arrH, arrX, p)
                //println("remainderLen: $remainderLen, equal: $equal, :: ${len * numberOfPossibleSites}")
            }
            //если equal до сих пор true значит мы нашли минимальное значение len
            if (equal) {
                //println("equal: true, len: $len")
                break@loop
            }
            len++
        }
        ans = "$len"
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

private fun compareStr(len: Int, stringStartB: Int, h: LongArray, x: LongArray, p: Int) : Boolean {
    val stringStartA = 0
    val a = (h[stringStartA + len] + h[stringStartB] * x[len]) % p
    val b = (h[stringStartB + len] + h[stringStartA] * x[len]) % p
    return a == b
}

private fun powIa(num: Int, degree: Int) : Int {
    if (degree == 0) return 1

    var ans = 1
    repeat(degree) { ans *= num }

    return ans
}


// 7:45 - 8:20 (35мин) > notOK частичное решение (224ms, 17.98Mb, не прошел тест 3)
private fun stringBaseV1(){
    //через z-function
    val input = BufferedReader(FileReader("input.txt"))
    val s = input.readLine() ?: "" // S <= 50000

    val arrZ = IntArray(s.length + 1) {0}
    var left = 1
    var right = 1

    for (i in 1..s.length){
        arrZ[i] = max(0, min(arrZ[i - left], right - 1))
        while (i + arrZ[i] < s.length && s[arrZ[i]] == s[i + arrZ[i]]) {
            arrZ[i] += 1
        }
        if (i + arrZ[i] > right) {
            left = i
            right = i + arrZ[i]
        }
    }

    val maxZ = arrZ.maxOrNull() ?: 0

    val ans = if (maxZ == 0) 0 else maxZ - 1

    println("s: $s")
    println(arrZ.joinToString(" "))
    println(ans)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans.toString())
    output.flush()
}

private fun stringBaseV2(){
    //через prefix
    val input = BufferedReader(FileReader("input.txt"))
    val prefix = input.readLine() ?: "" // S <= 50000
    println("prefixLength: ${prefix.length}")

    val arrP = IntArray(prefix.length) {0}


    for (i in 1 until prefix.length){
        var k = arrP[i - 1]
        while (k > 0 && prefix[k] != prefix[i]) {
            k = arrP[k - 1]
        }
        if (prefix[k] == prefix[i]) {
            k++
        }
        arrP[i] = k
    }

    val maxZ = arrP.maxOrNull() ?: 0

    val ans = if (maxZ == 0) 0 else maxZ - 1

    println("s: $prefix")
    println(arrP.joinToString(" "))
    println(arrP.size)
    println(ans)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans.toString())
    output.flush()
}
