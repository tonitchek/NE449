package tdm7Ex3;

public class ThreadSync {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		TaskForce th1 = new TaskForce("Task1",null,10);
		th1.start();
		TaskForce th2 = new TaskForce("Task2",th1,3);
		th2.start();
		TaskForce th3 = new TaskForce("Task3",th1,8);
		th3.start();
		TaskForce th4 = new TaskForce("Task4",th1,5);
		th4.start();
		TaskForce[] th23 = new TaskForce[2];
		th23[0] = th2;
		th23[1] = th3;
		TaskForce th5 = new TaskForce("Task5",th23,2,12);
		th5.start();
		TaskForce th6 = new TaskForce("Task6",th4,6);
		th6.start();
		System.out.println("All threads started... Let's syncing");
	}

}
