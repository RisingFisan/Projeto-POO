import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Encomenda implements Comparable{
    private String codEnc;
    private String codUtil;
    private String codLoja;
    private double peso;
    private Set<LinhaEncomenda> encomenda;
    
    public Encomenda (String newCodEnc,String newCodUtil,String newCodLoja,double newPeso){
        this.codEnc = newCodEnc;
        this.codUtil = newCodUtil;
        this.codLoja = newCodLoja;
        this.peso = newPeso;
        this.encomenda = new TreeSet<>();
        
    } 
    
     public Encomenda (String newCodEnc,String newCodUtil,String newCodLoja,double newPeso,Set<LinhaEncomenda> le){
        this.codEnc = newCodEnc;
        this.codUtil = newCodUtil;
        this.codLoja = newCodLoja;
        this.peso = newPeso;
        this.setEncomenda(le);
        
    }
    
     public Encomenda (Encomenda e){
        this.codEnc = e.codEnc;
        this.codUtil = e.codUtil;
        this.codLoja = e.codLoja;
        this.encomenda = e.getEncomenda();
        
        
    }
    
    public String getCodEnc() {return this.codEnc;}
    
    public String getCodUtil() {return this.codUtil;}
    
    public String getCodLoja() {return this.codLoja;}
    
    public double getPeso() {return this.peso;}
    
    public Set<LinhaEncomenda> getEncomenda() {
        return this.encomenda.stream().map(LinhaEncomenda::clone).collect(Collectors.toSet());
    }
    
    public void setCodEnc(String newCod) {this.codEnc = newCod;}
    
    public void setCodUtil(String newCod) {this.codUtil = newCod;}
    
    public void setCodLoja(String newCod) {this.codLoja = newCod;}
    
    public void setPeso(double newPeso) {this.peso = newPeso;}
    
    public void setEncomenda(Set<LinhaEncomenda> le) {
        this.encomenda = le.stream().map(LinhaEncomenda::clone).collect(Collectors.toSet());
    }
    
    public Encomenda clone(){
        return new Encomenda(this);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Código da Encomenda: ").append(this.codEnc).append("\n");
        sb.append("Código do Utilizador: ").append(this.codUtil).append("\n");
        sb.append("Código da Loja: ").append(this.codLoja).append("\n");
        sb.append("Peso: ").append(this.peso).append("\n");
        sb.append("Linhas da Encomenda: ").append(this.encomenda.toString()).append("\n");

        return sb.toString();
    } 
    
   public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda e = (Encomenda) o;
        return this.codEnc.equals(e.codEnc) &&
                this.codUtil.equals(e.codUtil) &&
                this.codLoja.equals(e.codLoja) &&
                (this.peso)==(e.peso) &&
                this.encomenda.equals(e.encomenda);
    }
    
   @Override
    public int compareTo(Object o) {
        Encomenda u = (Encomenda) o;
        return this.codEnc.compareTo(u.codEnc);
    } 
    
    
}
