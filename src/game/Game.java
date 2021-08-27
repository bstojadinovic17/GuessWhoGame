package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Game {
		private JFrame frame;
		private int  quesnum=0;
		private int player_char;
		private  String[] cpuAnswer=new String[10];
		private String character;
		private static String[] cpu_choice;
		private ArrayList<String[]> characters=new ArrayList<>();
		private ArrayList<String> questions=new ArrayList<>();
		private String[] questionsString = new String[15];
		private int b01, b02, b03, b04, b05, b06, b07, b08, b09, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
		public static void main(String[] args) {
			Game game=new Game();
			game.frame.setVisible(true);
			JDialog pocetniDialog=new JDialog(game.frame, "Choose character!", true);
			pocetniDialog.setLocationRelativeTo(null);
			pocetniDialog.setSize(350, 120);
			JPanel gore=new JPanel();
			gore.add(new JLabel("Choose your character!"));
			JPanel dole=new JPanel();
			JButton btnOk=new JButton("Ok");
			btnOk.setSize(new Dimension(100, 30));
			btnOk.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					pocetniDialog.setVisible(false);
					Random rnd=new Random();
					game.player_char=0;
					questions(game.questions, game.questionsString);
					game.quesnum=0;
					declare(game.characters);
					cpu_choice=game.characters.get(rnd.nextInt(17-0));		
					for(int i=0;i<10;i++) {
						System.out.println(cpu_choice[i]);
					}
				}
			});
			dole.add(btnOk);
			JPanel panel=new JPanel(new BorderLayout());
			panel.add(gore,BorderLayout.NORTH);
			panel.add(dole,BorderLayout.CENTER);
			pocetniDialog.add(panel);
			pocetniDialog.setVisible(true);
			
			
		}
		
		public Game() {
			initialize();
		}
		
		private void initialize() {
			
			
			frame=new JFrame();
			frame.setSize(860,732);
			frame.setTitle("Guess Who?");
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			JPanel mainPanel=new JPanel();
			mainPanel.setBackground(Color.red);
			frame.add(mainPanel);
			declare(characters);
			questions(questions, questionsString);
			JLabel lblYourCharacter=new JLabel("");
			lblYourCharacter.setToolTipText("Player Character");
			lblYourCharacter.setSize(new Dimension(99, 148));
			lblYourCharacter.setIcon(new ImageIcon("images/Red.png"));
			
			JLabel lblCPUCharacter=new JLabel("");
			lblCPUCharacter.setToolTipText("CPU Character");
			lblCPUCharacter.setIcon(new ImageIcon("images/Blue.png"));
			lblCPUCharacter.setSize(new Dimension(99, 148));
			
			JButton btnPlay=new JButton("Play");
			btnPlay.setSize(new Dimension(100, 30));
			btnPlay.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)  {
					JDialog dialog=new JDialog(frame, "Play", true);
					dialog.setSize(new Dimension(350, 120));
					dialog.setLocationRelativeTo(null);
					JPanel panel=new JPanel(new BorderLayout());
					JPanel gore=new JPanel();
					gore.add(new JLabel("Choose an option:"));
					panel.add(gore,BorderLayout.NORTH);
					JPanel dole=new JPanel(new FlowLayout());
					dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
					JButton btnAsk=new JButton("Ask");
					btnAsk.setSize(new Dimension(100, 30));
					btnAsk.setToolTipText("Ask question");
					btnAsk.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							dialog.setVisible(false);
							String s = (String)JOptionPane.showInputDialog(
				                    frame,
				                    "Question:",
				                    "Ask Question",
				                    JOptionPane.INFORMATION_MESSAGE,
				                    null,
				                    questionsString,
				                    "Alice");
							
							if (s != null) {
								
								if (s.contains("ale")) {
								
									int index = 1;
									cpuRespond(frame, s,"?", index);
									dialog.setVisible(false);
									
								} else if (s.contains("Skin")) {
																		
									int index = 2; 
									cpuRespond(frame, s," Skin?", index);
									dialog.setVisible(false);
									
								} else if (s.contains("Hair")) {
																		
									int index = 3;
									cpuRespond(frame, s," Hair?", index);
									dialog.setVisible(false);
									
								} else if (s.contains("Bald")) {
									
									int index = 4;
									cpuRespondYesNo(frame, s, index);
									dialog.setVisible(false);
									
								} else if (s.contains("Hat")) {
									
									int index = 5;
									cpuRespondYesNo(frame, s, index);
									dialog.setVisible(false);
									
								} else if (s.contains("Earrings")) {
									
									int index = 7;
									cpuRespondYesNo(frame, s, index);
									dialog.setVisible(false);
									
								} else if (s.contains("Glasses")) {
									
									int index = 6;
									cpuRespondYesNo(frame, s, index);
									dialog.setVisible(false);
									
								} else if (s.contains("Mustache")) {
									
									int index = 8;
									cpuRespondYesNo(frame, s, index);
									dialog.setVisible(false);
									
								} else if (s.contains("Beard")) {
									
									int index = 9;
									cpuRespondYesNo(frame, s, index);
									dialog.setVisible(false);
								} 
							
						}
						dialog.setEnabled(false);;
						btnPlay.setEnabled(false);
						questions.remove(s);
						}});
					dole.add(btnAsk);
					
					JButton btnGuess=new JButton("Guess");
					btnGuess.setSize(new Dimension(100, 30));
					btnGuess.setToolTipText("Guess character");					
					btnGuess.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Object[] likovi = {"Alice", "Anita", "Anne", "Art", "Bill", "Bob", "Brian", "Carol", "David", "Eric", "Frank", "Henry", "Jill", "Mike", "Paul", "Phil", "Sam", "Susan"};
							String s = (String)JOptionPane.showInputDialog(
							                    frame,
							                    "Characters:",
							                    "Guess Character",
							                    JOptionPane.INFORMATION_MESSAGE,
							                    null,
							                    likovi,
							                    "Alice");
							if (s != null) {
								
								if (s.equals(cpu_choice[0])) {
									lblCPUCharacter.setIcon(new ImageIcon(("images/" + cpu_choice[0] + ".png")));
									JOptionPane.showMessageDialog(frame,
										    "You have won! You guessed the correct character!");
								} else {
									lblCPUCharacter.setIcon(new ImageIcon(("images/" + cpu_choice[0] + ".png")));
									JOptionPane.showMessageDialog(frame,
											"You lost! You didn't guess the right character!\n"
											+ "The right character was: " + cpu_choice[0] + ".",
										    "Game Lost",
										    JOptionPane.ERROR_MESSAGE);
								}
								int one_more = JOptionPane.showConfirmDialog(frame, "Another game?" , "Question", JOptionPane.YES_NO_OPTION);
								if(one_more == 0){
									System.out.println("Another game starting!");
									frame.setVisible(false);
									String[] args = new String[0];
									main(args);
								}
							}
							dialog.setVisible(false);;
							btnPlay.setEnabled(false);
						}
						
					});
					
					dole.add(btnGuess);
					panel.add(dole,BorderLayout.CENTER);
					dialog.add(panel);
					dialog.setVisible(true);
				}
			});
			JButton btnNext=new JButton("Next");
			btnNext.setSize(new Dimension(100, 30));
			btnNext.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					btnPlay.setEnabled(true);
					String question;
					int choice=0;
					quesnum++;
					System.out.println("Broj preostalih: "+ characters.size());
					if(characters.size()==1) {
						int n = JOptionPane.showConfirmDialog(frame,
								"Are you " + character + "?",
								"Answer",
								JOptionPane.YES_NO_OPTION);
						lblCPUCharacter.setIcon(new ImageIcon("images/"+cpu_choice[0]+".png"));
						if (n == 1) {
							JOptionPane.showMessageDialog(frame,
								    "You Win! The CPU guessed wrong.");
						} else {
							JOptionPane.showMessageDialog(frame,
									"You Lose! The CPU guessed correctly.",
								    "Game Lost",
								    JOptionPane.ERROR_MESSAGE);
						}

						int another_game = JOptionPane.showConfirmDialog(frame, "Another game?" , "Question", JOptionPane.YES_NO_OPTION);
						if(another_game == 0){
							System.out.println("Another game starting!");
							frame.setVisible(false);
							String[] args = new String[0];
							main(args);
						}
					}else {
						if (quesnum == 1) {
							
							String value = ""; 
							int n = JOptionPane.showConfirmDialog(frame,
								    "Is your character a male?",
								    "CPU Question",
								    JOptionPane.YES_NO_OPTION);
							if ( n == 1) {
								value = "Male";
								removeChar(1, value, characters);
								cpuAnswer[0]="Female";
							} else {
								value = "Female";
								removeChar(1, value, characters);
								cpuAnswer[0]="Male";
							}							
						} 
						if (quesnum == 2) {
									
							if (cpuAnswer[0].equals("Female")) {								
								question = "Is your character wearing a hat?";
								askQuestion(5, question, cpuAnswer, characters);
							}
							if (cpuAnswer[0].equals("Male")) {																
								String value = "";
								int n = JOptionPane.showConfirmDialog(frame,
									    "Is your character dark skinned?",
									    "CPU Question",
									    JOptionPane.YES_NO_OPTION);
								if ( n == 1) {
									value = "Dark";
									removeChar(2, value, characters);
									cpuAnswer[1]="Light";
								} else {
									value = "Light";
									removeChar(2, value, characters);
									cpuAnswer[1]="Dark";
								}
							}	
						}
						if (quesnum == 3) {
									
							if (cpuAnswer[0].equals("Female")) {					
								question = "Is your character wearing glasses?";
								askQuestion(6, question, cpuAnswer, characters);
							}
							if (cpuAnswer[0].equals("Male")) {								
								question = "Is your character bald?";
								askQuestion(4, question, cpuAnswer, characters);
							}	
						}
						if (quesnum == 4) {
									
							if (cpuAnswer[0].equals("Female")) {								
								question = "Is your character wearing earrings?";
								askQuestion(7, question, cpuAnswer, characters);
							}
							if (cpuAnswer[0].equals("Male") &&  cpuAnswer[1].equals("Dark")  &&  cpuAnswer[4].equals("No")) {
								question = "Does your character have a mustache?";
								askQuestion(8, question, cpuAnswer, characters);
							}	
							if (cpuAnswer[0].equals("Male") &&  cpuAnswer[1].equals("Light")) {
								question = "Is your character wearing glasses?";
								askQuestion(6, question, cpuAnswer, characters);
								}	
						}
						if (quesnum == 5) {
									
							if (cpuAnswer[0].equals("Female") && cpuAnswer[7].equals("Yes")) {
								String hair = "Red";
								question="Red hair?";
								askHair(3, hair, cpuAnswer, characters);
							}
							if (cpuAnswer[0].equals("Female") && cpuAnswer[7].equals("No")) {		
								String hair = "Blonde";
								question="Blonde hair?";
								askHair(3, hair, cpuAnswer, characters);
							}
							if (cpuAnswer[0].equals("Male") &&  cpuAnswer[4].equals("Yes")) {
									
								String hair = "Black";
								question="Black hair?";
								askHair(3, hair, cpuAnswer, characters);
							}	
							if (cpuAnswer[0].equals("Male") &&  cpuAnswer[4].equals("No")) {
								question = "Does your chacter have a beard?";
								askQuestion(9, question, cpuAnswer, characters);
							}	
						}
						if (quesnum == 6) {
							question = "Does your character have a mustache?";
							askQuestion(8, question, cpuAnswer, characters);
						}	
						if (quesnum == 7) {
							question = "Is your character wearing a hat?";
							askQuestion(5, question, cpuAnswer, characters);
						}	
						if (quesnum == 8) {
									
							String hair =  null;
							if (cpuAnswer[5].equals("Yes")) { 
								hair = "Brown";
							}
							if (cpuAnswer[5].equals("No")) { 
								hair = "Red";
							}
							askHair(3, hair, cpuAnswer, characters);
						}
					}
				
				}
			});
			
			
			
			JButton btn1=new JButton("");
			btn1.setIcon(new ImageIcon("images/Alice.png"));
			btn1.setBounds(177, 19, 99, 148);
			btn1.setOpaque(false);
			btn1.setContentAreaFilled(false);
			btn1.setBorderPainted(false);
			btn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String who="Alice";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b01++;
						if ( (b01 % 2) == 0) {
							btn1.setIcon(new ImageIcon("images/Alice.png"));
						} else {
							btn1.setIcon(new ImageIcon("images/Red.png"));
						}
					}	
				}
			});
			mainPanel.add(btn1);
			
			JButton btn2=new JButton("");
			btn2.setIcon(new ImageIcon("images/Anita.png"));
			btn2.setBounds(296, 19, 99, 148);
			btn2.setOpaque(false);
			btn2.setContentAreaFilled(false);
			btn2.setBorderPainted(false);
			btn2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String who="Anita";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b02++;
						if ( (b02 % 2) == 0) {
							btn2.setIcon(new ImageIcon("images/Anita.png"));
						} else {
							btn2.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn2);
			
			JButton btn3=new JButton("");
			btn3.setIcon(new ImageIcon("images/Anne.png"));
			btn3.setBounds(415, 19, 99, 148);
			btn3.setOpaque(false);
			btn3.setContentAreaFilled(false);
			btn3.setBorderPainted(false);
			btn3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Anne";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b03++;
						if ( (b03 % 2) == 0) {
							btn3.setIcon(new ImageIcon("images/Anne.png"));
						} else {
							btn3.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn3);
			
			JButton btn4=new JButton("");
			btn4.setIcon(new ImageIcon("images/Art.png"));
			btn4.setBounds(534, 19, 99, 148);
			btn4.setOpaque(false);
			btn4.setContentAreaFilled(false);
			btn4.setBorderPainted(false);
			btn4.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Art";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b04++;
						if ( (b04 % 2) == 0) {
							btn4.setIcon(new ImageIcon("images/Art.png"));
						} else {
							btn4.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn4);
			
			JButton btn5=new JButton("");
			btn5.setIcon(new ImageIcon("images/Bill.png"));
			btn5.setBounds(653, 19, 99, 148);
			btn5.setOpaque(false);
			btn5.setContentAreaFilled(false);
			btn5.setBorderPainted(false);
			btn5.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Bill";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b05++;
						if ( (b05 % 2) == 0) {
							btn5.setIcon(new ImageIcon("images/Bill.png"));
						} else {
							btn5.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn5);
			
			JButton btn6=new JButton("");
			btn6.setIcon(new ImageIcon("images/Bob.png"));
			btn6.setBounds(177, 178, 99, 148);
			btn6.setOpaque(false);
			btn6.setContentAreaFilled(false);
			btn6.setBorderPainted(false);
			btn6.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Bob";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b06++;
						if ( (b06 % 2) == 0) {
							btn6.setIcon(new ImageIcon("images/Bob.png"));
						} else {
							btn6.setIcon(new ImageIcon("images/Red.png"));
						}
					}
					
				}
			});
			mainPanel.add(btn6);
			
			JButton btn7=new JButton("");
			btn7.setIcon(new ImageIcon("images/Brian.png"));
			btn7.setBounds(296, 178, 99, 148);
			btn7.setOpaque(false);
			btn7.setContentAreaFilled(false);
			btn7.setBorderPainted(false);
			btn7.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Brian";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b07++;
						if ( (b07 % 2) == 0) {
							btn7.setIcon(new ImageIcon("images/Brian.png"));
						} else {
							btn7.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn7);
			
			JButton btn8=new JButton("");
			btn8.setIcon(new ImageIcon("images/Carol.png"));
			btn8.setBounds(415, 178, 99, 148);
			btn8.setOpaque(false);
			btn8.setContentAreaFilled(false);
			btn8.setBorderPainted(false);
			btn8.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Carol";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b08++;
						if ( (b08 % 2) == 0) {
							btn8.setIcon(new ImageIcon("images/Carol.png"));
						} else {
							btn8.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn8);
			
			JButton btn9=new JButton("");
			btn9.setIcon(new ImageIcon("images/David.png"));
			btn9.setBounds(534, 178, 99, 148);
			btn9.setOpaque(false);
			btn9.setContentAreaFilled(false);
			btn9.setBorderPainted(false);
			btn9.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="David";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b09++;
						if ( (b09 % 2) == 0) {
							btn9.setIcon(new ImageIcon("images/David.png"));
						} else {
							btn9.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn9);
			
			JButton btn10=new JButton("");
			btn10.setIcon(new ImageIcon("images/Eric.png"));
			btn10.setBounds(653, 178, 99, 148);
			btn10.setOpaque(false);
			btn10.setContentAreaFilled(false);
			btn10.setBorderPainted(false);
			btn10.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String who="Eric";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b10++;
						if ( (b10 % 2) == 0) {
							btn10.setIcon(new ImageIcon("images/Eric.png"));
						} else {
							btn10.setIcon(new ImageIcon("images/Red.png"));
						}
					}
					
				}
			});
			mainPanel.add(btn10);
			
			JButton btn11=new JButton("");
			btn11.setIcon(new ImageIcon("images/Frank.png"));
			btn11.setBounds(177, 337, 99, 148);
			btn11.setOpaque(false);
			btn11.setContentAreaFilled(false);
			btn11.setBorderPainted(false);
			btn11.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Frank";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b11++;
						if ( (b11 % 2) == 0) {
							btn11.setIcon(new ImageIcon("images/Frank.png"));
						} else {
							btn11.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn11);
			
			JButton btn12=new JButton("");
			btn12.setIcon(new ImageIcon("images/Henry.png"));
			btn12.setBounds(296, 337, 99, 148);
			btn12.setOpaque(false);
			btn12.setContentAreaFilled(false);
			btn12.setBorderPainted(false);
			btn12.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Henry";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b12++;
						if ( (b12 % 2) == 0) {
							btn12.setIcon(new ImageIcon("images/Henry.png"));
						} else {
							btn12.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn12);
			
			JButton btn13=new JButton("");
			btn13.setIcon(new ImageIcon("images/Jill.png"));
			btn13.setBounds(415, 337, 99, 148);
			btn13.setOpaque(false);
			btn13.setContentAreaFilled(false);
			btn13.setBorderPainted(false);
			btn13.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Jill";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b13++;
						if ( (b13 % 2) == 0) {
							btn13.setIcon(new ImageIcon("images/Jill.png"));
						} else {
							btn13.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn13);
			
			JButton btn14=new JButton("");
			btn14.setIcon(new ImageIcon("images/Mike.png"));
			btn14.setBounds(534, 337, 99, 148);
			btn14.setOpaque(false);
			btn14.setContentAreaFilled(false);
			btn14.setBorderPainted(false);
			btn14.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Mike";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b14++;
						if ( (b14 % 2) == 0) {
							btn14.setIcon(new ImageIcon("images/Mike.png"));
						} else {
							btn14.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn14);
			
			JButton btn15=new JButton("");
			btn15.setIcon(new ImageIcon("images/Paul.png"));
			btn15.setBounds(653, 337, 99, 148);
			btn15.setOpaque(false);
			btn15.setContentAreaFilled(false);
			btn15.setBorderPainted(false);
			btn15.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Paul";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b15++;
						if ( (b15 % 2) == 0) {
							btn15.setIcon(new ImageIcon("images/Paul.png"));
						} else {
							btn15.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn15);
			
			JButton btn16=new JButton("");
			btn16.setIcon(new ImageIcon("images/Phil.png"));
			btn16.setBounds(177, 496, 99, 148);
			btn16.setOpaque(false);
			btn16.setContentAreaFilled(false);
			btn16.setBorderPainted(false);
			btn16.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Phil";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b16++;
						if ( (b16 % 2) == 0) {
							btn16.setIcon(new ImageIcon("images/Phil.png"));
						} else {
							btn16.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn16);
			
			JButton btn17=new JButton("");
			btn17.setIcon(new ImageIcon("images/Sam.png"));
			btn17.setBounds(296, 496, 99, 148);
			btn17.setOpaque(false);
			btn17.setContentAreaFilled(false);
			btn17.setBorderPainted(false);
			btn17.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Sam";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b17++;
						if ( (b17 % 2) == 0) {
							btn17.setIcon(new ImageIcon("images/Sam.png"));
						} else {
							btn17.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn17);
			
			JButton btn18=new JButton("");
			btn18.setIcon(new ImageIcon("images/Susan.png"));
			btn18.setBounds(415, 496, 99, 148);
			btn18.setOpaque(false);
			btn18.setContentAreaFilled(false);
			btn18.setBorderPainted(false);
			btn18.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Susan";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b18++;
						if ( (b18 % 2) == 0) {
							btn18.setIcon(new ImageIcon("images/Susan.png"));
						} else {
							btn18.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			mainPanel.add(btn18);
			
			
			JButton btn19=new JButton("");
			btn19.setIcon(new ImageIcon("images/Tom.png"));
			btn19.setBounds(534, 496, 99, 148);
			btn19.setOpaque(false);
			btn19.setContentAreaFilled(false);
			btn19.setBorderPainted(false);
			btn19.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Tom";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b19++;
						if ( (b19 % 2) == 0) {
							btn19.setIcon(new ImageIcon("images/Tom.png"));
						} else {
							btn19.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			//mainPanel.add(btn19);
			
			
			JButton btn20=new JButton("");
			btn20.setIcon(new ImageIcon("images/Jules.png"));
			btn20.setBounds(653, 496, 99, 148);
			btn20.setOpaque(false);
			btn20.setContentAreaFilled(false);
			btn20.setBorderPainted(false);
			btn20.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String who="Jules";
					choose(who, lblYourCharacter, lblCPUCharacter, btnPlay, btnNext);
					if (player_char != 1) {
						b20++;
						if ( (b20 % 2) == 0) {
							btn20.setIcon(new ImageIcon("images/Jules.png"));
						} else {
							btn20.setIcon(new ImageIcon("images/Red.png"));
						}
					}
				}
			});
			//mainPanel.add(btn20);
			
			mainPanel.add(new JLabel("Player character:"));
			
			mainPanel.add(lblYourCharacter);
			
			
			mainPanel.add(btnPlay);
			mainPanel.add(btnNext);
			
			
			mainPanel.add(lblCPUCharacter);
			mainPanel.add(new JLabel(":CPU Character"));

		
		}		
		public static void questions (ArrayList<String> questions, String[] questionsString) {
			
			questions.clear();
			questions.add("Male?"); 		questions.add("Female?");
			questions.add("Light Skin?"); 	questions.add("Dark Skin?");
			questions.add("Black Hair?"); 	questions.add("Brown Hair?"); 
			questions.add("Red Hair?"); 	questions.add("Blonde Hair?"); 
			questions.add("White Hair?"); 	questions.add("Bald?");
			questions.add("Hat?"); 			questions.add("Glasses?"); 
			questions.add("Earrings?"); 	questions.add("Mustache?"); 
			questions.add("Beard?");
			
			questionsString = questions.toArray(questionsString);
		}
		public static void declare (ArrayList<String[]> chars) {
			
			chars.clear();
			//                        Name      Gender      Skin     Hair       Bald   Hat    Glass  Earr   Mous   Beard
			chars.add( new String [] {"Alice", 	"Female", 	"Light", "Red", 	"No",  "No",  "No",  "Yes", "No",  "No"});
			chars.add( new String [] {"Anita", 	"Female", 	"Light", "Blonde", 	"No",  "No",  "No",  "No",  "No",  "No"});
			chars.add( new String [] {"Anne",  	"Female", 	"Light", "Black", 	"No",  "No",  "No",  "Yes", "No",  "No"});
			chars.add( new String [] {"Art",	"Male", 	"Dark",  "Black", 	"No",  "No",  "No",  "No",  "Yes", "No"});
			chars.add( new String [] {"Bill", 	"Male", 	"Dark",  "Black", 	"Yes", "No",  "No",  "No",  "Yes", "Yes"});
			chars.add( new String [] {"Bob",	"Male", 	"Light", "Brown", 	"No",  "No",  "No",  "No",  "No",  "No"});
			chars.add( new String [] {"Brian", 	"Male", 	"Light", "Brown", 	"No",  "Yes", "No",  "No",  "No",  "No"});
			chars.add( new String [] {"Carol", 	"Female", 	"Light", "Red", 	"No",  "Yes", "Yes", "Yes", "No",  "No"});
			chars.add( new String [] {"David", 	"Male", 	"Light", "Blonde",	"No",  "No",  "No",  "No",  "No",  "Yes"});
			chars.add( new String [] {"Eric", 	"Male", 	"Light", "Blonde", 	"No",  "Yes", "No",  "No",  "No",  "No"});
			chars.add( new String [] {"Frank", 	"Male", 	"Light", "Red", 	"No",  "No",  "No",  "No",  "No",  "No"});
			chars.add( new String [] {"Henry", 	"Male", 	"Light", "Red", 	"Yes", "No",  "No",  "No",  "No",  "No"});
			chars.add( new String [] {"Jill", 	"Female", 	"Light", "Blonde", 	"No",  "No",  "Yes", "Yes", "No",  "No"});
			chars.add( new String [] {"Mike", 	"Male", 	"Light", "Brown", 	"No",  "No",  "No",  "No",  "Yes", "No"});
			chars.add( new String [] {"Paul", 	"Male", 	"Light", "White", 	"No",  "No",  "Yes", "No",  "No",  "No"});
			chars.add( new String [] {"Phil", 	"Male", 	"Dark",  "Black", 	"No",  "No",  "No",  "No",  "No",  "Yes"});
			chars.add( new String [] {"Sam", 	"Male", 	"Light", "White", 	"Yes", "No",  "Yes", "No",  "No",  "No"});
			chars.add( new String [] {"Susan", 	"Female", 	"Light", "White", 	"No",  "No",  "No",  "No",  "No",  "No"});
			//chars.add( new String [] {"Tom", 	"Male", 	"Light", "Black", 	"Yes", "No",  "Yes", "No",  "No",  "No"});
			//chars.add( new String [] {"Jules", 	"Male", 	"Dark",  "Black", 	"No",  "No",  "No",  "No",  "Yes",  "No"});
			
		}
		
		private void choose (String who, JLabel lblplayer, JLabel lblcpu, JButton btnAsk, JButton btnGuess) {
			
			player_char++;
			if (player_char == 1) {
				character = who;
				lblplayer.setIcon(new ImageIcon("images/"+who+".png"));
				btnAsk.setEnabled(true);
				btnGuess.setEnabled(true);
			} 
		}
		
		
		
		
		
		public void removeChar(int index, String value, ArrayList<String[]> likovi) {
			for (int i=0; i<likovi.size(); i++) {
				if ((likovi.get(i))[index].equals(value)) {
					likovi.set(i, null);
				}
			}
			likovi.removeAll(Collections.singleton(null));
		}
		public void removeCharHair(int index, String value,ArrayList<String[]> likovi,int n) {
			if(n==1) {
				removeChar(index, value, likovi);
			}else {
				for (int i=0; i<likovi.size(); i++) {
					if (!(likovi.get(i))[index].equals(value)) {
						likovi.set(i, null);
					}
				}
				likovi.removeAll(Collections.singleton(null));
			}
		}
		public void askHair(int index,String hair,String[] cpuAnswer,ArrayList<String[]> characters) {
			int n = JOptionPane.showConfirmDialog(frame,
				    "Does your character have " + hair + " hair?",
				    "CPU Question",
				    JOptionPane.YES_NO_OPTION);
			if(n==1) {				removeCharHair(index, hair, characters, n);
			}else {
				removeCharHair(index, hair, characters, n);
				cpuAnswer[index]=hair;
			}
		}
		public void askQuestion(int index,String question,String[] cpuAnswer,ArrayList<String []> characters) {
			
			String value="";
			int n = JOptionPane.showConfirmDialog(frame,
				    question,
				    "CPU Question",
				    JOptionPane.YES_NO_OPTION);
			if ( n == 1) {
				if(index==3) {
					removeCharHair(index, question.replace(" hair?", ""), characters, n);
					cpuAnswer[index]="";
				}else {
					value = "Yes";
					removeChar(index, value, characters);
					cpuAnswer[index]="No";
				}
				
			} else {
				if(index==3) {
					removeCharHair(index, question.replace(" hair?", ""), characters, n);
					cpuAnswer[index]=question.replace(" hair?", "");
				}else {
				value = "No";
				removeChar(index, value, characters);
				cpuAnswer[index]="Yes";
				}
			}			
		}
		private static void cpuRespond (JFrame frame, String s, String s1, int index) {
			String line="\n";
			if (cpu_choice[index].equals(s.replace(s1,  ""))) {
				
				JOptionPane.showMessageDialog(frame,
						"Question: "+s+line+"Answer: "+"YES",
					    "CPU's Answer",
					    JOptionPane.INFORMATION_MESSAGE);
				
			} else if (!cpu_choice[index].equals(s.replace(s1,  ""))) {
				
				JOptionPane.showMessageDialog(frame,
						"Question: "+s+line+"Answer: "+"NO ",
					    "CPU's Answer",
					    JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		private static void cpuRespondYesNo (JFrame frame, String s, int index) {
			String line="\n";
			String answer="";
			
			if (cpu_choice[index].equals("Yes")) {
				answer="YES";
				JOptionPane.showMessageDialog(frame,
						
						"Question: "+s+line+"Answer: "+answer,
					    "CPU's Answer",
					    JOptionPane.INFORMATION_MESSAGE);
				
			} else if (cpu_choice[index].equals("No")) {
				answer="NO";
				JOptionPane.showMessageDialog(frame,
						
						"Question: "+s+line+"Answer: "+answer,
					    "CPU's Answer",
					    JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
}
