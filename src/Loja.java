import java.util.*;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;
import java.io.*;
public class Loja extends Conta implements Serializable {

    private Queue<Encomenda> filaEspera;
    private Set<String> historico;

    public Loja(String cod, String nome, double x, double y) {
        super(cod, nome, x, y);
        this.filaEspera = new ArrayDeque<>();
        this.historico=new TreeSet<>();
    }
    
    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword) {
        super(cod, nome, x, y, novoEmail, novaPassword);
        this.filaEspera = new ArrayDeque<>();
        this.historico=new TreeSet<>();
    }
    

    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword, List<Encomenda> l,Set<String> s) {
        super(cod, nome, x, y, novoEmail, novaPassword);
        this.setFilaEspera(l);
        this.historico=new TreeSet<>(s);
    }

    public Loja(Loja outro) {
        super(outro);
        this.filaEspera = outro.getFilaEspera();
        this.historico = outro.getHistorico();
        
    }
    
    public void addEncomenda(Encomenda e) {
        this.filaEspera.add(e.clone());
    }
    
    public void setHistorico(Set<String>s){
        this.historico = new TreeSet<>(s);
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
    
    public Set<String> getHistorico(){
        return new TreeSet<>(this.historico);
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
        sb.append("Historico ").append(this.historico.toString()).append("\n");;
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
