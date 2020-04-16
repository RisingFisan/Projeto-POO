import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Voluntario {
    private String codVoluntario;
    private String nomeVoluntario;
    private Point2D gps;
    private double raio;
    private String email;
    private String password;
    
    public Voluntario (String cod,String nome,Point2D point,double novoRaio,String novoEmail,String novaPassword) {
        this.codVoluntario =  cod;
        this.nomeVoluntario = nome;
        this.gps = point;
        this.email = novoEmail;
        this.password = novaPassword;
    }
    
    public Voluntario (Voluntario outro){
        this.codVoluntario = outro.getCodVoluntario();
        this.nomeVoluntario = outro.getNomeVoluntario();
        this.gps = outro.getGps();
        this.raio = outro.getRaio();
        this.email = outro.getEmail();
        this.password = outro.password;
        
    }
    
    // GETTERS
    public String getCodVoluntario(){
        return this.codVoluntario;
    }
    
    public String getNomeVoluntario(){
        return this.nomeVoluntario;
    }
    
    public Point2D getGps(){
        return this.gps;
    }
    
    public double getRaio() {
        return this.raio;
    }
    
    public String getEmail(){
        return this.email;
    }
    
   
    
    //SETTERS
    
     public void setCodVoluntario(String cod){
        this.codVoluntario = cod;
    }
    
    public void setNomeVoluntario(String nome){
        this.nomeVoluntario = nome;
    }
    
    public void setGps(Point2D point){
        this.gps = point;
    }
    
    public void setEmail(String novoEmail){
        this.email = novoEmail ;
    }
    
    public void setPassword(String novaPass){
        this.password= novaPass;
    }
    
    //CLONE
    public Voluntario clone() {
        return new Voluntario (this);
    }
    
    //EQUALS
    public boolean equals (Voluntario v){
        if (this.codVoluntario.equals(v.getCodVoluntario()) 
            && this.nomeVoluntario.equals(v.getNomeVoluntario()) 
            && this.gps.equals(v.getGps())
            && this.email.equals(v.getEmail())
            && this.password.equals(v.password)) return true;
        else return false;
            
    }
    
    //ToString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("C�digo de Voluntario: ").append(this.codVoluntario).append("\n");
        sb.append("Nome do Voluntario: ").append(this.nomeVoluntario).append("\n");
        sb.append("Coordenadas: ").append(this.gps.toString()).append("\n");
        sb.append("Raio: ").append(this.raio).append("\n");
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Password: ").append(this.password).append("\n");
        return sb.toString();
    }
    
    public boolean checkCredenciais (String outroEmail,String pass){
        return (this.email.equals(outroEmail) && this.password.equals(pass));
    }
   
}
