package com.utad.project.decoratorPattern.items;

import com.utad.project.base.Action;
import com.utad.project.base.ActionType;
import com.utad.project.base.Character;
import com.utad.project.base.SkillType;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.Item;
import com.utad.project.decoratorPattern.Tier;
import com.utad.project.decoratorPattern.UsableItemDecorator;
import com.utad.project.singletonPattern.GameManager;
import com.utad.project.statePattern.States;

public class Antidote extends UsableItemDecorator{ //Cambia a estado standard si es posible 
	
	public Antidote(Item equipment, int amount) {
		super(equipment, "Antidote", "Use antidote", new Stats(0, 0, 0, 0, 0),ActionType.NEUTRAL, SkillType.MAGIC,Tier.C, amount);
	}
	public Antidote(int amount) {
		this(null, amount);
	}
	public Antidote() {
		this(null,0);
	}

	@Override
	public void useSkill(Character user, Character target) {
		if(amount > 0) { //Quedan antidotos
			target.getState().setSuggestion(States.STANDARD);	
			informPlayer(user,target);
			amount--;
		}
		
	}
}
