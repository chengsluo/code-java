package com.chengsluo.ann;

import org.junit.Test;

public class XorTest {

	public static final class Obs {
		public double[] x;
		public double[] y;
		
		public Obs(double[] x,double[]y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static Obs obs(double[] x,double[] y) {
		return new Obs(x,y);
	}

	@Test
	public void test() {
		Obs[] xorData = new Obs[]{
				obs(new double[]{-1,0},new double[]{0}),
				obs(new double[]{1,0},new double[]{1}),
				obs(new double[]{0,1},new double[]{1}),
				obs(new double[]{1,1},new double[]{0}),				
		};
		
		
		NeuralNetwork nn = NeuralNetwork.build().inputs(2).layer(3).layer(1);
		NeuralNetwork bad= NeuralNetwork.build().inputs(2).layer(1);
		
		System.out.println("nn\tbad");
		nn.initialize();
		bad.initialize();
		double lastErr = Double.POSITIVE_INFINITY;
		for(int i=0;i<1000;i++) {
			double err = 0.0;
			double errBad = 0.0;
			for(Obs x : xorData) {
				nn.train(x.x,x.y);
				bad.train(x.x,x.y);
				err += nn.error();
				errBad += bad.error();
			}
			System.out.println(err+"\t"+errBad);
			if(Math.abs(lastErr - err) < 1e-6) break;
			lastErr = err;
		}
	}

}
