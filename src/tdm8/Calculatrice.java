package tdm8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Calculatrice extends Thread
{
	private Somme somme;
	private Lock lck;

	public Calculatrice(Lock l, Somme somme)
	{
		this.somme = somme;
		this.lck = l;
	}

	@Override
	public void run()
	{
		int res = 0;
		for (int i = 0; i < 100; i++)
		{
			lck.lock();
			res= somme.somme(res, i);
			lck.unlock();
		}
		System.out.println("La somme est 1 et 99 est :"+res);
	}

	public static void main(String[] args) throws InterruptedException
	{
		Lock l = new ReentrantLock();
		Somme somme = new Somme();
		Calculatrice c1 = new Calculatrice(l,somme);
		Calculatrice c2 = new Calculatrice(l,somme);
		c1.start();
		c2.start();
	}
	
	static class Somme
	{
		int c;
		public int somme(int a, int b)
		{
			c=a+b;
			System.out.println("c="+c);
			return c;
		}
	}
}