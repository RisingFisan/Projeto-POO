import java.awt.geom.Point2D;
import java.util.Objects;

public class Voluntario extends Conta {
    private double raio;

    public Voluntario(String cod, String nome, double x, double y, double raio) {
        super(cod,nome,x,y);
        this.raio = raio;
    }

    public Voluntario(String cod, String nome, double x, double y, double raio, String novoEmail, String novaPassword) {
        super(cod,nome,x,y,novoEmail,novaPassword);
        this.raio = raio;
    }

    public Voluntario(Voluntario outro) {
        super(outro);
        this.raio = outro.raio;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Voluntario that = (Voluntario) o;
        return Double.compare(that.raio, raio) == 0;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), raio);
    }

    //ToString
    public String toString() {
        StringBuilder sb = new StringBuilder("Voluntário\n");
        sb.append(super.toString());
        sb.append("Raio: ").append(this.raio).append("km\n");
        return sb.toString();
    }
}
