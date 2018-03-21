package com.chengsluo.ann;

import org.junit.Test;

public class MomentumComparisonTest {
	public static final class Obs {
		public double[] x;
		public double[] y;

		public Obs(double[] x, double[] y) {
			this.x = x;
			this.y = y;
		}
	}

	public static Obs obs(double[] x, double[] y) {
		return new Obs(x, y);
	}

	@Test
	public void test() {
		Obs[] xorData = new Obs[] { obs(new double[] { -1, 0 }, new double[] { 0 }),
				obs(new double[] { 1, 0 }, new double[] { 1 }), obs(new double[] { 0, 1 }, new double[] { 1 }),
				obs(new double[] { 1, 1 }, new double[] { 0 }), };

		NeuralNetwork[] nn = new NeuralNetwork[10];
		for (int i = 0; i < 5; i++) {
			nn[i] = NeuralNetwork.build().inputs(2).layer(3).layer(1);
		}
		for (int i = 5; i < 10; i++) {
			nn[i] = NeuralNetwork.build().inputs(2).momentumLayer(3).momentumLayer(1);
		}
		double err[] = new double[10];
		for (int i = 0; i < 10; i++)
			nn[i].initialize();
		for (int j = 0; j < 300; j++) {
			for (int i = 0; i < 10; i++) {
				NeuralNetwork n = nn[i];
				err[i] = 0.0;
				for (Obs x : xorData) {
					n.train(x.x, x.y);
					err[i] += n.error();
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 10; i++) {
				if (i > 0)
					sb.append("\t");
				sb.append(err[i]);
			}
			System.out.println(sb.toString());
		}

	}

}
