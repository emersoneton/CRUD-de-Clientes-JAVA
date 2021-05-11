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
import javax.swing.JOptionPane;

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
    public void Salvar(CadastroDeClientes cli) {

        // Chama a Clase de Conexão com Banco de Dados 
        Conexao();

        try {

            String sexo = "b'" + cli.getSexo() + "'";

            // Prepara os dados para serem enviado ao Banco De dados
            PreparedStatement salvar = con.prepareStatement("INSERT INTO clientes(CodCliente,Nome,Endereco,Cidade,Bairro,Cpf,LimiteCredito,ValorGastos,Sexo) "
                    + "VALUES (?,?,?,?,?,?,?,?," + sexo + ")");

            // insere todos os valores conforme a sequuência de valores no VALUES do Insert
            salvar.setString(1, "" + cli.getCodigo());
            salvar.setString(2, cli.getNome());
            salvar.setString(3, cli.getEndereco());
            salvar.setString(4, cli.getCidade());
            salvar.setString(5, cli.getBairro());
            salvar.setString(6, cli.getCpf());
            salvar.setString(7, "" + cli.getLimiteCredito());
            salvar.setString(8, "" + cli.getValorGasto());

            //Insere os dados no BD
            salvar.execute();

            JOptionPane.showMessageDialog(null, "CADASTRO DE CLIENTE SALVO COM SUCESSO!");
            
            //Fecha a conexão com o BD
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar"+ex);
        }

    }

    public void SalvarTelefones(CadastroDeClientes cli) {

        Conexao();

        try {
            PreparedStatement salvar = con.prepareStatement("INSERT INTO clientetelefones(CodCliente,CodigoArea,Telefone,Observacao) VALUES(?,?,?,?)");

            for (int x = 0; x <= cli.getContador(); x++) {
                
                salvar.setString(1, ""+cli.getCodigo());
                salvar.setString(2, ""+cli.getCodigoArea());
                salvar.setString(3, cli.getTelefone());
                salvar.setString(4, cli.getObservacao());
                
                salvar.execute();
                
            }

            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar"+ex);
        }

    }

}
