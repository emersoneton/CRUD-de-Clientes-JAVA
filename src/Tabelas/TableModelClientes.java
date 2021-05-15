/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabelas;

import Controller.CadastroDeClientes;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emerson
 */
public class TableModelClientes extends AbstractTableModel {

    private List<CadastroDeClientes> dados = new ArrayList<>();    // Arrai do dados da classe de Cadastro de Serviços que vão ser informados na tabela
    private String[] colunas = {"Código de Área", "Telefone", "Observação"};  // Criação das Colunas no Jtable

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
                return dados.get(linha).getCodigoArea();   // Insere os dados na Tabela
            case 1:
                return dados.get(linha).getTelefone();     // Insere os dados na Tabela
            case 2:
                return dados.get(linha).getObservacao();   // Insere os dados na Tabela
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setCodigoArea(Integer.parseInt((String) valor));    // Insere os dados na Tabela
            case 1:
                dados.get(linha).setTelefone((String) valor);   // Insere os dados na Tabela
            case 2:
                dados.get(linha).setObservacao((String) valor); // Insere os dados na Tabela
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
