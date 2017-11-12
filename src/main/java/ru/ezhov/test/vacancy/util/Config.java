package ru.ezhov.test.vacancy.util;

/**
 * Объект для конфигурирования приложения
 *
 * @author rrnezh
 */
public class Config {

    private String classDriver;
    private String url;
    private String user;
    private String pass;
    private long num;

    public Config(
            String classDriver,
            String url,
            String user,
            String pass,
            long num) {
        this.classDriver = classDriver;
        this.url = url;
        this.user = user == null ? "" : user;
        this.pass = pass == null ? "" : pass;
        this.num = num;
    }

    public String getClassDriver() {
        return classDriver;
    }

    public void setClassDriver(String classDriver) {
        this.classDriver = classDriver;
    }

    public void setPath(String path) {
        this.url = path;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public long getNum() {
        return num;
    }
}
