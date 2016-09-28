package tdm7Ex1;

import java.lang.Math;

public class PiMonoThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double sum = 0.0;
		long k;
		long timeStart = System.currentTimeMillis();
		for(k=0;k<1000000000L;k++) {
			sum += Math.pow(-1,k)/(2*k+1);
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println("Total time (ms): "+ (timeEnd - timeStart));
		System.out.println("Estimated PI: "+sum*4);
	}
}
