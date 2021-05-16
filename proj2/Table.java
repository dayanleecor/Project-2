package proj2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.ListSelectionListener;
/**
*	This GUI assumes that you are using a 52 card deck and that you have 13 sets in the deck.
*	The GUI is simulating a playing table
	@author Patti Ordonez
*/
public class Table extends JFrame implements ActionListener
{
	final static int numDealtCards = 9;
	JPanel player1;
	JPanel player2;
	JPanel deckPiles;
	JLabel deck;
	JLabel stack;
	JList p1HandPile;
	JList p2HandPile;
	Deck cardDeck;
	Pile stackPile;

	SetPanel [] setPanels = new SetPanel[13];
	JLabel topOfStack;
	JLabel deckPile;
	JButton p1Stack;
	JButton p2Stack;

	JButton p1Deck;
	JButton p2Deck;

	JButton p1Lay;
	JButton p2Lay;

	JButton p1LayOnStack;
	JButton p2LayOnStack;

	Hand p1Hand;
	Hand p2Hand;
	Context context = new Context();
	DrawState drawState = new DrawState();
	DiscardState discardState = new DiscardState();
	
	Context turnContext = new Context();
	PlayerTurnState playerTurnState = new PlayerTurnState();
	ComputerTurnState computerTurnState = new ComputerTurnState();
	GameOverState gameOverState = new GameOverState();
	
	boolean draw, discard;
	int computerSrc;
	
	private void deal(Card [] cards, int playerNum)
	{
		Card c;
		System.out.print("Initial Player " + playerNum + ":");

		for(int i = 0; i < cards.length; i ++) {
			c = (Card)cardDeck.dealCard();
			cards[i] = c;
			System.out.print(c);
			if(i != cards.length - 1) {
				System.out.print(",");
			}
		}	
	}
	
