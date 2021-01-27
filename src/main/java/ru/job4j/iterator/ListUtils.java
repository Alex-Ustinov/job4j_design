package ru.job4j.iterator;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();

        for (int i = 0; i < list.size(); i++) {
            if (i == index - 1) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }

        /*
        while (iterator.hasNext()) {
            if (iterator.previousIndex() == index - 1) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
        */
    }

    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        return Stream.of(list)
                .flatMap(el -> el.stream())
                .filter(filter)
                .collect(Collectors.toList());
    }

    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        return Stream.of(list)
                .flatMap(el -> el.stream())
                .filter(filter)
                .map(el -> value)
                .collect(Collectors.toList());
    }

    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        return Stream.of(list)
                .flatMap(item -> item.stream())
                .filter(el -> !elements.contains(el))
                .collect(Collectors.toList());
    }

}
