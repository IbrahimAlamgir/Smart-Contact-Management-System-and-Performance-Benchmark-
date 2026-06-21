package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateDetector {
    public static List<Contact> findDuplicateEmails(List<Contact> contacts) {
        List<Contact> duplicates = new ArrayList<>();
        Set<String> uniqueEmails = new HashSet<>();

        for (Contact c : contacts) {
            if (c.getEmail() != null && !c.getEmail().isEmpty()) {
                if (!uniqueEmails.add(c.getEmail().toLowerCase())) {
                    duplicates.add(c);
                }
            }
        }
        return duplicates;
    }
}
