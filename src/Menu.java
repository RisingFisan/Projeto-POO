import java.util.AbstractMap;
import java.util.Scanner;

public class Menu {
    public static int MenuInicial() {
        StringBuilder sb = new StringBuilder("----------MENU INICIAL-----------\n\n");
        sb.append("1) Iniciar sessão.\n");
        sb.append("2) Registar nova conta.\n\n");
        sb.append("0) Sair.\n\n");
        sb.append("Selecione a opção pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static TipoConta menuRegisto() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------REGISTAR NOVA CONTA---------").append("\n");
        sb.append("Como é que se deseja registar?\n\n");
        sb.append("1) Utilizador\n");
        sb.append("2) Voluntário\n");
        sb.append("3) Loja\n");
        sb.append("4) Transportadora\n\n");
        sb.append("Introduza a opção pretendida: ");

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
        if(errorMessage) sb.append("Erro - Dados inválidos! Tente novamente!\n\n");
        sb.append("Introduza os seus dados.\n\n");
        sb.append("Endereço de e-mail: ");

        System.out.print(sb.toString());

        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();

        System.out.print("Palavra-passe: ");

        String password = scanner.nextLine();
        return new AbstractMap.SimpleEntry<>(email, password);
    }


}
