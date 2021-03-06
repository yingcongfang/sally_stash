package ece651;

public class Draw {


  public void printBoard(Board b) {
    System.out.print(getSource(b));
 }

 public void printOpBoard(Board b) {
   System.out.print(getOpSource(b));
 }

  public void printMyBoard(Board b) {
    System.out.print(getMySource(b));
 }
  
 public void printGame(Board me, Board op) {
   System.out.print("------------------------------------------------------------------------------------\n");
   String[] os = getOpSource(op).split("\n");
   String[] ms = getMySource(me).split("\n");
   for (int i = 0; i < os.length; i++) {
     System.out.print(ms[i]);
     System.out.print("                      ");
     System.out.println(os[i]);  
   }
   System.out.print("------------------------------------------------------------------------------------\n");
 }

  

  public String getSource(Board b) {
    //board after we place the stack
    StringBuffer sb = new StringBuffer();
    sb.append("This is your board:\n");
    sb.append("  ");
    for (int i = 0; i < b.getW();i++){
      if (i != 0)
        sb.append("|");
      sb.append(i);
    }
    sb.append("\n");
    for (int j = 0; j < b.getH(); j++) {
       sb.append((char)(65+j)+" ");
       for (int i = 0; i < b.getW();i++){
         if (i != 0)
           sb.append("|");
         sb.append(b.findLoc(i, j));
       }
       sb.append(" "+(char)(65+j)+"\n");
    }
    sb.append("  ");
    for (int i = 0; i < b.getW();i++){
      if (i != 0)
        sb.append("|");
     sb.append(i);
    }
    sb.append("\n");
    return sb.toString();
  }


  public String getMySource(Board b) {
    //player's own board, which can find the stack already digged
    StringBuffer sb = new StringBuffer();
    sb.append("This is your board:\n");
    sb.append("  ");
    for (int i = 0; i < b.getW();i++){
      if (i != 0)
        sb.append("|");
      sb.append(i);
    }
    sb.append("\n");
    for (int j = 0; j < b.getH(); j++) {
       sb.append((char)(65+j)+" ");
       for (int i = 0; i < b.getW();i++){
         if (i != 0)
           sb.append("|");
         sb.append(b.findMyself(i, j));
       }
       sb.append(" "+(char)(65+j)+"\n");
    }
    sb.append("  ");
    for (int i = 0; i < b.getW();i++){
      if (i != 0)
        sb.append("|");
     sb.append(i);
    }
    sb.append("\n");
    return sb.toString();
  }

  public String getOpSource(Board b) {
    //opponent's board
   StringBuffer sb = new StringBuffer();
    sb.append("This is your opponent's board:\n");
    sb.append("    ");
    for (int i = 0; i < b.getW();i++){
      if (i != 0)
        sb.append("|");
      sb.append(i);
    }
    sb.append("\n");
    for (int j = 0; j < b.getH(); j++) {
       sb.append((char)(65+j)+" ");
       for (int i = 0; i < b.getW();i++){
         if (i != 0)
           sb.append("|");
         sb.append(b.findDig(i, j));
       }
       sb.append(" "+(char)(65+j)+"\n");
    }
    sb.append("    ");
    for (int i = 0; i < b.getW();i++){
      if (i != 0)
        sb.append("|");
     sb.append(i);
    }
    sb.append("\n");
    return sb.toString();
  }
  
}
