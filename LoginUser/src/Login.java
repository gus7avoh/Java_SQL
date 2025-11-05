import java.sql.PreparedStatement;
import java.sql.SQLDataException;

public class Login {

    private ConnectionFactory connection;

    public Login(){
        this.connection = new ConnectionFactory();
    }

    public boolean cadastrar(String usuario, String senha){
        String sql = "INSERT INTO usuario (usuario, senha) VALUES(?, ?)";

        Connection conn = connection.recuperarConexao();
    
        try{
            PreparedStatement preparedStatement = conn.preparedStatement(sql);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, senha);

            preparedStatement.execute();
            
        }catch(SQLDataException e){
            throw new RuntimeException(e);
        }
    }
}
