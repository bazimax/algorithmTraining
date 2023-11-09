package yandex.algorithms4training.class1

/**
 * C. Слияние
 *
 * Базовый алгоритм для сортировки слиянием — алгоритм слияния двух упорядоченных массивов в один упорядоченный массив.
 * Эта операция выполняется за линейное время с линейным потреблением памяти. Реализуйте слияние двух массивов в
 * качестве первого шага для написания сортировки слиянием.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    mergerInput()
}

//14:50 - 15:40 (50мин) > OK (2.694s, 200.94Mb)
fun mergerInput(){
    val input = BufferedReader(FileReader("input.txt"))

    val n = input.readLine().split(" ").map { it.toInt() }[0] //0 ≤ N ≤ 10^6
    var arrN = emptyArray<Int>().toIntArray()
    if (n != 0) arrN = input.readLine().split(" ").map { it.toInt() }.toIntArray() //-10^9 ≤ arr[i] ≤ 10^9
    else input.readLine()

    val m = input.readLine().split(" ").map { it.toInt() }[0] //0 ≤ N ≤ 10^6
    var arrM = emptyArray<Int>().toIntArray()
    if (m != 0) arrM = input.readLine().split(" ").map { it.toInt() }.toIntArray() //-10^9 ≤ arr[i] ≤ 10^9


    var answer = ""
    val ansArr = IntArray(n + m)


    if (arrN.isNotEmpty() && arrM.isNotEmpty()) {
        val newArr = IntArray(n + m)
        merger(arrN, arrM, newArr)

        newArr.forEachIndexed { index, i -> ansArr[index] = i }
        answer = ansArr.joinToString(" ")

    }
    else {
        answer = "${arrN.joinToString(" ")}${arrM.joinToString(" ")}"
    }


    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}

private fun merger(arrN: IntArray,
                   arrM: IntArray,
                   newArr: IntArray){
    var iN = 0
    var iM = 0

    newArr.forEachIndexed { index, _ ->
        when {
            iN < arrN.size && iM < arrM.size && arrN[iN] <= arrM[iM]  -> {
                newArr[index] = arrN[iN]
                iN++
            }
            iN < arrN.size && iM < arrM.size && arrN[iN] > arrM[iM] -> {
                newArr[index] = arrM[iM]
                iM++
            }
            iN < arrN.size && iM == arrM.size  -> {
                newArr[index] = arrN[iN]
                iN++
            }
            iN == arrN.size && iM < arrM.size -> {
                newArr[index] = arrM[iM]
                iM++
            }
        }
    }
}
