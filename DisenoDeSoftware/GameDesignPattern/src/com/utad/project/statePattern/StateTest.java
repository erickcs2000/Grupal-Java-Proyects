package com.utad.project.statePattern;

import com.utad.project.base.*;

//Esta clase se utiliza exclusivamente para hacer pruebas con el patron state
//No forma parte de la solucion presentada, pero se deja constancia y posibilidad en la correcion
//De probar cada uno de los metodos del patron
public class StateTest {
	public static void main(String argv[]) {
		State state = new State();
		System.out.println("Estado inicial "+state.getState().getStateName());
		
		state.setSuggestion(States.CONFUSED);
		state.process();
		
		System.out.println("Estado actual "+state.getState().getStateName());
		
		state.setSuggestion(States.PARALYZED); 
		state.process(); //No se puede pasar directamente de confundido a paralizado
		
		System.out.println("Estado actual "+state.getState().getStateName());
		
		state.setSuggestion(States.STANDARD);
		state.process();
		
		System.out.println("Estado actual "+state.getState().getStateName());
		
		state.setSuggestion(States.POISONED);
		state.process();
		
		System.out.println("Estado actual "+state.getState().getStateName());
		
		state.setSuggestion(States.POISONED);
		state.process(); //Envenear de nuevo pasa a seriamente envenado
		
		System.out.println("Estado actual "+state.getState().getStateName());
	}
}
