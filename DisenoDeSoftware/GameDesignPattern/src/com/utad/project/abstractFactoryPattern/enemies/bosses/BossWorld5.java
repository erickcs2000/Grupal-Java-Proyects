package com.utad.project.abstractFactoryPattern.enemies.bosses;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.RegularItem;
import com.utad.project.decoratorPattern.items.bossWeapons.SunlightSpear;
import com.utad.project.strategyPattern.normalStrategies.AgressiveStrategy;

public class BossWorld5 extends Boss{
	
	public BossWorld5() {
		super("Gwyn Lord of Cinder", "src/com/utad/project/display/gwyn.png");
		this.equipment = new RegularItem(this.equipment, "Base Equipment", new Stats(200,200,70,60,3));	
		this.addItem(new SunlightSpear()); 
		this.behaviour = new AgressiveStrategy();
	}
}
