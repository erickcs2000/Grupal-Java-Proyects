package com.utad.project.abstractFactoryPattern.enemies.capraDemon;
import com.utad.project.base.Enemy;
import com.utad.project.base.Stats;

public abstract class CapraDemon extends Enemy{
	//Estadisticas Base de los Capra Demon, las cuales son multiplicadas posteriormente por el factor de complejidad del mundo en el que son creados.
	protected final Stats capraDemonBaseStats = new Stats(50, 50, 20, 20, 1);
	
	public CapraDemon(String enemyName) {
		super(enemyName,"src/com/utad/project/display/caprademon.png");
	}
}
