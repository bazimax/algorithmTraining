package leetcode

import kotlin.math.min

fun main(args: Array<String>) {
    updateMatrix542(arrayOf(intArrayOf(0,0,0), intArrayOf(0,1,0), intArrayOf(1,1,1)))
    updateMatrix542(arrayOf(intArrayOf(0,0,0), intArrayOf(0,0,0), intArrayOf(0,1,0)))
    updateMatrix542(arrayOf(intArrayOf(1,1,1,1,1), intArrayOf(1,1,1,0,1), intArrayOf(1,1,1,1,1)))
}

//542. 01 Matrix - Medium - https://leetcode.com/problems/01-matrix/description/
//542 > 16:33 - 18:44 (~2ч11мин) > OK (fast 60%, memory 54%)
private fun updateMatrix542(mat: Array<IntArray>): Array<IntArray> {
    val newMatrix: Array<IntArray> = Array(mat.size){IntArray(mat[0].size){Int.MAX_VALUE}}

    for (i in mat.indices) {
        for (j in mat[i].indices) {
            if (mat[i][j] == 0) newMatrix[i][j] = 0
            else {
                val step = walk(mat, newMatrix, intArrayOf(i, j)) ?: Int.MAX_VALUE
                newMatrix[i][j] = if (step == Int.MAX_VALUE) step else step + 1
            }
            //printMatrix(mat)
            //printMatrix(newMatrix)
            //println("----")
        }
    }

    for (i in mat.indices.reversed()) {
        for (j in mat[i].indices.reversed()) {
            if (mat[i][j] == 0) newMatrix[i][j] = 0
            else {
                val step = walk(mat, newMatrix, intArrayOf(i, j)) ?: Int.MAX_VALUE
                //println("step: $step")
                newMatrix[i][j] = if (step == Int.MAX_VALUE) step else step + 1
            }
            //printMatrix(newMatrix)
        }
    }
    //printMatrix(newMatrix) //test
    return newMatrix
}

private fun walk (mat: Array<IntArray>, newMatrix: Array<IntArray>, pixel: IntArray): Int? {
    val arrayValue = IntArray(4){Int.MAX_VALUE}
    if (pixel[1] + 1 <= mat[0].size - 1) {
        //go right
        if (mat[pixel[0]][pixel[1] + 1] == 0) arrayValue[0] = 1
        arrayValue[0] = min(arrayValue[0], newMatrix[pixel[0]][pixel[1] + 1])
    }
    if (pixel[0] + 1 <= mat.size - 1) {
        //go bottom
        if (mat[pixel[0] + 1][pixel[1]] == 0) arrayValue[1] = 1
        arrayValue[1] = min(arrayValue[1], newMatrix[pixel[0] + 1][pixel[1]])
    }
    if (pixel[1] - 1 >= 0) {
        //go left
        if (mat[pixel[0]][pixel[1] - 1] == 0) arrayValue[2] = 1
        arrayValue[2] = min(arrayValue[2], newMatrix[pixel[0]][pixel[1] - 1])
    }
    if (pixel[0] - 1 >= 0) {
        //go up
        if (mat[pixel[0] - 1][pixel[1]] == 0) arrayValue[3] = 1
        arrayValue[3] = min(arrayValue[3], newMatrix[pixel[0] - 1][pixel[1]])
    }
    return arrayValue.minOrNull()
}