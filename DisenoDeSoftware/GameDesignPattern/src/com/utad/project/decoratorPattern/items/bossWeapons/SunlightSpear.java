package com.utad.project.decoratorPattern.items.bossWeapons;

import java.util.Random;

import com.utad.project.base.Action;
import com.utad.project.base.ActionType;
import com.utad.project.base.Character;
import com.utad.project.base.SkillType;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.ActiveItemDecorator;
import com.utad.project.decoratorPattern.Item;
import com.utad.project.decoratorPattern.Tier;
import com.utad.project.singletonPattern.GameManager;
import com.utad.project.statePattern.States;

public class SunlightSpear extends ActiveItemDecorator{ //Puede inflingir un golpe crítico y paraliza al objetivo.

	public SunlightSpear() {
		this(null);
	}
	public SunlightSpear(Item equipment) {
		super(equipment, "Sunlight Spear", "Thunder Spike", new Stats(0, 0, 10, 0, 0), ActionType.OFFENSIVE, SkillType.PHYSICAL, Tier.S);
	}

	public void useSkill(Character user, Character target) {
		Random rand = new Random();
		
		int aux = user.getEquipment().getAttack() - (target.getEquipment().getDefense() / 3); //Resultado del combate, ignorando dos tercios de la defensa del objetivo.
		
		if(rand.nextInt() < 30) { //Tiene una probabilidad del 30% de hacer golpe crítico.
			aux = (int) (aux * 1.5);
		}
		
		if(aux <= 0) { //La defensa es mayor que el ataque.
			aux = 0; //La vida que se resta al objetivo es cero.
		}		
		
		Stats variationTarget = new Stats( -aux, 0, 0, 0, 0); //Actualizar vida actual del objetivo.
		
		GameManager.getManager().getActions().add(new Action(variationTarget, actionType, skillType, user, target));
		target.getState().setSuggestion(States.PARALYZED); //Intento de paralizar.
		informPlayer(user,target);

	}

}
