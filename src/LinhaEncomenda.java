public class LinhaEncomenda implements Comparable {

    private String codProduto;
    private String descricao;
    private double quantidade;
    private double valorUnitario;
    
    public LinhaEncomenda() {
        this.codProduto = "";
        this.descricao = "";
        this.quantidade = 0;
        this.valorUnitario = 0;
    }

    public LinhaEncomenda(String cod, String desc, double quant, double valor) {
        this.codProduto = cod;
        this.descricao = desc;
        this.quantidade = quant;
        this.valorUnitario = valor;
    }

    public LinhaEncomenda(LinhaEncomenda le) {
        this.codProduto = le.getCodProduto();
        this.descricao = le.getDescricao();
        this.quantidade = le.getQuantidade();
        this.valorUnitario = le.getValor();
    }

    //GETTERS

    public String getCodProduto() {
        return this.codProduto;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getQuantidade() {
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

    public void setQuantidade(double quant) {
        this.quantidade = quant;
    }

    public void setValor(double valor) {
        this.valorUnitario = valor;
    }


    //CLONE
    public LinhaEncomenda clone() {
        return new LinhaEncomenda(this);
    }

    //EQUALS
    public boolean equals(LinhaEncomenda le) {
        return this.codProduto.equals(le.getCodProduto())
                && this.descricao.equals(le.getDescricao())
                && this.quantidade == le.getQuantidade()
                && Double.compare(this.valorUnitario, le.valorUnitario) == 0;
    }

    //toString

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo de Produto: ").append(this.codProduto).append("\n");
        sb.append("Descricao: ").append(this.descricao).append("\n");
        sb.append("Quantidade encomendada: ").append(this.quantidade).append("\n");
        sb.append("Valor Unitario: ").append(this.valorUnitario).append("\n");
        return sb.toString();
    }
    
    @Override
    public int compareTo(Object o) {
        LinhaEncomenda u = (LinhaEncomenda) o;
        return this.codProduto.compareTo(u.codProduto);
    
    }
}
