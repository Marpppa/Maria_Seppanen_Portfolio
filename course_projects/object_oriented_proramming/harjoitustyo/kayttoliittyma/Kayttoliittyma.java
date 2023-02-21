// Liitetään luokka oikeaan pakkaukseen.
package harjoitustyo.kayttoliittyma;
// Tuodaan tarvittavat pakkaukset.
import harjoitustyo.dokumentit.*;
import harjoitustyo.kokoelma.*;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Konkreettinen käyttöliittymäluokka. 
 * Kommunikaatio ohjelman ja käyttäjän välillä tapahtuu pääosin täällä.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020.
 * <p>
 * @author Maria Seppänen, maria.seppanen@tuni.fi
 */

public class Kayttoliittyma extends Ilmoitukset {
    // Luodaan kokoelma-olio ja viite olioon.
    Kokoelma<Dokumentti> kokoelma = new Kokoelma<Dokumentti>();

    /**
     * Kysytään käyttäjältä komento, ja toteutetaan se.
     * Jos komento on väärä, tulostetaan "Error!" tai pyydetään suoraan uutta komentoa.
     * 
     * @param lista lista, joka sisältää kaikki dokumenntit
     * @param tiedostonNimi tiedoston nimi
     * @param sulkusanat ohjelman käynnistyksen yhteydessä annettu lista sanoista
     */

    public void kysyKäyttäjältä(Kokoelma<Dokumentti> lista, String tiedostonNimi, LinkedList<String> sulkusanat) {
        // Tervehditään käyttäjää ja toivotetaan se tervetulleeksi Oopen viimeiseen koitokseen.
        käynnistys(0);

        // Luodaan scanner-olio, jolla pystytään lukemaan käyttäjän syötteet.
        Scanner lukija = new Scanner(System.in);

        // Luodaan boolean-tyyppiset muuttujat, jatketaan syötteenlukuluuppia varten, echo
        // echoa, eli syötteen toistamista varten.
        boolean jatketaan = true;
        boolean echo = false;

        // Luodaan silmukka, jossa kysytään käyttäjältä syötteitä ja reagoidaan niihin.
        while (jatketaan) {
            // Kysytään käyttäjältä komento.
            käynnistys(1);
            
            String syöte = lukija.nextLine();
            if (syöte.equals("echo")) {
                echo = !echo;
            }  
            if (echo) {
                System.out.println(syöte);
            }
            if (syöte.equals("quit")) {
                käynnistys(2);
                jatketaan = false;
            }
            else if (syöte.equals("reset")) {
                Tiedostonlukija tiedostonlukija = new Tiedostonlukija();
                try {
                    kokoelma = tiedostonlukija.dokumentitKokoelmaan(tiedostonNimi);
                    lista = kokoelma;
                }
                catch (Exception O){
                    System.out.println("vittuleissön");
                }
            }
            else {
                String[] sanat = syöte.split(" ");
            
                try {
                    //if sanat[0].equals("echo") {}
                    //System.out.println(sanat[0] + ", "+ sanat[1]);
                    if (sanat[0].equals("print")) {
                        // System.out.println("henlo");
                        if (sanat.length == 1) {
                            kokoelma.tulostaKaikki(lista);
                            //System.out.println(lista.dokumentit());
                            
                        }    
                        else if (sanat.length > 2) {
                            System.out.println("Error!");
                        }            
                        else {
                            try {
                                Dokumentti tuloste = kokoelma.tulostaDokumentti(Integer.parseInt(sanat[1]), lista);
                                System.out.println(tuloste);
                            }
                            catch (Exception IllegalArgumentException) {
                                System.out.println("Error!");
                            }
                        }
                        
                    }
                    else if (sanat[0].equals("add")) {
                        try {
                            syöte = syöte.replace("add ", "");
                            // System.out.println(syöte);
                            String[] osat = syöte.split("///");
                            // System.out.println(sanat[1]);
                            // Haetaan datasta päivämäärä ja muunnetaan se tarvittavaan muotoon.
                            String[] luvut = osat[1].split("\\.");
                            // System.out.println("r u ther");
                            // System.out.println(luvut[0] + ", " + luvut[1] + ", " + luvut[2]);
                            // System.out.println("not here tho?");
                            int tunniste = Integer.parseInt(osat[0]); 
                            int pv = Integer.parseInt(luvut[0]);
                            // System.out.println("Still here?");
                            int kk = Integer.parseInt(luvut[1]);
                            int vuosi = Integer.parseInt(luvut[2]);
                            LocalDate pvm = LocalDate.of(vuosi, kk, pv);
        
                            Dokumentti uutinen = new Uutinen(tunniste, pvm, osat[2]); 
                            
                            lista.lisää(uutinen);
                            // System.out.println(lista);
                        }
                        catch (Exception T) {
                            try {
                                // System.out.println("vitsi!!!");
                                syöte = syöte.replace("add ", "");
                                // System.out.println(syöte);
                                String[] osat = syöte.split("///");
                                // System.out.println(osat[1] + ", " + osat[2]);
                                Dokumentti vitsi = new Vitsi(Integer.parseInt(osat[0]), osat[1], osat[2]);
                                lista.lisää(vitsi);
                            }
                            catch (Exception E) {
                                System.out.println("vitsin tai uutisen lisäys epäonnistui :(");
                            }
                        }
                        // kokoelma.lisää(Dokumentti(sanat[1]));
                    }
                    else if (sanat[0].equals("remove")) {
                        lista = lista.poistaDokumentti(Integer.parseInt(sanat[1]), lista);
                        // System.out.println("why u no come here");
                    }
                    else if (sanat[0].equals("find")) {
                        LinkedList<String> sanoja = new LinkedList<String>();
                        for (int i = 1; i < sanat.length; i++) {
                            sanoja.add(sanat[i]);
                            
                        }
                        LinkedList<Integer> tunnisteet = lista.etsiSanat(sanoja);
                        for (int tunniste : tunnisteet) {
                            System.out.println(tunniste);
                        }
                    }
                    else if (sanat[0].equals("polish")) {
                        lista.siivoaDokumentit(sulkusanat, sanat[1]);
                    }
                    
                    else {
                        if (!echo) {
                            System.out.println("Error!");
                        }
                    }
                }
                catch (Exception IllegalArgumentException) {
                    System.out.println("Error!");
                }
            }
        }
    }
}