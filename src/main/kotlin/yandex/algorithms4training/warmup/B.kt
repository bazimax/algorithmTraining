package yandex.algorithms4training.warmup

/**
 * B. Сложить две дроби //B. Add two fractions
 *
 * Даны две рациональные дроби: a/b и c/d. Сложите их и результат представьте в виде несократимой дроби m/n.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    addTwoFractionsV3()
}

// 18:05 - 18:40 > OK (232ms, 15.16Mb)
fun addTwoFractionsV3(){
    //алгоритм евклида
    val input = BufferedReader(FileReader("input.txt"))

    val (a, b, c, d) = input.readLine().split(" ").map { it.toInt() } //a/b, c/d (a,b,c,d <= 100)

    val numerator = (a * d) + (b * c) //числитель
    val denominator = b * d //знаменатель

    var min = min(numerator, denominator)
    var max = max(numerator, denominator)

    while (max % min != 0) {
        val tempMin = max % min
        max = min
        min = tempMin
    }

    val answer = "${numerator / min} ${denominator / min}"

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

// notOK частичное решение (215ms, 15.13Mb, не прошел тест 9)
fun addTwoFractionsV2(){
    val input = BufferedReader(FileReader("input.txt"))

    val (a, b, c, d) = input.readLine().split(" ").map { it.toInt() } //a/b, c/d (a,b,c,d <= 100)

    var numerator = 0 //числитель
    var denominator = 0 //знаменатель

    var answer = ""

    if (b == 0 || d == 0) answer = "0 0"
    else {
        when {
            b == d -> { // 1/2, 1/2
                numerator = a + c
                denominator = b
            }
            b > d && b % d == 0 -> { // 1/4, 1/2
                numerator = a + (c * (b / d))
                denominator = b
            }
            b > d && b % d != 0 -> { // 1/3, 1/2
                numerator = (a * d) + (c * b)
                denominator = b * d
            }
            b < d && d % b == 0 -> { // 1/2, 1/4
                numerator = (a * (d / b)) + c
                denominator = d
            }
            b < d && d % b != 0 -> { // 1/2, 1/3
                numerator = (a * d) + (c * b)
                denominator = b * d
            }
        }

        when {
            numerator == denominator -> {
                numerator = 1
                denominator = 1
            }
            numerator > denominator && numerator % denominator == 0 -> {
                numerator /= denominator
                denominator = 1
            }
            numerator < denominator && denominator % numerator == 0 -> {
                denominator /= numerator
                numerator = 1
            }
        }

        when {
            numerator % 2 == 0 && denominator % 2 == 0 -> {
                numerator /= 2
                denominator /= 2
            }
            numerator % 3 == 0 && denominator % 3 == 0 -> {
                numerator /= 3
                denominator /= 3
            }
            numerator % 5 == 0 && denominator % 5 == 0 -> {
                numerator /= 5
                denominator /= 5
            }
            numerator % 7 == 0 && denominator % 7 == 0 -> {
                numerator /= 7
                denominator /= 7
            }
        }

        answer = "$numerator $denominator"
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

// 15:20 - 16:25 (1ч5мин) > notOK частичное решение (217ms, 15.04Mb, не прошел тест 5)
fun addTwoFractions(){
    val input = BufferedReader(FileReader("input.txt"))

    val (a, b, c, d) = input.readLine().split(" ").map { it.toInt() } //a/b, c/d (a,b,c,d <= 100)

    var numerator = 0 //числитель
    var denominator = 0 //знаменатель

    var answer = ""

    if (b == 0 || d == 0) answer = "0 0"
    else {
        when {
            b == d -> { // 1/2, 1/2
                numerator = a + c
                denominator = b
            }
            b > d && b % d == 0 -> { // 1/4, 1/2
                numerator = a + (c * (b / d))
                denominator = b
            }
            b > d && b % d != 0 -> { // 1/3, 1/2
                numerator = (a * d) + (c * b)
                denominator = b * d
            }
            b < d && d % b == 0 -> { // 1/2, 1/4
                numerator = (a * (d / b)) + c
                denominator = d
            }
            b < d && d % b != 0 -> { // 1/2, 1/3
                numerator = (a * d) + (c * b)
                denominator = b * d
            }
        }

        answer = if (numerator == denominator) {
            "1 1"
        } else if (numerator > denominator) {
            if (numerator % denominator == 0) "${numerator / denominator} 1"
            else "$numerator $denominator"
        } else {
            if (denominator % numerator == 0) "1 ${denominator / numerator}"
            else "$numerator $denominator"
        }
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}
