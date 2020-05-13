import java.util.Objects;
import java.util.*;

public class Voluntario extends Conta {
    private double raio;
    
    //Encomendas que aceitou
    private List <String> encAceites;
    private boolean disponivel;

   
    public Voluntario(String cod, String nome, double x, double y, double raio) {
        super(cod,nome,x,y);
        this.raio = raio;
        this.encAceites = new ArrayList<>();
        this.disponivel = true;
    }

    public Voluntario(String cod, String nome, double x, double y, double raio, String novoEmail, String novaPassword,List<String> lista) {
        super(cod,nome,x,y,novoEmail,novaPassword);
        this.raio = raio;
        this.encAceites = lista;
        this.disponivel = lista.isEmpty();
    }

    public Voluntario(Voluntario outro) {
        super(outro);
        this.raio = outro.raio;
        this.disponivel = outro.disponivel;
        this.encAceites = outro.getEncAceites();
    }

    // GETTERS
    public double getRaio() {
        return this.raio;
    }
    
    public boolean getDisponibilidade(){
        return this.disponivel;
    }
    
    public List<String> getEncAceites(){
        return new ArrayList(this.encAceites);
    }

    //SETTERS
    public void setRaio(double raio) {
        this.raio = raio;
    }
    
    public void setDisponibilidade(boolean b){
        this.disponivel=b;
    }
    
    public void getEncAceites(List<String> e){
        this.encAceites = new ArrayList(e);
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
        return sb.toString();
    }
}
