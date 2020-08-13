package ru.inversionkavkaz.btladmin.entity.dao;

import java.sql.Connection;

public class BaseDao {
    public enum MODE { INSERT("INS"), UPDATE("UPD"), DELETE("DEL");
        public String val;
        MODE(String val) {
            this.val = val;
        }
    };
    private Connection connection;
    public BaseDao(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnection() {
        return connection;
    }
}
