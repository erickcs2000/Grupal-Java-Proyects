package com.utad.project.singletonPattern;

import java.util.ArrayList;
import java.util.List;

import com.utad.project.base.*;
import com.utad.project.base.Character;

public class CombatManager {
	//Algoritmo para ordenar a los personajes de la lista por nivel de velocidad
	public List<Character> orderBySpeed(List<Character> characters) {
		List<Character> aux = new ArrayList<Character>();
		Character max = null;
		while(!characters.isEmpty()) {
			max = characters.get(0);
			for(int i = 0; i < characters.size(); i++) {
				if(characters.get(i).getEquipment().getSpeed() > max.getEquipment().getSpeed()) {
					max = characters.get(i);
				}
			}
			aux.add(max);
			characters.remove(max);
		}
		return aux;
		
	}
	
	public void combat(List<Character> characters, List<Action> actions) {
		//Calcular las estadisticas modificadas este turno
		for(int i = 0; i < characters.size();i++) {
			characters.get(i).applyStats(characters.get(i).modifyStats()); 
		}
		
		//Pedir las acciones a cada personaje y almacenarlas en el buffer para tratarlas
		for(int i = 0; i < characters.size();i++) {
			if(characters.get(i) instanceof Enemy) {
				((Enemy) characters.get(i)).changeStrategy();
			}
			characters.get(i).decision(); 
		}
		
		//Aplicar los efectos del estado de cada personaje a las acciones que ha lanzado
		for(int i = 0; i < actions.size(); i++) {
			if(actions.get(i).getUser()!=null) { //Eventos sin usuario directo (ej: envenenamiento)
				actions.get(i).getUser().StatusEffect(actions.get(i));
			}
			
		}
		
		//Resolver por orden
		while(actions.size()>0) {
		
		//El actor es el sistema (user null) o el actor concreto sigue vivo
		if(actions.get(0).getUser() == null || actions.get(0).getUser().isAlive()) { 
			actions.get(0).getTarget().applyStats(actions.get(0).getVariation()); //Ejecutar la accion
		}
		
		actions.remove(0); //Eliminar una vez ejecutada
			
		}
	}
}


