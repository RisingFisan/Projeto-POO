import java.util.*;
public class Teste
{
    public static void main() {
       List <Integer> teste = new ArrayList<>();
       teste.add(3);
       teste.add(1);
       teste.add(2);
       teste.add(4);
       
       int tam = (int)teste.stream().reduce(0,(ac,v)->ac+1);
       System.out.println(teste);
       teste.sort(Comparator.reverseOrder());
       System.out.println(teste);
       
       Set<Integer> teste1 = new TreeSet<>(Comparator.naturalOrder());
        
    }
    
}
