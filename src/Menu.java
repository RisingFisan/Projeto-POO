import java.util.AbstractMap;
import java.util.Scanner;

public class Menu {
    public static int MenuInicial() {
        StringBuilder sb = new StringBuilder("----------MENU INICIAL-----------\n\n");
        sb.append("1) Iniciar sessao.\n");
        sb.append("2) Registar nova conta.\n\n");
        sb.append("3) Carregar logs.\n\n");
        sb.append("4) Salvar Estado.\n\n");
        sb.append("5) Carregar Estado.\n\n");
        sb.append("0) Sair.\n\n");
        sb.append("Selecione a opcao pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
    //Tentar encontrar outra forma mais elegante
    public static void clearWindow() {
        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }

    
    public static String getEmail (boolean b){
        if (b) System.out.println("O email digitado já existe.Tente novamente. "); 
        System.out.print("Digite um e-mail de registo: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public static String getGeneralContaInfo(int i){
        if (i==1) System.out.print("Digite uma password: ");
        else if (i==2) System.out.print("Digite o seu nome: ");
        else if (i==3) System.out.print("Digite a sua coordenada em x: ");
        else if (i==4) System.out.print("Digite a sua coordenada em y: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static String getSpecificContaInfo(int i){
        if (i==1) System.out.print("Raio de Ação: ");
        else if (i==2) System.out.print("Numero maximo de encomendas a transportar: ");
        else if (i==3) System.out.print("NIF da transportadora: ");
        else if (i==4) System.out.print("Preco por KM: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    

    public static TipoConta menuRegisto() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------REGISTAR NOVA CONTA---------").append("\n");
        sb.append("Como se deseja registar?\n\n");
        sb.append("1) Utilizador\n");
        sb.append("2) Voluntario\n");
        sb.append("3) Loja\n");
        sb.append("4) Transportadora\n\n");
        sb.append("Introduza a opcao pretendida: ");

        System.out.println(sb.toString());

        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        switch(opcao) {
            case 1: return TipoConta.Utilizador;
            case 2: return TipoConta.Voluntario;
            case 3: return TipoConta.Loja;
            case 4: return TipoConta.Transportadora;
            default: return null;
        }
    }

    public static AbstractMap.SimpleEntry<String,String> menuLogin(boolean errorMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("------------INICIAR SESSÃO---------").append("\n\n");
        if(errorMessage) sb.append("Erro - Dados invalidos! Tente novamente!\n\n");
        sb.append("Introduza os seus dados.\n\n");
        sb.append("Endereço de e-mail: ");

        System.out.print(sb.toString());

        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();

        System.out.print("Palavra-passe: ");

        String password = scanner.nextLine();
        return new AbstractMap.SimpleEntry<>(email, password);
    }
    
    public static void errors(int i){
         StringBuilder sb = new StringBuilder();
        if (i==1) sb.append("****Ficheiro nao encontrado***").append("\n");
        else if (i==2) sb.append("****Não foi possível guardar o Estado***").append("\n");
        else if (i==3) sb.append("****Erro ao ler para as estruturas de dados***").append("\n");
        System.out.print(sb.toString());
    }
    
    


}
