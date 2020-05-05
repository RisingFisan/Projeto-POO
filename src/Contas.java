import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Contas {
    private Map<String,Conta> mapContas;

    public Contas() {
        this.mapContas = new TreeMap<>();
    }

    public Contas(Map<String, Conta> map) {
        this.setContas(map);
    }

    public Contas(Contas us) {
        this.mapContas = us.getContas();
    }

    public Map<String, Conta> getContas() {
        return this.mapContas.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone(), (a,b)->a, TreeMap::new));
    }
    
    public void addConta (Conta conta) {
        this.mapContas.put(conta.getCodigo(), conta.clone());
    }
    
    public void setContas(Map<String,Conta> map) {
        this.mapContas = map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone(), (a,b)->a, TreeMap::new));
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Contas: ").append(mapContas.toString()).append('\n');
        return sb.toString();
    }
    
    public boolean checkCredenciais (String email, String pass) {
        return this.mapContas.values().stream()
                .anyMatch(c->c.checkCredenciais(email,pass));
    }
    
    public Contas clone(){
        return new Contas(this);
    }

    public Conta getContaByCod(String cod){
        return this.mapContas.getOrDefault(cod,null);
    }
}
