package ece651;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class BoardTest {
  @Test
  public void test_board() {
    //test stack put method
    Board b = new Board();
    Draw draw = new Draw();
    Stash s1 = new Stash('R', 4, 1, 1);
    s1.putH();
    assertFalse(b.addStash(s1));
    Stash s2 = new Stash('P', 2, 1, 0);
    s2.putBD();
    assertFalse(b.addStash(s2));
    
    s2.putV();
    assertTrue(b.addStash(s2));
    assertFalse(b.move(1,0,7,0,'R'));
    assertFalse(b.move(1,0,7,0,'U'));
    assertFalse(b.move(1,0,7,0,'D'));
    assertFalse(b.move(1,0,7,0,'L'));
    Stash s3 = new Stash('P', 2, 9, 19);
    s3.putH();
    assertFalse(b.addStash(s3));
    Stash s4 = new Stash('P', 2, -1, 1);
    s4.putH();
    assertFalse(b.addStash(s4));

    //test parsing method
    Parsing par = new Parsing("G4V");
    assertFalse(par.checkParErr());
    System.out.println("x:"+par.getX()+" y:"+par.getY()+" o:"+par.getOri());
    par = new Parsing("z0");
    assertTrue(par.checkParErr());
    par = new Parsing("a1");
    assertFalse(par.checkParErr());
    
    
    
    
    Board a = new Board();
    Stash scrazy = new Stash('B', 100, 1, 1);
    scrazy.putBU();
    assertTrue(a.addStash(scrazy));
    Stash scrazy1 = new Stash('B', 100, 6, 0);
    scrazy1.putBD();
    assertTrue(a.addStash(scrazy1));
    assertFalse(a.move(6,0,7,0,'H'));
    assertFalse(a.move(6,0,7,0,'V'));
    Stash scrazy2 = new Stash('R', 100, 0, 9);
    scrazy2.putRL();
    assertFalse(a.addStash(scrazy2));
    Stash scrazy3 = new Stash('R', 100, 1, 7);
    scrazy3.putRR();
    assertTrue(a.addStash(scrazy3));
    Stash ss4 = new Stash('G', 2, 10, 1);
    ss4.putV();
    assertFalse(a.addStash(ss4));
    assertFalse(a.move(10, 1, 10, 2, 'H'));
    draw.printBoard(a);
    assertTrue(a.move(1, 2, 4, 11,'R'));
    assertFalse(a.move(1, 2, 0, 15,'R'));
    
    HashMap<Character,Integer> colcount= a.sonar(2, 6);
   
    assertEquals((Integer)0,(Integer)colcount.get('G'),"Green");
    assertEquals((Integer)0,(Integer)colcount.get('P'),"Purple");
    assertEquals((Integer)3,(Integer)colcount.get('R'),"Red");
    assertEquals((Integer)0,(Integer)colcount.get('B'),"Blue");
    
    draw.printBoard(a);
    a.dig(6, 0,"A");
    a.dig(6, 1,"A");
    a.dig(6, 2,"A");
    a.dig(5, 2,"A");
    a.dig(5, 3,"A");
    a.dig(5, 4,"A");
    a.dig(1, 7,"A");
    a.dig(1, 8,"A");
    a.dig(2, 8,"A");
    a.dig(1, 9,"A");
    a.dig(1, 10, "A");
    a.dig(2,10,"A");
    a.dig(3,10,"A");
    a.dig(3,11,"A");
    a.dig(4,11,"A");
    a.dig(4,11,"A");
    assertTrue(a.checkWinner()    );
    a.dig(5,11,"A");
    assertFalse(a.checkWinner() );
    
   
    s1 = new Stash('R', 2, 5, 5);
    s1.putV();
    s1.putRD();
    assertTrue(a.addStash(s1));
    s1 = new Stash('B', 2, 0, 7); 
    s1.putBU();
    s1.moveB(0, 7, 0, 7, 'D');
    assertTrue(a.addStash(s1));
    s1 = new Stash('B', 2, 1, 7);
    s1.putBL();
   assertFalse(a.addStash(s1));
    s1 = new Stash('B', 2, 5, 9);
    s1.putBR();
    assertTrue(a.addStash(s1));
    
    a.comMove();
     
    s1.moveR(5, 9, 7, 9,'R');
    assertTrue(a.addStash(s1));
    s1.moveR(7, 9, 2, 2,'U');
    assertTrue(a.addStash(s1));
    s1.moveR(2, 2, 1, 9,'L');
   assertFalse(a.addStash(s1));
    s1.moveR(1, 9, 7, 12,'D');
    assertTrue(a.addStash(s1));
    a.dig(0, 9, "A");
    a.dig(5, 5, "A");
    draw.printMyBoard(a);
   assertFalse(a.move(0, 8, 4, 8, 'R'));
     assertFalse(a.move(6, 6, 2, 2, 'U'));

    s1 = new Stash('G',2,0,11);
    s1.putV();
    assertTrue(a.addStash(s1));
    assertTrue(a.move(0,11,7,15,'H'));
    draw.printMyBoard(a);
    
    
    
    
  }

}
