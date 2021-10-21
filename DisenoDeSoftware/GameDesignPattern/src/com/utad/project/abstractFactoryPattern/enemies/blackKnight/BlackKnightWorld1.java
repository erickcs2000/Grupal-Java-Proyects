package com.utad.project.abstractFactoryPattern.enemies.blackKnight;
import com.utad.project.abstractFactoryPattern.*;
import com.utad.project.decoratorPattern.*;

public class BlackKnightWorld1 extends BlackKnight{
	
	public BlackKnightWorld1(int id) {
		super("Knight Nº"+id);
		//Estadisticas basicas aplicadas al nivel
		this.equipment = new RegularItem(this.equipment, "Base Equipment", levelStats(blackKnightBaseStats, World.WORLD1));			
	}
}
