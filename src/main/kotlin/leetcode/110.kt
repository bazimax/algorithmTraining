package leetcode

fun main(args: Array<String>) {
    println(isBalanced110(exampleTreeNode110Case2()))
    println("-----------")
    println(isBalanced110(exampleTreeNode110Case4()))
}

//IN WORK
//110. Balanced Binary Tree - Easy - https://leetcode.com/problems/balanced-binary-tree/description/

private fun exampleTreeNode110Case4(): TreeNode {
    //root = [1,2,3,4,5,6,null,8]
    val a1 = TreeNode(1)

    val a2 = TreeNode(2)
    val b2 = TreeNode(3)

    val a3 = TreeNode(4)
    val b3 = TreeNode(5)
    val c3 = TreeNode(6)
    //val d3 = TreeNode(7)

    val a4 = TreeNode(8)

    a1.left = a2
    a1.right = b2

    a2.left = a3
    a2.right = b3

    b2.left = c3

    a3.left = a4

    return a1
}
private fun exampleTreeNode110Case2(): TreeNode {
    //root = [1,2,2,3,3,null,null,4,4]
    val a1 = TreeNode(1)

    val a2 = TreeNode(2)
    val b2 = TreeNode(2)

    val a3 = TreeNode(3)
    val b3 = TreeNode(3)
    //val c3 = TreeNode(6)
    //val d3 = TreeNode(7)

    val a4 = TreeNode(4)
    val b4 = TreeNode(4)

    a1.left = a2
    a1.right = b2

    a2.left = a3
    a2.right = b3

    a3.left = a4
    a3.right = b4

    return a1
}

//110 > 6:30 - 7:15 (~45мин) > notOK (не все примеры проходит), зато код полностью написан в leetcode (без IDE)
//надо разобраться в height-balanced binary tree (AVL tree - АВЛ дерево)
private fun isBalanced110(root: TreeNode?): Boolean {
    var count = 1
    var timer = 0 //

    var list = if (root != null) listOf(root) else emptyList()

    while (list.isNotEmpty() && timer < 2) {
        if (list.size != count) timer++
        println("count: $count, listSize: ${list.size}, timer: $timer")
        count *= 2
        list = level(list)
        println("--- ${list.size}")
    }
    return timer < 2
}
private fun level(listTree: List<TreeNode>) : List<TreeNode> {
    val list = mutableListOf<TreeNode>()
    listTree.forEach{
        if (it.left != null) {
            list.add(it.left!!)
        }
        if (it.right != null) {
            list.add(it.right!!)
        }
    }
    list.forEach { print("item: ${it.`val`}, ") }
    println()

    return list
}
