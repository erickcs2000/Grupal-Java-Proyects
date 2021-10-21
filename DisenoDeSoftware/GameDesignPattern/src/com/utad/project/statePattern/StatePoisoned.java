package com.utad.project.statePattern;

import com.utad.project.base.Action;
import com.utad.project.base.ActionType;
import com.utad.project.base.SkillType;
import com.utad.project.base.Stats;
import com.utad.project.singletonPattern.GameManager;

public class StatePoisoned extends AbstractState{

	
	public StatePoisoned() {}
	public StatePoisoned(State state) {
		super(state,"Poisoned");
	}
	
	public void process() {
		if(suggestion == States.STANDARD || this.state.getTurns() <= 0) {
			standard();
		}else if(suggestion == States.POISONED) {
			seriouslyPosioned();
		}
		suggestion = null;
	}
	
	public Action effect(Action action) {
		//Nueva accion que afecta al usuario restandole vida
		GameManager.getManager().getActions().add(new Action(new Stats(-2,0,0,0,0), 
				ActionType.NEUTRAL,SkillType.MAGIC, null,action.getUser())); //El usuario es null -> accion del sistema
		GameManager.getManager().informPlayer(action.getUser().getName()+" is damaged by poison");
		this.state.setTurns(this.state.getTurns()-1);
		return action;
	}

	protected void standard() {
		this.state.setState(this.state.getPossibleState(States.STANDARD));
	}
	protected void seriouslyPosioned() {
		this.state.setState(this.state.getPossibleState(States.SERIOUSLYPOISONED));
		this.state.setTurns(this.state.getTurns()+1);
		
	}
	
}
