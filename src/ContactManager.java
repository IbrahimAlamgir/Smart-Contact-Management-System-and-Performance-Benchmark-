package src;

import java.util.List;

public class ContactManager {
    private final ContactTrie trie;
    private final AVLTree avlTree;
    private final ContactHashTable hashTable;

    public ContactManager() {
        this.trie = new ContactTrie();
        this.avlTree = new AVLTree();
        this.hashTable = new ContactHashTable();
    }

    public boolean addContact(Contact contact) {
        if (hashTable.containsKey(contact.getPhoneNumber())) {
            return false; 
        }
        hashTable.put(contact.getPhoneNumber(), contact);
        trie.insert(contact);
        avlTree.insert(contact);
        return true;
    }

    public Contact searchByPhone(String phoneNumber) {
        return hashTable.get(phoneNumber);
    }

    public List<Contact> autocompleteByName(String prefix) {
        return trie.searchPrefix(prefix);
    }

    public List<Contact> getAllContactsSorted() {
        return avlTree.getInOrderTraversal();
    }
}
