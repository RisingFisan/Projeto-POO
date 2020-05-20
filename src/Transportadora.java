import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.io.*;

public class Transportadora extends Conta implements Serializable {
    private String nif;
    private double raio;
    private double precoKm;
    private List<Integer> classificacao;
    private Set<String> historico;
    
    //Encomendas que aceitou
    private List <String> encAceites;
    private boolean disponivel;
    //numero m�ximo de encomendas que transporta de cada vez
    private int maxCapacidade;
    
    public Transportadora (String cod, String nome, double x, double y, String nif, double raio, double preco) {
        super(cod,nome,x,y);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
        this.encAceites = new ArrayList<>();
        this.disponivel = true;
        this.maxCapacidade = 1;
        this.classificacao = new ArrayList<>();
        this.historico=new TreeSet<>();
    }
    
    public Transportadora (String cod, String nome, double x, double y, String nif, double raio, double preco,int max) {
        super(cod,nome,x,y);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
        this.encAceites = new ArrayList<>();
        this.disponivel = true;
        this.maxCapacidade = max;
        this.classificacao = new ArrayList<>();
        this.historico=new TreeSet<>();
    }

    public Transportadora (String cod, String nome, double x, double y, String nif, double raio, double preco, String novoEmail,String novaPass,List<String> lista,int max,List<Integer> list,Set<String> s) {
       super(cod,nome,x,y,novoEmail,novaPass);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
        this.encAceites = new ArrayList<>(lista);
        this.disponivel = lista.isEmpty();
        this.maxCapacidade = max;
        this.classificacao = new ArrayList<>(list);
        this.historico=new TreeSet<>(s);
    }


    public Transportadora (Transportadora t) {
        super(t);
        this.nif = t.nif;
        this.raio = t.raio;
        this.precoKm = t.precoKm;
        this.encAceites = t.getEncAceites();
        this.disponivel = t.disponivel;
        this.maxCapacidade = t.maxCapacidade;
        this.classificacao = t.getClassif();
        this.historico=t.getHistorico();
    }

    //GETTERS
    public String getNIF() {
        return this.nif;
    }

    public double getRaio() {
        return this.raio;
    }

    public double getPrecoPorKm() {
        return this.precoKm;
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
    
    public List<Integer> getClassif(){
        return new ArrayList(this.classificacao);
    }
    
    public Set<String> getHistorico(){
        return new TreeSet<>(this.historico);
    }

    //SETTERS
    public void setNIF(String nif) {
        this.nif = nif;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public void setPrecoPorKm(double preco) {
        this.precoKm = preco;
    }
    
     public void setDisponibilidade(boolean b){
        this.disponivel=b;
    }
    
    public void setEncAceites(List<String> e){
        this.encAceites = new ArrayList(e);
    }
    
    public void setMaxCapacidade(int max){
        this.maxCapacidade = max;
    }
    
    public void setClassif(List<Integer> e){
        this.classificacao = new ArrayList(e);
    }
    
    public void setHistorico(Set<String>s){
        this.historico = new TreeSet<>(s);
    }
    
    
    //CLONE
    public Transportadora clone() {
        return new Transportadora(this);
    }
    
    //toSTRING
    public String toString() {
        StringBuilder sb = new StringBuilder("Transportadora\n");
        sb.append(super.toString());
        sb.append("NIF: '").append(this.nif).append("'\n");
        sb.append("Raio: ").append(this.raio).append("km\n");
        sb.append("Preco por km: ").append(this.precoKm).append("\n");
        sb.append("Encomendas Aceites: ").append(this.encAceites);
        sb.append("Classificacoes: ").append(this.classificacao);
        return sb.toString();
    }
    
    public void addClassif(int i){
        this.classificacao.add(i);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Transportadora that = (Transportadora) o;
        return Double.compare(that.raio, raio) == 0 &&
                Double.compare(that.precoKm, precoKm) == 0 &&
                nif.equals(that.nif);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), nif, raio, precoKm);
    }
    
    public void addEncomenda (String cod){
        if (this.encAceites.contains(cod) || this.encAceites.size()==this.maxCapacidade)return;
        else if (this.encAceites.isEmpty()) this.disponivel=true;
        this.encAceites.add(cod);
        if (this.encAceites.size()==this.maxCapacidade) this.disponivel = false;
    }
    
    public double getAverageClassif(){
        double average = this.classificacao.stream().mapToInt(val -> val).average().orElse(0.0);
        return average;
    }
}

