package tdm7Ex3;

public class ThreadSync {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		TaskForce th1 = new TaskForce("Task1",10);
		th1.start();
		TaskForce th2 = new TaskForce("Task2",th1,3);
		th2.start();
	}

}
