package logica;

import java.util.ArrayList;

/**
 * Representa un polinomio como una colección de monomios y polinomios anidados.
 * Maneja operaciones matemáticas complejas
 */
public class Polinomio extends Monomio {

	/** Lista de factores que componen el polinomio */
	private ArrayList<Monomio> factores;
	private Parentesis parentesis;

	public Polinomio(String expresion) {
		super(expresion);
	}

	public Polinomio(String expresion, boolean conParentesis) {
		super(expresion);

		if (conParentesis) {
			parentesis = Parentesis.ABIERTO;
		}
	}

	@Override
	public void printSelf(String spaces) {
		System.out.print("" + getOperador() + "( ");
		for (Monomio factor : factores) {
			factor.printSelf(spaces + "  ");
		}
		System.out.print(" )");
	}

	@Override
	public Estado getEstado() {
		Monomio termino = factores.get(factores.size() - 1);
		Estado estado = null;

		if (termino instanceof Polinomio) {
			if (((Polinomio) termino).getParentesis().equals(Parentesis.ABIERTO)) {
				estado = ((Polinomio) termino).getEstado();
			} else {
				estado = Estado.ENTERO;
			}
		} else {
			estado = termino.getEstado();
		}

		return estado;
	}

	@Override
	public void clear() {
		super.clear();
		factores = new ArrayList<Monomio>();
		factores.add(new Monomio(""));
		parentesis = Parentesis.NO;
	}

	/**
	 * Calcula el valor numérico del polinomio
	 */
	@Override
	public double calcular() {
		// Primer recorrido: priorizar multiplicaciones y divisiones
		ArrayList<Monomio> factoresProcesados = new ArrayList<Monomio>();
		double acumulador = 0;

		for (Monomio factor : factores) {
			double valor = factor instanceof Polinomio ? ((Polinomio) factor).calcularCompleto() : factor.calcularCompleto();
			char operadorActual = factor.getOperador();

			if (operadorActual == '*' || operadorActual == '/') {
				// realizar la operación inmediatamente
				double anterior = factoresProcesados.get(factoresProcesados.size() - 1).calcularCompleto();
				double resultado = operadorActual == '*' ? anterior * valor : anterior / valor;

				if (factoresProcesados.isEmpty()) {
					acumulador = resultado;
				} else {
					Monomio ultimo = factoresProcesados.get(factoresProcesados.size() - 1);
					ultimo.setExpresion("" + Math.abs(resultado));
					if (resultado < 0) ultimo.setOperador('-');
				}
			} else {
				// guardar para procesar después
				Monomio m = new Monomio("" + Math.abs(valor));
				if (valor < 0) m.setOperador('-');
				factoresProcesados.add(m);
			}
		}

		// Segundo recorrido: procesar sumas y restas
		double resultado = acumulador;
		for (Monomio factor : factoresProcesados) {
			resultado += factor.calcularCompleto();
		}

		return resultado;
	}

	/**
	 * Procesa un nuevo caracter para construir el polinomio maneja anidamiento
	 * automatico para multiplicaciones y divisiones
	 */
	@Override
	public void insertarEntrada(char entrada) {
		int ultimoIndice = factores.size() - 1;
		Monomio ultimoFactor = factores.get(ultimoIndice);
		Estado estadoActual = getEstado();
		boolean escribirExpresion = true;

		// si hay un polinomio sin cerrar, trabajar como su entrada
		if (ultimoFactor instanceof Polinomio
				&& ((Polinomio) ultimoFactor).getParentesis().equals(Parentesis.ABIERTO)) {
			ultimoFactor.insertarEntrada(entrada);
		}

		// aceptar apertura de parentesis
		else if (entrada == '(' && estadoActual.equals(Estado.INICIO)) {
			// nuevo nivel de anidamiento
			// reemplazar el monomio por un polinomio
			Polinomio nuevoNivel = new Polinomio("", true);
			nuevoNivel.setOperador(ultimoFactor.getOperador());
			factores.set(ultimoIndice, nuevoNivel);
		}

		// aceptar cierre de parentesis
		else if (entrada == ')' && (estadoActual.equals(Estado.ENTERO) || estadoActual.equals(Estado.DECIMAL))) {
			if (parentesis.equals(Parentesis.ABIERTO)) {
				parentesis = Parentesis.CERRADO;
				escribirExpresion = false;
			} else {
				throw new IllegalArgumentException("No quedan paréntesis para cerrar.");
			}
		}

		// aceptar un signo negativo al inicio
		else if (entrada == '-' && estadoActual.equals(Estado.INICIO) && ultimoIndice == 0) {
			ultimoFactor.setOperador('-');
		}

		// filtrar operaciones * y /
		else if ((estadoActual.equals(Estado.ENTERO) || estadoActual.equals(Estado.DECIMAL)) && isOperacion(entrada)) {
			// agregar nuevo monomio con el operador
			Monomio nuevoFactor = new Monomio("");
			nuevoFactor.setOperador(entrada);
			factores.add(nuevoFactor);
		}

		// continuar construyendo
		else {
			ultimoFactor.insertarEntrada(entrada);
		}

		if (escribirExpresion)
			expresion += entrada;
	}

	public Parentesis getParentesis() {
		return parentesis;
	}
}