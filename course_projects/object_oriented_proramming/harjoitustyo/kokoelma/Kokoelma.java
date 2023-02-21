// Liitetään luokka kokoelma-pakkaukseen.
package harjoitustyo.kokoelma;
// Tuodaan tarvittavat pakkaukset.
import harjoitustyo.dokumentit.*;
import harjoitustyo.omalista.*;
import harjoitustyo.apulaiset.*;
import java.util.LinkedList;

/**
 * Kokoelma-luokka, toteuttaa käyttäjän haluamia asioita dokumenttikokoelmalle.
 * Toteuttaa Kokoava<Dokumentti> -rajapinnan.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * <p>
 * 
 * @param <E> tyyppi periytyy ja sen on oltava Comparable-rajapinnan toteuttama.
 * @author Maria Seppänen, maria.seppanen@tuni.fi
 */ 
public class Kokoelma<E> implements Kokoava<Dokumentti> {

    /** Sisältää viitteet kokelmaan kuuluviin dokumenttiolioihin */
    private OmaLista<Dokumentti> dokumentit;

    /** Oletusrakentaja. Luo tyhjän OmaLista-tyyppisen viitteen. */ 
    public Kokoelma() {
        OmaLista<Dokumentti> tyhjäLista = new OmaLista<Dokumentti>();
        dokumentit = tyhjäLista;
    }

    /**
     * Tulostaa tietyn dokumentin kokoelmasta.
     * @param tunniste dokumentin int-tyyppinen (<0) tunniste.
     * @param lista OmaLista-tyyppinen kokoelma, joka sisältää kaikki dokumentit.
     * @return tulostettavan dokumentin.
     * @throws IllegalArgumentException jos mikään parametreistä on null tai tyhjä.
     */
    public Dokumentti tulostaDokumentti(int tunniste, Kokoelma<Dokumentti> lista) throws IllegalArgumentException {
        if (lista.hae(tunniste) != null) {
            return lista.hae(tunniste);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Tulostaa kaikki kokoelman dokumentit.
     * @param lista OmaLista-tyyppinen kokoelma, joka sisältää kaikki dokumentit.
     */
    public void tulostaKaikki(Kokoelma<Dokumentti> lista) {
        for (int i = 0; i < lista.dokumentit.size(); i++) {
            System.out.println(lista.dokumentit.get(i));
        }
    }
    
    /**
     * Poistaa kokoelmasta halutun dokumentin tunnisteen avulla. Jos tunnisteen mukaista dokumenttia ei ole,
     * heitetään poikkeus.
     * @param t dokumentin int-tyyppinen (<0) tunniste.
     * @param lista OmaLista-tyyppinen kokoelma, joka sisältää kaikki dokumentit.
     * @return kokoelman, josta on poistettu haluttu dokumentti.
     * @throws IllegalArgumentException jos mikään parametreista on null.
     */
    public Kokoelma<Dokumentti> poistaDokumentti(int t, Kokoelma<Dokumentti> lista) throws IllegalArgumentException {
        // Tarkistetaan, että kysytyn tunnisteen alla on olemassa dokumentti.
        Dokumentti dokumentti = lista.hae(t);
        if (dokumentti != null) {
            // Poistetaan haluttu dokumentti.
            dokumentit.remove(hae(t));
            return lista;
        }
        // Virhe-tilanteessa heitetään IllegalArgumentException-poikkeus.
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Etsitään dokumenttien teksteistä hakusanoja täsmääviä sanoja. Palauttaa listan dokumenttien
     * tunnisteista, jotka sisältävät hakusanat.
     * @param sanat LinkedList<String>-tyyppinen lista haettavista sanoista.
     * @return LinketList<Integer>-tyyppinen lista dokumenttien tunnisteista, jotka sisältävät
     * kaikki hakusanat.
     * @throws IllealArgumentExeption jos hakusanalista on null.
     */
    public LinkedList<Integer> etsiSanat(LinkedList<String> sanat) throws IllegalArgumentException {
        if (sanat != null) {
            // Luodaan lista tunnisteille.
            LinkedList<Integer> tunnisteet = new LinkedList<Integer>(); 
            // Etsitään dokumentit, jotka sisältävät hakusanat.
            for (int i = 0; i < dokumentit().size();i++) {
                Dokumentti dokumentti = dokumentit().get(i);
                // Jos dokumentti sisältää hakusanat, lisätään sen tunniste listalle.
                if (dokumentti.sanatTäsmäävät(sanat)) {
                    tunnisteet.add(dokumentti.tunniste());
                }
            }
            // Palautetaan lista tunnisteista.
            return tunnisteet;
        }
        // Jos hakusanalista on null, heitetään poikkeus.
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Siivotaan dokumentista halutut merkit pois.
     * @param sulkusanat LinkedList<String>-tyyppinen lista sanoista, jotka poistetaan dokumenttien teksteistä.
     * @param välimerkit String-tyyppinen lista merkeistä, jotka halutaan poistaa dokumenttien teksteistä.
     * @throws IllegalArgumentException jos mikään menee pieleen tai jokin parametreista on null tai tyhjä.
     */
    public void siivoaDokumentit(LinkedList<String> sulkusanat, String välimerkit) throws IllegalArgumentException {
        // Varaudutaan poikkeukseen, virhetilanteessa heitetään IllegalArgumentException-poikkeus.
        try { 
            // Käydään läpi dokumentit yksitellen ja kutsutaan OmaLista-luokan siivoa-metodia, joka
            // poistaa kaikki halutut merkit ja sulkusanat teksteistä.
            for (int i = 0; i < dokumentit().size(); i++) {
                Dokumentti dokumentti = dokumentit().get(i);
                dokumentti.siivoa(sulkusanat, välimerkit);
            }
        }
        catch (Exception E) {
            throw new IllegalArgumentException();
        }
    }
 
    /**
     * Lisätään kokoelmaan haluttu dokumentti halutuilla tiedoilla.
     * @param uusi dokumentti, joka halutaan lisätä kokoelmaan.
     * @throws IllegalArgumentException jos mikään parametri on null.
     */
    public void lisää(Dokumentti uusi) throws IllegalArgumentException {
        // Jos parametreista kumpikaan on null, heitetään poikkeus.
        if (uusi == null || hae(uusi.tunniste()) != null) {
            throw new IllegalArgumentException();
        }
        else {
            // Kokeillaan lisätä dokumentti kokoelmaan, virheen sattuessa heitetään
            // IllegalArgumentException-poikkeus.
            try {
                dokumentit.lisää(uusi);
            }
            catch (Exception E) {
                throw new IllegalArgumentException();
            }
        } 
    }
    
    /**
     * Haetaan haluttu dokumentti kokoelmasta sen tunnisteen perusteella. Haku ei onnistu, jos dokumentti-
     * lista on null tai tunnisteen alaista dokumenttia ei löydy kokoelmasta.
     * @param tunniste halutun dokumentin int-tyyppinen positiivinen tunniste.
     */
    public Dokumentti hae(int tunniste) {
        if (dokumentit != null) {
            for (int i = 0; i < dokumentit.size(); i++) {
                if (tunniste == dokumentit.get(i).tunniste()) {
                    // Palautetaan oikea dokumentti, jos tunnisteet täsmäävät.
                    return dokumentit.get(i);
                }
            }
            return null;
        }
        else {
            return null;
        }
    }

    /** Lukuaksessori. */
    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }
}