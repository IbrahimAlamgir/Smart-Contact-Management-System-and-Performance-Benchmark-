package src;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner scanner = new Scanner(System.util.in);
        
        // Seed default records for evaluation convenience
        manager.addContact(new Contact("Ibrahim Alamgir", "0501234567", "ibrahim@trent.ca"));
        manager.addContact(new Contact("Adnan Athar", "0507654321", "adnan@domain.com"));
        manager.addContact(new Contact("Zarmeen Usman", "0569876543", "zarmeen@york.ca"));

        while (true) {
            System.out.println("=== SMART CONTACT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add New Contact (Sync Registry Across Structures)");
            System.out.println("2. Exact Match Lookup via Phone Number [O(1) Hash Query]");
            System.out.println("3. Prefix Match Search-As-You-Type [O(L) Trie Filtering]");
            System.out.println("4. Display All Contacts Alphabetically [O(N) AVL Traversal]");
            System.out.println("5. Scan System for Redundant Email Channels");
            System.out.println("6. Run Systems Multi-Tier Performance Benchmark Harness");
            System.out.println("7. Exit Subsystem");
            System.out.print("Select operational routing command: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter full identifier name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter unique contact cell lane: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter structural email destination: ");
                    String email = scanner.nextLine();

                    boolean inserted = manager.addContact(new Contact(name, phone, email));
                    if (inserted) System.out.println("[SUCCESS] Synchronized record state across indices.\n");
                    else System.out.println("[FAILURE] Operation aborted: Cell key identity collision.\n");
                    break;

                case 2:
                    System.out.print("Enter targets phone verification value: ");
                    String lookupPhone = scanner.nextLine();
                    Contact match = manager.searchByPhone(lookupPhone);
                    if (match != null) System.out.println("[FOUND] " + match + "\n");
                    else System.out.println("[NOT FOUND] Hash lookup returned null match entry.\n");
                    break;

                case 3:
                    System.out.print("Enter name query sequence to autocomplete: ");
                    String prefix = scanner.nextLine();
                    List<Contact> trieMatches = manager.autocompleteByName(prefix);
                    System.out.println("--- Predictive Matches Found (" + trieMatches.size() + ") ---");
                    for (Contact c : trieMatches) System.out.println(c);
                    System.out.println();
                    break;

                case 4:
                    List<Contact> sorted = manager.getAllContactsSorted();
                    System.out.println("--- Sorted Registry Profile Matrix ---");
                    for (Contact c : sorted) System.out.println(c);
                    System.out.println();
                    break;

                case 5:
                    List<Contact> analyticalView = manager.getAllContactsSorted();
                    List<Contact> duplicates = DuplicateDetector.findDuplicateEmails(analyticalView);
                    System.out.println("--- Redundant Inboxes Detected ---");
                    for (Contact c : duplicates) System.out.println(c);
                    System.out.println();
                    break;

                case 6:
                    PerformanceBenchmark.runBenchmark();
                    break;

                case 7:
                    System.out.println("Shutting down core subsystem interface pipelines.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid operational command parameter.\n");
            }
        }
    }
}
