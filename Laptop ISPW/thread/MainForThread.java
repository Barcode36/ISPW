package thread;

public class MainForThread {
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {// ne creiamo solo 5
			Thread t = new Thread(new EsempioThread(i)); 
			//definiamo un'istanza della classe java thread
			//che richiede un'istanza di una classe che implementa l'interfaccia Runnable 
			t.setName("Thread t" + i);
			System.out.println("Thread t" + i + " è stato creato !");
			t.start();//metodo che invoca il metodo run() del thread 
		}
	}
}
