package com.utad.project.base;

import com.utad.project.decoratorPattern.ActiveItemDecorator;
import com.utad.project.decoratorPattern.RegularItem;

public class Player extends Character{
	
	//Variables para almacenar la decision desde que se decide hasta que se ejecuta
	private ActiveItemDecorator nextSkill;
	private Character target;

	public Player(String name) {
		super(name,"src/com/utad/project/display/Player.png");
		addItem(new RegularItem(new Stats(300,300,50,40,2))); //valores por defecto para las Stats del jugador
	}

	//Utilizar la habilidad previamente seleccionada
	public void decision() {
		nextSkill.useSkill(this, target); 
	}

	public ActiveItemDecorator getNextSkill() {
		return nextSkill;
	}

	public void setNextSkill(ActiveItemDecorator nextSkill) {
		this.nextSkill = nextSkill;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}
}
