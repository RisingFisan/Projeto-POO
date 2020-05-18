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

    public static Utilizador menuRegistoUtilizador() {
        Scanner scanner = new Scanner(System.in);

        StringBuilder sb = new StringBuilder("------------REGISTAR UTLIZADOR---------\n\n");
        sb.append("Introduza os dados a seguir pedidos.\n\n");
        sb.append("Nome: ");

        System.out.print(sb.toString());
        String nome = scanner.nextLine();

        System.out.print("Código da conta: ");
        String codigo = scanner.nextLine();

        System.out.print("Endereço de e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Palavra-passe: ");
        String password = scanner.nextLine();

        System.out.print("Coordenadas (separadas por um espaço, em formato decimal): ");
        Double x = scanner.nextDouble();
        Double y = scanner.nextDouble();

        return new Utilizador(codigo, nome, x , y, email, password);
    }

    public static Voluntario menuRegistoVoluntario() {
        Scanner scanner = new Scanner(System.in);

        StringBuilder sb = new StringBuilder("------------REGISTAR VOLUNTÁRIO---------\n\n");
        sb.append("Introduza os dados a seguir pedidos.\n\n");
        sb.append("Nome: ");

        System.out.print(sb.toString());
        String nome = scanner.nextLine();

        System.out.print("Código da conta: ");
        String codigo = scanner.nextLine();

        System.out.print("Endereço de e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Palavra-passe: ");
        String password = scanner.nextLine();

        System.out.print("Coordenadas (separadas por um espaço, em formato decimal): ");
        Double x = scanner.nextDouble();
        Double y = scanner.nextDouble();

        System.out.print("Raio de entrega: ");
        Double raio = scanner.nextDouble();

        return new Voluntario(codigo, nome, x , y, raio, email, password);
    }

    public static Loja menuRegistoLoja() {
        Scanner scanner = new Scanner(System.in);

        StringBuilder sb = new StringBuilder("------------REGISTAR LOJA---------\n\n");
        sb.append("Introduza os dados a seguir pedidos.\n\n");
        sb.append("Nome da loja: ");

        System.out.print(sb.toString());
        String nome = scanner.nextLine();

        System.out.print("Código da conta: ");
        String codigo = scanner.nextLine();

        System.out.print("Endereço de e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Palavra-passe: ");
        String password = scanner.nextLine();

        System.out.print("Coordenadas (separadas por um espaço, em formato decimal): ");
        Double x = scanner.nextDouble();
        Double y = scanner.nextDouble();

        return new Loja(codigo, nome, x , y, email, password);
    }

    public static Transportadora menuRegistoTransportadora() {
        Scanner scanner = new Scanner(System.in);

        StringBuilder sb = new StringBuilder("------------REGISTAR TRANSPORTADORA---------\n\n");
        sb.append("Introduza os dados a seguir pedidos.\n\n");
        sb.append("Nome da transportadora: ");

        System.out.print(sb.toString());
        String nome = scanner.nextLine();

        System.out.print("Código da conta: ");
        String codigo = scanner.nextLine();

        System.out.print("Endereço de e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Palavra-passe: ");
        String password = scanner.nextLine();

        System.out.print("Coordenadas (separadas por um espaço, em formato decimal): ");
        Double x = scanner.nextDouble();
        Double y = scanner.nextDouble();

        System.out.print("Número de Identificação Fiscal (NIF): ");
        String nif = scanner.nextLine();

        System.out.print("Raio de entrega: ");
        Double raio = scanner.nextDouble();

        System.out.print("Preço por Km: ");
        Double precoPorKm = scanner.nextDouble();

        System.out.print("Capacidade máxima de entrega (0 equivale a capacidade ilimitada): ");
        int capacidadeMax = scanner.nextInt();
        if(capacidadeMax == 0) capacidadeMax = Integer.MAX_VALUE;

        return new Transportadora(codigo, nome, x , y, nif, raio, precoPorKm, email, password, capacidadeMax);
    }
}
