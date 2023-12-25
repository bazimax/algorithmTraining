import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.*

/**
 * Byte	    8	-128	                            127                                     10^2    1 байт
 * Short	16	-32768	                            32767                                   10^4    2 байта
 * Int	    32	-2,147,483,648 (-2^31)	            2,147,483,647 (2^31 - 1)                10^9    4 байта
 * Long	    64	-9,223,372,036,854,775,808 (-2^63)	9,223,372,036,854,775,807 (2^63 - 1)    10^18   8 байт
 *
 * UBite        0                                   255                                             1 байт
 * UShort       0                                   65 535                                          2 байта
 * UInt         0                                   2^32 - 1                                        4 байта
 * ULong        0                                   2^64 - 1                                >10^18 8 байт
 */

fun main(args: Array<String>) {
    //priorityQueueTest()
    //linkedListTest()
    //filter()
    //Testy()
    //any()
    //vararg(group = "KT-091", "Tom", "Bob", "Alice", count = 3) // "users =" не прописывать?
    //testOperatorStar()
    //knockKnock()
    //println("- $valA")
    //nullTest()
    //valFun()
    //lambda()


    fun doOperation(x: Int, y: Int, op: (Int, Int) ->Int){

        val result = op(x, y)
        println(result)
    }
    val sum = {x:Int, y:Int -> x + y }
    doOperation(3, 4, sum)                          // 7
    doOperation(3, 4, {x:Int, y: Int -> x * y}) // 12
    doOperation(3, 4) { x: Int, y: Int -> x * y } // 12
    val a = 1
    val b = 2
    doOperation(3, a + b) {a: Int, b: Int -> a * b}
}


// File
private fun appendTextToFile(){
    val output = BufferedWriter(FileWriter("output.txt"))
    output.write("000\n")
    output.flush()

    output.append("123")
    output.append("\n321")
    output.flush()

    output.append("\n4321")
    output.flush()
}
private fun copyFileSimple(){
    Files.copy(File("input.txt").toPath(), File("input2.txt").toPath(),
        StandardCopyOption.REPLACE_EXISTING)
}
private fun copyFile(){
    //If the source and destination point to the same file, the function completes without copying the file.
    val from = File("src.txt").toPath()
    val to = File("dest.txt").toPath()
    try {
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING)
        println("File copied successfully.")
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
}


// Collection
private fun linkedListTest() {
    val brackets = LinkedList<Char>()

    fun nPrint(name: String, variable: Any) {
        val newName = (if (name == "") " " else "$name:") + " ".repeat(10 - name.length)
        val variableBlank = " ".repeat(1 - "$variable".length)

        println("$newName $variable$variableBlank   ${brackets.joinToString()}")
    }

    brackets.add('[')
    brackets.addFirst('{')
    brackets.add('(')
    brackets.addFirst('#')
    brackets.addLast('%')
    brackets.add('$')

    nPrint("", "")
    nPrint("pop", brackets.pop())
    nPrint("peek", brackets.peek())
    nPrint("peekFirst", brackets.peekFirst())
    nPrint("peekLast", brackets.peekLast())
    nPrint("poll", brackets.poll())
    nPrint("pollFirst", brackets.pollFirst())
    nPrint("pollLast", brackets.pollLast())

    //                #, {, [, (, %, $
    //pop:        #   {, [, (, %, $
    //peek:       {   {, [, (, %, $
    //peekFirst:  {   {, [, (, %, $
    //peekLast:   $   {, [, (, %, $
    //poll:       {   [, (, %, $
    //pollFirst:  [   (, %, $
    //pollLast:   $   (, %
}
private fun priorityQueueTest() {
    val num = PriorityQueue<Int>() // по умолчанию - первый МЕНЬШИЙ
    //val num = PriorityQueue<Int>() {a, b -> a - b} //с условием (например: a.second - b.second) - первый МЕНЬШИЙ
    //val num = PriorityQueue<Int>() {a, b -> b - a} //с условием - первый БОЛЬШИЙ

    fun nPrint(name: String, variable: Any) {
        val newName = (if (name == "") " " else "$name:") + " ".repeat(10 - name.length)
        val variableBlank = " ".repeat(1 - "$variable".length)

        println("$newName $variable$variableBlank   ${num.joinToString()}")
    }

    num.add(5)
    num.add(7)
    num.add(9)
    num.add(3)
    num.add(1)

    nPrint("", "")
    nPrint("peek", num.peek())
    nPrint("poll", num.poll())
    nPrint("first", num.first())
    nPrint("element", num.element())
    //nPrint("remove", num.remove(9)) //Count 'n' must be non-negative, but was -3.

    //                1, 3, 9, 7, 5
    //peek:       1   1, 3, 9, 7, 5
    //poll:       1   3, 5, 9, 7
    //first:      3   3, 5, 9, 7
    //element:    3   3, 5, 9, 7
}

