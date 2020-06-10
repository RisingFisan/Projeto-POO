import java.time.Duration;
import java.util.Map;
import java.util.AbstractMap;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.List;

public class ControllerTransportadora {
    public static void run(TrazAqui trazAqui){
        boolean errorMessage = false;
        boolean exit = false;
        while(!exit){
            int opcao = -1;
            boolean disp = trazAqui.getDisp();
            while(opcao < 0 || opcao > 5) {
                opcao = Menu.menuTransportadora(disp);
            }
            switch(opcao) {
                case 1:
                    Set<String> s  = trazAqui.encsPossibleToTransp();
                    String codEnc = Menu.transportadoraMenuData(1,s.toString());
                    if (trazAqui.isValidCodeEnc(codEnc)) {
                        if (trazAqui.podeTransportarT(codEnc)){
                            double preco = trazAqui.precoTransporte(codEnc);
                            Menu.transportadoraMenuResult(1, String.valueOf(preco));
                        }
                        else Menu.errors(13);
                    }
                    else Menu.errors(4);
                    break;
                case 2:
                
                    if (!trazAqui.getIsEmptyEncsT()) {
                            Map<String,AbstractMap.SimpleEntry<Double, Double>> p = trazAqui.entregaEncs();
                            if (p != null) Menu.mostrarTabelaTransportadora(p);
                            else Menu.errors(10);
                        
                    }
                    else {
                        Menu.errors(12);
                    }
                    break;
                case 3:
                    int ano = -1;
                        while (ano < 0) ano = Menu.dataInfo(1, true);
                        int mes = -1;
                        while (mes < 0 || mes > 12) mes = Menu.dataInfo(2, true);
                        int dia = -1;
                        while (dia < 0 || dia > 31) dia = Menu.dataInfo(3, true);
                        LocalDateTime inicio = LocalDateTime.of(ano, mes, dia, 0, 0, 0);

                        int ano1 = -1;
                        while (ano1 < 0) ano1 = Menu.dataInfo(1, false);
                        int mes1 = -1;
                        while (mes1 < 0 || mes1 > 12) mes1 = Menu.dataInfo(2, false);
                        int dia1 = -1;
                        while (dia1 < 0 || dia1 > 31) dia1 = Menu.dataInfo(3, false);
                        LocalDateTime fim = LocalDateTime.of(ano1, mes1, dia1, 0, 0, 0);

                   if (inicio.isAfter(fim)) Menu.errors(5);
                   else {
                       double fat = trazAqui.faturamentoEntreDatas(inicio,fim);
                       Menu.transportadoraMenuResult(4, String.valueOf(fat));
                    }
                        
                        
                case 4:
                  if (!trazAqui.getDisp()) Menu.errors(7);
                  else{
                  Map<String,List<Double>> map = trazAqui.transpInfoT();
                  if (map.isEmpty()) Menu.errors(14);
                  else{
                  int op = -1;
                  while (op!=1 && op!=0) op = Menu.transpEncInfo(map);
                    if (op==1) {
                        String codEnc1 = Menu.transportadoraMenuData(1,null);
                        if (!map.containsKey(codEnc1)) Menu.errors(4);
                        else {
                            trazAqui.addToPedidosTransp(codEnc1);
                            Menu.extraNotes(1);
                            Menu.pressEnter();
                             }
                    }
                }
                    
                 }
                Menu.clearWindow();
                break;
                
                case 5:
                double classific = trazAqui.getAverageClassi();
                Menu.transportadoraMenuResult(5,String.valueOf(classific));
                break;
                
                case 0:
                    exit=true;
                    Menu.clearWindow();
                    break;
            }
        }
    }
}
