// Copyright (c) Anudev Gill

import java.util.*;

public class Minesweeper
{
    static Board board = new Board(10, 10, "Minesweeper"); //build 10 by 10 board for game of Minesweeper
    static Random random = new Random(); //build random number generator
    static final int NUMBER_OF_MINES = random.nextInt(10) + 11; //generate a random integer between 11 and 20 (inclusive) for number of mines
    static int[][] mines = new int[NUMBER_OF_MINES][2]; //build 2D array to store positions of mines in form [row, col]

    //build method that places mines by generating random, unique coordinates and adding the [row, col] pairs to 2D array mines 
    public static void placeMines()
    {
        boolean unique = true; //build a check variable to ensure randomly generated [row, column] pair is unique and has not already been generated, since mines should be unique
        int unfilled = NUMBER_OF_MINES; //build a counter that checks if all mines have been generated, initial value is arbitrary (anything greater than 0 will allow the loop to run intially)
        int counter = 0; //build a counter to keep track of where random [row, col] pair is to be inserted in 2D array mines
        int row; //build int to store randomly generated row number
        int col; //build int to store randomly generated column number

        //loop through mines and add in [-1, -1] pair at each index to create initial state of array (so it is non-empty and presence of [-1, -1] pairs can be used to determine that array is unfilled, as used in while loop)
        for(int i = 0; i < mines.length; i++)
        {
            mines[i][0] = -1;
            mines[i][1] = -1;
        }

        //use while loop to keep generating random, unique [row, col] pairs until mines has been filled and doesn't contain any more [-1, -1] pairs 
        while(unfilled > 0)
        {
            unfilled = 0; //reset value of unfilled to 0, its value will later be increased if mines contains [-1, -1] pairs
            unique = true; //set unique variable to true, so that if it was set to false during previous run through loop, its value is reset
            row = random.nextInt(10); //generate random integer between 0 and 9 (inclusive) for row number
            col = random.nextInt(10); //generate random integer between 0 and 9 (inclusive) for column number

            //loop through values of mines and ensure that the random [row, col] pair generated on lines 34 and 35 has not already been generated prior (by coincidence)
            for(int i = 0; i < mines.length; i++)
            {
                //if the random [row, col] pair that was generated is already in the array, set unique to false
                if(row == mines[i][0] && col == mines[i][1])
                {
                    unique = false;
                }
            }

            //if unique is true (which means the random [row, col] pair was not already in mines) add the pair to mines
            if(unique)
            {
                mines[counter][0] = row; //add the row number to index [counter][0] of mines (counter is the current position we are in in mines)
                mines[counter][1] = col; //add the column number to index [counter][1] of mines (counter is the current position we are in in mines)

                counter++; //increment counter to move to the next position    
            }
            
            //loop through mines and check if it still has [-1, -1] pairs, in which case it is unfilled
            for(int i = 0; i < mines.length; i++)
            {
                //if mines at index i is [-1, -1], increment unfilled
                if(mines[i][0] == -1 && mines[i][1] == -1)
                {
                    unfilled++; //note that the loop won't stop until unfilled is 0
                }
            }
        }
    }

    //build method that checks if a given square has a mine and takes the square's row and column numbers as input and returns true or false
    public static boolean hasMine(int row, int col)
    {
        //loop through the mines array
        for(int i = 0; i < mines.length; i++)
        {
            //if the row and column number of the square correspond to a [row, col] pair in mines, return true
            if(row == mines[i][0] && col == mines[i][1])
            {
                return true;
            }
        }

        return false; //otherwise, return false
    }
    
