package com.neo;

import java.util.HashSet;
import java.util.Set;

public class Trie {

    /**
     * Every Tree type data structure have always one Root node
     * Here ROOT is final node that create when new Trie structure create.
     */
    private final Node ROOT;

    public Trie() {
        this.ROOT= new Node(" ");
    }


    /**
     * Use for insert new word in structure
     * Working :
     *      take that full word and check each subString
     *      that subString present in Trie structure or not
     *      If not present then insert that string using another insert method
     *      Start checking from root node
     *      checking and inserting new node using @insert(String , Node) method
     * @param word
     */
    public void insert(String word) {
        if(word.matches("\\s")){
            System.out.println("Please Enter Valid word.");
            return;
        }
        Node temp = ROOT;
        for (int i = 1; i <=word.length(); i++) {
            temp = insert(word.substring(0, i), temp);
        }
        temp.setEndPoint(true);
    }


    /**
     * Inserting new node into Trie structure with checking that not present into structure
     * Working :
     *      First check word is empty or not
     *      Second check that present node has childNode or not if not then create childNode and set node value as subString
     *      And childNode is present then check what subString value node is present or not if not then add node and set node value as subString
     *
     * @param word
     * @param node
     * @return present inserted node or subString founded node
     */
    private Node insert(String word, Node node) {
        Node child = null;
        boolean flag=true;
        if (!word.isEmpty()) {
            if (!node.childNode.isEmpty()) {
                if (node.isContains(node, word)) {
                    for (Node cNode : node.childNode) {
                        if (word.equals(cNode.value)) {
                            child = cNode;
                            break;
                        }
                    }
                }else {
                    flag=false;
                }
            } else {
                child = node.addChild(node,word);
            }
        }
        if(!flag){
            child = node.addChild(node,word);
        }
        return child;
    }


    /**
     * Word is present or not.
     * Working :
     *      finding word step by step as subString from root node
     *      If substring word present then travel that not and check present or not.
     *      Internal method search(String , Node) that return node if substring present.
     * @param word
     */
    public void search(String word) {
        Node temp = ROOT;
        boolean flag=true;
        for (int i=1; i <=word.length(); i++) {
            if(temp!=null) {
                temp = search(word.substring(0, i), temp);
            }else{
                flag=false;
                break;
            }
        }
        if (flag && temp!=null && temp.isEndPoint()) {
            System.out.println(word + " is Present.");
        } else {
            System.out.println(word+" is Not Present.");
        }
    }


    /**
     * Searching node that value as String
     * Working :
     *      First check word is empty or not if empty then return null value
     *      If present in childNode then explore childNode and return childNode.
     * @param word
     * @param node
     * @return present node that value as searching String
     */
    private Node search(String word, Node node) {
        Node child=null;
        if (!word.isEmpty()) {
            if(!node.childNode.isEmpty()) {
                if(node.isContains(node,word)) {
                    for (Node cNode : node.childNode) {
                        if (word.equals(cNode.value)) {
                            child=cNode;
                        }
                    }
                }
            }
        }
        return child;
    }


    /**
     * Find next possible word
     * Working :
     *      Take input as String and find node that value as input String
     *      IF that node is end point then return with has not next possible
     *      Then travel all path from that node and create set of all possible String
     *      travel internal method using findWord(String)
     * @param word
     */
    public void nextPossible(String word){
        Node temp=ROOT;
        Node internalNode;
        for (int i = 1; i <=word.length(); i++) {
            if(temp!=null) {
                temp = search(word.substring(0,i), temp);
            }
        }
        if(temp!=null && !temp.isEndPoint()) {
        Set<String> nextPossibleWord = new HashSet<>();
            internalNode = temp;
            for (Node node : internalNode.childNode) {
                nextPossibleWord.addAll(findWord(node));
            }

            nextPossibleWord.forEach(System.out::println);
        }else {
            if(temp.isEndPoint()){
                System.out.println(word+" has not next possible.");
            }else {
                System.out.println(word + " is not present.");
            }
        }

    }


    /**
     * travel all the internal node and collect all the possible string
     * Working :
     *      Using recursion travel node and create set of String and each node check with endPoint or not
     * @param internalNode
     * @return All next possible String
     */
    private Set<String> findWord(Node internalNode){
        Set<String> nextPossibleWord = new HashSet<>();
        if(internalNode.isEndPoint()){
            nextPossibleWord.add(internalNode.value);
        }
        for (Node node : internalNode.childNode) {
            nextPossibleWord.addAll(findWord(node));
        }
        return nextPossibleWord;
    }

}
