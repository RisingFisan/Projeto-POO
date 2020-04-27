import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Parse {
    Set<Conta> c;
    Set<Encomenda> e;

    public void parse() {
        List<String> linhas = lerFicheiro("LogsGerados.csv"); //alterar nome do ficheiro
        for (String linha : linhas) {
            String[] linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Utilizador":
                    Conta u = parseUtilizador(linhaPartida[1]); // criar um Utilizador
                    System.out.println(u.toString()); //enviar para o ecra apenas para teste
                    c.add(u);
                    break;
                case "Loja":
                    Conta l = parseLoja(linhaPartida[1]);
                    System.out.println(l.toString());
                    c.add(l);
                    break;
                case "Voluntario":
                    Conta v = parseVoluntario(linhaPartida[1]);
                    System.out.println(v.toString());
                    c.add(v);
                    break;
                case "Transportadora":
                    Conta t = parseTransportadora(linhaPartida[1]);
                    System.out.println(t.toString());
                    c.add(t);
                    break;
                case "Encomenda":
                    Encomenda enc = parseEnc(linhaPartida[1]);
                    System.out.println(e.toString());
                    e.add(enc);
                    break;
                default:
                    System.out.println("Linha invalida.");
                    break;
            }

        }
        System.out.println("done!");
    }


    public Conta parseUtilizador(String input) {
        String[] campos = input.split(",");
        String nome = campos[0];
        String codUtilizador = campos[1];
        double x = Double.parseDouble(campos[2]);
        double y = Double.parseDouble(campos[3]);
        //dados por omissao
        return new Conta(codUtilizador, nome, x, y);
    }

    public Loja parseLoja(String input) {
        String[] campos = input.split(",");
        String codLoja = campos[0];
        String nomeLoja = campos[1];
        double x = Double.parseDouble(campos[2]);
        double y = Double.parseDouble(campos[3]);
        // dados por omissao
        return new Loja(codLoja, nomeLoja, x, y);
    }

    public Voluntario parseVoluntario(String input) {
        String[] campos = input.split(",");
        String codVol = campos[0];
        String nomeVol = campos[1];
        double x = Double.parseDouble(campos[2]);
        double y = Double.parseDouble(campos[3]);
        double raio = Double.parseDouble(campos[4]);
        //dados por omissao
        return new Voluntario(codVol, nomeVol, x, y, raio);
    }

    public Transportadora parseTransportadora(String input) {
        String[] campos = input.split(",");
        String codTransp = campos[0];
        String nomeTransp = campos[1];
        double x = Double.parseDouble(campos[2]);
        double y = Double.parseDouble(campos[3]);
        String nif = campos[4];
        double raio = Double.parseDouble(campos[5]);
        double preco = Double.parseDouble(campos[6]);
        //dados por omissao
        return new Transportadora(codTransp, nomeTransp, x, y, nif, raio, preco);
    }
    
    public Encomenda parseEnc(String input){
        String[] campos = input.split(",");
        String cod = campos[0];
        String nome = campos[1];
        String loja = campos[2];
        double peso = Double.parseDouble(campos[3]);
        Set<LinhaEncomenda> l = new TreeSet<>();
        int i = 4;
        while(i<campos.length-1){
            String codProd = campos[i++];
            String codDesc = campos[i++];
            int quant = Integer.parseInt(campos[i++]);
            double val = Double.parseDouble(campos[i++]); 
            LinhaEncomenda le = new LinhaEncomenda(codProd,codDesc,quant,val);
            l.add(le);
        }
        return new Encomenda(cod,nome,loja,peso,l);
        
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return lines;
    }
    
    public Set getContas(){
        return this.c.stream().map(Conta::clone).collect(Collectors.toSet());
    }
    
    public Set getEncomendas(){
        return this.e.stream().map(Encomenda::clone).collect(Collectors.toSet());
    }


}
