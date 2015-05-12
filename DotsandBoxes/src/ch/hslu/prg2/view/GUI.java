/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.view;

import ch.hslu.prg2.controlling.Controller;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import ch.hslu.prg2.model.GameField;
import ch.hslu.prg2.model.IGameField;
import ch.hslu.prg2.model.Player;

/**
 *
 * @author Urs Müller
 */
public class GUI extends JFrame {

    private GameField gameField;
    private Player Player;

    //Layout MenuBar
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenu menuGame = new JMenu("online Game");
    JMenu menuHelp = new JMenu("Help");
    JMenu miFileNew = new JMenu("New Game");
    JMenuItem miFileNewEasy = new JMenuItem("Easy");
    JMenuItem miFileNewHard = new JMenuItem("Hard");
    JMenuItem miFileLoad = new JMenuItem("Load Game");
    JMenuItem miFileSave = new JMenuItem("Save Game");
    JMenuItem miFileExit = new JMenuItem("Exit");
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

    //Playboard
    private int rows;
    private int cols;
    private int dotSize;

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
            + "This was a project in PRG2 \r\n"
            + "Hochschule Luzern \r\n"
            + "FS15\r\n"
            + "\r\n";
    private Controller controller;

    /**
     * Konstruktor des GUI's
     *
     * @param model
     * @param gameController
     * @param controller
     * @param Player
     * @param gameVariant
     */
    public GUI(GameField gameField, Controller controller, Player Player) {
        //intial Frame
        super("Dots and Boxes");

        // closing the main window should dispose of it, allowing VM to exit
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.gameField = gameField;
        this.controller = controller;
        this.Player = Player;

        //playboard  und Frame zeichnen
        paintPlayBoard();

        //MenuBar
        setMenuBar();

        //ActionListner registrieren
        register();

        //Sichtbar machen
        //pack();
        setVisible(true);
    }

    public GUI() {
        //intial Frame
        super("Dots and Boxes");

        // closing the main window should dispose of it, allowing VM to exit
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //playboard  und Frame zeichnen
        this.rows = 6;
        this.cols = 6;
        paintPlayBoard();

        //MenuBar
        setMenuBar();

        //ActionListner registrieren
        register();

        //Sichtbar machen
        //pack();
        setVisible(true);

    }

    public void setPlayerColor(Player Player) {
        this.Player = Player;
    }

    public void update() {

        playBoard.repaint();

    }

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
                g.drawImage(icon1, 10, 50, rootPane);

            }
        };

        this.playBoard = new JPanel() {
            private final int lineThickness = 0x2;
            private Color dotColor = Color.BLACK;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                 
                //Draw Senkrechte Striche
                for (int y = cols - 1; y > 0; y--) {
                    for (int x = 0; x < rows; x++) {

                        //Kreise mit der richtigen Farbe füllen
                        //Player player = GameField.getGridPosition(x, y);
                        g.setColor(Color.LIGHT_GRAY);

                        g.fillRect(((int) x * 100) + 13, ((int) (y * -100) + 500) + 35, 5, 70);
                    }
                }
                //Draw Waagrechte Striche 
                for (int y = cols; y >= 0; y--) {
                    for (int x = 0; x < rows - 1; x++) {

                        //Kreise mit der richtigen Farbe füllen
                        //Player player = GameField.getGridPosition(x, y);
                        g.setColor(Color.LIGHT_GRAY);

                        g.fillRect(((int) x * 100) + 33, ((int) (y * -100) + 500) + 18, 70, 5);
                    }
                }

                //Draw Boxes
                g.setColor(this.dotColor);
                //Die Boxen zeichen und jenach Spielverlauf einfärben X O
                for (int y = cols - 1; y > 0; y--) {
                    for (int x = 0; x < rows - 1; x++) {
                        
                        boolean redplayer = false;
                        //Kreise mit der richtigen Farbe füllen
                        //Player player = GameField.getGridPosition(x, y);
                        if (redplayer) {
                            g.setColor(Color.red);

                            g.fillArc(((int) x * 100) + 30, ((int) (y * -100) + 500) + 30, 70, 70, 0, 360);
                            g.setColor(getBackground());
                            g.fillArc(((int) x * 100) + 35, ((int) (y * -100) + 500) + 35, 60, 60, 0, 360);
                        }
                        else{
                        
                            g.setColor(Color.red);

                            g.fillArc(((int) x * 100) + 30, ((int) (y * -100) + 500) + 30, 70, 70, 0, 360);
                            g.setColor(getBackground());
                            g.fillArc(((int) x * 100) + 35, ((int) (y * -100) + 500) + 35, 60, 60, 0, 360);
                        }
                    }
                }

                // DRAW DOTS
                g.setColor(this.dotColor);
		// half the dot size
                //Die Kreise zeichen und jenach Spielverlauf einfärben
                for (int y = cols; y >= 0; y--) {
                    for (int x = 0; x < rows; x++) {

                        //Kreise mit der richtigen Farbe füllen
                        //Player player = GameField.getGridPosition(x, y);
                        g.setColor(Color.black);

                        g.fillArc(((int) x * 100) + 10, ((int) (y * -100) + 500) + 15, 10, 10, 0, 360);
                    }
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
                g.drawImage(icon1, 10, 50, rootPane);

            }
        };

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setResizable(false);

        //Frame Location
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - super.getSize().width) / 2;
        int y = (dim.height - super.getSize().height) / 2;
        super.setLocation(x, y);

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

    private void setMenuBar() {

        this.menuFile.add(miFileNew);
        this.miFileNew.add(miFileNewEasy);
        this.miFileNew.add(miFileNewHard);
        this.menuFile.add(miFileLoad);
        this.menuFile.add(miFileSave);
        this.menuFile.addSeparator();
        this.menuFile.add(miFileExit);
        this.menuBar.add(menuFile);

        this.menuGame.add(miGameHost);
        this.menuGame.add(miGameJoin);
        this.menuGame.add(miUDPGameHost);
        this.menuGame.add(miUDPGameSearch);
        this.menuBar.add(menuGame);

        this.menuHelp.add(miHelpAbout);
        this.menuBar.add(menuHelp);

        setJMenuBar(menuBar);
    }

    private void register() {

    }

    public static void main(String[] Args) {

        GUI a = new GUI();
    }

}
