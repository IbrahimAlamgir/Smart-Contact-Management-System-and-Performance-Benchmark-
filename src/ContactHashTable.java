package src;

import java.util.HashMap;
import java.util.Map;

public class ContactHashTable {
    private final Map<String, Contact> table;

    public ContactHashTable() {
        this.table = new HashMap<>();
    }

    public void put(String phoneNumber, Contact contact) {
        table.put(phoneNumber, contact);
    }

    public Contact get(String phoneNumber) {
        return table.get(phoneNumber);
    }

    public boolean containsKey(String phoneNumber) {
        return table.containsKey(phoneNumber);
    }
}
