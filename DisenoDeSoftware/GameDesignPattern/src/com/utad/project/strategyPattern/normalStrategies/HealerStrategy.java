package com.utad.project.strategyPattern.normalStrategies;

import java.util.ArrayList;
import java.util.List;

import com.utad.project.base.ActionType;
import com.utad.project.base.Character;
import com.utad.project.base.Enemy;
import com.utad.project.base.Player;
import com.utad.project.decoratorPattern.ActiveItemDecorator;
import com.utad.project.decoratorPattern.items.*;
import com.utad.project.singletonPattern.GameManager;
import com.utad.project.statePattern.StateStandard;
import com.utad.project.strategyPattern.DecisionTemplate;
// Sobreescribir analize
// variables globales, borrar canHeal
public class HealerStrategy extends DecisionTemplate{
	
	// Declaramos las items donde se almacenaran los items de cada accion posible
	ActiveItemDecorator offensive;
	ActiveItemDecorator defensive;
	ActiveItemDecorator neutral;
	ActiveItemDecorator heal;
	int targetPos = -1;
	
	//Modifica el metodo analize para tener en cuenta si puede curar
	protected int[] analize(Enemy user, Player player) {
		int[] options = new int[4];
		options[0] = worthAttack(user,  player);
		options[1] = worthDefend(user,  player);
		options[2] = worthNeutral(user,  player);
		options[3] = worthHeal(user, player);
		return options;
	}
	
	//Esta funcion selecciona la accion que finalmente va a hacer el enemigo
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
		}else if(random < options[0] + options[1] + options[2]){
			neutral.useSkill(user, target);
		}else {
			heal.useSkill(user, GameManager.getManager().getCharacters().get(targetPos));
		}
	}
	
	// Esta funcion se encarga de ver tanto si es rentable como si siquiera puede curar
	private int worthHeal(Enemy user, Character player) {
		List<ActiveItemDecorator> skills = new ArrayList<ActiveItemDecorator>();
		skills = user.getEquipment().areThereAnyActives(skills);
		//Primero comprueba si tiene una pocion
		heal = (ActiveItemDecorator) user.getEquipment().isThereAny(new Potion(0));
		if(heal == null) {
			// Sino tiene pocion, revisa si tiene un antidoto 
			heal = (ActiveItemDecorator) user.getEquipment().isThereAny(new Antidote(0));
			// Sino la tiene devuleve 0 de probabilidad
			if(heal == null) {
				return 0;
			}else {
				// Si si la tiene revisa si hay alguien que lo necesite
				for(int i =0; i < GameManager.getManager().getCharacters().size(); i++) {
					if(GameManager.getManager().getCharacters().get(i) instanceof Enemy) {
						if(!(GameManager.getManager().getCharacters().get(i).getState().getState() instanceof StateStandard)) {
							targetPos = i;
						}
					}
				}
			}
		}else {
			//si tiene cura busca alguien que necesita la cura
			boolean firtEnemy = true;
			for(int i =0; i < GameManager.getManager().getCharacters().size(); i++) {
				if(GameManager.getManager().getCharacters().get(i) instanceof Enemy) {
					if(firtEnemy) {
						targetPos = i;
						firtEnemy = false;
					}else if(GameManager.getManager().getCharacters().get(i).getEquipment().getLife() < GameManager.getManager().getCharacters().get(targetPos).getEquipment().getLife()) {
						targetPos = i;
					}
				}
			}
		}
		// Si no ha seleccionado a nadie devuelve 0, y si si hay alguien devuelve 10 para hacer que la probabilidad sea alta
		if(targetPos == -1) {
			return 0;
		}else {
			return 10;
		}
		
	}
	
	protected int worthAttack(Enemy user, Player player) {
		offensive = selectItem(ActionType.OFFENSIVE, user);
		return 1;
	}
	protected int worthDefend(Enemy user, Player player) {
		defensive = selectItem(ActionType.DEFENSIVE, user);
		return 1;
	}
	protected int worthNeutral(Enemy user, Player player) {
		neutral = selectItem(ActionType.NEUTRAL, user);
		if(neutral == null) {
			return 0;
		}else {
			return 1;
		}
	}
	
	// Esta accion devuelve el primer item que encuentra del tipo que le pasas
	private ActiveItemDecorator selectItem(ActionType style, Character user) {
		List<ActiveItemDecorator> list = new ArrayList<ActiveItemDecorator>(); 
		list = user.getEquipment().areThereAnyActives(list);
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getActionType() == style) {
				return list.get(i);
			}
		}
		// Si no hay ningun item del tipo de item buscado devuelve null;
		return null;
	}
}
