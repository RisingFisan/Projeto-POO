import java.lang.Object;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

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
                        System.out.println(u.toString()); //enviar para o ecrã apenas para teste
                        break;
                case "Loja": 
                        Loja l = parseLoja(linhaPartida[1]);
                        System.out.println(l.toString());
                        break;                                   
                case "Voluntario":
                        Voluntario v = parseVoluntario(linhaPartida[1]);
                        System.out.println(l.toString());
                        break;
                case "Transportadora":
                        Transportadora t = parseTransportadora(linhaPartida[1]);
                        System.out.println(l.toString());
                        break; 
                case "Encomenda":
                        break;
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
        Point2D gps = new Point2D.Double(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        //dodos por omissao
        return new Utilizador(codUtilizador,nome,gps);
  }

  public Loja parseLoja(String input){
        String[] campos = input.split(",");
        String codLoja = campos[0]; 
        String nomeLoja = campos[1];
        // dados por omissao
        return new Loja(codLoja,nomeLoja);
  }
  
  public Voluntario parseVoluntario(String input){
        String[] campos = input.split(",");
        String codVol = campos[0]; 
        String nomeVol = campos[1];
        Point2D gps = new Point2D.Double(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        double raio = Double.parseDouble(campos[4]);
        //dados por omissao
        return new Voluntario(codVol,nomeVol,gps,raio);
  }
  
  public Transportadora parseTransportadora(String input){
        String[] campos = input.split(",");
        String codTrans = campos[0]; 
        String nomeTrans = campos[1];
        Point2D gps = new Point2D.Double(Double.parseDouble(campos[2]),Double.parseDouble(campos[3]));
        int nif =  Integer.parseInt(campos[4]);
        double raio = Double.parseDouble(campos[5]);
        double preco = Double.parseDouble(campos[6]);
        //dados por omissao
        return new Transportadora(codTrans,nomeTrans,gps,nif,raio,preco);
  }

  public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { System.out.println(exc.getMessage()); }
        return lines;
  }


}
