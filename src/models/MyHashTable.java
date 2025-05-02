package models;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }


        @Override
        public String toString() {
            return "{" + key + "=" + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = (HashNode<K, V>[]) new HashNode[M];
        size = 0;
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = (HashNode<K, V>[]) new HashNode[M];
        size = 0;
    }

    public int getM(){
        return M;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }


    public void put(K key, V value) {
        int index = hash(key) % M;
        if (chainArray[index] == null) {
            chainArray[index] = new HashNode<>(key, value);
            size++;
        } else {
            HashNode<K, V> root = chainArray[index];
            while (true) {
                if (root.key.equals(key)) {
                    root.value = value;
                    return; // не увеличиваем size
                }
                if (root.next == null) {
                    root.next = new HashNode<>(key, value);
                    size++;
                    return;
                }
                root = root.next;
            }
        }
    }


    public V get(K key) {
        HashNode<K, V> current = chainArray[hash(key) % M];
        while (current != null) {
            if (current.key.equals(key)) return current.value;
            current = current.next;
        }
        return null;
    }



    public V remove(K key) {
        V value = null;
        HashNode<K, V> current = chainArray[hash(key) % M];
        HashNode<K, V> previous = null;
        while (current != null) {
            if (current.key.equals(key)) {
                value = current.value;
                if (previous == null && current.next == null) {
                    chainArray[hash(key) % M] = null;
                } else if (previous == null && current.next != null) {
                    chainArray[hash(key) % M] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return value;
            }
            previous = current;
            current = current.next;
        }

        return null;
    }


    public boolean contains(K key) {
        int index = hash(key) % M;
        HashNode<K, V> current = chainArray[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }


    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }

        return null;
    }

    public int getBucketSize(int index) {
        HashNode<K, V> current = chainArray[index];
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }

        return size;
    }
}
