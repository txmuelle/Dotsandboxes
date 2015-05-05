/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.view;

import ch.hslu.prg2.controlling.GameController;
import ch.hslu.prg2.controlling.GameManager;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import ch.hslu.prg2.controlling.IGameManager;
import ch.hslu.prg2.controlling.IPlayerActionController;
import ch.hslu.prg2.model.GameModel;
import ch.hslu.prg2.model.IGameModelInformer;
import ch.hslu.prg2.model.IModelObserver;
import ch.hslu.prg2.model.PlayerColor;
import ch.hslu.prg2.model.GameVariant;

/**
 *
 * @author Urs Müller
 */
public class GUI extends JFrame implements IModelObserver {

    private IGameModelInformer gameModel;
    private final IPlayerActionController playerActionController;
    private final IGameManager gameManager;
    private final GameVariant gameVariant;
    private PlayerColor uiPlayerColor;

    //Layout MenuBar
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenu menuGame = new JMenu("online Game");
    JMenu menuHelp = new JMenu("Help");
    JMenu miFileNew = new JMenu("New Game");
    JMenuItem miFileNewEasy = new JMenuItem("Easy");
    JMenuItem miFileNewSimple = new JMenuItem("Simple");
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
    
    //Fonts
    private Font playerFont = new Font(Font.DIALOG, 1, 16);
 
    

    
     /**
     * Konstruktor des GUI's
     * @param gameModel
     * @param playerActionController
     * @param gameManager
     * @param uiPlayerColor The color of the human player that uses this GUI.
     * @param gameVariant
     */
    public GUI(IGameModelInformer gameModel, IPlayerActionController playerActionController, IGameManager gameManager, PlayerColor uiPlayerColor, GameVariant gameVariant) {
        //intial Frame
        super("Dots and Boxes");

        this.gameModel = gameModel;
        this.playerActionController = playerActionController;
        this.gameManager = gameManager;
        this.uiPlayerColor = uiPlayerColor;
        this.gameVariant = gameVariant;

                
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


    public void setPlayerColor(PlayerColor uiPlayerColor) {
        this.uiPlayerColor = uiPlayerColor;
    }

    @Override
    public void update() {

        playBoard.repaint();

    }

    private void paintPlayBoard() {
        
        this.playBoard = new JPanel(){
        
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                 placeholder1.repaint();
                 placeholder2.repaint();
                
                //Rendering einstellen
                Graphics2D g2 = (Graphics2D)g;
                RenderingHints rh = new RenderingHints(
                         RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHints(rh);

            }
        };
        
        this.placeholder1 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                
                g.setFont(playerFont);
                g.setColor(Color.black);
                g.drawString("Red Player", 20, 30);
                ImageIcon playerIcon1 = new ImageIcon("images\\player_red.png");
                Image icon1 = playerIcon1.getImage();
                g.drawImage(icon1, 10, 50, rootPane);
                if(gameModel.isGameOver()){
                    if(gameModel.getWinner() == PlayerColor.Red){
                        g.setColor(Color.red);
                        g.drawString("Winner!", 20, 200);
                    }
                }
                else
                {
                    if(gameModel.getCurrentPlayer() == PlayerColor.Red)
                    {
                        g.setColor(Color.red);
                        g.drawString("Is on turn", 20, 200);
                    }
                }
            }
        };
        
        this.placeholder2 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                
                g.setFont(playerFont);
                g.setColor(Color.black);
                g.drawString("Yellow Player", 20, 30);
                ImageIcon playerIcon2;
                
                if(gameModel.getVariant()== GameVariant.local){
                    playerIcon2= new ImageIcon("images\\player_computer.png");
                }
                else{
                    playerIcon2 = new ImageIcon("images\\player_yellow.png");
                }
                
                Image icon1 = playerIcon2.getImage();
                g.drawImage(icon1, 10, 50, rootPane);
                if(gameModel.isGameOver()){
                    if(gameModel.getWinner() == PlayerColor.Blue){
                        g.setColor(Color.orange);
                        g.drawString("Winner!", 20, 200);
                    }
                }
                else
                {
                    if(gameModel.getCurrentPlayer() == PlayerColor.Blue)
                    {
                        g.setColor(Color.orange);
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
        int x = (dim.width - super.getSize().width)/2;
        int y = (dim.height - super.getSize().height)/2;
        super.setLocation(x, y);
        
        //set Icon 
        ImageIcon logoIcon = new ImageIcon("images\\logo.png");
        Image logo = logoIcon.getImage();
        super.setIconImage(logo);
        
        //set Layout Manager
        setLayout(new BorderLayout(5, 5));
        
    }

    private void setMenuBar() {
        
        this.menuFile.add(miFileNew);
            this.miFileNew.add(miFileNewEasy);
            this.miFileNew.add(miFileNewSimple);
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
}
