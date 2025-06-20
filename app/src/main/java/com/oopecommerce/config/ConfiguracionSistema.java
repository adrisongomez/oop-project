package com.oopecommerce.config;

/**
 * Gestion central de configuraciones utilizando el patron Singleton.
 */
public class ConfiguracionSistema {
    private static ConfiguracionSistema instance;

    private String dbUrl;
    private String dbUsuario;
    private String dbPassword;
    private String uiTheme;

    private ConfiguracionSistema() {
        this.dbUrl = "jdbc:postgresql://localhost:5432/oop";
        this.dbUsuario = "oopuser";
        this.dbPassword = "secret";
        this.uiTheme = "light";
    }

    public static synchronized ConfiguracionSistema getInstance() {
        if (instance == null) {
            instance = new ConfiguracionSistema();
        }
        return instance;
    }

    public String getDbUrl() { return dbUrl; }
    public void setDbUrl(String dbUrl) { this.dbUrl = dbUrl; }

    public String getDbUsuario() { return dbUsuario; }
    public void setDbUsuario(String dbUsuario) { this.dbUsuario = dbUsuario; }

    public String getDbPassword() { return dbPassword; }
    public void setDbPassword(String dbPassword) { this.dbPassword = dbPassword; }

    public String getUiTheme() { return uiTheme; }
    public void setUiTheme(String uiTheme) { this.uiTheme = uiTheme; }
}
