package com.utad.project.decoratorPattern.items;

import java.util.List;

import com.utad.project.base.*;
import com.utad.project.base.Character;
import com.utad.project.decoratorPattern.*;
import com.utad.project.singletonPattern.GameManager;
import com.utad.project.statePattern.States;

public class ElectricShield extends ActiveItemDecorator{ //Paraliza a los atacantes

	public ElectricShield() {
		this(null);
	}
	public ElectricShield(Item equipment) {
		super(equipment, "Electric Shield", "Static field", new Stats(0, 0, 0, 20, -2), ActionType.DEFENSIVE, SkillType.MAGIC, Tier.B);
	}

	@Override
	public void useSkill(Character user, Character target) {
		List<Action> actions = GameManager.getManager().getActions();
		for(int i = 0; i < actions.size(); i++) {
			//Una accion ofensiva y fisica dirigida al jugador que usa el objeto
			if(actions.get(i).getActionType()==ActionType.OFFENSIVE && actions.get(i).getTarget() == user) {
				if(user.getEquipment().getAttack() > user.getEquipment().getDefense()) {
					actions.get(i).getUser().getState().setSuggestion(States.PARALYZED); //Intento de paralisis
					
				}else { //La defensa del usuario es mayor que su ataque
					actions.get(i).getUser().getState().setSuggestion(States.SERIOUSLYPARALYZED); //Intento de paralisis grave
				}
				informPlayer(user,target);
			}
		}
	}

}
