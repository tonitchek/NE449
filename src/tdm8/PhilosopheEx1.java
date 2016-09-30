package tdm8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosopheEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lock l1 = new ReentrantLock();
		Lock l2 = new ReentrantLock();
		Lock l3 = new ReentrantLock();
		Lock l4 = new ReentrantLock();
		Lock l5 = new ReentrantLock();
		PhilosopheThread ph1 = new PhilosopheThread("Philosophe1",l5,l2);
		PhilosopheThread ph2 = new PhilosopheThread("Philosophe2",l1,l3);
		PhilosopheThread ph3 = new PhilosopheThread("Philosophe3",l2,l4);
		PhilosopheThread ph4 = new PhilosopheThread("Philosophe4",l3,l5);
		PhilosopheThread ph5 = new PhilosopheThread("Philosophe5",l4,l1);
		ph1.start();
		ph2.start();
		ph3.start();
		ph4.start();
		ph5.start();
	}
}
