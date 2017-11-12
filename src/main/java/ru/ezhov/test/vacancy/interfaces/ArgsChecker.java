package ru.ezhov.test.vacancy.interfaces;

/**
 *
 * @author rrnezh
 */
public interface ArgsChecker {

    /**
     * Проверка
     *
     * @throws IllegalArgumentException
     */
    void check() throws IllegalArgumentException;

    /**
     * Параметры для проверки
     *
     * @param args
     */
    void setArgs(String[] args);

    /**
     * Сколько параметров должно быть
     *
     * @param mustCountArgs
     */
    void setMustCountArgs(int mustCountArgs);

}
