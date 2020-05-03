import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Encomendas
{
     private Set<Encomenda> encomendas;

    public Encomendas() {
        this.encomendas = new TreeSet<>();
    }

    public Encomendas(Set<Encomenda> set) {
        this.setEnc(set);
    }

    public Encomendas(Encomendas us) {
        this.encomendas = us.getEnc();
    }

    public Set<Encomenda> getEnc() {
        return this.encomendas.stream()
                .map(Encomenda::clone)
                .collect(Collectors.toSet());
    }

    public void setEnc(Set<Encomenda> set) {
        this.encomendas = set.stream()
                             .map(Encomenda::clone)
                             .collect(Collectors.toSet());
    }
    
    public void addEnc (Encomenda e) {
        this.encomendas.add(e);
    }
    
    public Encomendas clone() {
        return new Encomendas(this);
    }
    
    public boolean equals (Encomendas e){
        return this.encomendas.equals(e);
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Encomendas: ").append(encomendas.toString()).append('\n');
        return sb.toString();
    }
}
