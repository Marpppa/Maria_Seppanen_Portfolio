// Liitetään luokka dokumentit-pakkaukseen.
package harjoitustyo.dokumentit;
// Tuodaan tarvittavat pakkaukset.
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** 
 * Uutinen-luokka. Dokumentti-luokan aliluokka.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * <p>
 * 
 * @author Maria Seppänen, maria.seppanen@tuni.fi
 */

public class Uutinen extends Dokumentti {

    /** Yksityinen uutisen päivämäärä-attribuutti. */
   private LocalDate päivämäärä;

   /**
    * Uutinen-luokan parametrillinen rakentaja.
    * @param tunniste uutisen tunniste positiivisena kokonaislukuna.
    * @param päivämäärä uutisen LocalDate-tyyppinen päivämäärä.
    * @param teksti uutisen teksti String-tyyppisenä.
    * @throws IllegalArgumentException jos uutisen teksti tyhjä merkkijono tai mikään parametri null.
    */ 

   public Uutinen(int tunniste, LocalDate päivämäärä, String teksti) throws IllegalArgumentException {
      // Kutsutaan yliluokkaa tunnisteelle ja uutistekstille.
      super(tunniste, teksti);
      // Tarkistetaan, ettei päivämäärä ole null ja sijoitetaan paikalleen.
      if (päivämäärä != null) {
         päivämäärä(päivämäärä);
      }
      // Jos päivämäärä on null, heitetään IllegalArgumentException-poikkeus.
      else {
         throw new IllegalArgumentException();
      }
   }

   /*
    * Aksessorit.
    */
   public LocalDate päivämäärä() {
      return päivämäärä;
   }
  
   public void päivämäärä(LocalDate p) {
      // Tarkistetaan, onko päivämäärä null. Jos kyllä, heitetään IllegalArgumentException.
      if (p != null) {
         päivämäärä = p;
      }
      else {
         throw new IllegalArgumentException();
      }
   }

   /**
    * Korvataan toString-metodi palauttamaan uutinen oikeassa formaatissaan 
   "tunniste///päivämäärä///teksti".
    */

   public String toString() {
      // Kutsutaan yliluokan toString-metodia.
      String teksti = super.toString();
      String[] puolikkaat = teksti.split(EROTIN, 0);

      // Muutetaan päivämäärä oikeaan formaattiin.
      String päivä = päivämäärä.format(DateTimeFormatter.ofPattern("d.M.yyyy"));
      // Palautetaan uutisdokumentti oikeassa muodossaan.
      return puolikkaat[0] + EROTIN + päivä + EROTIN + puolikkaat[1];
   }
 }