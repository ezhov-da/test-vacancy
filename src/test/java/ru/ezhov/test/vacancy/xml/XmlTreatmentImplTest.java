package ru.ezhov.test.vacancy.xml;

import org.junit.*;
import ru.ezhov.test.vacancy.interfaces.XmlTreatment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Тестирование работы с XML
 *
 * @author rrnezh
 */
@Ignore
public class XmlTreatmentImplTest {

    @Test
    public void test() throws Exception {
        testCreatePlaneXML();
        testCreateWithXSLT();
    }

    /**
     * Test of createPlaneXML method, of class XmlTreatmentImpl.
     */
    public void testCreatePlaneXML() throws Exception {
        System.out.println("createPlaneXML");

        List<Long> longs = new ArrayList<>();
        for (long i = 1; i <= 3000000; i++) {
            longs.add(i);
        }
        XmlTreatment instance = new XmlTreatmentImpl();
        File expResult = null;
        File result = instance.createPlaneXML("1.xml", longs);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of createWithXSLT method, of class XmlTreatmentImpl.
     */
    public void testCreateWithXSLT() throws Exception {
        System.out.println("createWithXSLT");
        XmlTreatment instance = new XmlTreatmentImpl();
        File expResult = null;
        File result = instance.createWithXSLT("1.xml", "2.xml", "temp.xsl");
        assertNotEquals(expResult, result);
    }
}
