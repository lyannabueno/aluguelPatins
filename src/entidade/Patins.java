package entidade;

public class Patins {
    private final String numero;
    private final String id;
    private boolean disponivel;

    public Patins(String id, String numero) {
        this.id = id;
        this.numero = numero;
        this.disponivel = true;
    }

    public String getId() {
        return id;
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