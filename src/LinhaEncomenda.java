
public class LinhaEncomenda
{
    private String codProduto;
    private String descricao;
    private int quantidade;
    private double valorUnitario;
    
    public LinhaEncomenda(String cod,String desc,int quant,double valor){
        this.codProduto = cod;
        this.descricao = desc;
        this.quantidade = quant;
        this. valorUnitario = valor;
    }
    
    public LinhaEncomenda (LinhaEncomenda le) {
        this.codProduto = getCodProduto();
        this.descricao = getDescricao();
        this.quantidade = getQuantidade();
        this. valorUnitario = getValor();
    }
    
    
    //GETTERS
    
    public String getCodProduto() {
        return this.codProduto;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public double getValor() {
        return this.valorUnitario;
    }
    
    //SETTERS
    
    public void setCodProduto(String cod) {
        this.codProduto = cod;
    }
    
     public void setDescricao(String desc) {
        this.descricao = desc;
    }
    
     public void setQuantidade(int quant) {
        this.quantidade = quant;
    }
    
     public void setValor(double valor) {
        this.valorUnitario = valor;
    }
    
    
    //CLONE
    public LinhaEncomenda clone(){
        return new LinhaEncomenda(this);
    }
    
    //EQUALS
    public boolean equals(LinhaEncomenda le) {
         if (this.codProduto.equals(le.getCodProduto()) 
            && this.descricao.equals(le.getDescricao()) 
            && this.quantidade == le.getQuantidade()
            && this.valorUnitario == le.getValor()) return true;
        else return false;
    }
    
    //toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Código de Produto: ").append(this.codProduto).append("\n");
        sb.append("Descricao: ").append(this.descricao).append("\n");
        sb.append("Quantidade encomenda: ").append(this.quantidade).append("\n");
        sb.append("Valor Unitario: ").append(this.valorUnitario).append("\n");
        return sb.toString();
    }
    
    }
