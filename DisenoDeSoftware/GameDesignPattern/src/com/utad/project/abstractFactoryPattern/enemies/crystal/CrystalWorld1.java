package com.utad.project.abstractFactoryPattern.enemies.crystal;
import com.utad.project.abstractFactoryPattern.*;
import com.utad.project.decoratorPattern.*;

public class CrystalWorld1 extends Crystal{
	
	public CrystalWorld1(int id) {
		super("Crystal Nº"+id);
		//Estadisticas basicas aplicadas al nivel
		this.equipment = new RegularItem(this.equipment, "Base Equipment", levelStats(crystalBaseStats, World.WORLD1));
	
	}
}
