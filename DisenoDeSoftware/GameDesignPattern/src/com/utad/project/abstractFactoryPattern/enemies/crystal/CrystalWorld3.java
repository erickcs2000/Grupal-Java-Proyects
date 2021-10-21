package com.utad.project.abstractFactoryPattern.enemies.crystal;
import com.utad.project.abstractFactoryPattern.*;
import com.utad.project.decoratorPattern.*;

public class CrystalWorld3 extends Crystal{

	public CrystalWorld3(int id) {
		super("Crystal Knight Nº"+id);
		//Estadisticas basicas aplicadas al nivel
		this.equipment = new RegularItem(this.equipment, "Base Equipment", levelStats(crystalBaseStats, World.WORLD3));
	}
}
