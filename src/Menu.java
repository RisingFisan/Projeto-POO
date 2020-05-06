public class Menu {
    public void MenuInicial() {
        StringBuilder sb = new StringBuilder("----------MENU INICIAL-----------\n\n");
        sb.append("1) Registar-se.\n");
        sb.append("2) Iniciar sessão.\n");
        sb.append("Selecione a opção pretendida: ");
        System.out.println(sb.toString());
    }

    public void menuRegisto() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------REGISTO NA APP---------").append("\n");
        sb.append("Como é que se deseja registar? ").append("\n\n");
        sb.append("1) Utilizador").append("\n");
        sb.append("2) Voluntário ").append("\n");
        sb.append("3) Empresa ").append("\n");
        sb.append("4) Transportadora").append("\n");

        System.out.println(sb.toString());
    }


}
