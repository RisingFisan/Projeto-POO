import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerUtilizador {
    public static void run(TrazAqui trazAqui) {
        //boolean errorMessage = false;
        boolean exit = false;
        while (!exit) {
            int opcao = -1;
            while (opcao < 0 || opcao > 5) {
                opcao = Menu.menuUtilizador();
            }

            switch (opcao) {
                case 1:
                    String loja = Menu.userMenuData(3);
                    if (!trazAqui.isValidoLoja(loja)) {
                        Menu.errors(4);
                        loja = Menu.userMenuData(3);
                    }
                    String codEnc = trazAqui.getNewCodeEnc();
                    double peso = Double.parseDouble(Menu.userMenuData(9));
                    String codUt = trazAqui.getCodLoggedIn();
                    Encomenda e = new Encomenda(codEnc, codUt, loja, peso);
                    int numLinhas = -1;
                    while (numLinhas < 0) numLinhas = Integer.parseInt(Menu.userMenuData(4));
                    while (numLinhas > 0) {
                        String codP = Menu.userMenuData(5);
                        String desc = Menu.userMenuData(6);
                        double quant = Double.parseDouble(Menu.userMenuData(7));
                        double preco = Double.parseDouble(Menu.userMenuData(8));
                        numLinhas--;
                        LinhaEncomenda l = new LinhaEncomenda(codP, desc, quant, preco);
                        e.addProduto(l);
                    }
                    trazAqui.addEncToEstado(e);
                    Menu.clearWindow();
                    break;

                case 2:
                    String codEnc1 = Menu.userMenuData(10);
                    if (trazAqui.isValidCodeEnc(codEnc1)) {
                        trazAqui.solicitaEnc(codEnc1);
                    } else {
                        Menu.errors(4);
                    }
                    Menu.clearWindow();
                    break;

                case 3:
                    Map<String, List<AbstractMap.SimpleEntry<String, Double>>> res = trazAqui.getTranspOptions();
                    int op = -1;
                    while (op < 0 || op > 1) op = Menu.apresentaPedidosTransportes(res);

                    if (op == 1) {
                        String codE = Menu.userMenuData(10);
                        String codT = Menu.userMenuData(1);
                        if (res.containsKey(codE) && res.get(codE).stream().anyMatch(a -> a.getKey().equals(codT)))
                            trazAqui.encomendaParaSerEntregue(codE, codT);
                        else Menu.errors(4);
                    }
                    Menu.clearWindow();
                    break;

                case 4:
                    String code1 = Menu.userMenuData(1);
                    if (trazAqui.checkIfEntityWorkedForUser(code1)) {

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

                        if (inicio.isAfter(fim)) Menu.errors(4);
                        else {
                            List<Encomenda> historico = trazAqui.getHistoricoUser().stream()
                                    .filter(a -> (a.getData().isBefore(fim) && a.getData().isAfter(inicio)))
                                    .sorted(Comparator.comparing(Encomenda::getData))
                                    .collect(Collectors.toList());
                            Menu.printHistorico(historico, code1, inicio, fim);
                        }
                    } else Menu.errors(4);
                    Menu.clearWindow();
                    break;

                case 5:
                    String code = Menu.userMenuData(1);
                    if (trazAqui.checkIfEntityWorkedForUser(code)) {
                        int classi = -1;
                        while (classi < 0 || classi > 10) {
                            classi = Integer.parseInt(Menu.userMenuData(2));
                        }
                        trazAqui.classificaEntidade(classi, code);
                    } else Menu.errors(4);
                    break;

                case 0:
                    exit = true;
                    Menu.clearWindow();
                    break;
            }
        }
    }
}
