import java.util.*;
import java.io.*;

public class Controller {
    public static void run() {
        TrazAqui trazAqui = new TrazAqui();

        boolean errorMessage = false;
        while (true) {
            int opcao = -1;
            while (opcao < 0 || opcao > 5) {
                opcao = Menu.MenuInicial();
            }

            switch (opcao) {

                case 1:
                    while (true) {
                        AbstractMap.SimpleEntry<String, String> dados = Menu.menuLogin(errorMessage);
                        if (trazAqui.login(dados.getKey(), dados.getValue())) break;
                        else errorMessage = true;
                    }

                    errorMessage = false;
                    Menu.clearWindow();
                    TipoConta tc = trazAqui.getTipoConta();
                    if (tc.equals(TipoConta.Utilizador)) ControllerUtilizador.run(trazAqui);

                    break;

                case 2:
                    TipoConta tipoConta = null;
                    while (tipoConta == null) tipoConta = Menu.menuRegisto();

                    Conta conta;

                    if (tipoConta.equals(TipoConta.Utilizador)) conta = Menu.menuRegistoUtilizador();
                    else if (tipoConta.equals(TipoConta.Voluntario)) conta = Menu.menuRegistoVoluntario();
                    else if (tipoConta.equals(TipoConta.Loja)) conta = Menu.menuRegistoLoja();
                    else conta = Menu.menuRegistoTransportadora();

                    trazAqui.registo(conta);

                    if (tipoConta.equals(TipoConta.Utilizador))
                        break;

                case 3:
                    trazAqui.carregaLogs();
                    break;

                case 4:
                    try {
                        trazAqui.salvaEstadoObj();
                        System.out.println("Ficheiros salvos com sucesso!!!\n");
                    } catch (FileNotFoundException e) {
                        Menu.errors(1);
                    } catch (IOException e) {
                        Menu.errors(2);
                    }
                    break;

                case 5:
                    try {
                        trazAqui.carregaEstadoObj();
                        System.out.println("Ficheiros carregados com sucesso!!!\n");
                    } catch (FileNotFoundException e) {
                        Menu.errors(1);
                    } catch (IOException e) {
                        Menu.errors(6);
                    } catch (ClassNotFoundException e) {
                        Menu.errors(3);
                    }
                    break;


                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    
}
