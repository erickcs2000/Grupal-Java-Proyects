package com.utad.project.decoratorPattern;

import com.utad.project.base.Stats;

public class RegularItem extends ItemDecorator{ //Decoradores que solo modifican las estadisticas
	public RegularItem() {
		super();
	}
	
	public RegularItem(Item equipment, String name, Stats stats) {
		super(equipment, name, stats, Tier.C);
	}
	
	public RegularItem(String name, Stats stats) {
		this(null, name, stats);
	}
	
	public RegularItem(Stats stats) {
		this("Basic equipment",stats);
	}

}
