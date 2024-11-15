package aluguelpains;

public class AluguelPains {

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        JanelaAluguel janela = new JanelaAluguel(controlador);
        janela.setVisible(true);
    }
    
}
