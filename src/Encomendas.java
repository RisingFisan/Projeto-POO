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
    
    public Encomenda getEncomendaByCod(String code) {
        return this.encomendas.stream().reduce(null, (acc, e) -> e.getCodEnc().equals(code) ? e.clone() : acc);
    }
    
    public List<Encomenda> getListaEncomenda(List<String>lista){
        return lista.stream().map(this::getEncomendaByCod).collect(Collectors.toList());
    }
    
    public Encomendas clone() {
        return new Encomendas(this);
    }
    
    public boolean equals (Encomendas e){
        return this.encomendas.equals(e.encomendas);
    }
    
    public void solicitaEnc(String e){
        this.encomendas.stream().filter(a->a.getCodEnc().equals(e)).forEach(a->a.setFoiSolicitada(true));
    }
    
    public void quemTransportou(String e,String t){
        this.encomendas.stream().filter(a->a.getCodEnc().equals(e)).forEach(a->a.setQuemTransportou(t));
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Encomendas: ").append(encomendas.toString()).append('\n');
        return sb.toString();
    }
    
    public String getLastCode(){
        if (this.encomendas.isEmpty()) return "";
        TreeSet<Encomenda> t = new TreeSet<>(this.encomendas);
        Encomenda e = t.last();
        return e.getCodEnc();
    }
    
    
}
