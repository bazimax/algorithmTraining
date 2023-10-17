package leetcode

import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    testFun57(testAsk57)
}

//57. Insert Interval - Medium - https://leetcode.com/problems/insert-interval/description/

private val testAsk57 = arrayOf(
    Triple("[[1,3],[6,9]]", intArrayOf(2,5), "[[1,5],[6,9]]"),
    Triple("[[1,2],[3,5],[6,7],[8,10],[12,16]]", intArrayOf(4,8), "[[1,2],[3,10],[12,16]]"),
    Triple("[]", intArrayOf(0,0), "[[0,0]]"),
    Triple("[]", intArrayOf(1,2), "[[1,2]]"),
    Triple("[[1,5]]", intArrayOf(2,3), "[[1,5]]")
)
private fun testFun57(ask:  Array<Triple<String, IntArray, String>>) {
    for ((count, i) in ask.withIndex()) {

        val time = measureNanoTime {
            val inData = stringToArray57(i.first)
            val correctData = stringToArray57(i.third)

            val answer = insert57v2(inData.first, i.second)

            var ansString = ""
            answer.forEach {
                ansString = if (ansString == "") "[${it.joinToString(",")}]"
                else "$ansString,[${it.joinToString(",")}]"
            }

            println("${answer.contentDeepEquals(correctData.first)} :: arr: [${inData.second}], newArr: [${i.second[0]},${i.second[1]}], " +
                    "correctAnswer: [${correctData.second}], ans: [$ansString]") //contentEquals - Array
        }
        println("time: $time")
    }
}

