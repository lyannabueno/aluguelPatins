package controlador;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class JanelaRelatorioCaixa extends JDialog {
    private Map<String, Double> relatorio;
    private double totalMultas = 0.0;

    public JanelaRelatorioCaixa(Map<String, Double> relatorio) {
        this.relatorio = relatorio;
        setTitle("Relatório de Caixa");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setModal(true);
        
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 2));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Espaçamento no painel

        headerPanel.add(new JLabel("Forma de Pagamento", SwingConstants.CENTER));
        headerPanel.add(new JLabel("Total Recebido", SwingConstants.CENTER));

        add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(relatorio.size(), 2)); 

        if (relatorio.isEmpty()) {
            contentPanel.add(new JLabel("Nenhum pagamento registrado.", SwingConstants.CENTER));
            contentPanel.add(new JLabel(""));
        } else {
            for (Map.Entry<String, Double> entry : relatorio.entrySet()) {
                contentPanel.add(new JLabel(entry.getKey(), SwingConstants.CENTER));  
                contentPanel.add(new JLabel(String.format("R$ %.2f", entry.getValue()), SwingConstants.CENTER));

                if (entry.getKey().contains("Multa")) {
                    totalMultas += entry.getValue();
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel); 
        add(scrollPane, BorderLayout.CENTER);

        JPanel multasPanel = new JPanel();
        multasPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel labelMultas = new JLabel("Total de Multas: ");
        JLabel valorMultas = new JLabel(String.format("R$ %.2f", totalMultas));
        multasPanel.add(labelMultas);
        multasPanel.add(valorMultas);

        add(multasPanel, BorderLayout.SOUTH);  

        JPanel buttonPanel = new JPanel();
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        buttonPanel.add(btnFechar);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
