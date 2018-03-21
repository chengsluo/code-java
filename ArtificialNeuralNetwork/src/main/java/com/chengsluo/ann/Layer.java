package com.chengsluo.ann;
import java.util.Random;
public class Layer {

	//打印方法
	public static String printVec(double[] x) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(int i=0;i<x.length;i++) { if(i > 0) sb.append(",");sb.append(x[i]); }
		sb.append("}");
		return sb.toString();
	}

	double[]   v;
	double[][] w;
	double[]   bW = null;

	Activation fn;

	//构造函数
	/**
	 * @param units 本层节点个数
	 * @param inputs 与每个节点连接的输入节点的个数
	 * @param fn 本层所用的激活函数
	 * @param bias 是否使用bias
	 *
	 * */
	public Layer(int units,int inputs,Activation fn,boolean bias) {
		this.fn = fn;
		v   = new double[units];
		w = new double[units][];
		for(int i=0;i<v.length;i++) w[i] = new double[inputs];
		if(bias)
			bW = new double[units];
		err = new double[units];
	}
	//返回本层网络节点数
	public int units() { return v.length; }

	public double[] value()  { return v; }
	double[]   err;
	//记录反向传播时，本层节点的所有误差的平方和
	double     E;

	public double[] errors() { return err; }

	public double[] feed(double[] x) {
		for(int i=0;i<v.length;i++) {
			double[] W = w[i];
			v[i] = bW != null ? bW[i] : 0.0;
			for(int j=0;j<W.length;j++)
				v[i] += W[j]*x[j];
			//将当前节点值更新为权重乘以对应输入的总和.
			//并用激活函数归一化
			v[i] = fn.f(v[i]);
		}
		//这里的v[i]是上一层节点共同作用的结果。影响率为w[j].
		return v;
	}

	public double[] backprop(double[] s) {
		double[] out = new double[w[0].length];
		E = 0;
		for(int i=0;i<v.length;i++) {
			err[i] = fn.df(v[i])*s[i];
			//期望值与真实值的偏差->s[i]与此点的导数的乘积
			//同等偏差情况下，导数越大，误差err越大
			E += err[i]*err[i];
			double[] W = w[i];
			//根据前一层节点对本节点的贡献率来给前一层节点的对应位置制造反向传播的输入
			for(int j=0;j<W.length;j++) out[j] += W[j]*err[i];
		}
		//这里的out[i]是本层所有节点共同作用的结果
		return out;
	}
	/* backprop与feed不同的地方是：
	 *
	 * feed传入的是函数值，输出的也是函数值
	 * backprop传入的是偏差值，但会经过df处理，
	 * 按照复合函数的链式法则，处理后的结果仍然是内层函数的偏差值，
	 * 所以可以继续传递到前一层。
	 *
	 * */

	public double error() { return E; }

	public double[] update(double[] o,double r) {
		for(int i=0;i<v.length;i++) {
			if(bW != null)
				bW[i] += r*err[i];

			double[] W = w[i];
			for(int j=0;j<W.length;j++)
				W[j] += r*err[i]*o[j];
			//权重的增加值为:上一层的输出与连接位的对应误差err再乘以学习率的乘积的和。
		}
		//误差越大权重每轮增加越大，学习率越大，权重增加越快。
		return v;
	}

	//随机构建初始值[-1,1]
	public void initialize(Random rng) {
		for(int i=0;i<v.length;i++) {
			for(int j=0;j<w[i].length;j++)
				w[i][j] = 2*rng.nextDouble() - 1;
			bW[i] = 2*rng.nextDouble() - 1;
		}

	}

	public void initialize() {
		initialize(new Random());
	}


}