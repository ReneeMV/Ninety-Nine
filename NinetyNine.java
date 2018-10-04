/*
 * Renee Veit
 * 12/17/17
 * Mr. Del
 * ACSL Contest #1 INT
 */

import java.util.Scanner;
/*
 * This program represents a two player card game
 * Player vs dealer
 * The Player has a hand of 3 cards and must always
 * play the largest face value, adding that value to 
 * the point total (following the special conditions)
 * the dealer's turn also affects the point total
 * read in input as one string with each # 
 * separated by a comma, my program will convert the first 
 * value --> int
 */
public class NinetyNine
{
    public int pT; 
    //pointTotal
    public String [] cards = new String[11];
    //each line is read into an array of Strings
    public int [] hand = new int[3];
    
    //reading in file one line at a time
   public void getInput(){
        Scanner inputConsole = new Scanner(System.in);
        String strInput = inputConsole.nextLine();
        makeArray(strInput);
        
        //could print each line to debug
   }
    
    //read in String and store each item in an array (field)
   public void makeArray(String input){
       String filler = "";
       for(int i = 0; i < input.length(); i++){

            char value =  input.charAt(i);
            if(value == ','){
                filler += value;
            }else if(Character.isLetterOrDigit(value)){
                cards[i] = "" + input.charAt(i);
            }
       }
       //System.out.print(cards);
       setFields(cards);
   }
       
   //read in deck of cards 
   public void setFields(String cards[]){
      String pointTotal = cards[0];
       
      pT = Integer.parseInt(pointTotal);
       //set the initial pointTotal = first int

       //read in 1st 3 chars to create player's hand
      String c1 = cards[1];
      String c2 = cards[2];
      String c3 = cards[3];
       
      char val1 = c1.charAt(1);
      char val2 = c2.charAt(1);
      char val3 = c3.charAt(1);

      //convert face cards -> numerical value using helper method
      int card1 = 0;
      int card2 = 0;
      int card3 = 0;
      
      if(Character.isLetter(val1)){
        card1 = letterValue(val1);
        hand[0] = card1;
        
      }
      
      if(Character.isLetter(val2)){
        card2 = letterValue(val2);  
        hand[1] = card2;
        
      }
      
      if(Character.isLetter(val3)){
        card3 = letterValue(val3);
        hand[2] = card3;
        
      }
      //String card3 = letterValue(val3);
      //can't do bc cards = String, letterVal returns int
       
      //remaining cards = convert string ->int
      hand[0] = Integer.parseInt(c1);
      hand[1] = Integer.parseInt(c2);
      hand[2] = Integer.parseInt(c3);
      
      playersTurn(hand);
       
   } 
    
   //helper method, returns value of face cards 
   private int letterValue(char chr){
       //String -> .equals
       if(chr == 'T')
         return 10;
         
       if(chr == 'J')
         return 11;
         
       if(chr == 'Q')
         return 12; 
         
       if(chr == 'K')
         return 13; 
         
       if(chr == 'A')
         return 14;
       
         
       return 0;
    }
   
    
   public void playNinetyNine(String cards[], int iOfMaxV){
       //read in the remaining cards left to play 
       //starting @i=4 bc 1st turn is partially
       //taken care of in playersTurn method
       
       for(int i = 4; i<=cards.length-1; i++){
           //even index value = player'sTurn
           if( i % 2 == 0){
               //ensuring that playersTurn is called first
               //only after the first turn, since it was initally
               //called first to read in the index of MaxVal
               if(i > 4){
                   playersTurn(hand);
               }
                
               //player picks up new card from deck 
               //and discards the card they just played
               String nextCard = cards[i];
               hand[iOfMaxV] = Integer.parseInt(nextCard); 
           }else{
               //the following lines of code simulate
               //the dealer's turn (when index i is odd)
               String nextCard = cards[i];
               int cardDealer = Integer.parseInt(nextCard);
               if(cardDealer == 9){
                   pT = pT;
               }else if(cardDealer == 10){     
                   pT -= 10;
               }else if(nextCard.equals("A")){
                    if(pT >= 85){
                        pT+=1;  
                    }else{
                        pT+=14;
                    }
               }else{
                    pT += cardDealer;
               }
               
               if(pT > 99)
                    System.out.println(pT + "player");
           }

        }
       
    }
    
   public void playersTurn(int hand[]){
       //read in current hand of player, player must play max face value
       int card1 = hand[0];
       int card2 = hand[1];
       int card3 = hand[2];
       
       //find max value in hand
       //int maxVal = Math.max(card1, card2, card3); doesn't work
       int max1 = Math.max(card1, card2);
       int max2 = Math.max(card2, card3);
       int maxVal = Math.max(max1, max2);
       
       String maxV = Integer.toString(maxVal);
       //special conditions of gameplay
       if(maxVal == 9){
           pT = pT;
       }else if(maxVal == 10){     
           pT -= 10;
        }else if(maxV.equals("A")){
            if(pT >= 85){
                pT+=1;  
            }else{
                pT+=14;
            }
        }else{
            pT += maxVal;
        }
       //player adds max value to point total 
       
       //player looses if point total is >99
       if(pT > 99)
           System.out.println(pT + "dealer");
       
       //find index of max value
       int iOfMaxV = -1;
       for(int index = 0; index < hand.length; index++){
           if(hand[index]== maxVal)
                iOfMaxV = index;
        }    
           
       //send the index # of the max value 
       //to the play NinetyNine method
       playNinetyNine(cards, iOfMaxV);
       //note to self: cards[] = bad syntax     
    }
    
   
}
/*
 */


