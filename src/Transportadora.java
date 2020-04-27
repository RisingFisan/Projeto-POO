import java.awt.geom.Point2D;

public class Transportadora extends Conta {
    String nif;
    double raio;
    double precoKm;

    public Transportadora (String cod, String nome, double x, double y, String nif, double raio, double preco) {
        super(cod,nome,x,y);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
    }

    public Transportadora (String cod, String nome, double x, double y, String nif, double raio, double preco, String novoEmail,String novaPass) {
       super(cod,nome,x,y,novoEmail,novaPass);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
    }


    public Transportadora (Transportadora t) {
        super(t);
        this.nif = t.nif;
        this.raio = t.raio;
        this.precoKm = t.precoKm;
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
        sb.append("Preço por km: ").append(this.precoKm).append("\n");
        return sb.toString();
    }
}

