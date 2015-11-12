package Enunciado1;

/**
 * @author Pedro J. Camacho
 * @tarea Debes comentar adecuadamente el código
 *
 *        FERNANDO SÁNCHEZ MARTÍNEZ - COMENTARIO DE CÓDIGO
 *
 */
public class Contador {
	private int c = 0; // Se crea una variable int que representa el numero de
						// vocales y sera nuestro contador. Se inicializa a 0.

	/**
	 * se crea el constructor de la clase que recibe por parámetro el contador.
	 * 
	 * @param num
	 */
	public Contador(int num) {
		this.c = num;
	}

	/**
	 * se crean 3 metodos sincronizados para evitar errores en el contador, al
	 * ser una variable comparida por muchos hilos.
	 */
	public synchronized void increment() {
		c++;// suma el contador +1.
	}

	public synchronized void decrement() {
		c--;// resta el contador -1.
	}

	public synchronized int value() {
		return c;// proporciona el valor del contador en el momento de ser
					// llamado.
	}
}