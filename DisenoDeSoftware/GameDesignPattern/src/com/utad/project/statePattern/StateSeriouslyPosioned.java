package com.utad.project.statePattern;

import com.utad.project.base.Action;
import com.utad.project.base.ActionType;
import com.utad.project.base.SkillType;
import com.utad.project.base.Stats;
import com.utad.project.singletonPattern.GameManager;

public class StateSeriouslyPosioned extends AbstractState{

	public StateSeriouslyPosioned() {}
	public StateSeriouslyPosioned(State state) {
		super(state,"Seriously Poisoned");
	}
	public void process() {
		if(suggestion == States.STANDARD) {
			standard();
		}else if(suggestion == States.POISONED){
			this.state.setTurns(this.state.getTurns()+1);
		}else if(this.state.getTurns() <= 0) {
			poisoned();
		}
		suggestion = null;
		
	}
	
	public Action effect(Action action) {
		//Nueva accion que resta vida al usuario
		GameManager.getManager().getActions().add(new Action(new Stats(-(int)(action.getTarget().getEquipment().getMaxLife()*0.1),0,0,0,0), 
				ActionType.NEUTRAL,SkillType.MAGIC,null,action.getUser()));//El usuario es null -> accion del sistema
		GameManager.getManager().informPlayer(action.getUser().getName()+" is damaged by poison");
		this.state.setTurns(this.state.getTurns()-1);
		return action;
	}
	
	protected void standard() {
		this.state.setState(this.state.getPossibleState(States.STANDARD));
	}
	protected void poisoned() {
		this.state.setState(this.state.getPossibleState(States.POISONED));
		this.state.setTurns(1);
	}

}
