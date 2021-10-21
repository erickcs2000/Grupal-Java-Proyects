package com.utad.project.abstractFactoryPattern.enemies.bosses;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.RegularItem;
import com.utad.project.decoratorPattern.items.bossWeapons.ChaosBlade;
import com.utad.project.strategyPattern.bossStrategies.MediumStrategy;

public class BossWorld1 extends Boss{
	
	public BossWorld1() {
		super("Chaos Witch Quelaag", "src/com/utad/project/display/quelaag.png");
		this.equipment = new RegularItem(this.equipment, "Base Equipment", new Stats(90,90,20,25,2));
		this.addItem(new ChaosBlade()); 
		this.behaviour = new MediumStrategy();
	}
}
