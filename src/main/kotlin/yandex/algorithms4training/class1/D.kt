package yandex.algorithms4training.class1

/**
 * D. Сортировка слиянием
 *
 * Реализуйте сортировку слиянием, используя алгоритм из предыдущей задачи.
 * На каждом шаге делите массив на две части, сортируйте их независимо и сливайте с помощью уже реализованной функции.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    mergeSortInput()
}

//17:50 - 18:30 (40мин) > notOK частичное решение (1.941s, 175.07Mb)
fun mergeSortInput(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().split(" ").map { it.toInt() }[0] //0 ≤ N ≤ 10^6

    var ansArr = IntArray(n)

    if (n != 0) {
        val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray() //-10^9 ≤ arr[i] ≤ 10^9

        ansArr = mergeSortV1(arr)
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ansArr.joinToString(" "))
    output.flush()
}

private fun mergeSort(arr: IntArray, l: Int = 0, r: Int = arr.size - 1): IntArray{
    return if (l == r) intArrayOf( arr[l] )
        else {
            val p = (l + r) / 2

            val arrN = mergeSort(arr, l, p)
            val arrM = mergeSort(arr, p + 1, r)

            merger(arrN, arrM)
        }
}

private fun merger(arrN: IntArray, arrM: IntArray): IntArray{
    var iN = 0 //iteratorN
    var iM = 0 //iteratorM

    val sortArr = IntArray(arrN.size + arrM.size)


    sortArr.forEachIndexed { index, _ ->
        when {
            iN < arrN.size && iM < arrM.size && arrN[iN] <= arrM[iM]  -> {
                sortArr[index] = arrN[iN]
                iN++
            }
            iN < arrN.size && iM < arrM.size && arrN[iN] > arrM[iM] -> {
                sortArr[index] = arrM[iM]
                iM++
            }
            iN < arrN.size && iM == arrM.size  -> {
                sortArr[index] = arrN[iN]
                iN++
            }
            iN == arrN.size && iM < arrM.size -> {
                sortArr[index] = arrM[iM]
                iM++
            }
        }
    }
    return sortArr
}

//backup - даже чуть медленнее
private fun mergeSortV1(arr: IntArray, l: Int = 0, r: Int = arr.size - 1): IntArray{
    //println("${arr.joinToString(" ")}  |  l: $l, r: $r")
    return if (l + 1 < r) {
        val p = (l + r) / 2

        val arrN = mergeSortV1(arr, l, p)
        val arrM = mergeSortV1(arr, p + 1, r)

        merger(arrN, arrM)
    }
    else if (l + 1 == r) {
        if (arr[l] > arr[r]) intArrayOf( arr[r], arr[l] ) else intArrayOf( arr[l], arr[r] )
    }
    else intArrayOf( arr[l] ) //if(l == r)
}