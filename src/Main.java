
public class Main
{
    //Tentar encontrar outra forma mais elegante
    public void clearWindow() {
        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }
    
    public static void Main(){
        TrazAqui app = new TrazAqui();
        
        
        app.loadFiles();
    }
    
}
