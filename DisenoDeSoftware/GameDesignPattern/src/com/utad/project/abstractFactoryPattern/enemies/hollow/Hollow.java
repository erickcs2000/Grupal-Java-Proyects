package com.utad.project.abstractFactoryPattern.enemies.hollow;
import com.utad.project.base.Enemy;
import com.utad.project.base.Stats;

public abstract class Hollow extends Enemy{
	//Estadisticas Base de los Hollow, las cuales son multiplicadas posteriormente por el factor de complejidad del mundo en el que son creados.
	protected final Stats hollowBaseStats = new Stats(60, 60, 30, 30, 2);
	
	public Hollow(String enemyName) {
		super(enemyName,"src/com/utad/project/display/hollow.png");
	}
}
