package com.utad.project.abstractFactoryPattern.enemies.skeleton;
import com.utad.project.base.Enemy;
import com.utad.project.base.Stats;

public abstract class Skeleton extends Enemy{
	//Estadisticas Base de los Skeleton, las cuales son multiplicadas posteriormente por el factor de complejidad del mundo en el que son creados.
	protected final Stats skeletonBaseStats = new Stats(50, 50, 40, 10, 4);
	
	public Skeleton(String enemyName) {
		super(enemyName,"src/com/utad/project/display/skeleton.png");
	}
}
