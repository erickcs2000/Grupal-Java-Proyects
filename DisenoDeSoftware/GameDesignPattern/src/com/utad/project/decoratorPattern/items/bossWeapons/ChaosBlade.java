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

public class ChaosBlade extends ActiveItemDecorator{ //Resta un porcentaje de vida al usuario que utiliza la habilidad

	public ChaosBlade() {
		this(null);
	}
	public ChaosBlade(Item equipment) {
		super(equipment, "Chaos Blade", "Black Fire Slash", new Stats(0, 0, 5, 0, 0), ActionType.OFFENSIVE, SkillType.PHYSICAL, Tier.S);
	}

	public void useSkill(Character user, Character target) {
		int aux = user.getEquipment().getAttack() - (target.getEquipment().getDefense() / 2); //Resultado del combate, ignorando la mitad de la defensa del objetivo.
		if(aux <= 0) { //La defensa es mayor que el ataque.
			aux = 0; //La vida que se resta al objetivo es cero.
		}		
		Stats variationTarget = new Stats( -aux, 0, 0, 0, 0); //Actualizar vida actual del objetivo.
		
		Stats variationUser;
		if(user.getEquipment().getLife() > 1 && user.getEquipment().getLife() < (aux / 4)) {
			variationUser = new Stats( -(user.getEquipment().getMaxLife() - 1) , 0, 0, 0, 0); //Si el daño es superior a la vida del usuario y este tiene más de 1 de vida, 
																							//en vez de matarle le deja a 1 de vida.
		}
		
		else {
			variationUser = new Stats( -(aux / 4) , 0, 0, 0, 0); //Actualizar vida actual del usuario.
		}
		
		GameManager.getManager().getActions().add(new Action(variationTarget, actionType, skillType, user, target));
		GameManager.getManager().getActions().add(new Action(variationUser, actionType, skillType, user, user));
		informPlayer(user,target);

	}

}
