package gade12_rst;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Gade12_RST {

    public static JFrame main = new JFrame("Pro Dodger");

    public static Thread move;
    public static Thread move1;
        public static Thread move2;
    public static JPanel main1 = new JPanel();
    public static JLabel user = new JLabel();
         public static int points=0;
       public static   int displaycrash=0;
    public static int uxp;
    public static int uyp;
     public static int width;
    public static int height;
    public static int speed;
    public static int rRight;
    public static int rLeft;
    public static KeyListenerTester f1;

    public static class KeyListenerTester implements KeyListener {

        public KeyListenerTester() {

            main.addKeyListener(this);//assigns keylistene to frame

        }

        @Override
        public void keyTyped(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
// codes are executed based on estrictions
                if (uxp < rRight) {
                    uxp = uxp + speed;// moves the user by a specific number of pixels
                } else {
                    uxp = rRight - 1;// resets the location to comply with restriction
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gade12_RST.class.getName()).log(Level.SEVERE, null, ex);
                }
                user.setBounds(uxp, uyp, width, height); // repaints the player's gaphic
                main.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                if (uxp > rLeft) {
                    uxp = uxp - speed;// moves the user by a specific number of pixels
                } else {
                    uxp = rLeft + 1;// resets the location to comply with restriction
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gade12_RST.class.getName()).log(Level.SEVERE, null, ex);
                }
               user.setBounds(uxp, uyp, width, height);
                main.repaint();
            }

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
// the comments are the same as the one above
                if (uxp < rRight) {
                    uxp = uxp + speed;
                } else {
                    uxp = rRight - 1;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gade12_RST.class.getName()).log(Level.SEVERE, null, ex);
                }
             user.setBounds(uxp, uyp, width, height);
                main.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                if (uxp > rLeft) {
                    uxp = uxp - speed;
                } else {
                    uxp = rLeft + 1;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gade12_RST.class.getName()).log(Level.SEVERE, null, ex);
                }
                 user.setBounds(uxp, uyp, width, height);
                main.repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public static class Gamemode1 implements Runnable {

        @Override
        public void run() {

            int h = 400;
            int spaces = 0;
            int situation = 0;
            int k1 = 0;
              JButton score = new JButton();
            JLabel[] obstacles = new JLabel[3];
            Integer[] x_position = new Integer[3];
            Integer y_position[] = new Integer[3];
            JLabel background = new JLabel();
            randompic PR = new randompic("pics//",".jpg");// refer to a class to generate diffeent pics
            boolean p = false;
            graphics(obstacles, x_position, y_position, background,PR, score);// method for graphics
            main.addKeyListener(f1);// adds actionlistener to mainfame
            while (true) {
                k1++;
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gade12_RST.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (p == true || k1 == 1) {
                    for (int z = 0; z < obstacles.length; z++) {
                        obstacles[z].setVisible(true);
                    }

                    situation = RNG(100, 0);
// determines what is the situation (2 cars or one car interfering the user) - done by percents
                    if (situation >= 40) {
                        spaces = RNG(3, 0);
                        obstacles[spaces].setVisible(false);
                    } else if (situation < 40) {

                        for (int j = 0; j < obstacles.length; j++) {
                            obstacles[j].setVisible(false);

                        }
                        spaces = RNG(3, 0);
                        obstacles[spaces].setVisible(true);

                    }

                    h = RNG(1000, 300);
                }
// the following for loop moes the incoming cars across
                for (int k = 0; k < obstacles.length; k++) {

                    main.add(background);
                    y_position[k] = y_position[k] + 15;
                    obstacles[k].setBounds(x_position[k], y_position[k], 320, 264);
                    main.repaint();
                    p = false;
                    if (y_position[k] > 1000) {
                        p = true;
                       obstacles[k].setIcon(new ImageIcon(PR.getPic()));
                        y_position[k] = -1 * h;
                        obstacles[k].setBounds(x_position[k], y_position[k], 320, 264);
                    }
                    
                    if (y_position[k]>uyp+264){
                    displaycrash=0;// an index to stop the crash joptionpane from displaying
                    }
                }
                intersects(spaces, situation, obstacles,score);// checks for collisions
            }
        }

        // the method over here generates random numbers when passed a max and a min
        public int RNG(int max, int min) {
            Random j = new Random();
            int o = j.nextInt(max - min) + min;
            return o;

        }

        public void graphics(JLabel[] obstacles, Integer[] x_position, Integer[] y_position, JLabel background, randompic PR, JButton score) {
            // main.remove(main1);

            main1.setVisible(false);

            main.addKeyListener(f1);
            main.requestFocusInWindow();
// all the x-positions of incoming cars
            x_position[0] = 480;
            x_position[1] = 850;
            x_position[2] = 1250;

            // adding the incoming cars 
            for (int x = 0; x < obstacles.length; x++) {
                obstacles[x] = new JLabel();

                y_position[x] = -300;
                obstacles[x].setIcon(new ImageIcon(PR.getPic()));
                obstacles[x].setBounds(x_position[x], y_position[x], 320, 264);
                obstacles[x].setVisible(true);
                main.add(obstacles[x]);

            }
            //sets the user's position
            uxp = 810;
            uyp = 650;
           user.setBounds(uxp, uyp, width, height);
            user.setIcon(new ImageIcon("pics//1.jpg"));

            user.setVisible(true);
// the score box gaphics
              score.setLocation(0,0);
              score.setSize(140,140);
               score.setFont(new Font("Times New Roman", Font.PLAIN, 50));
           score.setVisible(true);
           score.setForeground(Color.RED);
           main.add(score);
            main.add(user);

            //JLabel background = new JLabel();
            background.setSize(2000, 1000);
            background.setIcon(new ImageIcon("pics\\background.png"));
            background.setVisible(true);
            main.add(background);
            main.repaint();
            main.setVisible(true);

        }

        public void intersects(int spaces, int situation, JLabel[] obstacles, JButton score) {
            Integer temp[] = new Integer[]{0, 0};
            Area areaA;
            Area areaB;
            Area areaC;
            areaA = new Area(user.getBounds());// gets the area of the user's graphic
            // the if statements recognizes which cars ae coming in order to check for collisions
            if (situation >= 40) {
                if (spaces == 0) {
                    temp[0] = 1;
                    temp[1] = 2;
                } else if (spaces == 1) {
                    temp[0] = 0;
                    temp[1] = 2;
                } else if (spaces == 2) {
                    temp[0] = 1;
                    temp[1] = 0;
                }
                //the following two lines of get the area of the incoming cars
                areaB = new Area(obstacles[temp[0]].getBounds());
                areaC = new Area(obstacles[temp[1]].getBounds());
                // the if statement check if there's an intersection and executes all what's necessary in that case
                if (areaA.intersects(areaB.getBounds2D()) == true || areaA.intersects(areaB.getBounds2D()) == true) {
                    if(displaycrash==0){
                    JOptionPane.showMessageDialog(null, "crash");
                    displaycrash++;
                    points++;
                    score.setText(String.valueOf(points));
                    main.repaint();
                    }
                   
               
                }

            } else if (situation < 40) {
                areaB = new Area(obstacles[spaces].getBounds());
                if (areaA.intersects(areaB.getBounds2D()) == true) {
                   if(displaycrash==0){
                    JOptionPane.showMessageDialog(null, "crash");
                       displaycrash++;
                   points++;
                   score.setText(String.valueOf(points));
                    main.repaint();}
               
                 
                }
            }

        }
    }

    public static class Gamemode2 implements Runnable {

        @Override
        public void run() {
            JLabel background = new JLabel();
            JLabel objects[] = new JLabel[150];
            Integer xval[] = new Integer[150];
            Integer yval[] = new Integer[150];
            Integer oyval[] = new Integer[150];
            JButton score = new JButton();
            position(xval, yval, oyval);// genrates random values for the incoming boxes
            graphics(objects, xval, yval,score);
            main.addKeyListener(f1);

            // moes the icoming boxes
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gade12_RST.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int o = 0; o < xval.length; o++) {

                    yval[o] += 2;
                    objects[o].setBounds(xval[o], yval[o], 30, 10);

                    if (yval[o] > 1000) {
                        yval[o] = oyval[o];
                        xval[o] = RNG(1950, 0);// randomizes the x positions of the incoming boxes
                        
                        
                    }
                    
                  
                    main.repaint();

                }
intersects(objects,score);
            }
        }

        public void position(Integer[] xval, Integer[] yval, Integer[] oyval) {
            int temp = 0;
            int z = 1;
            int ymax = 100;
            int ymin = 0;
            // generates the positions of the boxes with y-values that ae different every 15 boxes
            for (int y = 0; y < yval.length; y++) {

                if (y % 15 == 0) {

                    temp = -1 * RNG(ymax + (z * 450), ymin + (z * 450));
                    yval[y] = temp;
                    oyval[y] = temp;
                    z++;
                } else {
                    yval[y] = temp;
                    oyval[y] = temp;
                }

                xval[y] = RNG(1950, 0);

            }

        }

        public int RNG(int max, int min) {
            Random j = new Random();
            int o = j.nextInt(max - min) + min;
            return o;

        }

        public void graphics(JLabel[] objects, Integer[] xval, Integer[] yval, JButton score) {

            main1.setVisible(false);
            main.addKeyListener(f1);

            for (int j = 0; j < objects.length; j++) {
                objects[j] = new JLabel();
                objects[j].setOpaque(true);
                objects[j].setBackground(new Color (RNG(255,0),RNG(255,0),RNG(255,0)));
                objects[j].setVisible(true);
                main.add(objects[j]);

            }
            uxp = 1000;
            uyp = 800;

           user.setBounds(uxp, uyp, width, height);
            user.setIcon(new ImageIcon("pics\\k.jpg"));

            main.repaint();
            user.setVisible(true);
                   score.setLocation(0,0);
                     score.setSize(140,140);
                     score.setFont(new Font("Times New Roman", Font.PLAIN, 40));
           score.setVisible(true);
           score.setForeground(Color.RED);
           main.add(score);

         
            main.add(user);

           
            main.repaint();
            main.setVisible(true);

        }
