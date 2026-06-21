package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactTrie {
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        List<Contact> contactsWithPrefix = new ArrayList<>();
    }

    private final TrieNode root;

    public ContactTrie() {
        root = new TrieNode();
    }

    public void insert(Contact contact) {
        String name = contact.getName().toLowerCase();
        TrieNode current = root;
        current.contactsWithPrefix.add(contact);

        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
            current.contactsWithPrefix.add(contact);
        }
    }

    public List<Contact> searchPrefix(String prefix) {
        String lookup = prefix.toLowerCase();
        TrieNode current = root;

        for (int i = 0; i < lookup.length(); i++) {
            char ch = lookup.charAt(i);
            if (!current.children.containsKey(ch)) {
                return new ArrayList<>();
            }
            current = current.children.get(ch);
        }
        return new ArrayList<>(current.contactsWithPrefix);
    }
}
