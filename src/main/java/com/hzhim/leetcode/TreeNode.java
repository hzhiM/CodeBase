package com.hzhim.leetcode;

import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
//    public TreeNode process(int[] pre, int[] in, int stop) {
//        if (pre_idx == pre.length) return null;
//        if (in[in_idx] == stop) {
//            in_idx++;
//            return null;
//        }
//        TreeNode root = new TreeNode(pre[pre_idx++]);
//        root.left = process(pre, in, root.val);
//        root.right = process(pre, in, stop);
//        return root;
//    }

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

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public Node connect(Node root) {
            if (root == null) return null;
            List<Node> children = new ArrayList<>();
            children.add(root);
            while (!children.isEmpty()) {
                List<Node> newChildren = new ArrayList<>();
                for (int i = 0; i < children.size(); i++) {
                    if (i == children.size() - 1) {
                        children.get(i).next = null;
                    } else {
                        children.get(i).next = children.get(i + 1);
                    }
                    if (children.get(i).left != null) {
                        newChildren.add(children.get(i).left);
                    }
                    if (children.get(i).right != null) {
                        newChildren.add(children.get(i).right);
                    }
                }
                children = newChildren;
            }
            return root;
        }
    }

    /**
     * 前序遍历，中左右
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null) {
            flatten(root.right);
        } else {
            flatten(root.left);
            TreeNode left = root.left;
            while (left.right != null) {
                left = left.right;
            }
            left.right = root.right;
            root.right = root.left;
            flatten(root.right);
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.val == targetSum && root.left == null && root.right == null) {
            return true;
        }
        if (root.right != null) {
            root.right.val += root.val;
        }
        if (root.left != null) {
            root.left.val += root.val;
        }
        return hasPathSum(root.right, targetSum) || hasPathSum(root.left, targetSum);
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }

        if (root.left != null) {
            root.left.val += (root.val * 10);
        }
        if (root.right != null) {
            root.right.val += (root.val * 10);
        }
        return sumNumbers(root.right) + sumNumbers(root.left);
    }


    class BSTIterator {
        TreeNode cur;
        Deque<TreeNode> list;

        public BSTIterator(TreeNode root) {
            this.cur = root;
            list = new LinkedList<>();
        }

        public int next() {
            while (cur != null) {
                list.push(cur);
                cur = cur.left;
            }
            TreeNode pop = list.pop();
            cur = pop.right;
            return pop.val;

        }

        public boolean hasNext() {
            return cur != null || !list.isEmpty();
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftLevel = countLevel(root.left);
        int rightLevel = countLevel(root.right);
        int left = 0;
        int right = 0;
        if (leftLevel == rightLevel) {
            //左子树是满的
            left = 1 << leftLevel;
            right = countNodes(root.right);
        } else {
            //右子树是满的
            left = countNodes(root.left);
            right = 1 << rightLevel;
        }
        return left + right;


    }

    private int countLevel(TreeNode left) {
        int n = 0;
        while (left != null) {
            n++;
            left = left.left;
        }
        return n;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        result.add(root.val);
        List<Integer> right = rightSideView(root.right);
        List<Integer> left = rightSideView(root.left);
        if (right.size() >= left.size()) {
            result.addAll(right);
        } else {
            result.addAll(right);
            result.addAll(left.subList(right.size(), left.size()));
        }
        return result;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> resul = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Double level = 0d;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = queue.pop();
                level += pop.val;
                if (pop.left != null) queue.add(pop.left);
                if (pop.right != null) queue.add(pop.right);
            }
            resul.add(level / size);
        }
        return resul;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resul = new ArrayList<>();
        if (root == null) return resul;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = queue.pop();
                list.add(pop.val);
                if (pop.left != null) queue.add(pop.left);
                if (pop.right != null) queue.add(pop.right);
            }
            resul.add(list);
        }
        return resul;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resul = new ArrayList<>();
        if (root == null) return resul;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean left = true;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            Deque<TreeNode> newQue = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                if (left) {
                    TreeNode pop = queue.pop();
                    list.add(pop.val);
                    if (pop.left != null) newQue.push(pop.left);
                    if (pop.right != null) newQue.push(pop.right);
                } else {
                    TreeNode pop = queue.pop();
                    list.add(pop.val);
                    if (pop.right != null) newQue.push(pop.right);
                    if (pop.left != null) newQue.push(pop.left);
                }
            }
            queue = newQue;
            left = left ? false : true;
            resul.add(list);
        }
        return resul;
    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int left = Integer.MAX_VALUE;
        if (root.left != null) {
            TreeNode rightNode = root.left;
            while (rightNode.right != null) {
                rightNode = rightNode.right;
            }
            left = Math.min(root.val - rightNode.val, left);
        }
        int right = Integer.MAX_VALUE;
        if (root.right != null) {
            TreeNode leftNode = root.right;
            while (leftNode.left != null) {
                leftNode = leftNode.left;
            }
            left = Math.min(leftNode.val - root.val, left);
        }

        return Math.min(getMinimumDifference(root.left), Math.min(getMinimumDifference(root.right), Math.min(left, right)));
    }


    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            k--;
            if (k == 0) {
                break;
            }
            root = root.right;

        }
        return root.val;
    }

    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        Integer val = null;
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }

            root = deque.pop();
            if (val != null && val >= root.val) {
                return false;
            }
            val = root.val;
            root = root.right;
        }
        return true;
    }
}
