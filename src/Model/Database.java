/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Emerson
 */
public class Database {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/telecon";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "* ERRO DE CONEXÃO COM O BANCO DE DADOS *\n\n"
                    + "  CERTIFIQUE-SE QUE A CONEXÃO FOI INICIADA \n\n"
                    + "    FECHE A APLICAÇÃO E INICIE NOVAMENTE!");
            throw new RuntimeException("Erro na Conexão", ex);

        }

    }

}
