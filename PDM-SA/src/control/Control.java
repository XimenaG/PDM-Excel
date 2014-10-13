/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author cristhian
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;

public class Control {

    private Archivo Archivo;

    public Control() {
        this.Archivo = new Archivo();
    }

    public void ActualizarMT(DefaultTableModel modeloTransicion) {
        this.Archivo.setMatrizDeTransicion(new MatrizTransicion(modeloTransicion));
    }

    public void ActualizarMC(DefaultTableModel modedeloCostos) {
        this.Archivo.setMatrizCostos(new MatrizCostos(modedeloCostos));

    }

    public void guardarArchivo(DefaultTableModel ModeloMT, DefaultTableModel ModeloMC) {
        this.Archivo.EscribirExcel(ModeloMT, ModeloMC);

    }

    public void NuevoArchivo(JInternalFrame jInternalFrameMAtrizTransicion, JTable jTablaTransicion, JInternalFrame jInternalFrameMatrizCosto, JTable jTablaCosto) {
        this.Archivo.NuevoArchivo(jInternalFrameMAtrizTransicion, jTablaTransicion,
                jInternalFrameMatrizCosto, jTablaCosto);
    }

    public void AbrirArchivo(JInternalFrame jInternalFrameMAtrizTransicion, JTable jTablaTransicion,
            JInternalFrame jInternalFrameMatrizCosto, JTable jTablaCosto) throws IOException {
        try {
            this.Archivo.leerExcel(jInternalFrameMAtrizTransicion, jTablaTransicion, jInternalFrameMatrizCosto, jTablaCosto);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
}
