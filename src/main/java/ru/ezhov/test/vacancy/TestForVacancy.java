package ru.ezhov.test.vacancy;

import java.io.IOException;

import ru.ezhov.test.vacancy.interfaces.AppExecutor;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import ru.ezhov.test.vacancy.connection.DAOHolderImpl;
import ru.ezhov.test.vacancy.interfaces.ArgsChecker;
import ru.ezhov.test.vacancy.util.ArgsCheckerImpl;
import ru.ezhov.test.vacancy.util.Config;
import ru.ezhov.test.vacancy.xml.*;

public class TestForVacancy {

    static {
        try {
            LogManager.getLogManager().readConfiguration(TestForVacancy.class.getResourceAsStream("/log.properties"));
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(TestForVacancy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        TestForVacancy testForVacancy = new TestForVacancy();
        testForVacancy.checkArgs(args);
        testForVacancy.run(args);
    }

    private void checkArgs(String[] args) {
        ArgsChecker argsChecker = new ArgsCheckerImpl();
        argsChecker.setMustCountArgs(5);
        argsChecker.setArgs(args);
        argsChecker.check();
    }

    public void run(String[] args) {
        try {
            Config config = new Config(
                    args[0],
                    args[1],
                    args[2],
                    args[3],
                    Integer.parseInt(args[4]));

            AppExecutor appExecutor = new AppExecutorImpl();
            appExecutor.setConfig(config);
            appExecutor.setXmlTreatment(new XmlTreatmentImpl());
            appExecutor.setdAOHolder(new DAOHolderImpl());
            appExecutor.setSumCalculator(
                    new XmlSumCalculatorImpl(
                            XmlConstants.CHILD_ELEMENT,
                            XmlConstants.FIELD_ELEMENT));
            appExecutor.execute();

        } catch (Exception ex) {
            Logger.getLogger(
                    TestForVacancy.class.getName()).log(
                    Level.SEVERE,
                    "Ошибка при работе приложения",
                    ex);
        }
    }
}
