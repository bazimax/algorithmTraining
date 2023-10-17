package leetcode

fun main(args: Array<String>) {
}

//409. Longest Palindrome - Easy - https://leetcode.com/problems/longest-palindrome/description/

//409 > 15:40 - 15:54 (~14мин) > OK (fast 20%, memory 26%)
private fun longestPalindrome409(s: String): Int {
    s.length

    val list = java.util.HashMap<Char, Int>()
    var plusOne = 0
    var length = 0
    for (c in s) {
        if (list[c] != null) list[c] = list[c]!! + 1 else list[c] = 1
    }

    for (i in list) {
        if (i.value % 2 == 1) plusOne = 1
        if (i.value > 1) length += (i.value / 2) * 2
    }
    length += plusOne
    return length
}

//409 > OK (fast 56%, memory 92%)
private fun longestPalindrome409v2(s: String): Int {
    val array = IntArray(128)
    var plusOne = 0
    var length = 0
    for (i in s) {
        array[i.toInt()]++
    }
    for (c in array) {
        if (c > 1) length += (c / 2) * 2
    }
    return if (s.length > length) length + 1 else length
}