package leetcode

fun main(args: Array<String>) {
}

//383. Ransom Note - Easy - https://leetcode.com/problems/ransom-note/description/

//383 > 10:25 - 10:44 (~19мин) > OK (fast 78%, memory 33%)
private fun canConstruct383(ransomNote: String, magazine: String): Boolean {
    val mag = hashMapOf<Char, Int>()

    magazine.forEach{
        if (mag[it] == null) mag[it] = 1
        else mag[it] = mag[it]!! + 1
    }

    ransomNote.forEach{
        if (mag[it] == null || mag[it]!! < 1) return false
        else mag[it] = mag[it]!! - 1
    }
    return true
}