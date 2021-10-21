package com.utad.project.base;

import java.util.ArrayList;
import java.util.List;

import com.utad.project.decoratorPattern.*;
import com.utad.project.decoratorPattern.items.LongSword;
import com.utad.project.decoratorPattern.items.Shield;
import com.utad.project.statePattern.*;

public abstract class Character {
	
	protected String name;
	protected String sprite;
	protected State state;
	protected Item equipment;
	
	public Character(String name, String sprite) {
		super();
		this.name = name;
		this.sprite = sprite;
		this.state = new State(); //Crea el estado, por defecto standard
		this.equipment = new Equipment(); //Inicializa el equipamiento
		
		//Por defecto se empieza con espada y escudo
		this.equipment = new LongSword(this.equipment); //Necesario para atacar
		this.equipment = new Shield(this.equipment); //Necesario para defenderse
	}
	
	//Metodo que las clases hijas deben sobreescribir para comunicarse con el GameManager
	public abstract void decision();
	
	//Modificar la accion en funcion del estado
	public Action StatusEffect(Action action) {
		return state.effect(action);
	}
	
	//Decorar el equipamiento filtrando posibles errores
	public void addItem(ItemDecorator newItem) {
		this.equipment = this.equipment.addItem(newItem);
	}
	
	//Eliminar un item de la jerarquia
	public void deleteItem(ItemDecorator component) {
		this.equipment = this.equipment.deleteItem(component);
	}
	
	//Modificar el componente base Equipment con estadisticas temporales
	public Stats modifyStats() {
		List<PassiveItemDecorator> list = new ArrayList<PassiveItemDecorator>();
		Stats aux = new Stats(equipment.getLife(),equipment.getMaxLife(), equipment.getAttack(),equipment.getDefense(), equipment.getSpeed());
		Stats result = new Stats();
		list = equipment.areThereAnyPassives(list);
		for(int i=0; i < list.size(); i++) {
			result.applyStats(((PassiveItemDecorator) list.get(i)).modifyStats(aux));
		}
		return result;
	}
	
	public void applyStats(Stats variation) {
		equipment.applyStats(variation);
	}
	
	public void backToNormal() {
		equipment.backToNormal();
	}
	
	public boolean isAlive() {
		if(equipment.getLife()>0) {
			return true;
		}
		return false;
	}
	
	public Item getEquipment() {
		return equipment;
	}
	
	public void setEquipment(Item equipment) {
		this.equipment = equipment;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public State getState() {
		return state;
	}
	
	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}
	
	public String toString() {
		return "[Name->" + this.name + ", Stats->(Life: " + this.equipment.getLife() +
			", Att: " + this.equipment.getAttack() + ", Def: " + this.equipment.getDefense() + ", Speed: " + this.equipment.getSpeed() + ")]";
	}
	
	
}
