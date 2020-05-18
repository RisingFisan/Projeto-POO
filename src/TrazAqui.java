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
}
