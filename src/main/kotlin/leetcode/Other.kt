package leetcode

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    println((-2).abs1())



}

//Кастомная функция "модуля" > println((-2).abs1())
fun abs(int: Int) : Int = if (int < 0) -int else int

//Extension version (в виде Экстеншена\Расширения)
//@JvmName("abs1")
fun Int.abs1(): Int = if (this < 0) -this else this


fun stringToArray(stringArray: String): Array<IntArray>{
    val str = stringArray.replace("],[", ".").drop(2).dropLast(2).split(".")
    var arr: Array<IntArray> = Array(str.size){ IntArray(2){0} }

    if (stringArray != "[]") {
        str.forEachIndexed { index, it ->
            val a = it.split(",")
            arr[index] = intArrayOf(a[0].toInt(),a[1].toInt())
        }
    } else arr = emptyArray()

    //arr.forEach { println("[${it.joinToString(",")}]") }

    /*var arrToString = ""

    arr.forEach {
        arrToString = if (arrToString == "") "[${it.joinToString(",")}]"
        else "$arrToString,[${it.joinToString(",")}]"
    }*/
    return arr
}

fun arrayToString(array: Array<IntArray>): String{
    var arrToString = ""

    array.forEach {
        arrToString = if (arrToString == "") "[${it.joinToString(",")}]"
        else "$arrToString,[${it.joinToString(",")}]"
    }
    return arrToString
}

fun printMatrix(matrix: Array<IntArray>, up: Boolean? = false) {
    if (up != null && up == true) println()
    matrix.forEach {
        var string = ""
        it.forEach { item ->
            string = if (string == "") "$item"
            else "$string $item"
        }
        println(string)
    }
    if (up != null && up == false) println()

}

fun printArray(array: Array<IntArray>){
    var answer = ""
    array.forEach {
        answer = if (answer == "") "[${it.joinToString(",")}]"
        else "$answer,[${it.joinToString(",")}]"
    }
    println(" [$answer]")
}

fun printListArray(list: MutableList<IntArray>) {
    var answer = ""
    list.forEach {
        //println(it.joinToString(","))
        answer = if (answer == "") "[${it.joinToString(",")}]"
        else "$answer,[${it.joinToString(",")}]"
    }
    println(" ($answer)")
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

//@JvmName("listNodeToList")
fun ListNode?.listNodeToList1() : List<Int> {
    //extension
    val list = mutableListOf<Int>()
    var next = true
    var current = this

    while (next) {
        if (current != null) {
            list.add(current.`val`)
        }
        if (current != null) {
            if (current.next != null) current = current.next!!
            else next = false
        }
    }
    return list
}
fun listNodeToList(listNode: ListNode?): List<Int> {

    val list = mutableListOf<Int>()
    var next = true
    var current = listNode

    while (next) {
        if (current != null) {
            list.add(current.`val`)
        }
        if (current != null) {
            if (current.next != null) current = current.next!!
            else next = false
        }
    }
    return list
}
fun listNodeOfInt(vararg ints: Int) : ListNode? {

    if (ints.isNotEmpty()) {
        val startListNode = ListNode(ints.first())
        var currentListNode = startListNode
        var count = 1
        while (count < ints.size) {
            val nextListNode = ListNode(ints[count])
            currentListNode.next = nextListNode
            currentListNode = nextListNode
            count++
        }
        return startListNode
    }

    return null
}


//IN WORK
//function templates

//массив тестовых значений
private val examplesV1 = arrayOf(
    //Triple(listNodeOfInt(1,2,4), listNodeOfInt(1,3,4), listNodeOfInt(1,1,2,3,4,4)),
    //Pair(exampleTree(), listOf(2,3,1)),
    //Triple("anagram", "nagaram", true),
    //Pair("0P", false)
    Pair(intArrayOf(-2,1,-3,4,-1,2,1,-5,4), 6),
    Pair(intArrayOf(1), 1),
    Pair(intArrayOf(5,4,-1,7,8), 23),
    Pair(intArrayOf(0,3,-1), 3)
)
private val examplesV2 = arrayOf(
    //Triple(Pair(2,2), "[[1,1],[2,1],[1,2],[2,2]]", 2),
    Triple("[[1,3],[6,9]]", intArrayOf(2,5), "[[1,5],[6,9]]"),
    Triple("[[1,2],[3,5],[6,7],[8,10],[12,16]]", intArrayOf(4,8), "[[1,2],[3,10],[12,16]]"),
    Triple("[]", intArrayOf(0,0), "[]")
    //FloodFill("[[1,1,1],[1,1,0],[1,0,1]]", 1, 1, 2, "[[2,2,2],[2,2,0],[2,0,1]]"),

    //Pair(intArrayOf(0), listOf(listOf(0))),
    //Triple(intArrayOf(), ,)
)

//функция для проверки нескольких тестовых значений
private fun testingV1(ask: Array<Pair<IntArray, Int>>) {
    for (i in ask) {
        val time = measureTimeMillis { //measureTimeMillis //measureNanoTime
            val answer = exampleFun(i.first)//sortedSquares977(i.first)
            //println("${answer contentEquals i.second} :: in: ${i.first.joinToString()}, " +
            //        "correctAnswer: ${i.second.joinToString()}, ans: ${answer.joinToString()}") //contentEquals - Array
            println("${answer == i.second} :: in: [${i.first.joinToString(",")}], " + //target: ${i.second},
                    "correctAnswer: ${i.second}, ans: $answer") //contentEquals - Array
            //println("${answer == i.second} :: in: ${i.first}, correctAnswer: ${i.second}, ans: $answer")
            //println("${listNodeToList(answer) == listNodeToList(i.third)} :: list1: ${listNodeToList(i.first)}, list2: ${listNodeToList(i.second)}, " +
            //       "correctAnswer: ${listNodeToList(i.third)}, ans: ${listNodeToList(answer)}") //contentEquals - ListNode
            //println("${answer == i.third} :: s1: ${i.first}, s2: ${i.second}, correctAnswer: ${i.third}, ans: $answer")
        }
        //println("time: $time")
    }
}
private fun testingV2(ask: Array<Triple<IntArray, String, Int>>) {

    for (i in ask) {
        //println(i.second)
        val str = i.second.replace("],[", ".").drop(2).dropLast(2).split(".")
        val arr: Array<IntArray> = Array(str.size){ IntArray(2){0} }
        str.forEachIndexed() { index, it ->
            val a = it.split(",")
            arr[index] = intArrayOf(a[0].toInt(),a[1].toInt())
        }

        val time = measureTimeMillis {
            val answer = exampleFun(i.first)
            println("${answer == i.third} :: in: [${i.first}-${i.second}], " +
                    "correctAnswer: ${i.third}, ans: $answer") //contentEquals - Array
            //println("${answer == i.second} :: in: [${i.first.joinToString(",")}], target: ${i.second}, " +
            //        "correctAnswer: ${i.third}, ans: $answer") //contentEquals - Array
        }
        println("time: $time")
    }
}

private fun exampleFun(intArray: IntArray) : Int { return 0 }