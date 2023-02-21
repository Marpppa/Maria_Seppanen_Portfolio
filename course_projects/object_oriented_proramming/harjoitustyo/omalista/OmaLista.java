// Liitetään luokka omalista-pakkaukseen.
package harjoitustyo.omalista;
// Tuodaan tarvittavat pakkaukset.
import harjoitustyo.apulaiset.*;
import java.util.LinkedList;

/*
 * Oma lista -luokka.
 * 
 */
@SuppressWarnings({"unchecked"})
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {
    /**
     * 
     * @param uusi  
     */
    public void lisää(E uusi) throws IllegalArgumentException {
        if (uusi == null) {
            throw new IllegalArgumentException();
        }
        else {
            try {
                Comparable alkio = (Comparable)uusi;
                if (size() == 0) {
                    add(uusi);
                }
                else if (size() == 1) {
                    if (alkio.compareTo((Comparable)get(0)) >= 0) {
                        add(uusi);
                    }
                    else if (alkio.compareTo((Comparable)get(0)) < 0) {
                        addFirst(uusi);
                    }
                    else {
                        System.out.println("well fuck1");
                    }
                }
                else if (size() > 1) {
                    int lkm = 0;
                    for (int i = 0; i < size(); i++) {
                        if (alkio.compareTo((Comparable)get(i)) >= 0) {
                            lkm++;
                        }
                    }
                    add(lkm, uusi);
                }
                else {
                    System.out.println("well fuck3");
                }
            }
            catch (Exception E) {
                throw new IllegalArgumentException();
            }
        }
    }
}