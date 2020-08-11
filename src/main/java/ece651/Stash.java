package ece651;

import java.util.Vector;

import javafx.geometry.Point2D;



public class Stash {
  private char color;
  private int dimension;
  private Vector<Point2D> loc = new Vector<>();
  private Point2D put;
  private boolean putS = false;
  int[][] RU = { {0,0}, {1,0}, {2,0}, {1,-1} };
  int[][] RR = { {0,0}, {0,1}, {0,2}, {1,1} };
  int[][] RL = { {0,0}, {0,-1}, {0,-2}, {-1,-1} };
  int[][] RD = { {0,0}, {-1,0}, {-2,0}, {-1,1} };
  int[][] BU = { {0,0}, {0,1}, {0,2}, {1,2}, {1,3}, {1,4} };
  int[][] BD = { {0,0}, {0,-1}, {0,-2}, {1,-2}, {1,-3}, {1,-4} };
  int[][] BL = { {0,0}, {1,0}, {2,0}, {2,-1}, {3,-1}, {4,-1} };
  int[][] BR = { {0,0}, {-1,0}, {-2,0}, {-2,-1}, {-3,-1}, {-4,-1} };
  int[][] GV = { { 0, 0 }, { 0, 1 } };
  int[][] GH = { { 0, 0 }, { 1, 0 } };
  int[][] PH = { { 0, 0 }, { 1, 0 }, {2,0} };
  int[][] PV = { { 0, 0 }, { 0, 1 }, {0,2} };


  
  Stash(Stash s) {
    //copy other stack
    color = s.getColor();
    dimension = s.getDimesion();
    for (Point2D l : s.getLoc()) {
      loc.add(l);
    }
    put = s.getPut();
  }

  public Stash(char c, int d, int x, int y) {
    //initialize a stack
    color = c;
    dimension = d;
    put = new Point2D(x, y);
  }

  public void moveR(int oldX, int oldY, int newX, int newY,char Ori) {
    //move red stack
    int[][] spin = new int[4][2];
    if (Ori == 'U') {
      spin = RU;
    }
    if (Ori == 'D') {
      spin = RD;
    }
    if (Ori == 'L') {
      spin = RL;
    }
    if (Ori == 'R') {
      spin = RR;
    }
    Point2D o = new Point2D(oldX, oldY);
    int index = loc.indexOf(o);
    Vector<Point2D> newLoc = new Vector<>();
    for (int i = 0; i < 4; i++) {
      Point2D p = new Point2D(newX+spin[i][0]-spin[index][0], newY+spin[i][1]-spin[index][1]);
      newLoc.add(p);
    }
    loc = newLoc;
    putS = true;
  }


  public void moveB(int oldX, int oldY, int newX, int newY,char Ori) {
    //move blue stack
    int[][] spin = new int[6][2];
    if (Ori == 'U') {
      spin = BU;
    }
    if (Ori == 'D') {
      spin = BD;
    }
    if (Ori == 'L') {
      spin = BL;
    }
    if (Ori == 'R') {
      spin = BR;
    }
    Point2D o = new Point2D(oldX, oldY);
    int index = loc.indexOf(o);
    Vector<Point2D> newLoc = new Vector<>();
    for (int i = 0; i < 6; i++) {
      Point2D p = new Point2D(newX+spin[i][0]-spin[index][0], newY+spin[i][1]-spin[index][1]);
      newLoc.add(p);
    }
    loc = newLoc;
    putS = true;
  }


  public void moveG(int oldX, int oldY, int newX, int newY,char Ori) {
    //move green stack
    int[][] spin = new int[2][2];
    if (Ori == 'V') {
      spin=GV;
    }
    if (Ori == 'H') {
      spin = GH;
    }
    Point2D o = new Point2D(oldX, oldY);
    int index = loc.indexOf(o);
    Vector<Point2D> newLoc = new Vector<>();
    for (int i = 0; i < 2; i++) {
      Point2D p = new Point2D(newX+spin[i][0]-spin[index][0], newY+spin[i][1]-spin[index][1]);
      newLoc.add(p);
    }
    loc = newLoc;
    putS = true;
  }

  public void moveP(int oldX, int oldY, int newX, int newY,char Ori) {
    //move purple stack
    int[][] spin = new int[3][2];
    if (Ori == 'V') {
      spin=PV;
    }
    if (Ori == 'H') {
      spin = PH;
    }
    Point2D o = new Point2D(oldX, oldY);
    int index = loc.indexOf(o);
    Vector<Point2D> newLoc = new Vector<>();
    for (int i = 0; i < 3; i++) {
      Point2D p = new Point2D(newX+spin[i][0]-spin[index][0], newY+spin[i][1]-spin[index][1]);
      newLoc.add(p);
    }
    loc = newLoc;
    putS = true;
  }
  

  
  public Point2D getPut() {
    //return the coordinate of first put
    return put;
  }
  
