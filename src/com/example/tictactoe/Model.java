package com.example.tictactoe;

import android.util.Log;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import com.example.tictactoe.Model.BoxInfo;

public class Model extends Observable {

	class BoxInfo {

		int squareX;
		int squareY;
		int move;
		boolean taken;

		BoxInfo(int x, int y, int m, boolean b) {
			this.squareX = x;
			this.squareY = y;
			this.move = m;
			taken = b;
		}
	}
	private boolean gameStart = false;
	private boolean gameWin = false;
	private boolean gameTie = false;
	private int lastWin = 0;
	private int moveCount = 0;
	private int winXCount = 0;
	private int winOCount = 0;
	private int loseXCount = 0;
	private int loseOCount = 0;
	private int streakXCount = 0;
	private int streakOCount = 0;
	private int tieCount = 0;
	private int winningX;
	private int winningY;
	private int winningX2;
	private int winningY2;
	private int winningX3;
	private int winningY3;
	private String player1name ="";
	private String player2name ="";
	private boolean tmode = false;
	private boolean moveMade = false;
	private String player1move;
	private String player2move;
	private String moveMessage = "X's turn";
	private String message = "Make a move";
	private String pmessage = "Player 1: "+ player1name +" is " + player1move;
	private String pmessage2 = "Player 2: "+player2name +" is " + player2move ;
	private String txMessage = "Player 1: "+ player1name +"  Wins: 0  Losses: 0";
	private String toMessage = "Player 2: "+ player2name +"  Wins: 0  Losses: 0";
	private String tieMessage = "Ties: 0";
	public LinkedList<BoxInfo> boxInfo = new LinkedList<BoxInfo>();
	//public LinkedList<Integer> ySquare = new LinkedList();
	private int move = 0;

	Model() {
		setChanged();
	}
	public void initObservers() {
		setChanged();
		notifyObservers();
	}
	@Override
	public void addObserver(Observer observer) {
		Log.d("DEMO", "Model: Observer added");
		super.addObserver(observer);
	}

	@Override
	public void notifyObservers() {
		Log.d("DEMO", "Model: Observers notified");
		super.notifyObservers();
	}
	public void updatePlayerMessages(){
		pmessage = "Player 1: "+ player1name +" is " + player1move;
		pmessage2 = "Player 2: "+player2name +" is " + player2move ;
		txMessage = "Player 1: "+ player1name +"  Wins:  " + Integer.toString(winXCount) + "  Losses: " + Integer.toString(loseXCount);
		toMessage = "Player 2: "+ player2name +"  Wins:  " + Integer.toString(winOCount) + "  Losses: " + Integer.toString(loseOCount);
	}
	public void setP1m(String p){
		player1move = p;		
		updatePlayerMessages();
	}
	public void setP2m(String p){
		player2move = p;
		updatePlayerMessages();
	}
	public void setP1(String p){
		player1name = p;
		updatePlayerMessages();
	}
	public void setP2(String p){
		player2name = p;
		updatePlayerMessages();
	}
	public void setMoveMessage(){
		
	}
	public String getMoveMessage(){
		return moveMessage;
	}
	public String getPmessage(){
		return pmessage;
	}
	public String getPmessage2(){
		return pmessage2;
	}
	public int getWinX() {
		return winningX;
	}

	public int getWinY() {
		return winningY;
	}

	public int getWinX2() {
		return winningX2;
	}

	public int getWinY2() {
		return winningY2;
	}

	public int getWinX3() {
		return winningX3;
	}

	public int getWinY3() {
		return winningY3;
	}

	public boolean isGameWin() {
		return gameWin;
	}
	public boolean isGameTie() {
		return gameTie;
	}

	public void setTMode(boolean b) {
		tmode = b;
		setChanged();
		notifyObservers();
	}

	public boolean getTMode() {
		return tmode;
	}

	public void setMoveMade(boolean b) {
		moveMade = b;
	}

	public boolean getMoveMade() {
		return moveMade;
	}

