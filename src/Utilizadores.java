import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Utilizadores {
    private Set<Utilizador> utilizadores;

    public Utilizadores() {
        this.utilizadores = new TreeSet<>();
    }

    public Utilizadores(Set<Utilizador> set) {
        this.setUtilizadores(set);
    }

    public Utilizadores(Utilizadores us) {
        this.utilizadores = us.getUtilizadores();
    }

    public Set<Utilizador> getUtilizadores() {
        return this.utilizadores.stream()
                .map(Utilizador::clone)
                .collect(Collectors.toSet());
    }

    public void setUtilizadores(Set<Utilizador> set) {
        this.utilizadores = set.stream()
                .map(Utilizador::clone)
                .collect(Collectors.toSet());
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Utilizadores: ").append(utilizadores.toString()).append('\n');
        return sb.toString();
    }
}
