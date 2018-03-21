package com.chengsluo.ann;

import java.util.Random;
import org.junit.Test;

public class TimeSeriesTest {
	@Test
	public void test() {
		int k = 14;
		
		double[] x = new double[k];
		double[] y = new double[1];
		double   t = 0.0;
		Random twist = new Random();
		NeuralNetwork       nn    = NeuralNetwork.build().inputs(k).layer(5).layer(1).initialize();
		for(int i=0;i<1000;i++) {
			double base = Math.sin(t);
			y[0] = base + 0.5*twist.nextGaussian();
			//Update the input time series
			if(i >= x.length) {
				nn.train(x, y);
				System.out.println(nn.output()[0]+"\t"+y[0]+"\t"+base);
				//Move the time series over
				for(int j=0;i<x.length-1;j++) x[i] = x[i+1];
				x[x.length-1]=y[0];
			} else {
				x[i] = y[0];
			}
			t += 2.0*Math.PI/60.0;
		}
		
		
	}

}
