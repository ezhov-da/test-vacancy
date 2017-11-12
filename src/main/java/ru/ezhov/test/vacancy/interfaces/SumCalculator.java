package ru.ezhov.test.vacancy.interfaces;

/**
 * Получение суммы откуда угодно
 *
 * @author rrnezh
 * @param <T> - источник
 */
public interface SumCalculator<T> {

    /**
     * Источник получения суммы
     *
     * @param source
     */
    void setSource(T source);

    /**
     * Получение суммы
     *
     * @return
     * @throws Exception
     */
    long getSum() throws Exception;
}
