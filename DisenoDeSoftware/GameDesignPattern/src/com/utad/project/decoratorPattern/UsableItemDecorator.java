package com.utad.project.decoratorPattern;

import com.utad.project.base.*;

public abstract class UsableItemDecorator extends ActiveItemDecorator { //Objeto con activa que se puede usar un numero finito de veces
	
	protected int amount; //Cantidad de veces que se puede llamar a useSkill
	
	public UsableItemDecorator() {
		super();
	}
	public UsableItemDecorator(Item equipment, String name, String skillName, Stats stats, 
		ActionType actionType, SkillType skillType,Tier equipmentTier, int amount) {
		super(equipment, name, skillName, stats, actionType, skillType, equipmentTier);
		this.amount = amount;
	}
	
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void addAmount(int amount) {
		this.amount +=amount;
	}

	
}
