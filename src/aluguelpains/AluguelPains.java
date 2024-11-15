package aluguelpains;

import controlador.Controlador;
import javax.swing.SwingUtilities;
import controlador.JanelaPrincipal;

public class AluguelPains {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controlador controlador = new Controlador();
            JanelaPrincipal janelaPrincipal = new JanelaPrincipal(controlador);
            janelaPrincipal.setVisible(true);
        });
    }
}

// https://github.com/lyannabueno/aluguelPatins.git
