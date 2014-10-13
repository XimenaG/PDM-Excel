/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cristhian
 */
public class MatrizTransicion extends Matriz{

    public MatrizTransicion() {
        super();
    }

    public MatrizTransicion(DefaultTableModel modelo) {
        super(modelo);
    }
    
    public Matriz por(Matriz b) {
        int i, j;
        double suma;
        if (this.ncol == b.nren) {
            Matriz resul = new Matriz();
            resul.inicializa(this.nren, b.ncol);
            for (i = 0; i < resul.nren; i++) {
                for (j = 0; j < resul.ncol; j++) {
                    suma = 0;
                    for (int k = 0; k < this.ncol; k++) {
                        suma += this.datos[i][k] * b.datos[k][j];
                    }
                    resul.datos[i][j] = suma;
                }
            }
            return resul;
        } else {
            System.out.println("Matrices con diferente tamano");
        }
        return null;
    }
    
}