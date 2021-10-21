package com.utad.project.abstractFactoryPattern.enemies.skeleton;
import com.utad.project.abstractFactoryPattern.*;
import com.utad.project.decoratorPattern.*;

public class SkeletonWorld3 extends Skeleton{

	public SkeletonWorld3(int id) {
		super("Wheel Skull Nº"+id);
		//Estadisticas basicas aplicadas al nivel
		this.equipment = new RegularItem(this.equipment, "Base Equipment", levelStats(skeletonBaseStats, World.WORLD2));
	}
}
