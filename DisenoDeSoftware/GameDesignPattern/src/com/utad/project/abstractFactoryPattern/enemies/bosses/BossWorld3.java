package com.utad.project.abstractFactoryPattern.enemies.bosses;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.RegularItem;
import com.utad.project.decoratorPattern.items.bossWeapons.SunlightSpear;
import com.utad.project.strategyPattern.bossStrategies.MediumStrategy;
import com.utad.project.strategyPattern.normalStrategies.AgressiveStrategy;

public class BossWorld3 extends Boss{
	
	public BossWorld3() {
		super("Orstein & Smough", "src/com/utad/project/display/orsteinsmough.png");
		this.equipment = new RegularItem(this.equipment, "Base Equipment", new Stats(140,140,35,35,2));	
		this.addItem(new SunlightSpear()); 
		this.behaviour = new MediumStrategy();
	}
}
