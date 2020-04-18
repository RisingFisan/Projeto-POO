import java.awt.geom.Point2D;

public class Loja extends Utilizador {
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

    //EQUALS
    public boolean equals(Loja l) {
        return this.codigo.equals(l.codigo)
                && this.nome.equals(l.nome)
                && this.gps.equals(l.gps)
                && this.email.equals(l.email)
                && this.password.equals(l.password);
    }

    //ToString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Código de Loja: '").append(this.codigo).append("'\n");
        sb.append("Nome da Loja: '").append(this.nome).append("'\n");
        sb.append("Coordenadas: ").append(this.gps.toString()).append("\n");
        sb.append("Email: '").append(this.email).append("'\n");
        sb.append("Password: '").append(this.password.replaceAll(".","*")).append("'\n");
        return sb.toString();
    }
}
