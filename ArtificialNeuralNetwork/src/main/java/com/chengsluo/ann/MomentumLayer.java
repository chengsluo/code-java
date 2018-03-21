package com.chengsluo.ann;
//使用冲量层来构建进行权值更新

//这会使得权重在同一方向的收敛速度加快
public class MomentumLayer extends Layer {

	double dW[][];
	double dbW[];
	double m = 0.1;

	public MomentumLayer(int units, int inputs, Activation fn, boolean bias) {
		super(units, inputs, fn, bias);
		dW = new double[units][];
		for (int i = 0; i < v.length; i++)
			dW[i] = new double[inputs];
		if (bias)
			dbW = new double[units];

	}

	@Override
	//在权重的更新过程中，使用该权重的新delta值与旧delta值的加权平均
	//其中的权值为参数m
	public double[] update(double[] o, double r) {
		for (int i = 0; i < v.length; i++) {

			if (bW != null) {
				dbW[i] = (1 - m) * r * err[i] + m * dbW[i];
				bW[i] += dbW[i];
			}

			double[] W = w[i];
			for (int j = 0; j < W.length; j++) {
				dW[i][j] = (1 - m) * r * err[i] * o[j] + m * dW[i][j];
				W[j] += dW[i][j];
			}
		}
		return v;
	}

}
