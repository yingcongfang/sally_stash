package ece651;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import javafx.geometry.Point2D;

public class Board {
  private HashMap<Point2D, Stash> grid = new HashMap<>();
  private HashMap<Point2D, Character> digMap=new HashMap<>();
  private Vector<Point2D> correct=new Vector<>();
  int width=10;
  int height=20;
  int countM=2;
  int countS=1;

  
  public int countM() {
    //return number of move player can use
    return countM;
  }

  public int countS() {
    //return number of sonar player can use
    return countS;
  }

  public void minusM() {
    //reduce number of move
    countM--;
  }

  public void minusS() {
    //reduce number of sonar
    countS--;
  }
  
  public HashMap<Character,Integer> sonar(int x,int y){
    //sonar scan method
    HashMap<Character, Integer> colCount = new HashMap<>();
    colCount.put('G',0);
    colCount.put('P',0);
    colCount.put('R',0);
    colCount.put('B',0);
    for (int j = -3; j <= 3;j++){
      for (int i = Math.abs(j) - 3; i <= 3 - Math.abs(j); i++) {
        Point2D p = new Point2D(x+i, y+j);
        if (grid.containsKey(p)) {
          char c=grid.get(p).getColor();
          int n = colCount.get(c);
          colCount.replace(c,n ,n + 1);
        }
      }
    }
    return colCount;
  }




  
  public boolean move(int x, int y,int newx,int newy,char Ori) {
    //move method
    Point2D p = new Point2D(x, y);
    if (!grid.containsKey(p)) {
      System.out.println(output("Error: Can't find stack in this place! Please try again!"));
      return false;
    }
    Stash Old = grid.get(p);
    Stash New = new Stash(Old);
    if (New.getColor() == 'R'&&(Ori=='U'||Ori=='D'||Ori=='R'||Ori=='L')) {
      New.moveR(x, y, newx, newy,Ori);
    }
    if (New.getColor() == 'B'&&(Ori=='U'||Ori=='D'||Ori=='R'||Ori=='L')) {
      New.moveB(x, y, newx, newy,Ori);
    }
    if (New.getColor() == 'P'&&(Ori=='V'||Ori=='H')) {
      New.moveP(x, y, newx, newy,Ori);
    }
    if (New.getColor() == 'G'&&(Ori=='V'||Ori=='H')) {
      New.moveG(x, y, newx, newy,Ori);
    }
    if(addStash(New)){
      for (int i = 0; i < Old.getLoc().size();i++){
        //make stach in correct counter move too
        if (correct.contains(Old.getLoc().get(i))) {
          correct.remove(Old.getLoc().get(i));
          correct.add(New.getLoc().get(i));
        }
      }
      delete(Old);
    }
    else{
      return false;
    }
    return true;
  }

  private void delete(Stash s) {
    //delete a stack
    Vector<Point2D> locs = s.getLoc();
    for (Point2D loc : locs) {
      grid.remove(loc);
    }
    
  }

  
  public boolean checkWinner() {
    //check if the player find all stack
    if (correct.size() == grid.size()) {
      return false;
    }
    return true;
  }


  private String output(String line) {
    //format 
    StringBuffer sb = new StringBuffer();
    sb.append("--------------------------------\n");
    sb.append(line);
    sb.append("\n--------------------------------\n");
    return sb.toString();
  }
  
  public void dig(int x,int y,String name) {
    //dig
    Point2D p = new Point2D(x, y);
    if (grid.containsKey(p)) {
      if(!digMap.containsKey(p)){
        digMap.put(p,grid.get(p).getColor());
      }
      if(!correct.contains(p)){
        correct.add(p);
      }
      System.out.println(output(name+" found a stack!"));
    }
    else {
      if (!digMap.containsKey(p)) {
        digMap.put(p, 'X');
      }
      System.out.println(output(name+" missed!"));
    }
  }

  public char findDig(int x, int y) {
    //return the digmap
    Point2D pp = new Point2D(x, y);
    if (digMap.containsKey(pp)) {
      return digMap.get(pp);
    }
    return ' ';
  }

  public char findMyself(int x, int y) {
    //return self board
    Point2D pp = new Point2D(x, y);
    if(correct.contains(pp)){
      return '*';
    }
    else if (grid.containsKey(pp)) {
      Stash s = grid.get(pp);
      return s.getColor();
    }
    return ' ';
  }
  
  public int checkValid(Vector<Point2D> list) {
    //check if the stack is valid for the board
    int n = list.size();
    for (int i = 0; i < n; i++) {
      Point2D p = list.get(i);
      if (p.getX()<0||p.getY()<0||(p.getX() - width) >= 0 || (p.getY() - height) >= 0) {
        return 0;
      }
      if(grid.containsKey(p)){
        return -1;
      }
    }
    return 1;
  }

  public int getW() {
    //get board width
    return width;
  }

  public int getH() {
    //get board height
    return height;
  }
  
  public boolean addStash(Stash s) {
    //put stash in the board
    if (!s.putOrnot()) {
      System.out.println("This stack can't put in this way! Please try again!");
      return false;
    }
    Vector<Point2D> list = s.getLoc();
    if (checkValid(list)==1) {
      int n = list.size();
      for (int i = 0; i < n; i++) {
        grid.put(list.elementAt(i), s);
      }
      return true;
    }
    else if (checkValid(list) == 0) {
      System.out.println(output("Error:stack going off the grid! Please try again!"));
      return false;
    }
    else {
      System.out.println(output("Error: collides with another stack! Please try again!"));
      return false;
    }
  }
  
  public char findLoc(int x, int y) {
    //get the original site of the board
    Point2D p = new Point2D(x, y);
    if(grid.containsKey(p)){
      Stash s = grid.get(p);
      return s.getColor();
    }
    return ' ';
  }

  public void comMove() {
    //computer move stack
    Computer com = new Computer();    
    Set<Point2D> ps = grid.keySet();
    Iterator<Point2D> entry = ps.iterator();
    Point2D point=entry.next();
    
    Stash Old = grid.get(point);
    char ori;
    
    while (true) {
      if(Old.getColor()=='G'||Old.getColor()=='P'){
        ori=com.getNormM();
      }else{
        ori=com.getCrazy();
      }
      if (move((int)point.getX(), (int)point.getY(), com.getX(), com.getY(), ori)) {
        break;
    }


    }
    
  }

}
