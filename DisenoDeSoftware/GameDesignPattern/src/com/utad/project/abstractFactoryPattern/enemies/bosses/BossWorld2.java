package com.utad.project.abstractFactoryPattern.enemies.bosses;
import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.RegularItem;
import com.utad.project.decoratorPattern.items.bossWeapons.ArtoriasSword;
import com.utad.project.strategyPattern.bossStrategies.MediumStrategy;

public class BossWorld2 extends Boss{
			
	public BossWorld2() {
		super("Great Grey Wolf Sif", "src/com/utad/project/display/sif.png");
		this.equipment = new RegularItem(this.equipment, "Base Equipment", new Stats(80,80,30,20,3));	
		this.addItem(new ArtoriasSword());
		this.behaviour = new MediumStrategy();
	}
}
