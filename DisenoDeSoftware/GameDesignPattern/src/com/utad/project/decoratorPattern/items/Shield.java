package com.utad.project.decoratorPattern.items;

import java.util.List;

import com.utad.project.base.*;
import com.utad.project.base.Character;
import com.utad.project.decoratorPattern.*;
import com.utad.project.singletonPattern.GameManager;

public class Shield extends ActiveItemDecorator{ //Defiende de la primera habilidad leve recibida

	public Shield() {
		this(null);
	}
	public Shield(Item equipment) {
		super(equipment, "Shield", "Shield", new Stats(0, 0, 0, 5, 0), ActionType.DEFENSIVE, SkillType.PHYSICAL, Tier.C);
	}

	@Override
	public void useSkill(Character user, Character target) {
		List<Action> actions = GameManager.getManager().getActions();
		for(int i = 0; i < actions.size(); i++) {
			//Una accion dirigida al jugador que usa el escudo
			if(actions.get(i).getActionType()==ActionType.OFFENSIVE && actions.get(i).getTarget() ==user) {
				if(actions.get(i).getVariation().getLife()>-30) {
					actions.get(i).getVariation().setLife(0);
					informPlayer(user.getName()+"shields from "+actions.get(i).getUser().getName());
					break; //Solo funciona con la primera
				}
					
			}
		}
	}
}
