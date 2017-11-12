package ru.ezhov.test.vacancy.xml;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;
import ru.ezhov.test.vacancy.interfaces.SumCalculator;
import static org.junit.Assert.*;

/**
 * Тестируем сумму
 *
 * @author rrnezh
 */
@Ignore
public class XmlSumCalculatorImplTest {

    @Test
    public void testGetSum() throws Exception {

        System.out.println("getSum");
        SumCalculator instance
                = new XmlSumCalculatorImpl(
                        XmlConstants.CHILD_ELEMENT,
                        XmlConstants.FIELD_ELEMENT);

        instance.setSource(new File("2.xml"));

        long expResult = 0L;
        long result = instance.getSum();
        System.out.println("сумма: " + result);
        assertNotEquals(expResult, result);
    }

}
