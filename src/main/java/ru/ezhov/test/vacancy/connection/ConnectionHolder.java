package ru.ezhov.test.vacancy.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import ru.ezhov.test.vacancy.util.Config;

/**
 * Получение подключения
 *
 * @author rrnezh
 */
public class ConnectionHolder {

    public Connection getConnection(Config config) throws Exception {
        Class.forName(config.getClassDriver());
        return DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPass());
    }
}
