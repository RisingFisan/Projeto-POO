import java.awt.geom.Point2D;

public class Utilizador implements Comparable {
    protected String codigo;
    protected String nome;
    protected Point2D gps;
    protected String email;
    protected String password;

    public Utilizador() {
        this.codigo = null;
        this.nome = null;
        this.gps = null;
        this.email = null;
        this.password = null;
    }

    public Utilizador(String cod, String nome, double x, double y) {
        this.codigo = cod;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = cod;
        this.password = cod;
    }

    public Utilizador(String cod, String nome, double x, double y, String email, String password) {
        this.codigo = cod;
        this.nome = nome;
        this.gps = new Point2D.Double(x, y);
        this.email = email;
        this.password = password;
    }
    
    public Utilizador(Utilizador outro){
        this.codigo = outro.codigo;
        this.nome = outro.nome;
        this.gps = new Point2D.Double(outro.getGPSx(), outro.getGPSy());
        this.email = outro.email;
        this.password = outro.password;
    }

    // GETTERS
    public String getCodigo(){
        return this.codigo;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public double getGPSx() { return this.gps.getX(); }
    public double getGPSy() { return this.gps.getY(); }
    
    public String getEmail(){
        return this.email;
    }
    
    
    
    //SETTERS
    
    public void setCodigo(String cod){
        this.codigo = cod;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setGPS(double x, double y){
        this.gps.setLocation(x, y);
    }
    
    public void setEmail(String novoEmail){
        this.email = novoEmail ;
    }
    
    public void setPassword(String novaPass){
        this.password = novaPass;
    }
    
    //CLONE
    public Utilizador clone() {
        return new Utilizador(this);
    }
    
    //EQUALS
    public boolean equals (Utilizador u){
        return this.codigo.equals(u.codigo)
                && this.nome.equals(u.nome)
                && this.gps.equals(u.gps)
                && this.email.equals(u.email)
                && this.password.equals(u.password);
    }
    
    //ToString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Código de Utilizador: '").append(this.codigo).append("'\n");
        sb.append("Nome de Utilizador: '").append(this.nome).append("'\n");
        sb.append("Coordenadas: ").append(this.gps.toString()).append("\n");
        sb.append("Email: '").append(this.email).append("'\n");
        sb.append("Password: '").append(this.password.replaceAll(".","*")).append("'\n");
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        Utilizador u = (Utilizador) o;
        return this.codigo.compareTo(u.codigo);
    }

    public boolean checkCredenciais (String outroEmail, String pass){
        return (this.email.equals(outroEmail) && this.password.equals(pass));
    }


}
