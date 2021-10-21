package com.utad.project.decoratorPattern;

import java.util.List;

import com.utad.project.base.Stats;

public class Equipment implements Item{ //Componente base
	private Stats stats;
	
	public Equipment() {
		setStats(new Stats(0,0,0,0,0));
	}
	@Override
	public void applyStats(Stats variation) { //Aplicar una variacion de estadisticas
		stats.applyStats(variation);
	}
	@Override
	public void backToNormal() { //Devolver al estado basico todas las estadisticas menos la vida actual
		stats.backToNormal();
	}
	
	@Override
	public Item addItem(ItemDecorator newItem) {
		newItem.setEquipment(this);
		return newItem;
	}
	@Override
	public Item isThereAny(Item model){
		return null; //Se ha llegado al final y no se ha encontrado un model
		
	}
	@Override
	public List<ActiveItemDecorator> areThereAnyActives(List<ActiveItemDecorator> list){
		return list; //Se ha llegado al final y se devuelve la lista de los encontrados
	}
	@Override
	public List<PassiveItemDecorator> areThereAnyPassives(List<PassiveItemDecorator> list){
		return list; //Se ha llegado al final y se devuelve la lista de los encontrados
	}
	@Override
	public Item deleteItem(ItemDecorator component) {
		return this;//Se ha llegado al final y no se ha encontrado un component
	}

	@Override
	public List<UsableItemDecorator> areThereAnyUsables(List<UsableItemDecorator> list) {
		return list; //Se ha llegado al final y se devuelve la lista de los encontrados
	}
	
	@Override
	public int getLife() {
		return stats.getLife();
	}
	@Override
	public void setLife(int life) {
		stats.setLife(life);
	}
	@Override
	public int getMaxLife() {
		return stats.getMaxLife();
	}
	public void setMaxLife(int maxLife) {
		stats.setMaxLife(maxLife);
	}
	@Override
	public int getAttack() {
		return stats.getAttack();
	}
	public void setAttack(int attack) {
		stats.setAttack(attack);;
	}
	@Override
	public int getDefense() {
		return stats.getDefense();
	}
	public void setDefense(int defense) {
		stats.setDefense(defense);;
	}
	@Override
	public int getSpeed() {
		return stats.getSpeed();
	}
	public void setSpeed(int speed) {
		stats.setSpeed(speed);;
	}

	@Override
	public Tier getTier() {
		return null;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getDesc() {
		return "";
	}
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}

}
