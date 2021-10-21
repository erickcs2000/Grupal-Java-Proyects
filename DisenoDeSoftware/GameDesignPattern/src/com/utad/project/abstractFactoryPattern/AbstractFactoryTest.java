package com.utad.project.abstractFactoryPattern;

import com.utad.project.abstractFactoryPattern.enemyFactories.*;
import com.utad.project.base.*;

//Esta clase se utiliza exclusivamente para hacer pruebas con el patron abstract factory
//No forma parte de la solucion presentada, pero se deja constancia y posibilidad en la correcion
//De probar cada uno de los metodos del patron
public class AbstractFactoryTest {

	public static void main(String[] args) {
		AbstractLevelFactory factory = new LevelFactoryWorld1();
		Enemy enemy;
		
		System.out.println("Nivel 1");
		for(int i = 0; i < 5; i++) { //Observar como algunas estadisticas cambian entre objetos de la misma clase
			enemy =factory.generateEnemy();
			System.out.println(enemy); 
		}
		
		System.out.println(factory.generateItem());
		System.out.println(factory.generateItem());
		System.out.println(factory.generateBoss());
		
		System.out.println("\n\nNivel 3");
		factory = new LevelFactoryWorld3();
		
		for(int i = 0; i < 5; i++) { //Observar como algunas estadisticas cambian entre objetos de la misma clase
			enemy =factory.generateEnemy();
			System.out.println(enemy); 
		}
		
		System.out.println(factory.generateItem());
		System.out.println(factory.generateItem());
		System.out.println(factory.generateBoss());
		
	}
}
