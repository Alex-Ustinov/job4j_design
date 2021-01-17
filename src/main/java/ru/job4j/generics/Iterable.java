package ru.job4j.generics;

import java.util.Iterator;

public interface Iterable<T> {
    public Iterator<T> iterator();
}
