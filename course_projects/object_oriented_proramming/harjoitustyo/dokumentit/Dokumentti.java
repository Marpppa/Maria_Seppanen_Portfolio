// Liitetään luokka dokumentit-pakkaukseen.
package harjoitustyo.dokumentit;
// Tuodaan tarvittavat pakkaukset.
import harjoitustyo.apulaiset.*;

import java.util.LinkedList;
import java.util.Arrays;

/**
 * Dokumentti, abstakti yliluokka. Implementoi Comparable<Dokumentti> ja Tietoinen<Dokumentti>
 -luokat.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * <p>
 * @author Maria Seppänen, maria.seppanen@tuni.fi
 */
public abstract class Dokumentti implements Comparable<Dokumentti>, Tietoinen<Dokumentti> {
    /** Vakiomuotoinen erotin-attribuutti. */
    protected final String EROTIN = "///";

    /** Yksityinen dokumentin (kokonaisluku) tunniste-attribuutti. */
    private int tunniste;

    /** Yksityinen String-tyyppinen tekstiattribuutti dokumentin sisällölle. */
    private String teksti;

    /**
     * Dokumentti-luokan parametrillinen rakentaja. 
     * @param tunniste dokumentin int-tyyppinen tunniste.
     * @param teksti dokumentin String-tyyppinen tekstisisältö.
     * @throws IllegalArgumentException jos mikään merkkijono on tyhjä tai parametri null.
     */
    public Dokumentti(int tunniste, String teksti) throws IllegalArgumentException {
        // Tarkistetaan, että parametrit ovat oikanlaiset.
        if (tunniste < 1 || teksti == null || teksti == "") {
            // Jos ei, heitetään poikkeus.
            throw new IllegalArgumentException();
        }
        // Kutsutaan asetusmetodeja ja asetetaan tunniste ja teksti dokumenttiin.
        else {
            tunniste(tunniste);
            teksti(teksti);
        }
    }

    /*
     * Aksessorit.
     */
    public int tunniste() {
        return tunniste;
    }
  
    public void tunniste(int t) {
        // Tarkistetaan, että tunniste on positiivinen.
        if (t > 0) {
            tunniste = t;
        }
        // Muussa tapauksessa heitetään poikkeus.
        else {
            throw new IllegalArgumentException();
        }
    }

