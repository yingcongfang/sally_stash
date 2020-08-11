package ece651;

public class Parsing {
  private String command;


  public boolean checkParErr() {
    //check the commands' validation
    if (command.length() == 3 &&(command.charAt(2)=='H'||command.charAt(2) == 'V'||command.charAt(2) == 'U'||command.charAt(2) == 'D'||command.charAt(2) == 'R'||command.charAt(2) == 'L')) {
      return false;
    }
    if (command.length() == 2&&command.charAt(0)<='T'&&command.charAt(0)>='A'&&command.charAt(1)<='9'&&command.charAt(1)>='0') {
      return false;
    }
    return true;
  }


  
  public Parsing(String s) {
    //make all the command upper case
    command = s.toUpperCase();
  }

  public int getX() {
    //return x coordinate
    int x = command.charAt(1)-'0';
    return x;
  }

  public int getY() {
    //return y coordinate
    int y = command.charAt(0)-'A';
    return y;
  }

  public char getOri() {
    //return the stack orietation
    char ori = command.charAt(2);
    return ori;
  }

  
}
