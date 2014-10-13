package modelo;

import javax.swing.table.DefaultTableModel;

public class Matriz {
    public int nren, ncol;
    public double datos[][];
    /**
     * Crea una matriz nula
     *
     */
    public Matriz() {
        this.nren = 0;
        this.ncol = 0;
        inicializa(nren, ncol);
    }
    public void inicializa(int r, int c) {
        // Si la matriz es nula reserva memoria.
        if ((nren == 0 && ncol == 0) || this == null) {
            this.nren = r;
            this.ncol = c;
            this.datos = new double[this.nren][this.ncol];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    this.datos[i][j] = 0;
                }
            }
        }
    }
    public Matriz(DefaultTableModel modelo) {
        // crea una matriz con n renglones y m columnas

        this.nren = modelo.getRowCount();
        this.ncol = modelo.getColumnCount()-1;
        System.out.println("nren = " +nren+"ncol = "+ncol );    

        this.datos = new double[this.nren][this.ncol];
        for (int i = 0; i < nren; i++) {
            for (int j = 0; j < ncol; j++) {
                datos[i][j] = Double.parseDouble(modelo.getValueAt(i,j+1).toString());
            }
        }      
    }
    /**
     * Imprime el contenido en una matriz
     */
    public String imprime() {
        int i, j;
        double aux;
        String res = "";
        if ((nren == 0) && (ncol == 0)) {

            return "No tiene informacion la matriz";
        }

        for (i = 0; i < this.nren; i++) {
            for (j = 0; j < this.ncol; j++) {
                res += Double.parseDouble(redondea(datos[i][j], 4)) + "\t";
            }
            res += "\n";
        }
        return res;
    }
    /**
     * Rutina para redondear un numero
     *
     * @param dato valor a redondear
     * @param max tamano del numero a imprimir
     */
    
    String redondea(double dato, int max) {
        String salida = "", aux = "";
        float a;

        a = (Math.round(dato * 10000)) / 10000.0f;

        aux += a;
        max -= aux.length();
        for (int i = 0; i < max; i++) {
            salida += " ";
        }
        salida += aux;
        return salida;
    }
    public double getDato(int i, int j) {
        return this.datos[i][j];
    }
    
    
    
    public void inserta(int i, int j, double d) {
        this.datos[i][j] = d;
    }
    public double[][] getDatos() {
        return datos;
    }
    public void setDatos(double[][] datos) {
        this.datos = datos;
    }
    public int getNren() {
        return nren;
    }
    public void setNren(int nren) {
        this.nren = nren;
    }
    public int getNcol() {
        return ncol;
    }
    public void setNcol(int ncol) {
        this.ncol = ncol;
    }
    
    
}