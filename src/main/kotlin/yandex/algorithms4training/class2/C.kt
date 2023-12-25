package yandex.algorithms4training.class2

/**
 * C. Z-функция //Z-function
 *
 * Дана непустая строка S, длина которой N не превышает 106. Будем считать, что элементы строки нумеруются от 0 до N-1.
 * Вычислите z-функцию z[i] для всех i от 0 до N-1. z[i] определяется как максимальная длина подстроки, начинающейся
 * с позиции i и совпадающей с префиксом всей строки. z[0] = 0
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    //zFunctionV3()
    runTest("zFunction", examplesC, ::zFunctionV3, 0, printAnswer = true)
    //runOneTest("zFun", ::zFunctionV3, "y_4_2_C_92_input.txt", "y_4_2_C_92_correctAns.txt")
}

private val examplesC = arrayOf(
    Pair("abracadabra", "0 0 0 1 0 1 0 4 0 0 1"),
    Pair("abcdabcabcdabcdab", "0 0 0 0 3 0 0 7 0 0 0 6 0 0 0 2 0"),
    Pair("abcdabscabcdabia", "0 0 0 0 2 0 0 0 6 0 0 0 2 0 0 1"),
    Pair("zzz", "0 2 1"),
    Pair("bbbbbbabab", "0 5 4 3 2 1 0 1 0 1"),
    Pair("", "0"),
    //Pair("12", "2"),
)

// OK (1.08s, 61.38Mb)
private fun zFunctionV3(){
    //через бинарный поиск ~O(NlogN)
    val input = BufferedReader(FileReader("input.txt"))
    val s = input.readLine() // S <= 50000

    var ans = "0"

    if (s != null) {

        //заранее рассчитываем хеши подстрок
        val s1 = " $s"
        val n = s1.length
        val p = powIa(10, 9) + 7 //10.0.pow(9) + 7
        val x = 257
        val arrH = LongArray(n) {0}
        val arrX = LongArray(n) {0}
        arrX[0] = 1L

        for (i in 1 until n) {
            arrH[i] = (arrH[i - 1] * x + s1[i].code) % p
            arrX[i] = (arrX[i - 1] * x) % p
        }

        val ansArr = IntArray(s.length) {0}

        for (i in 1..ansArr.lastIndex) {
            var len = 0

            var left = i
            var right = ansArr.lastIndex

            if (s[i] == s[0]) {
                while (left < right) {
                    val mid = (left + right) / 2
                    //println("l:$left, r:$right, mid:$mid :: ${mid - i + 1}")

                    if (compareStr(mid - i + 1, i, arrH, arrX, p)) {
                        left = mid + 1
                        len = mid - i + 1
                        //println(" if len:$len, mid:${mid - i + 1}")
                    }
                    else {
                        right = mid - 1
                        //println(" else")
                    }
                }

                if (left + 1 >= right) {
                    if (compareStr(right - i + 1, i, arrH, arrX, p)) len = right - i + 1
                    //println(" else : $len")
                }
            }
            ansArr[i] = len
            //println("ansArr[$i] = $len")
            //println("count:$count")
        }

        ans = ansArr.joinToString(" ")

    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

private fun compareStr(len: Int, stringStartB: Int, h: LongArray, x: LongArray, p: Int) : Boolean {
    val stringStartA = 0
    val a = (h[stringStartA + len] + h[stringStartB] * x[len]) % p
    val b = (h[stringStartB + len] + h[stringStartA] * x[len]) % p
    /*println(" len: $len, ssB = $stringStartB")
    println(" ${h[stringStartA + len]} + ${h[stringStartB]} * ${x[len]} = $a")
    println(" ${h[stringStartB + len]} + ${h[stringStartA]} * ${x[len]} = $b ${a == b}")*/
    return a == b
}
private fun powIa(num: Int, degree: Int) : Int {
    if (degree == 0) return 1

    var ans = 1
    repeat(degree) { ans *= num }

    return ans
}


// медленное решение (2.075s, 57.99Mb, не прошел тест 92 TL)
private fun zFunctionV2(){
    val input = BufferedReader(FileReader("input.txt"))
    val s = input.readLine() // S <= 50000

    var ans = "0"

    if (s != null) {

        //заранее рассчитываем хеши подстрок
        val s1 = " $s"
        val n = s1.length
        val p = powIa(10, 9) + 7 //10.0.pow(9) + 7
        val x = 257
        val arrH = LongArray(n) {0}
        val arrX = LongArray(n) {0}
        arrX[0] = 1L

        for (i in 1 until n) {
            arrH[i] = (arrH[i - 1] * x + s1[i].code) % p
            arrX[i] = (arrX[i - 1] * x) % p
        }
        //println("--")

        //z-function
        val ansArr = IntArray(s.length) {0}

        for (i in 1..ansArr.lastIndex) {
            var len = 0
            var equal = true
            while (equal && i + len++ <= ansArr.lastIndex) {
                equal = compareStr(len, i, arrH, arrX, p)
                //println(" e: $equal, len: $len")
            }
            ansArr[i] = len - 1
            //println("ansArr[i]: ${ansArr[i]}")
        }
        //println("----")

        ans = ansArr.joinToString(" ")

        /*println("s: -$s-")
        if (s == "abracadabra") println("0 0 0 1 0 1 0 4 0 0 1")
        if (s == "abcdabcabcdabcdab") println("0 0 0 0 3 0 0 7 0 0 0 6 0 0 0 2 0")
        if (s == "abcdabscabcdabia") println("0 0 0 0 2 0 0 0 6 0 0 0 2 0 0 1")*/
        //println(ans)
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

// BACKUP
private fun zFunctionPrefix(){
    //prefix
    //Pair("abracadabra", "0 0 0 1 0 1 0 1 2 3 4"),
    //Pair("abcdabcabcdabcdab", "0 0 0 0 1 2 3 1 2 3 4 5 6 7 4 5 6"),
    //Pair("abcdabscabcdabia", "0 0 0 0 1 2 0 0 1 2 3 4 5 6 0 1"),
    val input = BufferedReader(FileReader("input.txt"))
    val s = input.readLine() ?: " " // S <= 50000

    val arrP = IntArray(s.length) {0}


    for (i in 1 until s.length){
        var k = arrP[i - 1]
        while (k > 0 && s[k] != s[i]) {
            k = arrP[k - 1]
        }
        if (s[k] == s[i]) {
            k++
        }
        arrP[i] = k
    }

    val arrZ = IntArray(s.length) {0}
    var left = 0
    var right = 0

    for (i in 1 until s.length){
        arrZ[i] = max(0, min(arrZ[i - left], right - 1))
        while (i + arrZ[i] < s.length && s[arrZ[i]] == s[i + arrZ[i]]) {
            arrZ[i] += 1
        }
        if (i + arrZ[i] > right) {
            left = i
            right = i + arrZ[i]
        }
    }

    println(arrP.joinToString(" "))
    println(arrZ.joinToString(" "))

    val arrAns = IntArray(arrZ.size - 1){0}
    for (i in 0..arrZ.size - 2) {
        arrAns[i] = arrZ[i]
    }
    //println(arrAns.joinToString(" "))

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(arrZ.joinToString(" "))
    output.flush()
}
