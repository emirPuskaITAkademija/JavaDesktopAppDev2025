package com.itakademija.three.sport.dao;

import java.util.List;

public interface Dao<E> {
    boolean save(E e);

    void update(E e);

    void delete(E e);

    E findById(int id);

    List<E> findAll();
}
