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
        setSize(500, 300);
        setLocationRelativeTo(null);
        setModal(true);
        
        // Usando BorderLayout para o painel principal
        setLayout(new BorderLayout());

        // Painel para o título e o cabeçalho
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 2));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Espaçamento no painel

        headerPanel.add(new JLabel("Forma de Pagamento", SwingConstants.CENTER));
        headerPanel.add(new JLabel("Total Recebido", SwingConstants.CENTER));

        // Adicionando o painel de cabeçalho ao topo da janela
        add(headerPanel, BorderLayout.NORTH);

        // Painel para exibir o conteúdo do relatório
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(relatorio.size(), 2));  // Criando um grid para os dados

        if (relatorio.isEmpty()) {
            contentPanel.add(new JLabel("Nenhum pagamento registrado.", SwingConstants.CENTER));
            contentPanel.add(new JLabel("")); // Adiciona um espaço vazio para completar a linha
        } else {
            for (Map.Entry<String, Double> entry : relatorio.entrySet()) {
                contentPanel.add(new JLabel(entry.getKey(), SwingConstants.CENTER));  // Centraliza os textos
                contentPanel.add(new JLabel(String.format("R$ %.2f", entry.getValue()), SwingConstants.CENTER));

                // Adiciona o valor das multas se houver
                if (entry.getKey().contains("Multa")) {
                    totalMultas += entry.getValue();  // Soma as multas
                }
            }
        }

        // Adicionando o conteúdo no painel central
        JScrollPane scrollPane = new JScrollPane(contentPanel); // Adiciona rolagem caso haja muitos registros
        add(scrollPane, BorderLayout.CENTER);

        // Painel para o campo de multas
        JPanel multasPanel = new JPanel();
        multasPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel labelMultas = new JLabel("Total de Multas: ");
        JLabel valorMultas = new JLabel(String.format("R$ %.2f", totalMultas));  // Exibe o total de multas
        multasPanel.add(labelMultas);
        multasPanel.add(valorMultas);

        add(multasPanel, BorderLayout.SOUTH);  // Coloca o painel de multas no rodapé

        // Painel para o botão de fechamento
        JPanel buttonPanel = new JPanel();
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        buttonPanel.add(btnFechar);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
