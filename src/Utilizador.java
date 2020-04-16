import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Utilizador {
    String codUtilizador;
    String nomeUtilizador;
    private Point2D.Double gps;
    private String email;
    private String password;
    
    public Utilizador (String cod,String nome,double x,double y,String novoEmail,String novaPassword) {
        this.codUtilizador =  cod;
        this.nomeUtilizador = nome;
        this.gps = new Point2D.Double(x,y);
        this.email = novoEmail;
        this.password = novaPassword;
    }
    
    public Utilizador (Utilizador outro){
        this.codUtilizador = outro.getCodUtilizador();
        this.nomeUtilizador = outro.getNomeUtilizador();
        this.gps = outro.getGps();
        this.email = outro.getEmail();
        this.password = outro.password;
        
    }
    
    // GETTERS
    public String getCodUtilizador(){
        return this.codUtilizador;
    }
    
    public String getNomeUtilizador(){
        return this.nomeUtilizador;
    }
    
    public Point2D.Double getGps(){
        return this.gps;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    
    
    //SETTERS
    
     public void setCodUtilizador(String cod){
        this.codUtilizador = cod;
    }
    
    public void setNomeUtilizador(String nome){
        this.nomeUtilizador = nome;
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
    public Utilizador clone() {
        return new Utilizador(this);
    }
    
    //EQUALS
    public boolean equals (Utilizador u){
        if (this.codUtilizador.equals(u.getCodUtilizador()) 
            && this.nomeUtilizador.equals(u.getNomeUtilizador()) 
            && this.gps.equals(u.getGps())
            && this.email.equals(u.getEmail())
            && this.password.equals(u.password)) return true;
        else return false;
            
    }
    
    //ToString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Código de Utilizador: ").append(this.codUtilizador).append("\n");
        sb.append("Nome do Utilizador: ").append(this.nomeUtilizador).append("\n");
        sb.append("Coordenadas: ").append(this.gps.toString()).append("\n");
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Password: ").append(this.password).append("\n");
        return sb.toString();
    }
    
    public boolean checkCredenciais (String outroEmail,String pass){
        return (this.email.equals(outroEmail) && this.password.equals(pass));
    }
    
    
}
