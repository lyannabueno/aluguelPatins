package entidade;

public class Patins {
    private final String numero;
    private final String id;
    private final String modelo; 
    private boolean disponivel;

    public Patins(String id, String numero, String modelo) {
        this.id = id;
        this.numero = numero;
        this.modelo = modelo;
        this.disponivel = true;
    }

    public String getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getModelo() {
        return modelo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}