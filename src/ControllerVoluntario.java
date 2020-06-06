import java.time.Duration;

public class ControllerVoluntario {
    public static void run(TrazAqui trazAqui){
        boolean errorMessage = false;
        boolean exit = false;
        while(!exit){
            int opcao = -1;
            String s;
            boolean disp = trazAqui.getDisp();
            while(opcao < 0 || opcao > 2) {
                opcao = Menu.menuVoluntario(disp);
            }
            switch(opcao) {
                case 1:
                    if (!disp) Menu.errors(7);
                    else {
                        int op2 = -1;
                        while(op2 != 1 && op2 != 0)
                            op2 = Menu.volEncInfo(trazAqui.transpInfo());
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
                    break;
                case 2:
                    if (disp) {
                        Duration time = trazAqui.entregaEnc();
                        if (time == null) Menu.errors(8);
                        else Menu.voluntarioMenuResult(3, String.valueOf(time));
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
