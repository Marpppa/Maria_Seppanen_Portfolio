package harjoitustyo.jp;

/*
 * Luodaan painotetun graafin luokka. 
 */
public class PainotettuGraafi { 
	/*
	 *  Solmun, johon kaari tulee koordinaatit ja kaaren paino, eli 
	 *  kyseisen solmun ja alkupisteen v‰linen intensiteettiero. 
	 */
	public class Kaari {
        int koordinaattiX;
        int koordinaattiY;
        int paino;

        public Kaari(int koordinaattiX, int koordinaattiY, int paino) {
            this.koordinaattiX = koordinaattiX;
            this.koordinaattiY = koordinaattiY;
            this.paino = paino;
        }
        
        /*
         * Haetaan kaaren tiedot ja palautetaan ne int[]-taulukkona.
         * Ensimm‰isen‰ x-koordinaatti, sitten y-koordinaatti ja viiemisen‰ paino.
         */
        public int[] kaarenTiedot() {
        	int tiedot[] = {koordinaattiX, koordinaattiY, paino};
        	return tiedot;
        }
    }

	/*
	 * Luodaan graafin luokka, joka sis‰lt‰‰ svierekkyyslistan (jono), jossa on kaikki kaaret.
	 */
    public class Graafi {
        int solmut;
        Jono vierekkyyslista;

        // Rakentaja, kun koko on tiedossa.
        public Graafi(int pikselit) {
            this.solmut = pikselit;
            vierekkyyslista = new Jono(pikselit);
        }

        /*
         * Lis‰t‰‰n graafiin Kaari-tyyppisi‰ kaaria. Palauttaa lis‰tyn kaaren.
         */
        public Kaari lisaaKaari(int koordinaattiX, int koordinaattiY, int paino) {
            Kaari kaari = new Kaari(koordinaattiX, koordinaattiY, paino);
            vierekkyyslista.enqueue(kaari);
            return kaari;
        }
        
        /*
         * Haetaan graafin ensimm‰inne kaari ja palautetaan se Kaari-tyyppisen‰.
         */
        public Kaari haeKaari() {
        	return (Kaari)vierekkyyslista.front();
        }
        
        /*
         * Haetaan kaaren tiedot int[]-tyyppisen‰ taulukkona. 
         * [0] = x-koordinaatti, [1] = y-koordinaatti, [3] = paino
         * 
         */
        public int[] kaari() {
        	Kaari kaari = (Kaari)vierekkyyslista.front();
        	return kaari.kaarenTiedot();
        }
    }
}