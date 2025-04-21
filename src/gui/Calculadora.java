package gui;

import javax.swing.*;

import logica.Polinomio;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Calculadora extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Polinomio operacion;
	private DefaultListModel<String> historialOperaciones; // Lista para almacenar el historial
	private JList<String> historialList;
	private JMenuBar menuBar; // Barra de menú superior

	public void render() {
		String expresion = operacion.getExpresion();
		System.out.println("");
		operacion.printSelf("");
		textField.setText(expresion.length() == 0 ? "0" : expresion);
	}

	private void abrirURL(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "No se pudo abrir el enlace: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void mostrarAcercaDe() {
		String mensaje = "<html><body width='300px'>" + "<h1>Calculadora Polinómica</h1>"
				+ "<p><b>Versión:</b> 1.6</p><br>" + "<p><b>Desarrolladores:</b><br>" + "- Rodny Estrada<br>"
				+ "- Martin Alejandro</p><br>" + "<p><b>Grupo:</b> 11<br>" + "<b>Curso:</b> 2024-2025<br>"
				+ "<b>Carrera:</b> Ingeniería Informática</p>" + "<p><b>Repositorio:</b><br>"
				+ "<a href='https://github.com/NegritoCode/Calculator'>NegritoCode/Calculator</a></p>"
				+ "<p style='text-align:center; margin-top:20px;'>© Todos los derechos reservados</p>"
				+ "</body></html>";

		JOptionPane.showMessageDialog(this, mensaje, "Acerca de la Calculadora", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Create the frame.
	 */
	public Calculadora() {
		operacion = new Polinomio("");
		historialOperaciones = new DefaultListModel<String>(); // Inicializar el historial

		setResizable(false);
		setTitle("Calculadora");
		setFont(new Font("Arial Black", Font.PLAIN, 12));
		setForeground(Color.RED);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 294, 369);

		// barra de menú
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// menú Historial
		JMenu mnHistorial = new JMenu("Historial");
		menuBar.add(mnHistorial);

		// opción para limpiar historial
		JMenuItem mntmLimpiarHistorial = new JMenuItem("Limpiar Historial");
		mntmLimpiarHistorial.setIcon(
				new ImageIcon(Calculadora.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		mntmLimpiarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historialOperaciones.clear();
				JOptionPane.showMessageDialog(null, "Historial limpiado", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHistorial.add(mntmLimpiarHistorial);

		JSeparator separator = new JSeparator();
		mnHistorial.add(separator);

		historialList = new JList<String>(historialOperaciones);
		historialList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String seleccionado = historialList.getSelectedValue();
					if (seleccionado != null) {
						operacion.setExpresion(seleccionado);
					}
				}
			}
		});
		mnHistorial.add(historialList);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmVerEnGithub = new JMenuItem("Ver en Github");
		mntmVerEnGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirURL("https://github.com/NegritoCode/Calculator");
			}
		});
		mntmVerEnGithub.setIcon(
				new ImageIcon(Calculadora.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		mnAyuda.add(mntmVerEnGithub);

		JMenuItem mntmCrditos = new JMenuItem("Créditos");
		mntmCrditos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarAcercaDe();
			}
		});
		mntmCrditos.setIcon(
				new ImageIcon(Calculadora.class.getResource("/com/sun/javafx/webkit/prism/resources/panIcon.png")));
		mnAyuda.add(mntmCrditos);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(12, 23, 255, 49);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBackground(Color.GRAY);
		textField.setForeground(Color.WHITE);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);

		JButton btnC = new JButton("C");
		btnC.setBounds(12, 76, 62, 42);
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnC.setForeground(Color.WHITE);
		btnC.setBackground(new Color(246, 97, 81));
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.clear();
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnC);

		JButton btnErase = new JButton("");
		btnErase.setIcon(new ImageIcon(
				Calculadora.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/enter-icon.png")));
		btnErase.setBounds(12, 260, 61, 42);
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnErase);

		JButton btn1 = new JButton("1");
		btn1.setBounds(12, 214, 61, 42);
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('1');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		contentPane.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setBounds(77, 214, 60, 42);
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('2');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.BLACK);
		contentPane.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setBounds(141, 214, 62, 42);
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('3');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.BLACK);
		contentPane.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.setBounds(12, 168, 61, 42);
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('4');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		contentPane.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.setBounds(77, 168, 60, 42);
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('5');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn5.setForeground(Color.WHITE);
		btn5.setBackground(Color.BLACK);
		contentPane.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.setBounds(141, 168, 62, 42);
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('6');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn6.setForeground(Color.WHITE);
		btn6.setBackground(Color.BLACK);
		contentPane.add(btn6);

		JButton btn7 = new JButton("7");
		btn7.setBounds(12, 122, 61, 42);
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('7');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn7.setBackground(Color.BLACK);
		btn7.setForeground(Color.WHITE);
		contentPane.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.setBounds(77, 122, 60, 42);
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('8');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn8.setForeground(Color.WHITE);
		btn8.setBackground(Color.BLACK);
		contentPane.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.setBounds(141, 122, 62, 42);
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('9');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn9.setForeground(Color.WHITE);
		btn9.setBackground(Color.BLACK);
		contentPane.add(btn9);

		JButton parIzq = new JButton("(");
		parIzq.setBounds(76, 76, 61, 42);
		parIzq.setFont(new Font("Tahoma", Font.PLAIN, 16));
		parIzq.setForeground(Color.WHITE);
		parIzq.setBackground(Color.DARK_GRAY);
		parIzq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('(');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		parIzq.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPane.add(parIzq);

		JButton parDer = new JButton(")");
		parDer.setHorizontalTextPosition(SwingConstants.CENTER);
		parDer.setFont(new Font("Dialog", Font.PLAIN, 16));
		parDer.setBounds(142, 76, 61, 42);
		parDer.setBackground(Color.DARK_GRAY);
		parDer.setForeground(Color.WHITE);
		parDer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada(')');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(parDer);

		JButton btnMult = new JButton("*");
		btnMult.setBounds(207, 122, 60, 42);
		btnMult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('*');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnMult.setBackground(Color.DARK_GRAY);
		btnMult.setForeground(Color.WHITE);
		contentPane.add(btnMult);

		JButton btnDiv = new JButton("/");
		btnDiv.setBounds(207, 76, 60, 42);
		btnDiv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('/');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDiv.setBackground(Color.DARK_GRAY);
		btnDiv.setForeground(Color.WHITE);
		contentPane.add(btnDiv);

		JButton btnResta = new JButton("-");
		btnResta.setBounds(207, 168, 60, 42);
		btnResta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnResta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('-');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnResta.setBackground(Color.DARK_GRAY);
		btnResta.setForeground(Color.WHITE);
		contentPane.add(btnResta);

		JButton btnSuma = new JButton("+");
		btnSuma.setBounds(207, 214, 60, 42);
		btnSuma.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSuma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('+');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSuma.setBackground(Color.DARK_GRAY);
		btnSuma.setForeground(Color.WHITE);
		contentPane.add(btnSuma);

		JButton btn0 = new JButton("0");
		btn0.setBounds(77, 260, 60, 42);
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('0');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn0.setForeground(Color.WHITE);
		btn0.setBackground(Color.BLACK);
		contentPane.add(btn0);

		JButton btnPunto = new JButton(".");
		btnPunto.setBounds(141, 260, 62, 42);
		btnPunto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					operacion.insertarEntrada('.');
					render();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnPunto.setForeground(Color.WHITE);
		btnPunto.setBackground(Color.BLACK);
		contentPane.add(btnPunto);

		JButton btnIgual = new JButton("=");
		btnIgual.setBounds(207, 260, 60, 42);
		btnIgual.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String expresion = operacion.getExpresion();
					String resultado = operacion.calcular() + "";
					operacion.set(resultado);
					render();

					// Agregar al historial
					historialOperaciones.addElement(expresion);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnIgual.setBackground(new Color(38, 162, 105));
		btnIgual.setForeground(Color.WHITE);
		contentPane.add(btnIgual);
	}
}