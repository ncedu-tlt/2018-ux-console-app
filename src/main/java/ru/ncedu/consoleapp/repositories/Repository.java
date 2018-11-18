package ru.ncedu.consoleapp.repositories;

import java.util.List;

public interface Repository<T> {
    List<T> get();
    T get(long id);
    T add(T object);
    T update(T object);
    boolean remove(T object);
    boolean remove(long id);
}
