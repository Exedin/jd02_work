package it.academy.date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public ProductSpecDao getProductSpecDao () throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product",
//                "root",
//                "1234");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root",
                "root");
        return new ProductSpecDaoImpl(connection);
    }

    public static DaoFactory getInstance(DatabaseName databaseName) throws ClassNotFoundException {
        switch (databaseName){
            case MYSQL:{
                if(daoFactory==null){
                    daoFactory=new DaoFactory();
                }
                return daoFactory;
            }
            case ORACLE:{
                //TODO: not implemented
                return null;
                }
        }
        throw new IllegalArgumentException("Database name is unknown");
    }
}

