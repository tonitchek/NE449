package tdm8;

import java.util.concurrent.locks.Lock;

public class PhilosopheThread extends Thread {
	private Lock lckRight;
	private Lock lckLeft;
	
	PhilosopheThread(String name, Lock lr, Lock lf) {
		super(name);
		this.lckRight = lr;
		this.lckLeft = lf;
	}

	public void run() {
		long waitingEat = (long) (Math.random()*10);
		long waitingDiscuss = (long) (Math.random()*10);
		System.out.println(this.getName()+" waitingEat:"+waitingEat+" | waitingDiscuss:"+waitingDiscuss);
		while(true) {
			for(int i=0;i<waitingDiscuss;i++) {
				try {
					System.out.println(this.getName()+"... Discussing "+i);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //wait 1s
			}
			lckRight.lock();
			lckLeft.lock();
			for(int j=0;j<waitingEat;j++) {
				try {
					System.out.println(this.getName()+"... Eating "+j);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //wait 1s
			}
			lckRight.unlock();
			lckLeft.unlock();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
