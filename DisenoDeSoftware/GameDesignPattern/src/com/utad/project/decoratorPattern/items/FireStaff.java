package com.utad.project.decoratorPattern.items;

import com.utad.project.base.*;
import com.utad.project.base.Character;
import com.utad.project.decoratorPattern.*;
import com.utad.project.singletonPattern.GameManager;


public class FireStaff extends ActiveItemDecorator{ //Ataque basico magico, ignora la defensa

	public FireStaff() {
		this(null);
	}
	public FireStaff(Item equipment) {
		super(equipment, "Fire Staff", "Fire ball", new Stats(0, 0, 10, 0, 0),ActionType.OFFENSIVE, SkillType.MAGIC, Tier.B);
	}

	@Override
	public void useSkill(Character user, Character target) {
		Stats variation = new Stats(-10, 0, 0, 0, 0); //Actualizar vida actual
		
		GameManager.getManager().getActions().add(new Action(variation, actionType, skillType,user, target));
		informPlayer(user,target);
	}

}
