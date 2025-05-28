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

    fun searchAllAfter(query: String): List<String> {
        val results = mutableListOf<String>()
        var currentNode = root
        for (char in query) {
            currentNode = currentNode.children[char] ?: return emptyList()
        }
        findAllWordsFromNode(currentNode, StringBuilder(query), results)
        return results
    }

    private fun findAllWordsFromNode(node: TrieNode, prefix: StringBuilder, results: MutableList<String>) {
        if (node.isEndOfWord) {
            results.add(prefix.toString())
        }
        for ((char, childNode) in node.children) {
            prefix.append(char)
            findAllWordsFromNode(childNode, prefix, results)
            prefix.deleteCharAt(prefix.length - 1)
        }
    }

    private class TrieNode {
        val children: MutableMap<Char, TrieNode> = mutableMapOf()
        var isEndOfWord: Boolean = false
    }
}
