package proj2;

import java.util.*;

public class Deck {

   java.util.LinkedList deck = new LinkedList();
   private int index;

	/*public Deck() {
		deck = new LinkedList();
	}*/
  /**
   * Creates an empty deck of cards.
   */
   public Deck() {
      //super();
      for(int i = 0; i < Card.suit.length; i++){
			for(int j = 0; j < Card.rank.length; j++){
				Card card = new Card(Card.suit[i],Card.rank[j]);
				addCard(card);
			}
		}
	  shuffle();

   }
   
   /**
   * Enables you to get the card at the top of the deck
   * without removing the card from it.
   */
	public Card peek()
	{
		if(deck.size() == 0)
			return null;
		else
			return (Card)deck.getLast();
	}

   /**
   * Adds the card passed as parameter to deck.
   */
   public void addCard( Card card ) {
      deck.add( card );
   }

   /**
   * Returns the current size of the deck.
   */
   public int getSizeOfDeck() {
      return deck.size();
   }

   /**
   * Removes the top card from deck.
   */
   public Card dealCard() {
   
	 if ( deck.size() == 0 )
         return null;
      else
       
		return (Card) deck.removeFirst();
   }
   
   /**
   * Removes the last card.
   */
   public Card removeCard() {
 
	if (deck.size() == 0)
         return null;
      else{
		
         return (Card) deck.removeLast( );
	}
   }


  /**
   * Shuffles the cards present in the deck.
   */
   public void shuffle() {
      Collections.shuffle( deck );
   }


  /**
   * Looks for an empty deck.
   * @return <code>true</code> if there are no cards left to be dealt from the deck.
   */
   public boolean isEmpty() {
		return deck.size() == 0;
   }



  /**
   * Restores the deck to "full deck" status.
   */
  public void restoreDeck() {
	//not sure if kosher
      deck.removeAll(deck);
   }
   

}
