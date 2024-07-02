package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DAOUsuario {
     Conexion oC;
     Connection con;
     
     PreparedStatement pst;

    public DAOUsuario() {
        oC = new Conexion();
        con = oC.getConexion();
    }
    
    //Listar registros
    public ArrayList<DTOUsuario> ListarUsuarios(String buscar) throws SQLException {
        ArrayList<DTOUsuario> lista = new ArrayList<>();
        DTOUsuario usuario;
        try {
            String sql = "SELECT * FROM usuario";
            pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            // Pasamos los datos del resulset a usuario (DTOUsuario)
            while (rs.next()) {
                usuario = new DTOUsuario(); // instanciamos la clase DTOUsuario (objeto usuario)
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setEstado(rs.getString("estado"));
                
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    } 
    
    //Registrar
    public boolean registrar(DTOUsuario oU) {
        boolean estado = false;
        try {
            //Verificar:
            //st = con.createStatement();
            //Paso 2
            String sqlV = "SELECT COUNT(*) FROM usuario WHERE nombre = ? ;";
            //paso 3
            pst = con.prepareStatement(sqlV);
            //paso 4
            pst.setString(1, oU.getNombre());
            //ResultSet rs = st.executeQuery(sqlV);
            //PASO 5
            ResultSet rs = pst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "El registro " + oU.getNombre() + "ya existe.");
            } else {
                //Paso 2
                String sql = "INSERT INTO usuario(nombre, contrasena) VALUES(?, ?)";
                //Paso 3
                pst = con.prepareStatement(sql);
                //Paso 4
                pst.setString(1, oU.getNombre());
                pst.setString(2, oU.getContrasena());
                
                int resp = pst.executeUpdate();
                estado = resp > 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
}
