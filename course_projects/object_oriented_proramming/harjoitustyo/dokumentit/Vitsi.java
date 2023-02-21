// Liitetään luokka dokumentit-pakkaukseen.
package harjoitustyo.dokumentit;

/**
 * Vitsi-luokka. Dokumentti-luokan aliluokka.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * <p>
 * @author Maria Seppänen, maria.seppanen@tuni.fis
 */

 public class Vitsi extends Dokumentti {

   /** Yksityinen laji-attribuutti vitsin lajille. */
   private String laji;

   
   /**
    * Vitsi-luokan parametrillinen rakentaja.
    * @param tunniste vitsin tunniste int-tyyppisenä positiivisena parametrina.
    * @param omaTieto vitsin laji String-tyyppisenä parametrina.
    * @param teksti vitsi itse String-tyyppisenä parametrina.
    * @throws IllegalArgumentException jos mikään merkkijono on tyhjä tai mikään parametri null.
    */
    public Vitsi(int tunniste, String omaTieto, String teksti) throws IllegalArgumentException {
      // Asetetaan tunniste ja vitsin teksti yliluokassa.
      super(tunniste, teksti);
      // Tarkistetaan, että omaTieto, eli vitsin laji ei ole tyhjä merkkijono tai null.
      if (omaTieto != null && omaTieto != "") {
         // Asetetaan vitsin laji.
         laji(omaTieto);
      }
      // Muussa tapauksessa heitetään IllegalArgumentException-poikkeus.
      else {
         throw new IllegalArgumentException();
      }
   }

   /*
    * Aksessorit.
    */
   public String laji() {
      return laji;
   }
  
   public void laji(String l) {
      if (l != null && l != "") {
         laji = l;
      }   
        
   }

   /**
    * Korvataan toString-metodi palauttamaan vitsi oikeassa muodossa, eli 
    "tunniste///laji///vitsi".
    */
   public String toString() {
      // Kutsutaan yliluokan toString-metodia.
      String teksti = super.toString();
      String[] puolikkaat = teksti.split(EROTIN, 0);
      // Palautetaan vitsidokumentti oikeassa muodossaan.
      return puolikkaat[0] + EROTIN + laji + EROTIN + puolikkaat[1];
   }
}