  public int getDimesion() {
    //return dimesion
    return dimension;
  }
  
  public char getColor() {
    //return color of the stack
    return color;
  }

  public Vector<Point2D> getLoc() {
    //return loction vector of the stack
    return loc;
  }

  public boolean putOrnot() {
    //if the stack is put in an direction
    return putS;
  }
  
  public void putH(){
    //put stack in horizontal
    if (color == 'G' || color == 'P') {
      for (int i = 0; i < dimension; i++) {
        Point2D pp = new Point2D(put.getX() + i, put.getY());
        loc.add(pp);
      }
      putS = true;
    }
  }

  public void putV() {
    //put stack in vertical
    if (color == 'G' || color == 'P') {
      for (int i = 0; i < dimension; i++) {
        Point2D pp = new Point2D(put.getX(), put.getY() + i);
        loc.add(pp);
      }
      putS = true;
    }
  }
  
  public void putRU() {
    //put red stack in up direction
    if (color == 'R') {
      for (int i = -1; i < 2; i++) {
        Point2D pp = new Point2D(put.getX() + i, put.getY() + 1);
        loc.add(pp);
      }
      Point2D pp = new Point2D(put.getX(), put.getY());
      loc.add(pp);
      putS = true;
    }
  }

  public void putRR() {
    //put red stack in right direction
    if (color == 'R') {
      for (int i = 0; i < 3; i++) {
        Point2D pp = new Point2D(put.getX(), put.getY() + i);
        loc.add(pp);
      }
      Point2D pp = new Point2D(put.getX() + 1, put.getY() + 1);
      loc.add(pp);
      putS = true;
    }
  }

  public void putRD() {
    //put red stack in down direction
    if (color == 'R') {
      for (int i = 2; i >=0; i--) {
        Point2D pp = new Point2D(put.getX() + i, put.getY());
        loc.add(pp);
      }
      Point2D pp = new Point2D(put.getX() + 1, put.getY() + 1);
      loc.add(pp);
      putS = true;
    }
  }

  public void putRL() {
    //put red stack in left direction
    if (color == 'R') {
      for (int i = 2; i >= 0; i--) {
        Point2D pp = new Point2D(put.getX(), put.getY() + i);
        loc.add(pp);
      }
      Point2D pp = new Point2D(put.getX() - 1, put.getY() + 1);
      loc.add(pp);
      putS = true;
    }
  }

  public void putBU() {
    //put blue stack in up direction
    if (color == 'B') {
     for (int i = 0; i < 3; i++) {
      Point2D pp = new Point2D(put.getX(), put.getY()+i);
      loc.add(pp);
    }
     for (int i = 0; i < 3; i++) {
      Point2D pp = new Point2D(put.getX()+1, put.getY()+2+i);
      loc.add(pp);
    }
     putS = true;
    }
  }

  public void putBR() {
    //put blue stack in right direction
    if (color == 'B') {
     for (int i = 0; i < 3; i++) {
      Point2D pp = new Point2D(put.getX()+i, put.getY());
      loc.add(pp);
    }
     for (int i = 0; i < 3; i++) {
      Point2D pp = new Point2D(put.getX()+2+i, put.getY()-1);
      loc.add(pp);
    }
     putS = true;
    }
  }

  public void putBD() {
    //put blue stack in down direction
    if (color == 'B') {
     for (int i = 0; i < 3; i++) {
      Point2D pp = new Point2D(put.getX()-1, put.getY()+4-i);
      loc.add(pp);
    }
     for (int i = 0; i < 3; i++) {
      Point2D pp = new Point2D(put.getX(), put.getY()+2-i);
      loc.add(pp);
    }
     putS = true;
    }
  }
  public void putBL() {
    //put blue stack in left direction
    if (color == 'B') {
     for (int i = 0; i < 3; i++) {
      Point2D pp = new Point2D(put.getX()+4-i, put.getY()+1);
      loc.add(pp);
    }
     for (int i = 0; i < 3; i++) {
      Point2D pp = new Point2D(put.getX()+2-i, put.getY());
      loc.add(pp);
    }
     putS = true;
    }
  }

  public void orient(char ori) {
    //check the direction
    if (ori == 'H') {
      putH();
    }
    if (ori == 'V') {
      putV();
    }
    if (ori == 'U') {
      putBU();
      putRU();
    }
    if (ori == 'D') {
      putBD();
      putRD();
    }
    if (ori == 'L') {
      putBL();
      putRL();
    }
    if (ori == 'R') {
      putRR();
      putBR();
    }
  }
  
}
