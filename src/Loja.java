import java.util.*;
import java.util.stream.Collectors;

public class Loja extends Conta {

    private Queue<Encomenda> filaEspera;

    public Loja(String cod, String nome, double x, double y) {
        super(cod, nome, x, y);
        this.filaEspera = new ArrayDeque<>();
    }

    public Loja(String cod, String nome, double x, double y, String novoEmail, String novaPassword, List<Encomenda> l) {
        super(cod, nome, x, y, novoEmail, novaPassword);
        this.setFilaEspera(l);
    }

    public Loja(Loja outro) {
        super(outro);
        this.filaEspera = outro.getFilaEspera();
    }

    public void setFilaEspera(List<Encomenda> l) {
        this.filaEspera = new ArrayDeque<>(l);
    }

    public Queue<Encomenda> getFilaEspera() {
        return new ArrayDeque<>(this.filaEspera);
    }

    //CLONE
    public Loja clone() {
        return new Loja(this);
    }

    //ToString
    public String toString() {
        StringBuilder sb = new StringBuilder("Loja\n");
        sb.append(super.toString());
        sb.append("Fila de espera: ").append(this.filaEspera.toString());
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Loja loja = (Loja) o;
        return this.filaEspera.size() == loja.filaEspera.size() &&
                this.filaEspera.containsAll(loja.filaEspera) &&
                loja.filaEspera.containsAll(this.filaEspera);
    }
}
