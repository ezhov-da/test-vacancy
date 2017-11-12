package ru.ezhov.test.vacancy;

import ru.ezhov.test.vacancy.interfaces.DAOHolder;
import ru.ezhov.test.vacancy.util.Config;
import ru.ezhov.test.vacancy.interfaces.*;
import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.logging.*;
import ru.ezhov.test.vacancy.connection.ConnectionHolder;
import ru.ezhov.test.vacancy.interfaces.XmlTreatment;

/**
 * Основной класс для работы приложения
 *
 * @author rrnezh
 */
public class AppExecutorImpl implements AppExecutor {

    private static final Logger LOG = Logger.getLogger(AppExecutorImpl.class.getName());

    private static final String NAME_FIRST_FILE = "1.xml";
    private static final String NAME_SECOND_FILE = "2.xml";
    private static final String NAME_XSL_FILE = "test.xsl";

    private Config config;
    private DAOHolder dAOHolder;
    private XmlTreatment xmlTreatment;
    private SumCalculator sumCalculator;

    @Override
    public void execute() throws Exception {

        long startApp = System.currentTimeMillis();

        try (Connection connection
                = new ConnectionHolder().getConnection(config);) {

            dAOHolder.setSource(connection);
            List<Long> longs = executeDAO();
            File file = executeXml(longs);
            long sum = getSum(file);

            long stopApp = System.currentTimeMillis();

            String finalText
                    = "~Приложение завершило работу~\n"
                    + "Итог:\n"
                    + "\tСУММА: {0}\n"
                    + "\tЗатраченное время выполнения для {1} строк - {2} секунд.";
            LOG.log(
                    Level.INFO,
                    finalText,
                    new Object[]{
                        sum,
                        config.getNum(),
                        (stopApp - startApp) / 1000});
        }
    }

    private List<Long> executeDAO() throws SQLException {
        deleteFromBase();
        insertToBase();
        List<Long> longs = getDataFromBase();
        return longs;
    }

    private File executeXml(List<Long> longs) throws Exception {
        createPlainXml(longs);
        File file = createXmlFromXsl();
        return file;
    }

    private void deleteFromBase() throws SQLException {
        LOG.log(Level.INFO, "Удаление данных...");
        dAOHolder.delete();
        LOG.log(Level.INFO, "Удаление данных завершено");
    }

    private void insertToBase() throws SQLException {
        LOG.log(Level.INFO, "Вставка данных...");
        dAOHolder.insert(config.getNum());
        LOG.log(Level.INFO, "Вставка данных завершена");
    }

    private List<Long> getDataFromBase() throws SQLException {
        LOG.log(Level.INFO, "Получение данных...");
        List<Long> list = dAOHolder.select();
        LOG.log(Level.INFO, "Данные получены");
        return list;
    }

    private File createPlainXml(List<Long> longs) throws Exception {
        LOG.log(Level.INFO, "Создание файла [{0}]...", NAME_FIRST_FILE);
        File file = xmlTreatment.createPlaneXML(NAME_FIRST_FILE, longs);
        LOG.log(Level.INFO, "Файл [{0}] создан", file.getAbsolutePath());
        return file;
    }

    private File createXmlFromXsl() throws Exception {
        LOG.log(Level.INFO, "Создание файла [{0}] из XSL [{1}]...",
                new Object[]{NAME_SECOND_FILE, NAME_XSL_FILE});
        File file = xmlTreatment.createWithXSLT(
                NAME_FIRST_FILE,
                NAME_SECOND_FILE,
                NAME_XSL_FILE);
        LOG.log(Level.INFO, "Файл [{0}] создан", file.getAbsolutePath());
        return file;
    }

    private long getSum(File fileParseForSum) throws Exception {
        LOG.log(Level.INFO, "Начат подсчет суммы...");
        sumCalculator.setSource(fileParseForSum);
        long l = sumCalculator.getSum();
        LOG.log(Level.INFO, "Подсчет суммы окончен");
        return l;
    }

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public void setdAOHolder(DAOHolder dAOHolder) {
        this.dAOHolder = dAOHolder;
    }

    @Override
    public void setXmlTreatment(XmlTreatment xmlTreatment) {
        this.xmlTreatment = xmlTreatment;
    }

    @Override
    public void setSumCalculator(SumCalculator sumCalculator) {
        this.sumCalculator = sumCalculator;
    }
}
