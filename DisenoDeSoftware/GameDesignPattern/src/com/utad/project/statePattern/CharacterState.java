package com.utad.project.statePattern;

import com.utad.project.base.Action;

public interface CharacterState {
	public void process(); //Logica de cambio de estado
	public Action effect(Action action); //Modificacion (o sustitucion) de la accion
	public void setSuggestion(States suggestion); //Cambio en el siguiente estado
	public States getSuggestion(); //Obtener cual es el posible siguiente estado
	public String getStateName(); //Nombre del estado
}
