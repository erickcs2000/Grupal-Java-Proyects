package com.utad.project.decoratorPattern;

import com.utad.project.base.Stats;

public abstract class PassiveItemDecorator extends ItemDecorator { //Objetos con una habilidad pasiva

	public PassiveItemDecorator() {
		super();
	}
	public PassiveItemDecorator(Item equipment, String name, Stats stats, Tier tier) {
		super(equipment, name, stats, tier);
	}
	
	//Metodo para ejecutar la habilidad pasiva, devuelve un objeto tipo Stats con la variacion
	public abstract Stats modifyStats(int life, int maxLife, int attack, int defense, int speed);
	
	//Sobrecarga del metodo para que pueda recibir un objeto tipo stats 
	public Stats modifyStats(Stats equipment) {
		return modifyStats(equipment.getLife(),equipment.getMaxLife(), equipment.getAttack(), equipment.getDefense(), equipment.getSpeed());
	}

}
