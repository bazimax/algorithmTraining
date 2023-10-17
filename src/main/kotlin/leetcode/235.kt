package leetcode

fun main(args: Array<String>) {
}

//235. Lowest Common Ancestor of a Binary Search Tree - Medium - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

//235 > 7:10 - 8:30 (~1ч20мин) 1ч размышлял и 20мин писал код :\ > OK (fast 91%, memory 24%)
private fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root != null) {
        if (root.`val` == p!!.`val` || root.`val` == q!!.`val`) return root
        else if (p.`val` <= root.`val` && q.`val` <= root.`val`) return lowestCommonAncestor(root.left, p, q)
        else if (p.`val` >= root.`val` && q.`val` >= root.`val`) return lowestCommonAncestor(root.right, p, q)
    }
    return root
}