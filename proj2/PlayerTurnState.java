package proj2;

public class PlayerTurnState implements State{
	
	/**
	* Changes the state to player turn state.
	*/
	public void doAction(Context context) {
		System.out.println("Player 1");
		context.setState(this);
	}
	
	/**
	* Returns the string Player Turn State to compare it in the
	* action listener.
	*/
	public String toString(){
		return "Player Turn State";
	}
}