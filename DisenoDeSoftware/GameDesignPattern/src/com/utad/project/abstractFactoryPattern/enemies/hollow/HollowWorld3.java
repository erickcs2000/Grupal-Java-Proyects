package com.utad.project.abstractFactoryPattern.enemies.hollow;
import com.utad.project.abstractFactoryPattern.*;
import com.utad.project.decoratorPattern.*;

public class HollowWorld3 extends Hollow{
	
	public HollowWorld3(int id) {
		super("Scared Hollow Nº"+id);
		//Estadisticas basicas aplicadas al nivel
		this.equipment = new RegularItem(this.equipment, "Base Equipment", levelStats(hollowBaseStats, World.WORLD3));	
	}
}
