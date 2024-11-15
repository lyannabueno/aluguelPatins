package controlador;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class JanelaRelatorioCaixa extends JDialog {
    private Map<String, Double> relatorio;
    private double totalMultas = 0.0;
    private double totalSemMultas = 0.0;

    public JanelaRelatorioCaixa(Map<String, Double> relatorioCaixa) {
        this.relatorio = relatorioCaixa;

        setTitle("Relatório de Caixa");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        double totalComMultas = 0.0;

        // Preenchendo o modelo da lista com os valores de pagamento
        for (Map.Entry<String, Double> entry : relatorio.entrySet()) {
            String formaPagamento = entry.getKey();
            Double valor = entry.getValue();

            if ("Multas".equals(formaPagamento)) {
                totalMultas += valor;
            } else {
                listModel.addElement(formaPagamento + ": R$ " + valor);
                totalSemMultas += valor;
            }
            totalComMultas += valor;
        }

        // Criando a lista de pagamentos e centralizando
        JList<String> list = new JList<>(listModel);
        list.setBorder(BorderFactory.createTitledBorder("Pagamentos Recebidos"));
        list.setCellRenderer(new CentralizedListCellRenderer());
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel panelTotais = new JPanel(new GridLayout(3, 1));

        JLabel lblTotalMultas = new JLabel("Total de Multas: R$ " + totalMultas);
        lblTotalMultas.setHorizontalAlignment(SwingConstants.CENTER);
        panelTotais.add(lblTotalMultas);

        JLabel lblTotalSemMultas = new JLabel("Total (Sem Multas): R$ " + totalSemMultas);
        lblTotalSemMultas.setHorizontalAlignment(SwingConstants.CENTER);
        panelTotais.add(lblTotalSemMultas);

        JLabel lblTotalComMultas = new JLabel("Total (Com Multas): R$ " + totalComMultas);
        lblTotalComMultas.setHorizontalAlignment(SwingConstants.CENTER);
        panelTotais.add(lblTotalComMultas);

        add(panelTotais, BorderLayout.SOUTH);
    }

    private static class CentralizedListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            return label;
        }
    }
}
