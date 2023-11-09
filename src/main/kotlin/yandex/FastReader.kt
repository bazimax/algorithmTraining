package yandex

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

internal class FastReader {

    var br: BufferedReader = BufferedReader(
        InputStreamReader(System.`in`)
    )
    var st: StringTokenizer? = null

    operator fun next(): String {
        while (st == null || !st!!.hasMoreElements()) {
            try {
                st = StringTokenizer(br.readLine())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return st!!.nextToken()
    }

    fun nextInt(): Int {
        return next().toInt()
    }

    fun nextLong(): Long {
        return next().toLong()
    }

    fun nextDouble(): Double {
        return next().toDouble()
    }

    fun nextLine(): String {
        var str = ""
        try {
            str = if (st!!.hasMoreTokens()) {
                st!!.nextToken("\n")
            } else {
                br.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return str
    }
}


fun main(args: Array<String>) {
    val s = FastReader()
    var n = s.nextInt()
    val k = s.nextInt()
    var count = 0
    while (n-- > 0) {
        val x = s.nextInt()
        if (x % k == 0) count++
    }
    println(count)
}

/*
//backup
internal class FastReader {

    var br: BufferedReader
    var st: StringTokenizer? = null

    init {
        br = BufferedReader(
            InputStreamReader(System.`in`)
        )
    }

    operator fun next(): String {
        while (st == null || !st!!.hasMoreElements()) {
            try {
                st = StringTokenizer(br.readLine())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return st!!.nextToken()
    }

    fun nextInt(): Int {
        return next().toInt()
    }

    fun nextLong(): Long {
        return next().toLong()
    }

    fun nextDouble(): Double {
        return next().toDouble()
    }

    fun nextLine(): String {
        var str = ""
        try {
            str = if (st!!.hasMoreTokens()) {
                st!!.nextToken("\n")
            } else {
                br.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return str
    }
}*/
