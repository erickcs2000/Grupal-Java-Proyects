package com.utad.project.base;

public class Stats{ //Wrapper de atributos 
	private int life=0;
	private int maxLife=0;
	private int attack=0;
	private int defense=0;
	private int speed=0;
	
	public Stats() {
		super();
	}
	
	public Stats(int life, int maxLife, int attack, int defense, int speed) {
		super();
		this.life = life;
		this.maxLife = maxLife;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
	}
	
	public String toString() {
		return "(Life: " + life + ", MaxLife: " + maxLife +", Att: " + attack + ", Def: " + defense + ", Speed: " + speed + ")";
		
	}
	
	public void applyStats(Stats variation) { //Aplicar una variacion de estadisticas (suma de objetos tipo Stats)
		life+=variation.getLife();
		maxLife+=variation.getMaxLife();
		attack+=variation.getAttack();
		defense+=variation.getDefense();
		speed+=variation.getSpeed();
	}
	
	public void backToNormal() { //Devolver al estado basico todas las estadisticas menos la vida actual
		maxLife=0;
		attack = 0;
		defense = 0;
		speed = 0;
	}
		
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getMaxLife() {
		return maxLife;
	}
	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
