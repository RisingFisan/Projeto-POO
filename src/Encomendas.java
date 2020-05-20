import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;
public class Encomendas implements Serializable
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
    
    public Encomenda getEncomendaByCod(String code){
        return this.encomendas.stream().filter(e->e.getCodEnc().equals(code)).findFirst().orElse(null).clone();
        
    }
    
    public List<Encomenda> getListaEncomenda(List<String>lista){
        return lista.stream().map(a->getEncomendaByCod(a)).collect(Collectors.toList());
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
