import java.util.AbstractMap;

public class Controller {
    public static void run() {
        TrazAqui trazAqui = new TrazAqui();

        boolean errorMessage = false;

        int opcao = -1;
        while(opcao < 0 || opcao > 2) {
            opcao = Menu.MenuInicial();
        }
        switch(opcao) {
            case 1:
                while(true) {
                    AbstractMap.SimpleEntry<String, String> dados = Menu.menuLogin(errorMessage);
                    if(trazAqui.login(dados.getKey(), dados.getValue())) break;
                    else errorMessage = true;
                }
                break;
            case 2:
                TipoConta tipoConta = null;
                while(tipoConta == null) tipoConta = Menu.menuRegisto();

                Conta conta;

                if(tipoConta.equals(TipoConta.Utilizador)) conta = Menu.menuRegistoUtilizador();
                else if(tipoConta.equals(TipoConta.Voluntario)) conta = Menu.menuRegistoVoluntario();
                else if(tipoConta.equals(TipoConta.Loja)) conta = Menu.menuRegistoLoja();
                else conta = Menu.menuRegistoTransportadora();

                trazAqui.registo(conta);
                break;
            case 0:
                System.exit(0);
                break;
        }

        // Aqui chamar o controlador respetivo dependendo do tipo de conta.
    }
}
