package ru.ezhov.test.vacancy.xml;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.ezhov.test.vacancy.interfaces.SumCalculator;

/**
 * Реализация подсчета суммы из XML
 *
 * @author rrnezh
 */
public class XmlSumCalculatorImpl
        extends DefaultHandler
        implements SumCalculator<File> {

    private long sum = 0;

    private final String entryValue;
    private final String fieldValue;
    private File fileParse;

    public XmlSumCalculatorImpl(
            String entryValue,
            String fieldValue) {
        this.entryValue = entryValue;
        this.fieldValue = fieldValue;
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        if (qName.equals(entryValue)) {
            sum += Long.parseLong(attributes.getValue(fieldValue));
        }
    }

    @Override
    public long getSum() throws Exception {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser sAXParser = parserFactory.newSAXParser();
        sAXParser.parse(fileParse, this);
        return sum;
    }

    @Override
    public void setSource(File source) {
        fileParse = source;
    }

}
