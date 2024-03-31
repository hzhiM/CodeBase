package com.hzhim.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maori
 * @since 2024/03/30
 **/
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        treeNode.buildTree(pre, in);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null && q != null) return false;
        if (p != null && q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (right == null || left == null || left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * @param preorder 中左右
     * @param inorder  左中右
     * @return
     */
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        if (preorder.length == 0) {
//            return null;
//        }
//        TreeNode node = new TreeNode(preorder[0]);
//        int leftNum = 0;
//        for (int i = 0; i < inorder.length; i++) {
//            if (inorder[i] == preorder[0]) {
//                leftNum = i;
//                break;
//            }
//        }
//        //找到左边的 前序和中序
//        int[] newPre = Arrays.copyOfRange(preorder, 1, 1 + leftNum);
//        int[] newIn = Arrays.copyOfRange(inorder, 0, leftNum);
//
//        node.left = buildTree(newPre, newIn);
//        //找到右边的 前序和中序
//        newPre = Arrays.copyOfRange(preorder, 1 + leftNum, preorder.length);
//        newIn = Arrays.copyOfRange(inorder, 1 + leftNum, inorder.length);
//        node.right = buildTree(newPre, newIn);
//        return node;
//    }

//    int pre_idx = 0;
//    int in_idx = 0;
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        return process(preorder,inorder,Integer.MAX_VALUE);
//    }
    public TreeNode process(int[] pre, int[] in, int stop) {
        if (pre_idx == pre.length) return null;
        if (in[in_idx] == stop) {
            in_idx++;
            return null;
        }
        TreeNode root = new TreeNode(pre[pre_idx++]);
        root.left = process(pre, in, root.val);
        root.right = process(pre, in, stop);
        return root;
    }

    /**
     * 中序和后序
     *
     * @param inorder 左中右
     * @param postorder 左右中
     * @return
     */
    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
        if (inBegin < 0 || inEnd < 0 || postBegin < 0 || postEnd < 0 || inBegin > inEnd || postBegin > postEnd)
            return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        //左子树
        Integer index = indexMap.get(postorder[postEnd]);
        //postEnd = postBegin+左子树的个数
        root.left = buildTree(inorder, inBegin, index - 1, postorder, postBegin, postBegin + index - inBegin - 1);
        //右子树
        //postBegin=
        root.right = buildTree(inorder, index + 1, inEnd, postorder, postBegin + index - inBegin, postEnd - 1);
        return root;
    }


}
