import java.awt.geom.Point2D;

public class Utilizador extends Conta {
    public Utilizador(String codigo, String nome, double x, double y) {
        this.codigo = codigo;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = codigo;
        this.password = codigo;
    }

    public Utilizador(String codigo, String nome, double x, double y, String email, String passowrd) {
        this.codigo = codigo;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = email;
        this.password = passowrd;
    }

    public Utilizador(Utilizador outro) {
        this.codigo = outro.codigo;
        this.nome = outro.nome;
        this.gps = new Point2D.Double(outro.getGPSx(), outro.getGPSy());
        this.email = outro.email;
        this.password = outro.password;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Utilizador\n");
        sb.append(super.toString());
        return sb.toString();
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }
}
