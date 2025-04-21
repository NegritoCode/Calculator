 package gui;

 import javax.swing.*;
import logica.Polinomio;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.Operation;
import init.Iniciadora;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class Calculadora extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Polinomio operacion;
    
    public void render() {
		String expresion = operacion.getExpresion();
		System.out.println("");
		operacion.printSelf("");
    	textField.setText(expresion.length() == 0 ? "0" : expresion);
    }

	/**
	 * Create the frame.
	 */
	public Calculadora() {
		operacion = new Polinomio("");
		
		setResizable(false);
		setTitle("Calculadora");
		setFont(new Font("Arial Black", Font.PLAIN, 12));
		setForeground(Color.RED);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(77, 12, 190, 49);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBackground(Color.GRAY);
		textField.setForeground(Color.WHITE);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		JButton parentesisIzq = new JButton("(");
		parentesisIzq.setBounds(12, 65, 61, 42);
		parentesisIzq.setFont(new Font("Tahoma", Font.PLAIN, 16));
		parentesisIzq.setForeground(Color.WHITE);
		parentesisIzq.setBackground(Color.DARK_GRAY);
		parentesisIzq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		parentesisIzq.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPane.add(parentesisIzq);

		JButton btnCe = new JButton("CE");
		btnCe.setBounds(12, 16, 60, 42);
		btnCe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.clear();
					render();
				} catch(Exception e) {
					
				}
			}
		});
		btnCe.setForeground(Color.WHITE);
		btnCe.setBackground(Color.DARK_GRAY);
		btnCe.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPane.add(btnCe);

		JButton btnC = new JButton("C");
		btnC.setBounds(141, 65, 62, 42);
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnC.setForeground(Color.WHITE);
		btnC.setBackground(Color.DARK_GRAY);
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.clear();
					render();
				} catch(Exception e) {}
			}
		});
		contentPane.add(btnC);

		JButton btnErase = new JButton("<--");
		btnErase.setBounds(12, 249, 61, 42);
		btnErase.setBackground(Color.BLACK);
		btnErase.setForeground(Color.WHITE);
		btnErase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnErase.setActionCommand("ERASE");
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String expresion = operacion.getExpresion();
					if (expresion.length() > 0) {
						operacion.set(expresion.substring(0, expresion.length() - 1));
						render();
					}
				} catch (Exception e) {}
			}
		});
		contentPane.add(btnErase);

		JButton btn7 = new JButton("7");
		btn7.setBounds(12, 111, 61, 42);
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('7');
					render();
				} catch (Exception e) {}
			}
		});
		btn7.setBackground(Color.BLACK);
		btn7.setForeground(Color.WHITE);
		contentPane.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.setBounds(77, 111, 60, 42);
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('8');
					render();
				} catch (Exception e) {}
			}
		});
		btn8.setForeground(Color.WHITE);
		btn8.setBackground(Color.BLACK);
		contentPane.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.setBounds(141, 111, 62, 42);
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('9');
					render();
				} catch (Exception e) {}
			}
		});
		btn9.setForeground(Color.WHITE);
		btn9.setBackground(Color.BLACK);
		contentPane.add(btn9);

		JButton btn4 = new JButton("4");
		btn4.setBounds(12, 157, 61, 42);
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('4');
					render();
				} catch (Exception e) {}
			}
		});
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		contentPane.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.setBounds(77, 157, 60, 42);
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('5');
					render();
				} catch (Exception e) {}
			}
		});
		btn5.setForeground(Color.WHITE);
		btn5.setBackground(Color.BLACK);
		contentPane.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.setBounds(141, 157, 62, 42);
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('6');
					render();
				} catch (Exception e) {}
			}
		});
		btn6.setForeground(Color.WHITE);
		btn6.setBackground(Color.BLACK);
		contentPane.add(btn6);

		JButton btn1 = new JButton("1");
		btn1.setBounds(12, 203, 61, 42);
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('1');
					render();
				} catch (Exception e) {}
			}
		});
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		contentPane.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setBounds(77, 203, 60, 42);
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('2');
					render();
				} catch (Exception e) {}
			}
		});
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		contentPane.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setBounds(141, 203, 62, 42);
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('3');
					render();
				} catch (Exception e) {}
			}
		});
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.BLACK);
		contentPane.add(btn3);

		JButton btnDiv = new JButton("/");
		btnDiv.setBounds(207, 65, 60, 42);
		btnDiv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('/');
					render();
				} catch (Exception e) {}
			}
		});
		btnDiv.setBackground(Color.DARK_GRAY);
		btnDiv.setForeground(Color.WHITE);
		contentPane.add(btnDiv);

		JButton btnMult = new JButton("*");
		btnMult.setBounds(207, 111, 60, 42);
		btnMult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('*');
					render();
				} catch (Exception e) {}
			}
		});
		btnMult.setBackground(Color.DARK_GRAY);
		btnMult.setForeground(Color.WHITE);
		contentPane.add(btnMult);

		JButton btnResta = new JButton("-");
		btnResta.setBounds(207, 157, 60, 42);
		btnResta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnResta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('-');
					render();
				} catch (Exception e) {}
			}
		});
		btnResta.setBackground(Color.DARK_GRAY);
		btnResta.setForeground(Color.WHITE);
		contentPane.add(btnResta);

		JButton btnSuma = new JButton("+");
		btnSuma.setBounds(207, 203, 60, 42);
		btnSuma.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSuma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('+');
					render();
				} catch (Exception e) {}
			}
		});
		btnSuma.setBackground(Color.DARK_GRAY);
		btnSuma.setForeground(Color.WHITE);
		contentPane.add(btnSuma);

		JButton btn0 = new JButton("0");
		btn0.setBounds(77, 249, 60, 42);
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('0');
					render();
				} catch (Exception e) {}
			}
		});
		btn0.setForeground(Color.WHITE);
		btn0.setBackground(Color.BLACK);
		contentPane.add(btn0);

		JButton btnPunto = new JButton(".");
		btnPunto.setBounds(141, 249, 62, 42);
		btnPunto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('.');
					render();
				} catch (Exception e) {}
			}
		});
		btnPunto.setForeground(Color.WHITE);
		btnPunto.setBackground(Color.BLACK);
		contentPane.add(btnPunto);

		JButton btnIgual = new JButton("=");
		btnIgual.setBounds(207, 249, 60, 42);
		btnIgual.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.set(operacion.calcular() + "");
					render();
				} catch (Exception e) {}
			}
		});
		btnIgual.setBackground(Color.RED);
		btnIgual.setForeground(Color.WHITE);
		contentPane.add(btnIgual);
		
		JButton button = new JButton(")");
		button.setBounds(76, 65, 61, 42);
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.WHITE);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 322, 255, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 5, 218, 77);
		panel.add(textArea);
		textArea.setEditable(false);
		final JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(228, 5, 17, 77);
		panel.add(scrollBar);
		
		JButton btnSh = new JButton("H");
		btnSh.setBackground(Color.DARK_GRAY);
		btnSh.setForeground(Color.WHITE);
		btnSh.setSelectedIcon(new ImageIcon(Calculadora.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-more-details@2x.png")));
		btnSh.setBounds(12, 294, 61, 23);
		contentPane.add(btnSh);
		 btnSh.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                scrollBar.setVisible(!scrollBar.isVisible()); // Alternar visibilidad
	                revalidate(); // Actualizar el layout
	            }
	        });
	}
}
