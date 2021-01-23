package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    public Optional<Integer> searchById(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean replace(String id, T model) {
        Optional<Integer> index = searchById(id);
        if (index.isPresent()) {
            mem.add(index.get(), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Optional<Integer> index = searchById(id);
        if (index.isPresent()) {
            mem.remove(index.get());
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        Optional<Integer> index = searchById(id);
        if (index.isPresent()) {
            return mem.get(index.get());
        }
        return null;
    }
}
