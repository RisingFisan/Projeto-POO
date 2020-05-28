import java.util.*;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;
import java.io.*;
public class Loja extends Conta implements Serializable {

    private Queue<Encomenda> filaEspera;

    public Loja(String cod, String nome, double x, double y) {
        super(cod, nome, x, y);
        this.filaEspera = new ArrayDeque<>();
    }
    
    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword) {
        super(cod, nome, x, y, novoEmail, novaPassword);
        this.filaEspera = new ArrayDeque<>();
    }
    

    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword, List<Encomenda> l) {
        super(cod, nome, x, y, novoEmail, novaPassword);
        this.setFilaEspera(l);
    }

    public Loja(Loja outro) {
        super(outro);
        this.filaEspera = outro.getFilaEspera();
        
    }
    
    public void addEncomenda(Encomenda e) {
        this.filaEspera.add(e.clone());
    }
    

    public void setFilaEspera(List<Encomenda> l) {
        this.filaEspera = l.stream()
                .map(Encomenda::clone)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public Queue<Encomenda> getFilaEspera() {
        return this.filaEspera.stream()
                .map(Encomenda::clone)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
    
    

    //CLONE
    public Loja clone() {
        return new Loja(this);
    }

    //ToString
    public String toString() {
        StringBuilder sb = new StringBuilder("Loja\n");
        sb.append(super.toString());
        sb.append("Fila de espera: ").append(this.filaEspera.toString()).append("\n");
        return sb.toString();
        
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Loja loja = (Loja) o;
        return this.filaEspera.size() == loja.filaEspera.size() &&
                this.filaEspera.containsAll(loja.filaEspera) &&
                loja.filaEspera.containsAll(this.filaEspera);
    }
}
