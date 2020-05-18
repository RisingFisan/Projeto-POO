public class Utilizador extends Conta {
    public Utilizador(String codigo, String nome, double x, double y) {
        super(codigo, nome, x, y);
    }

    public Utilizador(String codigo, String nome, double x, double y, String email, String password) {
        super(codigo, nome, x, y, email, password);
    }

    public Utilizador(Utilizador outro) {
        super(outro);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Utilizador\n");
        sb.append(super.toString());
        return sb.toString();
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    public boolean equals(Utilizador u) {
        return super.equals(u);
    }
}
