package src;

import java.util.ArrayList;
import java.util.List;

public class PerformanceBenchmark {
    public static void runBenchmark() {
        System.out.println("\n--- Starting Systems Performance Validation ---");
        int size = 10000;
        List<Contact> testDataset = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            testDataset.add(new Contact("User" + i, "999" + i, "user" + i + "@benchmark.com"));
        }

        ContactManager benchmarkManager = new ContactManager();

        // 1. Benchmark Insertion Pace
        long startTime = System.nanoTime();
        for (Contact c : testDataset) {
            benchmarkManager.addContact(c);
        }
        long endTime = System.nanoTime();
        System.out.println("Bulk Data Ingestion (" + size + " records): " + ((endTime - startTime) / 1_000_000.0) + " ms");

        // 2. Benchmark Hash Table Instant Exact Match lookup
        startTime = System.nanoTime();
        benchmarkManager.searchByPhone("9995000");
        endTime = System.nanoTime();
        System.out.println("Hash Table Exact Match Lookup O(1): " + (endTime - startTime) + " ns");

        // 3. Benchmark Prefix Tree Auto-Complete Trajectory Lookup
        startTime = System.nanoTime();
        benchmarkManager.autocompleteByName("User50");
        endTime = System.nanoTime();
        System.out.println("Prefix Tree Autocomplete Lookup O(L): " + (endTime - startTime) + " ns");

        // 4. Benchmark Balanced AVL Tree In-Order Sequential Read Out
        startTime = System.nanoTime();
        benchmarkManager.getAllContactsSorted();
        endTime = System.nanoTime();
        System.out.println("AVL Tree Ordered Traversal Readout O(N): " + ((endTime - startTime) / 1_000_000.0) + " ms");
        System.out.println("-----------------------------------------------\n");
    }
}
