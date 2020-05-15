import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Voluntario extends Conta {
    private double raio;
    
    //Encomendas que aceitou
    private List <String> encAceites;
    private boolean disponivel;
    //numero máximo de encomendas que transporta de cada vez
    private int maxCapacidade;

   
    public Voluntario(String cod, String nome, double x, double y, double raio) {
        super(cod,nome,x,y);
        this.raio = raio;
        this.encAceites = new ArrayList<>();
        this.disponivel = true;
        this.maxCapacidade = 1;
    }

    public Voluntario(String cod, String nome, double x, double y, double raio, String novoEmail, String novaPassword,List<String> lista,int max) {
        super(cod,nome,x,y,novoEmail,novaPassword);
        this.raio = raio;
        this.encAceites = lista;
        this.disponivel = lista.isEmpty();
        this.maxCapacidade = max;
    }

    public Voluntario(Voluntario outro) {
        super(outro);
        this.raio = outro.raio;
        this.encAceites = outro.getEncAceites();
        this.disponivel = outro.disponivel;
        this.maxCapacidade = outro.maxCapacidade;
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
    
    public int getMaxCapacidade(){
        return this.maxCapacidade;
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
    
    public void setMaxCapacidade(int max){
        this.maxCapacidade = max;
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
    
    public void addEncomenda (String cod){
       if (this.encAceites.contains(cod) || this.encAceites.size()==this.maxCapacidade) return;
        else if (this.encAceites.isEmpty()) this.disponivel=true;
        this.encAceites.add(cod);
        if (this.encAceites.size()==this.maxCapacidade) this.disponivel = false;
    }
}
