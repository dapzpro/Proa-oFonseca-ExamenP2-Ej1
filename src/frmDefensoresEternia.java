import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class frmDefensoresEternia {
    private JPanel pGeneral;
    private JTextField IDtext;
    private JTextField nombretxt;
    private JComboBox<String> cbNivelPoder;
    private JComboBox<String> cbRegion;
    private JComboBox<String> cbHabilidades;
    private JTable tablaOriginal;
    private JButton ordenarButton;
    private JTable tablaOrdenada;
    private JComboBox<String> cbRegioAOrdenar;
    private JButton conteoPorHabilidadButton;
    private JTextArea txtAreaConteo;
    private JButton agregarButton;

    private ListaDefensores lista = new ListaDefensores();

    public frmDefensoresEternia() {
        agregarButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(IDtext.getText());
                String nombre = nombretxt.getText();
                String habilidad = (String) cbHabilidades.getSelectedItem();
                String region = (String) cbRegion.getSelectedItem();
                int nivel = Integer.parseInt((String) cbNivelPoder.getSelectedItem());

                DefensorEternia nuevo = new DefensorEternia(id, nivel, nombre, habilidad, region);
                lista.agregarDefensor(nuevo);
                lista.mostrar(tablaOriginal);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pGeneral, "Error al agregar defensor: " + ex.getMessage());
            }
        });

        ordenarButton.addActionListener(e -> {
            String regionFiltro = (String) cbRegioAOrdenar.getSelectedItem();
            if (regionFiltro != null) {
                lista.filtrarOrdenar(regionFiltro, tablaOrdenada);
            }
        });

        conteoPorHabilidadButton.addActionListener(e -> {
            lista.mostrarConteoHabilidades(txtAreaConteo);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Defensores de Eternia");
        frmDefensoresEternia form = new frmDefensoresEternia();
        frame.setContentPane(form.pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
