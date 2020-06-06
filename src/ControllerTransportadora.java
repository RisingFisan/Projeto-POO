import java.time.Duration;
import java.util.Map;

public class ControllerTransportadora {
    public static void run(TrazAqui trazAqui){
        boolean errorMessage = false;
        boolean exit = false;
        while(!exit){
            int opcao = -1;
            boolean disp = trazAqui.getDisp();
            while(opcao < 0 || opcao > 2) {
                opcao = Menu.menuTransportadora(disp);
            }
            switch(opcao) {
                case 1:
                    if (!disp) Menu.errors(7);
                    else {
                        int op2 = -1;
                        while(op2 != 1 && op2 != 0)
                            op2 = Menu.transpEncInfo(trazAqui.transpInfoT());
                        if (op2 == 1) {
                            String codEnc = Menu.transportadoraMenuData(2);
                            if (trazAqui.isValidCodeEnc(codEnc)) {
                                boolean res = trazAqui.pedirTranspT(codEnc);
                                if (res) Menu.transportadoraMenuResult(2, codEnc);
                                else Menu.errors(9);
                            }
                            else
                                Menu.errors(4);
                        }
                    }
                    break;
                case 2:
                    if (disp) {
                        String codEnc = Menu.transportadoraMenuData(2);
                        if (trazAqui.isValidCodeEnc(codEnc)) {
                            Map.Entry<Duration,Double> p = trazAqui.entregaEnc(codEnc);
                            if (p != null) Menu.transportadoraMenuResult(3, String.valueOf(p));
                            else Menu.errors(9);
                        }
                        else
                            Menu.errors(4);
                    }
                    else {
                        Menu.errors(7);
                    }
                    break;
                case 0:
                    exit=true;
                    Menu.clearWindow();
                    break;
            }
        }
    }
}
