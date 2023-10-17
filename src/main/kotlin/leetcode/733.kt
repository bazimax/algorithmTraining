package leetcode

import java.util.*

fun main(args: Array<String>) {
    test733()
}

//733. Flood Fill - Easy - https://leetcode.com/problems/flood-fill/description/
private fun test733(){
    val image0: Array<IntArray> = arrayOf(
        arrayOf(1,1,1).toIntArray(),
        arrayOf(1,1,0).toIntArray(),
        arrayOf(1,0,1).toIntArray(),
    )
    val image: Array<IntArray> = arrayOf(
        arrayOf(0,0,0).toIntArray(),
        arrayOf(0,0,0).toIntArray(),
    )
    image.forEach { sr ->
        sr.forEach { sc ->
            print(" $sc")
        }
        println()
    }
    println("---")
    val a = floodFill733(image, 1, 0, 2)
    a.forEach { sr ->
        sr.forEach { sc ->
            print(" $sc")
        }
        println()
    }
}

//733 > 5:50 - 7:00 (~1ч10мин) > OK (fast 81%, memory 27%), большую часть решал без IDE, но ошибки уже в ней правил
private fun floodFill733(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
    val pixelStart = image[sr][sc]
    if (pixelStart == color) return image
    val listOfPixelsToCheck = LinkedList<Pair<Int, Int>>()
    listOfPixelsToCheck.add(Pair(sr, sc))

    while (listOfPixelsToCheck.isNotEmpty()) {

        val pixel = listOfPixelsToCheck.pollFirst()
        if (image[pixel.first][pixel.second] == pixelStart) {
            image[pixel.first][pixel.second] = color
            if (pixel.first - 1 >= 0) {
                listOfPixelsToCheck.add(Pair(pixel.first - 1, pixel.second))
            }
            if (pixel.second - 1 >= 0) {
                listOfPixelsToCheck.add(Pair(pixel.first, pixel.second - 1))
            }
            if (pixel.first + 1 <= image.count() - 1) {
                listOfPixelsToCheck.add(Pair(pixel.first + 1, pixel.second))
            }
            if (pixel.second + 1 <= image[0].count() - 1) {
                listOfPixelsToCheck.add(Pair(pixel.first, pixel.second + 1))
            }
        }
    }
    return image
}

class FloodFill (val image: String, val sr: Int, val sc: Int, val color: Int, val output: String)