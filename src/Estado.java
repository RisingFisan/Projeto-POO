import java.io.Serializable;
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
import java.util.Map;
import java.io.*;
import java.util.*;
import java.awt.geom.Point2D;

public class Estado implements Serializable {
    private Contas utilizadores;
    private Contas voluntarios;
    private Contas transportadoras;
    private Contas lojas;
    private Encomendas encomendas;

    public Estado() {
        this.utilizadores = new Contas();
        this.voluntarios = new Contas();
        this.transportadoras = new Contas();
        this.lojas = new Contas();
        this.encomendas = new Encomendas();
    }
    
    public Estado (Estado outro){
       this.utilizadores = outro.utilizadores;
       this.voluntarios = outro.voluntarios;
       this.transportadoras =outro.transportadoras;
       this.lojas = outro.lojas;
       this.encomendas = outro.encomendas;
    }
    

    public Conta getContaFromCredentials(String email, String password) {
        Conta conta = this.utilizadores.getContaByEmail(email);
        
        if(conta == null || !conta.checkPassword(password)) {
            conta = this.voluntarios.getContaByEmail(email);
            
        if (conta == null || !conta.checkPassword(password)){
            conta = this.transportadoras.getContaByEmail(email);
            
        if(conta == null || !conta.checkPassword(password)){
            conta = this.lojas.getContaByEmail(email);
             }
        else return null;     
                 
          }
       }
        
       return conta.clone();
    }
    
    public Estado clone(){
        return new Estado(this);
    }

    public void loadEstado() {
        parse();

    }

    public void saveEstado() throws FileNotFoundException, IOException  {
        try {
             ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream("Estado.txt"));
             oos.writeObject(this);
             oos.flush();
             oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       

    }
    
    
    //Outras opcao de leitura --Nao tenho a certeza se está certo.
     public void loadEstado1(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
         Estado e = parse1();
         this.utilizadores = e.utilizadores;
         this.voluntarios = e.voluntarios;
         this.lojas = e.lojas;
         this.transportadoras = e.transportadoras;
      }
      
    public Estado parse1() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream (new FileInputStream("estado.txt"));
        Estado e = (Estado) ois.readObject();
        ois.close();
        return e;
    
    }
    
    /*--------------------FUNCOES QUE ESTAVAM NA PARSE-----------------------*/
    
   
    
    public void parse() {
        
        List<String> linhas = lerFicheiro("log.txt");
        List<Conta> listaVol = new ArrayList<>();
        List<Conta> listaTransportadora = new ArrayList<>();
        List<Conta> listaLoja = new ArrayList<>();
        List<Encomenda> listaEnc = new ArrayList<>();
        List<String> listaAceite = new ArrayList<>();
        
        for (String linha : linhas) {
            String[] linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Utilizador":
                    Conta u = parseUtilizador(linhaPartida[1]);
                    //System.out.println(u.toString());
                    this.utilizadores.addConta(u);
                    break;
                case "Loja":
                    Conta l = parseLoja(linhaPartida[1]);
                    //System.out.println(l.toString());
                    listaLoja.add(l);
                    break;
                case "Voluntario":
                    Conta v = parseVoluntario(linhaPartida[1]);
                    //System.out.println(v.toString());
                    listaVol.add(v);
                    break;
                case "Transportadora":
                    Conta t = parseTransportadora(linhaPartida[1]);
                    //System.out.println(t.toString());
                    listaTransportadora.add(t);
                    break;
                case "Encomenda":
                    Encomenda enc = parseEnc(linhaPartida[1]);
                    listaEnc.add(enc);
                    break;
                case "Aceite":
                    listaAceite.add(linhaPartida[1]);
                     break;
                default:
                    System.out.println("Linha inválida.");
                    break;
              }
            }
            
            
            putEncInQueues(listaLoja,listaEnc);
            
            //Distribuir aleatoriamente encomendas aceites pelas entidades transportadoras(tendo em atencao o raio)
            distributeEncAceites(listaEnc,listaAceite,listaTransportadora,listaVol,listaLoja);
            System.out.println(listaVol);
            System.out.println(listaTransportadora);
            //System.out.println(listaAceite);
            
            listaVol.stream().forEach(a->this.voluntarios.addConta(a));
            listaTransportadora.stream().forEach(a->this.transportadoras.addConta(a));
            listaLoja.stream().forEach(a->this.lojas.addConta(a));
            listaEnc.stream().forEach(a->this.encomendas.addEnc(a));
            
            
            System.out.println("----Ficheiros carregados!---");

    }
    
  //Meio estranha.A ideia é que faça com que algumas transportadoras transportem mais que uma encomenda
    public void distributeEncAceites(List<Encomenda>encomendas,List<String> encAceites,List<Conta> t,List<Conta> v,List<Conta> l){
        int found=1;
        for (String s : encAceites){
            found = 0;
            Encomenda e = encomendas.stream().filter(a->a.getCodEnc().equals(s)).findFirst().orElse(null);
            Conta loja = l.stream().filter(a->a.getCodigo().equals(e.getCodLoja())).findFirst().orElse(null);
            for(Conta c : v){
                Voluntario vol = (Voluntario) c;
                double raioV = vol.getRaio();
                Point2D ponto = new Point2D.Double(vol.getGPSx(),vol.getGPSy());
                if (vol.getDisponibilidade() && ponto.distance(loja.getGPSx(),loja.getGPSy())<=raioV) {
                    vol.addEncomenda(s);
                    found=1;
                    break;
                }
            }
            if (found==1) continue;
            else{
                for(Conta c : t){
                Transportadora tps = (Transportadora) c;
                double raioT = tps.getRaio();
                Point2D ponto = new Point2D.Double(tps.getGPSx(),tps.getGPSy());
                if (ponto.distance(loja.getGPSx(),loja.getGPSy())<=raioT) {
                    tps.setMaxCapacidade(tps.getEncAceites().size()+1);
                    tps.addEncomenda(s);
                    break;}
            }
            }
        }
    }
    
   
    
    public void putEncInQueues(List<Conta> lj,List<Encomenda> encs) {
       for (Conta c : lj){
            Loja loj = (Loja) c; 
            List<Encomenda> aux = encs.stream().filter(a->a.getCodLoja().equals(loj.getCodigo())).collect(Collectors.toList());
            loj.setFilaEspera(aux);
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