    //build method to count and return the number of adjacent mines around a given clicked square, taking the clicked square as input
    public static int countMines(Coordinate click)
    {
        int count = 0; //build accumulator variable and initialize it to 0
        
        //try to check the top left square
        try
        {
            //if it has a mine, increment count
            if(hasMine(click.getRow() - 1, click.getCol() - 1))
            {
                count++;
            }
        }
        //otherwise if the square is inaccessible / doesn't exist on the board
        catch(Exception e)
        {
            //do nothing
        }

        //try to check the top middle square
        try
        {
            //if it has a mine, increment count
            if(hasMine(click.getRow() - 1, click.getCol()))
            {
                count++;
            }

        }
        //otherwise if the square is inaccessible / doesn't exist on the board
        catch(Exception e)
        {
            //do nothing
        }   

        //try to check the top right square
        try
        {
            //if it has a mine, increment count
            if(hasMine(click.getRow() - 1, click.getCol() + 1))
            {
                count++;
            }
        }
        //otherwise if the square is inaccessible / doesn't exist on the board
        catch(Exception e)
        {
            //do nothing
        }    

        //try to check the middle left square
        try
        {
            //if it has a mine, increment count
            if(hasMine(click.getRow(), click.getCol() - 1))
            {
                count++;
            }
        }
        //otherwise if the square is inaccessible / doesn't exist on the board
        catch(Exception e)
        {
            //do nothing
        }

        //try to check the middle right square
        try
        {
            //if it has a mine, increment count
            if(hasMine(click.getRow(), click.getCol() + 1))
            {
                count++;
            }
        }
        //otherwise if the square is inaccessible / doesn't exist on the board
        catch(Exception e)
        {
            //do nothing
        }

        //try to check the bottom left square
        try
        {
            //if it has a mine, increment count
            if(hasMine(click.getRow() + 1, click.getCol() - 1))
            {
                count++;
            }
        }
        //otherwise if the square is inaccessible / doesn't exist on the board
        catch(Exception e)
        {
            //do nothing
        }    

        //try to check the bottom middle square
        try
        {
            //if it has a mine, increment count
            if(hasMine(click.getRow() + 1, click.getCol()))
            {
                count++;
            }
        }
        //otherwise if the square is inaccessible / doesn't exist on the board
        catch(Exception e)
        {
            //do nothing
        }

        //try to check the bottom right square
        try
        {
            //if it has a mine, increment count
            if(hasMine(click.getRow() + 1, click.getCol() + 1))
            {
                count++;
            }
        }
        //otherwise if the square is inaccessible / doesn't exist on the board
        catch(Exception e)
        {
            //do nothing
        }

        return count; //return the value of count
    }
    
    //build method that places a peg with the number of adjacent mines, taking the clicked square as input
    public static void placePeg(Coordinate click) throws Exception
    {
        //call countMines passing in the click as input and use String.valueOf to convert this to a string so as to put the correct peg at the row, column location of the click
        board.putPeg(new Peg(String.valueOf(countMines(click)) + ".png"), click.getRow(), click.getCol());
    }

    //build method that reveals mines by placing a mine peg at their locations
    public static void revealMines() throws Exception
    {
        //loop through mines array
        for(int i = 0; i < mines.length; i++)
        {
            board.putPeg(new Peg("mine.png"), mines[i][0], mines[i][1]); //put mine peg at the [row, col] location
            Thread.sleep(200); //sleep the thread for 200 milliseconds to add a stagger effect    
        }
    }

    //build method to check if player's current click is legal, taking as arguments the click as a Coordinate, and a 2D array of all the past clicks in the form [row, col]
    public static boolean isLegal(Coordinate click, int[][] pastClicks)
    {
        //loop through the pastClicks array
        for(int i = 0; i < pastClicks.length; i++)
        {
            //if the row and column values of the current click match with a [row, col] pair in pastClicks then the player has already clicked this square before
            if(pastClicks[i][0] == click.getRow() && pastClicks[i][1] == click.getCol())
            {
                return false; //in this case return false and exit the method becuase this is not a valid move, and we do not want this to count in the number of tries it takes the player to clear the board
            }
        }
        
        return true; //otherwise if the click is unique and the player has not already clicked these coordinates before, return true as the click is legal
    }
    
