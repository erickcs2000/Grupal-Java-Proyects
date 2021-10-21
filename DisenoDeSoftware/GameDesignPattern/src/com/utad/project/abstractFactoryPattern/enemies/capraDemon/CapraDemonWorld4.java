package com.utad.project.abstractFactoryPattern.enemies.capraDemon;
import com.utad.project.abstractFactoryPattern.*;
import com.utad.project.decoratorPattern.*;

public class CapraDemonWorld4 extends CapraDemon{

	public CapraDemonWorld4(int id) {
		super("Capra Demon Nº"+id);
		//Estadisticas basicas aplicadas al nivel
		this.equipment = new RegularItem(this.equipment, "Base Equipment", levelStats(capraDemonBaseStats, World.WORLD4));	
	}
}
