/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.view;

import ch.hslu.prg2.controlling.Controller;
import ch.hslu.prg2.controlling.GameVariant;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import ch.hslu.prg2.model.GameField;
import ch.hslu.prg2.model.IGameField;
import ch.hslu.prg2.model.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Urs Müller
 */
public class GUI extends JFrame implements MouseInputListener, ActionListener {

    private GameField gameField;
    private Player player1, player2;

    //Layout MenuBar
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenu menuGame = new JMenu("online Game");
    JMenu menuHelp = new JMenu("Help");
    JMenuItem miFileNew = new JMenu("New Game");
    JMenuItem miFileNewEasy = new JMenuItem("Easy");
    JMenuItem miFileNewHard = new JMenuItem("Hard");
    JMenuItem miFileLoad = new JMenuItem("Load Game");
    JMenuItem miFileSave = new JMenuItem("Save Game");
    JMenuItem miFileExit = new JMenuItem("Exit");
    JMenuItem miFileRepaint = new JMenuItem("repaint");
    JMenuItem miGameHost = new JMenuItem("Host a Game");
    JMenuItem miGameJoin = new JMenuItem("join a Game");
    JMenuItem miUDPGameHost = new JMenuItem("Host a UDP Game");
    JMenuItem miUDPGameSearch = new JMenuItem("Search a UDP Game");
    JMenuItem miHelpRules = new JMenuItem("Rules");
    JMenuItem miHelpAbout = new JMenuItem("About");

    //Panel 
    private JPanel display1 = new JPanel(new BorderLayout());
    private JPanel placeholder1;
    private JPanel placeholder2;
    private JPanel display2 = new JPanel(new BorderLayout());
    private JPanel placeholder3 = new JPanel(new GridLayout(1, 7));
    private JPanel playBoard;

    //Frame
    int xFramePos;
    int yFramePos;
    //Playboard
    private int rows;
    private int cols;
    private int size;
    private int boxSize;
    private Color dotColor = Color.BLACK;
    private int[][] lines;

    //Fonts
    private Font playerFont = new Font(Font.DIALOG, 1, 16);

    // gameplay instructions
    final String HOWTOPLAYTEXT = "How To Play\r\n" + "\r\n"
            + "The game consists of a field of dots.  Take turns with the\r\n"
            + "computer adding lines between the dots.  Complete a box to\r\n"
            + "get another turn.  Your boxes will show O.  The computer's\r\n"
            + "boxes get X.  Complete the most boxes to win!\r\n";

    // about box text
    final String ABOUTTEXT = "Dots and Boxes is a Java program written by "
            + "Jordan Klaus, Müller Urs, Rossacher Patrick, Schärer Lucius, Ruckli Adrian\r\n"
            + "\r\n"
            + "This was a project in PRG2 Hochschule Luzern FS15\r\n"
            + "\r\n";
    private Controller controller;

    /**
     * Konstruktor des GUI's
     *
     * @param gameField
     * @param controller
     * @param player1
     * @param player2
     * @param gameVar
     */
    public GUI(GameField gameField, Controller controller, Player player1, Player player2, GameVariant gameVar) {
        //intial Frame
        super("Dots and Boxes");

        //GameField dimensionieren
        this.gameField = gameField;
        this.controller = controller;
        this.player1 = player1;
        this.player2 = player2;
        this.size = (gameField.getMatrixSize() - 1) / 2;
        this.rows = size;
        this.cols = size;
        this.boxSize = 550 / cols;

        //playboard  und Frame zeichnen
        paintPlayBoard();

        //MenuBar
        setMenuBar();

        //ActionListner registrieren
        register();

        //Sichtbar machen
        setVisible(true);
    }

    public void startGame() {

    }

    /*    public GUI() {
     //intial Frame
     super("Dots and Boxes");
    
     //playboard  und Frame zeichnen
     this.size = 5;
     this.rows = size;
     this.cols = size;
     boxSize = 550 / cols;
     paintPlayBoard();
    
     //MenuBar
     setMenuBar();
    
     //ActionListner registrieren
     register();
    
     //Sichtbar machen
     //pack();
     setVisible(true);
    
     }*/
    /**
     * Zeichnet das Spielfeld neu
     */
    public void update() {

        this.playBoard.repaint();
        this.placeholder1.repaint();
        this.placeholder2.repaint();

    }

    /**
     * Zeichnet das ganze Spielfeld
     */
    private void paintPlayBoard() {

        this.playBoard = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                placeholder1.repaint();
                placeholder2.repaint();
                playBoard.repaint();

                //Rendering einstellen
                Graphics2D g2 = (Graphics2D) g;
                RenderingHints rh = new RenderingHints(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHints(rh);

            }
        };

