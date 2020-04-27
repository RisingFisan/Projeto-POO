/** Controlador Temporario */

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.io.*;

public class TrazAqui implements Serializable {
   Contas contas;
   Encomendas encomendas;
   
   public TrazAqui(){
       this.contas = new Contas();
       this.encomendas = new Encomendas();
       
    }
   
    public TrazAqui(TrazAqui t){
} 


    public TrazAqui loadFiles(){
      Parse p = new Parse();
      TrazAqui t = new TrazAqui();
       p.parse();
       t.contas = new Contas(p.getContas());
       t.encomendas = new Encomendas(p.getEncomendas());
       return t;
     
     
    }
   
   
    
    public void fazerRegisto(){
        
    }
    
   
   
}
