package com.hzhim.leetcode;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author maori
 * @since 2024/04/23
 **/
public class Trie {
    Trie[] children;
    boolean stop;

    public Trie() {
        this.children = new Trie[26];
        stop = false;
    }

    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        char c = word.charAt(0);
        if (children[c - 'a'] == null) children[c - 'a'] = new Trie();
        if (word.length() > 1) {
            children[c - 'a'].insert(word.substring(1));
        } else {
            children[c - 'a'].stop = true;
        }

    }

    public boolean search(String word) {
        char c = word.charAt(0);
        if (children[c - 'a'] == null) {
            return false;
        }
        if (word.length() > 1) {
            return children[c - 'a'].search(word.substring(1));
        } else {
            return children[c - 'a'].stop;
        }
    }

    public boolean startsWith(String prefix) {
        char c = prefix.charAt(0);
        if (children[c - 'a'] == null) {
            return false;
        }
        if (prefix.length() > 1) {
            return children[c - 'a'].startsWith(prefix.substring(1));
        } else {
            return true;
        }
    }

    public static class WordDictionary {
        WordDictionary[] children;
        boolean stop;

        public WordDictionary() {
            this.children = new WordDictionary[26];
            stop = false;
        }

        public void addWord(String word) {
            WordDictionary[] children1 = this.children;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (children1[c - 'a'] == null) {
                    children1[c - 'a'] = new WordDictionary();
                }
                if (i == word.length() - 1) {
                    children1[c - 'a'].stop = true;
                }
                children1 = children1[c - 'a'].children;
            }

        }

        public boolean search(String word) {
            Queue<WordDictionary[]> queue = new ArrayDeque<>();
            queue.add(this.children);
            boolean last = false;

            for (int k = 0; k < word.length(); k++) {
                char c = word.charAt(k);
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    WordDictionary[] children1 = queue.poll();
                    for (int j = 0; j < 26; j++) {
                        if (children1[j] == null) {
                            continue;
                        }
                        if (c == '.' || (c - 'a') == j) {
                            if (k == word.length() - 1) {
                                last = last || children1[j].stop;
                            }
                            queue.add(children1[j].children);
                        }
                    }
                }
                if (queue.isEmpty()) {
                    return false;
                }

            }
            return last;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        wordDictionary.addWord("bat");
        wordDictionary.search("..");
        wordDictionary.search("bad");
        wordDictionary.search(".ad");
        wordDictionary.search("b..");
    }

}

