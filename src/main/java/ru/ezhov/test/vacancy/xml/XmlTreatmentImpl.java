package ru.ezhov.test.vacancy.xml;

import ru.ezhov.test.vacancy.interfaces.XmlTreatment;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.AccessDeniedException;
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Обработчик XML
 *
 * @author rrnezh
 */
public class XmlTreatmentImpl implements XmlTreatment {

    @Override
    public File createPlaneXML(String nameFile, List<Long> longs) throws Exception {

        File file = new File(nameFile);

        checkDerictory(file);
        deleteFile(file);

        try (FileWriter fileWriter = new FileWriter(file);) {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter documentWtiter
                    = xmlOutputFactory.createXMLStreamWriter(fileWriter);

            documentWtiter.writeStartDocument();
            documentWtiter.writeStartElement(XmlConstants.ROOT_ELEMENT);

            for (Long i : longs) {
                documentWtiter.writeStartElement(XmlConstants.CHILD_ELEMENT);
                documentWtiter.writeStartElement(XmlConstants.FIELD_ELEMENT);
                documentWtiter.writeCharacters(String.valueOf(i));
                documentWtiter.writeEndElement();
                documentWtiter.writeEndElement();
            }

            documentWtiter.writeEndElement();
            documentWtiter.writeEndDocument();
            documentWtiter.close();
        }
        return file;
    }

    private void checkDerictory(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Не поддерживается работа с папками, укажите путь к файлу");
        }
    }

    private void deleteFile(File fileDelete) throws AccessDeniedException {
        if (fileDelete.exists()) {
            boolean isDelete = fileDelete.delete();
            if (!isDelete) {
                throw new AccessDeniedException(
                        "Не удалось удалить файл: "
                        + fileDelete.getAbsolutePath()
                        + ", возможно файл занят другим процессом.");
            }
        }
    }

    @Override
    public File createWithXSLT(
            String nameFileForTransform,
            String nameFileOut,
            String nameFileXsl) throws Exception {

        File fileTransform = new File(nameFileForTransform);
        File fileOut = new File(nameFileOut);
        File fileXsl = new File(nameFileXsl);

        checkDerictory(fileXsl);
        checkDerictory(fileTransform);
        checkDerictory(fileOut);
        deleteFile(fileOut);

        TransformerFactory tFactory = TransformerFactory.newInstance();
        StreamSource streamXsl = new StreamSource(fileXsl);
        StreamSource streamIn = new StreamSource(fileTransform);
        StreamResult streamResult = new StreamResult(fileOut);
        Transformer transformer
                = tFactory.newTransformer(streamXsl);
        transformer.transform(streamIn, streamResult);
        return fileOut;
    }

}
