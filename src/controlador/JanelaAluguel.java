package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

public class JanelaAluguel extends JFrame {
    private final Controlador controlador;
    private PatinsTableModel tableModel;
    private final JTable table;
    private JFormattedTextField txtCpf;
    private JTextField txtId;
    private JFormattedTextField txtTelefone;

    @SuppressWarnings("empty-statement")
    public JanelaAluguel(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Aluguel de Patins");
        setSize(350, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new PatinsTableModel(controlador.getPatinsDisponiveis());
        table = new JTable(tableModel);
        
        CentralizedTableCellRenderer centralizedRenderer = new CentralizedTableCellRenderer();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centralizedRenderer);
        }
        
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("ID do Patins:"), gbc);

        txtId = new JTextField(10);
        gbc.gridx = 1;
        panel.add(txtId, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("CPF:"), gbc);

        txtCpf = new JFormattedTextField(createFormatter("###.###.###-##"));
        txtCpf.setColumns(10);
        gbc.gridx = 1;
        panel.add(txtCpf, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Telefone:"), gbc);

        txtTelefone = new JFormattedTextField(createFormatter("(##) ####-####"));
        txtTelefone.setColumns(10);
        gbc.gridx = 1;
        panel.add(txtTelefone, gbc);


        String[] formasPagamento = {"Pix", "Boleto", "Dinheiro", "Cartão de Débito", "Cartão de Crédito"};
        JComboBox<String> comboPagamento = new JComboBox<>(formasPagamento);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Forma de Pagamento:"), gbc);

        gbc.gridx = 1;
        panel.add(comboPagamento, gbc);
        
        // Adicionando um espaço em branco entre os campos e o botão
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        
        JButton btnAlugar = new JButton("Alugar");

        btnAlugar.addActionListener((ActionEvent e) -> {
            String id = txtId.getText().trim();
            String cpf = txtCpf.getText().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
            String telefone = txtTelefone.getText().replaceAll("[^0-9]", "");
            String formaPagamento = (String) comboPagamento.getSelectedItem();

            if (id.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || formaPagamento == null) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos antes de alugar.", "Campos Obrigatórios", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            if (controlador.alugarPatins(id, formaPagamento)) {
                JOptionPane.showMessageDialog(null, "Patins alugado com sucesso!");

                txtId.setText("");
                txtCpf.setText("");
                txtTelefone.setText("");
                comboPagamento.setSelectedIndex(0);

                tableModel.setPatinsList(controlador.getPatinsDisponiveis());
            } else {
                JOptionPane.showMessageDialog(null, "Patins não disponível ou número inválido.", "Erro", JOptionPane.ERROR_MESSAGE);

                // Limpar os campos após erro
                txtId.setText("");
                txtCpf.setText("");
                txtTelefone.setText("");
            }
        });



        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.addActionListener(e -> {
            JanelaDevolucao janelaDevolucao = new JanelaDevolucao(controlador, this);
            janelaDevolucao.setVisible(true);
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
        panel.add(btnDevolver, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
        panel.add(btnAlugar, gbc);

        add(panel, BorderLayout.SOUTH);
    }

    public void atualizarTabela() {
        tableModel.setPatinsList(controlador.getPatinsDisponiveis());
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
    
    public class CentralizedTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
            return cell;
        }
    }
}
