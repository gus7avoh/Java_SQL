package br.com.alura.bytebank;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class Login {

    private ConnectionFactory connection;

    public Login(){
        this.connection = new ConnectionFactory();
    }

    public boolean cadastrar(String usuario, String senha){
        String sql = "INSERT INTO usuario (usuario, senha) VALUES(?, ?)";

        Connection conn = connection.recuperarConexao();
    
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, senha);
            preparedStatement.execute();
            return true;
        }catch(SQLException e){
            throw new RuntimeException(e);  
        }

    }
}
