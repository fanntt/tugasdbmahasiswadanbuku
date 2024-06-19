/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fanntt_14062024.controller;

import fanntt_14062024.dao.BukuDb;
import fanntt_14062024.model.Buku;
import fanntt_14062024.view.FormBukuDb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class BukuControllerDb {
    FormBukuDb formBukuDb;
    Buku buku;
    BukuDb  bukuDb;
    
    public BukuControllerDb(FormBukuDb formBukuDb){
        this.formBukuDb = formBukuDb;
        bukuDb = new BukuDb();
    }

    public void cancel(){
        formBukuDb.getTxtKode().setText("");
        formBukuDb.getTxtJudul().setText("");
        formBukuDb.getTxtPengarang().setText("");
        formBukuDb.getTxtTahun().setText("");
    } 
    
    
    
    public void insert(){
        try {
            buku = new Buku();
            buku.setKodeBuku(formBukuDb.getTxtKode().getText());
            buku.setJudulBuku(formBukuDb.getTxtJudul().getText());
            buku.setPengarang(formBukuDb.getTxtPengarang().getText());
            buku.setTahunTerbit(formBukuDb.getTxtTahun().getText());
            bukuDb.insert(buku);
            JOptionPane.showMessageDialog(formBukuDb, "Entri Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formBukuDb, "Error  "+ex.getMessage());
        }
    }
    
    public void viewData(){
        try {
            DefaultTableModel model = (DefaultTableModel)formBukuDb.getTabelBuku().getModel();
            model.setNumRows(0);
            List<Buku> list = bukuDb.getAllBuku();
            for(Buku buku : list){
                Object[] data = {
                    buku.getKodeBuku(),
                    buku.getJudulBuku(),
                    buku.getPengarang(),
                    buku.getTahunTerbit()
                };
                model.addRow(data);
            }
        } catch (Exception ex) {
            Logger.getLogger(BukuControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void actionClickTabel(){
        try {
            String kodebuku = formBukuDb.getTabelBuku()
                    .getValueAt(formBukuDb.getTabelBuku().getSelectedRow(), 0).toString();
            buku = bukuDb.getBuku(kodebuku);
            formBukuDb.getTxtKode().setText(buku.getKodeBuku());
            formBukuDb.getTxtJudul().setText(buku.getJudulBuku());
            formBukuDb.getTxtPengarang().setText(buku.getPengarang());
            formBukuDb.getTxtTahun().setText(buku.getTahunTerbit());
        } catch (Exception ex) {
            Logger.getLogger(BukuControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        
        try {
            buku.setKodeBuku(formBukuDb.getTxtKode().getText());
            buku.setJudulBuku(formBukuDb.getTxtJudul().getText());
            buku.setPengarang(formBukuDb.getTxtPengarang().getText());
            buku.setTahunTerbit(formBukuDb.getTxtTahun().getText());
            bukuDb.update(buku);
            JOptionPane.showMessageDialog(formBukuDb, "Update Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(BukuControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            String kodebuku= formBukuDb.getTabelBuku()
                    .getValueAt(formBukuDb.getTabelBuku().getSelectedRow(), 0).toString();
            bukuDb.delete(kodebuku);
            JOptionPane.showMessageDialog(formBukuDb, "Delete Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(BukuControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
