package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (mem.indexOf(id) > 0) {
            //mem.add(id, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public T findById(String id) {
        return null;
    }
}
