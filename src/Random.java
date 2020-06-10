
/**
 * Escreva a descrição da classe TemposFilaEspera aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public interface Random {
    default double calculaTempo(int count) {
        double time;
        if(count>1 && count<3) time = (double) (Math.random() * (15 - 5)) + 5;
        else if(count>=3 && count<7) time = (double) (Math.random() * (25 - 15)) + 15;
        else time = (double) (Math.random() * (35 - 25)) + 25;
        return time;
    }
    
    default double calculaVelocidadeVol() {
        double v;
        v = (double) (Math.random() * (55 - 35)) + 35;
        return v;
    }
    
    default double calculaVelocidadeTransp() {
        double v;
        v = (double) (Math.random() * (75 - 35)) + 35;
        return v;
    }
}
