package com.chengsluo.ann;
//将加权和映射到某个固定范围，如[-1,1];
public abstract class Activation {
	//激活函数
	public abstract double f(double x);
	//激活函数的导数，这是在反向传播过程中必须要用到的。
	public abstract double df(double x);

	//后面的一部分是Sigmod函数的具体实现

	//sigmod(k,x)函数的实现1/(1+e^(-k*x))，其中k控制函数值从正变为负的变化速率
	//可以推到其导数为k*f(x)*(1-f(x))
	public static Activation logit(final double k) {
		return new Activation() {
			@Override
			public double f(double x) {
				return 1.0/(1.0 + Math.exp(-k*x));
			}

			@Override
			public double df(double x) {
				x = f(x);
				return k*x*(1.0-x);
			}
		};
	}
	public static Activation sigmoid(double k) {
		return logit(k);
	}
	//默认k=1.0
	public static Activation logit() {
		return logit(1.0);
	}
	public static Activation sigmoid() {
		return logit();
	}
	//双曲正切函数
	public static Activation tanh() {
		return new Activation() {
			@Override
			public double f(double x) {
				return Math.tanh(x);
			}
			//(tanh(x))'=1-tanh(x)^2;
			@Override
			public double df(double x) {
				x = f(x);
				return 1.0 - x*x;
			}
		};
	}
}