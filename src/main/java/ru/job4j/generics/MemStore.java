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
        if (searchById(id).isPresent()) {
            mem.add(searchById(id).get(), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (searchById(id).isPresent()) {
            mem.remove(searchById(id).get());
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        if (searchById(id).isPresent()) {
            return mem.get(searchById(id).get());
        }
        return null;
    }
}
