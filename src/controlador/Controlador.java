package controlador;

import entidade.Patins;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controlador {
    private final List<Patins> patinsList;
    private final Map<String, Double> relatorioCaixa;
    private final double precoAluguel = 20.0;
    final double multaDanos = 50.0;

    public Controlador() {
        patinsList = new ArrayList<>();
        relatorioCaixa = new HashMap<>();
        patinsList.add(new Patins("1", "36"));
        patinsList.add(new Patins("2", "37"));
        patinsList.add(new Patins("3", "38"));
        patinsList.add(new Patins("4", "39"));
        patinsList.add(new Patins("5", "40"));
    }

    public boolean alugarPatins(String id) {
        for (Patins patins : patinsList) {
            if (patins.getId().equals(id) && patins.isDisponivel()) {
                patins.setDisponivel(false);
                registrarPagamento("Dinheiro", precoAluguel);
                return true;
            }
        }
        return false;
    }

    public void devolverPatins(String id, boolean houveDanos) {
        for (Patins patins : patinsList) {
            if (patins.getId().equals(id)) {
                patins.setDisponivel(true);
                if (houveDanos) {
                    registrarPagamento("Multa por Danos", multaDanos);
                }
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