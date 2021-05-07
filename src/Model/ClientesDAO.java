/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.CadastroDeClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emerson
 */
public class ClientesDAO {
    
    // chama o metodo de conexão
    private Connection con;

    // cria a conexão
    private void Conexao() {
        this.con = Database.getConnection();
    }
    
    // Salva o cliente no Banco de Dados
    public void Salvar(CadastroDeClientes cli){
        
        Conexao();
        
        try {
            
            PreparedStatement salvar = con.prepareStatement("INSERT INTO clientes(CodCliente,Nome,Endereco,Cidade,Bairro,Cpf,LimiteCredito,ValorGastos,sexo) VALUES (?,?,?,?,?,?,?,?,?,?)");
            
            salvar.setString(1, ""+cli.getCodigo());
            salvar.setString(2, cli.getNome());
            salvar.setString(3, cli.getEndereco());
            salvar.setString(4, cli.getCidade());
            salvar.setString(5, cli.getBairro());
            salvar.setString(6, cli.getCpf());
            salvar.setString(7, ""+cli.getLimiteCredito());
            salvar.setString(8, ""+cli.getValorGasto());
            salvar.setString(9, ""+cli.getSexo());
            
            salvar.executeUpdate();
            
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
