import java.awt.geom.Point2D;

public class Voluntario extends Utilizador{
    private double raio;

    public Voluntario(String cod, String nome, double x, double y, double raio) {
        this.codigo = cod;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = cod;
        this.password = cod;
        this.raio = raio;
    }

    public Voluntario(String cod, String nome, double x, double y, double raio, String novoEmail, String novaPassword) {
        this.codigo = cod;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = novoEmail;
        this.password = novaPassword;
        this.raio = raio;
    }

    public Voluntario(Voluntario outro) {
        this.codigo = outro.codigo;
        this.nome = outro.nome;
        this.gps = new Point2D.Double(outro.getGPSx(), outro.getGPSy());
        this.raio = outro.raio;
        this.email = outro.email;
        this.password = outro.password;
    }

    // GETTERS
    public double getRaio() {
        return this.raio;
    }

    //SETTERS
    public void setRaio(double raio) {
        this.raio = raio;
    }

    //CLONE
    public Voluntario clone() {
        return new Voluntario(this);
    }

    //EQUALS
    public boolean equals(Voluntario v) {
        return this.codigo.equals(v.codigo)
                && this.nome.equals(v.nome)
                && this.gps.equals(v.gps)
                && this.email.equals(v.email)
                && this.password.equals(v.password);
    }

    //ToString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Código de Voluntario: '").append(this.codigo).append("'\n");
        sb.append("Nome do Voluntario: '").append(this.nome).append("'\n");
        sb.append("Coordenadas: ").append(this.gps.toString()).append("\n");
        sb.append("Raio: ").append(this.raio).append("km\n");
        sb.append("Email: '").append(this.email).append("'\n");
        sb.append("Password: '").append(this.password.replaceAll(".","*")).append("'\n");
        return sb.toString();
    }
}
