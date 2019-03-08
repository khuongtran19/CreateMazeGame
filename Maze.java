package Project5;
import java.util.*;
import java.io.*;
public class Maze{
   int row;
   int column;
   String maze[][]=null;
   boolean done =false;
   public Maze(){
      row=41;
      column=41;
      maze=new String[row][column];
   }
   public void readMaze(){
      String mazeFile="Project5/Maze - Input.csv";
      BufferedReader br=null;
      String line ="";
      String split=",";
      try{
         br=new BufferedReader(new FileReader(mazeFile));
         br.readLine();
         int row=0;
         while((line = br.readLine()) !=null){
            String[] r= line.split(split);
            for(int column=0;column<r.length-1;column++){
               maze[row][column]=r[column+1];
            }
            row++;
         }
      }
      catch(FileNotFoundException e){
         e.printStackTrace();
      }
      catch(IOException e){
         e.printStackTrace();
      }
      finally{
         if(br !=null){
            try{
               br.close();
            }
            catch (IOException e){
               e.printStackTrace();
            }
         }
      }  
   }
   public void run(int x, int y){
      maze[x][y]="*";
      if(x==40&&y==17){
         done=true;
         return;
      }
      if(!done && x+1<maze.length && maze[x+1][y].equals("1")){
         run(x+1,y);
         if(!done)
            maze[x+1][y]="B";
      }
      if(!done && x-1<maze.length && maze[x-1][y].equals("1")){
         run(x-1,y);
         if(!done)
            maze[x-1][y]="B";
      }
      if(!done && y+1<maze.length && maze[x][y+1].equals("1")){
         run(x,y+1);
         if(!done)
            maze[x][y+1]="B";
      }
      if(!done && y-1<maze.length && maze[x][y-1].equals("1")){
         run(x,y-1);
         if(!done)
            maze[x][y-1]="B";
      }
   }
   public void print(){
      for(int row=0;row<maze.length;row++){
         for(int col=0;col<maze[0].length;col++){
            System.out.print(maze[row][col]+" ");
         }
         System.out.println();
      }
   }
   
   public static void main(String[]args){
      Maze m = new Maze();
      m.readMaze();
      m.run(0,27);
      m.print();
   } 
}