import java.sql.DriverManager;
import java.sql.SQLException;
import javax.management.RuntimeErrorException;
import java.sql.Connection;

public class ConnectionFactory {
    public Connection recuperarConexao(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_sistem?user=root&password=root");
            return connection;
        }catch(SQLException e){
            throw new RuntimeErrorException(e);
        }
    }
}
