import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.Iterator; 

public class Loja extends Conta {
    
    private Queue <Encomenda> filaEspera;
    
    public Loja(String cod, String nome, double x, double y) {
        this.codigo = cod;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = cod;
        this.password = cod;
        this.filaEspera = new LinkedList<>();
    }

    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword, LinkedList<Encomenda> l) {
        this.codigo = cod;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = novoEmail;
        this.password = novaPassword;
        this.setFilaEspera(l);
    }

    public Loja(Loja outro) {
        this.codigo = outro.codigo;
        this.nome = outro.nome;
        this.gps = new Point2D.Double(outro.getGPSx(), outro.getGPSy());
        this.email = outro.getEmail();
        this.password = outro.password;
        this.filaEspera = outro.getFilaEspera();
    }
    
    
    public void setFilaEspera(LinkedList<Encomenda> l){
        this.filaEspera = l.stream().map(Encomenda::clone).collect(Collectors.toCollection(LinkedList::new));
    }
    
    
    public Queue getFilaEspera() {
        return this.filaEspera.stream().map(Encomenda::clone).collect(Collectors.toCollection(LinkedList::new));
       }
       

    //CLONE
    public Loja clone() {
        return new Loja(this);
    }

    //ToString
    public String toString() {
        StringBuilder sb = new StringBuilder("Loja\n");
        sb.append(super.toString());
        sb.append(this.filaEspera);
        return sb.toString();
    }
    
    public boolean equals(Loja l){
        if (!super.equals(l) || this.filaEspera.size()!=l.getFilaEspera().size()) return false;
        else {
            Iterator it = this.filaEspera.iterator();
            Iterator it1 = l.getFilaEspera().iterator();
            while (it.hasNext() && it1.hasNext())
              if (!it.next().equals(it1.next())) return false;
            return true;  
             
        }
    }
}
