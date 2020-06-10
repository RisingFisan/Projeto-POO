import java.time.Duration;
import java.util.Map;

public class ControllerVoluntario {
    public static void run(TrazAqui trazAqui){
        boolean errorMessage = false;
        boolean exit = false;
        while(!exit){
            int opcao = -1;
            String s;
            boolean disp = trazAqui.getDisp();
            while(opcao < 0 || opcao > 3) {
                opcao = Menu.menuVoluntario(disp);
            }
            switch(opcao) {
                case 1:
                    if (!disp) Menu.errors(7);
                    else {
                        int op2 = -1;
                        Map<String,Double> tp = trazAqui.transpInfo();
                        if (!tp.isEmpty()) {
                            while(op2 != 1 && op2 != 0)
                                op2 = Menu.volEncInfo(tp);
                            if (op2 == 1) {
                                String codEnc = Menu.voluntarioMenuData(2);
                                if (trazAqui.isValidCodeEnc(codEnc)) {
                                    boolean res = trazAqui.pedirTranspVol(codEnc);
                                    if (res) Menu.voluntarioMenuResult(2, codEnc);
                                    else Menu.errors(9);
                                }
                                else
                                    Menu.errors(4);
                            }
                        }
                        else Menu.errors(12);
                    }
                    break;
                    
                case 2:
                    if (!disp) {
                        Double time = trazAqui.entregaEnc();
                        if (time == -1) Menu.errors(8);
                        else Menu.voluntarioMenuResult(3, Menu.time(time));
                    }
                    else {
                        Menu.errors(12);
                    }
                    break;
                    
                case 3:
                     double classific = trazAqui.getAverageClassi();
                     Menu.voluntarioMenuResult(4,String.valueOf(classific));
                    break;
                case 0:
                    exit=true;
                    Menu.clearWindow();
                    break;
            }
        }
    }
}
