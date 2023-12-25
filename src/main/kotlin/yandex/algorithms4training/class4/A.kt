package yandex.algorithms4training.class4

/**
 * A. Все перестановки заданной длины //All permutations of a given length
 *
 * По данному числу N (0 < N < 10) выведите все перестановки чисел от 1 до N в лексикографическом порядке.
 */

import yandex.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    //allPermutationsOfAGivenLengthV2()
    //runTest("allPermutationsOfAGivenLength", examplesA, ::allPermutationsOfAGivenLengthV6, )
    //testAlgorithm()
}

private val examplesA = arrayOf(
    Pair("1",
        "1"), //1
    Pair("2",
        "12\n" +
            "21"), //2
    Pair("3",
        "123\n" +
            "132\n" +
            "213\n" +
            "231\n" +
            "312\n" +
            "321"), //3
    //Pair("9", "1"),
    //Pair("12", "2"),
)

// OK (0.827s, 58.41Mb)
private fun allPermutationsOfAGivenLengthV6(){
    //вариант через перестановку //https://prog-cpp.ru/permutation/
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] // 0 < N < 10

    val arr = MutableList(n){ it + 1 }.toIntArray()

    val ansList = mutableListOf<String>()
    ansList.add(arr.joinToString(""))

    while (nextSet(arr, n)) ansList.add(arr.joinToString(""))

    println(ansList.joinToString("\n"))

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ansList.joinToString("\n"))
    output.flush()
}
private fun nextSet(arr: IntArray, num: Int) : Boolean {
    var j = num - 2
    while (j != -1 && arr[j] >= arr[j + 1]) j--
    if (j == -1) return false

    var k = num - 1
    while (arr[j] >= arr[k]) k--
    swap(arr, j, k)

    var l = j + 1
    var r = num - 1
    while (l < r) swap(arr, l++, r--)
    return true
}
private fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}
/*private fun testAlgorithm(){
    val n = 3
    val arr = intArrayOf(3,2,1)//MutableList(n){ it + 1 }.toIntArray()
    println(arr.joinToString(""))

    if (testNextSet(arr, n)) println(arr.joinToString(""))

}
private fun testNextSet(arr: IntArray, num: Int) : Boolean {
    var j = num - 2
    while (j != -1 && arr[j] >= arr[j + 1]) {
        j--
    }
    println(" j:$j")
    if (j == -1) return false

    var k = num - 1
    while (arr[j] >= arr[k]) k--
    println(" k:$k")
    swap(arr, j, k)
    println(arr.joinToString(""))

    var l = j + 1
    var r = num - 1
    println(" l:$l, r:$r")
    while (l < r) {
        println(" w:: l:${l + 1}, r:${r - 1}")
        swap(arr, l++, r--)
    }
    return true
}*/

// notOK частичное решение (0.991s, 67.55Mb не прошел тест 9 ML) избавились от множества mutableList, но это не помогло
private fun allPermutationsOfAGivenLengthV5(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] // 0 < N < 10
    val listInt = MutableList(n){ it + 1 }

    val answer = overkillV5(listInt)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer.joinToString("\n"))
    output.flush()
}
private fun overkillV5(listInt: MutableList<Int>) : MutableList<String>{

    //если элемент 1, то его и возвращаем
    if (listInt.size == 1) return mutableListOf("${listInt[0]}")

    //иначе элементов больше 1
    val ansList = mutableListOf<String>()

    val size = listInt.size

    for (i in 0 until size) {

        val num = listInt[i]

        listInt.remove(num)
        if (listInt.size == 1) {
            ansList.add("$num${listInt[0]}")
        }
        else {
            overkillV5(listInt).forEach { overkillIt ->
                ansList.add("$num$overkillIt")
            }
        }
        listInt.add(num)
        listInt.sort()
    }

    return ansList
}

// notOK частичное решение (0.887s, 68.72Mb не прошел тест 9 ML)
private fun allPermutationsOfAGivenLengthV4(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] // 0 < N < 10
    val listInt = MutableList(n){ it + 1 }

    val answer = overkillV4(listInt)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer.joinToString("\n"))
    output.flush()
}
private fun overkillV4(listInt: MutableList<Int>) : MutableList<String>{

    //если элемент 1, то его и возвращаем
    if (listInt.size == 1) return mutableListOf("${listInt[0]}")

    //иначе элементов больше 1
    val ansList = mutableListOf<String>()

    listInt.forEach { num ->
        val nextListInt = listInt.toMutableList()
        nextListInt.remove(num)

        if (nextListInt.size == 1) ansList.add("$num${nextListInt[0]}")
        else {
            overkillV4(nextListInt).forEach { overkillIt ->
                ansList.add("$num$overkillIt")
            }
        }
    }
    return ansList
}

// супер-медленное решение
private fun allPermutationsOfAGivenLengthV3(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] // 0 < N < 10

    val listInt = MutableList(n){ it + 1 }
    val numMin = listInt.joinToString("").toInt()
    val numMax = listInt.reversed().joinToString("").toInt()


    val setBannedNum = HashSet<Char>()
    setBannedNum.add('0')
    for (i in n + 1 .. 9) {
        setBannedNum.add(i.toString()[0])
    }


    val ans = mutableListOf<Int>()

    loop@ for (i in numMin..numMax) {
        val strI = i.toString()
        val setNum = setBannedNum.toHashSet()// HashSet<Char>()

        for (j in 0 .. strI.lastIndex) {

            if (!setNum.contains(strI[j])) {
                setNum.add(strI[j])
            }
            else continue@loop
        }

        ans.add(i)
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans.joinToString("\n"))
    output.flush()
}

// notOK частичное решение (0.892s, 73.91Mb, не прошел тест 9 ML)
private fun allPermutationsOfAGivenLengthV2(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] // 0 < N < 10
    val listInt = MutableList(n){ it + 1 }

    val answer = overkillV2(listInt)

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer.joinToString("\n"))
    output.flush()
}
private fun overkillV2(listInt: MutableList<Int>) : MutableList<String>{

    //если элемент 1, то его и возвращаем
    if (listInt.size == 1) return mutableListOf("${listInt[0]}")

    //иначе элементов больше 1
    val ansList = mutableListOf<String>()

    listInt.forEach { num ->
        val nextListInt = listInt.toMutableList()
        nextListInt.remove(num)

        overkillV2(nextListInt).forEach { overkillIt ->
            ansList.add("$num$overkillIt")
        }
    }
    return ansList
}

// 13:10 - 14:40 (1ч30мин) > notOK частичное решение (0.889s, 69.23Mb, не прошел тест 9 ML)
private fun allPermutationsOfAGivenLengthV1(){
    val input = BufferedReader(FileReader("input.txt"))
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] // 0 < N < 10
    val listInt = MutableList(n){ it + 1 }

    val a = overkillV1(listInt)
    val answer = Array(a.size){ a[it].joinToString("") }


    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer.joinToString("\n"))
    output.flush()
}
private fun overkillV1(listInt: MutableList<Int>) : MutableList<List<Int>>{

    //если элемент 1, то его и возвращаем
    if (listInt.size == 1) return mutableListOf(listOf(listInt[0]))

    //иначе элементов больше 1
    val ansList = mutableListOf<List<Int>>()

    listInt.forEach { num ->
        val nextListInt = listInt.toMutableList()
        nextListInt.remove(num)

        overkillV1(nextListInt).forEach { overkillIt ->
            ansList.add(listOf(num) + overkillIt)
        }
    }
    return ansList
}
