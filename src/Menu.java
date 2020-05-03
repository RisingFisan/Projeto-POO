
public class Menu
{
   public Menu(){
    }
    
    public String menuRegisto() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------REGISTO NA APP---------").append("\n");
        sb.append("Como é que se deseja registar? ").append("\n\n");
        sb.append("   1) Utilizador").append("\n");
        sb.append("   2)Voluntario ").append("\n");
        sb.append("   3)Empresa ").append("\n");
        sb.append("   4)Transportadora").append("\n");

        return sb.toString();
        
    }
    
    
}
