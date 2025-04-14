
package logica;

import java.util.ArrayList;
import logica.Monomio.Estado;

/**
 * Representa un polinomio como una colección de monomios y polinomios anidados.
 * maneja operaciones matemáticas complejas
 */
public class Polinomio extends Monomio {

    /** Lista de factores que componen el polinomio */
    private ArrayList<Monomio> factores;

    public Polinomio(String expresion) {
        super(expresion);
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
        // el estado del polinomio es el estado de su ultimo termino
        return factores.get(factores.size() - 1).getEstado();
    }

    @Override
    public void clear() {
        super.clear();
        factores = new ArrayList<Monomio>();
        factores.add(new Monomio(""));
    }

    /**
     * Calcula el valor numérico del polinomio
     */
    @Override
    public double calcular() {
        double result = 0;

        for (Monomio factor : factores) {
            double parcial = factor instanceof Polinomio
                    ? ((Polinomio) factor).calcular()
                    : factor.calcular();

            switch (factor.getOperador()) {
                case '+':
                    result += parcial;
                    break;
                case '-':
                    result -= parcial;
                    break;
                case '*':
                    result *= parcial;
                    break;
                case '/':
                    result /= parcial;
                    break;
            }
        }

        return result;
    }

    /**
     * Procesa un nuevo caracter para construir el polinomio
     * maneja anidamiento automatico para multiplicaciones y divisiones
     */
    @Override
    public void insertarEntrada(char entrada) {
        int ultimoIndice = factores.size() - 1;
        Monomio ultimoFactor = factores.get(ultimoIndice);
        Estado estadoActual = getEstado();

        if (estadoActual.equals(Estado.INICIO) && ultimoIndice == 0 && entrada == '-') {
            ultimoFactor.setOperador('-');
        } else if ((estadoActual.equals(Estado.ENTERO) || estadoActual.equals(Estado.DECIMAL))
                && isOperacion(entrada)) {
            if (ultimoIndice > 0 && (entrada == '*' || entrada == '/')) {
                // nuevo nivel de anidamiento para pririzar las multiplicaciones
                Polinomio nuevoNivel = new Polinomio(ultimoFactor.getExpresion() + entrada);
                nuevoNivel.setOperador(ultimoFactor.getOperador());
                factores.set(ultimoIndice, nuevoNivel);
            } else {
                // agregar nuevo monomio con el operador
                Monomio nuevoFactor = new Monomio("");
                nuevoFactor.setOperador(entrada);
                factores.add(nuevoFactor);
            }
        } else {
            // continuar construyendo
            ultimoFactor.insertarEntrada(entrada);
        }

        expresion += entrada;
    }
}