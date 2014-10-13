/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import vista.PanelMDI;

/**
 *
 * @author cristhian
 */
public class EscribirExcel {

    private FileNameExtensionFilter filtro;
    private Workbook libro;
    private Sheet hoja;
    private File archivo;
    private FileOutputStream escribir;
    
    public EscribirExcel(JFileChooser ruta) {
        filtro = new FileNameExtensionFilter("Archivos xls","xls");
        
        ruta.setFileFilter(filtro);
        ruta.showSaveDialog(PanelMDI.jDesktopPane);
        archivo = ruta.getSelectedFile();
        escribir = null;
        libro = new HSSFWorkbook();
        hoja = null;
    }

    public void escribir(DefaultTableModel MT,DefaultTableModel MC) {
        if (archivo.exists()) {
            archivo.delete();
        }
        /*System.out.println(archivo.getAbsolutePath());
        boolean alreadyExists = new File(archivo.getAbsolutePath()).exists();
        if (alreadyExists) {
            File ficheroUsuarios = new File(archivo.getAbsolutePath());
            ficheroUsuarios.delete();
        }
*/    try {
            escribir = new FileOutputStream(archivo);
            hoja= libro.createSheet("M Transicion");
            for (int i = 0; i < MT.getRowCount(); i++) {
                Row fila = hoja.createRow(i);
                for (int j = 1; j < MT.getColumnCount(); j++) {
                    Cell celda = fila.createCell(j - 1);
                    celda.setCellValue(Double.parseDouble(MT.getValueAt(i, j).toString()));
                }
            }
            hoja= libro.createSheet("M Costo");
            for (int i = 0; i < MC.getRowCount(); i++) {
                Row fila = hoja.createRow(i);
                for (int j = 1; j < MC.getColumnCount(); j++) {
                    Cell celda = fila.createCell(j - 1);
                    celda.setCellValue(Double.parseDouble(MC.getValueAt(i, j).toString()));
                }
            }
            libro.write(escribir);
            escribir.close();
            Desktop.getDesktop().open(archivo);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //escribir.close();
        }
    }
}