    public String teksti() {
        return teksti;
    }
    public void teksti(String t) {
        // Tarkistetaan, että teksti on kunnossa.
        if (t != null && t != "") {
            teksti = t;
        }   
        // Muussa tapauksessa heitetään poikkeus.
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Etsii kokoelmasta hakusanojen (LinkedList) kanssa täsmääviä sanoja dokumenteista.
     * @param hakusanat sanat, joita etsitään dokumenteista.
     * @return true/false, riippuen löytyikö hakua täsmäävä sana kokoelmasta.
     * @throws IllegalArgumentException jos hakusanat-lista on null.
     */
    public boolean sanatTäsmäävät(LinkedList<String> hakusanat) throws IllegalArgumentException {
        // Jos hakusanat on tyhjä tai null, heitetään IllegalArgumentEcxeption-poikkeus.
        if (hakusanat == null || hakusanat.isEmpty()) {
            throw new IllegalArgumentException();
        }
        else {
            // Pilkotaan dokumentin teksti sanoihin. 
            String[] tekstinSanat = teksti.split(" ");
            // Laskuri sanojen täsmäämisen määrälle.
            int lkm = 0;
            // Käydään läpi jokainen dokumentti ja kasvatetaan laskuria aina kun hakua täsmäävä sana löytyy.
            // Palautetaan null, jos yksikään sana ei täsmää.
            for (int i = 0; i < hakusanat.size(); i++) {
                if (Arrays.asList(tekstinSanat).contains(hakusanat.get(i))) {
                    lkm++;
                }
                else {
                    return false;
                }
            }
            // Tarkistetaan, että jokainen hakusanoista esiintyi tuloksissa laskurin avulla ja palautetaan true,
            // jos se pitää paikkaansa ja false jos täsmiä on eri määrä.
            if (lkm == hakusanat.size()) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    /**
     * Poistaa dokumenteista LinkedList-tyyppissä listassa annetut sanat ja String-tyyppisenä parametrina
     * annettujen välimerkkien esiintymät.
     * @param sulkusanat LinkedList-tyyppinen lista sanoista, jotka halutaan poistaa.
     * @param välimerkit String tyyppisenä parametrina annetut merkit, jotka halutaan poistaa
     dokumenttien teksteistä.
     * @throws IllegalArgumentException jos kumpikaan parametreistä on null tai tyhjiä.
     */
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit) 
        throws IllegalArgumentException { 
            if (sulkusanat == null || välimerkit == null || sulkusanat.isEmpty() || välimerkit == "") {
                throw new IllegalArgumentException();
            }
            else {
                // Luodaan pian tarvittavia  muuttujia.
                String ilmanMerkkejä = "";
                String pienetKirjaimet = "";
                String sanaPois = "";
                char[] merkit = välimerkit.toCharArray();

                // Käydään läpi välimerkit-muuttuja ja poistetaan jokainen merkki teksteistä
                // yksitellen.
                for (int i = 0; i < välimerkit.length(); i++) {
                    ilmanMerkkejä = teksti.replace(Character.toString(merkit[i]), "");
                    teksti = ilmanMerkkejä;
                }
                // Muunnetaan teksti pieniksi kirjaimiksi.
                pienetKirjaimet = teksti.toLowerCase();
                // Erotellaan teksti sanoittain taulukkoon.
                String[] sanatErikseen = pienetKirjaimet.split(" ");

                // Luodaan muuttujia helpottaaksemme kauheaa koodia.
                int viimeinen = sanatErikseen.length-1;
                String ensimmäinenSana = sanatErikseen[0];
                String viimeinenSana = sanatErikseen[viimeinen];
                int ensimmäisenSananPituus = ensimmäinenSana.length();
                int viimeisenSananPituus = viimeinenSana.length();

                // Käydään läpi jokainen poistettava sana yksitellen ja poistetaan sen esiintymät teksteistä.
                for (int i = 0; i < sulkusanat.size(); i++) {
                    int sananPituus = pienetKirjaimet.length();
                    // Tarkistetaan, onko poistettava sana ensimmäisenä tai viimeisenä, ja poistetaan se 
                    // tarvittaessa.
                    if (ensimmäinenSana.equals(sulkusanat.get(i))) {
                        pienetKirjaimet = pienetKirjaimet.substring(ensimmäisenSananPituus + 1, sananPituus);
                    }
                    if (viimeinenSana.equals(sulkusanat.get(i))) {
                        pienetKirjaimet = pienetKirjaimet.substring(0, sananPituus - viimeisenSananPituus - 1);
                    }
                    // Tarkistetaan, ettei minnekään jäänyt ylimääräsiä välilyöntejä sanojen poistamisen
                    // yhteydessä ja poistetaan haluttu sana keskeltä tekstiä, jos se sieltä löytyy.
                    for (int i2 = 1; i2 < sanatErikseen.length - 1; i2++) {
                        if (sanatErikseen[i2].equals(sulkusanat.get(i))) {
                            String poistettava = " " + sulkusanat.get(i) + " ";
                            sanaPois = pienetKirjaimet.replace(poistettava, " ");
                            pienetKirjaimet = sanaPois.replace("  ", " ");
                        }
                    }
                }
                // Valmis puhdistettu teksti.
                teksti = pienetKirjaimet;
            }
        }

    /**
     * Korvataan equals-metodi niin, että se vertailee vain dokumenttien tunnisteita.
     * 
     * @param toinen verrattava dokumentti.
     * @return true, jos dokumenttien tunnisteet ovat samat, false jos ei.
     */
    public boolean equals(Object toinen) {
        try {
            // Jos annettu parametri on null, palautetaan false.
            if (toinen == null) {
                return false;
            }
            // Tarkistetaan, täsmäävätkö dokumenttien tunnisteet.
            else {
                Dokumentti toinenDok = (Dokumentti)toinen;
                return tunniste == toinenDok.tunniste();
            }
        }
        // Poikkeustilanteessa palautetaan false.
        catch (Exception E) {
            return false;
        }
    }

    /**
     * Overridataan toString-metodi palauttamaan dokumentti muodossa "tunniste///teksti".
     */
    @Override
    public String toString() {
        // Palautetaan dokumentti oikeassa muodossa.
        return Integer.toString(tunniste) + EROTIN + teksti;
    }

    /**
     * Overridataan compareTo-metodi vertailemaan vain dokumenttien tunnisteita.
     * Palauttaa arvon 1, jos annetun dokumentin tunniste on suurempi, 0 jos ne ovat yhtä suuria 
     ja -1 jos se on pienempi.
     * @param toinen vertailtava dokumentti.
     * @return lukuarvo, joka kertoo kumman dokumentin tunniste on suurempi.
     */
    @Override
    public int compareTo(Dokumentti toinen) {
        Dokumentti toinenDok = (Dokumentti)toinen;
        if (tunniste > toinenDok.tunniste()) { 
            return 1;
        }
        if (tunniste == toinenDok.tunniste()) {
            return 0;
        }
        else {
            return -1;
        }
    }
}