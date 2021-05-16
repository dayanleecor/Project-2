package proj2;

public class ComputerTurnState implements State{
	
	/**
	* Changes the state to computer turn state.
	*/
	public void doAction(Context context) {
		System.out.println("Player 2");
		context.setState(this);
	}
	
	/**
	* Returns the string Computer Turn State to compare it in the
	* action listener.
	*/
	public String toString(){
		return "Computer Turn State";
	}
}