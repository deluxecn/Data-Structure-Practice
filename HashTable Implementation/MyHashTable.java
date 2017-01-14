public class MyHashTable implements MyHTInterface {

    /**
     * Initial capacity value.
     */
    private static final int INITIAL_CAPACITY = 10;
    /**
     * Load factor value.
     */
    private static final double LOAD_FACTOR = 0.5;
    /**
     * DataItem[] array that implements the hash table.
     */
    private DataItem[] arr;
    /**
     * The number of items and the length of the array.
     */
    private int size, length;
    /**
     * The deleted DataItem object.
     */
    private static final DataItem DELETED = new DataItem(null);

    /**
     * No arg constructor.
     */
    public MyHashTable() {
        length = INITIAL_CAPACITY;
        arr = new DataItem[length];
        size = 0;
    }

    /**
     * Constructor with initial capacity.
     * @param capacity input capacity.
     */
    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity should be larger than 0.");
        }
        length = capacity;
        arr = new DataItem[length];
        size = 0;
    }

    // TODO implement required methods
    /**
     * Inserts a new String value (word). Frequency of each word to be stored
     * too.
     * @param value String value to add
     */
    public void insert(String value) {
        if (!isWord(value)) {
            return;
        }
        int hash = hashValue(value);
        int pos = hash;
        // System.out.println("hash value of " + value + " is: " + hash);
        while (arr[pos] != null && arr[pos] != DELETED) {
            if (value.equals(arr[pos].getValue())) {
                arr[pos].increaseFreq();
                return;
            }
            pos = (pos + 1) % length;
        }
        DataItem item = new DataItem(value);
        arr[pos] = item;
        size++;
        if ((double) size / length > LOAD_FACTOR) {
            rehash();
        }
    }

    /**
     * Returns the size, number of items, of the table.
     * @return the number of items in the table
     */
    public int size() {
        return size;
    }

    /**
     * Displays the values of the table. If an index is empty, it shows ** If
     * previously existed data item got deleted, then it should show #DEL#
     */
    public void display() {
        for (int i = 0; i < length; i++) {
            if (arr[i] == null) {
                System.out.print("** ");
            } else if (arr[i] == DELETED) {
                System.out.print("#DEL# ");
            } else {
                System.out.print("[" + arr[i].getValue() + ", "
                        + arr[i].getFreq() + "] ");
            }
        }
        System.out.println();
    }

    /**
     * Returns true if value is contained in the table.
     * @param key String key value to search
     * @return true if found, false if not found.
     */
    public boolean contains(String key) {
        if (!isWord(key)) {
            return false;
        }
        int hash = hashValue(key);
        int pos = hash;
        while (arr[pos] != null) {
            if (arr[pos] != DELETED && key.equals(arr[pos].getValue())) {
                return true;
            }
            pos = (pos + 1) % length;
        }
        return false;
    }

    /**
     * Returns the number of collisions in relation to insert and rehash. When
     * rehashing process happens, the number of collisions should be properly
     * updated. The definition of collision is "two different keys map to the
     * same hash value." Be careful with the situation where you could
     * overcount. Try to think as if you are using separate chaining. "How would
     * you count the number of collisions?" when using separate chaining.
     * @return number of collisions
     */
    public int numOfCollisions() {
        int ret = 0;
        for (int i = 0; i < length; i++) {
            if (arr[i] != null && arr[i] != DELETED) {
                if (hashValue(arr[i].getValue()) != i) {
                    ret++;
                }
            }
        }
        return ret;
    }

    /**
     * Returns the hash value of a String.
     * @param value value for which the hash value should be calculated
     * @return int hash value of a String
     */
    public int hashValue(String value) {
        // System.out.println("hashFunc(" + value + "): " + hashFunc(value));
        return hashFunc(value);
    }

    /**
     * Returns the frequency of a key String.
     * @param key string value to find its frequency
     * @return frequency value if found. If not found, return 0
     */
    public int showFrequency(String key) {
        if (!isWord(key)) {
            return 0;
        }
        int hash = hashValue(key);
        while (arr[hash] != null) {
            if (arr[hash] != DELETED && key.equals(arr[hash].getValue())) {
                return arr[hash].getFreq();
            }
            hash = (hash + 1) % length;
        }
        return 0;
    }

    /**
     * Removes and returns removed value.
     * @param key String to remove
     * @return value that is removed
     */
    public String remove(String key) {
        if (!isWord(key)) {
            return null;
        }
        String ret = null;
        int hash = hashValue(key);
        while (arr[hash] != null) {
            if (arr[hash] != DELETED && key.equals(arr[hash].getValue())) {
                ret = arr[hash].getValue();
                arr[hash] = DELETED;
                size--;
                return ret;
            }
            hash = (hash + 1) % length;
        }
        return ret;
    }

    /**
     * Check if a number is a prime number.
     * @param n input number.
     * @return If the number is a prime number or not.
     */
    private boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if an input is a valid word.
     * @param input input word.
     * @return Whether it is valid.
     */
    private boolean isWord(String input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (!(input.charAt(i) >= 'a' && input.charAt(i) <= 'z')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the array that implements the hash table.
     * @return the array that implements the hash table.
     */
    public DataItem[] getArr() {
        return arr;
    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method. In
     * other words, you are to combine the following two steps into one step. 1.
     * converting Object into integer value 2. compress into the table using
     * modular hashing (division method) Helper method to hash a string for
     * English lowercase alphabet and blank, we have 27 total. But, you can
     * assume that blank will not be added into your table. Refer to the
     * instructions for the definition of words. For example, "cats" : 3*27^3 +
     * 1*27^2 + 20*27^1 + 19*27^0 = 60,337 But, to make the hash process faster,
     * Horner's method should be applied as follows; var4*n^4 + var3*n^3 +
     * var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as (((var4*n + var3)*n +
     * var2)*n + var1)*n + var0 Note: You must use 27 for this homework.
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        // TODO implement this
        int ret = 0;
        for (int i = 0; i < input.length(); i++) {
            ret = (ret * 27 + input.charAt(i) - 'a' + 1) % length;
        }
        return ret;
    }

    /**
     * doubles array length and rehash items whenever the load factor is
     * reached.
     */
    private void rehash() {
        // TODO implement this
        int newLength = 2 * length;
        while (!isPrime(newLength)) {
            newLength++;
        }
        System.out.println(
                "Rehashing " + size + " items, new size is " + newLength);
        MyHashTable newTable = new MyHashTable(newLength);
        for (int i = 0; i < length; i++) {
            if (arr[i] != null && arr[i] != DELETED) {
                while (arr[i].getFreq() > 0) {
                    newTable.insert(arr[i].getValue());
                    arr[i].decreaseFreq();
                }
            }
        }
        arr = newTable.getArr();
        length = newLength;
        size = newTable.size;

    }

    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        /**
         * constructor with String value.
         * @param val String value.
         */
        DataItem(String val) {
            value = val;
            frequency = 1;
        }

        /**
         * Get the frequency.
         * @return frequency of this String.
         */
        public int getFreq() {
            return frequency;
        }

        /**
         * Get the String value of this object.
         * @return The String value of this object.
         */
        public String getValue() {
            return value;
        }

        /**
         * Increase the frequency by one.
         */
        public void increaseFreq() {
            frequency++;
        }

        /**
         * Decrease the frequency by one.
         */
        public void decreaseFreq() {
            frequency--;
        }
    }
}
