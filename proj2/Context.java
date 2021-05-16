package proj2;

public class Context {

	   private State state;
	   
	   public Context(){
	      state = null;
	   }

		/**
		* Sets a state.
		*/
	   public void setState(State state){
	      this.state = state;		
	   }
		
	   /**
	   * Returns the current state.
	   */	
	   public State getState(){
	      return state;
	   }
	}