private fun stringToArray57(stringArray: String): Pair<Array<IntArray>, String>{
    val str = stringArray.replace("],[", ".").drop(2).dropLast(2).split(".")
    var arr: Array<IntArray> = Array(str.size){ IntArray(2){0} }

    if (stringArray != "[]") {
        str.forEachIndexed { index, it ->
            val a = it.split(",")
            arr[index] = intArrayOf(a[0].toInt(),a[1].toInt())
        }
    } else arr = emptyArray()

    //arr.forEach { println("[${it.joinToString(",")}]") }

    var arrToString = ""

    arr.forEach {
        arrToString = if (arrToString == "") "[${it.joinToString(",")}]"
        else "$arrToString,[${it.joinToString(",")}]"
    }
    return arr to arrToString
}
//57 > 12:54 - 13:39 (~45мин) + ~2часа на неудачную версию (57backup) > OK (fast 51%, memory 8%)
private fun insert57(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {

    if (intervals.isEmpty() && newInterval.contentEquals(intArrayOf(0, 0))) return intervals
    val array: Array<IntArray> = Array(intervals.size + 1){IntArray(2)}

    var nI = newInterval[0]

    var count = 0
    for (i in array.indices) {
        if (intervals.isNotEmpty() && count <= intervals.size - 1 && intervals[count][0] <= nI) {
            array[i] = intervals[count]
            count++
        }
        else {
            array[i] = newInterval
            nI = Int.MAX_VALUE
        }
    }

    val list = mutableListOf<IntArray>()
    list.add(array[0])

    for (i in 1 until array.size) {
        if (array[i][0] in list.last()[0]..list.last()[1] && array[i][1] >= list.last()[1]) {
            list[list.lastIndex] = intArrayOf(list.last()[0], array[i][1])
        }
        else if (array[i][0] > list.last()[1]) {
            list.add(array[i])
        }
    }

    val ans: Array<IntArray> = Array(list.size){IntArray(2)}
    list.forEachIndexed { index, it ->
        ans[index] = it
    }
    return ans
}

//57v2 > 13:40 - 14:07 (~27мин) > OK (fast 73%, memory 5-16%)
private fun insert57v2(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {

    if (intervals.isEmpty()) return arrayOf(newInterval)

    val list = mutableListOf<IntArray>()
    var count = 0

    for (i in 0..intervals.size) {
        var tempInterval: IntArray
        if (count <= intervals.size - 1 && intervals[count][0] <= newInterval[0]) {
            tempInterval = intervals[count]
            count++
        }
        else {
            tempInterval = intArrayOf(newInterval[0],newInterval[1])
            newInterval[0] = Int.MAX_VALUE
        }

        if (list.isEmpty()) list.add(tempInterval)
        else {
            if (tempInterval[0] in list.last()[0]..list.last()[1] && tempInterval[1] >= list.last()[1]) {
                list[list.lastIndex] = intArrayOf(list.last()[0], tempInterval[1])
            }
            else if (tempInterval[0] > list.last()[1]) {
                list.add(tempInterval)
            }
        }
    }

    return list.toTypedArray()
}

//57backup - 10:44 - ~20мин на написание тестов - 12:54 (~2часа) не работает как надо
private fun insert57backup(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    println()
    print("newInterval: [${newInterval[0]},${newInterval[1]}] -")
    printArray57(intervals)

    if (intervals.isEmpty()) {
        return if (newInterval[0] == 0 && newInterval[1] == 0) intervals
        else arrayOf(newInterval)
    }
    if (newInterval[0] == 0 && newInterval[1] == 0) return intervals


    //val max = newInterval[1]
    val list = mutableListOf<IntArray>()

    if (intervals[0][0] < newInterval[0]) list.add(intervals[0]) else list.add(newInterval)

    /*for (i in intervals.withIndex()) {
        println("i: $i, intervals: ${i.value.joinToString(",")}")
    }*/
    for (i in intervals.indices) {
        printListArray57(list)
        println("i: $i, intervals: ${intervals[i].joinToString(",")}, list: ${list.last().joinToString(",")}")
        println()

        if (intervals[i][0] in list.last()[0]..list.last()[1] && intervals[i][1] >= list.last()[1]) {
            println("--- 1")
            println("intervals: ${intervals[i].joinToString(",")}, list: ${list.last().joinToString(",")}")
            list.add(arrayOf(list.last()[0], intervals[i][1]).toIntArray())
            list.removeAt(list.lastIndex - 1)
        }
        if (newInterval[0] in list.last()[0]..list.last()[1] && newInterval[1] >= list.last()[1]) {
            println("--- 2")
            println("intervals: ${intervals[i].joinToString(",")}, list: ${list.last().joinToString(",")}")
            list.add(arrayOf(list.last()[0], newInterval[1]).toIntArray())
            list.removeAt(list.lastIndex - 1)
        }
        if (intervals[i][0] > list.last()[1]) {
            println("--- 3")
            println("intervals: ${intervals[i].joinToString(",")}, list: ${list.last().joinToString(",")}")
            list.add(intervals[i])
        }
        if (newInterval[0] > list.last()[1]) {
            println("--- 4")
            println("intervals: ${intervals[i].joinToString(",")}, list: ${list.last().joinToString(",")}")
            list.add(newInterval)
        }
        //else println("Error")

        //if (intervals[i][0] >= list.last()[0] && intervals[i][1] <= list.last()[1]) //do nothing
    }

    println("----")
    list.forEach { println(it.joinToString(",")) }

    val ans: Array<IntArray> = Array(list.size){IntArray(2)}//arrayOf(IntArray(2))
    list.forEachIndexed { index, it ->
        ans[index] = it
    }

    return ans
}

private fun printArray57(array: Array<IntArray>){
    var answer = ""
    array.forEach {
        answer = if (answer == "") "[${it.joinToString(",")}]"
        else "$answer,[${it.joinToString(",")}]"
    }
    println(" [$answer]")
}
private fun printListArray57(list: MutableList<IntArray>) {
    var answer = ""
    list.forEach {
        //println(it.joinToString(","))
        answer = if (answer == "") "[${it.joinToString(",")}]"
        else "$answer,[${it.joinToString(",")}]"
    }
    println(" ($answer)")
}