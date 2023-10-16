package com.a00n.dao;

import java.util.List;

/**
 *
 * @author ay0ub
 * @param <T>
 */
public interface IDao<T> {

    boolean create(T o);

    boolean delete(T o);

    boolean update(T o);

    List<T> findAll();

    T findById(int id);
}
