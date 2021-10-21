package com.utad.project.decoratorPattern;

import java.util.ArrayList;
import java.util.List;

import com.utad.project.base.*;

public abstract class ItemDecorator implements Item{
	protected Item equipment; //Objeto decorado
	protected Stats stats; //Estadisticas con las que decora
	protected String name;
	protected Tier tier; //Categoria del item
	
	public ItemDecorator() {
		super();
	}
	public ItemDecorator(Item equipment, String name,  Stats stats, Tier tier) {
		this.equipment = equipment;
		this.stats = stats;
		this.name = name;
		this.tier = tier;
	}
	
	//Metodo para añadir un item sin que haya repeticiones
	//Evita errores producidos por varios items con la misma activa
	public Item addItem(ItemDecorator newItem) { 
		Item aux = isThereAny(newItem);
		if(aux==null || newItem instanceof RegularItem) { //No hay items del mismo tipo equipados
			newItem.setEquipment(this); //Decorar
			return newItem;
		}else { //Hay un item del mismo tipo equipado
			if(newItem instanceof UsableItemDecorator) { //El objeto es un consumible
				((UsableItemDecorator) aux).addAmount(((UsableItemDecorator) newItem).getAmount()); //Aumentar su cantidad
				return this;
			}else { //En cualquier otro caso, crear una copia sin habilidades, solo estadisticas
				Stats newItemCopy = new Stats(newItem.getLife(), newItem.getMaxLife(), newItem.getAttack(), newItem.getDefense(), newItem.getSpeed());
				ItemDecorator regularVersion = new RegularItem(this, newItem.getName()+" Repeated", newItemCopy);
				return regularVersion;
			}
			
		}
		
	}
	
	public Item deleteItem(ItemDecorator component) {
		if(component.getClass().equals(this.getClass())) {
			return equipment; //Se elimina a si mismo de la lista devolviendo el componente que decora
		}else {
			equipment = equipment.deleteItem(component); //Llamada recursiva
			return this;
		}
	}
	
	public Item isThereAny(Item model){
		if(this.getClass() == model.getClass()) { //Si son de la misma clase devuelve el objeto
			return this;
		}else {
			return equipment.isThereAny(model);	//Llamada recursiva
		}
		
	}
	
	public List<ActiveItemDecorator> areThereAnyActives(List<ActiveItemDecorator> list){
		if(this instanceof ActiveItemDecorator) { //Si es un objeto con activa lo añade a la lista
			list.add((ActiveItemDecorator) this);
		}
		
		return equipment.areThereAnyActives(list); //Llamada recursiva
	}
	public List<UsableItemDecorator> areThereAnyUsables(List<UsableItemDecorator> list){
		if(this instanceof UsableItemDecorator) { //Si es un objeto usable lo añade a la lista
			list.add((UsableItemDecorator) this);
		}
		
		return equipment.areThereAnyUsables(list); //Llamada recursiva
	}
	public List<PassiveItemDecorator> areThereAnyPassives(List<PassiveItemDecorator> list){
		if(this instanceof PassiveItemDecorator) {//Si es un objeto con pasiva lo añade a la lista
			list.add((PassiveItemDecorator) this);
		}
		
		return equipment.areThereAnyPassives(list);//Llamada recursiva
	}
	
	public void applyStats(Stats variation) { //Solo se aplican en el componente base
		equipment.applyStats(variation);
	}
	
	public void backToNormal() { //Solo se aplica en el componente base
		equipment.backToNormal();
	}
	
	public Item getEquipment() {
		return equipment;
	}
	public void setEquipment(Item equipment) {
		this.equipment = equipment;
	}
	public int getLife() {
		if(equipment!=null) {//Filtrar para instancias de decoradores que aun no tienen objeto decorado
			return equipment.getLife()+stats.getLife();
		}else {
			return 0+stats.getLife();
		}
		
	}
	
	public void setLife(int life) {
		equipment.setLife(life);
	}
	public int getAttack() {
		if(equipment!=null) {//Filtrar para instancias de decoradores que aun no tienen objeto decorado
			return equipment.getAttack()+stats.getAttack();
		}else {
			return 0+stats.getAttack();
		}
		
	}
	
	public int getDefense() {
		if(equipment!=null) {//Filtrar para instancias de decoradores que aun no tienen objeto decorado
			return equipment.getDefense()+stats.getDefense();
		}else {
			return 0+stats.getDefense();
		}
		
	}
	public int getSpeed() {
		if(equipment!=null) {//Filtrar para instancias de decoradores que aun no tienen objeto decorado
			return equipment.getSpeed()+stats.getSpeed();
		}else {
			return 0+stats.getSpeed();
		}
		
	}

	public int getMaxLife() {
		if(equipment!=null) {//Filtrar para instancias de decoradores que aun no tienen objeto decorado
			return equipment.getMaxLife()+stats.getMaxLife();
		}else {
			return 0+stats.getMaxLife();
		}
		
	}
	
	public String toString() {
		return name+" "+tier+" "+stats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() { //Uso de lenguaje html
		return equipment.getDesc()+"<br>"+this.getName();
	}
	
	public Tier getTier() {
		return tier;
	}
	
	public void setTier(Tier tier) {
		this.tier = tier;
	}
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	
}
