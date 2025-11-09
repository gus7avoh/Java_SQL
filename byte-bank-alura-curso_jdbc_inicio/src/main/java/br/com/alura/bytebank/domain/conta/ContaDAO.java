package br.com.alura.bytebank.domain.conta;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.Set;
import java.util.HashSet;

import br.com.alura.bytebank.domain.cliente.Cliente;
import br.com.alura.bytebank.domain.cliente.DadosCadastroCliente;

public class ContaDAO {
    private Connection conn;
    ContaDAO(Connection connection){
        this.conn = connection;
    }

    public void salvar(DadosAberturaConta dadosDaConta){
        var cliente = new Cliente(dadosDaConta.dadosCliente());
        var conta = new Conta(dadosDaConta.numero(), cliente);

        String sql = "INSERT INTO conta (numero, saldo, cliente_nome, cliente_cpf, cliente_email) VALUES(?, ?, ?, ?, ?)";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql); //Linha padro para prepar a clusular sql com a conexao

            //Nesse caso queremos passar dados para o banco, entao vamos usar o set para setar os dados de acordo com o seu tipo em posicoes especificas no banco 
            preparedStatement.setInt(1, conta.getNumero());
            preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
            preparedStatement.setString(3, dadosDaConta.dadosCliente().nome());
            preparedStatement.setString(4, dadosDaConta.dadosCliente().cpf());
            preparedStatement.setString(5, dadosDaConta.dadosCliente().email());

            preparedStatement.execute(); // executando a conexao previavemente preperada

            //Quando finalizado tenho que fechar as conexoes
            preparedStatement.close();
            conn.close();
        }catch( SQLException e){
            throw new RuntimeException(e);
        }  
    }


    public Set<Conta> listar(){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        //Criando um set de retorno, poderia ser um ArrayList
        Set<Conta> contas = new HashSet();

        String sql = "SELECT * FROM conta";

        try{
            preparedStatement = conn.prepareStatement(sql);// padao para prepara a clausula sql
             //Como nesse caso nao vamos colocar dados mas sim buscalos posso so executar a clusula
             //outro ponto eh que vou usar o executeQuery, ele eh proprio para select e vai retornar um set com os dados coletados pelo banco.
            resultSet = preparedStatement.executeQuery();
           
            //A variavel result set nao retorna um formato padao de dado para o java, de forma que precisamos percorrelo e adicionalo em um set/arraylist
            while (resultSet.next()) {
                //Percorrendo todos os dados usando o resultSet.getTipo(posicao no banco)
                Integer numero =  resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String nome = resultSet.getString(3);
                String cpf = resultSet.getString(4);
                String email = resultSet.getString(5);

                //Instanciando os dados no padrao de objeto definido para retornar a estrutura correta
                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nome, cpf, email);
                Cliente cliente = new Cliente(dadosCadastroCliente);
                
                //adicionando os dados ao set se retorno definido no inicio do codigo para retornar ao programa
                contas.add(new Conta(numero, cliente));
            }
            //Fechando todas as conexoes abertas para impedir falhas no banco
            preparedStatement.close();
            resultSet.close();
            conn.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        //Retornando os dados
        return contas;
    }

    public Set<Conta> listar(int numero){

        Set<Conta> lista = new HashSet<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        
        String sql = "SELECT * FROM conta WHERE numero = ?";

        try{
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, numero);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                // Coletar todos os dados e ir colocando em variaveis no final criar o objeto e adicionalo no set

                int numeroConta = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String nome = resultSet.getString(3);
                String cpf = resultSet.getString(4);
                String email = resultSet.getString(5);

                DadosCadastroCliente dados = new DadosCadastroCliente(nome, cpf, email);
                Cliente cliente = new Cliente(dados);
                lista.add(new Conta(numeroConta, cliente));
            }

            preparedStatement.close();
            resultSet.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

}




