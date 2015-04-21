package com.sysgears.task3;

import java.util.List;

/**
 * To solve this task we build a prefix tree - efficient structure for text
 * storage and search.
 *
 * @author Victor Tyrin
 */
public class LongestWord {

    public static void main(String[] args) {
        String[] arr = {"five", "fivetwo", "fourfive", "fourfivetwo", "one", "onefiveone", "two", "twofivefourtwo"};
        Trie trie = new Trie();
        String longestWord = "";
        for (String str : arr) {
            trie.add(str);
        }
        for (String word : arr) {
            List<String> prefixes = trie.getAllPrefixesOfWord(word); //for each word in array we find all prefixes
            for (String prefix : prefixes) { 
                String suffix = word.substring(prefix.length());
                if (trie.isValidWord(suffix)) {
                    if (word.length() > longestWord.length()) {//for each prefix, if the rest of word is a valid word from trie,
                                                                //we compare its length with other compound words
                        longestWord = word;
                    }
                }
            }
        }
        System.out.println("The longest compound word is: " + longestWord);
    }

}
