package aluguelpains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controlador {
    private final List<Patins> patinsList;
    private final Map<String, Double> relatorioCaixa;
    private final double precoAluguel = 20.0; // Preço fixo para o aluguel

    public Controlador() {
        patinsList = new ArrayList<>();
        relatorioCaixa = new HashMap<>();
        patinsList.add(new Patins("36"));
        patinsList.add(new Patins("37"));
        patinsList.add(new Patins("38"));
        patinsList.add(new Patins("39"));
        patinsList.add(new Patins("40"));
    }

    public boolean alugarPatins(String numero) {
        for (Patins patins : patinsList) {
            if (patins.getNumero().equals(numero) && patins.isDisponivel()) {
                patins.setDisponivel(false);
                // Aqui você pode registrar o pagamento
                registrarPagamento("Dinheiro", precoAluguel); // Exemplo de pagamento em dinheiro
                return true;
            }
        }
        return false;
    }

    public void devolverPatins(String numero) {
        for (Patins patins : patinsList) {
            if (patins.getNumero().equals(numero)) {
                patins.setDisponivel(true);
                break;
            }
        }
    }

    public void registrarPagamento(String formaPagamento, double valor) {
        relatorioCaixa.put(formaPagamento, relatorioCaixa.getOrDefault(formaPagamento, 0.0) + valor);
    }

    public Map<String, Double> getRelatorioCaixa() {
        return relatorioCaixa;
    }

    public List<Patins> getPatinsDisponiveis() {
        List<Patins> disponiveis = new ArrayList<>();
        for (Patins patins : patinsList) {
            if (patins.isDisponivel()) {
                disponiveis.add(patins);
            }
        }
        return disponiveis;
    }
}