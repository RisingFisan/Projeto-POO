import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.io.*;

public class Voluntario extends Conta implements Serializable {
    private double raio;
    
    //Encomendas que aceitou
    private String encAceite;
    private boolean disponivel;
    private List<Integer> classificacao;
    
    

   
    public Voluntario(String cod, String nome, double x, double y, double raio) {
        super(cod,nome,x,y);
        this.raio = raio;
        this.encAceite = null;
        this.disponivel = true;
        this.classificacao = new ArrayList<>();
    }
    
    public Voluntario(String cod, String nome, double x, double y, double raio, String novoEmail, String novaPassword) {
        super(cod,nome,x,y,novoEmail,novaPassword);
        this.raio = raio;
        this.encAceite = "";
        this.disponivel = true;
        this.classificacao = new ArrayList<>();
        
    }

    public Voluntario(String cod, String nome, double x, double y, double raio, String novoEmail, String novaPassword) {
        super(cod,nome,x,y,novoEmail,novaPassword);
        this.raio = raio;
        this.encAceite = null;
        this.disponivel = false;
    }

    public Voluntario(String cod, String nome, double x, double y, double raio, String novoEmail, String novaPassword,String aceite, List<Integer>list) {
        super(cod,nome,x,y,novoEmail,novaPassword);
        this.raio = raio;
        this.encAceite = aceite;
        this.disponivel = false;
        this.classificacao = new ArrayList<>(list);
    }

    public Voluntario(Voluntario outro) {
        super(outro);
        this.raio = outro.raio;
        this.encAceite = outro.getEncAceite();
        this.disponivel = outro.disponivel;
        this.classificacao = outro.getClassif();
    }

    // GETTERS
    public double getRaio() {
        return this.raio;
    }
    
    public boolean getDisponibilidade(){
        return this.disponivel;
    }
    
    public String getEncAceite(){
        return this.encAceite;
    }
    
    public List<Integer> getClassif(){
        return new ArrayList(this.classificacao);
    }
    
    
    

    //SETTERS
    public void setRaio(double raio) {
        this.raio = raio;
    }
    
    public void setDisponibilidade(boolean b){
        this.disponivel=b;
    }
    
    public void setEncAceite(String e){
        this.encAceite = e;
    }
    
    public void setClassif(List<Integer> e){
        this.classificacao = new ArrayList(e);
    }
    
    
    
    //CLONE
    public Voluntario clone() {
        return new Voluntario(this);
    }

    //EQUALS
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Voluntario that = (Voluntario) o;
        return Double.compare(that.raio, raio) == 0;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), raio);
    }

    //ToString
    public String toString() {
        StringBuilder sb = new StringBuilder("Voluntario\n");
        sb.append(super.toString());
        sb.append("Raio: ").append(this.raio).append("km\n");
        sb.append("Encomenda Aceite: ").append(this.encAceite);
        return sb.toString();
    }
    
    public void addEncomenda (String cod){
       if (this.disponivel) {setEncAceite(cod);this.disponivel = false;}
       else return;
    }
    
    public void addClassif(int i){
        this.classificacao.add(i);
    }
    
    public double getAverageClassif(){
        double average = this.classificacao.stream().mapToInt(val -> val).average().orElse(0.0);
        return average;
    }
}
