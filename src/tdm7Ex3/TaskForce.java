package tdm7Ex3;

public class TaskForce extends Thread {

	private int counter;
	private boolean severalThreads;
	private TaskForce th;
	private TaskForce[] ths;
	private int thNb;
	private long waiting;
	
	TaskForce(String name, TaskForce th, int counter) {
		super(name);
		this.counter = counter;
		this.severalThreads = false;
		this.th = th;
		waiting = (long) (Math.random()*1000);
		System.out.println(this.getName()+" waiting time: "+waiting+"ms");
	}
	
	TaskForce(String name, TaskForce[] th, int thNb, int counter) {
		super(name);
		this.counter = counter;
		this.severalThreads = true;
		this.ths = th;
		this.thNb = thNb;
		waiting = (long) (Math.random()*1000);
		System.out.println(this.getName()+" waiting time: "+waiting+"ms");
	}
	
	public void run() {
		if(severalThreads) {
			try {
				for(int i=0;i<thNb;i++) {
					ths[i].join();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				if(th != null) {
					th.join();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
