package com.utad.project.decoratorPattern.items;

import java.util.List;

import com.utad.project.base.*;
import com.utad.project.base.Character;
import com.utad.project.decoratorPattern.*;
import com.utad.project.singletonPattern.GameManager;

public class SolarAegis extends ActiveItemDecorator{ //Devuelve daño de un ataque

	public SolarAegis() {
		this(null);
	}
	public SolarAegis(Item equipment) {
		super(equipment, "Solar Aegis", "Aegis desire", new Stats(0, 0, 0, 20, -2), ActionType.DEFENSIVE, SkillType.PHYSICAL, Tier.A);
	}

	@Override
	public void useSkill(Character user, Character target) {
		List<Action> actions = GameManager.getManager().getActions();
		for(int i = 0; i < actions.size(); i++) {
			//Una accion ofensiva y fisica dirigida al jugador que usa el objeto
			if(actions.get(i).getActionType()==ActionType.OFFENSIVE &&actions.get(i).getSkillType()==SkillType.PHYSICAL
			&& actions.get(i).getTarget() ==user) {
				if(-actions.get(i).getVariation().getLife()<user.getEquipment().getDefense()) { //Comprueba que haya defensa de sobra
				
					informPlayer(user,actions.get(i).getUser());
					//Devuelve un ataque con la defensa de sobra a cada enemigo
					Stats variation = new Stats(-user.getEquipment().getDefense()+
							actions.get(i).getVariation().getLife()+
							actions.get(i).getUser().getEquipment().getDefense()/2
							,0,0,0,0);
					actions.add(new Action(variation, actionType, skillType, user, actions.get(i).getUser()));
					
				}
			}
		}
	}

}