	public void newGame() {
		while (!boxInfo.isEmpty()) {
			boxInfo.remove();

		}
		moveCount = 0;
		move = 0;
		lastWin = 0;
		winningX = -999;
		winningX2 = -999;
		winningY = -999;
		winningY2 = -999;
		moveMade = false;
		gameStart = false;
		gameWin = false;
		gameTie = false;
		//message = "Select which Player starts";
		setChanged();
		notifyObservers();

	}

	public void toggleTMode() {
		winXCount = 0;
		winOCount = 0;
		loseXCount = 0;
		loseOCount = 0;
		streakXCount = 0;
		streakOCount = 0;
		tieCount = 0;
		txMessage = "Player 1: "+ player1name +"  Wins: 0  Losses: 0";
		toMessage = "Player 2: "+ player2name +"  Wins: 0  Losses: 0";
		tieMessage = "Ties: 0";
		setChanged();
		notifyObservers();
	}

	public void startGame() {
		if (!gameStart) {
			message = "Make a move";
			setChanged();
			notifyObservers();
			gameStart = true;
		}
	}

	public boolean isStart() {
		return gameStart;
	}

	public void updateBox(int x, int y, boolean b) {
		setChanged();
		notifyObservers();

		boxInfo.add(new BoxInfo(x, y, move, b));
		move *= -1;
	}

	public void updateMove(int m) {
		move = m;
	}

	public int getMove() {
		return move;
	}

	public String getMessage() {
		return message;
	}

	public String getTxMessage() {
		return txMessage;
	}

