import java.util.Objects;

public class Transportadora extends Conta {
    private String nif;
    private double raio;
    private double precoKm;

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
}

