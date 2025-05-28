package org.jj.makcha.domain.station

import org.springframework.stereotype.Component

@Component
class StationNameTrieRepository {

    private val root = TrieNode()

    fun insert(name: String) {
        var currentNode = root
        for (char in name) {
            currentNode = currentNode.children.computeIfAbsent(char) { TrieNode() }
        }
        currentNode.isEndOfWord = true
    }

    fun search(name: String): Boolean {
        var currentNode = root
        for (char in name) {
            currentNode = currentNode.children[char] ?: return false
        }
        return currentNode.isEndOfWord
    }

    private class TrieNode {
        val children: MutableMap<Char, TrieNode> = mutableMapOf()
        var isEndOfWord: Boolean = false
    }
}
