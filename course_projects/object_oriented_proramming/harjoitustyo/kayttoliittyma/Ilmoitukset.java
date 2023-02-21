// Liitetään luokka oikeaan pakkaukseen.
package harjoitustyo.kayttoliittyma;

/**
 * Ilmoitusluokka, jota kutsutaan, kun käyttäjälle halutaan sanoa jotain.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020.
 * <p>
 * @author Maria Seppänen, maria.seppanen@tuni.fi
 */
public class Ilmoitukset {

    /**
     * Tervehditään käyttäjää, kysytään komentoa tai hyvästellään käyttäjä.
     * @param luku 0, kun ohjelma käynnistetään, 1, kun kysytään komentoa, 2, kun ohjelma suljetaan
     */
    public void käynnistys(int luku) {
        if (luku == 0) {
            System.out.println("Welcome to L.O.T.");
        }
        else if (luku == 1) {
            System.out.println("Please, enter a command.");
        }
        else if (luku == 2) {
            System.out.println("Program terminated.");
        }
    }
    /**
     * Virheilmoitus, kun komentorivillä on väärä määrä parametreja.
     */
    public void puuttuviaParametreja() {
        System.out.println("Welcome to L.O.T.");
        System.out.println("Wrong number of command-line arguments!");
        System.out.println("Program terminated.");
    }
    /**
     * Virheilmoitus, kun komentorivillä annettu tiedoston nimi on väärä tai 
     * tiedostoa ei löydy.
     */
    public void puuttuvaTiedosto() {
        System.out.println("Welcome to L.O.T.");
        System.out.println("Missing file!");
        System.out.println("Program terminated.");
    }
}