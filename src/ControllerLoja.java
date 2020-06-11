import java.time.Duration;
import java.util.List;

public class ControllerLoja {
    public static void run(TrazAqui trazAqui) {
        boolean errorMessage = false;
        boolean exit = false;
        while(!exit){
            int opcao = -1;
            while(opcao < 0 || opcao > 2) {
                opcao = Menu.menuLoja();
            }
            switch(opcao) {
                case 1:
                    List<Encomenda> encomendas = trazAqui.listaEncsLoja();
                    Menu.encomendasListMenu(encomendas);
                    break;
                case 2:
                     String enc = Menu.LojaMenuData(1);
                     double time;
                     if (trazAqui.checkEncInStore(enc)) {
                         time = trazAqui.getTempoEspera(enc);
                         Menu.LojaMenuRes(1,String.valueOf(time));
                        }
                     else Menu.errors(17);   
                break;
                case 0:
                    exit=true;
                    Menu.clearWindow();
                    break;
            }
        }
    }
}
