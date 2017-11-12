package ru.ezhov.test.vacancy.connection;

/**
 * Перечисление с хранением запросов
 *
 * @author rrnezh
 */
public enum Querys {
    DELETE("truncate table TEST"),
    SELECT("select FIELD  from TEST"),
    INSERT("insert into TEST(FIELD) values (?)");

    private final String query;

    private Querys(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
