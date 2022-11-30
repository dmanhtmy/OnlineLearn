/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author hp
 */
public interface BaseDAO<T> {
    T get(int id);

    List<T> getAll();

    boolean insert(T t);

    boolean update(T t);

    boolean delete(int id);
}
