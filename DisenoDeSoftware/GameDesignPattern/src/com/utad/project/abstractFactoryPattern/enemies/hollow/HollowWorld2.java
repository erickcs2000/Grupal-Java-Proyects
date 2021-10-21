package com.utad.project.abstractFactoryPattern.enemies.hollow;
import com.utad.project.abstractFactoryPattern.*;
import com.utad.project.decoratorPattern.*;

public class HollowWorld2 extends Hollow{

	public HollowWorld2(int id) {
		super("Hollow Knight Nº"+id);
		//Estadisticas basicas aplicadas al nivel
		this.equipment = new RegularItem(this.equipment, "Base Equipment", levelStats(hollowBaseStats, World.WORLD2));
	}
}
