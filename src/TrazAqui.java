import java.util.*;
import java.io.*;
import java.time.Duration;
import java.util.stream.Collectors;


public class TrazAqui implements Serializable {
    private Conta contaLoggedIn;
    private Estado estado;

    public TrazAqui() {
        this.estado = new Estado();
        this.contaLoggedIn = null;
    }

    public boolean login(String email, String password) {
        Conta conta = this.estado.getContaFromCredentials(email, password);
        if (conta != null) {
            this.contaLoggedIn = conta;
            return true;
        } else return false;
    }

    public void registo(Conta conta) {
        this.estado.addConta(conta);
        this.contaLoggedIn = conta.clone();
    }

    public String getCodLoggedIn() {
        return this.contaLoggedIn.getCodigo();
    }

    public TipoConta getTipoConta() {
        if (this.contaLoggedIn instanceof Utilizador) return TipoConta.Utilizador;
        else if (this.contaLoggedIn instanceof Voluntario) return TipoConta.Voluntario;
        else if (this.contaLoggedIn instanceof Transportadora) return TipoConta.Transportadora;
        else return TipoConta.Loja;
    }
    
    public List<String> utilMaisFreq() {
        return this.estado.utilMaisFreq();
    }
    
    public List<String> transpMaisFreq() {
        return this.estado.transpMaisFreq();
    }
    
    public void addEncToEstado(Encomenda e){
        this.estado.addNewEncToQueue(e.clone());
        this.estado.addToEncomendas(e.clone());
    }
    
    public void encomendaParaSerEntregue(String codEnc,String transportadora) {
        ((Utilizador) this.contaLoggedIn).addToEncTransp();
        this.estado.encomendaParaSerEntregue(codEnc, transportadora);
    }

    public boolean isValidoLoja(String loja) {
        return this.estado.lojaCodeValid(loja);
    }

    public boolean isValidCodeEnc(String enc) {
        return this.estado.encCodeValid(enc);
    }

    public boolean checkIfEntityWorkedForUser(String code) {
        return this.estado.checkIfEntityWorkedForUser(code, this.contaLoggedIn.getCodigo());
    }

    public void classificaEntidade(int c, String code) {
        this.estado.classifica(c, code);
    }

    public boolean existeEmail(String s) {
        return this.estado.existeEmail(s);
    }

    public String getNewCode(TipoConta t) {
        return estado.newCode(t);
    }

    public String getNewCodeEnc() {
        return estado.newCodeEnc();
    }

    public List<Encomenda> getHistoricoUser() {
        return this.estado.getHistoricoUser(this.contaLoggedIn.getCodigo());
    }

    public Map<String, List<AbstractMap.SimpleEntry<String, Double>>> getTranspOptions() {
        return this.estado.getTranspOptions(this.contaLoggedIn.getCodigo());
    }

    public void solicitaEnc(String e) {
        this.estado.solicitaEnc(e);
    }
    
    // Voluntario
    
    public boolean getDisp() {
        TranspVolunt v  = (TranspVolunt) this.contaLoggedIn;
        return v.getDisponibilidade();
    }
    
    public void alteraDispV(boolean i) {
        Voluntario v  = (Voluntario) this.contaLoggedIn;
        v.setDisponibilidade(i); 
        this.estado.alteraDispV(this.contaLoggedIn.getCodigo(), i);
    }
    /* Teste */
    public double dist (String enc) {
        return this.estado.dist(enc, this.contaLoggedIn.getCodigo());
    }
    
    public boolean pedirTranspVol(String enc) {
        Voluntario v  = (Voluntario) this.contaLoggedIn;
        if (!this.estado.encFoiSolicitada(enc) || !v.getEncAceite().equals("") || !this.estado.podeTranportar(enc, this.contaLoggedIn.getCodigo())) return false;
        this.estado.encomendaParaSerEntregue(enc, this.contaLoggedIn.getCodigo());
        v.setEncAceite(enc);
        this.contaLoggedIn = v.clone();
        return true;
    }
    
    public Duration entregaEnc () {
        Voluntario v  = (Voluntario) this.contaLoggedIn;
        String enc = v.getEncAceite();
        if (enc.equals("")) return null;
        return this.estado.entregaEnc(v,enc);
    }
    
    
    public Map<String,Double> transpInfo () {
        Voluntario v  = (Voluntario) this.contaLoggedIn;
        return this.estado.transpInfo(v);
    }
    
    // Transportadora
    
    public void alteraDispT(int i) {
        this.estado.alteraDispT(this.contaLoggedIn.getCodigo(), i);
    }
    
    public Map<String,List<Double>> transpInfoT () {
        Transportadora t  = (Transportadora) this.contaLoggedIn;
        return this.estado.transpInfoT(t); 
    } 
    
    public boolean pedirTranspT(String enc) {
        Transportadora v  = (Transportadora) this.contaLoggedIn;
        if (!this.estado.encFoiSolicitada(enc) || v.getEncAceites().size() >= v.getMaxCapacidade() || !this.estado.podeTranportar(enc, this.contaLoggedIn.getCodigo())) return false;
        this.estado.encomendaParaSerEntregueT(enc, this.contaLoggedIn.getCodigo());
        v.addEncomenda(enc);
        this.contaLoggedIn = v.clone();
        return true;
    }
    
    /*public Map.Entry<Duration,Double> entregaEnc (String enc) {
        Transportadora v  = (Transportadora) this.contaLoggedIn;
        boolean b = v.getEncAceites().contains(enc);
        if(!b) return null;
        return this.estado.entregaEnc(v,enc);
    }*/

    public Map<String,AbstractMap.SimpleEntry<Double, Double>> entregaEncs() {
        return this.estado.entregaEncs((Transportadora)this.contaLoggedIn);
    }
    
    // Loja

    public List<Encomenda> listaEncsLoja() {
        if(contaLoggedIn instanceof Loja) {
            Loja l = (Loja) contaLoggedIn;
            return new ArrayList<>(l.getFilaEspera());
        }
        else return null;
    }

    public void carregaLogs() {
        this.estado.loadEstadoLogs();
    }

    public void salvaEstadoObj() throws IOException {
        this.estado.saveEstado();
    }

    public void carregaEstadoObj() throws IOException, ClassNotFoundException {
        this.estado.loadEstadoObj("Estado.obj");
    }
}
