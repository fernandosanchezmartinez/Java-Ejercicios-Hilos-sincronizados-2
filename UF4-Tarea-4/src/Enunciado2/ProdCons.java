package Enunciado2;
/**
 * @author Pedro J. Camacho
 * @tarea Debes comentar adecuadamente el código
 *
 */
class Productor extends Thread {
	private Deposito deposito;

	public Productor(Deposito d) {
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++)
			deposito.guardar();
	}
}

class Consumidor extends Thread {
	private Deposito deposito;

	public Consumidor(Deposito d) {
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++)
			deposito.sacar();
	}
}

class Deposito {
	private int elementos = 0;

	public synchronized void guardar() {
		if (elementos > 0)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		elementos++;
		System.out.println("Guardar - nº eltos: " + elementos);
		this.notify();
	}

	public synchronized void sacar() {
		if (elementos == 0)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		elementos--;
		System.out.println("Sacar - nº eltos: " + elementos);
		this.notify();
	}
}

public class ProdCons {
	public static void main(String[] args) {
		Deposito d = new Deposito();
		Thread consumidor = new Consumidor(d);
		Thread productor = new Productor(d);
		consumidor.start();
		productor.start();
	}
}
