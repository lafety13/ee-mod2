package ua.goit.java.jdbc.views;

import java.util.List;

public class Output {

    public static <T> void printEntity(List<T> entity) {
        entity.forEach(System.out::println);
    }

    public static <T> void printEntity(T entity) {
        System.out.println(entity);
    }
}
