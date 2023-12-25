package yandex.algorithms4training.class2

/**
 * E. Подпалиндромы //Subpalindromes
 *
 * Строка называется палиндромом, если она читается одинаково как слева направо, так и справа налево. Например,
 * строки abba, ata являются палиндромами.
 *
 * Вам дана строка. Ее подстрокой называется некоторая непустая последовательность подряд идущих символов.
 * Напишите программу, которая определит, сколько подстрок данной строки являются палиндромами.
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.min

fun main(args: Array<String>) {
    //subPalindromes()
    runTest("subPalindromes", examplesE, ::subPalindromes, )
}

private val examplesE = arrayOf(
    Pair("aaa", "6"), //1
    Pair("aba", "4"), //2
    Pair("aabbaa", "11"),
    Pair("aabaa", "9"),
    Pair("abababc", "13"),
    Pair("aa", "3"),
    Pair("a", "1"),
    //Pair("12", "2"),
)

// OK (344ms, 20.54Mb)
private fun subPalindromes(){
    //Реализация алгоритма Манакера //https://e-maxx.ru/algo/palindromes_count
    val input = BufferedReader(FileReader("input.txt"))
    val s = input.readLine().trim()

    val answer = calcA(s) + calcB(s)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer.toString())
    output.flush()
}

private fun calcA(s: String): Int{
    // для нечётной длины
    val arr = IntArray(s.length)

    var (l, r) = 0 to -1

    for (i in s.indices) {
        var k = if (i > r) 1 else min(arr[l + r - i], r - i + 1)
        while (i + k < s.length && i - k >= 0 && s[i + k] == s[i - k]) ++k
        arr[i] = k
        if (i + k - 1 > r) {
            l = i - k + 1
            r = i + k - 1
        }
    }

    return arr.sum()
}

private fun calcB(s: String): Int{
    // для чётной длины
    val arr = IntArray(s.length)

    var (l, r) = 0 to -1

    for (i in s.indices) {
        var k = if (i > r) 0 else min(arr[l + r - i + 1], r - i + 1)
        while (i + k < s.length && i - k - 1 >= 0 && s[i + k] == s[i - k - 1]) ++k
        arr[i] = k
        if (i + k - 1 > r) {
            l = i - k
            r = i + k - 1
        }
    }

    return arr.sum()
}