package com.neo;

import java.util.HashSet;
import java.util.Set;

public class Node {

    /**
     * create Node with passing String
     * @param value
     */
    public Node(String value) {
        this.value = value;
    }

    /**
     * Property of Node : value of node
     */
    public String value;

    /**
     * property of Node : Set of child Node
     */
    public Set<Node> childNode = new HashSet<>();

    /**
     * property of Node : set that node is endPoint or not
     */
    public boolean endPoint;


    /**
     * Get that Node is end point or not
     * @return
     */
    public boolean isEndPoint() {
        return endPoint;
    }

    /**
     * set that Node is end point
     * @param endPoint
     */
    public void setEndPoint(boolean endPoint) {
        this.endPoint = endPoint;
    }


    /**
     * check all childNode for any childNode value is same as String or not
     * @param node
     * @param c
     * @return boolean
     */
    public Boolean isContains(Node node, String c) {
        return node.childNode.stream().anyMatch(e -> e.value.equals(c));
    }


    /**
     * Adding childNode  in present node with value
     * @param node
     * @param word
     * @return
     */
    public Node addChild(Node node , String word){
        Node node1 = new Node(word);
        node.childNode.add(node1);
        System.out.println(" node value " + node1.value + " is insert.");
        return node1;
    }
}
