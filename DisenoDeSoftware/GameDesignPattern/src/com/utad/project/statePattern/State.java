package com.utad.project.statePattern;

import java.util.ArrayList;
import java.util.List;

import com.utad.project.base.*;

public class State {//Contexto del patron de estados
	private CharacterState state; //estado concreto

	private List<CharacterState> possibleStates = new ArrayList<CharacterState>(); //Posibles estados

	private int turns;
	
	public void process() {
		this.state.process();
	}
	
	public State() { //inicializacion de los posibles estados coincidiendo con el orden del enumerado States
		this.state = new StateStandard(this);
		this.possibleStates.add(state);
		this.possibleStates.add(new StateConfused(this));
		this.possibleStates.add(new StateFurious(this));
		this.possibleStates.add(new StateParalyzed(this));
		this.possibleStates.add(new StatePoisoned(this));
		this.possibleStates.add(new StateSeriouslyPosioned(this));
		this.possibleStates.add(new StateSeriouslyParalyzed(this));
		this.turns = 0;
	}
	
	public CharacterState getState() {
		return state;
	}

	public void setState(CharacterState state) {
		this.state = state;
		this.turns = 2;
	}
	
	//Obtencion del estado deseado sabiendo solo el tipo enumerado al que se quiere cambiar
	public CharacterState getPossibleState(States desired) {
		return possibleStates.get(desired.ordinal());
	}
	public String getStateName() {
		return state.getStateName();
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turnos) {
		this.turns = turnos;
	}
	
	public Action effect(Action action) {
		return state.effect(action);
	}
	
	public void setSuggestion(States suggestion) {
		state.setSuggestion(suggestion);
	}

	
}
