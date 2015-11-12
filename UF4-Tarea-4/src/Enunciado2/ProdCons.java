package Enunciado2;

/**
 * @author Pedro J. Camacho
 * @tarea Debes comentar adecuadamente el código
 * 
 *        FERNANDO SÁNCHEZ MARTÍNEZ - COMENTARIO DE CÓDIGO
 *
 */

/**
 * ésta clase Productor representa al hilo Productor el cual crea un elemento y
 * lo añade al dépósito
 * 
 * @author fernando.sanchez
 *
 */
class Productor extends Thread {
	private Deposito deposito;

	public Productor(Deposito d) {// se le pasa por contructor el deposito y se
									// iguala con el creado
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++)
			// se crea un bucle que va guardando en deposito del 1 al 20
			deposito.guardar();
	}
}

/**
 * Ésta clase, representa al hilo Consumidor, el cual va consumuiendo los
 * elementos que va dejando el Productor en el depósito
 * 
 * @author fernando.sanchez
 *
 */
class Consumidor extends Thread {
	private Deposito deposito;

	public Consumidor(Deposito d) {// se le pasa por contructor el deposito y se
									// iguala con el creado
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++)
			deposito.sacar();// el consumidor recorre el deposito y va sacando
								// elementos.
	}
}

/**
 * Ésta clase representa al depósito donde se almacenan los elementos fabricador
 * por el Productor y consumidos por el Consumidor.
 * 
 * @author fernando.sanchez
 *
 */
class Deposito {
	private int elementos = 0;// se crea la variable comun Nª elementos

	// se crean métodos synchronized para evitar errores en dicha variable ya
	// que es accedida por varios hilos.

	public synchronized void guardar() {// método guardar
		if (elementos > 0)// si hay elementos
			try {
				this.wait();// detiene el hilo hasta poder continuar
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		elementos++;// se suma 1 a elementos
		System.out.println("Guardar - nº eltos: " + elementos);
		this.notify();// se deja libre el objeto compartido
	}

	public synchronized void sacar() {// metodo sacar
		if (elementos == 0)// si elementos vale 0
			try {
				this.wait();// detiene el hilo hasta poder continuar
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		elementos--;// se resta 1 a elementos por el que se ha sacado
		System.out.println("Sacar - nº eltos: " + elementos);
		this.notify();// se deja libre el objeto compartido
	}
}

// Ésta clase ProdCons recoge el main en el cual se van a inicializar nuestros
// hiilos
public class ProdCons {
	public static void main(String[] args) {
		Deposito d = new Deposito();// se crea el objeto Deposito
		Thread consumidor = new Consumidor(d);// se crea el hilo consumidor
		Thread productor = new Productor(d);// se crea el hilo productor
		consumidor.start();// se lanzan los hilos
		productor.start();
	}
}
