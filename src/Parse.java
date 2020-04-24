import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Parse {

    public void parse() {
        List<String> linhas = lerFicheiro("LogsGerados.csv"); //alterar nome do ficheiro
        for (String linha : linhas) {
            String[] linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Utilizador":
                    Conta u = parseUtilizador(linhaPartida[1]); // criar um Utilizador
                    System.out.println(u.toString()); //enviar para o ecra apenas para teste
                    break;
                case "Loja":
                    Loja l = parseLoja(linhaPartida[1]);
                    System.out.println(l.toString());
                    break;
                case "Voluntario":
                    Voluntario v = parseVoluntario(linhaPartida[1]);
                    System.out.println(v.toString());
                    break;
                case "Transportadora":
                    Transportadora t = parseTransportadora(linhaPartida[1]);
                    System.out.println(t.toString());
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
        String codTrans = campos[0];
        String nomeTrans = campos[1];
        double x = Double.parseDouble(campos[2]);
        double y = Double.parseDouble(campos[3]);
        String nif = campos[4];
        double raio = Double.parseDouble(campos[5]);
        double preco = Double.parseDouble(campos[6]);
        //dados por omissao
        return new Transportadora(codTrans, nomeTrans, x, y, nif, raio, preco);
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


}
