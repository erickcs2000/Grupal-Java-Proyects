package com.utad.project.decoratorPattern;

import java.util.ArrayList;
import java.util.List;

import com.utad.project.base.Stats;
import com.utad.project.decoratorPattern.items.*;

//Esta clase se utiliza exclusivamente para hacer pruebas con el patron decorador
//No forma parte de la solucion presentada, pero se deja constancia y posibilidad en la correcion
//De probar cada uno de los metodos del patron
public class DecoratorTest {

	public static void main(String[] args) {
		//Creamos un item y lo decoramos
		Item item = new Equipment();
		item =item.addItem(new Potion(2));
		item =item.addItem(new Thornmail());
		item =item.addItem(new RegularItem("Regular",new Stats(20,20,20,20,2)));
		item =item.addItem(new Potion(1)); //Debería añadirse al objeto Potion ya creado y aumentar su cantidad
		item =item.addItem(new Thornmail()); //Debería almacenarse como un objeto repetido
		item =item.addItem(new Bow());
		System.out.println("\nDescripcion");
		System.out.println(item.getDesc()); //Descripcion de la jerarquia en html
		System.out.println("\nActivas");
		List <ActiveItemDecorator> list = new ArrayList<ActiveItemDecorator>();
		list = item.areThereAnyActives(list); 
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("\nPasivas");
		List <PassiveItemDecorator> list2 = new ArrayList<PassiveItemDecorator>();
		list2 = item.areThereAnyPassives(list2); 
		for(int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i));
		}
		
		System.out.println("\nValores");
		System.out.println("Vida "+item.getLife()+"\nVida max "+item.getMaxLife()
			+"\nAtaque "+item.getAttack()+"\nDefensa "+item.getDefense()+"\nVelocidad "+item.getSpeed());
		
		item=item.deleteItem(new Bow()); //Eliminar el arco de la jerarquia
		System.out.println("\nDescripcion");
		System.out.println(item.getDesc()); //Descripcion de la jerarquia en html
		
		item.applyStats(new Stats(4,2,3,4,1));
		System.out.println("\nValores actualizados");
		System.out.println("Vida "+item.getLife()+"\nVida max "+item.getMaxLife()
			+"\nAtaque "+item.getAttack()+"\nDefensa "+item.getDefense()+"\nVelocidad "+item.getSpeed());
		
	}
}
