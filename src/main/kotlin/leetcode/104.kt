package leetcode

fun main(args: Array<String>) {
    println(maxDepth104(exampleTreeNode104Case2()))
}

//104. Maximum Depth of Binary Tree - Easy - https://leetcode.com/problems/maximum-depth-of-binary-tree/description/

private fun exampleTreeNode104Case2(): TreeNode {
    //root = [1,2,2,3,3,null,null,4,4]
    val a1 = TreeNode(3)

    val a2 = TreeNode(9)
    val b2 = TreeNode(20)

    val a3 = TreeNode(15)
    val b3 = TreeNode(7)
    //val c3 = TreeNode(6)
    //val d3 = TreeNode(7)

    //val a4 = TreeNode(4)
    //val b4 = TreeNode(4)

    a1.left = a2
    a1.right = b2

    b2.left = a3
    b2.right = b3

    //a3.left = a4
    //a3.right = b4

    return a1
}

//104 > 9:15 - 9:35 почти без IDE (~20мин) > OK (fast 5%, memory 20%)
private fun maxDepth104(root: TreeNode?): Int {
    if (root == null) return 0
    var list = listOf(root)
    var count = 0

    while (list.isNotEmpty()) {
        count++
        list = level(list)
    }
    return count
}

private fun level(list: List<TreeNode>): List<TreeNode> {
    val newList = mutableListOf<TreeNode>()
    list.forEach{
        if (it.left != null) newList.add(it.left!!)
        if (it.right != null) newList.add(it.right!!)
    }
    return newList
}

