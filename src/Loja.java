import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Loja {
    String codLoja;
    String nomeLoja;
    private Point2D.Double gps;
    private String email;
    private String password;
    
    public Loja (String cod,String nome,double x,double y,String novoEmail,String novaPassword) {
        this.codLoja =  cod;
        this.nomeLoja = nome;
        this.gps = new Point2D.Double(x,y);
        this.email = novoEmail;
        this.password = novaPassword;
    }
    
    public Loja (Loja outro){
        this.codLoja = outro.getCodLoja();
        this.nomeLoja = outro.getNomeLoja();
        this.gps = outro.getGps();
        this.email = outro.getEmail();
        this.password = outro.getPassword();
        
    }
    
    // GETTERS
    public String getCodLoja(){
        return this.codLoja;
    }
    
    public String getNomeLoja(){
        return this.nomeLoja;
    }
    
    public Point2D.Double getGps(){
        return this.gps;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    //SETTERS
    
     public void setCodLoja(String cod){
        this.codLoja = cod;
    }
    
    public void setNomeLoja(String nome){
        this.nomeLoja = nome;
    }
    
    public void setGps(Point2D.Double point){
        this.gps = point;
    }
    
    public void setEmail(String novoEmail){
        this.email = novoEmail ;
    }
    
    public void setPassword(String novaPass){
        this.password= novaPass;
    }
    
    //CLONE
    public Loja clone() {
        return new Loja(this);
    }
    
    //EQUALS
    public boolean equals (Loja l){
        if (this.codLoja.equals(l.getCodLoja()) 
            && this.nomeLoja.equals(l.getNomeLoja()) 
            && this.gps.equals(l.getGps())
            && this.email.equals(l.getEmail())
            && this.password.equals(l.getPassword())) return true;
        else return false;
            
    }
    
    //ToString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("C�digo de Loja: ").append(this.codLoja).append("\n");
        sb.append("Nome da Loja: ").append(this.nomeLoja).append("\n");
        sb.append("Coordenadas: ").append(this.gps.toString()).append("\n");
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Password: ").append(this.password).append("\n");
        return sb.toString();
    }
    
    
}
