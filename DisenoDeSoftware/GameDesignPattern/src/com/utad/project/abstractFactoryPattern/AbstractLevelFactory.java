package com.utad.project.abstractFactoryPattern;

import com.utad.project.abstractFactoryPattern.enemies.bosses.Boss;
import com.utad.project.base.Enemy;
import com.utad.project.decoratorPattern.Item;
import com.utad.project.decoratorPattern.ItemDecorator;

public interface AbstractLevelFactory {	//Genera instancias concretas mediante distintos algoritmos para cada nivel
	//Genera un enemigo concreto de cualquier clase menos las que heredan de Boss
	public Enemy generateEnemy();
	//Genera items de acuerdo con la temática y la dificultad de los niveles
	public ItemDecorator generateItem();
	//Genera instancias de clases que heredan de Boss
	public Boss generateBoss();
	
}
