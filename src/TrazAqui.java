
/**
Controlador Temporario */
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TrazAqui
{
   Contas contas;
   Encomendas encomendas;
   
   
   public void initApp() {
       contas = new Contas();
       encomendas = new Encomendas();
    }
   
   public void loadFiles(){
       Parse p = new Parse();
       p.parse();
       contas = new Contas(p.getContas());
       encomendas = new Encomendas(p.getEncomendas());
    }
   
   
}
