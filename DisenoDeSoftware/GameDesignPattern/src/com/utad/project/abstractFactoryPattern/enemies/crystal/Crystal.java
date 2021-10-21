package com.utad.project.abstractFactoryPattern.enemies.crystal;
import com.utad.project.base.Enemy;
import com.utad.project.base.Stats;

public class Crystal extends Enemy{
	//Estadisticas Base de los Crystal, las cuales son multiplicadas posteriormente por el factor de complejidad del mundo en el que son creados.
	protected final Stats crystalBaseStats = new Stats(30, 30, 80, 15, 1);
	
	public Crystal(String enemyName) {
		super(enemyName,"src/com/utad/project/display/crystal.png");
	}
}
