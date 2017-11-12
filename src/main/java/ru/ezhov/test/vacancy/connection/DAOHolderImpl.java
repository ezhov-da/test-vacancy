package ru.ezhov.test.vacancy.connection;

import ru.ezhov.test.vacancy.interfaces.DAOHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Объект для работы с БД
 *
 * @author rrnezh
 */
public class DAOHolderImpl implements DAOHolder<Connection, Long> {

    private Connection connection;

    @Override
    public void setSource(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Long toNum) throws SQLException {
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(Querys.INSERT.getQuery());) {

            for (int i = 1; i <= toNum; i++) {
                preparedStatement.clearParameters();
                preparedStatement.setLong(1, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    @Override
    public void delete() throws SQLException {
        try (Statement statement
                     = connection.createStatement();) {
            statement.execute(Querys.DELETE.getQuery());
        }
    }

    @Override
    public List<Long> select() throws SQLException {
        List<Long> longs = new ArrayList<>();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(Querys.SELECT.getQuery());) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    longs.add(resultSet.getLong(1));
                }
            }
        }
        return longs;
    }
}
