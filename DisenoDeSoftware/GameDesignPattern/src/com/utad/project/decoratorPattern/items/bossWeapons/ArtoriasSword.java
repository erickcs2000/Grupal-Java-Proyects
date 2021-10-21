package com.utad.project.decoratorPattern.items.bossWeapons;

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

public class ArtoriasSword extends ActiveItemDecorator{ //Ignora toda la defensa y confunde al enemigo.

	public ArtoriasSword() {
		this(null);
	}
	public ArtoriasSword(Item equipment) {
		super(equipment, "Artorias Sword", "Dark Void", new Stats(0, 0, 10, 0, 0), ActionType.OFFENSIVE, SkillType.PHYSICAL, Tier.S);
	}

	public void useSkill(Character user, Character target) {
		int aux = user.getEquipment().getAttack()/2; //Resultado del combate, ignorando toda la defensa del objetivo.
		
		Stats variationTarget = new Stats( -aux, 0, 0, 0, 0); //Actualizar vida actual del objetivo.

		GameManager.getManager().getActions().add(new Action(variationTarget, actionType, skillType, user, target));
		target.getState().setSuggestion(States.CONFUSED); //Intento de confundir.
		informPlayer(user,target);

	}

}
