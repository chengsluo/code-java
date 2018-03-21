package com.chengsluo.ann;

import java.util.ArrayList;

public class NeuralNetwork {

	int inputUnits = 0;
	ArrayList<Layer> layers = new ArrayList<Layer>();
	double learningRate = 0.2;

	public NeuralNetwork learningRate(double l) {
		learningRate = l;
		return this;
	}

	public NeuralNetwork inputs(int inputUnits) {
		this.inputUnits = inputUnits;
		return this;
	}

	public NeuralNetwork momentumLayer(int units, Activation fn, boolean bias) {
		int inputs = (layers.size() == 0) ? this.inputUnits : layers.get(layers.size() - 1).units();
		layers.add(new MomentumLayer(units, inputs, fn, bias));
		return this;
	}

	//默认bias为空
	public NeuralNetwork momentumLayer(int units, Activation fn) {
		return momentumLayer(units, fn, true);
	}

	//默认激活函数为tanh();
	public NeuralNetwork momentumLayer(int units) {
		return momentumLayer(units, Activation.tanh());
	}

	public NeuralNetwork layer(int units, Activation fn, boolean bias) {
		//获取上一层网络的节点数
		int inputs = (layers.size() == 0) ? this.inputUnits : layers.get(layers.size() - 1).units();
		layers.add(new Layer(units, inputs, fn, bias));
		return this;
	}

	public NeuralNetwork layer(int units, Activation fn) {
		return layer(units, fn, true);
	}

	public NeuralNetwork layer(int units) {
		return layer(units, Activation.tanh());
	}

	//从前到后，将前一层的输出反馈给本层作输入,返回最后一层的feed结果
	public double[] feed(double[] x) {
		for (Layer l : layers)
			x = l.feed(x);
		return x;
	}

	//获取最后一层的Value
	public double[] output() {
		return layers.get(layers.size() - 1).value();
	}

	//将每一层的错误累加起来
	public double error() {
		double E = 0;
		for (Layer l : layers)
			E += l.error();
		return E;
	}

	public NeuralNetwork train(double[] x, double[] y) {
		//首先前向传播值
		double[] out = feed(x);
		//计算最终误差
		double[] s = new double[out.length];
		for (int i = 0; i < y.length; i++)
			s[i] = y[i] - out[i];
		//打印每层的偏差向量
		System.out.println(Layer.printVec(y)+" - "+Layer.printVec(out)+" => "+Layer.printVec(s));
		//反向传播错误率，同时更新了节点错误率
		for (int i = layers.size() - 1; i >= 0; i--)
			s = layers.get(i).backprop(s);
		//按照错误率和前一层值更新本层本层对应节点权重
		for (Layer l : layers)
			x = l.update(x, learningRate);
		return this;
	}

	public NeuralNetwork initialize() {
		for (Layer l : layers)
			l.initialize();
		return this;
	}

	//获取一个新的神经网络对象
	public static NeuralNetwork build() {
		return new NeuralNetwork();
	}

}
