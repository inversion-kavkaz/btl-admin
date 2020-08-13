package ru.inversionkavkaz.btladmin.entity.dao;

import java.sql.Connection;

public class DaoFactory {
    private static Connection connection;
    private static DaoFactory ourInstance = null;

    public static DaoFactory getInstance(Connection connection) {
        if(ourInstance==null)
            ourInstance = new DaoFactory(connection);
        return ourInstance;
    }

    private DaoFactory(Connection connection) {
        this.connection = connection;
    }

    public BtlFogDao getImpDocDbfDao() {return new BtlFogDao(connection);}
}
