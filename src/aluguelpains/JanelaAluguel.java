
package aluguelpains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class JanelaAluguel extends JFrame {
    private Controlador controlador;
    private PatinsTableModel tableModel;
    private JTable table;
    private JFormattedTextField txtCpf;
    private JTextField txtNumero;

    public JanelaAluguel(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Aluguel de Patins");
        setSize(400, 400); // Aumentando a altura da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criar a tabela de patins
        tableModel = new PatinsTableModel(controlador.getPatinsDisponiveis());
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Painel para os campos de entrada e botões
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Usando BoxLayout para empilhar componentes

        txtNumero = new JTextField(10);
        txtCpf = new JFormattedTextField(createFormatter("###.###.###-##"));
        txtCpf.setColumns(10);
        JButton btnAlugar = new JButton("Alugar");
        JButton btnDevolucao = new JButton("Devolução");
        JButton btnRelatorio = new JButton("Relatório de Caixa");

        // Ação para o botão de aluguel
        btnAlugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numero = txtNumero.getText();
                String cpf = txtCpf.getText().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
                if (controlador.alugarPatins(numero)) {
                    JOptionPane.showMessageDialog(null, "Patins alugados com sucesso! CPF: " + cpf);
                    tableModel.setPatinsList(controlador.getPatinsDisponiveis());
                } else {
                    JOptionPane.showMessageDialog(null, "Patins não disponíveis ou número inválido.");
                }
            }
        });

        // Ação para o botão de devolução
        btnDevolucao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaDevolucao janelaDevolucao = new JanelaDevolucao(controlador);
                janelaDevolucao.setVisible(true);
            }
        });

        // Ação para o botão de relatório
        btnRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaRelatorioCaixa janelaRelatorio = new JanelaRelatorioCaixa(controlador.getRelatorioCaixa());
                janelaRelatorio.setVisible(true);
            }
        });

        // Adicionando os componentes ao painel
        panel.add(new JLabel("Número do Patins:"));
        panel.add(txtNumero);
        panel.add(new JLabel("CPF:"));
        panel.add(txtCpf);
        panel.add(btnAlugar);
        panel.add(btnDevolucao);
        panel.add(btnRelatorio);

        // Adicionando o painel ao layout da janela
        add(panel, BorderLayout.SOUTH);
    }

    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
}