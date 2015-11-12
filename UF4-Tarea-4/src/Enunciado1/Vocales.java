package Enunciado1;

import java.util.Scanner;

/**
 * @author Pedro J. Camacho
 * @tarea Debes comentar adecuadamente el código
 *
 *   FERNANDO SÁNCHEZ MARTÍNEZ - COMENTARIO DE CÓDIGO
 *
 */

/**
 * Ésta clase representa el hilo
 * 
 * @author fernando.sanchez
 *
 */
class Rastrea extends Thread {
	private Contador cont;// variable contador que se igualara a la de la clase
							// contador
	private String texto;// string que representa el txt
	private char letra;// caracteres

	/**
	 * se crea el contructor y se le pasan por parámetro las variables creadas
	 * anteriormente
	 * 
	 * @param cont
	 * @param texto
	 * @param letra
	 */
	public Rastrea(Contador cont, String texto, char letra) {
		this.cont = cont;
		this.texto = texto;
		this.letra = letra;
	}

	/**
	 * Método encargado de las acciones a la hora de ejecutar un hilo.
	 */
	public void run() {
		// se recorre el texto.
		for (int i = 0; i < texto.length(); i++)
			// si el texto en el caracter correspondiente en el bucle es igual a
			// la letra, incrementar contador.
			if (texto.charAt(i) == letra)
				cont.increment();
	}
}

/**
 * Clase main que crea y ejecuta los distintos hilos.
 * 
 * @author fernando.sanchez
 *
 */
public class Vocales {
	public static void main(String[] args) {
		String miTexto;// string que representa el texto
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduzca el texto: ");
		miTexto = sc.nextLine();// se recoge en el string el texto introducido
								// por teclado
		Contador contador = new Contador(0);// se crea un objeto contador
											// inicializado a 0
		// se crean nª de hilos correspondientes por cada vocal
		Thread contadorA = new Rastrea(contador, miTexto, 'a');
		Thread contadorE = new Rastrea(contador, miTexto, 'e');
		Thread contadorI = new Rastrea(contador, miTexto, 'i');
		Thread contadorO = new Rastrea(contador, miTexto, 'o');
		Thread contadorU = new Rastrea(contador, miTexto, 'u');
		// se lanzan los hilos
		contadorA.start();
		contadorE.start();
		contadorI.start();
		contadorO.start();
		contadorU.start();
		try {
			contadorA.join();
			contadorE.join();
			contadorI.join();
			contadorO.join();
			contadorU.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// se imprime el numero de vocales recogidas de nuestro texto.
		System.out.print("El número de vocales del texto es: "
				+ contador.value());
	}
}