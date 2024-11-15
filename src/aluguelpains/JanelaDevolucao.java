package aluguelpains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaDevolucao extends JFrame {
    private final Controlador controlador;
    private JTextField txtNumero;
    private JTextField txtDanos;
    private final JButton btnDevolver;

    public JanelaDevolucao(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Devolução de Patins");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        txtNumero = new JTextField(10);
        txtDanos = new JTextField(10);
        btnDevolver = new JButton("Devolver");

        btnDevolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numero = txtNumero.getText();
                String danos = txtDanos.getText();
                controlador.devolverPatins(numero);
                // Aqui você pode adicionar lógica para registrar danos, se necessário
                JOptionPane.showMessageDialog(null, "Patins devolvidos com sucesso! Danos: " + danos);
                dispose();
            }
        });

        add(new JLabel("Número do Patins:"));
        add(txtNumero);
        add(new JLabel("Danos (se houver):"));
        add(txtDanos);
        add(btnDevolver);
    }
}