	public String getToMessage() {
		return toMessage;
	}
	public String getTieMessage() {
		return tieMessage;
	}
	public void updateXWin(){
		if(player1move=="X"){
			if(lastWin==0 || lastWin ==1){
				streakXCount++;
			}
			streakOCount = 0;
			winXCount++;
			loseOCount++;
			lastWin = 1;
		}else{
			if(lastWin==0 || lastWin ==-1){
				streakOCount++;
			}
			streakXCount = 0;
			winOCount++;
			loseXCount++;
			lastWin = -1;
		}	
	}
	public void updateOWin(){
		if(player2move=="X"){
			if(lastWin==0 || lastWin ==1){
				streakXCount++;
			}
			streakOCount = 0;
			winXCount++;
			loseOCount++;
			lastWin = 1;
		}else{
			if(lastWin==0 || lastWin ==-1){
				streakOCount++;
			}
			streakXCount = 0;
			winOCount++;
			loseXCount++;
			lastWin = -1;
		}	
	}
	public boolean checkMove(int x, int y) {
		for (int i = 0; i < boxInfo.size(); i++) {
			if (boxInfo.get(i).squareX == x && boxInfo.get(i).squareY == y) {
				if (boxInfo.get(i).taken) {
					message = "Illegal Move";
					setChanged();
					notifyObservers();
					return false;
				}
			}
		}
		if(move == 1){
			moveMessage = "O's turn";
		}else if(move == -1){
			moveMessage = "X's turn";
		}
		return true;
	}
	public boolean checkHover(int x, int y) {
		
		for (int i = 0; i < boxInfo.size(); i++) {
			if (boxInfo.get(i).squareX == x && boxInfo.get(i).squareY == y) {
				if (boxInfo.get(i).taken) {
					//	                    setChanged();
					//	                    notifyObservers();
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkBoard() {
		for (int i = 0; i < boxInfo.size(); i++) {
			if (boxInfo.get(i).squareX == 0 && boxInfo.get(i).squareY == 0) {
				winningX = boxInfo.get(i).squareX;
				winningY = boxInfo.get(i).squareY;
				for (int j = 0; j < boxInfo.size(); j++) {
					if (boxInfo.get(j).squareX == 1 && boxInfo.get(j).squareY == 0) {
						if (boxInfo.get(i).move == boxInfo.get(j).move) {
							winningX2 = boxInfo.get(j).squareX;
							winningY2 = boxInfo.get(j).squareY;
							for (int k = 0; k < boxInfo.size(); k++) {
								if (boxInfo.get(k).squareX == 2 && boxInfo.get(k).squareY == 0) {
									if (boxInfo.get(i).move == boxInfo.get(k).move) {
										if (boxInfo.get(i).move == 1) {
											message = "X Wins!";
											updateXWin();
										} else {
											message = "O Wins!";
											updateOWin();
										}
										txMessage = "Player 1: "+ player1name +"  Wins:  " + Integer.toString(winXCount) + "  Losses: " + Integer.toString(loseXCount);
										toMessage = "Player 2: "+ player2name +"  Wins:  " + Integer.toString(winOCount) + "  Losses: " + Integer.toString(loseOCount);
										winningX3 = boxInfo.get(k).squareX;
										winningY3 = boxInfo.get(k).squareY;
										gameWin = true;
										setChanged();
										notifyObservers();
										return true;
									}
								}
							}
						}
					}
				}
			}
			if (boxInfo.get(i).squareX == 0 && boxInfo.get(i).squareY == 0) {
				for (int j = 0; j < boxInfo.size(); j++) {
					if (boxInfo.get(j).squareX == 0 && boxInfo.get(j).squareY == 1) {
						winningX = boxInfo.get(i).squareX;
						winningY = boxInfo.get(i).squareY;
						if (boxInfo.get(i).move == boxInfo.get(j).move) {
							winningX2 = boxInfo.get(j).squareX;
							winningY2 = boxInfo.get(j).squareY;
							for (int k = 0; k < boxInfo.size(); k++) {
								if (boxInfo.get(k).squareX == 0 && boxInfo.get(k).squareY == 2) {
									if (boxInfo.get(i).move == boxInfo.get(k).move) {
										if (boxInfo.get(i).move == 1) {
											message = "X Wins!";
											updateXWin();
										} else {
											message = "O Wins!";
											updateOWin();
										}
										txMessage = "Player 1: "+ player1name +"  Wins:  " + Integer.toString(winXCount) + "  Losses: " + Integer.toString(loseXCount);
										toMessage = "Player 2: "+ player2name +"  Wins:  " + Integer.toString(winOCount) + "  Losses: " + Integer.toString(loseOCount);
										winningX3 = boxInfo.get(k).squareX;
										winningY3 = boxInfo.get(k).squareY;
										gameWin = true;
										setChanged();
										notifyObservers();
										return true;
									}
								}
							}
						}
					}
				}
			}
			if (boxInfo.get(i).squareX == 0 && boxInfo.get(i).squareY == 0) {
				for (int j = 0; j < boxInfo.size(); j++) {
					if (boxInfo.get(j).squareX == 1 && boxInfo.get(j).squareY == 1) {
						winningX = boxInfo.get(i).squareX;
						winningY = boxInfo.get(i).squareY;
						if (boxInfo.get(i).move == boxInfo.get(j).move) {
							winningX2 = boxInfo.get(j).squareX;
							winningY2 = boxInfo.get(j).squareY;
							for (int k = 0; k < boxInfo.size(); k++) {
								if (boxInfo.get(k).squareX == 2 && boxInfo.get(k).squareY == 2) {
									if (boxInfo.get(i).move == boxInfo.get(k).move) {
										if (boxInfo.get(i).move == 1) {
											message = "X Wins!";
											updateXWin();
										} else {
											message = "O Wins!";
											updateOWin();
										}
										txMessage = "Player 1: "+ player1name +"  Wins:  " + Integer.toString(winXCount) + "  Losses: " + Integer.toString(loseXCount);
										toMessage = "Player 2: "+ player2name +"  Wins:  " + Integer.toString(winOCount) + "  Losses: " + Integer.toString(loseOCount);
										winningX3 = boxInfo.get(k).squareX;
										winningY3 = boxInfo.get(k).squareY;
										gameWin = true;
										setChanged();
										notifyObservers();
										return true;
									}
								}
							}
						}
					}
				}
			}
			if (boxInfo.get(i).squareX == 2 && boxInfo.get(i).squareY == 0) {
				for (int j = 0; j < boxInfo.size(); j++) {
					if (boxInfo.get(j).squareX == 2 && boxInfo.get(j).squareY == 1) {
						winningX = boxInfo.get(i).squareX;
						winningY = boxInfo.get(i).squareY;
						if (boxInfo.get(i).move == boxInfo.get(j).move) {
							winningX2 = boxInfo.get(j).squareX;
							winningY2 = boxInfo.get(j).squareY;
							for (int k = 0; k < boxInfo.size(); k++) {
								if (boxInfo.get(k).squareX == 2 && boxInfo.get(k).squareY == 2) {
									if (boxInfo.get(i).move == boxInfo.get(k).move) {
										if (boxInfo.get(i).move == 1) {
											message = "X Wins!";
											updateXWin();
										} else {
											message = "O Wins!";
											updateOWin();
										}
										txMessage = "Player 1: "+ player1name +"  Wins:  " + Integer.toString(winXCount) + "  Losses: " + Integer.toString(loseXCount);
										toMessage = "Player 2: "+ player2name +"  Wins:  " + Integer.toString(winOCount) + "  Losses: " + Integer.toString(loseOCount);
										winningX3 = boxInfo.get(k).squareX;
										winningY3 = boxInfo.get(k).squareY;
										gameWin = true;
										setChanged();
										notifyObservers();
										return true;
									}
								}
							}
						}
					}
				}
			}
			if (boxInfo.get(i).squareX == 2 && boxInfo.get(i).squareY == 2) {
				for (int j = 0; j < boxInfo.size(); j++) {
					if (boxInfo.get(j).squareX == 1 && boxInfo.get(j).squareY == 2) {
						winningX = boxInfo.get(i).squareX;
						winningY = boxInfo.get(i).squareY;
						if (boxInfo.get(i).move == boxInfo.get(j).move) {
							winningX2 = boxInfo.get(j).squareX;
							winningY2 = boxInfo.get(j).squareY;
							for (int k = 0; k < boxInfo.size(); k++) {
								if (boxInfo.get(k).squareX == 0 && boxInfo.get(k).squareY == 2) {
									if (boxInfo.get(i).move == boxInfo.get(k).move) {
										if (boxInfo.get(i).move == 1) {
											message = "X Wins!";
											updateXWin();
										} else {
											message = "O Wins!";
											updateOWin();
										}
										txMessage = "Player 1: "+ player1name +"  Wins:  " + Integer.toString(winXCount) + "  Losses: " + Integer.toString(loseXCount);
										toMessage = "Player 2: "+ player2name +"  Wins:  " + Integer.toString(winOCount) + "  Losses: " + Integer.toString(loseOCount);
										winningX3 = boxInfo.get(k).squareX;
										winningY3 = boxInfo.get(k).squareY;
										gameWin = true;
										setChanged();
										notifyObservers();
										return true;
									}
								}
							}
						}
					}
				}
			}
			if (boxInfo.get(i).squareX == 1 && boxInfo.get(i).squareY == 0) {
				for (int j = 0; j < boxInfo.size(); j++) {
					if (boxInfo.get(j).squareX == 1 && boxInfo.get(j).squareY == 1) {
						winningX = boxInfo.get(i).squareX;
						winningY = boxInfo.get(i).squareY;
						if (boxInfo.get(i).move == boxInfo.get(j).move) {
							winningX2 = boxInfo.get(j).squareX;
							winningY2 = boxInfo.get(j).squareY;
							for (int k = 0; k < boxInfo.size(); k++) {
								if (boxInfo.get(k).squareX == 1 && boxInfo.get(k).squareY == 2) {
									if (boxInfo.get(i).move == boxInfo.get(k).move) {
										if (boxInfo.get(i).move == 1) {
											message = "X Wins!";
											updateXWin();
										} else {
											message = "O Wins!";
											updateOWin();
										}
										txMessage = "Player 1: "+ player1name +"  Wins:  " + Integer.toString(winXCount) + "  Losses: " + Integer.toString(loseXCount);
										toMessage = "Player 2: "+ player2name +"  Wins:  " + Integer.toString(winOCount) + "  Losses: " + Integer.toString(loseOCount);
										winningX3 = boxInfo.get(k).squareX;
										winningY3 = boxInfo.get(k).squareY;
										gameWin = true;
										setChanged();
										notifyObservers();
										return true;
									}
								}
							}
						}
					}
				}
			}
			if (boxInfo.get(i).squareX == 0 && boxInfo.get(i).squareY == 1) {
				for (int j = 0; j < boxInfo.size(); j++) {
					if (boxInfo.get(j).squareX == 1 && boxInfo.get(j).squareY == 1) {
						winningX = boxInfo.get(i).squareX;
						winningY = boxInfo.get(i).squareY;
						if (boxInfo.get(i).move == boxInfo.get(j).move) {
							winningX2 = boxInfo.get(j).squareX;
							winningY2 = boxInfo.get(j).squareY;
							for (int k = 0; k < boxInfo.size(); k++) {
								if (boxInfo.get(k).squareX == 2 && boxInfo.get(k).squareY == 1) {
									if (boxInfo.get(i).move == boxInfo.get(k).move) {
										if (boxInfo.get(i).move == 1) {
											message = "X Wins!";
											updateXWin();
										} else {
											message = "O Wins!";
											updateOWin();
										}
										txMessage = "Player 1: "+ player1name +"  Wins:  " + Integer.toString(winXCount) + "  Losses: " + Integer.toString(loseXCount);
										toMessage = "Player 2: "+ player2name +"  Wins:  " + Integer.toString(winOCount) + "  Losses: " + Integer.toString(loseOCount);
										winningX3 = boxInfo.get(k).squareX;
										winningY3 = boxInfo.get(k).squareY;
										gameWin = true;
										setChanged();
										notifyObservers();
										return true;
									}
								}
							}
						}
					}
				}
			}
			if (boxInfo.get(i).squareX == 2 && boxInfo.get(i).squareY == 0) {
				for (int j = 0; j < boxInfo.size(); j++) {
					if (boxInfo.get(j).squareX == 1 && boxInfo.get(j).squareY == 1) {
						winningX = boxInfo.get(i).squareX;
						winningY = boxInfo.get(i).squareY;
						if (boxInfo.get(i).move == boxInfo.get(j).move) {
							winningX2 = boxInfo.get(j).squareX;
							winningY2 = boxInfo.get(j).squareY;
							for (int k = 0; k < boxInfo.size(); k++) {
								if (boxInfo.get(k).squareX == 0 && boxInfo.get(k).squareY == 2) {
									if (boxInfo.get(i).move == boxInfo.get(k).move) {
										if (boxInfo.get(i).move == 1) {
											message = "X Wins!";
											updateXWin();
										} else {
											message = "O Wins!";

										}
										txMessage = "Player 1: "+ player1name +"  Wins:  " + Integer.toString(winXCount) + "  Losses: " + Integer.toString(loseXCount);
										toMessage = "Player 2: "+ player2name +"  Wins:  " + Integer.toString(winOCount) + "  Losses: " + Integer.toString(loseOCount);
										winningX3 = boxInfo.get(k).squareX;
										winningY3 = boxInfo.get(k).squareY;
										gameWin = true;
										setChanged();
										notifyObservers();
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		if (boxInfo.size() == 9 && !gameTie) {
			message = "Game over, no winner";
			tieCount++;
			tieMessage = "Ties: "+tieCount;
			gameTie = true;
			setChanged();
			notifyObservers();
		}
		return false;
	}

	public void incrementCount() {
		moveCount++;
		message = Integer.toString(moveCount) + " moves";
		setChanged();
		notifyObservers();
	}



}