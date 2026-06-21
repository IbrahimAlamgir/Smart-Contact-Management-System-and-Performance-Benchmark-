# Smart Contact Management System and Performance Benchmark Suite

An advanced, high-performance Java application engineered to store, search, and optimize contact management operations using an optimized hybrid data structure architecture. By deploying specialized concurrent data structures—including a Prefix Tree (Trie), a Self-Balancing Binary Search Tree (AVL Tree), and a Hash Table—the system achieves optimal algorithmic time complexity across distinct lookup, sorting, and prefix-matching paradigms, moving away from inefficient linear array sweeps.

---

## System Architecture and Hybrid Data Structure Design

To maximize performance in high-throughput enterprise environments, the system mirrors data across three specialized structures, leveraging the unique strengths of each:

* **Prefix Tree (ContactTrie):** Dedicated to prefix-based matching and search-as-you-type autocomplete functionality. It executes name-string lookups in O(L) time complexity, where L represents the length of the search string, entirely independent of total record volume.
* **Self-Balancing Tree (AVLTree):** Enforces a strict height balance factor to maintain a height of O(log N). This guarantees log-time operations for ordered contact listings, range queries, and alphabetic sorting boundaries.
* **Hash Table (ContactHashTable):** Utilizes custom hash distributions and collision resolution mechanisms to facilitate exact-match queries (such as phone number or unique ID verifications) in O(1) constant time complexity.
* **Orchestration Layer (ContactManager):** Acts as the system controller, synchronizing modifications atomically across all sub-structures to preserve data parity and prevent system-wide state drift.

### Structural Topology and Data Paths

```text
                     [ Interface Layer: ConsoleUI ]
                                    |
                                    v
                     [ Orchestrator: ContactManager ]
                                    |
         +--------------------------+--------------------------+
         |                          |                          |
         v                          v                          v
  [ ContactTrie ]            [ AVLTree ]               [ ContactHashTable ]
  Complexity: O(L)           Complexity: O(log N)      Complexity: O(1)
  Task: Prefix Search        Task: Sorted Retrieval     Task: Exact Key Lookup
```
## Software Component Matrix and Design

| Module / File | Operational Responsibility | Core Algorithmic Framework / API |
| :--- | :--- | :--- |
| `Contact.java` | Core data model encapsulation representing a distinct entity, overriding equality contracts. | Encapsulation, Hash Code Overriding |
| `ContactTrie.java` | Prefix-tree tracking structure optimizing spelling trajectories and predictive search auto-completion. | Node Array Ingestion, Prefix Filtering |
| `AVLTree.java` | Self-balancing binary tree providing consistent log-time insertions and dynamic balancing factors. | Left/Right Rotations, In-Order Traversal |
| `ContactHashTable.java` | Key-value store executing constant-time lookups for unique contact markers. | Chaining/Open Addressing, Collision Control |
| `ContactManager.java` | Core orchestration layer distributing atomic data mutations across all supporting indices. | Structural Synchronization Logic |
| `DuplicateDetector.java` | Analytical parsing module scanning record attributes to locate and flag redundant entities. | Linear Scan Filters, Intersection Checking |
| `PerformanceBenchmark.java` | Execution harness tracking data ingestion speed and querying metrics across all tree/hash layers. | `System.nanoTime()`, Data Collection |
| `ConsoleUI.java` | Command-line presentation layer routing configuration sequences and showing query timelines. | Scanner Streams, Output Buffer Mapping |

### Architectural Robustness Features
* **Memory Management and Optimization:** Nodes inside the Trie structure initialize children arrays dynamically to reduce memory footprint over sparse tracking matrix layouts.
* **Data Parity Enforcement:** Write mutations (additions, modifications, deletions) are structured as atomic operations managed by the orchestration layer, preventing stale indices across data pipelines.
* **Algorithmic Shielding:** AVL tree rotation parameters execute defensive restructuring during structural alterations, ensuring degenerate tree conversions (linear linked-list degradation) never happen.

---

## System Validation and Operational Verification

* **Test Case 1 (Prefix-Search Accuracy):** Injecting a complex set of identical string prefixes verifies that the system isolates and produces complete autocomplete responses in O(L) time bounds. **[Pass]**
* **Test Case 2 (Dynamic Rebalancing Stability):** Executing monotonic sequential contact insertions into the system verifies that the AVL node engine initiates balance corrections to preserve log-time path lengths. **[Pass]**
* **Test Case 3 (Constant-Time Exact Retrieval):** Simulating multi-threaded exact identifier searches across the Hash Table layer validates constant O(1) performance profile tracking. **[Pass]**
* **Test Case 4 (Benchmarking Execution Parity):**
    * *Issue:* Extreme data population loads initially led to execution tracking variances due to early JVM memory garbage collection interrupts.
    * *Resolution:* Standardized the `PerformanceBenchmark` harness routines to execute warm-up cycles and trace systemic operational averages over high-density array pools. **[Pass]**

---

## Engineering Team and Roles

* **Ibrahim Alamgir:** Software Engineering Lead (Hybrid Data Structure Design, AVL Tree Rotation Mechanics, Trie Traversal Optimization, Architectural Synchronizer).

---

## Project Context and References

Developed to explore operational efficiency limits and complexity trade-offs between linear, logarithmic, and constant-time search architectures.

* Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). *Introduction to Algorithms: Advanced Data Hierarchies*.
* Knuth, D. E. (1998). *The Art of Computer Programming: Sorting and Searching Structures*.
