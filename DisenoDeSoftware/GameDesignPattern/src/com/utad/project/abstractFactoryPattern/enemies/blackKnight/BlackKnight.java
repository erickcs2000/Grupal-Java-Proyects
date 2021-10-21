package com.utad.project.abstractFactoryPattern.enemies.blackKnight;
import com.utad.project.base.Enemy;
import com.utad.project.base.Stats;

public abstract class BlackKnight extends Enemy{
	//Estadisticas Base de los Black Knight, las cuales son multiplicadas posteriormente por el factor de complejidad del mundo en el que son creados.
	protected static final Stats blackKnightBaseStats = new Stats(60, 60, 20, 20, 2);
	
	public BlackKnight(String enemyName) {
		super(enemyName,"src/com/utad/project/display/blackknight.png");
	}
}
