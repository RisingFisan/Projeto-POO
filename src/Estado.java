import java.io.Serializable;

public class Estado implements Serializable {
    private Contas utilizadores;
    private Contas voluntarios;
    private Contas transportadoras;
    private Contas lojas;

    public Estado() {
        this.utilizadores = new Contas();
        this.voluntarios = new Contas();
        this.transportadoras = new Contas();
        this.lojas = new Contas();
    }

    public Conta getContaFromCredentials(String email, String password) {
        Conta conta = this.utilizadores.getContaByEmail(email);
        if(conta == null || !conta.checkPassword(password)) return null;
        else return conta.clone();
    }

    public void loadEstado() {

    }

    public void saveEstado() {

    }
}
