import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;


public class Utilizador extends Conta implements Serializable {
    //Tabela associa codigo da entidade transportadora á lsita de encomendas que transportou pra o utilizador
    Map<String,List<String>>historico;
    public Utilizador(String codigo, String nome, double x, double y) {
        super(codigo, nome, x, y);
        this.historico = new HashMap<>();
    }

    public Utilizador(String codigo, String nome, double x, double y, String email, String password) {
        super(codigo, nome, x, y, email, password);
        this.historico = new HashMap<>();
    }
    
    public Utilizador(String codigo, String nome, double x, double y, String email, String password,Map<String,List<String>> t) {
        super(codigo, nome, x, y, email, password);
        setHistorico(t);
    }

    public Utilizador(Utilizador outro) {
        super(outro);
        this.historico = outro.getHistorico();
    }
    
    public Map<String,List<String>> getHistorico(){
        return this.historico.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().stream().collect(Collectors.toList())));
    }
    
    public void setHistorico(Map<String,List<String>> t){
        this.historico= t.entrySet().stream()
                                 .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().stream().collect(Collectors.toList())));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Utilizador\n");
        sb.append(super.toString());
        return sb.toString();
    }
    
    
    public Utilizador clone() {
        return new Utilizador(this);
    }

    public boolean equals(Utilizador u) {
        return super.equals(u);
    }
    
    public boolean contains(String s){
        return this.historico.keySet().contains(s);
    }
    
}
