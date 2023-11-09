package yandex.cup2023algorithm

import yandex.writeFile
import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    catsInArt1()
}

fun catsInArt1() {
    val input = BufferedReader(FileReader("input.txt"))

    val (n, m) = input.readLine().split(" ").map { it.toInt() }
    val testTubes = input.readLine().split(" ").map { it.toInt() }.toIntArray()

    //println("n: $n, m:$m")

    val arr: Array<IntArray> = Array(n){ intArrayOf(0,0)}

    testTubes.forEachIndexed { index, i ->
        arr[index] = arrayOf(i, index).toIntArray()
    }

    val sortedArr = arr.sortedBy { it[0] }
    //sortedArr.forEach { println("${it[0]}-${it[1]}") }

    var minPos = Int.MAX_VALUE//sortedArr[0][0]//map[0]
    var maxPos = 0

    repeat(m) {
        val testTube = sortedArr[it]
        minPos = min(minPos, testTube[1])
        maxPos = max(maxPos, testTube[1])
        //println("min: $minPos, max:$maxPos, ${testTube[0]}-${testTube[1]}")
    }

    val minDistance = maxPos - minPos + 1

    //println(minDistance)

    writeFile("$minDistance", "output.txt")

}
fun catsInArtLong1() {
    val input = BufferedReader(FileReader("input.txt"))

    val (n, m) = input.readLine().split(" ").map { it.toInt() }
    val testTubes = input.readLine().split(" ").map { it.toLong() }.toLongArray()

    //println("n: $n, m:$m")

    val arr: Array<LongArray> = Array(n){ longArrayOf(0,0) }

    testTubes.forEachIndexed { index, i ->
        arr[index] = arrayOf(i, index.toLong()).toLongArray()
    }

    val sortedArr = arr.sortedBy { it[0] }
    //sortedArr.forEach { println("${it[0]}-${it[1]}") }

    var minPos = Long.MAX_VALUE//sortedArr[0][0]//map[0]
    var maxPos = 0L

    repeat(m) {
        val testTube = sortedArr[it]
        minPos = min(minPos, testTube[1])
        maxPos = max(maxPos, testTube[1])
        //println("min: $minPos, max:$maxPos, ${testTube[0]}-${testTube[1]}")
    }

    val minDistance = maxPos - minPos + 1

    //println(minDistance)

    writeFile("$minDistance", "output.txt")

}

/*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

const val INPUT = "input.txt"
const val OUTPUT = "output.txt"

fun readFile(pathToInputFile: String): MutableList<String>{

    val file = BufferedReader(FileReader(pathToInputFile))

    val listString = mutableListOf<String>()
    file.lines().forEach { listString.add(it) }

    return listString
}

fun writeFile(string: String, pathToOutputFile: String){

    val output = BufferedWriter(FileWriter(pathToOutputFile))

    output.write(string)
    output.flush()
}*/