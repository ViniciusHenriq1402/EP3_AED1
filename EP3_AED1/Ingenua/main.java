package EP3_AED1.Ingenua;

import java.io.File;



public class main {
    public static void main(String[] args) {
        System.out.println("Inicio programa - Busca Ingenua");
        
        //pasta verificar
        File folder1 = new File(args[0]);
        
        //pasta entrada
        File folder2 = new File(args[1]);
        
        processa listFiles = new processa();
        listFiles.carrega(folder1, folder2);
        
        System.out.println("Log criado com sucesso!");
    }
}