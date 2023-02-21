import harjoitustyo.dokumentit.*;
import harjoitustyo.kokoelma.*;
import harjoitustyo.kayttoliittyma.*;
import java.util.LinkedList;

/**
 * Ajoluokka.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020.
 * <p>
 * @author Maria Seppänen, maria.seppanen@tuni.fi
 */

 public class Oope2HT {
     /**
      * Ohjelman päämetodi.
      * @param args ensimmäinen parametri on polku tekstitiedostoon, joka sisältää halutut dokumentit ja
      toinen tekstitiedostoon, joka sisältää listan turhista täytesanoista
      * 
      */
     public static void main(String[] args) {
        // Luodaan viite käyttöliittymäolioon.
        Kayttoliittyma käyttis = new Kayttoliittyma();

        // Tarkistetaan, onko komentoriviparametreja oikea määrä. Jos ei, tulostetaan virheilmoitus
        // ja suljetaan ohjelma.
        if (args.length != 2) {
            käyttis.puuttuviaParametreja();
        }
        else {
            // Luodaan viitteet null-arvoiset muuttujat kokoelmalle, johon dokumentit tulevat sekä
            // listalle, jolle täytesanat tulevat.
            Kokoelma<Dokumentti> lista = null;
            LinkedList<String> sulkusanat = null;

            // Varaudutaan siihen, että tiedoston lukeminen ei onnistu.
            try {
                // Luodaan tiedostonlukijaolio, jolla pystytään lukemaan dokumentit ja sulkusanat 
                // tiedostoista. 
                Tiedostonlukija tiedostonlukija = new Tiedostonlukija();
                // Kutsutaan tiedostonlukijaoliota erikseen dokumenteille ja erikseen sulkusanoille. 
                lista = tiedostonlukija.dokumentitKokoelmaan(args[0]);
                sulkusanat = tiedostonlukija.sulkusanatListaan(args[1]);

                // Kutsutaan käyttöliittymäoliota.
                käyttis.kysyKäyttäjältä(lista, args[0], sulkusanat);
            }
            // Jos tiedoston lukeminen ei onnistunut, tulostetaan virheilmoitus ja suljetaan ohjelma.
            catch (Exception IllegalArgumentException) {
                käyttis.puuttuvaTiedosto();  
            }
            
        }
    }
}
