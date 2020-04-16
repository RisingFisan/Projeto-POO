import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Parse
{
	
  public void parse(){
        List<String> linhas = lerFicheiro("LogsGerados.csv"); //alterar nome do ficheiro
        String[] linhaPartida;
        for (String linha : linhas) {
                linhaPartida = linha.split(":", 2);
                switch(linhaPartida[0]){
                case "Utilizador": 
                        Utilizador u = parseUtilizador(linhaPartida[1]); // criar um Utilizador
                        System.out.println(u.toString()); //enviar para o ecrán apenas para teste
                        break;
                case "Loja": 
                        Loja l = parseLoja(linhaPartida[1]);
                        System.out.println(l.toString());
                        break;                                   
                //...
                default: 
                        System.out.println("Linha invalida.");
                        break;
                }

        }
        System.out.println("done!");
  }
                                

  public Utilizador parseUtilizador(String input){
        String[] campos = input.split(",");
        String nome = campos[0]; 
        String codUtilizador = campos[1];
        Point2D.Double gps = new Point2D.Double(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        //Default email and password
        return new Utilizador(codUtilizador,nome,gps);
  }

  public Loja parseLoja(String input){
        String[] campos = input.split(",");
        String codLoja = campos[0]; 
        String nomeLoja = campos[1];
        return new Loja(...);
  }

  public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { System.out.println(exc.getMessage()); }
        return lines;
  }


}
