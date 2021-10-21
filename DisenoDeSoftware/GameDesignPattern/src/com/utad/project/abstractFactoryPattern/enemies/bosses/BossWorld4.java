package com.utad.project.abstractFactoryPattern.enemies.bosses;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.RegularItem;
import com.utad.project.decoratorPattern.items.bossWeapons.ChaosBlade;
import com.utad.project.strategyPattern.normalStrategies.AgressiveStrategy;

public class BossWorld4 extends Boss{
	
	public BossWorld4() {
		super("Gravelord Nito", "src/com/utad/project/display/nito.png");
		this.equipment = new RegularItem(this.equipment, "Base Equipment", new Stats(170,170,40,50,1));
		this.addItem(new ChaosBlade()); 
		this.behaviour = new AgressiveStrategy();
	}
}
