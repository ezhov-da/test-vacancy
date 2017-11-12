package ru.ezhov.test.vacancy.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс для доступа к данным из БД
 *
 * @author rrnezh
 * @param <T> - источник данных
 * @param <E> - данные для внесения и получения
 */
public interface DAOHolder<T, E> {

    /**
     * Источник данных
     *
     * @param source
     */
    void setSource(T source);

    /**
     * Удаление данных
     *
     * @throws SQLException
     */
    void delete() throws SQLException;

    /**
     * Вставка данных
     *
     * @param toNum
     * @throws SQLException
     */
    void insert(E toNum) throws SQLException;

    /**
     * Получение данных
     *
     * @return
     * @throws SQLException
     */
    List<E> select() throws SQLException;

}
