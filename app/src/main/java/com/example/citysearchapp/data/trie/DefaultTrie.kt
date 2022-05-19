package com.example.citysearchapp.data.trie

import java.util.*

/**
 * Search is implemented using "Trie Data Structures".
 * A tree like structure is formed using the city title (city name, country code).
 * Each node of the tree represents a character.
 * Each trie has an empty root node, with links (or references) to other nodes one for each possible character.
 * Searched cities are retrieved by traversing down a branch path of the tree.

 * Trie allows us to input and locate strings in O(L) time, where L is the length of a single word.
 * Lookups are dependent on the depth of the tree. So if a tree is balanced, like in the case of BST with n number of keys, the lookup will take O(logn) time. As a result, a BST takes O(m log n) time in the worst case.
 * Tries allow for ordered iteration, whereas iterating over a hash table produces a pseudorandom order determined by the hash function (the order of hash collisions is also implementation specified), which is typically useless.
 * Helps in finding longest-prefix matching, assisting in the discovery of the key with the longest possible prefix of characters, all of which are unique.
 * For short keys like integers and pointers, attempts are often quicker than hash tables since they skip the hash function.
 * Compared to BST, tries take up less space. Because the keys are not explicitly saved, each key requires just an amortised fixed amount of space to be stored.
 * Deletion is also a straightforward O(L) algorithm where L is the length of the word to be deleted.
 **/

class DefaultTrie<T : TrieModelWithKey> : Trie<T> {

    /*
     * Root of structure
     */
    private var root = Node<T>()

    /*
     * Count of the nodes
     */
    private var size: Int = 0


    override fun insert(data: List<T>): Boolean {
        if (data.isEmpty()) return false

        val list = data.sortedBy { it.key }
        val len = list.size
        var counter = 0

        for (element in list) {
            if (insert(element)) counter++
        }
        return counter == len
    }

    override fun search(query: String): List<T> {
        var node = root
        val key = query.lowercase()

        var find = true

        for (element in key) {
            val child = node.child[element]
            if (child != null) {
                node = child
            } else {
                find = false
                break
            }
        }

        if (find) {
            val result = arrayListOf<T>()
            find(node) {
                it.data?.apply { result.addAll(this) }
            }
            return result
        }

        return emptyList()
    }

    override fun all(): List<T> {
        return search("")
    }

    override fun clear() {
        root = Node()
        size = 0
    }

    override fun length(): Int {
        return size
    }


    private fun insert(element: T): Boolean {
        val len = element.key.length
        val key = element.key.lowercase()
        var current = root
        var insert = false
        /*
         * Traversing on tree with every character
         * create node if not exist for specific character, ignore otherwise
         */
        for (i in 0 until len) {
            /*
             * Check if current node has element with specific character then move to next node or
             * create new node and attach to tree
             */
            var node = current.child[key[i]]
            if (node == null) {
                node = Node()
                current.child[key[i]] = node
            }

            current = node
            /*
             * If touch end of characters must mark it as leaf and put data to this node
             */
            if (i == len - 1) {
                current.end = true
                if (current.data == null) {
                    current.data = mutableListOf()
                }
                /*
                 * If we find duplicated data should ignore inserting but return true
                 * If node can not add data to structure must return false
                 */
                insert = if (current.data?.contains(element) == false) {
                    if (current.data?.add(element) == true) {
                        size++
                        true
                    } else {
                        false
                    }
                } else {
                    true
                }
            }
        }

        return insert
    }

    private fun find(node: Node<T>, block: (Node<T>) -> Unit) {
        if (node.end) {
            block(node)
        }
        /*
         * Search recursively to find node with end flag until leaf node
         */
        for (element in node.child) {
            find(element.value, block)
        }
    }

    /**
     * Node data class
     */
    private data class Node<T : TrieModelWithKey>(
        val child: SortedMap<Char, Node<T>> = sortedMapOf({ node1, node2 -> node1.compareTo(node2) }),
        var end: Boolean = false,
        var data: MutableList<T>? = null
    )
}