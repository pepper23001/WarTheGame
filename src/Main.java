// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean is_playing = true;

        printInstructions();
        ArrayList<String> deck = createDeck();
        System.out.print("\nWould you like to play?\nType yes to play\n");
        while (is_playing) {

            Scanner input = new Scanner(System.in);
            String action = input.next().toLowerCase();

            if (action.charAt(0) == 'y') {
                game(deck);
                System.out.print("\nWould you like to play again?\nType yes to play\n");
            }else{
                is_playing = false;

            }
        }
    }

    static void game(ArrayList<String> deck){
        boolean game = true;
        ArrayList<String> shuffledDeck = shuffle(deck);

        ArrayList<String> hand = deal(shuffledDeck);
        ArrayList<String> compHand = deal(shuffledDeck);
        int turns = 0;
        while (game == true){

            turn(hand, compHand);
            ++turns;
            if (compHand.size() < 5){
                char number = compHand.get(0).charAt(1);
                char myNumber = hand.get(0).charAt(1);
                if(number == myNumber){
                    game = false;
                    System.out.print("\nYou Won!\nIt took:"+turns+" turns for you to win!");
                }

            }
            if (hand.size() < 5){
                char number = compHand.get(0).charAt(1);
                char myNumber = hand.get(0).charAt(1);
                if(number == myNumber){
                    game = false;
                    System.out.print("\nYou lost!\nBetter luck next time!");
                }

            }
            if (hand.size()== 0){
                game = false;
                System.out.print("\nYou lost!\nBetter luck next time!");
            }
            if (compHand.size() == 0){
                game = false;
                System.out.print("\nYou Won!\nIt took:"+turns+"for you to win!");
            }
        }

    }
    static void printInstructions(){
        System.out.print("Welcome to War!\nThis is a card game played between you and the computer");
        System.out.print("\nEach turn you play a card against your opponent the highest card wins,\nThus the order is A,1,2,3... ");
        System.out.print("If you win you get both of the cards\nAfter your turn the amount of card is both your hand and the computer's will be displayed\n");
        System.out.print("If the cards are the same then three cards get put face down and a final card gets placed on top.");
        System.out.print("\nIf your final card is bigger than their's you win all the cards otherwise you lose.");
    }
    static ArrayList<String> createDeck(){//creates all 52 cards
        ArrayList<String> deck = new ArrayList<String>();
        String suit = "";//turn to array
        int cardNumInt = 0;
        int cardSuitInt = 0;

        String[] suits = new String[]{"D","S","C","H"};
        String[] cardNumber = new String[]{"A ","2 ","3 ","4 ","5 ","6 ","7 ","8 ","9 ","10","J ","Q ","K "};
        while (cardNumInt < cardNumber.length){

            while (cardSuitInt < suits.length){

                deck.add((suits[cardSuitInt]+cardNumber[cardNumInt]));
                ++cardSuitInt;

            }
            cardSuitInt = 0;
            ++cardNumInt;
        }
        return deck;
    }
    static ArrayList<String> shuffle(ArrayList<String> deck){//changes the order of the deck
        int unshuffled = 0;
        Random rand = new Random();


        ArrayList<String> shuffledDeck = new ArrayList<String>();
        while (unshuffled < deck.size()) {
            int placeInDeck = rand.nextInt((unshuffled+1));
            shuffledDeck.add(placeInDeck,(deck.get(unshuffled)));
            ++unshuffled;

        }
        return shuffledDeck;
    }
    static ArrayList<String> deal(ArrayList<String> shuffledDeck){
        ArrayList<String> hand = new ArrayList<String>();


        int cardsInHand = 0;
        while (cardsInHand < 26) {

            String cardDrawn = shuffledDeck.get(0);
            shuffledDeck.remove(0);
            hand.add(cardDrawn);
            ++cardsInHand;

        }
        return hand;
    }
    static void turn(ArrayList<String> hand,ArrayList<String> compHand ){
        boolean turn = true;
        while (turn == true) {
            Scanner readinput = new Scanner(System.in);
            String enterkey = "\nPress enter to place down a card\n";
            System.out.print(enterkey);
            enterkey = readinput.nextLine();
            System.out.print(enterkey);

            if (enterkey.equals("")) {
                boolean war = false;
                flip(hand, compHand, war);
                if (hand.size() < 5|compHand.size() < 5){
                    char number = compHand.get(0).charAt(1);
                    char myNumber = hand.get(0).charAt(1);
                    if(number == myNumber){
                        return;
                    }

                }
                if (hand.size() == 0|compHand.size() == 0){
                    return;
                }
                System.out.print("\nyou have: "+hand.size()+" cards in your hand\n");
                System.out.print("the computer has: "+compHand.size()+" cards in its hand");
                turn = false;

            } else {
                System.out.print("\noops you might have typed something wrong, try again!");
            }
        }

    }
    static boolean flip(ArrayList<String> hand,ArrayList<String> compHand,boolean war ) {
        boolean flipped = true;

        if (war == false) {
            System.out.printf("\n __________" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|       " + compHand.get(0) + "|" +
                    "\n|__________|");
            System.out.printf("\n __________" +
                    "\n|" + hand.get(0) + "       |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|__________|");
        }else if (war == true){
            System.out.printf("\n __________" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|       " + compHand.get(0) + "|" +
                    "\n|__________|"+
                    "\n|__________|"+
                    "\n|__________|"+
                    "\n|__________|");

            System.out.printf("\n __________" +
                    "\n|__________|" +
                    "\n|__________|" +
                    "\n|__________|" +
                    "\n|" + hand.get(0) + "       |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|          |" +
                    "\n|__________|");
        }
        char number = compHand.get(0).charAt(1);
        char myNumber = hand.get(0).charAt(1);
        char[] cardNumber = new char[]{'A','2','3','4','5','6','7','8','9','1','J','Q','K'};
        boolean loop = true;
        int cardNumberInt = 0;
        while (loop == true){
            if (myNumber == number ){
                System.out.print("\nduh, duh, duh....War!\n");
                if (hand.size() < 5|compHand.size() < 5){
                    return flipped;
                }
                war = true;
                ArrayList<String> limbo = new ArrayList<String>();
                for (int i = 0; i < 4; i++) {
                    limbo.add(hand.get(0));
                    hand.remove(0);
                    limbo.add(compHand.get(0));
                    compHand.remove(0);
                }
                flipped = flip(hand,compHand,war);
                war = false;
                System.out.print(limbo+" cards");
                if (flipped == true){

                    for (int i = 0; i < 8; i++) {
                        hand.add(limbo.get(0));
                        limbo.remove(0);

                    }
                }else{

                    for (int i = 0; i < 8; i++) {
                        compHand.add(limbo.get(0));
                        limbo.remove(0);

                    }
                }
                break;
            } else if (cardNumber[cardNumberInt] == number){
                System.out.print("\nyou won ");
                String moving = compHand.get(0);
                hand.add(hand.size(), moving);
                compHand.remove(0);
                String movingBack = hand.get(0);
                hand.add(hand.size(), movingBack);
                hand.remove(0);
                flipped = true;
                break;
            } else if (cardNumber[cardNumberInt] == myNumber) {

                System.out.print("\nyou lost ");
                String moving = hand.get(0);
                compHand.add(compHand.size(), moving);
                hand.remove(0);
                String movingBack = compHand.get(0);
                compHand.add(compHand.size(), movingBack);
                compHand.remove(0);
                flipped = false;
                break;
            }
            ++cardNumberInt;
        }

        return flipped;
    }
}
