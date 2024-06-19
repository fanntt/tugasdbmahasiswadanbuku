/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanntt_14062024.controller;

import fanntt_14062024.model.Buku;
import fanntt_14062024.model.BukuDao;
import fanntt_14062024.view.FormBukuDbOriginal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class BukuController {
    FormBukuDbOriginal view;
    Buku model;
    BukuDao dao;
    int index;
    
    public BukuController(FormBukuDbOriginal view){
        this.view = view;
        dao = new BukuDao();
    }

    
    
    public void clearView(){
        view.getTxtKode().setText("");
        view.getTxtJudul().setText("");
        view.getTxtPengarang().setText("");
        view.getTxtTahun().setText("");                      
    } 
    
    public void insert(){
        model = new Buku();
        model.setKodeBuku(view.getTxtKode().getText());
        model.setJudulBuku(view.getTxtJudul().getText());
        model.setPengarang(view.getTxtPengarang().getText());
        model.setTahunTerbit(view.getTxtTahun().getText());
        dao.insert(model);
        JOptionPane.showMessageDialog(view, "Entri Data Ok");
    }
    
    public void viewData(){
        DefaultTableModel model = (DefaultTableModel)view.getTblBuku().getModel();
        model.setNumRows(0);
        List<Buku> list = dao.getAllBuku();
        for(Buku buku : list){
            Object[] data = {
                buku.getKodeBuku(),
                buku.getJudulBuku(),
                buku.getPengarang(),
                buku.getTahunTerbit()
                     
            };
            model.addRow(data);
        }
    }
    
    public  void actionClickTabel(){
        index = view.getTblBuku().getSelectedRow();
        model = dao.getBuku(index);
        view.getTxtKode().setText(model.getKodeBuku());
        view.getTxtJudul().setText(model.getJudulBuku());
        view.getTxtPengarang().setText(model.getPengarang());
        view.getTxtTahun().setText(model.getTahunTerbit());
        
    }
    
    public void update(){
        
        model.setKodeBuku(view.getTxtKode().getText());
        model.setJudulBuku(view.getTxtJudul().getText());
        model.setPengarang(view.getTxtPengarang().getText());
        model.setTahunTerbit(view.getTxtTahun().getText());
        dao.update(index, model);
        JOptionPane.showMessageDialog(view, "Update Data Ok");
    }
    
    public void delete(){
        dao.delete(index);
        JOptionPane.showMessageDialog(view, "Delete Data Ok");
    }
}
