package logica;

import java.util.ArrayList;

/**
 * Representa un polinomio como una composición de monomios y subpolinomios.
 * Maneja operaciones matemáticas complejas incluyendo paréntesis anidados y
 * prioridad de operadores.
 */
public class Polinomio extends Monomio {

	/**
	 * Lista de componentes del polinomio (monomios o subpolinomios)
	 */
	private ArrayList<Monomio> factores;
	private Parentesis parentesis;

	/**
	 * Constructor base para polinomio sin paréntesis
	 */
	public Polinomio(String expresion) {
		super(expresion);
	}

	/**
	 * Constructor con control de paréntesis
	 */
	public Polinomio(String expresion, boolean conParentesis) {
		super(expresion);
		if (conParentesis) {
			parentesis = Parentesis.ABIERTO;
		}
	}

	/**
	 * Imprime la estructura del polinomio por consola (para testeos)
	 */
	@Override
	public void printSelf() {
		System.out.print("" + getOperador() + "( ");
		for (Monomio factor : factores) {
			factor.printSelf();
		}
		System.out.print(" )");
	}

	/**
	 * reinicia el polinomio a estado inicial vacío
	 */
	@Override
	public void clear() {
		super.clear();
		factores = new ArrayList<Monomio>();
		factores.add(new Monomio("")); // Inicia con monomio vacío
		parentesis = Parentesis.NO;
	}

	/**
	 * Retorna el resultado respetando prioridad de operaciones
	 */
	@Override
	public double calcular() {
		// Fase 1: priorizar multiplicaciones y divisiones
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

		// Fase 2: procesar sumas y restas
		double resultado = acumulador;
		for (Monomio factor : factoresProcesados) {
			resultado += factor.calcularCompleto();
		}

		return resultado;
	}

	/**
	 * Inserta un nuevo carácter en la expresión
	 * maneja y valida:
	 * - anidamiento de paréntesis 
	 * - operadores entre términos 
	 * - construcción incremental de monomios
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

		// si hay una operación, insertar otro termino
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
		
		// guardar entrada si todo fue satisfactorio
		if (escribirExpresion)
			expresion += entrada;
	}
	
	//
	// getters
	//
	
	/**
	 * Obtiene el estado actual de construcción del último componente
	 */
	@Override
	public Estado getEstado() {
		Monomio termino = factores.get(factores.size() - 1);

		// si es un subpolinomio, verificar su estado de paréntesis
		if (termino instanceof Polinomio) {
			if (((Polinomio) termino).getParentesis().equals(Parentesis.ABIERTO)) {
				return ((Polinomio) termino).getEstado(); // estado subpolinomio abierto
			}
			return Estado.ENTERO; // subpolinomio cerrado se considera completo
		}

		return termino.getEstado(); // estado del monomio simple
	}

	public Parentesis getParentesis() {
		return parentesis;
	}
}