package com.utad.project.abstractFactoryPattern;

public enum World {
	WORLD1(1), WORLD2(1.2), WORLD3(1.4), WORLD4(1.6), WORLD5(1.8), END(0);
	
	private double complexFactor; //Factor de complejidad que determina el poder de los enemigos del nivel
	public double getComplexFactor() {
		return complexFactor;
	}
	
	private World(double complexFactor) {
		this.complexFactor = complexFactor;
	}
}
