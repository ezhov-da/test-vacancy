package ru.ezhov.test.vacancy.util;

import java.util.Map;
import ru.ezhov.test.vacancy.interfaces.ArgsChecker;

/**
 * Проверка входных параметров
 *
 * @author rrnezh
 */
public class ArgsCheckerImpl implements ArgsChecker {

    private int mustCountArgs;
    private String[] args;
    private Map<Integer, Class> mapType;

    @Override
    public void setMustCountArgs(int mustCountArgs) {
        this.mustCountArgs = mustCountArgs;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public void check() throws IllegalArgumentException {
        if (args == null || args.length != mustCountArgs) {

            String text = String.format(
                    "Некорректное количество входных параметров. Допустимо: %s",
                    mustCountArgs);
            throw new IllegalArgumentException(text);
        }
    }
}
