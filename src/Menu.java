import java.util.AbstractMap;
import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;

public class Menu {
    public static int MenuInicial() {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------MENU INICIAL-----------\n\n");
        sb.append("1) Iniciar sessao.\n");
        sb.append("2) Registar nova conta.\n");
        sb.append("3) Carregar logs.\n");
        sb.append("4) Salvar Estado.\n");
        sb.append("5) Carregar Estado.\n");
        sb.append("6) Utilizadores que mais utilizam o sistema(em número de encomendas transportadas).\n");
        sb.append("7) Empresas transportadoras que mais utilizam o sistema(em número de kms percorridos).\n");
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
    
    public static int menuUtilizador(){
        StringBuilder sb = new StringBuilder("-----------MENU UTILIZADOR-----------\n\n");
        sb.append("1) Efetuar uma compra.\n");
        sb.append("2) Solicitar entrega de uma encomenda.\n");
        sb.append("3) Verificar ofertas de transportadora.\n");
        sb.append("4) Historico de encomendas.\n");
        sb.append("5) Classificar voluntario ou transportadora.\n");
        sb.append("0) Logout.\n\n");
        sb.append("Selecione a opcao pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
    public static void maisFreq(List<String> l, String c) {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------Entidades que utilizam mais o sistema-----------\n\n");
        sb.append(c).append(":\n").append(l);
        System.out.println(sb.toString());
    }
    
    public static String userMenuData(int i){
        StringBuilder sb = new StringBuilder("----------MENU UTILIZADOR-----------\n\n");
        if (i==1)sb.append("Digite um código da entidade:\n ");
        else if (i==2) sb.append("Digite a sua avaliação(1-10):\n ");
        else if (i==3) sb.append("Digite o código de uma loja:\n ");
        else if (i==4) sb.append("Digite o numero de linhas de encomenda:\n ");
        else if (i==5) sb.append("Digite o codigo de produto:\n ");
        else if (i==6) sb.append("Digite a descricao:\n ");
        else if (i==7) sb.append("Digite a quantidade:\n ");
        else if (i==8) sb.append("Digite o preco:\n ");
        else if (i==9) sb.append("Digite o peso:\n ");
        else if (i==10) sb.append("Digite um codigo de encomenda:\n ");
        else if (i==11) sb.append("Encomenda medica?(S/N):\n ");
        else if (i==12) sb.append("Digite a velocidade media:\n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public static int menuVoluntario(boolean disp){
        StringBuilder sb = new StringBuilder("-----------MENU VOLUNTARIO-----------\n\n");
        sb.append("1) Selecionar encomenda a transportar.\n");
        sb.append("2) Registar entrega de uma encomenda.\n");
        sb.append("0) Logout.\n\n");
        sb.append("Selecione a opcao pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
    public static String voluntarioMenuData(int i){
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------MENU VOLUNTARIO-----------\n\n");
        if (i==2) sb.append("Digite um codigo de encomenda:\n ");
        else if (i==3) sb.append("Digite o codigo de uma loja:\n ");
        
        
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public static void voluntarioMenuResult(int i, String res){
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------MENU VOLUNTARIO-----------\n\n");
        if (i==1) sb.append("O voluntario esta agora ").append(res).append("\n");
        else if (i==2) sb.append("Pedido de transporte da encomenda ").append(res).append(" efetuado com sucesso!\n ");
        else if (i==3) sb.append("Entrega registada com sucesso!\n").append("Tempo de transporte: ").append(res).append("\n");
        sb.append("Pressione enter para continuar...");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        clearWindow();
    }
    
    public static int volEncInfo(Map<String,Double> encInfo) {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------MENU VOLUNTARIO-----------\n\n");
        sb.append(encInfo).append("\n");
        sb.append("1) Escolher encomenda a transportar;\n0) Retroceder;\nSelecione a opcao pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
    public static int menuTransportadora(boolean disp){
        StringBuilder sb = new StringBuilder("-----------MENU TRANSPORTADORA-----------\n\n");
        sb.append("1) Preco de transporte de uma encomenda.\n");
        sb.append("2) Registar entrega de uma encomenda.\n");
        sb.append("0) Logout.\n\n");
        sb.append("Selecione a opcao pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
    public static String transportadoraMenuData(int i){
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------MENU TRANSPORTADORA-----------\n\n");
        if (i==1) sb.append("Digite um codigo de encomenda:\n ");
        else if (i==2) sb.append("Digite o codigo de uma loja:\n ");
        
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public static void transportadoraMenuResult(int i, String res) {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------MENU TRANSPORTADORA-----------\n\n");
        if (i==2) sb.append("Pedido de transporte da encomenda ").append(res).append(" efetuado com sucesso!\n ");
        else if (i==3) sb.append("Entrega registada com sucesso!\n").append("Tempo de transporte e custo: ").append(res).append("\n");
        sb.append("Pressione enter para continuar...");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        clearWindow();
    }
    
    public static int transpEncInfo(Map<String,List<Double>> encInfo) {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------MENU TRANSPORTADORA-----------\n\n");
        sb.append(encInfo).append("\n");
        sb.append("1) Escolher encomenda a transportar;\n0) Retroceder;\nSelecione a opcao pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
     
     public static int apresentaPedidosTransportes(Map<String,List<AbstractMap.SimpleEntry <String, Double>>> transp){
        StringBuilder sb = new StringBuilder("----------MENU UTILIZADOR-----------\n\n");
        for (Map.Entry<String,List<AbstractMap.SimpleEntry <String, Double>>> a : transp.entrySet()){
            sb.append("* ").append("Encomenda: ").append(a.getKey()).append("\n ");
            for (AbstractMap.SimpleEntry <String, Double> p : a.getValue()){
                sb.append("Transportadora: ").append(p.getKey()).append ("---->").append("Custo: ").append(p.getValue()).append("\n");
                
            }
            
            
        }
        sb.append("1) Escolher uma das opcoes:\n ");
        sb.append("0) Nenhuma das opcoes:\n ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
     
    public static int dataInfo(int i,boolean b){
       StringBuilder sb = new StringBuilder();
       if (b) sb.append("*****Data de inicio***** ");
       else sb.append("*****Data de fim***** ");
        if (i==1) sb.append("Digite o ano: ");
        else if (i==2) sb.append("Digite o mes: ");
        else if (i==3) sb.append("Digite o dia: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
     
     
    public static void printHistorico(List<Encomenda>me,String entidade,LocalDateTime i,LocalDateTime f) {
        StringBuilder sb = new StringBuilder("-----------Historico de Encomendas-----------\n\n");
        sb.append("********Entidade "+entidade+"********");
        sb.append("Encomendas entre "+i.toString()+" e "+f.toString());
        me.stream().forEach(a->sb.append(a));
        System.out.println(sb.toString());
        
        
    }

    public static int menuLoja(){
        StringBuilder sb = new StringBuilder("-----------MENU LOJA-----------\n\n");
        sb.append("1) Ver lista de encomendas.\n");
        sb.append("0) Logout.\n\n");
        sb.append("Selecione a opcao pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
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
        sb.append("------------REGISTAR NOVA CONTA------------").append("\n");
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
        else if (i==2) sb.append("****Não foi possivel guardar o Estado****").append("\n");
        else if (i==3) sb.append("****Erro ao ler para as estruturas de dados****").append("\n");
        else if (i==4) sb.append("****Codigo invalido****").append("\n");
        else if (i==5) sb.append("****Datas invalidas****").append("\n");
        else if (i==6) sb.append("****Nao foi possivel carregar o Estado****").append("\n");
        else if (i==7) sb.append("****Voluntario indisponivel****").append("\n");
        else if (i==8) sb.append("****Nao existe encomenda a transportar****").append("\n");
        else if (i==9) sb.append("****A encomenda nao foi solicitada****").append("\n");
        sb.append("\nPressione enter para continuar...");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        clearWindow();
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
        
        System.out.print("Velocidade media: ");
        Double vel = scanner.nextDouble();
        
        return new Voluntario(codigo, nome, x , y, raio, email, password, vel);
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
        
        System.out.print("Velocidade media: ");
        Double vel = scanner.nextDouble();
        
        return new Transportadora(codigo, nome, x , y, nif, raio, precoPorKm, email, password, capacidadeMax, vel);
    }

    public static void encomendasListMenu(List<Encomenda> encomendas) {
        for(Encomenda e : encomendas) {
            System.out.println("Encomenda " + e.getCodEnc());
            System.out.println("Utilizador: " + e.getCodUtil());
            System.out.println("Conteúdo da encomenda:");
            for(LinhaEncomenda le : e.getProdutos().values()) {
                System.out.println("\n" + le);
            }
        }
    }
}
