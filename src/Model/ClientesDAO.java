/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.CadastroDeClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
        }

    }

    // Salva os Telefones na tabela de ClienteTelefones do BD
    public void SalvarTelefones(CadastroDeClientes cli) {

        Conexao();

        try {
            PreparedStatement salvar = con.prepareStatement("INSERT INTO clientetelefones(CodCliente,CodigoArea,Telefone,Observacao) VALUES(?,?,?,?)");

            for (int x = 0; x <= cli.getContador(); x++) {

                salvar.setString(1, "" + cli.getCodigo());
                salvar.setString(2, "" + cli.getCodigoArea());
                salvar.setString(3, cli.getTelefone());
                salvar.setString(4, cli.getObservacao());

                salvar.execute();

            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
        }

    }

    //BUSCA DO CLIENTE
    public boolean BuscarCliente(CadastroDeClientes cli) {

        Conexao();

        boolean validar = false;

        try {
            PreparedStatement buscar = con.prepareStatement("SELECT *, BINARY(Sexo) AS SexoBIT FROM clientes WHERE CodCliente = " + cli.getCodigo() + "");

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {

                cli.setNome(rs.getString("Nome"));
                cli.setEndereco(rs.getString("Endereco"));
                cli.setCidade(rs.getString("Cidade"));
                cli.setBairro(rs.getString("Bairro"));
                cli.setCpf(rs.getString("Cpf"));
                cli.setLimiteCredito(Double.parseDouble(rs.getString("LimiteCredito")));
                cli.setValorGasto(Double.parseDouble(rs.getString("ValorGastos")));
                cli.setSexo(rs.getString("Sexo"));

                return validar = true;

            }
            
            
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Dados do cliente, referente: " + ex);
        }
        
        return validar;

    }

    //BUSCA LISTA DE TELEFONES DO CLIENTE SELECIONADO
    public List<CadastroDeClientes> BuscarTelefoneDeCliente(CadastroDeClientes cli) {

        Conexao();

        List<CadastroDeClientes> lista = new ArrayList<>();
        
        try {
            PreparedStatement buscar = con.prepareStatement("SELECT * FROM clientetelefones WHERE CodCliente = " + cli.getCodigo() + "");
            
            ResultSet rs = buscar.executeQuery();
            
            while(rs.next()){
                CadastroDeClientes cli1 = new CadastroDeClientes();
                
                cli1.setCodigoArea(Integer.parseInt(rs.getString("CodigoArea")));
                cli1.setTelefone(rs.getString("Telefone"));
                cli1.setObservacao(rs.getString("Observacao"));
                
                lista.add(cli1);
            }
            
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Dados do Telefone, referente: " + ex);
        }

        return lista;
    }
    
    
    public void DeletarClientes(CadastroDeClientes cli){
        
        Conexao();
        
        try {
            PreparedStatement deletar = con.prepareStatement("DELETE FROM clientes WHERE CodCliente = ?");                    // Deleta os dados da tabela Clientes
            PreparedStatement deletarTelefones = con.prepareStatement("DELETE FROM clientetelefones WHERE CodCliente = ?");   // Deleta os dados da tabela ClienteTelefones
            
            deletar.setString(1, ""+cli.getCodigo());             // Recebe a informação do ? e preenche com os dados do código informado para a exclusão da tabela Clientes
            deletarTelefones.setString(1, ""+cli.getCodigo());    // Recebe a informação do ? e preenche com os dados do código informado para a exclusão da tabela ClienteTelefones
            
            deletar.execute();            // Executa a ação do SQL em Exclusão de dados no BD na tabela Clientes
            deletarTelefones.execute();   // Executa a ação do SQL em Exclusão de dados no BD na tabela ClienteTelefones
            
            JOptionPane.showMessageDialog(null, "CADASTRO DE CLIENTE EXCLUIDO COM SUCESSO!");
            
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Deletar Dados do cliente, referente: " + ex);
        }
        
    }
    
    

}
