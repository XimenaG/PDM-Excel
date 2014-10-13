/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import vista.PanelMDI;

/**
 *
 * @author cristhian
 */
public class LeerExcel {

    private FileNameExtensionFilter filtro;
    private POIFSFileSystem archivo;
    private HSSFWorkbook libro;
    private HSSFSheet hoja;
    private FileInputStream leer;
    private Iterator rowIterator;
    private HSSFRow hssfRow;
    private Iterator iterator;

    public LeerExcel(JFileChooser ruta) throws FileNotFoundException, IOException {
        filtro = new FileNameExtensionFilter("Archivos csv", "csv");
        ruta.setFileFilter(filtro);
        ruta.showSaveDialog(PanelMDI.jDesktopPane);
        leer = new FileInputStream(ruta.getSelectedFile().getPath());
        archivo = new POIFSFileSystem(leer);
        libro = new HSSFWorkbook(archivo);
        hoja = null;
        iterator = null;
        rowIterator = null;
        hssfRow = null;

    }

    public DefaultTableModel LeerMT() {
        DefaultTableModel MT = new DefaultTableModel();

        if (archivo != null) {

            hoja = libro.getSheetAt(0);
            rowIterator = hoja.rowIterator();
            boolean bandera = false;
            MT = new DefaultTableModel();
            while (rowIterator.hasNext()) {
                hssfRow = (HSSFRow) rowIterator.next();
                iterator = hssfRow.cellIterator();
                Vector fila = new Vector();
                fila.add(0);
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    fila.add(Double.parseDouble(hssfCell.toString()));
                   
                }
                System.out.println(fila.size()+" --- ");
                if(bandera== false){
                        MT = new DefaultTableModel(cabeceraModelo(fila.size()), 0);
                        bandera= true;
                }
                
                MT.addRow(fila);
            }
        }
        for(int i = 0 ; i< MT.getRowCount();i++){
        MT.setValueAt((char) ('A' + i ),i,0);
        }
        return MT;
    }
    public DefaultTableModel LeerMC() {
        DefaultTableModel MC = new DefaultTableModel();

        if (archivo != null) {

            hoja = libro.getSheetAt(1);
            rowIterator = hoja.rowIterator();
            boolean bandera = false;
            MC = new DefaultTableModel();
            while (rowIterator.hasNext()) {
                hssfRow = (HSSFRow) rowIterator.next();
                iterator = hssfRow.cellIterator();
                Vector fila = new Vector();
                fila.add(0);
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    fila.add(Double.parseDouble(hssfCell.toString()));
                   
                }
                System.out.println(fila.size()+" --- ");
                if(bandera== false){
                        MC = new DefaultTableModel(cabeceraModeloCosto(fila.size()), 0);
                        bandera= true;
                }
                
                MC.addRow(fila);
            }
        }
        for(int i = 0 ; i< MC.getRowCount();i++){
        MC.setValueAt((char) ('A' + i ),i,0);
        }
        return MC;
    }
    public Object[] cabeceraModelo(int columnas) {
        Object cabecera[] = new Object[columnas];
        cabecera[0] = "Estados";
        for (int i = 1; i < cabecera.length; i++) {
            cabecera[i] = (char) ('A' + i - 1);
        }
        return cabecera;
    }
     public Object[] cabeceraModeloCosto(int columnas) {
        Object cabecera[] = new Object[columnas];
        cabecera[0] = "Decision";
        for (int i = 1; i < cabecera.length; i++) {
            cabecera[i] =  i ;
        }
        return cabecera;
    }
}
