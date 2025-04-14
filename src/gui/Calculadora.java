package gui;

import logica.Operacion;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Calculadora extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Operacion operacion;

    public void render() {
    	textField.setText(operacion.getExpresion());
    }

	/**
	 * Create the frame.
	 */
	public Calculadora() {
		operacion = new Operacion();

		setResizable(false);
		setTitle("Calculadora");
		setFont(new Font("Arial Black", Font.PLAIN, 12));
		setForeground(Color.RED);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 285, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[67px][67px][68px][67px]", "[53px][46px][46px][46px][46px][46px]"));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBackground(Color.GRAY);
		textField.setForeground(Color.WHITE);
		contentPane.add(textField, "cell 0 0 4 1,grow");
		textField.setColumns(10);

		JButton button = new JButton("%");
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setForeground(Color.WHITE);
		button.setBackground(Color.DARK_GRAY);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPane.add(button, "cell 0 1,grow");

		JButton btnCe = new JButton("CE");
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
		contentPane.add(btnCe, "cell 1 1,grow");

		JButton btnC = new JButton("C");
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
		contentPane.add(btnC, "cell 2 1,grow");

		JButton btnErase = new JButton("<--");
		btnErase.setBackground(Color.BLACK);
		btnErase.setForeground(Color.WHITE);
		btnErase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnErase.setActionCommand("ERASE");
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String expresion = operacion.getExpresion();
					if (expresion.length() > 0) {
						operacion.set(expresion.substring(0, expresion.length() - 2));
						render();
					}
				} catch (Exception e) {}
			}
		});
		contentPane.add(btnErase, "cell 0 5,grow");

		JButton btn7 = new JButton("7");
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('7');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn7.setBackground(Color.BLACK);
		btn7.setForeground(Color.WHITE);
		contentPane.add(btn7, "cell 0 2,grow");

		JButton btn8 = new JButton("8");
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('8');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn8.setForeground(Color.WHITE);
		btn8.setBackground(Color.BLACK);
		contentPane.add(btn8, "cell 1 2,grow");

		JButton btn9 = new JButton("9");
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('9');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn9.setForeground(Color.WHITE);
		btn9.setBackground(Color.BLACK);
		contentPane.add(btn9, "cell 2 2,grow");

		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('4');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		contentPane.add(btn4, "cell 0 3,grow");

		JButton btn5 = new JButton("5");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('5');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn5.setForeground(Color.WHITE);
		btn5.setBackground(Color.BLACK);
		contentPane.add(btn5, "cell 1 3,grow");

		JButton btn6 = new JButton("6");
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('6');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn6.setForeground(Color.WHITE);
		btn6.setBackground(Color.BLACK);
		contentPane.add(btn6, "cell 2 3,grow");

		JButton btn1 = new JButton("1");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('1');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		contentPane.add(btn1, "cell 0 4,grow");

		JButton btn2 = new JButton("2");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('2');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		contentPane.add(btn2, "cell 1 4,grow");

		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('3');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.BLACK);
		contentPane.add(btn3, "cell 2 4,grow");

		JButton btnDiv = new JButton("/");
		btnDiv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('/');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btnDiv.setBackground(Color.DARK_GRAY);
		btnDiv.setForeground(Color.WHITE);
		contentPane.add(btnDiv, "cell 3 1,grow");

		JButton btnMult = new JButton("*");
		btnMult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('*');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btnMult.setBackground(Color.DARK_GRAY);
		btnMult.setForeground(Color.WHITE);
		contentPane.add(btnMult, "cell 3 2,grow");

		JButton btnResta = new JButton("-");
		btnResta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnResta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('-');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btnResta.setBackground(Color.DARK_GRAY);
		btnResta.setForeground(Color.WHITE);
		contentPane.add(btnResta, "cell 3 3,grow");

		JButton btnSuma = new JButton("+");
		btnSuma.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSuma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('+');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {
					throw e;
				}
			}
		});
		btnSuma.setBackground(Color.DARK_GRAY);
		btnSuma.setForeground(Color.WHITE);
		contentPane.add(btnSuma, "cell 3 4,grow");

		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('0');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btn0.setForeground(Color.WHITE);
		btn0.setBackground(Color.BLACK);
		contentPane.add(btn0, "cell 1 5,grow");

		JButton btnPunto = new JButton(".");
		btnPunto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('.');
					textField.setText(operacion.getExpresion());
				} catch (Exception e) {}
			}
		});
		btnPunto.setForeground(Color.WHITE);
		btnPunto.setBackground(Color.BLACK);
		contentPane.add(btnPunto, "cell 2 5,grow");

		JButton btnIgual = new JButton("=");
		btnIgual.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					textField.setText("" + operacion.calcular());
				} catch (Exception e) {}
			}
		});
		btnIgual.setBackground(Color.RED);
		btnIgual.setForeground(Color.WHITE);
		contentPane.add(btnIgual, "cell 3 5,grow");
	}
}
