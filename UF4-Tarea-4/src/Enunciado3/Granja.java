package Enunciado3;

/**
 * @author Pedro J. Camacho
 * @tarea Programar tu solución utilizando estas clases de base
 */

/**
 * ésta clase representa el Hilo Pastor
 * 
 * @author fernando.sanchez
 *
 */
class Pastor extends Thread {
	private Comedero comedero;// objeto compartido con las ovejas

	public Pastor(Comedero c) {// se le pasa el comedero por parámetro en el
								// constructor y se iguala al creado
		comedero = c;
	}

	public void run() {// acciones del pastor
		for (int i = 0; i < 100; i++) {// el pastor de de comer 100 veces, 5
										// veces por oveja hasta que se duerme

			comedero.alimentar();
			try {
				sleep(5);// el pastor da de comer a las obejas cada 5mgs
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}
	}
}

/**
 * Ésta es la clase que representa e los hilos Oveja
 * 
 * @author fernando.sanchez
 *
 */
class Oveja extends Thread {
	private Comedero comedero;// objeto conpartido con el pastor
	int i;

	public Oveja(Comedero c) {// se le pasa el comedor por parámetro y se iguala
								// con el creado
		comedero = c;
	}

	public void run() {// acciones de las obejas

		for (i = 1; i < 6; i++) {// una oveja come 5 veces antes de dormirse y
									// entre comida y comida espera 10mgs.

			comedero.comer();
			try {
				sleep(10);// se espera
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}
		// se muestra que obeja ha comido y por lo tanto se ha echado a dormir.
		System.out.println("La oveja " + getName() + " se duerme");
	}
}

/**
 * Ésta es la clase que representa el objeto Comedero el cual es compartido
 * tanto por el Pastor como por sus Ovejas
 * 
 * @author fernando.sanchez
 *
 */
class Comedero {
	private int comida = 0;// se crea la variable comida que nos indica si se ha
							// comido / si hay comida.

	public synchronized void alimentar() {// MÉTODO SINCRONIZADO ALIMENTAR
		while (comida != 0) { // si hay comida
			try {
				this.wait();// detiene el hilo hasta poder continuar
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		comida++;// se suma 1 a comida
		System.out.println("SE HA ALIMENTADO : " + comida);
		notifyAll();// se deja libre el objeto compartido

	}

	public synchronized void comer() {// MÉTODO SINCRONIZADO COMER
		while (comida == 0) { // si no hay comida
			try {
				this.wait();// detiene el hilo hasta poder continuar
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		comida--;// se resta 1 a comida
		System.out.println("SE HA COMIDO: " + comida);
		notifyAll();// se deja libre el objeto compartido

	}
}

/**
 * La clase Granja se encarga del main y la creacion de los distintos hilos
 * 
 * @author fernando.sanchez
 *
 */
public class Granja {
	public static void main(String[] args) throws InterruptedException {

		Comedero com = new Comedero();// se crea el comedero
		Thread pastor = new Pastor(com);// se crea el pastor
		pastor.start();// se lanza el pastor
		// pastor.join();

		for (int i = 0; i < 20; i++) {// se lanzan 20 ovejas
			Thread obeja = new Oveja(com);
			obeja.start();// se lanzan
			obeja.join();// esta fila es opcional si se desea que cada oveja
							// espere a la anterior para comer
		}

	}

}
