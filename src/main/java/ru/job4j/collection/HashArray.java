package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashArray<K, V> implements Iterable<V> {
    private Entry<K, V>[] table;
    private int modCount = 0;
    private int count = 0;
    private double loadFactor = 0.75;

    HashArray(double loadFactor) {
        this.loadFactor = loadFactor;
    }

    private int getIndex(K key, int size) {
        int keyHash = Objects.hash(key);
        int index = keyHash % size;
        return index;
    }

    public void expandTable() {
        Entry[] newTable = new Entry[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            int newIndex = getIndex(table[i].key, newTable.length);
            if (table[i] != null) {
                newTable[newIndex] = table[i];
            }
        }
        table = newTable;
    }

    public boolean insert(K key, V value) {
        if (count / table.length >= loadFactor) {
            expandTable();
        }
        int newIndex = getIndex(key, table.length - 1);
        Entry basket = new Entry(key, value);
        if (table[newIndex] == null) {
            table[newIndex] = basket;
            modCount++;
            count++;
            return true;
        }
        return false;
    }

    public V get(K key) {
        int index = getIndex(key, table.length - 1);
        if (table[index] != null) {
            if (Objects.equals(table[index].getKey(), key)) {
                return table[index].getValue();
            }
        }
        return null;
    }

    public boolean delete(K key) {
        int index = getIndex(key, table.length - 1);
        if (table[index] != null) {
            if (Objects.equals(table[index].getKey(), key)) {
                table[index] = null;
                modCount++;
                count--;
                return true;
            }
        }
        return false;
    }

    static class Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.value = value;
            this.key = key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new InnerIterator();
    }

    class InnerIterator implements Iterator<K> {
        private int define = modCount;
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return table.length > cursor && table[cursor].getValue() != null;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (define != modCount) {
                throw new ConcurrentModificationException();
            }
            K result = table[cursor].getKey();
            cursor++;
            return result;
        }
    }
}
