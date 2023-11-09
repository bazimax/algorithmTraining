package yandex.cup2023android

//import yandex.runTest
//import yandex.*

fun main(args: Array<String>) {
    //runTest("flowersOfLife", examplesP(), ::flowersOfLifeV5)
    //runTest2("flowersOfLife", examplesP(), ::flowersOfLifeV5)
    //runTest("flowersOfLife", examplesP(), testedFunction = { flowersOfLifeV5() })
    yandex.runTest("flowersOfLife", examplesXX, testedFunction = ::flowersOfLifeV5) //?? Unresolved reference:

}

private val examplesXX = arrayOf(
    Pair("5 10\n" +
            "4 6 7\n" +
            "2 3 3\n" +
            "1 1 4\n" +
            "2 3 1\n" +
            "2 4 5",
        "Yes"),
    Pair("3 10\n" +
            "1 5 10\n" +
            "2 3 4\n" +
            "2 6 5",
        "No"),
    Pair("1 2\n" +
            "1 1 1",
        "No"),
    Pair("1 1\n" +
            "1 1 1",
        "Yes"),
    Pair("2 1\n" +
            "1 1 1\n" +
            "1 1 1",
        "Yes"),
    Pair("2 23232445443434343\n" +
            "1 23232445443434343 23232445443434343\n" +
            "1 1 1",
        "No"),
    //Pair("12", "2"),
)

private fun examplesP(): Array<Pair<String, String>> {
    return arrayOf(
        Pair("5 10\n" +
                "4 6 7\n" +
                "2 3 3\n" +
                "1 1 4\n" +
                "2 3 1\n" +
                "2 4 5",
            "Yes"),
        Pair("3 10\n" +
                "1 5 10\n" +
                "2 3 4\n" +
                "2 6 5",
            "No"),
        Pair("1 2\n" +
                "1 1 1",
            "No"),
        Pair("1 1\n" +
                "1 1 1",
            "Yes"),
        Pair("2 1\n" +
                "1 1 1\n" +
                "1 1 1",
            "Yes"),
        Pair("2 23232445443434343\n" +
                "1 23232445443434343 23232445443434343\n" +
                "1 1 1",
            "No"),
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
