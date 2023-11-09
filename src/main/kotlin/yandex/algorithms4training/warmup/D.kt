package yandex.algorithms4training.warmup

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

/**
 * D. Анаграмма?
 *
 * Задано две строки, нужно проверить, является ли одна анаграммой другой. Анаграммой называется строка, полученная
 * из другой перестановкой букв.
 */

fun main(args: Array<String>) {
    anagram()
}

//17:37 - 17:55 (18мин) > OK (421ms, 21.60Mb)
fun anagram(){
    //O(n)
    val input = BufferedReader(FileReader("input.txt"))
    val str1 = input.readLine().replace("\\s".toRegex(), "")
    val str2 = input.readLine().replace("\\s".toRegex(), "")

    var ans = "YES"

    val dictionary = HashMap<Char, Int>()

    str1.forEach {
        if (dictionary.contains(it)) dictionary[it] = dictionary[it]!! + 1 else dictionary[it] = 1
    }

    str2.forEach {
        if (dictionary.contains(it) && dictionary[it]!! > 0) dictionary[it] = dictionary[it]!! - 1
        else {
            ans = "NO"
        }
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

fun anagramV2(){
    //более лаконичная запись, но меньшая скорость O(N*logN)
    val input = BufferedReader(FileReader("input.txt"))
    val str1 = input.readLine().replace("\\s".toRegex(), "")
    val str2 = input.readLine().replace("\\s".toRegex(), "")

    val str1Sorted = str1.map { it }.sorted()
    val str2Sorted = str2.map { it }.sorted()

    val ans = if (str1Sorted == str2Sorted) "YES" else "NO"

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}

//backup
/*fun reverese(){
    val input = BufferedReader(FileReader("input.txt"))
    val str1 = input.readLine().replace("\\s".toRegex(), "")
    val str2 = input.readLine().replace("\\s".toRegex(), "")

    var ans = "YES"

    if (str1.length != str2.length) ans = "NO"
    else {
        var count = str2.length - 1

        str1.forEach {
            if (it != str2[count]) ans = "NO"
            count--
        }
    }

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(ans)
    output.flush()
}*/
