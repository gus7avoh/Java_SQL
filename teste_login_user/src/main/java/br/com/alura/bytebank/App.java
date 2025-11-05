package br.com.alura.bytebank;
public class App {
    public static void main(String[] args) throws Exception {
        Login usuario  = new Login();
        boolean verificar = usuario.cadastrar("Henrique", "senha");
        if(verificar){
            System.out.println("Usuario salvo com sucesso");
        }else{
            System.out.println("Problema ao salvar o usuario");
        }
    }
}
