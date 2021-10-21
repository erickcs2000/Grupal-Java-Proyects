package com.utad.project.strategyPattern.bossStrategies;

import java.util.ArrayList;
import java.util.List;

import com.utad.project.base.*;
import com.utad.project.decoratorPattern.*;
import com.utad.project.strategyPattern.DecisionTemplate;

public class HardStrategy extends DecisionTemplate{
	
	// Declaramos las items donde se almacenaran los items de cada accion posible
	ActiveItemDecorator offensive;
	ActiveItemDecorator defensive;
	ActiveItemDecorator neutral;
	
	// Esta funcion elige de que tipo va a ser la accion que va a realizar
	protected void selectSkill(int[] options, List<ActiveItemDecorator> skills, Enemy user, Player target) {
		int total = 0;
		for(int i =0; i < options.length; i++) {
			total += options[i];
		}
		int random = (int)Math.random()*(total+1);
		if(random < options[0]) {
			offensive.useSkill(user, target);
		}else if(random < options[0] + options[1]) {
			defensive.useSkill(user, target);
		}else {
			neutral.useSkill(user, user);
		}
	}
	
	//Funcion que comprueba que tan "rentable" es atacar
	protected int worthAttack(Enemy enemy, Player player) {
		//Creamos una lista con los items del enemigo (el atacante) y guardamos el tier del mejor item
		List<ActiveItemDecorator> list = new ArrayList<ActiveItemDecorator>(); 
		list = enemy.getEquipment().areThereAnyActives(list);
		offensive = saveBestItem(list,ActionType.OFFENSIVE);

		//Creamos una lista con los items del jugador (el target del ataque) y guardamos el tier del mejor item
		List<ActiveItemDecorator> listPlayer = new ArrayList<ActiveItemDecorator>(); 
		listPlayer = player.getEquipment().areThereAnyActives(listPlayer);
		Item playerItem = saveBestItem(listPlayer,ActionType.DEFENSIVE);
		
		//Calculamos la probabilidad
		int i = 0;
		// Este if compara el dano, mas el dano del mejor item del enemigo contra la defensa del jugador mas la defensa de su mejor item
		if(enemy.getEquipment().getAttack() > player.getEquipment().getDefense() ) {
			i += 2;
		}
		// Comprobamos si el enemigo es capaz de matarlo en unos 3 turnos teniendo en cuenta los mismos datos que el if anterior
		if(enemy.getEquipment().getAttack()*2 - player.getEquipment().getDefense() > 
		player.getEquipment().getLife()) {
			i+=3;
		}
		return i + offensive.getTier().ordinal() - playerItem.getTier().ordinal();
	}
	
	//Funcion que comprueba que tan "rentable" es defender
	protected int worthDefend(Enemy enemy, Player player) {
		List<ActiveItemDecorator> list = new ArrayList<ActiveItemDecorator>(); 
		list = enemy.getEquipment().areThereAnyActives(list);
		Item defensive = saveBestItem(list,ActionType.DEFENSIVE);
		//Creamos una lista con los items del jugador (el target del ataque)
		List<ActiveItemDecorator> listPlayer = new ArrayList<ActiveItemDecorator>(); 
		listPlayer = player.getEquipment().areThereAnyActives(listPlayer);
		Item playerItem = saveBestItem(list,ActionType.OFFENSIVE);
		int i = 0;
		// Este if compara el dano, mas el dano del mejor item del enemigo contra la defensa del jugador mas la defensa de su mejor item
		if(enemy.getEquipment().getAttack()  > player.getEquipment().getDefense()) {
			i += 2;
		}
		// Comprobamos si el enemigo es capaz de matarlo en unos 3 turnos teniendo en cuenta los mismos datos que el if anterior
		if((player.getEquipment().getAttack() - player.getEquipment().getDefense() )*2 > 
		enemy.getEquipment().getLife()) {		
			i+=3;
		}
		return i + defensive.getTier().ordinal() - playerItem.getTier().ordinal();
	}
	//Esta funcion devuelve la posibilidad de que use un item neutral basandose en el tier del item
	protected int worthNeutral(Enemy enemy, Player player) {
		List<ActiveItemDecorator> list = new ArrayList<ActiveItemDecorator>(); 
		list = enemy.getEquipment().areThereAnyActives(list);
		neutral = saveBestItem(list, ActionType.NEUTRAL);
		// Si no tiene devuelve 0 de probabilidad
		if(neutral == null) {
			return 0;
		}
		return neutral.getTier().ordinal();
	}
	
	// Esta funcion devuelve unoo de los mejores items que tenga el enemigo, del tipo de accion que se solicita
	private ActiveItemDecorator saveBestItem(List<ActiveItemDecorator> skills, ActionType action){
		// Esto guarda el valor de la mejor tier de sus items
		int tierValue = -1;
		//Recorre la lista buscando el tier mas alto
		for(int i =0; i < skills.size(); i++) {
			if(skills.get(i).getActionType() == action) {
				if(skills.get(i).getTier().ordinal() > tierValue) {
					tierValue = skills.get(i).getTier().ordinal();
				}
			}
		}
		// Como puede ser que no tenga objetos neutrales, si el tier list no ha cambiado y sigue siendo 0 significa que no tiene
		if(tierValue == -1) {
			return null;
		}
		// se crea una lista con la posicion de los items de mejor nivel
		ArrayList<Integer>bestItems = new ArrayList<Integer>();
		for(int i =0; i < skills.size(); i++) {
			if(skills.get(i).getActionType() == action) {
				if(skills.get(i).getTier().ordinal() == tierValue) {
					bestItems.add(i);
				}
			}
		}
		// Elige un item de forma aleatoria de la lista
		boolean correctAbilitySelected = false;
		int skill = 0;
		int numberofSkills = skills.size();
		do {
			skill = (int)(Math.random()*numberofSkills);
			for(int i =0; i < bestItems.size(); i++) {
				if(skill == bestItems.get(i)) {
					correctAbilitySelected = true;
				}
			}
		}while(correctAbilitySelected == false);
		// retorna el item
		return skills.get(skill);
	}

}
