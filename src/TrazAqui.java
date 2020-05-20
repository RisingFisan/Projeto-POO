
import java.io.*;
public class TrazAqui {
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
    
    public boolean existeEmail(String s){
        return this.estado.existeEmail(s);
    }
    
    public String getNewCode(TipoConta t){
    return estado.newCode(t);
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
