package proj2;

public class GameOverState implements State{

	/**
	* Changes the state to computer turn state.
	*/
	public void doAction(Context context) {
		System.out.println("Game over state");
		context.setState(this);
	}
	
	/**
	* Returns the string Game Over State to compare it in the
	* action listener.
	*/
	public String toString(){
		return "Game Over State";
	}
	
}