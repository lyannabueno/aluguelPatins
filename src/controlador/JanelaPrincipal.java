package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal extends JFrame {
    private Controlador controlador;

    public JanelaPrincipal(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Sistema de Aluguel de Patins");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Layout
        setLayout(new GridLayout(3, 1));

        JButton btnAlugar = new JButton("Alugar Patins");
        JButton btnDevolucao = new JButton("Devolver Patins");
        JButton btnRelatorio = new JButton("Relatório de Caixa");

        // Ação para o botão de aluguel
        btnAlugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAluguel janelaAluguel = new JanelaAluguel(controlador);
                janelaAluguel.setVisible(true);
            }
        });

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

        // Adicionando botões à janela
        add(btnAlugar);
        add(btnDevolucao);
        add(btnRelatorio);
    }
}