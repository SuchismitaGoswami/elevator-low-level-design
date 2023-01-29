package org.suchismita.repositories;

import java.util.ArrayList;

public interface IRepository<T> {
    void add(T model);
    void delete(T model);
    void update(int id, T model);
    ArrayList<T> getAll();
    T getById(int id);

}
