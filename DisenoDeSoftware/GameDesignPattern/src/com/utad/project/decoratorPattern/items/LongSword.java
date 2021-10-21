package com.utad.project.decoratorPattern.items;

import com.utad.project.base.*;
import com.utad.project.base.Character;
import com.utad.project.decoratorPattern.*;
import com.utad.project.singletonPattern.GameManager;

public class LongSword extends ActiveItemDecorator{ //Ataque basico con espada

	public LongSword() {
		this(null);
	}
	public LongSword(Item equipment) {
		super(equipment, "Long Sword", "Sword Attack", new Stats(0, 0, 10, 0, 0), ActionType.OFFENSIVE, SkillType.PHYSICAL, Tier.C);
	}

	@Override
	public void useSkill(Character user, Character target) {
		int aux = user.getEquipment().getAttack()- target.getEquipment().getDefense(); //Resultado del combate
		if(aux <= 0) { //La defensa es mayor que el ataque
			aux = 0; //La vida que se resta al objetivo es cero
		}
		Stats variation = new Stats( -aux, 0, 0, 0, 0); //Actualizar vida actual
		
		GameManager.getManager().getActions().add(new Action(variation, actionType, skillType,user, target));
		informPlayer(user,target);
	}

}
