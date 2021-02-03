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
            newTable[newIndex] = table[i];
        }
        table = newTable;
    }

    public boolean insert(K key, V value) {
        if (table.length - 1 == count || count / table.length >= 0.75) {
            expandTable();
        }
        int newIndex = getIndex(key, table.length - 1);
        Entry basket = new Entry(key, value);
        table[newIndex] = basket;
        modCount++;
        count++;
        return true;
    }

    public V get(K key) {
        int index = getIndex(key, table.length - 1);
        if (Objects.equals(table[index].getKey(), key)) {
            return table[index].getValue();
        }
        return null;
    }

    public boolean delete(K key) {
        int index = getIndex(key, table.length - 1);
        if (Objects.checkIndex(index, table.length) > 0) {
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

    class InnerIterator implements Iterator<V> {

        private int define = modCount;
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return table.length > cursor;
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (define != modCount) {
                throw new ConcurrentModificationException();
            }
            V result = table[cursor].getValue();
            cursor++;
            return result;
        }
    }
}