// Vararg
private fun vararg(group: String, vararg users: String, count:Int){
    println("Group: $group")
    println("Count: $count")
    for(user in users)
        println(user)
}
// Operator *
private fun testOperatorStar(){
    val nums = intArrayOf(1, 2, 3, 4)
    operatorStar(*nums, koef = 2)
    operatorStar(5,6,7, koef = 2)
}
private fun operatorStar(vararg numbers: Int, koef: Int){
    for(number in numbers)
        println(number * koef)
}

// Function
private fun knockKnock() = println("Who’s there?")
//private val valA = println("val A") // will be printed when the program starts; value A == kotlin.Unit
fun topLevelFunction(age1: Int, age2: Int){ //Top-Level-Function


    fun ageIsValid(age: Int) = age in 1..110 //Local-Function

    if( !ageIsValid(age1) || !ageIsValid(age2)) {
        println("Invalid age")
        return
    }

    when {
        age1 == age2 -> println("age1 == age2")
        age1 > age2 -> println("age1 > age2")
        else -> println("age1 < age2")
    }
}
// Function overloading
private fun funOverload(){
    fun sum(a: Int, b: Int) : Int = a + b
    fun sum(a: Double, b: Double) : Double = a + b
    fun sum(a: Int, b: Int, c: Int) : Int = a + b + c
    fun sum(a: Int, b: Double) : Double = a + b
    fun sum(a: Double, b: Int) : Double = a + b
    //fun sum(a: Double, b: Int) : String = "$a + $b" // error

    // тут, если запустить, выполнится вторая функция:
    fun test(vararg strings : String)  = println("funTest1")
    fun test(string1: String, string2: String) = println("funTest2")

}

// Variable as function
private fun valFun() {
    fun hello2() = println("Hello Kotlin")

    val message: () -> Unit
    message = ::hello2
    message()

    println("- $message, ${message.javaClass}, ${message.javaClass.simpleName}, ${message.javaClass.typeName}")

    println(message.javaClass.typeName.split("$")[0].replace("Kt", "")) // file name
    println(message.javaClass.typeName.split("$")[1]) // top-level-function name
    println(message.toString().split(" ")[1]) // local function name ?
}

//?? Lambda
private fun lambda(){
    val sum1 = {x:Int, y:Int -> println(x + y)}
    fun sum2(x:Int, y:Int) = println(x + y)
    sum1(2, 3)
    sum2(2, 3)

    val sum3 = {x:Int, y:Int ->
        val result = x + y
        println("$x + $y = $result")
        result
    }
    println(sum3(1,2))

    // Пример
    val sum4 = {x:Int ->
        val result = x + 1
        println("$x + $1 = $result")
        //result
    }
    println(sum3) // Function1<java.lang.Integer, kotlin.Unit>   // first - x, second - return


    fun doOperation(x: Int, y: Int, op: (Int, Int) ->Int){

        val result = op(x, y)
        println(result)
    }
    val sum = {x:Int, y:Int -> x + y }
    doOperation(3, 4, sum)                          // 7
    doOperation(3, 4, {x:Int, y: Int -> x * y}) // 12
    doOperation(3, 4) { a: Int, b: Int -> a * b } // 12 // конечная лямбда или trailing lambda


    val a = {println("hello")} // not printed
    a() // printed

    //{println("Hello Kotlin")}() // будет работать если только эта строка и напечатана
    a();
    {println("Hello Kotlin")}() // так будет работать > ;
    a();
    {message: String -> println(message)}("Welcome to Kotlin")
}

// Any
private fun any(){

    // Property / Variables ??
    var name: Any = "Tom"
    println(name)   // Tom
    name = 6758
    println(name)   // 6758


    // Array Any
    val a = Array<Any>(5) {"#"}
    a[1] = "123"
    a[2] = 12
    a[1] = 44
    //a[3] = a[1] + a[2] // don't work
    a[3] = a[1].toString() + a[2] // work
    println(a.joinToString()) // #, 44, 12, 4412, #

    val b = Array<Int>(5){0}
    b[0] = 5
    b[1] = 4
    b[2] = b[0] + b[1] // work
    println(b.joinToString()) // 5, 4, 9, 0, 0
}

// Null
private fun nullTest(){
    val a: Int
    //println(a) // error

    val b: Int?
    //println(b) // error

    // val c // error

    val d: Int? = null
    println(d) // null
}

// Filter
private fun filter(){
    val fruits = listOf("банан", "авокадо", "яблоко", "киви")
    fruits
        .filter { it.startsWith("а") }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach { println(it) } // АВОКАДО

    println(fruits) // [банан, авокадо, яблоко, киви]
}




// Documentation (KDoc syntax)

// brief description of the class
// after a space - detailed information about the class
/**
 * Brief
 *
 * Detailed information about the class
 */
class Testy







