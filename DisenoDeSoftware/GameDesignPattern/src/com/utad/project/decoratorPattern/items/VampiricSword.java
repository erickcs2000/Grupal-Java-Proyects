package com.utad.project.decoratorPattern.items;

import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.Item;
import com.utad.project.decoratorPattern.PassiveItemDecorator;
import com.utad.project.decoratorPattern.Tier;

public class VampiricSword extends PassiveItemDecorator{ 

	public VampiricSword(Item equipment) {
		super(equipment, "Vampiric Sword", new Stats(0, 0, 20, 0, 0), Tier.B);
	}
	

	public VampiricSword(){
		this(null);
	}


	@Override
	 //Restaura vida en funcion del ataque
	public Stats modifyStats(int life, int maxLife, int attack, int defense, int speed) {
		int newLife;
		
		if(life >= maxLife) { //Su vida sobrepasa su vida maxima 
			newLife=maxLife-life; 
		}else {
			newLife=attack/10;
		}
		return new Stats(newLife, 0, 0, 0, 0);
	}

}
