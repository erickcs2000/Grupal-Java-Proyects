package com.utad.project.abstractFactoryPattern;

import java.util.Random;

import com.utad.project.base.Enemy;

public abstract class FactoryTemplate implements AbstractLevelFactory{
	protected Random rand = new Random();
	
	//Método para generar un enemigo completo con equipamiento y comportamiento.
	public final Enemy generateEnemy() {
		Enemy enemy = createEnemy();
		decorateEnemy(enemy);
		finishEnemy(enemy);
		
		return enemy;
	}
	
	//Crear al enemigo 
	protected abstract Enemy createEnemy();
	//Decorarlo con items del nivel
	protected abstract void decorateEnemy(Enemy enemy);
	//Cambiar el estado / darle un BehaviourStrategy/ etc.
	protected abstract void finishEnemy(Enemy enemy);
}
