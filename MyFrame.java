import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.Random;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MyFrame extends JFrame implements KeyListener, ActionListener, MouseListener, WindowFocusListener{
	
	
	
	/*
	TO-DO:
	- score color changes
	- languages setting
	- levels
	*/
	
	
	Random random = new Random();
	
	
	Color lightBlue = new Color(104, 128, 255);
	Color daymode = new Color(255,255,150);
	Color nightmode = new Color(204,204,255);
	Color red = new Color(255,100,100);
	Color gold = new Color(221,175,55);
	Color brightColor = new Color(245,245,255);
	String randomWord;
	String lastWholeWord;
	boolean isFocused = false;
	boolean settingsButtonAn;
	boolean inMainGame = false;
	boolean inRestartScreen = false;
	boolean isFocusChanged = false;
	boolean isDifChanged = false;
	boolean firstInputDone = false;
	char lastInput;
	long endTime;
	long startTime;
	long writeTime;
	int lastInputCode;
	int points;
	int pointMultiplier;
	int wordsWritten;
		
	String[] words = { 
		    "about", "all", "also", "and", "as", "at", "be", "because", "but", "by", 
		    "can", "come", "could", "day", "do", "even", "find", "first", "for", "from", 
		    "get", "give", "go", "have", "he", "her", "here", "him", "his", "how", 
		    "I", "if", "in", "into", "it", "its", "just", "know", "like", "look", 
		    "make", "man", "many", "me", "more", "my", "new", "no", "not", "now", 
		    "of", "on", "one", "only", "or", "other", "our", "out", "people", "say", 
		    "see", "she", "so", "some", "take", "tell", "than", "that", "the", "their", 
		    "them", "then", "there", "these", "they", "thing", "think", "this", "those", 
		    "time", "to", "two", "up", "use", "very", "want", "way", "we", "well", 
		    "what", "when", "which", "who", "will", "with", "would", "year", "you", "your"
		};

	
	JTextField txt = new JTextField();
	JLabel txt1 = new JLabel();
	JLabel txt2 = new JLabel();
	JLabel txt3 = new JLabel();
	JLabel txt1after = new JLabel();
	JLabel txt2after = new JLabel();
	JLabel txt3after = new JLabel();
	JLabel worddisplay = new JLabel();
	JLabel score = new JLabel("Score: 0");
	JLabel afkScreen = new JLabel("[Click to play]");
	JLabel wrongInput = new JLabel("wrong input: ");
	JLabel infoESC = new JLabel("press \"esc\" to play");
	JLabel infoFocus = new JLabel("\"Change focus\" (sets focus on the second word)");
	JLabel infoDif = new JLabel("\"Expert mode\" (gives you a better score but a single wrong letter will make you loose)");
	JButton play = new JButton("Play");
	JButton settingsButton;
	JButton changeFocus = new JButton("");
	JButton changeDif = new JButton("");
	
	
	
	
	public MyFrame(){
		
		infoDif.setSize(900,60);
		infoDif.setLocation(110,150);
		infoDif.setFont(new Font("Times New Roman", Font.BOLD, 20));
		infoDif.setForeground(Color.gray);
		
		changeDif.setSize(50,50);
		changeDif.setLocation(50,150);
		changeDif.setOpaque(true);
		changeDif.setBackground(Color.white);
		changeDif.setFocusable(false);
		changeDif.addActionListener(this);
		changeDif.addMouseListener(this);
		
		infoFocus.setSize(900,60);
		infoFocus.setLocation(110,50);
		infoFocus.setFont(new Font("Times New Roman", Font.BOLD, 20));
		infoFocus.setForeground(Color.gray);
		
		changeFocus.setSize(50,50);
		changeFocus.setLocation(50,50);
		changeFocus.setOpaque(true);
		changeFocus.setBackground(Color.white);
		changeFocus.setFocusable(false);
		changeFocus.addActionListener(this);
		changeFocus.addMouseListener(this);
		
		infoESC.setSize(400,60);
		infoESC.setLocation(300,500);
		infoESC.setFont(new Font("Times New Roman", Font.BOLD, 40));
		infoESC.setHorizontalAlignment(SwingConstants.CENTER);
		infoESC.setForeground(Color.gray);
		
		wrongInput.setSize(1000,40);
		wrongInput.setLocation(0,50);
		wrongInput.setFont(new Font("DialogInput", Font.BOLD, 30));
		wrongInput.setFocusable(false);
		wrongInput.setForeground(lightBlue);
		wrongInput.setVisible(false);
		wrongInput.setHorizontalAlignment(SwingConstants.CENTER);
		
		play.setSize(200,100);
		play.setLocation(400,250);
		play.setOpaque(true);
		play.setBackground(nightmode);
		play.setFocusable(false);
		play.setFont(new Font("Times New Roman", Font.BOLD, 50));
		play.addActionListener(this);
		play.addMouseListener(this);
		
        settingsButton = new JButton("Settings");  //settingsButton
        settingsButton.setBackground(Color.white);
        settingsButton.setSize(150,50);
        settingsButton.setLocation(830,0);
        settingsButton.setFocusable(false);
        settingsButton.addActionListener(this); 
        settingsButton.addMouseListener(this);
        settingsButton.setFont(new Font("Times New Roman",Font.PLAIN, 20));
		
		score.setSize(1000,100);
		score.setLocation(0,25);
		score.setOpaque(true);
		score.setBackground(nightmode);
		score.setForeground(Color.white);
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setFont(new Font("Times New Roman", Font.BOLD, 50));
		
		afkScreen.setSize(1000,650);
		afkScreen.setFont(new Font("Times New Roman", Font.BOLD, 50));
		afkScreen.setOpaque(true);
		afkScreen.setBackground(Color.gray);
		afkScreen.setForeground(Color.white);
		afkScreen.setHorizontalAlignment(SwingConstants.CENTER);
		afkScreen.setVerticalAlignment(SwingConstants.CENTER);
		afkScreen.setVisible(false);
		
		worddisplay.setSize(250,100);
		worddisplay.setLocation(375,150);
		worddisplay.setFont(new Font("Times New Roman", Font.BOLD, 60));
		worddisplay.setBorder(null);
		worddisplay.setOpaque(true);
		worddisplay.setBackground(Color.white);
		worddisplay.setHorizontalAlignment(SwingConstants.CENTER);
		worddisplay.setVerticalAlignment(SwingConstants.CENTER);
		
		txt1.setSize(125,100);
		txt1.setLocation(250,150);
		txt1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txt1.setOpaque(true);
		txt1.setBackground(Color.white);
		txt1.setBorder(null);
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt1.setVerticalAlignment(SwingConstants.CENTER);
		
		txt2.setSize(125,100);
		txt2.setLocation(125,150);
		txt2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txt2.setOpaque(true);
		txt2.setBackground(Color.white);
		txt2.setBorder(null);
		txt2.setHorizontalAlignment(SwingConstants.CENTER);
		txt2.setVerticalAlignment(SwingConstants.CENTER);
		
		txt3.setSize(125,100);
		txt3.setLocation(0,150);
		txt3.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txt3.setOpaque(true);
		txt3.setBackground(Color.white);
		txt3.setBorder(null);
		txt3.setHorizontalAlignment(SwingConstants.CENTER);
		txt3.setVerticalAlignment(SwingConstants.CENTER);
		
		txt1after.setSize(125,100);
		txt1after.setLocation(625,150);
		txt1after.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txt1after.setOpaque(true);
		txt1after.setBackground(Color.white);
		txt1after.setBorder(null);
		txt1after.setHorizontalAlignment(SwingConstants.CENTER);
		txt1after.setVerticalAlignment(SwingConstants.CENTER);
		
		txt2after.setSize(125,100);
		txt2after.setLocation(750,150);
		txt2after.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txt2after.setOpaque(true);
		txt2after.setBackground(Color.white);
		txt2after.setBorder(null);
		txt2after.setHorizontalAlignment(SwingConstants.CENTER);
		txt2after.setVerticalAlignment(SwingConstants.CENTER);
		
		txt3after.setSize(125,100);
		txt3after.setLocation(875,150);
		txt3after.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txt3after.setOpaque(true);
		txt3after.setBackground(Color.white);
		txt3after.setBorder(null);
		txt3after.setHorizontalAlignment(SwingConstants.CENTER);
		txt3after.setVerticalAlignment(SwingConstants.CENTER);
		
		txt.setSize(250,75);
		txt.setLocation(375,275);
		txt.setFont(new Font("Times New Roman", Font.BOLD, 45));
		txt.setForeground(Color.black);
		txt.setBackground(brightColor);
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		txt.addKeyListener(this);
		
			this.add(settingsButton);
			this.add(infoESC);
			this.add(wrongInput);
			this.add(afkScreen);
			this.add(play);		
		this.setSize(1000,650);	
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.addWindowFocusListener(this);
		this.addKeyListener(this);
		this.setVisible(true);	
	}
		

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(txt.getText().equals(worddisplay.getText())) {
		addRandomWord();	
		}
		if(!firstInputDone) {
			startTime = System.currentTimeMillis();
			firstInputDone = true;
		}
		
		lastWholeWord = txt.getText();
		lastInput = e.getKeyChar();
		lastInputCode = e.getKeyCode();;
		if (!inMainGame && lastInputCode == 27 && !settingsButtonAn) {
		    play();
		}
		

		
		if(txt.getText().length() > worddisplay.getText().length()) { 
		lose();
		
		} else {
			if(isDifChanged && !txt.getText().equals(worddisplay.getText().substring(0,txt.getText().length()))) {
				lose();
			}
		}
		
		
		
	}
	public void addRandomWord() {
		randomWord = words[random.nextInt(words.length)];
		wordsWritten += 1;
		
		endTime = System.currentTimeMillis();
		writeTime = (endTime - startTime) / worddisplay.getText().length();
		startTime = System.currentTimeMillis();
		calculate();
		
		
		txt3.setText(txt2.getText());
		txt2.setText(txt1.getText());
		txt1.setText(worddisplay.getText());
		worddisplay.setText(txt1after.getText());
		txt1after.setText(txt2after.getText());
		txt2after.setText(txt3after.getText());
		while(randomWord.equals(txt3after.getText()) || randomWord.equals(txt2after.getText())) { randomWord = words[random.nextInt(words.length)]; }
		txt3after.setText(randomWord);
		txt.setText("");
		this.repaint();
		
	}
	
	public void play() {
		randomWord = words[random.nextInt(words.length)];
		 worddisplay.setText(randomWord);
		 while(randomWord == worddisplay.getText()) {randomWord = words[random.nextInt(words.length)];}
		 txt1after.setText(randomWord);
		 while(randomWord == worddisplay.getText()||randomWord == txt1after.getText()) {randomWord = words[random.nextInt(words.length)];}
		 txt2after.setText(words[random.nextInt(words.length)]);
		 while(randomWord == txt1after.getText()||randomWord == txt2after.getText()) {randomWord = words[random.nextInt(words.length)];}
		 txt3after.setText(randomWord);
		 
		 txt1.setText("");
		 txt2.setText("");
		 txt3.setText("");
		 
		 wordsWritten = 0;
		 points = 0;
		 score.setText("score: 0");
		 inMainGame = true;
		 this.getContentPane().setBackground(nightmode);
		score.setLocation(25,25);
		score.setHorizontalAlignment(SwingConstants.LEFT);
		 
	        txt.setVisible(true);
	        worddisplay.setVisible(true);
	        txt1.setVisible(true);
	        txt2.setVisible(true);
	        txt3.setVisible(true);
	        txt1after.setVisible(true);
	        txt2after.setVisible(true);
	        txt3after.setVisible(true);
	        score.setVisible(true);
	        play.setVisible(false);
	        wrongInput.setVisible(false);
	        infoESC.setVisible(false);

	        score.setBackground(nightmode);
	        score.setForeground(Color.white);
	        
		 this.add(txt);
		 this.add(txt1);
		 this.add(txt2);
		 this.add(txt3);
		 this.add(txt1after);
		 this.add(txt2after);
		 this.add(txt3after);
		 this.add(worddisplay);
		 this.add(score);
		 this.repaint();
		 
		 txt.requestFocusInWindow();
	}
	
	public void lose() {
	txt.setText("");
	infoESC.setText("press \"esc\" to restart");
	if(isDifChanged) {wrongInput.setText("wrong input: \""+lastInput+"\""); if(lastInput == ' ') {wrongInput.setText("wrong input: \"space\""); }}
	else {wrongInput.setText("wrong input: \""+lastWholeWord+"\"");}
    txt.setVisible(false);
    txt1.setVisible(false);
    txt2.setVisible(false);
    txt3.setVisible(false);
    txt1after.setVisible(false);
    txt2after.setVisible(false);
    txt3after.setVisible(false);
    worddisplay.setVisible(false);
    play.setVisible(true);
    wrongInput.setVisible(true);
    infoESC.setVisible(true);
	
    score.setBackground(Color.white);
    score.setForeground(nightmode);
    inMainGame = false;
    inRestartScreen = true;
    firstInputDone = false;
    score.setLocation(0,125);
    score.setHorizontalAlignment(SwingConstants.CENTER);
	play.setText("Restart");
	this.getContentPane().setBackground(Color.white);
	this.requestFocusInWindow(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == settingsButton) {
             if (settingsButtonAn) {
            	 
            	 this.remove(infoFocus);
            	 this.remove(infoDif);
            	 this.remove(changeFocus);
            	 this.remove(changeDif);;
            	 if(inMainGame) {
            		this.getContentPane().setBackground(nightmode);
              	    this.add(txt);
            	    this.add(worddisplay);
            	    this.add(txt1);
            	    this.add(txt2);
            	    this.add(txt3);
            	    this.add(txt1after);
            	    this.add(txt2after);
            	    this.add(txt3after);
            	    if(inRestartScreen || inMainGame) {this.add(score);}
            	 } else {
            		this.getContentPane().setBackground(Color.white);
            		this.add(wrongInput);
            		this.add(infoESC);
            		this.add(play);
            		if(inRestartScreen || inMainGame) {this.add(score);}
            	 }
         	    
                 settingsButton.setBackground(Color.white);
                 settingsButtonAn = false;
             } else {
            	 this.getContentPane().setBackground(Color.white);
            	 this.add(changeDif);
            	 this.add(changeFocus);
            	 this.add(infoFocus);
            	 this.add(infoDif);
               	 if(inMainGame) {
               	    this.remove(txt);
             	    this.remove(worddisplay);
             	    this.remove(txt1);
             	    this.remove(txt2);
             	    this.remove(txt3);
             	    this.remove(txt1after);
             	    this.remove(txt2after);
             	    this.remove(txt3after);
             	    if(inRestartScreen || inMainGame) {this.remove(score);}
             	 } else {
             		this.remove(wrongInput);
             		if(inRestartScreen || inMainGame) {this.remove(score);}
             		this.remove(infoESC);
             		this.remove(play);
             	 }
                 
                 settingsButton.setBackground(lightBlue);
                 settingsButtonAn = true;
             }
             this.repaint();
         }
		 if(e.getSource()==play) {
			 play();
		 }
		 
		 if(e.getSource()==changeFocus) {
			 if(!isFocusChanged) {
				 worddisplay.setFont(new Font("Times New Roman", Font.BOLD, 30));
				 txt1after.setFont(new Font("Times New Roman", Font.BOLD, 60));
				 changeFocus.setBackground(nightmode);
				 
					txt1after.setSize(250,100);
					txt1after.setLocation(500,150);
					worddisplay.setSize(125,100);
					worddisplay.setLocation(375,150);
				 isFocusChanged = true;
			 } else {
				 worddisplay.setFont(new Font("Times New Roman", Font.BOLD, 60));
				 txt1after.setFont(new Font("Times New Roman", Font.BOLD, 30));
				 changeFocus.setBackground(Color.white);
				 	
					txt1after.setSize(125,100);
					txt1after.setLocation(625,150);
					worddisplay.setSize(250,100);
					worddisplay.setLocation(375,150);
				 isFocusChanged = false;
			 }
		 }
		 if(e.getSource()==changeDif) {
			 if(!isDifChanged) {
				 changeDif.setBackground(nightmode);
				 isDifChanged = true;
				 
			 } else {
				 changeDif.setBackground(Color.white);
				 isDifChanged = false;

			 }
		 }
		 	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==settingsButton) {
			settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			settingsButton.setBackground(Color.gray);
		}
		if(e.getSource()==play) {
			play.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			play.setBackground(Color.gray);
			play.setSize(play.getSize().width + 10, play.getSize().height + 5);
			play.setLocation(play.getLocation().x - 5, play.getLocation().y - 3);
		}	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==settingsButton) {
			settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			if(!settingsButtonAn) {
			settingsButton.setBackground(Color.white);
			} else if (settingsButtonAn) {
			settingsButton.setBackground(lightBlue);
			}
		}
		if(e.getSource()==play) {
			play.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			play.setBackground(nightmode);
			play.setSize(play.getSize().width - 10, play.getSize().height - 5);
			play.setLocation(play.getLocation().x + 5, play.getLocation().y + 3);
		}
		
	}



	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		    if(e.getSource() == this) {
		    	if(inMainGame) {
			        txt.setVisible(true);
			        worddisplay.setVisible(true);
			        txt1.setVisible(true);
			        txt2.setVisible(true);
			        txt3.setVisible(true);
			        txt1after.setVisible(true);
			        txt2after.setVisible(true);
			        txt3after.setVisible(true);
			        score.setVisible(true);
			        settingsButton.setVisible(true);
			        afkScreen.setVisible(false);
		    	} else {
		    		settingsButton.setVisible(true);
		    		score.setVisible(true);
		    		play.setVisible(true);
		    		afkScreen.setVisible(false);
		    		if(inRestartScreen) { wrongInput.setVisible(true);}
		    	}

		        txt.requestFocusInWindow(); 
		        this.repaint();
		    }
		
	    
	}


	@Override
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub
	    if(e.getSource() == this) {
	    	if(inMainGame) {
		        txt.setVisible(false);
		        worddisplay.setVisible(false);
		        txt1.setVisible(false);
		        txt2.setVisible(false);
		        txt3.setVisible(false);
		        txt1after.setVisible(false);
		        txt2after.setVisible(false);
		        txt3after.setVisible(false);
		        score.setVisible(false);
		        settingsButton.setVisible(false);
		        afkScreen.setVisible(true);
	    	} else {
	    		settingsButton.setVisible(false);
	    		score.setVisible(false);
	    		play.setVisible(false);
	    		afkScreen.setVisible(true);
	    		if(inRestartScreen) { wrongInput.setVisible(false);}
	    	}

	        this.repaint();
	    }
	}
	
		public void calculate() {
		if(writeTime <= 60) { 
			pointMultiplier = 10;
		}else if(writeTime <= 90) { 
			pointMultiplier = 5;
			
		}else if(writeTime <= 130) { 
			pointMultiplier = 3;
			
		}else if(writeTime <= 200) {
			pointMultiplier = 2;
			
		} else if(writeTime <= 1000) {
			pointMultiplier = 1;
		} else { pointMultiplier = 0; }
		if(isDifChanged) { pointMultiplier *= 3; }
		
		
		points += worddisplay.getText().length() * pointMultiplier * random.nextInt(100,111) * ((wordsWritten * 0.03 ) +1);
		score.setText("score: "+points);

	}

}











