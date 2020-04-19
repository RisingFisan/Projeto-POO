import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Contas {
    private Set<Conta> setContas;

    public Contas() {
        this.setContas = new TreeSet<>();
    }

    public Contas(Set<Conta> set) {
        this.setContas(set);
    }

    public Contas(Contas us) {
        this.setContas = us.getContas();
    }

    public Set<Conta> getContas() {
        return this.setContas.stream()
                .map(Conta::clone)
                .collect(Collectors.toSet());
    }

    public void setContas(Set<Conta> set) {
        this.setContas = set.stream()
                .map(Conta::clone)
                .collect(Collectors.toSet());
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Utilizadores: ").append(setContas.toString()).append('\n');
        return sb.toString();
    }
}
