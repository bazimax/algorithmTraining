package yandex.algorithms4training.class1

/**
 * E. Поразрядная сортировка
 */

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    bitSortingInput()
}

//18:45 - 20:20 (1ч35мин) > OK (380ms, 35.41Mb)
fun bitSortingInput(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().split(" ").map { it.toInt() }[0] //1 ≤ n ≤ 1000

    val arr: Array<String> = Array(n){""} //= input.readLine().split(" ").map { it.toInt() }.toIntArray()
    repeat(n) { item ->
        arr[item] = input.readLine().toString()
    }

    val ans = bitSorting(arr)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

private fun bitSorting(arr: Array<String>): String{
    var answer = "Initial array:\n${arr.joinToString(", ")}\n" +
            "**********\n"

    val arrMap = Array<MutableList<String>>(10) { mutableListOf()}

    //Первая сортировка
    answer += "Phase 1\n"

    val strLength = arr[0].length

    arr.forEach { str ->
        arrMap[str[strLength - 1].digitToInt()].add(str)
    }

    var tempAnswer = ""
    arrMap.forEachIndexed { index, listStr ->
        tempAnswer += if (listStr.isEmpty()) "Bucket $index: empty\n" else "Bucket $index: ${listStr.joinToString(", ")}\n"
    }
    answer += "$tempAnswer**********\n"

    //Последующие сортировки
    var newArrMap = arrMap//: Array<MutableList<String>> = Array(10) { mutableListOf()}

    repeat(strLength - 1) {
        answer += "Phase ${it + 2}\n"

        //sort
        newArrMap = sortMap(newArrMap, strLength - 2 - it)

        tempAnswer = ""
        newArrMap.forEachIndexed { index, listStr ->
            tempAnswer += if (listStr.isEmpty()) "Bucket $index: empty\n" else "Bucket $index: ${listStr.joinToString(", ")}\n"
        }
        answer += "$tempAnswer**********\n"
    }

    val sortList = mutableListOf<String>()
    newArrMap.forEach { list ->
        list.forEach{ str ->
            sortList.add(str)
        }
    }
    answer += "Sorted array:\n" + sortList.joinToString(", ")

    return answer
}

private fun sortMap(arrMap: Array<MutableList<String>>, step: Int): Array<MutableList<String>>{
    val sortedArrayMap = Array<MutableList<String>>(10) { mutableListOf()}

    arrMap.forEach { list ->
        list.forEach { str ->
            sortedArrayMap[str[step].digitToInt()].add(str)
        }
    }

    return sortedArrayMap
}
