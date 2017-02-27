package ua.goit.java.jdbc.models.jdbc;

import java.util.List;

public interface DAO<E> {
    List<E> getAll();
    E getById(int id);
    E update(int entity, String name, float salary);
    boolean delete(int id);
    boolean create(E entity);
}
