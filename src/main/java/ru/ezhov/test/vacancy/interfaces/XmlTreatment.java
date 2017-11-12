package ru.ezhov.test.vacancy.interfaces;

import java.io.File;
import java.util.List;

/**
 * Работа с XML
 *
 * @author rrnezh
 */
public interface XmlTreatment {

    /**
     * Получение обычного XML
     *
     * @param nameFile
     * @param data
     * @return
     * @throws Exception
     */
    File createPlaneXML(String nameFile, List<Long> data) throws Exception;

    /**
     * Получение XML после трансформации
     *
     * @param nameFileForTransform
     * @param nameFileOut
     * @param nameFileXsl
     * @return
     * @throws Exception
     */
    File createWithXSLT(String nameFileForTransform, String nameFileOut, String nameFileXsl) throws Exception;
}
