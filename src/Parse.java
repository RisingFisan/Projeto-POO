import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.io.*;

/* TODO: Passar as funcionalidades desta classe para a classe Estado */

public class Parse {
    private Set<Conta> c;
    private Set<Encomenda> e;

    public Parse() {
        this.c = new TreeSet<>();
        this.e = new TreeSet<>();
    }

    public void parse() {
        List<String> linhas = lerFicheiro("logs.csv");
        for (String linha : linhas) {
            String[] linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Utilizador":
                    Conta u = parseUtilizador(linhaPartida[1]);
                    //System.out.println(u.toString()); 
                    c.add(u);
                    break;
                case "Loja":
                    Conta l = parseLoja(linhaPartida[1]);
                    //System.out.println(l.toString());
                    c.add(l);
                    break;
                case "Voluntario":
                    Conta v = parseVoluntario(linhaPartida[1]);
                    //System.out.println(v.toString());
                    c.add(v);
                    break;
                case "Transportadora":
                    Conta t = parseTransportadora(linhaPartida[1]);
                    //System.out.println(t.toString());
                    c.add(t);
                    break;
                case "Encomenda":
                    Encomenda enc = parseEnc(linhaPartida[1]);
                    e.add(enc);
                    break;
                case "Aceite":
                    break;
                default:
                    System.out.println("Linha inválida.");
                    break;
            }
            putEncInQueues();
        }
        System.out.println("----Ficheiros carregados!---");

    }

    /*Prototipo de outra parse - nao acabada*/
    public void parse2() {


        try {
            File file = new File("logs.csv");
            System.out.println(file.getCanonicalPath());
            FileInputStream ft = new FileInputStream(file);

            DataInputStream in = new DataInputStream(ft);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strline;

            while ((strline = br.readLine()) != null) {
                System.out.println(strline.toString());
            }
            in.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void putEncInQueues() {

        for (Conta conta : this.c) {
            if (conta instanceof Loja) {
                Loja l = (Loja) conta;
                LinkedList<Encomenda> encDaLoja = this.e.stream()
                        .filter(a -> a.getCodLoja().equals(l.getCodigo()))
                        .collect(Collectors.toCollection(LinkedList::new));
                l.setFilaEspera(encDaLoja);
            }

        }

    }


    public Conta parseUtilizador(String input) {
        String[] campos = input.split(",");
        String nome = campos[0];
        String codUtilizador = campos[1];
        double x = Double.parseDouble(campos[2]);
        double y = Double.parseDouble(campos[3]);
        return new Utilizador(codUtilizador, nome, x, y);
    }

    public Conta parseLoja(String input) {
        String[] campos = input.split(",");
        String codLoja = campos[0];
        String nomeLoja = campos[1];
        double x = Double.parseDouble(campos[2]);
        double y = Double.parseDouble(campos[3]);
        // dados por omissao
        return new Loja(codLoja, nomeLoja, x, y);
    }

    public Conta parseVoluntario(String input) {
        String[] campos = input.split(",");
        String codVol = campos[0];
        String nomeVol = campos[1];
        double x = Double.parseDouble(campos[2]);
        double y = Double.parseDouble(campos[3]);
        double raio = Double.parseDouble(campos[4]);
        //dados por omissao
        return new Voluntario(codVol, nomeVol, x, y, raio);
    }

    public Conta parseTransportadora(String input) {
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

    public Encomenda parseEnc(String input) {
        String[] campos = input.split(",");
        String cod = campos[0];
        String nome = campos[1];
        String loja = campos[2];
        double peso = Double.parseDouble(campos[3]);
        Encomenda e = new Encomenda(cod, nome, loja, peso);
        int i = 4;
        while (i < campos.length - 1) {
            String codProd = campos[i++];
            String codDesc = campos[i++];
            double quant = Double.parseDouble(campos[i++]);
            double val = Double.parseDouble(campos[i++]);
            LinhaEncomenda le = new LinhaEncomenda(codProd, codDesc, quant, val);
            e.addProduto(le.clone());
        }
        return e;

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
