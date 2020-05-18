import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Voluntario extends Conta {
    private double raio;
    
    //Encomendas que aceitou
    private String encAceite;
    private boolean disponivel;
    

   
    public Voluntario(String cod, String nome, double x, double y, double raio) {
        super(cod,nome,x,y);
        this.raio = raio;
        this.encAceite = "";
        this.disponivel = true;
    }

    public Voluntario(String cod, String nome, double x, double y, double raio, String novoEmail, String novaPassword,String aceite) {
        super(cod,nome,x,y,novoEmail,novaPassword);
        this.raio = raio;
        this.encAceite = aceite;
        this.disponivel = false;
        
    }

    public Voluntario(Voluntario outro) {
        super(outro);
        this.raio = outro.raio;
        this.encAceite = outro.getEncAceite();
        this.disponivel = outro.disponivel;
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
}
