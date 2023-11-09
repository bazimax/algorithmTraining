package yandex.algorithms4training.class1

/**
 * B. Быстрая сортировка
 *
 * Реализуйте быструю сортировку, используя алгоритм из предыдущей задачи. На каждом шаге выбирайте опорный элемент
 * и выполняйте partition относительно него. Затем рекурсивно запуститесь от двух частей, на которые
 * разбился исходный массив.
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    quickSortInputB()
}


// OK (2.246s, 184.03Mb)
fun quickSortInputB(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().split(" ").map { it.toInt() }[0] //0 ≤ N ≤ 10^6

    var ansArr = IntArray(n)

    if (n == 0) ansArr = emptyArray<Int>().toIntArray()
    else {
        val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray() //-10^9 ≤ arr[i] ≤ 10^9
        quickSortB(arr, 0, n - 1)

        arr.forEachIndexed { index, i -> ansArr[index] = i }
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ansArr.joinToString(" "))
    output.flush()
}
private fun quickSortB(arr: IntArray, l: Int, r: Int){

    if (l + 1 < r) {
        val predicate = (l until r).random() //(l + r) / 2
        val p = partitionB(arr, l, r, arr[predicate])
        quickSortB(arr, l, p.first)
        quickSortB(arr, p.second, r)
    }
    else if (l + 1 == r) {
        if (arr[l] > arr[r]) {
            val temp = arr[r]
            arr[r] = arr[l]
            arr[l] = temp
        }
    }
}
private fun partitionB(arr: IntArray, l: Int, r: Int, x: Int): Pair<Int, Int> {
    //версия для сортировки слиянием (устойчивая)
    val sortedTempArr = IntArray(r - l + 1) //последний элемент массива для индекса разделителя (predicate)

    var lassThanX = 0 //left pointer
    var moreThanX = sortedTempArr.size - 1  //right pointer

    for (index in l..r) {

        if (arr[index] < x) {
            sortedTempArr[lassThanX] = arr[index]
            lassThanX++
        }
        else if (arr[index] > x) {
            sortedTempArr[moreThanX] = arr[index]
            moreThanX--
        }
    }

    for (i in 0 until lassThanX) {
        arr[l + i] = sortedTempArr[i]
    }
    for (i in l + lassThanX ..l + moreThanX) {
        arr[i] = x
    }

    var j = l + moreThanX + 1
    for (i in sortedTempArr.size - 1 downTo moreThanX + 1) {
        arr[j] = sortedTempArr[i]
        j++
    }

    return l + lassThanX - 1 to l + moreThanX + 1 //индексы разделителей (predicate)
}

//backup
//2:50 - 4:20 (1ч30мин) > notOK частичное решение (10.072s / 124.00Mb, не прошел тест 15 TL)
//даже после изменения answer = "$answer$it " не проходит 18 тест TL
fun quickSortInput(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().split(" ").map { it.toInt() }[0] //0 ≤ N ≤ 10^6

    //var answer = ""

    var ansArr = IntArray(n)

    if (n == 0) ansArr = emptyArray<Int>().toIntArray()//answer = ""
    else {
        val arr = input.readLine().split(" ").map { it.toInt() }.toIntArray() //-10^9 ≤ arr[i] ≤ 10^9
        quickSortV2(arr, 0, n - 1)

        //arr.forEach { answer = "$answer$it " }
        arr.forEachIndexed { index, i ->
            ansArr[index] = i
        }
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ansArr.joinToString(" "))
    output.flush()
}
private fun quickSortV2(arr: IntArray, l: Int, r: Int){
    //val a = "  ".repeat(count)
    //println("$a $count  l: $l, r: $r    | ${arr.joinToString(" ")}")

    if (l + 1 < r) {
        val predicate = (l + r) / 2 //(l until r).random()
        val p = partitionA(arr, l, r, predicate)
        //println("$a $count  l: $l, r: $r    | ${arr.joinToString(" ")}    | pI: $predicate, p: $p")
        quickSortV2(arr, l, p)
        quickSortV2(arr, p + 1, r)
    }
    else if (l + 1 == r) {
        if (arr[l] > arr[r]) {
            val temp = arr[r]
            arr[r] = arr[l]
            arr[l] = temp
        }
    }
}
private fun partitionA(arr: IntArray, l: Int, r: Int, predicate: Int): Int {
    //версия для быстрой сортировки (не устойчивая)

    val x = arr[predicate]

    var equalsX = -1
    var moreThanX = -1

    for (index in l..r) {
        val i = arr[index]
        when {
            //i < x && equalsX == -1 && moreThanX == -1 -> {} //nothing do
            i < x && equalsX == -1 && moreThanX != -1 -> {
                arr[index] = arr[moreThanX]
                arr[moreThanX] = i
                moreThanX++
            }
            i < x && equalsX != -1 && moreThanX == -1 -> {
                arr[index] = arr[equalsX]
                arr[equalsX] = i
                equalsX++
            }
            i < x && equalsX != -1 && moreThanX != -1 -> {
                arr[index] = arr[moreThanX]
                arr[moreThanX] = arr[equalsX]
                arr[equalsX] = i
                moreThanX++
                equalsX++
            }
            i == x && equalsX == -1 && moreThanX == -1 -> {
                equalsX = index
            }
            i == x && equalsX == -1 && moreThanX != -1 -> {
                arr[index] = arr[moreThanX]
                arr[moreThanX] = i
                equalsX = moreThanX
                moreThanX++
            }
            i == x && equalsX != -1 && moreThanX != -1 -> {
                arr[index] = arr[moreThanX]
                arr[moreThanX] = i
                moreThanX++
            }
            i > x && equalsX == -1 && moreThanX == -1 -> {
                moreThanX = index
            }
            i > x && equalsX != -1 && moreThanX == -1 -> {
                moreThanX = index
            }
            //i > x && equalsX != -1 && moreThanX != -1 -> {} //nothing do
        }
    }

    return when {
        equalsX == -1 && moreThanX == -1 -> r
        equalsX == -1 && moreThanX != -1 -> l
        else -> equalsX
    }
}
private fun quickSortV1(arr: IntArray, l: Int, r: Int){
    //val a = "  ".repeat(count)
    //println("$a $count  l: $l, r: $r    | ${arr.joinToString(" ")}")

    if (l < r) {
        val predicate = (l + r) / 2 //(l until r).random()
        val p = partitionA(arr, l, r, predicate)
        //println("$a $count  l: $l, r: $r    | ${arr.joinToString(" ")}    | pI: $predicate, p: $p")
        quickSortV1(arr, l, p)
        quickSortV1(arr, p + 1, r)
    }
}
