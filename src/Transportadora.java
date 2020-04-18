import java.awt.geom.Point2D;

public class Transportadora extends Utilizador {
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
    
    //EQUALS
    public boolean equals(Transportadora p) {
        return this.codigo.equals(p.codigo) &&
                this.nome.equals(p.nome) &&
                this.email.equals(p.email) &&
                this.password.equals(p.password) &&
                this.gps.equals(p.gps) &&
                this.nif.equals(p.nif) &&
                Double.compare(this.raio, p.raio) == 0 &&
                Double.compare(this.precoKm, p.precoKm) == 0;
    }
    
    //toSTRING
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append("Código da empresa: '").append(this.codigo).append("'\n");
        sb.append("Nome da empresa: '").append(this.nome).append("'\n");
        sb.append("Email: '").append(this.email).append("'\n");
        sb.append("Password: '").append(this.password.replaceAll(".","*")).append("'\n");
        sb.append("Localização: ").append(this.gps.toString()).append("\n");
        sb.append("NIF: '").append(this.nif).append("'\n");
        sb.append("Raio: ").append(this.raio).append("km\n");
        sb.append("Preco por Km: ").append(this.precoKm).append("\n");
        return sb.toString();
    }
}

