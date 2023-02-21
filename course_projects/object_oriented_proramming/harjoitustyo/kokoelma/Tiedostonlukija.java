// Liitetään luokka oikeaan pakkaukseen.
package harjoitustyo.kokoelma;
// Tuodaan tarvittavat pakkaukset.
import harjoitustyo.dokumentit.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Tiedostonlukijaluokka, joka lukee tekstitiedostosta dokumentit ja lisää ne kokoelmaan
 * tai lukee tekstitiedostosta sanat ja lisää ne listaan.
 * 
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020.
 * <p>
 * @author Maria Seppänen, maria.seppanen@tuni.fi
 */
public class Tiedostonlukija {
    
    /**
     * Lukee tiedostosta dokumentit ja lisää ne kokoelmaan.
     * 
     * @param tiedostonNimi tiedoston nimi, josta löytyvät halutut dokumentit.
     * @return kokoelman, joka sisältää kaikki dokumentit.
     * @throws FileNotFoundException jos tiedostoa ei löydy.
     */
    public Kokoelma<Dokumentti> dokumentitKokoelmaan(String tiedostonNimi) throws FileNotFoundException {
        Kokoelma<Dokumentti> dokumentit = new Kokoelma<Dokumentti>();
        // Tarkistetaan, että tiedoston nimi on annettu. Virhetapauksessa heitetään
        // FileNotFoundException-poikkeus.
        if (tiedostonNimi == null) {
            throw new FileNotFoundException();
        }
        else {
            // Yritetään lukea tiedostoa, mutta varaudutaan virheeseen. 
            // Virhetilanteessa heitetään FileNotFoundException-poikkeus. 
            try { 
                // Liitetään tiedosto olioon ja luodaan tulostusvirtaolio.
                File tiedosto = new File(tiedostonNimi);
                Scanner tiedostonlukija = new Scanner(tiedosto);
            
                // Erotetaan polusta tiedoston nimen etuliite, joka kertoo
                // dokumenttien tyypin (vitsi vai uutinen).
                String[] polku = tiedostonNimi.split("\\\\");
                String[] etuliite = polku[polku.length-1].split("_");
            
                // Käydään tiedoston rivit yksitellen läpi.
                while (tiedostonlukija.hasNextLine()) {
                    // Jokainen yksittäinen rivi on yksi dokumentti.
                    String rivi = tiedostonlukija.nextLine();

                    // Jaetaan rivit osiin, jotta saadaan eroteltua dokumentin tiedot.
                    String[] osat = rivi.split("///");
                    int tunniste = Integer.parseInt(osat[0]);

                    // Tarkistetaan dokumentin tyyppi ja jatketaan sen mukaan.
                    if (etuliite[0].equals("jokes")) {
                        // Luodaan vitsi-olio käyttäen dokumentista saatuja tietoja.
                        // Tällöin osat[1] = vitsin tyyppi ja osat[2] = itse vitsi.
                        Dokumentti vitsi = new Vitsi(tunniste, osat[1], osat[2]);
                        // Kutsutaan lisää-metodia, joka lisää vitsin kokoelmaan.
                        dokumentit.lisää(vitsi);
                    }
                    if (etuliite[0].equals("news")) {
                        
                        // Nyt osat[1] = päivämäärä. Muunnetaan se tarvittavaan muotoon.
                        String[] luvut = osat[1].split("\\.");
                       
                        int pv = Integer.parseInt(luvut[0]);
                        int kk = Integer.parseInt(luvut[1]);
                        int vuosi = Integer.parseInt(luvut[2]);
                        // Muunnetaan numerot LocalDate-tyyppiseksi päivämääräksi.
                        LocalDate pvm = LocalDate.of(vuosi, kk, pv);

                        // Luodaan uutis-olio käyttäen dokumentista saatuja tietoja.
                        Dokumentti uutinen = new Uutinen(tunniste, pvm, osat[2]); 
                        // Kutsutaan lisää-metodia, joka lisää uutisen kokoelmaan.
                        dokumentit.lisää(uutinen); 
                    }
                }
                //Suljetaan tiedostonlukija.
                tiedostonlukija.close();
                // Palautetaan valmis kokoelma, jossa ovat kaikki tiedostossa olleet dokumentit.
                return dokumentit;
            }
            // Virhetilanteessa napataan virhe. 
            catch (Exception FileNotFoundException) {
                throw new FileNotFoundException();
            }
        }
    }

    /**
     * Lukee tiedostosta sanat ja lisää ne listaan.
     * 
     * @param tiedostonNimi sanat sisältävän tiedoston polku.
     * @return listan, joka sisältää kaikki tiedoston sisältämät sanat.
     * @throws FileNotFoundException jos tiedostoa ei löytynyt.
     */
    public LinkedList<String> sulkusanatListaan(String tiedostonNimi) throws FileNotFoundException {
        // Tarkistetaan, että tiedoston nimi on annettu. Virhetapauksessa heitetään
        // FileNotFoundException-poikkeus.
        if (tiedostonNimi != null) {
            // Varaudutaan virheeseen. Virhetilanteessa heitetään FileNotFoundExcpetion.
            try {
                // Luodaan sulkusanat-lista, jonne tiedostossa olevat sanat tulevat.
                LinkedList<String> sulkusanat = new LinkedList<String>();
                // Liitetään tiedosto olioon ja luodaan tulostusvirtaolio.
                File tiedosto = new File(tiedostonNimi);
                Scanner tiedostonlukija = new Scanner(tiedosto);
                // Käydään rivit yksitellen läpi.
                while (tiedostonlukija.hasNextLine()) {
                    // Tiedoston yksi rivi vastaa yhtä sanaa.
                    String rivi = tiedostonlukija.nextLine();
                    // Lisätään sana listalle kutsumalla add-metodia.
                    sulkusanat.add(rivi);
                }
                // Suljetaan tiedostonlukijaolio ja palautetaan valmis lista sanoista.
                tiedostonlukija.close();
                return sulkusanat;
            }
            catch (Exception FileNotFoundException) {
                throw new FileNotFoundException();
            }
        } 
        else {
            throw new FileNotFoundException();
        } 
    }
}