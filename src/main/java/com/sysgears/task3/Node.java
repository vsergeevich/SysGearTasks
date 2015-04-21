/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysgears.task3;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class that describes the node in the tree
 *
 * @author Victor Tyrin
 */
public class Node {

    char val; //Value of the tries node
    Map<Character, Node> children; //Map that contains the values of the child nodes
    boolean terminal;//If terminal is true that current path is a valid word

    Node(char value) {
        this.val = value;
        children = new TreeMap<>();
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

}
