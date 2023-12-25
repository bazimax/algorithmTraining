package yandex.algorithms4training.warmup

import yandex.*

fun main(args: Array<String>) {
    // runTest("notAMinimumOnTheSegment", examplesA(), ::notAMinimumOnTheSegmentV2) //OK
    // runTest("addTwoFractions", examplesB(), ::addTwoFractionsV3, ) //OK
    // runTest("travelInMoscow", examplesC(), ::travelInMoscow) //OK
    // runTest("anagram", examplesD(), ::anagramV2) // OK
    runTest("mediumLevel", examplesE(), ::mediumLevelV3) //OK ?
    //runTest("elevator", examplesF(), ::elevatorV3, ) //OK
    // runTest("bunnyIsLearningGeometry", examplesG(), ::bunnyIsLearningGeometryV4, ) //OK
    // runTest("contestResults", examplesH(), ::contestResultsV2) //OK
    // runTest("theCorrectParentheticalSequence", examplesI(), ::theCorrectParentheticalSequence) // OK
    // runTest("groupProject", examplesJ(), ::groupProjectV3) //OK
}

private fun examplesA(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("10 5\n" +
                "1 1 1 2 2 2 3 3 3 10\n" +
                "0 1\n" +
                "0 3\n" +
                "3 4\n" +
                "7 9\n" +
                "3 7",
            "NOT FOUND\n" +
                    "2\n" +
                    "NOT FOUND\n" +
                    "10\n" +
                    "3"),
        Pair("4 2\n" +
                "1 1 1 2\n" +
                "0 2\n" +
                "0 3",
            "NOT FOUND\n" +
                    "2"),
        Pair("10 5\n" +
                "1 1 1 2 2 5 3 4 3 10\n" +
                "0 1\n" +
                "0 3\n" +
                "3 4\n" +
                "7 9\n" +
                "3 7",
            "NOT FOUND\n" +
                    "2\n" +
                    "NOT FOUND\n" +
                    "10\n" +
                    "4"),
        Pair("3 2\n" +
                "3 1 1\n" +
                "0 0\n" +
                "0 1",
            "NOT FOUND\n" +
                    "3"),
        Pair("1 1\n" +
                "1\n" +
                "0 0",
            "NOT FOUND"),
        Pair("5 1\n" +
                "1 1 3 1 1\n" +
                "0 4",
            "3"),
        Pair("5 1\n" +
                "3 2 1 2 3\n" +
                "0 4",
            "3"),
        //Pair("12", "2"),
    )
}
private fun examplesB(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("1 2 1 2", "1 1"), //0
        Pair("1 4 1 2", "3 4"),
        Pair("1 3 1 2", "5 6"),
        Pair("1 2 1 4", "3 4"),
        Pair("1 2 1 3", "5 6"),
        Pair("1 1 1 1", "2 1"), //5
        Pair("2 1 3 1", "5 1"),
        Pair("3 2 1 2", "2 1"),
        Pair("1 4 1 6", "5 12"),
        Pair("3 12 6 12", "3 4"),
        Pair("90 66 85 21", "1250 231"), //10
        //Pair("12", "2"),
    )
}
private fun examplesC(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("0 5 4 3",
            "4.636476090008"),
        Pair("0 5 4 -3",
            "10.000000000000"),
        Pair("-3 -3 3 3",
            "8.485281374239"),
        Pair("-3 -3 0 0",
            "4.242640687119"),
        Pair("0 5 4 -3",
            "10.000000000000"),
        //Pair("12", "2"),
    )
}
private fun examplesD(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("dusty\n" +
                "study",
            "YES"),
        Pair("rat\n" +
                "bat",
            "NO"),
        Pair("du sty\n" +
                "study",
            "YES"),
        //Pair("12", "2"),
    )
}
private fun examplesE(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("3\n" +
                "1 3 4",
            "5 3 4 "),
        Pair("5\n" +
                "3 7 8 10 15",
            "28 16 15 17 32 "),
        //Pair("12", "2"),
    )
}

