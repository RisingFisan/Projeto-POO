import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Transportadora {
    String codEmpresa;
    String nomeEmpresa;
    Point2D gps;
    int NIF;
    double raio;
    double precoKm;

    public Transportadora (String cod, String nome, Point2D gps, int NIF, double raio, double preco) {
        this.codEmpresa = cod;
        this.nomeEmpresa = nome;
        this.gps = gps;
        this.raio = raio;
        this.precoKm = preco;
    }


    public Transportadora (Transportadora util) {
        this.codEmpresa = util.getCodEmpresa();
        this.nomeEmpresa = util.getNome();
        this.gps = util.getGps();
        this.NIF = util.getNIF();
        this.raio = util.getRaio();
        this.precoKm = util.getPreco();
    }

    //GETTERS
    public String getCodEmpresa() {
        return this.codEmpresa;
    }


    public String getNome() {
        return this.nomeEmpresa;
    }


    public Point2D getGps() {
        return this.gps;
    }


    public int getNIF() {
        return this.NIF;
    }


    public double getRaio() {
        return this.raio;
    }

    public double getPreco() {
        return this.precoKm;
    }
  
    //SETTERS
    public void setCodEmpresa(String util) {
        this.codEmpresa = util;
    }

    public void setNome(String nome) {
        this.nomeEmpresa = nome;
    }

    public void setGps(Point2D gps) {
        this.gps = gps;
    }

    public void setNIF(int nif) {
        this.NIF = nif;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public void setPreco(double preco) {
        this.precoKm = preco;
    }
    
    
    //CLONE
    public Transportadora clone() {
        return new Transportadora(this);
    }
    
    //EQUALS
    public boolean equals(Transportadora p) {
        if (this.codEmpresa == p.getCodEmpresa() &&
            this.nomeEmpresa == p.getNome() &&
            this.gps == p.getGps() &&
            this.NIF == p.getNIF() &&
            this.raio == p.getRaio() &&
            this.precoKm == p.getPreco()) 
            return true;

        else return false;

    }
    
    //toSTRING
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append("Código da empresa: ").append(this.codEmpresa).append("\n");
        sb.append("Nome da empresa: ").append(this.nomeEmpresa).append("\n");
        sb.append("Localização: ").append(this.gps.toString()).append("\n");
        sb.append("NIF: ").append(this.NIF).append("\n");
        sb.append("Raio(km): ").append(this.raio).append("\n");
        sb.append("Preco por Km: ").append(this.precoKm).append("\n");

        return sb.toString();
    }



}

