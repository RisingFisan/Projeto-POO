
/**
 * Escreva a descricao da classe ComparatorCod aqui.
 * 
 * @author (seu nome) 
 * @version (numero de versao ou data)
 */
import java.util.Comparator;
public class ComparatorCod implements Comparator<String> {
    
    public int compare(String a, String b) {
        String a1 = a.substring(1);
        String b1 = b.substring(1);
        return a1.compareTo(b1);
    }
    
}
