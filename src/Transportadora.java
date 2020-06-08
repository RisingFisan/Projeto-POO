import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class Transportadora extends Conta implements Serializable, TranspVolunt,Random {
    private String nif;
    private double raio;
    private double precoKm;
    private List<Integer> classificacao;
    
    //Encomendas que aceitou
    private List<String> encAceites;
    private boolean disponivel;
    //numero máximo de encomendas que transporta de cada vez
    private int maxCapacidade;
    private boolean medicamentos;
    private double kmPercorridos;
    private double velocidade;

    public Transportadora(String cod, String nome, double x, double y, String nif, double raio, double preco) {
        super(cod, nome, x, y);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
        this.encAceites = new ArrayList<>();
        this.disponivel = true;
        this.maxCapacidade = Integer.MAX_VALUE;
        this.classificacao = new ArrayList<>();
        this.medicamentos = false;
        this.kmPercorridos = 0;
        this.velocidade = calculaVelocidadeVol();
    }

    public Transportadora(String cod, String nome, double x, double y, String nif, double raio, double preco, int max) {
        super(cod, nome, x, y);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
        this.encAceites = new ArrayList<>();
        this.disponivel = true;
        this.maxCapacidade = max;
        this.classificacao = new ArrayList<>();
        this.medicamentos = false;
        this.kmPercorridos = 0;
        this.velocidade = calculaVelocidadeVol();
    }

    public Transportadora(String cod, String nome, double x, double y, String nif, double raio, double preco, String novoEmail, String novaPass) {
        super(cod, nome, x, y, novoEmail, novaPass);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
        this.encAceites = new ArrayList<>();
        this.disponivel = true;
        this.maxCapacidade = Integer.MAX_VALUE;
        this.velocidade = calculaVelocidadeVol();
    }
    
    

    public Transportadora(String cod, String nome, double x, double y, String nif, double raio, double preco, String novoEmail, String novaPass, int maxCapacidade) {
        super(cod, nome, x, y, novoEmail, novaPass);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
        this.encAceites = new ArrayList<>();
        this.disponivel = true;
        this.maxCapacidade = maxCapacidade;
        this.velocidade = calculaVelocidadeVol();
    }
    
    public Transportadora(String cod, String nome, double x, double y, String nif, double raio, double preco, String novoEmail, String novaPass, int maxCapacidade, double vel) {
        super(cod, nome, x, y, novoEmail, novaPass);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
        this.encAceites = new ArrayList<>();
        this.disponivel = true;
        this.maxCapacidade = maxCapacidade;
        this.velocidade = vel;
    }
    
    public Transportadora(String cod, String nome, double x, double y, String nif, double raio, double preco, String novoEmail, String novaPass, List<String> list, int max) {
        super(cod, nome, x, y, novoEmail, novaPass);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
        this.encAceites = new ArrayList<>(list);
        this.disponivel = list.isEmpty();
        this.maxCapacidade = max;
        this.classificacao = new ArrayList<>();
        this.medicamentos = false;
        this.kmPercorridos = 0;
        this.velocidade = calculaVelocidadeVol();
    }


    public Transportadora(Transportadora t) {
        super(t);
        this.nif = t.nif;
        this.raio = t.raio;
        this.precoKm = t.precoKm;
        this.encAceites = t.getEncAceites();
        this.disponivel = t.disponivel;
        this.maxCapacidade = t.maxCapacidade;
        this.classificacao = t.getClassif();
        this.medicamentos = t.aceitoTransporteMedicamentos();
        this.kmPercorridos = t.getKmPercorridos();
        this.velocidade = calculaVelocidadeVol();
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

    public boolean getDisponibilidade() {
        return this.disponivel;
    }

    public List<String> getEncAceites() {
        return new ArrayList<>(this.encAceites);
    }

    public int getMaxCapacidade() {
        return this.maxCapacidade;
    }

    public List<Integer> getClassif() {
        return new ArrayList<>(this.classificacao);
    }

    public boolean aceitoTransporteMedicamentos() {
        return this.medicamentos;
    }

    public double getKmPercorridos() {
        return this.kmPercorridos;
    }
    
    public double getVelocidade() {
        return this.velocidade;
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

    public void setDisponibilidade(boolean b) {
        this.disponivel = b;
    }

    public void setEncAceites(List<String> e) {
        this.encAceites = new ArrayList<>(e);
    }

    public void setMaxCapacidade(int max) {
        this.maxCapacidade = max;
    }

    public void setClassif(List<Integer> e) {
        this.classificacao = new ArrayList<>(e);
    }

    public void aceitaMedicamentos(boolean state) {
        this.medicamentos = state;
    }

    public void setKmPercorridos(double km) {
        this.kmPercorridos = km;
    }
    
    public void setVelocidade(double vel) {
        this.velocidade = vel;
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

    public void addClassif(int i) {
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

    public void addEncomenda(String cod) {
        if (this.encAceites.contains(cod) || this.encAceites.size() == this.maxCapacidade) return;
        else if (this.encAceites.isEmpty()) this.disponivel = true;
        this.encAceites.add(cod);
        if (this.encAceites.size() == this.maxCapacidade) this.disponivel = false;
    }

    public double getAverageClassif() {
        return this.classificacao.stream().mapToInt(val -> val).average().orElse(0.0);
    }

    public double totalPreco(double dist) {
        return this.precoKm * dist;
    }
}