    public static void main(String[] args) throws Exception
    {
        boolean unHit = true; //build boolean variable to check if player has hit a mine
        int guessCounter = 0; //build int guessCounter that starts off at 0 and tracks how many guesses it took the player to clear the board
        int clickCounter = 0; //build int clickCounter that starts off at 0 and acts as a counter/index to add all of the player's valid clicks to 2D int[][] array clickTracker which is passed to isLegal() method as an argument to determine if user has already clicked a given square before
        Coordinate currentClick; //build Coordinate currentClick that stores the coordinate (e.g. row, col pair) of the player's latest click
        int[][] clickTracker = new int[100 - NUMBER_OF_MINES][2]; //build 2D int[][] array called clickTracker that contains (100 - the number of mines) 2-element pairs of the form [row, col] to store all of the player's possible clicks
        // Background music = new Background("SuspenseTheme.wav"); //build instance of Background passing SuspenseTheme.wav as argument for filePath, this is for the background music
        
        placeMines(); //call placeMines method to place the mines

        //loop through clickTracker array and set each pair to [-1, -1] since no squares on the board have been clicked as of yet
        for(int i = 0; i < clickTracker.length; i++)
        {
            clickTracker[i][0] = -1;
            clickTracker[i][1] = -1;
        }

        // music.run(); //play the background music

        //welcome player and explain how to play the game, sleep thread in between so that player has time to read messages
        board.displayMessage("Welcome to Minesweeper!");
        Thread.sleep(3000);
        board.displayMessage("The goal of the game is to clear the board without hitting a mine.");
        Thread.sleep(3500);
        board.displayMessage("You can click squares with your mouse.");
        Thread.sleep(3000);
        board.displayMessage("The displayed number signals how many adjacent squares have a mine.");
        Thread.sleep(4000);
        board.displayMessage("For example, a 2 means two adjacent squares have a mine, so be careful!");
        Thread.sleep(4000);
        board.displayMessage("Good luck!");

        //while condition checks that no mine has been hit and the board has not been cleared
        while(unHit && guessCounter < (100 - NUMBER_OF_MINES))
        {
            currentClick = board.getClick(); //get the user's current click

            //if condition calls isLegal method to check that the click is legal
            if(isLegal(currentClick, clickTracker))
            {
                clickTracker[clickCounter][0] = currentClick.getRow(); //add the row of the current click to clickTracker
                clickTracker[clickCounter][1] = currentClick.getCol(); //add the column of the current click to clickTracker
                
                clickCounter++; //increment clickCounter which keeps track of where we are in clickTracker, e.g. the appropriate index
                guessCounter++; //increment guessCounter
                
                //if there is a mine at the location of the click
                if(hasMine(currentClick.getRow(), currentClick.getCol()))
                {
                    revealMines(); //reveal all the mines
                    unHit = false; //set unHit to false to break the loop
                   
                    //if the player hit a mine on their first hit, print a losing message with the correct grammar
                    if(guessCounter == 1)
                    {
                        board.displayMessage("Game over. It took you 1 click until you hit a mine!");
                    }
                    //otherwise if it took them more than one click, print a similar grammatically appropriate losing message telling them how many clicks it took them until they hit a mine
                    else
                    {
                        board.displayMessage("Game over. It took you " + guessCounter + " clicks until you hit a mine!");
                    }
                }
                //otherwise if there is no mine at the location of the click, place the appropriate peg there
                else
                {
                    placePeg(currentClick);
                }
            }
            //otherwise if the move was not legal
            else
            {
                board.displayMessage("Invalid move. Please do not click squares you have already clicked."); //tell the player the move was invalid
                Thread.sleep(2000); //sleep the thread, forcing the player to read the message
                board.displayMessage(""); //clear the message
            }
        }

        //if the user was successful in clearing the board and clicked all the squares that don't have mines
        if(guessCounter == 100 - NUMBER_OF_MINES)
        {
            board.displayMessage("Game over. You cleared all the mines! It took you " + guessCounter + " clicks!"); //print a winning message with the number of clicks it took the player to clear the board
            revealMines(); //reveal the mines
        }
    }
}