import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Transportadora {
    String codEmpresa;
    String nomeEmpresa;
    String email;
    String password;
    Point2D.Double gps;
    int NIF;
    double raio;
    double precoKm;

    public Transportadora (String cod, String nome,String novoEmail,String novaPass, Point2D.Double gps, int NIF, double raio, double preco) {
        this.codEmpresa = cod;
        this.nomeEmpresa = nome;
        this.email = novoEmail;
        this.password= novaPass;
        this.gps = gps;
        this.raio = raio;
        this.precoKm = preco;
    }


    public Transportadora (Transportadora util) {
        this.codEmpresa = util.getCodEmpresa();
        this.nomeEmpresa = util.getNome();
        this.email = util.getEmail();
        this.password = util.password;
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
    
    public String getEmail() {
        return this.email;
    }


    public Point2D.Double getGps() {
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
    public void setEmail(String novoEmail) {
        this.email = novoEmail;
    }
    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setGps(Point2D.Double gps) {
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
        if (this.codEmpresa.equals(p.getCodEmpresa()) &&
            this.nomeEmpresa.equals(p.getNome()) &&
            this.email.equals(p.getEmail()) && 
            this.password.equals(p.password)&&
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
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Localização: ").append(this.gps.toString()).append("\n");
        sb.append("NIF: ").append(this.NIF).append("\n");
        sb.append("Raio(km): ").append(this.raio).append("\n");
        sb.append("Preco por Km: ").append(this.precoKm).append("\n");

        return sb.toString();
    }
    
    public boolean checkCredenciais (String outroEmail,String pass){
        return (this.email.equals(outroEmail) && this.password.equals(pass));
    }



}