        this.placeholder1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setFont(playerFont);
                g.setColor(Color.black);
                g.drawString("Red Player", 20, 30);
                ImageIcon playerIcon1 = new ImageIcon("images\\player_red.png");
                Image icon1 = playerIcon1.getImage();
                g.drawImage(icon1, 10, 60, rootPane);
                g.drawString(player1.getName(), 20, 50);
                g.drawString("Score: " + player1.getScore(), 20, 220);
                if(controller.isGameOver()){
                    if(player1.getScore() > player2.getScore()){
                        g.setColor(Color.red);
                        g.drawString("Winner!", 20, 200);
                    }
                }
                else
                {
                    
                    if(player1.getIsActive())
                    {
                        g.setColor(Color.red);
                        g.drawString("Is on turn", 20, 200);
                    }
                }

            }
        };

        this.playBoard = new JPanel() {

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                super.setBackground(Color.WHITE);

                for (int y = 0; y <= rows + 1; y++) {
                    for (int x = 0; x <= cols + 1; x++) {

                        //Draw Senkrechte Striche
                        if ((x < cols + 1) && y < rows) {
                            //Striche mit der richtigen Farbe füllen
                            //Player player = GameField.getGridPosition(x, y);
                            int xcol;
                            int yrow;
                            //Linie finden
                            yrow = 2 * y + 1;
                            xcol = 2 * x;

                            if ((xcol % 2 == 0) && !(yrow % 2 == 0)) {
                                Color col2 = gameField.getLineColor(yrow, xcol);
                                g.setColor(col2);
                            }

                            //g.setColor(Color.LIGHT_GRAY);
                            g.fillRect(((int) x * boxSize) + 13 * boxSize / 100, ((int) (y * boxSize)) + 35 * boxSize / 100, 5 * boxSize / 100, 70 * boxSize / 100);
                        }

                        //Draw Waagrechte Striche
                        if ((y < rows + 1) && x < cols) {
                            //Striche mit der richtigen Farbe füllen
                            //Player player = GameField.getGridPosition(x, y);

                            int xcol;
                            int yrow;
                            //Linie finden

                            yrow = 2 * y;
                            xcol = 2 * x + 1;

                            if (!(xcol % 2 == 0) && (yrow % 2 == 0)) {
                                Color col1 = gameField.getLineColor(yrow, xcol);
                                g.setColor(col1);
                            }
                            //g.setColor(Color.LIGHT_GRAY);

                            g.fillRect(((int) x * boxSize) + 33 * boxSize / 100,
                                    ((int) (y * boxSize)) + 18 * boxSize / 100, 70 * boxSize / 100, 5 * boxSize / 100);
                        }
                    }
                }

                //Draw Boxes
                //Die Boxen zeichen und jenach Spielverlauf einfärben X O
                for (int y = 0; y < rows; y++) {
                    for (int x = 0; x < cols; x++) {

                        //Kreise mit der richtigen Farbe füllen
                        //Player player = GameField.getGridPosition(x, y);
                        int xcol;
                        int yrow;
                        //Linie finden

                        yrow = 2 * y + 1;
                        xcol = 2 * x + 1;

                        if (!(xcol % 2 == 0) && !(yrow % 2 == 0)) {
                            Color col = gameField.getBoxColor(yrow, xcol);
                            g.setColor(col);
                            Player owner = gameField.getBoxOwner(yrow, xcol);
                         

                            if (player1 == owner) {

                                g.fillArc(((int) x * boxSize) + 30 * boxSize / 100, ((int) (y * boxSize)) + 30 * boxSize / 100, 70 * boxSize / 100, 70 * boxSize / 100, 0, 360);
                                g.setColor(getBackground());
                                // east
                                g.fillArc(((int) x * boxSize) + 36 * boxSize / 100, ((int) (y * boxSize)) + 30 * boxSize / 100, 70 * boxSize / 100, 70 * boxSize / 100, -45, 90);
                                // west
                                g.fillArc(((int) x * boxSize) + 24 * boxSize / 100, ((int) (y * boxSize)) + 30 * boxSize / 100, 70 * boxSize / 100, 70 * boxSize / 100, 135, 90);
                                // north
                                g.fillArc(((int) x * boxSize) + 30 * boxSize / 100, ((int) (y * boxSize)) + 30 * boxSize / 100 - 6 * boxSize / 100, 70 * boxSize / 100, 70
                                        * boxSize / 100, 45, 90);
                                // south
                                g.fillArc(((int) x * boxSize) + 30 * boxSize / 100, ((int) (y * boxSize)) + 30 * boxSize / 100 + 6 * boxSize / 100, 70 * boxSize / 100, 70
                                        * boxSize / 100, -135, 90);
                            } else {

                                g.fillArc(((int) x * boxSize) + 30 * boxSize / 100, ((int) (y * boxSize)) + 30 * boxSize / 100, 70 * boxSize / 100, 70 * boxSize / 100, 0, 360);
                                g.setColor(getBackground());
                                g.fillArc(((int) x * boxSize) + 30 * boxSize / 100 + 5
                                        * boxSize / 100, ((int) (y * boxSize)) + 30 * boxSize / 100 + 5 * boxSize / 100, 60
                                        * boxSize / 100, 60 * boxSize / 100, 0, 360);
                            }
                        }
                    }
                }

                // DRAW DOTS
                g.setColor(dotColor);
                for (int y = 0; y < rows + 1; y++) {
                    for (int x = 0; x < cols + 1; x++) {

                        //Kreise mit der richtigen Farbe füllen
                        g.setColor(dotColor);

                        g.fillArc(((int) x * boxSize) + (10 * boxSize / 100), ((int) (y * boxSize)) + 15 * boxSize / 100, 10 * boxSize / 100, 10 * boxSize / 100, 0, 360);
                    }
                }

                //bei Spielende das "Game over" - Fenster zeichnen
                if (controller.isGameOver()) {
                    g.setColor(Color.black);
                    g.drawRoundRect(49, 149, 602, 202, 8, 8);
                    g.setColor(new Color(181, 181, 181, 200));
                    g.fillRoundRect(50, 150, 600, 200, 8, 8);
                    Font f = new Font(Font.SANS_SERIF, 1, 100);
                    g.setFont(f);
                    g.setColor(Color.red);
                    g.drawString("Game Over", 60, 250);
                    Font info = new Font(Font.DIALOG, 1, 40);
                    g.setFont(info);
                    g.setColor(Color.black);
                    String WinnerName= " ";
                    if (player1.getScore() > player2.getScore()) {
                    WinnerName = player1.getName();
                    } else {
                    WinnerName = player2.getName();
                    }
                    g.drawString("  Winner is: " + WinnerName, 60, 300);
                    placeholder1.repaint();
                    placeholder2.repaint();
                }

            }

        };

        this.placeholder2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setFont(playerFont);
                g.setColor(Color.black);
                g.drawString("Blue Player", 20, 30);
                ImageIcon playerIcon2;

                playerIcon2 = new ImageIcon("images\\player_blue.png");

                Image icon1 = playerIcon2.getImage();
                g.drawImage(icon1, 10, 60, rootPane);
                g.drawString(player2.getName(), 20, 50);
                g.drawString("Score: " + player2.getScore(), 20, 220);
                if(controller.isGameOver()){
                    if(player1.getScore() < player2.getScore()){
                        g.setColor(Color.red);
                        g.drawString("Winner!", 20, 200);
                    }
                }
                else
                {
                    if(player2.getIsActive())
                    {
                        g.setColor(Color.red);
                        g.drawString("Is on turn", 20, 200);
                    }
                }
            }
        };

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setResizable(false);

        //Frame Location
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        xFramePos = (dim.width - super.getSize().width) / 2;

        yFramePos = (dim.height - super.getSize().height) / 2;
        super.setLocation(xFramePos, yFramePos);

        //set Icon 
        ImageIcon logoIcon = new ImageIcon("images\\logo.png");
        Image logo = logoIcon.getImage();
        super.setIconImage(logo);

        //set Layout Manager
        setLayout(new BorderLayout(5, 5));

        //Display 1 (Panel aussen)
        add(display1, BorderLayout.CENTER);
        this.display1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.display1.add(placeholder1, BorderLayout.WEST);
        this.placeholder1.setPreferredSize(new Dimension(150, 500));
        this.display1.add(display2, BorderLayout.CENTER);
        this.display2.setPreferredSize(new Dimension(600, 500));
        this.display1.add(placeholder2, BorderLayout.EAST);
        this.placeholder2.setPreferredSize(new Dimension(150, 500));

        //Display 2 (Panel innen)    
        this.display2.add(placeholder3, BorderLayout.NORTH);
        this.placeholder3.setPreferredSize(new Dimension(600, 50));

        //Play Board
        this.display2.add(playBoard, BorderLayout.CENTER);
        this.playBoard.setPreferredSize(new Dimension(600, 400));
        this.playBoard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    }

    /**
     * Setzt das Menu
     */
    private void setMenuBar() {

        //File Menu
        this.menuFile.add(miFileNew);
        miFileNew.addActionListener(this);
        this.menuFile.add(miFileLoad);
        miFileLoad.addActionListener(this);
        this.menuFile.add(miFileSave);
        miFileSave.addActionListener(this);
        this.menuFile.addSeparator();
        this.menuFile.add(miFileExit);
        miFileExit.addActionListener(this);
        this.menuFile.add(miFileRepaint);
        miFileRepaint.addActionListener(this);
        this.menuBar.add(menuFile);

        //Onlinegame Menu
        this.menuGame.add(miGameHost);
        this.menuGame.add(miGameJoin);
        this.menuGame.add(miUDPGameHost);
        this.menuGame.add(miUDPGameSearch);
        this.menuBar.add(menuGame);
        //Help Menu
        this.menuHelp.add(miHelpAbout);
        miHelpAbout.addActionListener(this);
        this.menuHelp.add(miHelpRules);
        miHelpRules.addActionListener(this);
        this.menuBar.add(menuHelp);

        setJMenuBar(menuBar);
    }

    /**
     * Registriert die MouseListener
     */
    private void register() {

        // listen to own mouse input
        this.addMouseListener(this);
        //this.addMouseMotionListener(this);

    }

    /*    public static void main(String[] Args) {
    
     GUI a = new GUI();
     }*/
    /**
     * MouseClickListener: Fangt den MousEvent e auf und gibt die x / y Position
     * an getNearest weiter.
     *
     * @param e MousClickEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int nearx, neary;
        // lookup line nearest to the mouse pointer
        getNearest(e.getX(), e.getY());
        // pass the event to state machine
        //Controller.mousePressed(nearx, neary);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // lookup line nearest to the mouse pointer

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Sucht nach die nächste Linie und gibt diese an den Controller mit move()
     * weiter.
     *
     * @param x X Position der Maus
     * @param y Y Posiiotion der Maus
     */
    private int getNearest(int x, int y) {

        // find the mouse position relative to the field origin
        x -= xFramePos - 17;
        y -= xFramePos - 66;
        // mouse is over the box at this row and column
        int col = x / boxSize;
        int row = y / boxSize;

        // clamp point into field
        if (col < 0) {
            col = 0;
        }
        if (col >= this.cols) {
            col = this.cols - 1;
        }
        if (row < 0) {
            row = 0;
        }
        if (row >= this.rows) {
            row = this.rows - 1;
        }

        // the nearest box
        //Box nearestBox = this.box[col][row];
        // find mouse position relative to the box's origin
        x -= boxSize * col;
        y -= boxSize * row;

        // Box finden
        if (col == 0) {
            col = 1;
        } else {
            col += col + 1;
        }
        if (row == 0) {
            row = 1;
        } else {
            row += row + 1;
        }

        //Linie finden
        int lineCol = 0;
        int lineRow = 0;
        if (x < boxSize / 8) {
            lineCol = col - 1;
        } else if (x > 7 * boxSize / 8) {
            lineCol = col + 1;
        } else {
            lineCol = col;
        }
        if (y < boxSize / 8) {
            lineRow = row - 1;
        } else if (y > 7 * boxSize / 8) {
            lineRow = row + 1;
        } else {
            lineRow = row;
        }

        //Boxen und Punkte ignorieren
        if ((x % 2 == 0) && (y % 2 == 0)) {
            return -1;
        }
        if (!(x % 2 == 0) && !(y % 2 == 0)) {
            return -1;
        } // Infos an Gamemanager weitergeben
        else {
            System.out.println("line: " + lineRow + "y  " + lineCol + "x");
            this.controller.move(lineRow, lineCol, this);
            return 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // New Game menu item was selected
        if (e.getActionCommand().equals("New Game")) {
            // show modal new game dialog box
            //newGameDialog.showDialog();
            //new Game
            this.controller.startGame(2, 4, "Blue", "Red");
        } // How to Play menu item was selected
        else if (e.getActionCommand().equals("Rules")) {
            // show the instructional modal dialog box
            JOptionPane.showMessageDialog(this, HOWTOPLAYTEXT, "How To Play",
                    JOptionPane.PLAIN_MESSAGE);
        } // About menu item was selected
        else if (e.getActionCommand().equals("About")) {
            // show the modal about box
            JOptionPane.showMessageDialog(this, ABOUTTEXT,
                    "About Dots and Boxes", JOptionPane.PLAIN_MESSAGE);
        } // Exit menu item was selected
        else if (e.getActionCommand().equals("Exit")) {
            this.dispose();
        } else if (e.getActionCommand().equals("repaint")) {
            this.dotColor = Color.ORANGE;
            this.playBoard.repaint();

        }
    }

    public void setPlayer(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

}
