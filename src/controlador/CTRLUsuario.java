/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.DAOUsuario;
import modelo.DTOUsuario;
import vista.FrmUsuario;

public class CTRLUsuario implements ActionListener {

    private DTOUsuario mod;
    private DAOUsuario dao;
    private FrmUsuario frm;

    DefaultTableModel modeloTabla; // para asignar al JTable (tbUsuario)

    public CTRLUsuario(DTOUsuario obj, DAOUsuario dao, FrmUsuario vista) {
        this.mod = obj;
        this.dao = dao;
        this.frm = vista;

        //Eventos ActionListener
        this.frm.btnRegistrar.addActionListener(this);
    }

    public void inicio() throws SQLException {
        frm.setTitle("Mantenimiento Usuario"); // titulo al formulario
        frm.setLocationRelativeTo(null); // al centro
        cargaTabla(frm.tblUsuario, "");
       
    }

    public void cargaTabla(JTable tabla, String buscar) throws SQLException {
        // la estructura del JTable se la paso a variable modelo
        String[] titulo = {"ID", "Nombre", "Contraseña", "Estado"};
        modeloTabla = new DefaultTableModel(titulo, 0);

        ArrayList<DTOUsuario> lista = new ArrayList<>(); // variable que recibe datos del metodo selectPart...(DaoParticipante)

        try {
            lista = dao.ListarUsuarios(buscar);
            Object[] objeto = new Object[4]; // numero de columnas del jtable

            for (int i = 0; i < lista.size(); i++) { // lista.size devuelve el numero de filas
                objeto[0] = lista.get(i).getId();// id
                objeto[1] = lista.get(i).getNombre(); // nombre
                objeto[2] = lista.get(i).getContrasena(); // contraseña
                objeto[3] = lista.get(i).getEstado().equals("A") ? "ACTIVO" : "INACTIVO";  // estado

                modeloTabla.addRow(objeto);
            }
            frm.tblUsuario.setModel(modeloTabla);
        } catch (SQLException e) {
            Logger.getLogger(CTRLUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void limpiar() throws SQLException {
        frm.txtUsuario.setText("");
        frm.txtContrasena.setText("");
        frm.txtUsuario.requestFocus();
        modeloTabla.setRowCount(0); // establecer las filas en 0
        cargaTabla(frm.tblUsuario, "");
       
    }

    //Creamos el metodo registrar
    public void metodoRegistrar() {
        mod.setNombre(frm.txtUsuario.getText().toUpperCase());
        mod.setContrasena(frm.txtContrasena.getText());
        try {
            if (dao.registrar(mod)) { // se asume que es true, osea si se realizó el registro
                JOptionPane.showMessageDialog(null, "Registro Correcto", "Registrar", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se realizó el registro", "Registrar", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            Logger.getLogger(CTRLUsuario.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnRegistrar) {
            metodoRegistrar();
        }
    }

}
