/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabelas;

import Controller.CadastroDeClientes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Emerson
 */
public class TableModelClientesRelatorios extends AbstractTableModel{
    
    private List<CadastroDeClientes> dados = new ArrayList<>();                        // Arrai do dados da classe de Cadastro de Serviços que vão ser informados na tabela
    private String[] colunas = {"Código","Nome","Cpf","Cidade","Endereço","Bairro"};   // Criação das Colunas no Jtable

    @Override
    public String getColumnName(int culumn) {
        return colunas[culumn];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getCodigo();     // Insere os dados na Tabela 
            case 1:
                return dados.get(linha).getNome();       // Insere os dados na Tabela
            case 2:
                return dados.get(linha).getCpf();        // Insere os dados na Tabela
            case 3:
                return dados.get(linha).getCidade();     // Insere os dados na Tabela
            case 4:
                return dados.get(linha).getEndereco();   // Insere os dados na Tabela
            case 5:
                return dados.get(linha).getBairro();     // Insere os dados na Tabela      
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setCodigo(Integer.parseInt((String) valor));  // Insere os dados na Tabela
            case 1:
                dados.get(linha).setNome((String) valor);                      // Insere os dados na Tabela
            case 2:
                dados.get(linha).setCpf((String) valor);                       // Insere os dados na Tabela
            case 3:
                dados.get(linha).setCidade((String) valor);                    // Insere os dados na Tabela
            case 4:
                dados.get(linha).setEndereco((String) valor);                  // Insere os dados na Tabela
            case 5:
                dados.get(linha).setBairro((String) valor);                    // Insere os dados na Tabela   
        }
        this.fireTableRowsUpdated(linha, linha);
    }
    
    public void addRow(CadastroDeClientes c){
        this.dados.add(c);            // Insere os dados na Jtable
        this.fireTableDataChanged(); // Atualiza a Jtable
    }
    
    
    public void removeRow(int linha){
        this.dados.remove(linha);                 // Remove os dados na Jtable
        this.fireTableRowsDeleted(linha, linha);  // Atualiza a Jtable
    }
    
}