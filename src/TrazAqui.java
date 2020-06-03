import java.util.*;
import java.io.*;
import javafx.util.Pair;
import java.time.Duration;
 
public class TrazAqui implements Serializable {
    private Conta contaLoggedIn;
    private Estado estado;

    public TrazAqui() {
        this.estado = new Estado();
        this.contaLoggedIn = null;
    }

    public boolean login(String email, String password) {
        Conta conta = this.estado.getContaFromCredentials(email, password);
        if(conta != null) {
            this.contaLoggedIn = conta;
            return true;
        }
        else return false;
    }

    public void registo(Conta conta) {
       this.estado.addConta(conta);
       this.contaLoggedIn = conta.clone();
    }
    public String getCodLoggedIn() {
        return this.contaLoggedIn.getCodigo();
    }
    
    public TipoConta getTipoConta(){
        if (this.contaLoggedIn instanceof Utilizador) return TipoConta.Utilizador;
        else if (this.contaLoggedIn instanceof Voluntario) return TipoConta.Voluntario;
        else if (this.contaLoggedIn instanceof Transportadora) return TipoConta.Transportadora;
        else return TipoConta.Loja;
    }
    
    public void addEncToEstado(Encomenda e){
        this.estado.addNewEncToQueue(e.clone());
        this.estado.addToEncomendas(e.clone());
    }
    
    public void encomendaParaSerEntregue(String codEnc,String transportadora){
        ((Utilizador)this.contaLoggedIn).addToEncTransp();
        this.estado.encomendaParaSerEntregue(codEnc,transportadora);
    }
    
    
    
    public boolean isValidoLoja(String loja){
        return this.estado.lojaCodeValid(loja);
    }
    
    public boolean isValidCodeEnc(String enc){
        return this.estado.encCodeValid(enc);
    }
    
    public boolean checkIfEntityWorkedForUser(String code){
        return this.estado.checkIfEntityWorkedForUser(code,this.contaLoggedIn.getCodigo());
    }
    
    public void classificaEntidade(int c,String code){
        this.estado.classifica(c,code);
    }
    
    public boolean existeEmail(String s){
        return this.estado.existeEmail(s);
    }
    
    public String getNewCode(TipoConta t){
    return estado.newCode(t);
    }
    public String getNewCodeEnc(){
    return estado.newCodeEnc();
    }
    
    public List<Encomenda> getHistoricoUser(){
       return this.estado.getHistoricoUser(this.contaLoggedIn.getCodigo());
    }
    
    public Map<String,List<Pair <String, Double>>> getTranspOptions(){
        return this.estado.getTranspOptions(this.contaLoggedIn.getCodigo());
    }
    
    public void solicitaEnc(String e) {
       this.estado.solicitaEnc(e);
    }
    
    // Voluntario
    
    public boolean getDisp() {
        Voluntario v  = (Voluntario) this.contaLoggedIn;
        return v.getDisponibilidade();
    }
    
    public void alteraDisp(int i) {
        this.estado.alteraDisp(this.contaLoggedIn.getCodigo(), i);
    }
    
    public boolean pedirTranspVol(String enc) {
        Voluntario v  = (Voluntario) this.contaLoggedIn;
        if (!this.estado.encFoiSolicitada(enc) || !v.getEncAceite().equals("")) return false;
        this.estado.encomendaParaSerEntregue(enc, this.contaLoggedIn.getCodigo());
        v.setEncAceite(enc);
        return true;
    }
    
    public Duration entregaEnc () {
        Voluntario v  = (Voluntario) this.contaLoggedIn;
        String enc = v.getEncAceite();
        if (enc.equals("")) return null;
        return this.estado.entregaEnc(enc);
    }
    
    public void carregaLogs(){
        this.estado.loadEstadoLogs();
    }
    
    public void salvaEstadoObj() throws FileNotFoundException, IOException{
        this.estado.saveEstado();
    }
    
    public void carregaEstadoObj() throws FileNotFoundException, IOException, ClassNotFoundException{
        this.estado.loadEstadoObj("Estado.obj");
    }
  }
