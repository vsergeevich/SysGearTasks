/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysgears.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Class describes a structure of prefix tree
 *
 * @author Victor Tyrin
 */
class Trie {

    private final Node root;

    /**
     * Default constructor
     */
    public Trie() {
        root = new Node(' ');
    }

    /**
     * adds the word to the current tree
     */
    public void add(String word) {
        int length = word.length();
        char[] letters = word.toCharArray();
        Node currentNode = root;
        for (int i = 0; i < length; i++) {
            if (!currentNode.children.containsKey(letters[i])) { //checking for the existence of this path of the current letter
                Node newNode = new Node(letters[i]);//if not - add the node to current 
                if (i == length - 1) {
                    newNode.setTerminal(true);
                }
                currentNode.children.put(letters[i], newNode);
            }
            currentNode = currentNode.children.get(letters[i]); // if exists - go deeper on path
        }
    }

    /**
     * @return true if the tree contains parameter word
     */
    public boolean contains(String word) {
        char[] letters = word.toCharArray();
        Node currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (!currentNode.children.containsKey(letters[i])) {  //checking for the existence of this path of the current letter
                return false;
            } else {
                currentNode = currentNode.children.get(letters[i]);// if exists - go deeper on path
            }
        }
        if (currentNode == null || !currentNode.terminal) {
            return false;
        }
        return true;
    }

    /**
     * @return all prefixes of the word that are valid words from the array
     */
    public List<String> getAllPrefixesOfWord(String word) {
        List<String> prefixes = new ArrayList<>();
        int length = word.length();
        char[] letters = word.toCharArray();
        Node currentNode = root;
        for (int i = 0; i < length; i++) {
            if (!currentNode.children.containsKey(letters[i])) {//if the current sequence doesn't exist in tree, return empty List
                return prefixes;
            } else {
                currentNode = currentNode.children.get(letters[i]);// if exists - go deeper on path
                if (currentNode.terminal && i < length - 1) { //if the current sequence is a completed word - add it to List of prefixes
                    prefixes.add(word.substring(0, i + 1));
                }
            }
        }
        return prefixes;
    }

    /**
     * @return true when the @param word is contained in trie or is a compound
     * of words from the array
     */
    public boolean isValidWord(String word) {
        if (this.contains(word)) {
            return true;
        }
        List<String> prefixes = this.getAllPrefixesOfWord(word);
        for (String prefix : prefixes) {
            if (isValidWord(word.substring(prefix.length()))) {
                return true;
            }
        }
        return false;
    }
}
