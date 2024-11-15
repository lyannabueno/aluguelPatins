package controlador;

import controlador.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaDevolucao extends JFrame {
    private final Controlador controlador;
    private JTextField txtNumero;
    private JTextField txtDanos;
    private final JButton btnDevolver;
    private JCheckBox chkDanos;

    public JanelaDevolucao(Controlador controlador, JanelaAluguel janelaAluguel) {
        this.controlador = controlador;
        setTitle("Devolução de Patins");
        setSize(300, 300); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("ID do Patins:"), gbc);

        txtNumero = new JTextField(10);
        gbc.gridx = 1;
        add(txtNumero, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Danos (se houver):"), gbc);

        txtDanos = new JTextField(10);
        txtDanos.setEnabled(false); // Desabilita o campo de danos, que só será habilitado caso a caixa de danos seja marcada
        gbc.gridx = 1;
        add(txtDanos, gbc);

        
        chkDanos = new JCheckBox("Houve danos?");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(chkDanos, gbc);

        chkDanos.addItemListener(e -> {
            if (chkDanos.isSelected()) {
                txtDanos.setEnabled(true);
            } else {
                txtDanos.setEnabled(false);
            }
        });

        btnDevolver = new JButton("Devolver");
        btnDevolver.addActionListener(e -> {
            String numero = txtNumero.getText();
            String danos = txtDanos.getText();

            if (numero.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, informe o número do patins.");
                return;
            }

            if (!controlador.isPatinsAlugado(numero)) {
                JOptionPane.showMessageDialog(null, "Este patins não foi alugado ou não existe.");
                return;
            }

            if (chkDanos.isSelected() && !danos.isEmpty()) {
                controlador.devolverPatins(numero, true);
                JOptionPane.showMessageDialog(null, "Patins devolvido com sucesso! Danos: " + danos + ". Multa aplicada.");
            } else if (chkDanos.isSelected()) {
                JOptionPane.showMessageDialog(null, "Por favor, descreva os danos.");
                return;
            } else {
                controlador.devolverPatins(numero, false);
                JOptionPane.showMessageDialog(null, "Patins devolvido com sucesso! Nenhum dano registrado.");
            }

            janelaAluguel.atualizarTabela();
            dispose();
        });


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnDevolver, gbc);
    }
}
