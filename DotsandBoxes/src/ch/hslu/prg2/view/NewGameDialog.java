/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.view;


import ch.hslu.prg2.controlling.Controller;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class NewGameDialog extends javax.swing.JDialog implements ListSelectionListener {
	// version number of this class, used for serialization
	private static final long serialVersionUID = 1L;
	// default number of rows
	private final int DEFAULTROWS = 3;

	// row spinner, returns Integer objects
	private JSpinner rowSpinner;
        
        // game Variant
        JList wordList;
        private String [] variants = {"Ki - Ki", "Ki - Player", "Player - Ki", "Player - Player" };
        
        private String variantValue;
        private int selectet;
        private int rows;
        private String player1;
        private String player2;
        
        //Player Name
        private JTextField Player1Field, Player2Field;
                
	// callback object which receives user's selection
	//private NewGameParametersCallback callback;

	/**
	 * Shows the dialog box in the right position over the parent window
	 */
	public void showDialog() {
		// TODO figure out why this works incorrectly on Linux
		// get the origin of the window
		Point location = this.getParent().getLocation();
		// move down and to the right
		location.translate(30, 30);
		// place the dialog here
		this.setLocation(location);
                
                wordList.addListSelectionListener(this);
		// show the dialog
		this.setVisible(true);
	}

	/**
	 * Allow testing of dialog as a standalone class
	 * 
	 * @param args command line parameters (ignored)
	 */
	public static void main(String[] args) {
		// make an invisible toplevel window
		JFrame frame = new JFrame();
		// closing the window should dispose of it, allowing VM to exit
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		// show it
		frame.setSize(400, 300);
		frame.setVisible(true);
		// anonymous inline callback
		NewGameParametersCallback cback = new NewGameParametersCallback() {
			public void newGameParameters(int vari, int rows, String player1,String player2) {
				// print out the user's choices
				System.out.println("NewGameParametersCallback Vari=" + vari
						+ " rows=" + rows + " player1=" + player1 + " player2=" + player2);
			}
		};
		// make the modal dialog
		NewGameDialog instance = new NewGameDialog(frame);
		// show the dialog
		instance.showDialog();
	}

	/**
	 * Creates the invisible modal dialog box
	 * 
	 * @param frame window that owns the dialog
	 * @param callback callback to receive the new game parameters
	 */
	public NewGameDialog(JFrame frame) {
		// allow the super-class to initialize (JDialog)
		super(frame);
		// save data
		//this.callback = callback;
		// set up the dialog
		initGUI();
                
	}

	/**
	 * Configure's the dialog's appearance and creates the contained widgets
	 */
	private void initGUI() {
		// configure dialog layout
		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1 };
		thisLayout.columnWidths = new int[] { 7, 7, 2 };
		thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1 };
		thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7 };
		this.getContentPane().setLayout(thisLayout);

		// set other characteristics of dialog
		this.setTitle("New Game");
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(200, 350);

		{// "New Game" label
			JLabel newGameLabel = new JLabel();
			newGameLabel.setText("New Game");
			newGameLabel.setFont(new java.awt.Font("Dialog", 1, 18));

			GridBagConstraints newGameLabelConstraints = new GridBagConstraints();
			newGameLabelConstraints.gridy = 0;
			newGameLabelConstraints.gridwidth = 2;

			this.getContentPane().add(newGameLabel, newGameLabelConstraints);
		}

		{// "Choose dimensions:" label
			JLabel chooseLabel = new JLabel();
			chooseLabel.setText("Choose dimensions:");
			chooseLabel.setLayout(null);
			chooseLabel.setHorizontalTextPosition(SwingConstants.LEADING);
			chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);

			GridBagConstraints chooseLabelConstraints = new GridBagConstraints();
			chooseLabelConstraints.gridy = 1;
			chooseLabelConstraints.gridwidth = 2;
			chooseLabelConstraints.anchor = GridBagConstraints.WEST;
			chooseLabelConstraints.ipadx = 10;

			this.getContentPane().add(chooseLabel, chooseLabelConstraints);
		}

		{// "Rows/Cols:" label
			JLabel rowsLabel = new JLabel();
			rowsLabel.setText("Rows/Cols:");

			GridBagConstraints rowsLabelConstraints = new GridBagConstraints();
			rowsLabelConstraints.gridy = 2;
			rowsLabelConstraints.anchor = GridBagConstraints.EAST;
			rowsLabelConstraints.ipadx = 10;

			this.getContentPane().add(rowsLabel, rowsLabelConstraints);
		}

		{// rows spinner
			this.rowSpinner = new JSpinner();
			SpinnerNumberModel rowSpinnerModel = new SpinnerNumberModel(
					DEFAULTROWS, 1, 20, 1);
			this.rowSpinner.setModel(rowSpinnerModel);

			GridBagConstraints rowSpinnerConstraints = new GridBagConstraints();
			rowSpinnerConstraints.gridx = 1;
			rowSpinnerConstraints.gridy = 2;
			rowSpinnerConstraints.fill = GridBagConstraints.HORIZONTAL;

			this.getContentPane().add(this.rowSpinner, rowSpinnerConstraints);
		}
                {// "Choose Gamevariant:" label
			JLabel chooseLabel = new JLabel();
			chooseLabel.setText("Choose Gamevariant:");
			chooseLabel.setLayout(null);
			chooseLabel.setHorizontalTextPosition(SwingConstants.LEADING);
			chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);

			GridBagConstraints chooseLabelConstraints = new GridBagConstraints();
			chooseLabelConstraints.gridy = 7;
			chooseLabelConstraints.gridwidth = 2;
			chooseLabelConstraints.anchor = GridBagConstraints.WEST;
			chooseLabelConstraints.ipadx = 10;

			this.getContentPane().add(chooseLabel, chooseLabelConstraints);
		}
                /*{// "GameVariant:" label
                JLabel colsLabel = new JLabel();
                colsLabel.setText("GameVariant:");
                
                GridBagConstraints cosLabelConstraints = new GridBagConstraints();
                cosLabelConstraints.gridy = 8;
                cosLabelConstraints.anchor = GridBagConstraints.EAST;
                cosLabelConstraints.ipadx = 10;
                
                this.getContentPane().add(colsLabel, cosLabelConstraints);
                }*/
                
                {// GameVariantspinner
                this.wordList = new JList(variants);
                
                //JScrollPane scrollPane = new JScrollPane(wordList);
                //this.variantSpinner.setModel(varSpinnerModel);
                
                GridBagConstraints colSpinnerConstraints = new GridBagConstraints();
                colSpinnerConstraints.gridx = 1;
                colSpinnerConstraints.gridy = 8;
                colSpinnerConstraints.fill = GridBagConstraints.HORIZONTAL;
                
                this.getContentPane().add(this.wordList, colSpinnerConstraints);
                }
                {// "Set Names:" label
			JLabel chooseLabel = new JLabel();
			chooseLabel.setText("Set Names:");
			chooseLabel.setLayout(null);
			chooseLabel.setHorizontalTextPosition(SwingConstants.LEADING);
			chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);

			GridBagConstraints chooseLabelConstraints = new GridBagConstraints();
			chooseLabelConstraints.gridy = 3;
			chooseLabelConstraints.gridwidth = 2;
			chooseLabelConstraints.anchor = GridBagConstraints.WEST;
			chooseLabelConstraints.ipadx = 10;

			this.getContentPane().add(chooseLabel, chooseLabelConstraints);
		}
                {// "Player1: " label
			JLabel Name1Label = new JLabel();
			Name1Label.setText("Player1:");

			GridBagConstraints Name1LabelConstraints = new GridBagConstraints();
			Name1LabelConstraints.gridy = 4;
			Name1LabelConstraints.anchor = GridBagConstraints.EAST;
			Name1LabelConstraints.ipadx = 10;

			this.getContentPane().add(Name1Label, Name1LabelConstraints);
		}
                {// "Player1:" Textfiled
                        this.Player1Field = new JTextField();
                        Player1Field.setText("Player 1");
                        
                        GridBagConstraints Player1FieldConstraints = new GridBagConstraints();
			Player1FieldConstraints.gridy = 4;
			Player1FieldConstraints.gridwidth = 2;
			Player1FieldConstraints.anchor = GridBagConstraints.WEST;
			Player1FieldConstraints.ipadx = 10;

			this.getContentPane().add(Player1Field, Player1FieldConstraints);
                }
                
                {// "Player2: " label
			JLabel Name2Label = new JLabel();
			Name2Label.setText("Player2:");

			GridBagConstraints Name2LabelConstraints = new GridBagConstraints();
			Name2LabelConstraints.gridy = 6;
			Name2LabelConstraints.anchor = GridBagConstraints.EAST;
			Name2LabelConstraints.ipadx = 10;

			this.getContentPane().add(Name2Label, Name2LabelConstraints);
		}
                
                {// "Player1:" Textfiled
                        this.Player2Field = new JTextField();
                        Player2Field.setText("Player 2");
                        
                        GridBagConstraints Player2FieldConstraints = new GridBagConstraints();
			Player2FieldConstraints.gridy = 6;
			Player2FieldConstraints.gridwidth = 2;
			Player2FieldConstraints.anchor = GridBagConstraints.WEST;
			Player2FieldConstraints.ipadx = 10;

			this.getContentPane().add(Player2Field, Player2FieldConstraints);
                }
                                
		{// "Start Game" button
			JButton startGameButton = new JButton();
			startGameButton.setText("Start Game");

			// button's action map
			ActionMap acmap = startGameButton.getActionMap();
			// map for input events that happen anywhere in the dialog
			InputMap inmap = startGameButton
					.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

			// ENTER KEY HANDLER
			// make an anonymous object to handle dispatch action
			Action dispatchAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;
				// Invoked when the action occurs. Dispatches the dialog box.
				public void actionPerformed(ActionEvent e) {
					// System.out.println("NewGameDialog:actionPerformed:
					// dispatch");
					dispatchDialog();
				}
			};
			// set object as action handler for the button (for button presses)
			startGameButton.addActionListener(dispatchAction);
			// register "dispatch" action for enter key presses
			KeyStroke EnterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
			acmap.put("dispatch", dispatchAction);
			inmap.put(EnterKey, "dispatch");

			// ESCAPE KEY HANDLER
			// make an anonymous object to handle dispose action
			Action disposeAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;
				// Invoked when an action occurs. Disposes of the dialog box.
				public void actionPerformed(ActionEvent e) {
					// System.out.println("NewGameDialog:actionPerformed:
					// dispose");
					dispose();
				}
			};
			// register "dispose" action for escape key presses
			KeyStroke EscapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
			acmap.put("dispose", disposeAction);
			inmap.put(EscapeKey, "dispose");

			GridBagConstraints startGameButtonConstraints = new GridBagConstraints();
			startGameButtonConstraints.gridx = 1;
			startGameButtonConstraints.gridy = 15;

			this.getContentPane().add(startGameButton,
					startGameButtonConstraints);
		}
	}

  
        @Override
  public void valueChanged(ListSelectionEvent evt) {
    JList source = (JList) evt.getSource();
    Object[] values = source.getSelectedValues();

    String text = "";
    for (int i = 0; i < values.length; i++) {
      String word = (String) values[i];
      text += word + " ";
      variantValue = word;
    }  
    VariantValueToInt(variantValue);
    //label.setText( " " + variantValue +  " " +selectet);
    
  }
    public void VariantValueToInt(String val){
      
      if(val == "Ki - Ki") selectet = 0;
      if(val == "Ki - Player") selectet = 1;
      if(val == "Player - Ki") selectet = 2;
      if(val == "Player - Player") selectet = 3;
  }
	/**
	 * Disposes of the dialog box and passes dialog values to the callback
	 * object. The dialog may be recreated again by calling setVisible(true).
	 */
	private void dispatchDialog() {
		// get the values from the spinner widgets
		//Integer colInteger = (Integer) this.colSpinner.getValue();
		Integer rowInteger = (Integer) this.rowSpinner.getValue();
                player1 = (String) this.Player1Field.getText();
                player2 = (String) this.Player2Field.getText();
		//int cols = colInteger.intValue();
		rows = rowInteger.intValue();
		// System.out.println("colSpinner=" + cols);
		// System.out.println("rowSpinner=" + rows);
                // System.out.println("Pl1=" + player1);
                // System.out.println("Pl2=" + player2);
                // System.out.println("Variant =" + selectet);
		// Dispose of the dialog (hides it, removes all event handlers, VM exit
		// allowed)
		this.dispose();

		// pass the values to the callback object
		//this.callback.newGameParameters(selectet, rows, player1,player2);
	}



    void startGame(Controller controller) {
        
        controller.startGame(selectet, this.rows, player1, player2);
    }
}