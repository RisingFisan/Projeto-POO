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
                //errorMessage = false;
                break;
                
            case 2:
                TipoConta tipoConta = null;
                while(tipoConta == null) tipoConta = Menu.menuRegisto();

                Conta conta= null;
                String email= "";
               while(true) {
                    email = Menu.getEmail(errorMessage);
                    if(!trazAqui.existeEmail(email)) break;
                    else errorMessage = true;
                }
                String password = Menu.getGeneralContaInfo(1);
                String nome = Menu.getGeneralContaInfo(2);
                double x = Double.valueOf(Menu.getGeneralContaInfo(3));
                double y = Double.valueOf(Menu.getGeneralContaInfo(4));
                String code = trazAqui.getNewCode(tipoConta);
                if (tipoConta.equals(TipoConta.Utilizador)) conta = new Utilizador(code,nome,x,y,email,password);
                
                else if (tipoConta.equals(TipoConta.Voluntario)){
                    double raioV = Double.valueOf(Menu.getSpecificContaInfo(1));
                    conta = new Voluntario(code,nome,x,y,raioV,email,password);
                }
                else if (tipoConta.equals(TipoConta.Loja)) conta = new Loja(code,nome,x,y,email,password);
                
                else if (tipoConta.equals(TipoConta.Transportadora)){
                    double raioT = Double.valueOf(Menu.getSpecificContaInfo(1));
                    int max = Integer.valueOf(Menu.getSpecificContaInfo(2));
                    String nif = Menu.getSpecificContaInfo(3);
                    double preco = Double.valueOf(Menu.getSpecificContaInfo(4));
                    conta = new Transportadora(code,nome,x,y,nif, raioT,preco,max);
                }
                trazAqui.registo(conta);
                break;
            case 0:
                System.exit(0);
                break;
        }
    }
}
