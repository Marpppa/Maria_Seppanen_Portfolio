package harjoitustyo.jp;


/*
 * Luodaan jono-luokka, jota k‰ytet‰‰n graafin muodostamisessa ja leveyshaussa.
 */
public class Jono {
 
	public static final int defaultN = 1000;
	private int N;
	private int first;
	private int rear;
	private int items;
	
	private Object Q[];
	
	// Oletusrakentaja.
	public Jono() {
		this(defaultN);
	}
	
	// Rakentaja, kun sen koko on tiedossa.
	public Jono(int size) {
		first = 0;
		rear = 0;
		items = 0;
		N = size;
		Q = new Object[N];
	}
	
	/*
	 * Lis‰t‰‰n jonoon viimeinen alkio.Ei palauta mit‰‰n ja heitt‰‰ poikkeuksen, jos jono on t‰ynn‰.
	 */
	public void enqueue(Object alkio) throws IllegalArgumentException {
		if (items < N) {
			Q[rear] = alkio;
			rear = (rear + 1) % N;
			items++;
		    }
		else {
			throw new IllegalArgumentException("Queue is full.");
		}
	}
	
	
	/*
	 * Poistetaan jonosta ensimm‰inen alkio. Palauttaa poistettavan alkion ja heitt‰‰ poikkeuksen, jos 
	 * jono on tyhj‰.
	 */
	public Object dequeue() throws IllegalArgumentException {
		if (isEmpty()) {
			throw new IllegalArgumentException("Queue is empty.");
		}
		else {
			Object temp = Q[first];
			Q[first] = null;
			first = (first+1) % N;
			items--;
			return temp;
		}
	}
	
	/*
	 * Haetaan jonon koko, palauttaa jonon pituuden int-tyyppisen‰. 
	 */
	public int size() {
		return (N-first+rear) % N;
	}
	
	/*
	 * Tarkistetaan, onko jono tyhj‰. Palauttaaa boolean-arvon true tai false.
	 */
	public boolean isEmpty() {
		return (first == rear);
	}
	
	/*
	 * Palauttaa ensimm‰isen alkoin ja heitt‰‰ poikkeuksen, jos jono on tyhj‰. 
	 */
	public Object front() {
		Object result = null;
		if (isEmpty()) {
			throw new IllegalArgumentException("Queue is empty.");
		}
		else {
			
			result = Q[first];
			return result;
		}
	}
}
