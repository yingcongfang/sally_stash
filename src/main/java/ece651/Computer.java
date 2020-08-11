package ece651;

import java.util.Random;

public class Computer {
  public String getAction() {
    Random rand =new Random();
    int randnum = rand.nextInt((3 - 1) + 1) + 1;
    if(randnum==1){
      return "D";
    }
    else if(randnum==2){
      return "M";
    }
    else {
      return "S";
    }
  }

  public int getX() {
    Random rand =new Random();
    int randnum = rand.nextInt((9 - 0) + 1) + 0;
    return randnum;
  }

   public int getY() {
    Random rand =new Random();
    int randnum = rand.nextInt((19 - 0) + 1) + 0;
    return randnum;
  }

  public char getNormM() {
    Random rand =new Random();
    int randnum = rand.nextInt((1 - 0) + 1) + 0;
    if (randnum == 1) {
      return 'V'; 
    }
    else {
      return 'H';
    }
  }

  public char getCrazy() {
     Random rand =new Random();
    int randnum = rand.nextInt((4 - 1) + 1) + 1;
    if (randnum == 1) {
      return 'U'; 
    }
    else if(randnum == 2){
      return 'D';
    }
    else if(randnum == 3){
      return 'L';
    }
    else {
      return 'R';
    }
  }



}