// this method checks if there's a collision with the objects
        public void intersects(JLabel objects[], JButton score) {
             
            Area areaA;
            Area areaB[]= new Area[150];
           // areaB[0].reset();
            areaA = new Area(user.getBounds());

            for (int c=0;c<areaB.length;c++){
               
             areaB[c] = new Area(objects[c].getBounds());
            
             if (areaA.getBounds().intersects(areaB[c].getBounds2D()) == true){
       points--;
       score.setText(String.valueOf(points));
}

             }
            }}
            
            
    
//            
//            

    public static class Gamemode3 implements Runnable {

        @Override
        public void run() {
          
            Color BC[] = new Color[10];
            JLabel background = new JLabel();
            JButton cboxes[] = new JButton[10];
            JButton player = new JButton();
            Integer x[] = new Integer[10];
      
            Integer y[] = new Integer[10];
            JButton score = new JButton("0");
        
            int match;
              main.addKeyListener(f1);
      
            for (int j = 0; j < cboxes.length; j++) {

                cboxes[j] = new JButton();
                  x[j] = 0 + (j * 200);// x-position generator with 200 space in between them
                y[j] = -500;// y-position initials
                cboxes[j].setBounds(x[j],y[j],200,30);
              
                BC[j]=coloringtool();// obtains RGB for colors
                cboxes[j].setBackground(BC[j]);//sets the colo to RGB
            }
            graphics(cboxes, x, y, background,score);
            match=RNG(cboxes.length,0);// generates a random number, that random number is the spot where the user has to go to avoid a crash it has the same color as the user
            user.setBackground(BC[match]);
        // moes the colored boxed
            while (true){
                try {
                    Thread.sleep(60);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gade12_RST.class.getName()).log(Level.SEVERE, null, ex);
                }
            for (int k =0;k<cboxes.length;k++){
                y[k]+=20;
            cboxes[k].setBounds(x[k],y[k],200,20);
            
            
            if (y[k]>1000){
            y[k]=-500;
            cboxes[k].setBounds(x[k],y[k],200,20);
            BC[k]=coloringtool();
            cboxes[k].setBackground(BC[k]);
            match=RNG(cboxes.length,0);
            user.setOpaque(true);
            user.setBackground(BC[match]);
           displaycrash=0;
            }
          
            main.repaint();
                    try {
                        intersects(match,cboxes,score);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Gade12_RST.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
             
            
            
            
            
            
            }
           
        }

        public Color coloringtool() {

         
                int R = RNG(255, 0);
                int G = RNG(255, 0);
                int B = RNG(255, 0);
Color result = new Color(R,G,B);
              return result;
                
            
        }

        public int RNG(int max, int min) {
            Random j = new Random();
            int o = j.nextInt(max - min) + min;
            return o;

        }

        public void graphics(JButton[] cboxes, Integer[] x, Integer[] y, JLabel background, JButton score) {

            main1.setVisible(false);
           
            main.requestFocus();

            for (int j = 0; j < cboxes.length; j++) {
                cboxes[j].setVisible(true);
                main.add(cboxes[j]);
            }
            uxp = 1000;
            uyp = 600;

           user.setBounds(uxp, uyp, width, height);
           user.setOpaque(true);
           user.setVisible(true);
          // user.setIcon(new ImageIcon("pics\\1.jpg"));
            main.repaint();
           
            main.add(user);
 score.setSize(140,140); 
           score.setLocation(0,0);
           score.setBackground(Color.BLACK);
                   score.setFont(new Font("Times New Roman", Font.PLAIN, 40));
           score.setVisible(true);
           main.add(score);
          
            
            background.setSize(2000, 1000);
            background.setIcon(new ImageIcon("pics\\black.png"));
            background.setVisible(true);
            main.add(background);
            main.repaint();
            main.setVisible(true);

        }

        public void intersects(int match, JButton[] cboxes,  JButton Score) throws InterruptedException {
        boolean checker[]=new boolean[10];
        int counter=0;
        int collision=80;
  
        
        Area A_cboxes[]=new Area[10];
        Area A_user = new Area(user.getBounds());
    
for (int h=0; h<cboxes.length;h++){
A_cboxes[h] = new Area(cboxes[h].getBounds());
checker[h]=A_user.intersects(A_cboxes[h].getBounds2D());
//System.out.println( h + "-"+ checker[h]);
if (checker[h]==true){
counter++;
collision=h;
}

}
// the if-statements checks if there's a collison with other objects and the counter is just a variable that indicates that the entire system underwent a collision
if (match!=collision && counter!=0){
  
 if (displaycrash==0){
JOptionPane.showMessageDialog(null, "crash");
 points=points-30;
  Score.setForeground(Color.RED);
 Score.setText(String.valueOf(points));
 Score.repaint();
 main.repaint();
 
 }
 displaycrash++; 
 
}else if (match==collision){// adds points in case of a collision 
 points++;
 Score.setForeground(Color.GREEN);
 Score.setText(String.valueOf(points));
 Score.repaint();
 main.repaint();
}

        }

    }

    public static void main(String[] args) throws InterruptedException {

       // threads that execute the game modes
            move = new Thread(new Gamemode1());
            move1 = new Thread(new Gamemode2());
             move2 = new Thread(new Gamemode3());

        
        f1 = new KeyListenerTester();
        main.setSize(2000, 1000);
        main.setLayout(null);
      mainmenu();
    }

    public static void mainmenu() throws InterruptedException {
// the actions listeners are for different games and they initiialize the featues of the game speed and everything
        ActionListener AL1;
        AL1 = (ActionEvent e) -> {
            rRight = 1250;
            rLeft = 455;
            speed = 40;
            width = 320;
            height = 264;

            move.start();
            main.requestFocusInWindow();
        };

        ActionListener AL2;
        AL2 = (ActionEvent e) -> {
            rRight = 1980;
            rLeft = 0;
            speed = 10;
            width =32;
            height = 10;
            move1.start();
            main.requestFocusInWindow();
        };

        ActionListener AL3;
        AL3 = (ActionEvent e) -> {
       rRight = 1980;
            rLeft = 0;
            speed = 20;
               width =60;
            height = 60;
        move2.start();
         main.requestFocusInWindow();
        };

        main1.setSize(2000, 1000);
        main1.setLocation(0, 0);
        main1.setLayout(null);
        main.add(main1);
        JLabel bc = new JLabel();
        bc.setSize(2000, 1000);
        bc.setBackground(Color.black);
        main1.add(bc);

        JLabel title = new JLabel("                Welcome to Pro-Dodger");
        title.setLocation(0, 0);
        title.setSize(2000, 200);
        title.setFont(new Font("Times New Roman", Font.PLAIN, 107));
        title.setForeground(Color.red);
        title.setBackground(Color.yellow);
        main1.add(title);

        JButton carmode = new JButton("Car Game");
        carmode.setSize(300, 200);
        carmode.setLocation(300, 400);
        carmode.addActionListener(AL1);
        main1.add(carmode);

        JButton Dmode = new JButton("Dodger Game");
        Dmode.setSize(300, 200);
        Dmode.setLocation(700, 400);
        Dmode.addActionListener(AL2);
        main1.add(Dmode);
        
        JButton Colormode = new JButton("Color Game");
        Colormode.setSize(300, 200);
        Colormode.setLocation(1100, 400);
        Colormode.addActionListener(AL3);
        main1.add(Colormode);

        main.setVisible(true);
        main1.setVisible(true);

    }

}
