package ru.ezhov.test.vacancy.interfaces;

import ru.ezhov.test.vacancy.util.Config;

/**
 * Основной интерфейс для обработчика
 *
 * @author rrnezh
 */
public interface AppExecutor {

    /**
     * Выполнениеt
     *
     * @throws Exception
     */
    void execute() throws Exception;

    /**
     * Установка настроек
     *
     * @param config
     */
    void setConfig(Config config);

    /**
     * XML обработчик
     *
     * @param xmlTreatment
     */
    void setXmlTreatment(XmlTreatment xmlTreatment);

    /**
     * Работа с БД
     *
     * @param dAOHolder
     */
    void setdAOHolder(DAOHolder dAOHolder);

    /**
     * Получение Суммы
     *
     * @param sumCalculator
     */
    void setSumCalculator(SumCalculator sumCalculator);

}