	public Table()
	{
		super("The Card Game of the Century");

		setLayout(new BorderLayout());
		setSize(1200,700);
		getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		cardDeck = new Deck();

		stackPile = new Pile();

		JPanel top = new JPanel();
		JPanel top2 = new JPanel();

		for (int i = 0; i < Card.rank.length;i++)
			setPanels[i] = new SetPanel(Card.getRankIndex(Card.rank[i]));

		top.add(setPanels[0]);
		top.add(setPanels[1]);
		top.add(setPanels[2]);
		top.add(setPanels[3]);
		

		player1 = new JPanel();
		
		JPanel pl2 = new JPanel();

		player1.add(top);

		add(player1, BorderLayout.NORTH);
		
		JPanel bottom = new JPanel();
		JPanel bottom2 = new JPanel();


		bottom.add(setPanels[4]);
		bottom.add(setPanels[5]);
		bottom.add(setPanels[6]);
		bottom.add(setPanels[7]);
		bottom.add(setPanels[8]);

		player2 = new JPanel();

		player2.add(bottom);
		add(player2, BorderLayout.SOUTH);


		JPanel middle = new JPanel(new GridLayout(1,3));

		Card [] cardsPlayer1 = new Card[numDealtCards];
		deal(cardsPlayer1,1);
		p1Hand = new Hand();
		for(int i = 0; i < cardsPlayer1.length; i++) {
			p1Hand.addCard(cardsPlayer1[i]);
		}
		p1Hand.sort();
		p1HandPile = new JList(p1Hand.asVector());
		
		System.out.print("\n");

		p1Stack = new JButton("Stack");
		p1Stack.addActionListener(this);
		p1Deck = new JButton("Deck ");
		p1Deck.addActionListener(this);
		p1Lay = new JButton("Lay Set");
		p1Lay.addActionListener(this);
		p1LayOnStack = new JButton("Lay on Stack");
		p1LayOnStack.addActionListener(this);

		middle.add(new HandPanel("Player 1", p1HandPile, p1Stack, p1Deck, p1Lay, p1LayOnStack));

		deckPiles = new JPanel();
		deckPiles.setLayout(new BoxLayout(deckPiles, BoxLayout.Y_AXIS));
		deckPiles.add(Box.createGlue());
		JPanel left = new JPanel();
		left.setAlignmentY(Component.CENTER_ALIGNMENT);


		stack = new JLabel("Stack");
		stack.setAlignmentY(Component.CENTER_ALIGNMENT);

		left.add(stack);
		topOfStack = new JLabel();
		topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));
		topOfStack.setAlignmentY(Component.CENTER_ALIGNMENT);
		left.add(topOfStack);
		deckPiles.add(left);
		deckPiles.add(Box.createGlue());

		JPanel right = new JPanel();
		right.setAlignmentY(Component.CENTER_ALIGNMENT);

		deck = new JLabel("Deck");

		deck.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deck);
		deckPile = new JLabel();
		deckPile.setIcon(new ImageIcon(Card.directory + "b.gif"));
		deckPile.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deckPile);
		deckPiles.add(right);
		deckPiles.add(Box.createGlue());
		middle.add(deckPiles);

		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2Lay = new JButton("Lay  ");
		p2Lay.addActionListener(this);
		p2LayOnStack = new JButton("LayOnStack");
		p2LayOnStack.addActionListener(this);

		Card [] cardsPlayer2 = new Card[numDealtCards];
		deal(cardsPlayer2,2);
		p2Hand = new Hand();
		for(int i = 0; i < cardsPlayer2.length; i++) {
			p2Hand.addCard(cardsPlayer2[i]);
		}	
		p2Hand.sort();
		p2HandPile = new JList(p2Hand.asVector());
		
		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2Lay = new JButton("Lay Set");
		p2Lay.addActionListener(this);
		p2LayOnStack = new JButton("Lay on Stack");
		p2LayOnStack.addActionListener(this);

		
		System.out.print("\n");

		middle.add(new HandPanel("Player 2", p2HandPile, p2Stack, p2Deck, p2Lay, p2LayOnStack));

		add(middle, BorderLayout.CENTER);

		JPanel leftBorder = new JPanel(new GridLayout(2,1));

		setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
		setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
		leftBorder.add(setPanels[9]);
		leftBorder.add(setPanels[10]);
		add(leftBorder, BorderLayout.WEST);

		JPanel rightBorder = new JPanel(new GridLayout(2,1));

		setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
		setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
		rightBorder.add(setPanels[11]);
		rightBorder.add(setPanels[12]);
		add(rightBorder, BorderLayout.EAST);
		
		Card firstCard = (Card)cardDeck.removeCard();
		topOfStack.setIcon(firstCard.getCardImage());
		
		drawState.doAction(context);
		playerTurnState.doAction(turnContext);
		draw = false;
		discard = false;
	}

	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		
		if(cardDeck.isEmpty() == true || p1Hand.isEmpty() == true || p2Hand.isEmpty() == true) {
			gameOverState.doAction(turnContext);		
			int result = p1Hand.compareTo(p2Hand);
		}
		
		if(turnContext.getState().toString() != "Game Over State") {
		if(turnContext.getState().toString() == "Player Turn State") {
		if(p1Deck == src && context.getState().toString() == "Draw State"){

			Card card = cardDeck.dealCard();

			if (card != null){
				p1Hand.addCard(card);
				p1Hand.sort();
				p1HandPile.setListData(p1Hand.asVector());
				drawState.doAction(context, card);
				discardState.doAction(context);
				draw = true;
			}
			if(cardDeck.getSizeOfDeck() == 0)
				deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));

		}
		
		if(p1Stack == src && context.getState().toString() == "Draw State"){

			Card card = stackPile.removeCardStack();

			if(card != null){
				Card topCard = stackPile.peekCard();
				if (topCard != null)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));

				p1Hand.addCard(card);
				p1Hand.sort();
				p1HandPile.setListData(p1Hand.asVector());
				drawState.doAction(context, card);
				discardState.doAction(context);
				draw = true;
			}

		}

		if(p1Lay == src && context.getState().toString() == "Discard State"){
			Object [] cards = p1HandPile.getSelectedValues();
	
			if (cards != null && cards.length >= 3) {
				Card firstCard = (Card)cards[0];
				char firstCardRank = firstCard.getRank();
				Set set = new Set(firstCardRank);
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					if(firstCardRank != card.getRank()) {
						set.deleteSet();
						break;
					}
					set.addCard(card);	
				}			
				int i = 0;
				System.out.print("Discarded: ");
				while (i < set.getNumberOfCards()) {
					layCard(set.getCard(i));
					p1Hand.removeCard(set.getCard(i));
					p1Hand.sort();
					p1HandPile.setListData(p1Hand.asVector());
					i++;
				}
			}		
		}

		else if(p1LayOnStack == src && context.getState().toString() == "Discard State"){
			int [] num  = p1HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p1HandPile.getSelectedValue();
				if (obj != null)
				{
					p1Hand.removeCard((Card)obj);
					p1Hand.sort();
					p1HandPile.setListData(p1Hand.asVector());
					Card card = (Card)obj;
					stackPile.addCardStack(card);
					topOfStack.setIcon(card.getCardImage());
					discardState.doAction(context, (Card)obj);
					drawState.doAction(context);
					p1Hand.sort();
					System.out.println("Hand now: " + p1Hand.getCurrentHand());
					discard = true;
				}
			}
		}
		if(draw == true && discard == true) {
			computerTurnState.doAction(turnContext);
			draw = false;
			discard = false;
		}
		}
		//--------------------------------------------------------------------------
		
		if(turnContext.getState().toString() == "Computer Turn State") {
		
		computerSrc = p1Hand.playDraw();
		
		if(computerSrc == 1 && context.getState().toString() == "Draw State"){

			Card card = cardDeck.dealCard();

			if (card != null){
					p2Hand.addCard(card);
					p2Hand.sort();
					p2HandPile.setListData(p2Hand.asVector());
					drawState.doAction(context, card);
					discardState.doAction(context);
					draw = true;
					computerSrc = 4;
			}
			if(cardDeck.getSizeOfDeck() == 0)
				deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));

		}
		
		if(computerSrc == 2 && context.getState().toString() == "Draw State"){

			Card card = stackPile.removeCardStack();

			if(card != null){
				Card topCard = stackPile.peekCard();
				if (topCard != null)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));

					p2Hand.addCard(card);
					p2Hand.sort();
					p2HandPile.setListData(p2Hand.asVector());
					drawState.doAction(context, card);
					discardState.doAction(context);
					draw = true;
					computerSrc = 4;
			}

		}

		if(computerSrc == 3 && context.getState().toString() == "Discard State"){
			Object [] cards = p2HandPile.getSelectedValues();
			if (cards != null)
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					layCard(card);
					p2Hand.removeCard(card);
					p2Hand.sort();
					p2HandPile.setListData(p2Hand.asVector());
				}
		}

		if(computerSrc == 4 && context.getState().toString() == "Discard State"){
			int index = p2Hand.makeIndex();
			Card removedCard = p2Hand.getCard(index);
			p2Hand.removeCard(removedCard);
			p2Hand.sort();
			p2HandPile.setListData(p2Hand.asVector());
			stackPile.addCardStack(removedCard);
			topOfStack.setIcon(removedCard.getCardImage());
			discardState.doAction(context, removedCard);
			drawState.doAction(context);
			discard = true;
			System.out.println("Hand now: " + p2Hand.getCurrentHand());
		}
		
		if(draw == true && discard == true) {
			playerTurnState.doAction(turnContext);
			draw = false;
			discard = false;
		}
		
		}
		}
	}

	void layCard(Card card)
	{
		char rank = card.getRank();
		char suit = card.getSuit();
		int suitIndex =  Card.getSuitIndex(suit);
		int rankIndex =  Card.getRankIndex(rank);
		System.out.print(card + " ");
		setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
	}

}

class HandPanel extends JPanel
{

	public HandPanel(String name, JList hand, JButton stack, JButton deck, JButton lay, JButton layOnStack)
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(stack);
		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(deck);
		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);
		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
	}

}

class SetPanel extends JPanel
{
	private Set data;
	JButton [] array = new JButton[4];

	public SetPanel(int index)
	{
		super();
		data = new Set(Card.rank[index]);

		for(int i = 0; i < array.length; i++){
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}

}
