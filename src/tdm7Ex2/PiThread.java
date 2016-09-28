package tdm7Ex2;

public class PiThread extends Thread {

	long limite_m;
	long limite_M;
	double sum;
	
	PiThread(long start, long end) {
		super();
		limite_m = start;
		limite_M = end;
	}
	
	public void run() {
		long k;
		for(k=limite_m;k<limite_M;k++) {
			sum += Math.pow(-1,k)/(2*k+1);
		}
	}
}
