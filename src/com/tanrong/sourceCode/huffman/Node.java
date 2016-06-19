package com.tanrong.sourceCode.huffman;


/**
 * Created by tanrong.ltr on 16/6/19.
 */

/**
 * Huffman编码算法主要用到的数据结构是完全二叉树(full binary tree)和优先级队列。
 * 后者用的是Java.util.PriorityQueue，前者自己实现(都为内部类)，
 */
public class Node  implements Comparable<Node> {
    public String chars = "";
    public int frequence = 0;
    public Node parent;
    public Node leftNode;
    public Node rightNode;

    @Override
    public int compareTo(Node n) {
        return frequence - n.frequence;
    }

    public boolean isLeaf() {
        return chars.length() == 1;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeftChild() {
        return parent != null && this == parent.leftNode;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

}
class Tree{
    public Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

