package tdm7Ex3;

public class TaskForce extends Thread {

	private int counter;
	
	TaskForce(String name,int counter) {
		super(name);
		this.counter = counter;
	}
	
	TaskForce(String name, TaskForce th, int counter) {
		super(name);
		this.counter = counter;
		try {
			th.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		long waiting = (long) (Math.random()*1000);
		for(int i=0;i<counter;i++) {
			try {
				Thread.sleep(waiting);
				System.out.println("Thread: "+this.getName()+"... Waiting count: "+i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Thread: "+this.getName()+"... done");
	}
}
