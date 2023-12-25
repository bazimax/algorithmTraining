package yandex.algorithms4training.warmup

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.max
import kotlin.math.min

/**
 * A. Не минимум на отрезке
 *
 * Задана последовательность целых чисел a1, a2, …, an. Задаются запросы: сказать любой элемент последовательности
 * на отрезке от L до R включительно, не равный минимуму на этом отрезке.
 */

fun main(args: Array<String>) {
    notAMinimumOnTheSegmentV2()
}
// 16:20 - 16:49 (29мин) > notOK (271ms, 28.77Mb, не прошел тест 3)
fun notAMinimumOnTheSegment(){

    val input = BufferedReader(FileReader("input.txt"))
    val m = input.readLine().split(" ").map { it.toInt() }[1]
    val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray()


    var answer = ""
    repeat(m){
        val (l, r) = input.readLine().split(" ").map { it.toInt() }

        //val tempAns = if (arr[l] == arr[r]) "NOT FOUND" else "${arr[r]}"

        //answer = if (answer == "") tempAns else "$answer\n$tempAns"
        answer = if (arr[l] == arr[r]) answer + "NOT FOUND\n" else "$answer${arr[r]}\n"

    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

// 16:50 - 17:35 (45мин) + 30 > OK (282ms, 30.42Mb)
fun notAMinimumOnTheSegmentV2(){

    val input = BufferedReader(FileReader("input.txt"))
    val m = input.readLine().split(" ").map { it.toInt() }[1]
    val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray()

    var answer = ""
    //println("m: $m |  ${arr.joinToString(" ")}")

    repeat(m){
        var (l, r) = input.readLine().split(" ").map { it.toInt() }

        var min = min(arr[l], arr[r])
        var max = max(arr[l], arr[r])

        //проверяем есть ли значения больше минимального (i)
        loop@ do {
            val tempMin = min(arr[l], arr[r])
            val tempMax = max(arr[l], arr[r])

            min = min(min, tempMin)
            max = max(max, tempMax)

            //println("min: $min, max: $max")
            if (min < max) {
                //если есть, то мы нашли искомое, выходим из цикла
                break@loop
            }
            else {
                //если нет, то сдвигаем границы отрезка
                l++
                r--
            }
        }
        while (l <= r)

        answer = if (min == max) answer + "NOT FOUND\n" else "$answer$max\n"

    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}