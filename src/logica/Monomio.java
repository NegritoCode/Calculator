package logica;

/**
 * Representa un único término de una expresión matemática.
 * Un monomio puede ser un número entero o decimal, precedido por un operador.
 */
public class Monomio {

    private Estado estado;
    private char operador; // Operador por defecto
    protected String expresion;

    /**
     * Constructor que inicializa el monomio con una expresión dada.
     * @param expresion La expresión inicial del monomio
     */
    public Monomio(String expresion) {
        setExpresion(expresion);
    }
    
    //
    // Métodos públicos
    //
    
    /**
     * Reinicia el monomio a su estado inicial vacío.
     */
    public void clear() {
        expresion = "";
        operador = '+';
        estado = Estado.INICIO;
    }

    /**
     * Procesa un nuevo carácter para construir la expresión.
     * Valida el carácter según el estado de construcción actual.
     * @param entrada El carácter a insertar
     * @throws IllegalArgumentException Si el carácter no es válido en el estado actual
     */
    public void insertarEntrada(char entrada) {
        switch (estado) {
            case INICIO:
                if (Character.isDigit(entrada)) {
                    expresion += entrada;
                    estado = Estado.ENTERO;
                } else {
                    throw new IllegalArgumentException("Se esperaba un dígito inicial");
                }
                break;

            case ENTERO:
                if (Character.isDigit(entrada)) {
                    expresion += entrada;
                } else if (entrada == '.') {
                    expresion += entrada;
                    estado = Estado.PUNTO;
                } else {
                    throw new IllegalArgumentException("Se esperaba dígito o punto decimal");
                }
                break;

            case PUNTO:
                if (Character.isDigit(entrada)) {
                    expresion += entrada;
                    estado = Estado.DECIMAL;
                } else {
                    throw new IllegalArgumentException("Se esperaba dígito después del punto");
                }
                break;

            case DECIMAL:
                if (Character.isDigit(entrada)) {
                    expresion += entrada;
                } else {
                    throw new IllegalArgumentException("Se esperaba dígito decimal");
                }
                break;
        }
    }

    /**
     * Calcula el valor numérico absoluto del monomio.
     * @return El valor numérico del monomio
     * @throws IllegalStateException Si la expresión está vacía
     */
    public double calcular() {
        if (getExpresion().isEmpty()) {
            throw new IllegalStateException("La expresión del monomio está vacía. Imposible calcular");
        }
        return Double.parseDouble(getExpresion());
    }

    /**
     * Retorna el valor del monomio incluyendo el signo del operador
     */
    public double calcularCompleto() {
        return calcular() * (getOperador() == '-' ? -1 : 1);
    }

    /**
     * Imprime el monomio mostrando primero su operador previo.
     * @param spaces Espacios para formato (no utilizado actualmente)
     */
    public void printSelf() {
        System.out.print(getOperador() + getExpresion());
    }
    
    //
    // Métodos de acceso (getters y setters)
    //
    
    public String getExpresion() {
        return expresion;
    }

    public final void setExpresion(String expresion) {
        clear();
        for (int i = 0; i < expresion.length(); i++) {
            insertarEntrada(expresion.charAt(i));
        }
    }

    public Estado getEstado() {
        return estado;
    }

    public final char getOperador() {
        return operador;
    }

    public final void setOperador(char operador) {
        if (isOperacion(operador)) {
            this.operador = operador;
        } else {
            throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }
    
    //
    // Métodos estáticos públicos
    //
    
    /**
     * Determina si un carácter es una operación matemática válida.
     * @param entrada El carácter a evaluar
     * @return true si es una operación válida, false en caso contrario
     */
    public static boolean isOperacion(char entrada) {
        return entrada == '+' || entrada == '-' || entrada == '*' || entrada == '/';
    }
}