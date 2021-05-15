/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClientesDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Emerson
 */
public class GeraPdf {

    // Fontes disponíveis para o Relatório
    private static Font fonteCabecalho = new Font(Font.FontFamily.COURIER, 18,
            Font.BOLD);
    private static Font fontePadrao = new Font(Font.FontFamily.TIMES_ROMAN, 10);
    private static Font fontePadraoNegrito = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
    private static Font fonteVermelha = new Font(Font.FontFamily.TIMES_ROMAN,
            12, Font.NORMAL, BaseColor.RED);
    private static Font negritoPequena = new Font(Font.FontFamily.HELVETICA,
            10, Font.BOLD);
    private static Font negrito = new Font(Font.FontFamily.TIMES_ROMAN,
            12, Font.BOLD);

    public void RelatorioClientes(CadastroDeClientes cli) {

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        DecimalFormat df = new DecimalFormat("###,###,###,###,###.00");

        try {

            PdfWriter.getInstance(document, new FileOutputStream("c:/Relatorios/Clientes.pdf"));

            document.open();
            Paragraph p = new Paragraph("RELATÓRIO DE CLIENTES", fonteCabecalho);
            p.setAlignment(1);
            document.add(p);

            Paragraph linha = new Paragraph("_____________________________________");
            linha.setAlignment(1);
            document.add(linha);

            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "
                    + ""
                    + ""));
            document.add(new Paragraph("Os dados do relatório contém: Nome, CPF, Cidade, Endereço, Bairro, Limite de Crédito, Valor de Gastos, Sexo e Telefones."));
            document.add(new Paragraph("****************************************************************************************************************"));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));

            ClientesDAO cliDao = new ClientesDAO();
            List<CadastroDeClientes> lista = cliDao.PesquisaDeRelatorio(cli);
            List<CadastroDeClientes> lista2 = cliDao.PesquisaDeTelefonesParaGerarPdf(cli);

            for (int x = 0; x < lista.size(); x++) {

                //Seleciona qual sexo que foi salvo no cadastro de clientes
                String sexo = lista.get(x).getSexo();
                String validaSexo = "";
                if (sexo.trim().equals("1")) {
                    validaSexo = "Masculino";
                } else if (sexo.trim().equals("2")) {
                    validaSexo = "Feminino";
                }

                document.add(new Paragraph((x + 1) + " - " + lista.get(x).getNome() + " (Cod "+lista.get(x).getCodigo()+")   CPF - " + lista.get(x).getCpf() + "\nCidade: " + lista.get(x).getCidade()
                        + ",   Endereço: " + lista.get(x).getEndereco() + ",   Bairro: " + lista.get(x).getBairro() + "\nLimite de Crédito R$" + df.format(lista.get(x).getLimiteCredito())
                        + ",   Valor de Gastos R$" + df.format(lista.get(x).getValorGasto()) + "\nSexo: " + validaSexo));

                for (int y = 0; y < lista2.size(); y++) {
                    if (lista.get(x).getCodigo() == lista2.get(y).getCodigo()) {
                        document.add(new Paragraph("DDD (" + lista2.get(y).getCodigoArea() + ") " + lista2.get(y).getTelefone() + ", Observação: " + lista2.get(y).getObservacao()));
                    }
                }

                document.add(new Paragraph("  "));
            }

            document.add(new Paragraph("  "
                    + ""
                    + ""));

            Paragraph fim = new Paragraph("FIM DA LISTA", fonteVermelha);
            fim.setAlignment(1);
            document.add(fim);

        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("c:/Relatorios/Clientes.pdf"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

}
