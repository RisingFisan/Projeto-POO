public class TrazAqui {
    private Conta contaLoggedIn;
    private Estado estado;

    public TrazAqui() {
        this.estado = new Estado();
        this.contaLoggedIn = null;
    }

    public void login(String email, String password) {
        Conta conta = this.estado.getContaFromCredentials(email, password);
        if(conta == null) System.out.println("Erro - Credenciais inválidas.");
        else contaLoggedIn = conta;
    }
}
