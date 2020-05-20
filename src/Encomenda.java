import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.io.*;

public class Encomenda implements Comparable<Encomenda>,Serializable {
    private String codEnc;
    private String codUtil;
    private String codLoja;
    private double peso;
    private Map<String, LinhaEncomenda> produtos;
    private LocalDateTime data;
    private boolean foiSolicitada;

    public Encomenda(String codEnc, String codUtil, String codLoja, double peso) {
        this.codEnc = codEnc;
        this.codUtil = codUtil;
        this.codLoja = codLoja;
        this.peso = peso;
        this.produtos = new HashMap<>();
        this.data = LocalDateTime.now();
        this.foiSolicitada = false;
    }
    

    public Encomenda (String newCodEnc, String newCodUtil, String newCodLoja, double newPeso, HashMap<String, LinhaEncomenda> le,LocalDateTime dt,boolean b){
        this.codEnc = newCodEnc;
        this.codUtil = newCodUtil;
        this.codLoja = newCodLoja;
        this.peso = newPeso;
        this.setProdutos(le);
        this.data = dt;
        this.foiSolicitada = b;
    }
    
     public Encomenda (Encomenda e){
        this.codEnc = e.codEnc;
        this.codUtil = e.codUtil;
        this.codLoja = e.codLoja;
        this.peso = e.peso;
        this.produtos = e.getProdutos();
        this.data = e.getData();
        this.foiSolicitada = e.foiSolicitada;
    }
    
    public String getCodEnc() {return this.codEnc;}
    
    public String getCodUtil() {return this.codUtil;}
    
    public String getCodLoja() {return this.codLoja;}
    
    public double getPeso() {return this.peso;}
    
    public Map<String, LinhaEncomenda> getProdutos() {
        return this.produtos.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }
    public LocalDateTime getData(){
        return this.data;
    }
    
    public boolean getFoiSolicitada(){
        return this.foiSolicitada;
    }
    
    public void setCodEnc(String newCod) {this.codEnc = newCod;}
    
    public void setCodUtil(String newCod) {this.codUtil = newCod;}
    
    public void setCodLoja(String newCod) {this.codLoja = newCod;}
    
    public void setPeso(double newPeso) {this.peso = newPeso;}
    
    public void setProdutos(Map<String, LinhaEncomenda> produtos) {
        this.produtos = produtos.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }
    
    public void setData(LocalDateTime d){
        this.data = d;
    }
    public void setFoiSolicitada(boolean b){
        this.foiSolicitada=b;
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
        sb.append("Data: ").append(this.data.toString()).append("\n");
        sb.append("Foi solicitada entrega pelo utilizador?: ").append(this.foiSolicitada).append("\n");

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
