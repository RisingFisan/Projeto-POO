import java.util.*;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;
import java.io.*;
public class Loja extends Conta implements Serializable, Randoms {

    private Queue<Encomenda> filaEspera;
    private double tempoEsperaIndividual;

    public Loja(String cod, String nome, double x, double y) {
        super(cod, nome, x, y);
        this.filaEspera = new ArrayDeque<>();
        this.tempoEsperaIndividual=0;
    }
    
    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword) {
        super(cod, nome, x, y, novoEmail, novaPassword);
        this.filaEspera = new ArrayDeque<>();
        this.tempoEsperaIndividual=0; 
    }
    
    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword,double te) {
        super(cod, nome, x, y, novoEmail, novaPassword);
        this.filaEspera = new ArrayDeque<>();
        this.tempoEsperaIndividual=te;
    }

    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword, List<Encomenda> l,double te) {
        super(cod, nome, x, y, novoEmail, novaPassword);
        this.setFilaEspera(l);
        this.tempoEsperaIndividual=te;
    }

    public Loja(Loja outro) {
        super(outro);
        this.filaEspera = outro.getFilaEspera();
        this.tempoEsperaIndividual=outro.tempoEsperaIndividual;
        
    }
    
    
    
    public void addEncomenda(Encomenda e) {
        this.filaEspera.add(e.clone());
    }
    
    public double getTempoEsperaIndividual(){return this.tempoEsperaIndividual;}
    public void serTempoEsperaIndividual(double d){this.tempoEsperaIndividual=d;}

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
    
    //Quanto tempo ate estar despachada
    public Double tempoEsperaTeorico(String enc) {
        int count = 0;
        for (Encomenda e: filaEspera) {
            if (e.getCodEnc().equals(enc))
                break;
            count++;
        }
        return (count*this.tempoEsperaIndividual);
    }
    
    public Double tempoEspera(String enc) {
        int count = 0;
        for (Encomenda e: filaEspera) {
            if (e.getCodEnc().equals(enc))
                break;
            count++;
        }
        if (count == 0) return 0.0;;
        return (calculaTempo(count*this.tempoEsperaIndividual));
    }
    
    
    
    public int quantosNaFrente(String cod){
         int count = 0;
        for (Encomenda e: filaEspera) {
            if (e.getCodEnc().equals(cod))
                break;
            count++;
        }
        return count;
    }
    
    public void remove(String cod){
        if (!filaEspera.isEmpty()) filaEspera.remove(cod);
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
