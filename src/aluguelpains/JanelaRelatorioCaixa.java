package aluguelpains;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class JanelaRelatorioCaixa extends JFrame {
    private final Map<String, Double> relatorio;

    public JanelaRelatorioCaixa(Map<String, Double> relatorio) {
        this.relatorio = relatorio;
        setTitle("Relatório de Caixa");
        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(relatorio.size() + 1, 2));

        add(new JLabel("Forma de Pagamento"));
        add(new JLabel("Total Recebido"));

        if (relatorio.isEmpty()) {
            add(new JLabel("Nenhum pagamento registrado."));
            add(new JLabel(""));
        } else {
            for (Map.Entry<String, Double> entry : relatorio.entrySet()) {
                add(new JLabel(entry.getKey()));
                add(new JLabel(String.format("%.2f", entry.getValue())));
            }
        }
    }
}