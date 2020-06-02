import java.util.AbstractMap;
import java.io.*;
import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.Collections;
import javafx.util.Pair; 
public class Controller {
    public static void run() {
        TrazAqui trazAqui = new TrazAqui();

        boolean errorMessage = false;
        while(true){
            int opcao = -1;
            while(opcao < 0 || opcao > 5) {
                opcao = Menu.MenuInicial();
            }
       
            switch(opcao) {
                
                case 1:
                    while(true) {
                        AbstractMap.SimpleEntry<String, String> dados = Menu.menuLogin(errorMessage);
                        if(trazAqui.login(dados.getKey(), dados.getValue())) break;
                        else errorMessage = true;
                    }
                    
                    errorMessage = false;
                    Menu.clearWindow();
                    TipoConta tc = trazAqui.getTipoConta();
                    if (tc.equals(TipoConta.Utilizador)) runLoggedAccountUser(trazAqui);
                    if (tc.equals(TipoConta.Voluntario)) runLoggedAccountVoluntario(trazAqui);
                    
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
                    String code = trazAqui.getNewCodeEnc();
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
                    
                case 3:
                    trazAqui.carregaLogs();
                    break;
                    
                case 4:
                    try{trazAqui.salvaEstadoObj();System.out.println("Ficheiros salvos com sucesso!!!\n");}
                    catch (FileNotFoundException e) {Menu.errors(1);}
                    catch (IOException e) {Menu.errors(2);}
                    break;
                    
                case 5:
                    try {trazAqui.carregaEstadoObj();System.out.println("Ficheiros carregados com sucesso!!!\n");}
                    catch (FileNotFoundException e) {Menu.errors(1);}
                    catch (IOException e) {Menu.errors(6);}
                    catch (ClassNotFoundException e) {Menu.errors(3);}
                    break;
                
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
    
    public static void runLoggedAccountUser(TrazAqui trazAqui){
        boolean errorMessage = false;
        boolean exit = false;
        while(!exit){
            int opcao = -1;
            while(opcao < 0 || opcao > 2) {
                opcao = Menu.menuUtilizador();
            }
            
            switch(opcao) {
                case 1:
                    String loja = Menu.userMenuData(3);
                    if (!trazAqui.isValidoLoja(loja)) {
                        Menu.errors(4);
                        loja = Menu.userMenuData(3);
                    }
                    String codEnc = trazAqui.getNewCodeEnc();
                    double peso = Double.valueOf(Menu.userMenuData(9));
                    String codUt = trazAqui.getCodLoggedIn();
                    Encomenda e = new Encomenda(codEnc,codUt, loja, peso);
                    int numLinhas = -1;
                    while (numLinhas<0) numLinhas = Integer.valueOf(Menu.userMenuData(4));
                    while (numLinhas>0){
                        String codP = Menu.userMenuData(5);
                        String desc = Menu.userMenuData(6);
                        double quant = Double.valueOf(Menu.userMenuData(7));
                        double preco = Double.valueOf(Menu.userMenuData(8));
                        numLinhas--;
                        LinhaEncomenda l = new LinhaEncomenda(codP,desc,quant,preco);
                        e.addProduto(l);
                    }
                    trazAqui.addEncToEstado(e);
                    Menu.clearWindow();
                    break;
                
                case 2:
                    String codEnc1 = Menu.userMenuData(10);
                    if (trazAqui.isValidCodeEnc(codEnc1))  {    
                           trazAqui.solicitaEnc(codEnc1); 
                        }
                    else {
                        Menu.errors(4);
                    }
                    Menu.clearWindow();
                    break;
                    
                case 3:
                    Map<String,List<Pair <String, Double>>> res = trazAqui.getTranspOptions();
                    int op = -1;
                    while (op<0 || op>1) Menu.apresentaPedidosTransportes (res);
                    
                    if (op ==1){
                    String codE = Menu.userMenuData(10);
                    String codT = Menu.userMenuData(1);
                    if (res.containsKey(codE) && res.get(codE).stream().anyMatch(a->a.getKey().equals(codT))) trazAqui.encomendaParaSerEntregue(codE,codT);
                    else Menu.errors(4);}
                    Menu.clearWindow();
                    break;
                   
                case 4: 
                    String code1 = Menu.userMenuData(1);
                    if (trazAqui.checkIfEntityWorkedForUser(code1)){
                        
                        int ano=-1;
                         while (ano<0) ano = Menu.dataInfo(1,true);
                        int mes=-1;
                         while (mes<0 || mes>12) mes = Menu.dataInfo(2,true);
                        int dia=-1;
                         while (dia<0 || dia>31) dia = Menu.dataInfo(3,true); 
                        LocalDateTime inicio = LocalDateTime.of(ano,mes,dia,0,0,0);
                        
                        int ano1=-1;
                         while (ano1<0) ano1 = Menu.dataInfo(1,false);
                        int mes1=-1;
                         while (mes1<0 || mes1>12) mes1 = Menu.dataInfo(2,false);
                        int dia1=-1;
                         while (dia1<0 || dia1>31) dia1 = Menu.dataInfo(3,false); 
                        LocalDateTime fim = LocalDateTime.of(ano1,mes1,dia1,0,0,0);
                        
                        if (inicio.isAfter(fim)) Menu.errors(4);
                        else {
                        List<Encomenda> historico = trazAqui.getHistoricoUser();
                         historico.stream()
                                  .filter(a->(a.getData().isBefore(fim) && a.getData().isAfter(inicio)))
                                  .collect(Collectors.toList());
                        Collections.sort(historico, (x, y) -> x.getData().compareTo(y.getData()));                                             
                        Menu.printHistorico(historico,code1,inicio,fim);
                        }
                    }
                    else Menu.errors(4);
                    Menu.clearWindow();
                    break;
                   
                case 5:
                    String code = Menu.userMenuData(1);
                    if (trazAqui.checkIfEntityWorkedForUser(code)){
                       int classi = -1;
                       while (classi<0||classi>10){
                           classi = Integer.valueOf(Menu.userMenuData(2));
                        }
                        trazAqui.classificaEntidade(classi,code);
                    }
                    else Menu.errors(4);
                    break;
                    
                case 0:
                    exit=true;
                    Menu.clearWindow();
                    break;
            }
        }
        
    }
    
    public static void runLoggedAccountVoluntario(TrazAqui trazAqui){
        boolean errorMessage = false;
        boolean exit = false;
        while(!exit){
            int opcao = -1;
            String s;
            while(opcao < 0 || opcao > 3) {
                opcao = Menu.menuVoluntario();
            }
            switch(opcao) {
                case 1:
                    int op = -1;
                    while(op<1 || op >2)
                        op = Integer.valueOf(Menu.voluntarioMenuData(1));
                    trazAqui.alteraDisp(op);
                    Menu.voluntarioMenuResult(1, "");
                    break;
                case 2:
                    String codEnc = Menu.voluntarioMenuData(2);
                    if (trazAqui.isValidCodeEnc(codEnc)) {
                        try { 
                            boolean res = trazAqui.pedirTranspVol(codEnc);
                            if (res) Menu.voluntarioMenuResult(2, "");
                            else Menu.errors(4);
                        }
                        catch (VolNaoDisponivelException e) {
                            Menu.errors(7);
                        }
                    }
                    else 
                        Menu.errors(4);
                    break;
                case 3:
                    try { 
                        long time = trazAqui.entregaEnc();
                        if (time == -1) Menu.errors(4);
                        else Menu.voluntarioMenuResult(3, String.valueOf(time));
                    }
                    catch (VolNaoDisponivelException e) {
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
