import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
import java.awt.geom.Point2D;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Estado implements Serializable {
    private Contas utilizadores;
    private Contas voluntarios;
    private Contas transportadoras;
    private Contas lojas;
    private Encomendas encomendas;
    private Set<AbstractMap.SimpleEntry<String, String>> pedidosTransporte; // -> (codEnc, transp)
    
    
    public Estado() {
        this.utilizadores = new Contas();
        this.voluntarios = new Contas();
        this.transportadoras = new Contas();
        this.lojas = new Contas();
        this.encomendas = new Encomendas();
        this.pedidosTransporte = new HashSet<>();
    }

    public Estado(Estado outro) {
        this.utilizadores = outro.utilizadores;
        this.voluntarios = outro.voluntarios;
        this.transportadoras = outro.transportadoras;
        this.lojas = outro.lojas;
        this.encomendas = outro.encomendas;
        this.pedidosTransporte = outro.getPedidos();
    }

    public List<String> utilMaisFreq() {
        return this.utilizadores.getContas().values().stream().map(a -> (Utilizador) a)
                .sorted(Comparator.comparingInt(Utilizador::getEncTransportadas).reversed())
                .map(Conta::getCodigo)
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<String> transpMaisFreq() {
        return this.transportadoras.getContas().values().stream().map(a -> (Transportadora) a)
                .sorted(Comparator.comparingDouble(Transportadora::getKmPercorridos).reversed())
                .map(Conta::getCodigo)
                .limit(10)
                .collect(Collectors.toList());
    }

    public Set<AbstractMap.SimpleEntry<String, String>> getPedidos() {
        return new TreeSet<>(this.pedidosTransporte);
    }

    public boolean existeEmail(String s) {
        return this.utilizadores.existeEmail(s) || this.voluntarios.existeEmail(s)
                || this.lojas.existeEmail(s)
                || this.transportadoras.existeEmail(s);
    }

    public String newCode(TipoConta tipoConta) {
        String cod = "";
        if (tipoConta.equals(TipoConta.Utilizador)) cod = this.utilizadores.lastCode();
        else if (tipoConta.equals(TipoConta.Voluntario)) cod = this.voluntarios.lastCode();
        else if (tipoConta.equals(TipoConta.Loja)) cod = this.lojas.lastCode();
        else if (tipoConta.equals(TipoConta.Transportadora)) cod = this.transportadoras.lastCode();
        char first = cod.charAt(0);
        int newNum = Integer.parseInt(cod.substring(1)) + 1;
        return (first + String.valueOf(newNum));

    }

    public String newCodeEnc() {
        String cod = this.encomendas.getLastCode();
        char first = cod.charAt(0);
        int newNum = Integer.parseInt(cod.substring(1)) + 1;
        return (first + String.valueOf(newNum));

    }

    public void addToEncomendas(Encomenda e) {
        this.encomendas.addEnc(e.clone());
    }

    public void addNewEncToQueue(Encomenda e) {
        String cod = e.getCodLoja();
        Loja l = (Loja) this.lojas.getContaByCode(cod);
        l.addEncomenda(e.clone());
        /*Substituindo a loja antiga pela atual com a encomenda adicionada*/
        this.lojas.addConta(l);
    }

    public void solicitaEnc(String e) {
        this.encomendas.solicitaEnc(e);
    }

    public boolean lojaCodeValid(String loja) {
        return this.lojas.getContas().values().stream().anyMatch(a -> a.getCodigo().equals(loja));
    }

    public boolean encCodeValid(String enc) {
        return this.encomendas.getEnc().stream().anyMatch(a -> a.getCodEnc().equals(enc));
    }

    public boolean checkIfEntityWorkedForUser(String code, String user) {
        return this.encomendas.getEnc().stream().anyMatch(a -> (a.getCodUtil().equals(user) && a.getQuemTransportou().equals(code)));
    }

    public boolean encFoiSolicitada(String enc) {
        return this.encomendas.getEncomendaByCod(enc) != null;
    }

    public List<Encomenda> getHistoricoUser(String user) {
        return this.encomendas.getEnc().stream().filter(a -> a.getCodUtil().equals(user)).collect(Collectors.toList());

    }

    public void encomendaParaSerEntregue(String codEnc, String transp) {
        this.encomendas.quemTransportou(codEnc, transp);
        this.pedidosTransporte.removeIf(a -> a.getKey().equals(codEnc));
    }

    public void encomendaParaSerEntregueT(String codEnc, String transp) {
        this.encomendas.quemTransportou(codEnc, transp);
        AbstractMap.SimpleEntry<String, String> p = new AbstractMap.SimpleEntry<>(codEnc, transp);
        this.pedidosTransporte.add(p);
    }

    public Map<String, List<AbstractMap.SimpleEntry<String, Double>>> getTranspOptions(String user) {
        Map<String, List<AbstractMap.SimpleEntry<String, Double>>> res = new HashMap<>();

        for (AbstractMap.SimpleEntry<String, String> a : this.pedidosTransporte) {
            Encomenda e = this.encomendas.getEncomendaByCod(a.getValue());
            if (e.getCodUtil().equals(user)) {
                Conta util = this.utilizadores.getContaByCode(user);
                Transportadora transp = (Transportadora) this.transportadoras.getContaByCode(a.getValue());
                Conta loja = this.lojas.getContaByCode(e.getCodLoja());
                Point2D p = new Point2D.Double(util.getGPSx(), util.getGPSy());
                Point2D p1 = new Point2D.Double(transp.getGPSx(), transp.getGPSy());
                double jessica = transp.totalPreco(p.distance(loja.getGPSx(), loja.getGPSy()) + p1.distance(loja.getGPSx(), loja.getGPSy()));
                AbstractMap.SimpleEntry<String, Double> ans = new AbstractMap.SimpleEntry<>(a.getValue(), jessica);
                if (!res.containsKey(a.getKey())) {
                    List<AbstractMap.SimpleEntry<String, Double>> l = new ArrayList<>();
                    res.put(a.getKey(), l);
                }
                res.get(a.getKey()).add(ans);
            }
        }
        return res;
    }


    public void classifica(int c, String code) {
        Voluntario v = (Voluntario) this.voluntarios.getContaByCode(code);
        Transportadora t = (Transportadora) this.transportadoras.getContaByCode(code);
        if (v != null) {
            v.addClassif(c);
            this.voluntarios.addConta(v.clone());
        } else {
            t.addClassif(c);
            this.transportadoras.addConta(t.clone());
        }
    }

    // Voluntario

    public void alteraDispV(String code, boolean i) {
        Voluntario v = (Voluntario) this.voluntarios.getContaByCode(code);
        v.setDisponibilidade(i);
        this.voluntarios.addConta(v);
    }

    public Duration entregaEnc(Voluntario v,String enc) {
        double x,y;
        double dist;
        Encomenda e = this.encomendas.getEncomendaByCod(enc);
        Utilizador u = (Utilizador) this.utilizadores.getContaByCode(e.getCodUtil());
        u.addToEncTransp();
        this.utilizadores.addConta(u.clone());
        x = u.getGPSx();
        y = u.getGPSy();
        dist = Point.distance(x, y, v.getGPSx(), v.getGPSy());
        v.setGPS(x,y);
        v.setEncAceite("");
        u.addToEncTransp();
        this.voluntarios.addConta(v.clone());
        e.setFoiEntregue(true);
        e.setDistPercorrida(dist);
        this.encomendas.addEnc(e);
        LocalDateTime d = e.getData();
        return Duration.between(d, LocalDateTime.now());
    }

    /* Teste*/
    public double dist(String enc, String vol) {
        Encomenda e = this.encomendas.getEncomendaByCod(enc);
        String l = e.getCodLoja();
        Loja loja = (Loja) this.lojas.getContaByCode(l);
        Voluntario v = (Voluntario) this.voluntarios.getContaByCode(vol);
        return Point.distance(loja.getGPSx(), loja.getGPSy(), v.getGPSx(), v.getGPSy());
    }

    public boolean podeTranportar(String enc, String vol) {
        Encomenda e = this.encomendas.getEncomendaByCod(enc);
        String l = e.getCodLoja();
        Loja loja = (Loja) this.lojas.getContaByCode(l);
        Voluntario v = (Voluntario) this.voluntarios.getContaByCode(vol);
        return Point.distance(loja.getGPSx(), loja.getGPSy(), v.getGPSx(), v.getGPSy()) < v.getRaio() && (v.aceitoTransporteMedicamentos() == e.aceitoTransporteMedicamentos());
    }

    public Map<String, Double> transpInfo(Voluntario v) {
        Map<String, Double> res = new HashMap<>();
        for (Encomenda e : this.encomendas.getEnc()) {
            String l = e.getCodLoja();
            String u = e.getCodUtil();
            Utilizador util = (Utilizador) this.utilizadores.getContaByCode(u);
            Loja loja = (Loja) this.lojas.getContaByCode(l);
            if (Point.distance(loja.getGPSx(), loja.getGPSy(), util.getGPSx(), util.getGPSy()) < v.getRaio()
                    && Point.distance(loja.getGPSx(), loja.getGPSy(), v.getGPSx(), v.getGPSy()) < v.getRaio()
                    && (v.aceitoTransporteMedicamentos() == e.aceitoTransporteMedicamentos()) && encFoiSolicitada(e.getCodEnc())) {
                res.put(e.getCodEnc(), Point.distance(loja.getGPSx(), loja.getGPSy(), v.getGPSx(), v.getGPSy()));
            }
        }
        return res;
    }

    // Transportadora

    public void alteraDispT(String code, int i) {
        Transportadora v = (Transportadora) this.transportadoras.getContaByCode(code);
        if (i == 1) v.setDisponibilidade(true);
        if (i == 2) v.setDisponibilidade(false);
        this.transportadoras.addConta(v);
    }

    /*public Map.Entry<Duration, Double> entregaEnc(Transportadora t, String enc) {
        double x,y;
        double dist;
        Encomenda e = this.encomendas.getEncomendaByCod(enc);
        Utilizador u = (Utilizador) this.utilizadores.getContaByCode(e.getCodUtil());
        u.addToEncTransp();
        this.utilizadores.addConta(u.clone());
        e.setFoiEntregue(true);
        this.encomendas.addEnc(e);
        LocalDateTime d = e.getData();
        Double custo = e.getDistPercorrida() * t.getPrecoPorKm();
        Duration data = Duration.between(d, LocalDateTime.now());
        return new AbstractMap.SimpleEntry<>(data, custo);
    }*/
    
    //Transportadora realiza a rota e devolve uma map com a encomenda,o tempo que demorou e o custo do transporte
    public Map<String,AbstractMap.SimpleEntry<Double, Double>> entregaEncs(Transportadora t) {
        List<String> l = t.getEncAceites();
        double[] distancias = new double[l.size()];
        List<Encomenda> le = new ArrayList<>();
        Map<String,AbstractMap.SimpleEntry<Double, Double>> map = new HashMap<>();
        for (String e : l) {
            Encomenda enc = this.encomendas.getEncomendaByCod(e);
            le.add(enc);
        }
        Comparator<Encomenda> compareByDist = (Encomenda o1, Encomenda o2) -> Double.compare(this.lojas.getContaByCode(o1.getCodLoja()).calcDist(t.getGPSx(),t.getGPSy()),this.lojas.getContaByCode(o2.getCodLoja()).calcDist(t.getGPSx(),t.getGPSy()));
        Collections.sort(le, compareByDist);
        for(Encomenda e: le) {
            Loja loja = (Loja) this.lojas.getContaByCode(e.getCodLoja());
            double dist = loja.calcDist(t.getGPSx(),t.getGPSy());
            t.setGPS(loja.getGPSx(),loja.getGPSy());
            distancias[le.indexOf(e)] = dist;
        }
        Comparator<Encomenda> compareByDistU = (Encomenda o1, Encomenda o2) -> Double.compare(this.utilizadores.getContaByCode(o1.getCodUtil()).calcDist(t.getGPSx(),t.getGPSy()),this.utilizadores.getContaByCode(o2.getCodUtil()).calcDist(t.getGPSx(),t.getGPSy()));
        Collections.sort(le, compareByDistU);
        for(Encomenda e: le) {
            Utilizador util = (Utilizador) this.lojas.getContaByCode(e.getCodUtil());
            //adiciona uma encomenda transportada
            util.addToEncTransp();
            this.utilizadores.addConta(util.clone());
            double dist = util.calcDist(t.getGPSx(),t.getGPSy());
            t.setGPS(util.getGPSx(),util.getGPSy());
            distancias[le.indexOf(e)] += dist;
        }
        int i = 0;
        double custo = t.getPrecoPorKm();
        double vel = t.getVelocidade();
        for (Encomenda e : le){
            e.setFoiEntregue(true);
            this.encomendas.addEnc(e);
            double distanc = distancias[i++];
            //v=d/t == t =d/v
            AbstractMap.SimpleEntry<Double, Double> me = new AbstractMap.SimpleEntry<Double, Double>(distanc/vel,distanc*custo);
            map.put(e.getCodEnc(),me);
            
        }
        //apagar todas as encomendas da transportadora
        t.setEncAceites(new ArrayList<>());
        
        return map;
    }
    
    public Conta getContaFromCredentials(String email, String password) {
        Conta conta = this.utilizadores.getContaByEmail(email);
        if (conta != null && conta.checkPassword(password)) return conta;

        conta = this.voluntarios.getContaByEmail(email);
        if (conta != null && conta.checkPassword(password)) return conta;

        conta = this.transportadoras.getContaByEmail(email);
        if (conta != null && conta.checkPassword(password)) return conta;

        conta = this.lojas.getContaByEmail(email);
        if (conta != null && conta.checkPassword(password)) return conta;

        return null;
    }

    public Map<String, List<Double>> transpInfoT(Transportadora v) {
        Map<String, List<Double>> res = new HashMap<>();
        for (Encomenda e : this.encomendas.getEnc()) {
            String l = e.getCodLoja();
            String u = e.getCodUtil();
            Utilizador util = (Utilizador) this.utilizadores.getContaByCode(u);
            Loja loja = (Loja) this.lojas.getContaByCode(l);
            if (Point.distance(loja.getGPSx(), loja.getGPSy(), util.getGPSx(), util.getGPSy()) < v.getRaio()
                    && Point.distance(loja.getGPSx(), loja.getGPSy(), v.getGPSx(), v.getGPSy()) < v.getRaio()
                    && (v.aceitoTransporteMedicamentos() == e.aceitoTransporteMedicamentos()) && encFoiSolicitada(e.getCodEnc())) {
                List<Double> list = new ArrayList<>();
                double d = Point.distance(loja.getGPSx(), loja.getGPSy(), v.getGPSx(), v.getGPSy());
                list.add(d);
                list.add(d * v.getPrecoPorKm());
                res.put(e.getCodEnc(), list);
            }
        }
        return res;
    }


    public void addConta(Conta conta) {
        if (conta instanceof Utilizador) this.utilizadores.addConta(conta);
        else if (conta instanceof Voluntario) this.voluntarios.addConta(conta);
        else if (conta instanceof Loja) this.lojas.addConta(conta);
        else this.transportadoras.addConta(conta);
    }

    public Estado clone() {
        return new Estado(this);
    }

    public void saveEstado() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Estado.obj"));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }


    //carregar de ficheiro objeto
    public void loadEstadoObj(String file) throws IOException, ClassNotFoundException {
        Estado e = loadAux(file);
        this.utilizadores = e.utilizadores;
        this.voluntarios = e.voluntarios;
        this.lojas = e.lojas;
        this.transportadoras = e.transportadoras;
        this.encomendas = e.encomendas;
        this.pedidosTransporte = e.pedidosTransporte;
    }

    public Estado loadAux(String file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Estado e = (Estado) ois.readObject();
        ois.close();
        return e;

    }

    /*--------------------FUNCOES QUE ESTAVAM NA PARSE-----------------------*/
    //ler do ficheiro log


    public void loadEstadoLogs() {

        List<String> linhas = lerFicheiro("db/logs.csv");
        List<Voluntario> listaVol = new ArrayList<>();
        List<Transportadora> listaTransportadora = new ArrayList<>();
        List<Loja> listaLoja = new ArrayList<>();
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
                    Loja l = parseLoja(linhaPartida[1]);
                    //System.out.println(l.toString());
                    listaLoja.add(l);
                    break;
                case "Voluntario":
                    Voluntario v = parseVoluntario(linhaPartida[1]);
                    //System.out.println(v.toString());
                    listaVol.add(v);
                    break;
                case "Transportadora":
                    Transportadora t = parseTransportadora(linhaPartida[1]);
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
                    System.out.println("Linha invalida.");
                    break;
            }
        }


        putEncInQueues(listaLoja, listaEnc);

        //Distribuir aleatoriamente encomendas aceites pelas entidades transportadoras(tendo em atencao o raio)
        distributeEncAceites(listaEnc, listaAceite, listaTransportadora, listaVol, listaLoja);
        System.out.println(this.utilizadores);

        listaVol.forEach(a -> this.voluntarios.addConta(a));
        listaTransportadora.forEach(a -> this.transportadoras.addConta(a));
        listaLoja.forEach(a -> this.lojas.addConta(a));
        listaEnc.forEach(a -> this.encomendas.addEnc(a));


        System.out.println("----Ficheiros carregados!---");

    }

    //Meio estranha.A ideia é que faça com que algumas transportadoras transportem mais que uma encomenda
    public void distributeEncAceites(List<Encomenda> encomendas, List<String> encAceites, List<Transportadora> t, List<Voluntario> vols, List<Loja> l) {
        for (String s : encAceites) {
            boolean found = false;
            Encomenda e = encomendas.stream().filter(a -> a.getCodEnc().equals(s)).findFirst().orElse(null);
            Loja loja = l.stream().filter(a -> a.getCodigo().equals(e.getCodLoja())).findFirst().orElse(null);
            for (Voluntario vol : vols) {
                double raioV = vol.getRaio();
                Point2D ponto = new Point2D.Double(vol.getGPSx(), vol.getGPSy());
                if (vol.getDisponibilidade() && ponto.distance(loja.getGPSx(), loja.getGPSy()) <= raioV) {
                    e.setQuemTransportou(vol.getCodigo());
                    vol.addEncomenda(s);
                    found = true;
                    break;
                }
            }
            if(!found) {
                for (Conta c : t) {
                    Transportadora tps = (Transportadora) c;
                    double raioT = tps.getRaio();
                    Point2D ponto = new Point2D.Double(tps.getGPSx(), tps.getGPSy());
                    if (ponto.distance(loja.getGPSx(), loja.getGPSy()) <= raioT) {
                        tps.setMaxCapacidade(tps.getEncAceites().size() + 1);
                        e.setQuemTransportou(tps.getCodigo());
                        tps.addEncomenda(s);
                        break;
                    }
                }
            }
        }
    }


    public void putEncInQueues(List<Loja> lj, List<Encomenda> encs) {
        for (Loja loj : lj) {
            List<Encomenda> aux = encs.stream().filter(a -> a.getCodLoja().equals(loj.getCodigo())).collect(Collectors.toList());
            loj.setFilaEspera(aux);
        }
    }


    public Utilizador parseUtilizador(String input) {
        String[] campos = input.split(",");
        String nome = campos[1];
        String codUtilizador = campos[0];
        double x = Double.parseDouble(campos[2]);
        double y = Double.parseDouble(campos[3]);
        return new Utilizador(codUtilizador, nome, x, y);
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
