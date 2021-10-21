package com.utad.project.abstractFactoryPattern.enemies.giant;
import com.utad.project.abstractFactoryPattern.*;
import com.utad.project.decoratorPattern.*;

public class GiantWorld4 extends Giant{

	public GiantWorld4(int id) {
		super("Giant Nº"+id);
		//Estadisticas basicas aplicadas al nivel
		this.equipment = new RegularItem(this.equipment, "Base Equipment", levelStats(giantBaseStats, World.WORLD4));
	}
}
