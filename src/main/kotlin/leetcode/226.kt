package leetcode

import java.util.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFunOld226(testAskOld226)
}

//226. Invert Binary Tree - Easy - https://leetcode.com/problems/invert-binary-tree/description/

private val testAskOld226 = arrayOf(Pair(exampleTree(), listOf(2,3,1)))
private fun testFunOld226(ask: Array<Pair<TreeNode, List<Int>>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            println(treeNodeToString(i.first))
            val answer = invertTree226(i.first)

            println("${treeNodeToString(answer) == i.second} :: in: ${treeNodeToString(i.first)}, " +
                    "correctAnswer: ${i.second}, ans: ${treeNodeToString(answer)}")
        }
        //println("time: $time")
    }
}
private fun exampleTree(): TreeNode{
    val root = TreeNode(2)
    val left = TreeNode(1)
    val right = TreeNode(3)
    root.left = left
    root.right = right
    return root
}

//226 > 19:18 - 20:03 (~45мин) > OK (fast 26%, memory 28%)
private fun invertTree226(root: TreeNode?): TreeNode? {
    if (root == null) return null
    val treeDeque = LinkedList<TreeNode>()
    treeDeque.add(root)

    while (treeDeque.isNotEmpty()) {
        val tree = treeDeque.pollFirst()
        val treeTemp = tree.left
        tree.left = tree.right
        tree.right = treeTemp
        if (tree.right != null) treeDeque.add(tree.right!!)
        if (tree.left != null) treeDeque.add(tree.left!!)
    }
    return root
}

private fun treeNodeToString(treeNode: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
    if (treeNode != null) {
        list.add(treeNode.`val`)
        if (treeNode.left != null) {
            list.add((treeNode.left!!.`val`))
        }
        if (treeNode.right != null) {
            list.add((treeNode.right!!.`val`))
        }
    }
    return list
}

//https://developer.android.com/reference/kotlin/java/util/LinkedList#add