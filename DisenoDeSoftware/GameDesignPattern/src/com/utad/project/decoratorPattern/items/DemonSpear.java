package com.utad.project.decoratorPattern.items;

import com.utad.project.base.*;
import com.utad.project.decoratorPattern.*;

public class DemonSpear extends PassiveItemDecorator{ 

	public DemonSpear() {
		this(null);
	}
	public DemonSpear(Item equipment) {
		super(equipment, "Demon Spear", new Stats(0, 0, 20, 0, 0), Tier.S);
	}
	

	@Override
	public Stats modifyStats(int life, int maxLife, int attack, int defense, int speed) {
		return new Stats(0, 0, attack, -defense, 1); //Duplica el ataque y reduce a 0 la defensa. Aumenta la velocidad
	}

}
