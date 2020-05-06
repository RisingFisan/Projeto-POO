import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Encomenda implements Comparable<Encomenda> {
    private String codEnc;
    private String codUtil;
    private String codLoja;
    private double peso;
    private Map<String, LinhaEncomenda> produtos;

    public Encomenda(String codEnc, String codUtil, String codLoja, double peso) {
        this.codEnc = codEnc;
        this.codUtil = codUtil;
        this.codLoja = codLoja;
        this.peso = peso;
        this.produtos = new HashMap<>();
    }

    public Encomenda (String newCodEnc, String newCodUtil, String newCodLoja, double newPeso, HashMap<String, LinhaEncomenda> le){
        this.codEnc = newCodEnc;
        this.codUtil = newCodUtil;
        this.codLoja = newCodLoja;
        this.peso = newPeso;
        this.setProdutos(le);
    }
    
     public Encomenda (Encomenda e){
        this.codEnc = e.codEnc;
        this.codUtil = e.codUtil;
        this.codLoja = e.codLoja;
        this.peso = e.peso;
        this.produtos = e.getProdutos();
    }
    
    public String getCodEnc() {return this.codEnc;}
    
    public String getCodUtil() {return this.codUtil;}
    
    public String getCodLoja() {return this.codLoja;}
    
    public double getPeso() {return this.peso;}
    
    public Map<String, LinhaEncomenda> getProdutos() {
        return this.produtos.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }
    
    public void setCodEnc(String newCod) {this.codEnc = newCod;}
    
    public void setCodUtil(String newCod) {this.codUtil = newCod;}
    
    public void setCodLoja(String newCod) {this.codLoja = newCod;}
    
    public void setPeso(double newPeso) {this.peso = newPeso;}
    
    public void setProdutos(Map<String, LinhaEncomenda> produtos) {
        this.produtos = produtos.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }

    public void addProduto(LinhaEncomenda le) {
        this.produtos.put(le.getCodProduto(), le.clone());
    }
    
    public Encomenda clone(){
        return new Encomenda(this);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("Encomenda\n");
        sb.append("Código da Encomenda: ").append(this.codEnc).append("\n");
        sb.append("Código do Utilizador: ").append(this.codUtil).append("\n");
        sb.append("Código da Loja: ").append(this.codLoja).append("\n");
        sb.append("Peso: ").append(this.peso).append("\n");
        sb.append("Produtos: ").append(this.produtos.toString()).append("\n");

        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda encomenda = (Encomenda) o;
        return Double.compare(encomenda.peso, peso) == 0 &&
                Objects.equals(codEnc, encomenda.codEnc) &&
                Objects.equals(codUtil, encomenda.codUtil) &&
                Objects.equals(codLoja, encomenda.codLoja) &&
                Objects.equals(produtos, encomenda.produtos);
    }

    public int hashCode() {
        return Objects.hash(codEnc, codUtil, codLoja, peso, produtos);
    }

    public int compareTo(Encomenda e) {
        return this.codEnc.compareTo(e.codEnc);
    } 
    
    
}
