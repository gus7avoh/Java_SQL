package br.com.alura.bytebank;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {

    public Connection recuperarConexao(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/byte_bank?user=root&password=root");
            return connection;
            
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
