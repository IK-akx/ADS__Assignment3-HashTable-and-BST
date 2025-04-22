package models.interfaces;

public interface IMyHashTable<K, V> {
    public void put(K key, V value);
    public V get(K key);
    public V remove(K key);
    public boolean contains(K key);
    public K getKey(V value);
}
