package eu.kanade.tachiyomi;

/**
 *
 * @author jeandev
 */

import eu.kanade.tachiyomi.ui.Tachiyomi;
import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        // Configurar el look and feel de la UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Iniciar la ventana en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Tachiyomi");
                Tachiyomi tachiyomi = new Tachiyomi();
                frame.setContentPane(tachiyomi.getPanelPrincipal());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }
}
