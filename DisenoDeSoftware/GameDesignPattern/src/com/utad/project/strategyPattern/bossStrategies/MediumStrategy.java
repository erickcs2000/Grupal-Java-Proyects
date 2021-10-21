package com.utad.project.strategyPattern.bossStrategies;
import java.util.ArrayList;
import java.util.List;

import com.utad.project.base.ActionType;
import com.utad.project.base.Character;
import com.utad.project.base.Enemy;
import com.utad.project.base.Player;
import com.utad.project.decoratorPattern.ActiveItemDecorator;
import com.utad.project.decoratorPattern.Item;
import com.utad.project.strategyPattern.DecisionTemplate;

public class MediumStrategy extends DecisionTemplate{
	
	// Declaramos las items donde se almacenaran los items de cada accion posible
	ActiveItemDecorator offensive;
	ActiveItemDecorator defensive;
	ActiveItemDecorator neutral;
	
	// Esta funcion elige de que tipo va a ser la accion que va a realizar
	protected void selectSkill(int[] options, List<ActiveItemDecorator> skills, Enemy user, Player target) {
		int total = 0;
		options[0] *=1.5;	// Este multiplicador hace ms agresivo a la IA
		for(int i =0; i < options.length; i++) {
			total += options[i];
		}
		int random = (int)Math.random()*(total);
		if(random < options[0]) {
			offensive.useSkill(user, target);
		}else if(random < options[0] + options[1]) {
			defensive.useSkill(user, target);
		}else {
			neutral.useSkill(user, user);
		}
		
	}
	
	//Funcion que comprueba que tan "rentable" es defender (no tiene en cuenta objetos al contrario del HardStrategy)
	protected int worthDefend(Enemy user, Player player) {
		defensive = selectItem(ActionType.DEFENSIVE, user);
		int i = 0;
		// Comprueba si el ataque del jugador es mayor a la defensa del enemigo
		if(player.getEquipment().getAttack() > user.getEquipment().getDefense()*1.3) {
			i++;
		}
		// Comprueba si la vida del enemigo es menor al 75%
		if(user.getEquipment().getLife() < 0.75* user.getEquipment().getLife()) {
			i++;
		}
		return i;
	}
	
	//Funcion que comprueba que tan "rentable" es atacar (no tiene en cuenta objetos al contrario del HardStrategy)
	protected int worthAttack(Enemy user, Player player) {
		offensive = selectItem(ActionType.OFFENSIVE, user);	
		int i = 0;
		// Comprueba que el ataque del enemigo seaa mejor que la defensa del jugador
		if(user.getEquipment().getAttack() > player.getEquipment().getDefense()) {
			i++;
		}
		// Comprueba si el enemigo puede aguantar 3 ataques o no
		if(user.getEquipment().getAttack()*3 > player.getEquipment().getLife()) {
			i++;
		}
		return i;
	}
	
	protected int worthNeutral(Enemy user, Player player) {
		neutral = selectItem(ActionType.NEUTRAL, user);
		if(neutral == null) {
			return 0;
		}else {
			return 2;
		}	
	}

	// Esta funcion devuelve la posicion del primer equipment del tipo que le pases
	private ActiveItemDecorator selectItem(ActionType style, Character user) {
		List<ActiveItemDecorator> list = new ArrayList<ActiveItemDecorator>(); 
		list = user.getEquipment().areThereAnyActives(list);
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getActionType() == style) {
				return list.get(i);
			}
		}
		return null;
	}
}
