package ru.ezhov.test.vacancy.connection;

import org.junit.Ignore;
import ru.ezhov.test.vacancy.interfaces.DAOHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rrnezh
 */
@Ignore
public class DAOHolderImplTest {

    private static Connection connection;

    public DAOHolderImplTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(
                "jdbc:h2:~/test",
                "test",
                "test");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        connection.close();
    }

    @Test
    public void test() throws Exception {
        System.out.println("delete");
        DAOHolder instance = new DAOHolderImpl();
        instance.setSource(connection);
        instance.delete();

        System.out.println("insert");
        int toNum = 300;
        instance.insert(toNum);

        System.out.println("select");
        List<Integer> expResult = null;
        List<Integer> result = instance.select();
        System.out.println("count row: " + result.size());
        assertNotEquals(expResult, result);
        assertTrue(result.size() > 0);
    }

}
