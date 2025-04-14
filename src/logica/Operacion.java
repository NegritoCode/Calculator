package logica;


public class Operacion {
	private String numero1;
	private String numero2;
	private String expresion;
	private char operacion;

	/**
	 * Estado global, indica en que numero se esta insertando entradas
	 * n1
	 * n2
	 */
	private String estado1;

	/**
	 * Estado de numero: indica que se esta haciendo en el numero
	 * null          - esta vacio
	 * write         - se esta escribiendo en las unidades
	 * point         - se escribio un punto
	 * write-point   - se esta escribiendo en las decimas
	 */
	private String estado2;

	public Operacion(){
		clear();
	}

	public static boolean isOperacion(char entrada) {
		return entrada == '+' || entrada == '-' || entrada == '*' || entrada == '/';
	}

	public String getNumero1() {
		return numero1;
	}
	public String getNumero2() {
		return numero2;
	}

	public char getOperacion() {
		return operacion;
	}

	public String getExpresion() {
		return expresion;
	}

	/**
	 * Insertar una expresion directamente
	 * @param expresion
	 */
	public void set(String expresion) {
		clear();
		for (int i = 0; i < expresion.length(); i++){
			insertarEntrada(expresion.charAt(i));
		}
	}

	/**
	 * Vaciar operacion
	 */
	public void clear() {
		expresion = "";
		numero1 = null;
		numero2 = null;
		operacion = '+';
		estado1 = "n1";
		estado2 = "null";
	}

	/**
	 * Concatenar una entrada con la expresion
	 */
	public void insertarEntrada(char entrada) {
		switch (estado1) {
		// es el primer numero?
		case "n1":
			if (estado2 == "null") {
				if (Character.isDigit(entrada)){
					numero1 = "" + entrada;
					estado2 = "write";
				}
				else {
					throw new IllegalArgumentException("");
				}
			}

			else if (estado2.equals("write")){
				if (Character.isDigit(entrada)){
					numero1 += entrada;
				}
				else if(entrada == '.'){
					estado2 = "point";
					numero1 += ".";
				}
				else if(isOperacion(entrada)) {
					estado1 = "n2";
					estado2 = "null";
					operacion = entrada;
				}
				else {
					throw new IllegalArgumentException("");
				}
			}

			else if (estado2.equals("point")) {
				if (Character.isDigit(entrada)) {
					numero1 = numero1 + entrada;
					estado2 = "write-point";
				}
				else {
					throw new IllegalArgumentException("");
				}
			}

			else if (estado2.equals("write-point")) {
				if (Character.isDigit(entrada)){
					numero1 += entrada;
				}
				else if(isOperacion(entrada)) {
					estado1 = "n2";
					estado2 = "null";
					operacion = entrada;
				}
				else {
					throw new IllegalArgumentException("");
				}
			}
			break;

			// es el segundo numero?
		case "n2":
			if (estado2 == "null") {
				if (Character.isDigit(entrada)){
					numero2 = "" + entrada;
					estado2 = "write";
				}
				else {
					throw new IllegalArgumentException("");
				}
			}

			else if (estado2.equals("write")){
				if (Character.isDigit(entrada)){
					numero2 += entrada;
				}
				else if(entrada == '.'){
					estado2 = "point";
					numero2 += ".";
				}
				else {
					throw new IllegalArgumentException("");
				}
			}

			else if (estado2.equals("point")) {
				if (Character.isDigit(entrada)) {
					numero2 = numero1 + entrada;
					estado2 = "write-point";
				}
				else {
					throw new IllegalArgumentException("");
				}
			}

			else if (estado2.equals("write-point")) {
				if (Character.isDigit(entrada)){
					numero2 += entrada;
				}
				else {
					throw new IllegalArgumentException("");
				}
			}
		}

		expresion += entrada;
	}

	/**
	 * Obtener resultado
	 */
	public double calcular(){
		double numero1 = Double.parseDouble(this.numero1);
		double numero2 = Double.parseDouble(this.numero2);
		double resultado;

		if (estado1 == "n2" && (estado2 == "write" || estado2 == "write-point")) {
			switch(operacion){
			case '+':
				resultado = numero1 + numero2;
				break;
			case '-':
				resultado = numero1 - numero2;
				break;
			case '*':
				resultado = numero1 * numero2;
				break;
			case '/':
				resultado = numero1 / numero2;
				break;
			default: 
				resultado = numero1;
			}
		}
		else {
			throw new IllegalArgumentException("");
		}

		return resultado;	
	}
}
