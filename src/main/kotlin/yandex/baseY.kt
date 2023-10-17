package yandex

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

const val INPUT = "input.txt"
const val OUTPUT = "output.txt"
const val CORRECT_ANSWER = "files/correctAnswer.txt"

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
}