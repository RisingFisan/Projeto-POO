
/**
 * Escreva a descrição da classe ComparatorCod aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Comparator;
public class ComparatorCod implements Comparator<String> {
    
    public int compare(String a, String b) {
        String a1 = a.substring(1);
        String b1 = b.substring(1);
        return a1.compareTo(b1);
    }
    
}