private fun examplesF(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("2\n" +
                "3\n" +
                "3\n" +
                "0\n" +
                "1",
            "8"),
        Pair("1000000000\n" +
                "3\n" +
                "3\n" +
                "0\n" +
                "1",
            "6"),
        Pair("1\n" +
                "3\n" +
                "1000000000\n" +
                "1000000000\n" +
                "0",
            "6000000000"),
        Pair("2\n" +
                "3\n" +
                "0\n" +
                "0\n" +
                "0",
            "0"),
        Pair("3\n" +
                "100\n" +
                "0\n" +
                "2\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "2\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "1\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "1\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "2\n" +
                "2\n" +
                "1\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "2\n" +
                "1\n" +
                "2\n" +
                "0\n" +
                "1\n" +
                "0\n",
            "2286"),
        Pair("1\n" +
                "3\n" +
                "1000\n" +
                "1000\n" +
                "0",
            "6000"),
        //Pair("12", "2"),
    )
}
private fun examplesG(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("4 5\n" +
                "0 0 0 1 0\n" +
                "0 1 1 1 0\n" +
                "0 0 1 1 0\n" +
                "1 0 1 0 0",
            "2"),
        Pair("10 10\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1\n" +
                "1 1 1 1 1 1 1 1 1 1",
            "10"),
        Pair("10 10\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0",
            "0"),
        Pair("10 10\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 1 1 1 1 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0",
            "1"),
        Pair("1 1\n" +
                "1", "1"),
        Pair("1 1\n" +
                "0", "0"),
        Pair("10 10\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 1 0 1 0 0 0\n" +
                "0 0 0 0 1 1 1 0 0 0\n" +
                "0 0 0 1 1 1 1 1 0 0\n" +
                "0 0 0 0 0 0 1 1 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0",
            "2"),
        Pair("10 10\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 1 0 0 0\n" +
                "0 0 0 0 1 0 1 0 0 0\n" +
                "0 0 0 0 1 1 1 0 0 0\n" +
                "0 0 0 1 1 1 1 1 0 0\n" +
                "0 0 0 0 0 0 1 1 1 1\n" +
                "0 0 0 0 0 0 1 1 1 1\n" +
                "0 0 0 0 0 0 1 1 1 1\n" +
                "0 0 0 0 0 0 1 1 1 1",
            "4"),
        //Pair("12", "2"),
    )
}
private fun examplesH(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("60\n" +
                "30\n" +
                "4",
            "Yes"),
        Pair("30\n" +
                "30\n" +
                "1",
            "No"),
        Pair("30\n" +
                "150\n" +
                "4",
            "No"),
        Pair("8\n" +
                "5\n" +
                "4",
            "Yes"),
        //Pair("12", "2"),
    )
}
private fun examplesI(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("()[]", "yes"),
        Pair("([)]", "no"),
        Pair("(", "no"),
        Pair("([]){}", "yes"),
        Pair("()[]{}()[]{}()[]{}()[]{}", "yes"),
        //Pair("12", "2"),
    )
}
private fun examplesJ(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("4\n" +
                "10 2 3\n" +
                "11 7 8\n" +
                "28 4 6\n" +
                "3 1 2",
            "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES"),
        Pair("1\n" +
                "2 1 3",
            "YES"),
        Pair("1\n" +
                "10 5 6",
            "YES"),
        Pair("1\n" +
                "10 3 4",
            "YES"),
        Pair("1\n" +
                "5 3 4",
            "NO"),
        Pair("1\n" +
                "1 1 1",
            "YES"),
        Pair("1\n" +
                "20 5 6",
            "YES"),
        Pair("100\n" +
                "82 9 9\n" +
                "17 5 5\n" +
                "8 8 8\n" +
                "77 1 8\n" +
                "100 50 93\n" +
                "78 78 78\n" +
                "41 1 31\n" +
                "87 9 67\n" +
                "80 4 33\n" +
                "59 1 1\n" +
                "45 45 45\n" +
                "54 18 49\n" +
                "39 9 28\n" +
                "22 7 19\n" +
                "74 18 32\n" +
                "23 1 1\n" +
                "7 1 5\n" +
                "55 9 9\n" +
                "84 1 21\n" +
                "77 15 15\n" +
                "64 32 32\n" +
                "19 19 19\n" +
                "65 65 65\n" +
                "1 1 1\n" +
                "100 11 98\n" +
                "79 11 38\n" +
                "69 1 1\n" +
                "42 8 25\n" +
                "60 1 1\n" +
                "84 1 1\n" +
                "43 1 37\n" +
                "45 11 11\n" +
                "49 12 12\n" +
                "49 9 12\n" +
                "91 45 45\n" +
                "83 27 27\n" +
                "40 6 6\n" +
                "50 1 1\n" +
                "26 26 26\n" +
                "50 7 50\n" +
                "3 3 3\n" +
                "41 1 9\n" +
                "49 8 8\n" +
                "63 15 15\n" +
                "34 6 23\n" +
                "91 1 53\n" +
                "10 1 1\n" +
                "31 3 3\n" +
                "86 14 79\n" +
                "59 5 5\n" +
                "96 2 3\n" +
                "14 14 14\n" +
                "5 2 2\n" +
                "40 1 1\n" +
                "51 1 1\n" +
                "22 7 20\n" +
                "3 1 1\n" +
                "14 1 1\n" +
                "19 6 11\n" +
                "82 10 24\n" +
                "71 35 59\n" +
                "69 6 6\n" +
                "9 9 9\n" +
                "92 1 68\n" +
                "76 15 15\n" +
                "66 3 6\n" +
                "37 4 34\n" +
                "62 1 1\n" +
                "72 6 6\n" +
                "71 2 53\n" +
                "42 14 32\n" +
                "42 4 31\n" +
                "37 9 9\n" +
                "100 50 89\n" +
                "95 47 47\n" +
                "34 11 32\n" +
                "39 19 19\n" +
                "69 1 52\n" +
                "68 34 34\n" +
                "17 8 8\n" +
                "44 44 44\n" +
                "25 1 1\n" +
                "40 2 9\n" +
                "55 18 18\n" +
                "48 48 48\n" +
                "50 8 16\n" +
                "1 1 1\n" +
                "41 13 16\n" +
                "33 1 1\n" +
                "30 15 24\n" +
                "27 9 26\n" +
                "11 1 1\n" +
                "44 11 34\n" +
                "55 1 3\n" +
                "89 11 11\n" +
                "68 17 17\n" +
                "54 54 54\n" +
                "64 16 52\n" +
                "18 9 18\n" +
                "34 8 9",
            "NO\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "NO\n" +
                    "YES\n" +
                    "NO\n" +
                    "NO\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "NO\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES\n" +
                    "YES"),
        //Pair("12", "2"),
    )
}

private fun examplesX(): Array<Pair<String, String>> {
    return arrayOf(
        //Pair("12", "2"),
    )
}

