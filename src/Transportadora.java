import java.awt.geom.Point2D;

public class Transportadora extends Conta {
    String nif;
    double raio;
    double precoKm;

    public Transportadora (String cod, String nome, double x, double y, String nif, double raio, double preco) {
        this.codigo = cod;
        this.nome = nome;
        this.email = cod;
        this.password= cod;
        this.gps = new Point2D.Double(x, y);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
    }

    public Transportadora (String cod, String nome, double x, double y, String nif, double raio, double preco, String novoEmail,String novaPass) {
        this.codigo = cod;
        this.nome = nome;
        this.email = novoEmail;
        this.password= novaPass;
        this.gps = new Point2D.Double(x, y);
        this.raio = raio;
        this.nif = nif;
        this.precoKm = preco;
    }


    public Transportadora (Transportadora util) {
        this.codigo = util.codigo;
        this.nome = util.nome;
        this.email = util.email;
        this.password = util.password;
        this.gps = new Point2D.Double(this.getGPSx(), this.getGPSy());
        this.nif = util.nif;
        this.raio = util.raio;
        this.precoKm = util.precoKm;
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

