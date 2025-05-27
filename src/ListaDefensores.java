import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListaDefensores {
    private Nodo inicio;

    public Nodo buscarID(int id) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.defensor.id == id) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }



    public void agregarDefensor(DefensorEternia nuevoDefensor) {
        if (buscarID(nuevoDefensor.id) != null) {
            JOptionPane.showMessageDialog(null, "Ya existe un defensor con ese ID\nTrate de cambiarlo");
            return;
        }

        Nodo nuevoDefensorNodo = new Nodo(nuevoDefensor);
        if (inicio == null) {
            inicio = nuevoDefensorNodo;
        } else {
            Nodo actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoDefensorNodo;
        }
    }




    public void bubbleNivel() {
        if (inicio == null || inicio.siguiente == null) {
          return;
        }
        boolean ordenado;
        do {
            ordenado = false;
            Nodo actual = inicio;
            while (actual.siguiente != null) {
                if (actual.defensor.nivelPoder > actual.siguiente.defensor.nivelPoder) {
                    DefensorEternia temporal = actual.defensor;
                    actual.defensor = actual.siguiente.defensor;
                    actual.siguiente.defensor = temporal;
                    ordenado = true;
                }
                actual = actual.siguiente;
            }
        } while (ordenado);
    }

    public void filtrarOrdenar(String region, JTable tablaOrdenada) {
        ListaDefensores listaFiltrada = new ListaDefensores();
        Nodo actual = inicio;
        while (actual != null) {
            if (!actual.defensor.region.equals(region)) {
                listaFiltrada.agregarDefensor(actual.defensor);
            }
            actual = actual.siguiente;
        }
        listaFiltrada.bubbleNivel();
        listaFiltrada.mostrar(tablaOrdenada);
    }

    public void mostrarConteoHabilidades(JTextArea areaTexto) {
        areaTexto.setText("Conteo por Habilidad Especial:\n\n" + contarRecursivo(inicio, inicio));
    }

    private String contarRecursivo(Nodo actual, Nodo comparador) {
        if (actual == null) return "";
        Nodo temp = comparador;
        while (temp != actual) {
            if (temp.defensor.Habilidad.equals(actual.defensor.Habilidad)) {
                return contarRecursivo(actual.siguiente, comparador);
            }
            temp = temp.siguiente;
        }
        int cantidad = contarOcurrencias(actual.defensor.Habilidad, comparador);
        return actual.defensor.Habilidad + ": " + cantidad + "\n" + contarRecursivo(actual.siguiente, comparador);
    }

    private int contarOcurrencias(String habilidad, Nodo nodo) {
        if (nodo == null) return 0;
        return (nodo.defensor.Habilidad.equals(habilidad) ? 1 : 0) + contarOcurrencias(habilidad, nodo.siguiente);
    }

    public void mostrar(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Habilidad", "Region", "Nivel"}, 0);
        Nodo actual = inicio;
        while (actual != null) {
            DefensorEternia defensorActual = actual.defensor;
            modelo.addRow(new Object[]{defensorActual.id, defensorActual.nombre, defensorActual.Habilidad, defensorActual.region, defensorActual.nivelPoder});
            actual = actual.siguiente;
        }
        tabla.setModel(modelo);
    }
}
