package controlador;

import entidade.Patins;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Controlador {
    private final List<Patins> patinsList;
    private final Map<String, Double> relatorioCaixa;
    private final double precoAluguel = 20.0;
    final double multaDanos = 50.0;

    public Controlador() {
        patinsList = new ArrayList<>();
        relatorioCaixa = new HashMap<>();
        
        patinsList.add(new Patins("1", "36", "Modelo A"));
        patinsList.add(new Patins("2", "37", "Modelo B"));
        patinsList.add(new Patins("3", "38", "Modelo C"));
        patinsList.add(new Patins("4", "39", "Modelo D"));
        patinsList.add(new Patins("5", "40", "Modelo E"));
    }

    public boolean alugarPatins(String id, String formaPagamento) {
        for (Patins patins : patinsList) {
            if (patins.getId().equals(id) && patins.isDisponivel()) {
                patins.setDisponivel(false);
                registrarPagamento(formaPagamento, precoAluguel);
                return true;
            }
        }
        return false;
    }

    public void devolverPatins(String id, boolean houveDanos) {
        for (Patins patins : patinsList) {
            if (patins.getId().equals(id)) {
                if (houveDanos) {
                    registrarMulta(multaDanos);
                    JOptionPane.showMessageDialog(null, "Patins devolvido com danos. Multa aplicada de R$ " + multaDanos + ". O patins não estará disponível para aluguel.");
                } else {
                    patins.setDisponivel(true);
                    JOptionPane.showMessageDialog(null, "Patins devolvido com sucesso e disponível para aluguel.");
                }
                break;
            }
        }
    }
    
    public void registrarMulta(double valorMulta) {
        relatorioCaixa.put("Multas", relatorioCaixa.getOrDefault("Multas", 0.0) + valorMulta);
    }

    public void registrarPagamento(String formaPagamento, double valor) {
        relatorioCaixa.put(formaPagamento, relatorioCaixa.getOrDefault(formaPagamento, 0.0) + valor);
    }
    
    public void registrarMulta(String formaPagamento, double valorMulta) {
        registrarPagamento(formaPagamento, valorMulta);
        relatorioCaixa.put("Multas", relatorioCaixa.getOrDefault("Multas", 0.0) + valorMulta);
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

    public boolean isPatinsAlugado(String id) {
        for (Patins patins : patinsList) {
            if (patins.getId().equals(id) && !patins.isDisponivel()) {
                return true;
            }
        }
        return false;
    }
}