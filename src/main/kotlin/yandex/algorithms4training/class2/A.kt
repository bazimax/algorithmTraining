package yandex.algorithms4training.class2

/**
 * A. Равенство подстрок //Substring Equality
 *
 * Дана строка S, состоящая из строчных латинских букв.
 * Определите, совпадают ли строки одинаковой длины L, начинающиеся с позиций A и B.
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    //substringEquality()
    runTest("substringEquality", examplesA, ::substringEquality)
}

private val examplesA = arrayOf(
    Pair("acabaca\n" +
            "3\n" +
            "4 3 2\n" +
            "3 4 0\n" +
            "2 0 1",
        "no\n" +
                "yes\n" +
                "no"),
    Pair("caeabaeadedcbdcdccec\n" +
            "10\n" +
            "13 4 3\n" +
            "2 12 15\n" +
            "10 1 3\n" +
            "3 8 15\n" +
            "13 5 6\n" +
            "7 2 6\n" +
            "9 8 8\n" +
            "19 0 0\n" +
            "19 0 0\n" +
            "6 7 13",
        "no\n" +
                "no\n" +
                "no\n" +
                "no\n" +
                "no\n" +
                "no\n" +
                "yes\n" +
                "yes\n" +
                "yes\n" +
                "no"),
    //Pair("12", "2"),
)

// 6:05 - 6:50 (45мин) > OK (1.246s, 44.73Mb)
private fun substringEquality(){
    val input = BufferedReader(FileReader("input.txt"))

    val s = " " + input.readLine().trim() //строка состоящая из строчных латинских букв - 1 ≤ |S| ≤ 2 ⋅ 10^5
    val q = input.readLine().trim().split(" ").map { it.toInt() }[0] //количество запросов - 1 ≤ Q ≤ 2 ⋅ 10^5

    //s = " $s"
    val n = s.length
    val p = powIa(10, 9) + 7 //10.0.pow(9) + 7
    val x = 257
    val arrH = LongArray(n) {0}
    val arrX = LongArray(n) {0}
    arrX[0] = 1L


    for (i in 1 until n) {
        arrH[i] = (arrH[i - 1] * x + s[i].code) % p
        arrX[i] = (arrX[i - 1] * x) % p
    }

    val arrAns = Array(q){""}

    repeat(q) {
        // длина подстрок и позиции, с которых они начинаются - 1 ≤ L ≤ |S|, 0 ≤ A, B ≤ (|S| - L)
        var (len, from1, from2) = input.readLine().trim().split(" ").map { it.toInt() }
        //from2++ //если 0
        //from1++
        //val a = (arrH[from1 + len - 1] + arrH[from2 - 1] * arrX[len]) % p
        //val b = (arrH[from2 + len - 1] + arrH[from1 - 1] * arrX[len]) % p
        val a = (arrH[from1 + len] + arrH[from2] * arrX[len]) % p
        val b = (arrH[from2 + len] + arrH[from1] * arrX[len]) % p

        arrAns[it] = if (a == b) "yes" else "no"
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(arrAns.joinToString("\n"))
    output.flush()
}

private fun powIa(num: Int, degree: Int) : Int {
    if (degree == 0) return 1

    var ans = 1
    repeat(degree) { ans *= num }

    return ans
}

