package aluguelpains;

public class Patins {
    private final String numero;
    private boolean disponivel;

    public Patins(String numero) {
        this.numero = numero;
        this.disponivel = true;
    }

    public String getNumero() {
        return numero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}