package com.utad.project.decoratorPattern;

import java.util.List;

import com.utad.project.base.Stats;

public interface Item { //Interfaz general 
	
	//Obtener los atributo basicos
	public int getLife();
	public void setLife(int life);
	public int getMaxLife();
	public int getAttack();
	public int getDefense();
	public int getSpeed();
	public String getName();
	public String getDesc();
	public Tier getTier();
	
	public void applyStats(Stats variation); //Suma de estadisticas
	public void backToNormal(); //Devolver a valores por defecto
	public Item addItem(ItemDecorator newItem); //Añadir un nuevo item al equipamiento sin que este repetido
	public Item deleteItem(ItemDecorator component);//Elimina un item de la jerarquia
	
	//Busca un item en la jerarquia, lo devuelve si lo encuentra y si no devuelve null
	public Item isThereAny(Item model); 
	//Crea una lista con todos los items con activa en la jerarquia
	public List<ActiveItemDecorator> areThereAnyActives(List<ActiveItemDecorator> list);
	//Crea una lista con todos los items con pasiva en la jerarquia
	public List<PassiveItemDecorator> areThereAnyPassives(List<PassiveItemDecorator> list);
	//Crea una lista con todos los items usables en la jerarquia
	public List<UsableItemDecorator> areThereAnyUsables(List<UsableItemDecorator> list);
}
