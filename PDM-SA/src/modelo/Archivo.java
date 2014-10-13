/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static java.awt.image.ImageObserver.WIDTH;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.PanelMDI;

/**
 *
 * @author cristhian
 */
public class Archivo {

    private MatrizTransicion MatrizDeTransicion;
    private MatrizCostos MatrizCostos;
    private DefaultTableModel modeloMTrasicion;
    private DefaultTableModel modeloMCosto;
    private JFileChooser ruta;
    private LeerExcel l;

    public Archivo() {
        MatrizDeTransicion = new MatrizTransicion();
        modeloMTrasicion = new DefaultTableModel();
        MatrizCostos = new MatrizCostos();
        modeloMCosto = new DefaultTableModel();
        ruta = new JFileChooser(".xls");
    }

    public Archivo(DefaultTableModel modeloTransicion, DefaultTableModel modeloCosto) {
        this.MatrizDeTransicion = new MatrizTransicion(modeloCosto);
        this.MatrizCostos = new MatrizCostos(modeloCosto);
        modeloMTrasicion = modeloTransicion;
        modeloMCosto = modeloCosto;
        ruta = new JFileChooser(".xls");
    }

    public DefaultTableModel getModeloMTrasicion() {
        return modeloMTrasicion;
    }

    public void setModeloMTrasicion(DefaultTableModel modeloMTrasicion) {
        this.modeloMTrasicion = modeloMTrasicion;
    }

    public DefaultTableModel getModeloMCosto() {
        return modeloMCosto;
    }

    public void setModeloMCosto(DefaultTableModel modeloMCosto) {
        this.modeloMCosto = modeloMCosto;
    }

    public JFileChooser getRuta() {
        return ruta;
    }

    public void setRuta(JFileChooser ruta) {
        this.ruta = ruta;
    }

    public MatrizTransicion getMatrizDeTransicion() {
        return MatrizDeTransicion;
    }

    
    public void setMatrizDeTransicion(MatrizTransicion MatrizDeTransicion) {
        this.MatrizDeTransicion.setDatos(MatrizDeTransicion.getDatos());
    }

    public MatrizCostos getMatrizCostos() {
        return MatrizCostos;
    }

    public void setMatrizCostos(MatrizCostos MatrizCostos) {
        this.MatrizCostos = MatrizCostos;
    }

    

    /**
     * @param args
     */
    public void EscribirExcel(DefaultTableModel MT, DefaultTableModel ModeloMC) {

        EscribirExcel e = new EscribirExcel(ruta);
        e.escribir(MT, ModeloMC);

    }

    public void leerExcel(JInternalFrame jInternalFrameMAtrizTransicion, JTable jTablaTransicion,
            JInternalFrame jInternalFrameMatrizCosto, JTable jTablaCosto) throws FileNotFoundException, IOException {
        l = new LeerExcel(ruta);
        LeerMT(jInternalFrameMAtrizTransicion, jTablaTransicion);
        LeerMC(jInternalFrameMatrizCosto, jTablaCosto);

    }

    public void LeerMT(JInternalFrame jInternalFrameMAtrizTransicion, JTable jTablaTransicion) throws FileNotFoundException, IOException {

        modeloMTrasicion = l.LeerMT();
        MatrizDeTransicion = new MatrizTransicion(modeloMTrasicion);
        jTablaTransicion.setModel(modeloMTrasicion);
        jInternalFrameMAtrizTransicion.setVisible(true);

    }

    public void LeerMC(JInternalFrame jInternalFrameMatrizCosto, JTable jTablaCosto) throws FileNotFoundException, IOException {

        modeloMCosto = l.LeerMC();
        MatrizCostos = new MatrizCostos(modeloMCosto);
        jTablaCosto.setModel(modeloMCosto);
        jInternalFrameMatrizCosto.setVisible(true);
    }

    public void NuevoArchivo(JInternalFrame jInternalFrameMatrizTransicion, JTable jTablaTransicion,
            JInternalFrame jInternalFrameMatrizCosto, JTable jTablaCosto) {

        montarTablaTransicion();
        jTablaTransicion.setModel(modeloMTrasicion);
        jInternalFrameMatrizTransicion.setVisible(true);

        montarTablaCosto(modeloMTrasicion.getColumnCount() - 1);
        jTablaCosto.setModel(modeloMCosto);
        jInternalFrameMatrizCosto.setVisible(true);

    }

    private void montarTablaTransicion() {
        Vector vector = new Vector(18);

        for (int i = 2; i <= 20; i++) {
            vector.add(i - 2, i);
        }

        JComboBox cantidadEstados = new JComboBox(vector);
        JOptionPane.showMessageDialog(PanelMDI.jDesktopPane, cantidadEstados, "Definir Cantidad De Estados,", WIDTH);
        int nEstados = Integer.parseInt(cantidadEstados.getSelectedItem().toString());
        modeloMTrasicion = new DefaultTableModel(nEstados, nEstados + 2);
        modeloMTrasicion.setDataVector(matrizInicial(nEstados, nEstados + 1), cabeceraModelo(nEstados + 1));

    }

    public Object[] cabeceraModelo(int columnas) {
        Object cabecera[] = new Object[columnas];
        cabecera[0] = "Estados";
        for (int i = 1; i < cabecera.length; i++) {
            cabecera[i] = (char) ('A' + i - 1);
        }
        return cabecera;
    }

    private void montarTablaCosto(int nEstados) {
        Vector vectorCosto = new Vector(18);

        for (int i = 2; i <= 20; i++) {
            vectorCosto.add(i - 2, i);
        }

        JComboBox cantidadDecisiones = new JComboBox(vectorCosto);
        JOptionPane.showMessageDialog(PanelMDI.jDesktopPane, cantidadDecisiones, "Definir Cantidad De Decisiones,", WIDTH);
        int nDecisiones = Integer.parseInt(cantidadDecisiones.getSelectedItem().toString());
        modeloMCosto = new DefaultTableModel(nEstados, nDecisiones + 1);
        modeloMCosto.setDataVector(matrizInicial(nEstados, nDecisiones + 1), cabeceraModeloMCosto(nDecisiones + 1));
    }

    public Object[] cabeceraModeloMCosto(int columnas) {
        Object cabecera[] = new Object[columnas];
        cabecera[0] = "Decisiones";
        for (int i = 1; i < cabecera.length; i++) {
            cabecera[i] = i;
        }
        return cabecera;
    }

    private Object[][] matrizInicial(int x, int y) {

        Object[][] matrizInicial = new Object[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrizInicial[i][j] = 0.0;
            }
        }
        for (int i = 0; i < x; i++) {
            matrizInicial[i][0] = (char) ('A' + i);
        }
        return matrizInicial;
    }
}
