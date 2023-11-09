package yandex.cup2023algorithm

import yandex.*

fun main(args: Array<String>) {
    //runTest("catsInArtLong", examplesB(), ::catsInArtLong1) //??
    //runTest("catsInArt", examplesB(), ::catsInArt1) //??
    //runTest("smartCatSteven", examplesA(), ::smartCatSteven) //delete
}
private fun examplesA(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("5\n" +
                "1 2 3 1 4",
            "1"),
        Pair("5\n" +
                "1 2 3 2 3",
            "3"),
        Pair("5\n" +
                "3 3 4 5 3",
            "0"),
        //Pair("12", "2"),
    )
}
private fun examplesB(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("5 4\n" +
                "1 1 10 1 1",
            "5"),
        Pair("6 3\n" +
                "3 10 5 3 5 5",
            "4"),
        Pair("6 1\n" +
                "4 8 10 3 1 1",
            "1"),
        Pair("6 1\n" +
                "1 1 10 3 1 1",
            "1"),

        Pair("6 4\n" +
                "1 1 10 3 1 1",
            "6"),
        Pair("6 3\n" +
                "1 1 10 3 1 1",
            "5"),
        Pair("6 2\n" +
                "1 1 10 3 1 1",
            "2"),
        Pair("8 5\n" +
                "3 2 1 1 10 3 1 1",
            "7"),
        Pair("1 1\n" +
                "1",
            "1"),
        //Pair("12", "2"),
    )
}

/*private fun runTest2(nameFun: String, examples: Array<Pair<String, String>>, function: () -> Unit){
    println(" $nameFun")
    repeat(examples.size) {
        writeExample(input = examples[it].first, correctAnswer = examples[it].second)

        function()
        val answer = readFile(OUTPUT_FILE)
        val correctAnswer = readFile(CORRECT_ANSWER_FILE)
        println(answer == correctAnswer)
        //println(readFile(OUTPUT))
    }
}
private fun writeExample(input: String, correctAnswer: String){
    writeFile(input, INPUT_FILE)
    writeFile(correctAnswer, CORRECT_ANSWER_FILE)
    writeFile("DEFAULT", OUTPUT_FILE)
}*/

