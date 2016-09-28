package tdm7Ex2;

public class PiMultiThread {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		double sum=0.0;
		int nbThread = 16;
		PiThread[] piCalc;
		long iteration=1000000000;
		long step = iteration/nbThread;
		System.out.println("Nb thread: "+nbThread);
		System.out.println("Nb iteration: "+iteration);
		System.out.println("step: "+step);
		
		piCalc = new PiThread[nbThread];
		for(int i=0;i<nbThread;i++) {
			piCalc[i] = new PiThread(i*step,i*step+step-1);
		}
		long timeStart = System.currentTimeMillis();
		for(int l=0;l<nbThread;l++) {
			piCalc[l].start();
		}
		for(int j=0;j<nbThread;j++) {
			piCalc[j].join();
		}
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("Total time (ms): "+ (timeEnd - timeStart));
		for(int k=0;k<nbThread;k++) {
			sum += piCalc[k].sum;
		}
		System.out.println("Pi estimated: "+sum*4);
	}

}
