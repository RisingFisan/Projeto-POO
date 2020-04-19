import java.awt.geom.Point2D;

public class Loja extends Conta {
    public Loja(String cod, String nome, double x, double y) {
        this.codigo = cod;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = cod;
        this.password = cod;
    }

    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword) {
        this.codigo = cod;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = novoEmail;
        this.password = novaPassword;
    }

    public Loja(Loja outro) {
        this.codigo = outro.codigo;
        this.nome = outro.nome;
        this.gps = new Point2D.Double(outro.getGPSx(), outro.getGPSy());
        this.email = outro.getEmail();
        this.password = outro.password;
    }

    //CLONE
    public Loja clone() {
        return new Loja(this);
    }

    //ToString
    public String toString() {
        StringBuilder sb = new StringBuilder("Loja\n");
        sb.append(super.toString());
        return sb.toString();
    }
}
