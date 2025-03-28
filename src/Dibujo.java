import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Dibujo {
    LinkedList<Trazo> trazos = new LinkedList<>();

    public void agregarTrazo(Trazo trazo) {
        trazos.add(trazo);
    }

    public void seleccionarTrazo(int indice) {
        if (indice >= 0 && indice < trazos.size()) {
            trazos.get(indice).seleccionar();
        }
    }

    public void eliminarTrazo(int indice) {
        if (indice >= 0 && indice < trazos.size()) {
            trazos.remove(indice);
        }
    }

    public void borrarUltimoTrazo() {
        if (!trazos.isEmpty()) {
            trazos.removeLast();
        }
    }

    public void mostrarTrazos(Graphics g) {
        for (Trazo trazo : trazos) {
            trazo.dibujar(g);
        }
    }

    public void guardarDibujo(String nombreArchivo) {
        try (PrintWriter out = new PrintWriter(nombreArchivo)) {
            for (Trazo trazo : trazos) {
                out.println(trazo.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cargarDibujo(String nombreArchivo) {
        try (BufferedReader in = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            trazos.clear();
            while ((linea = in.readLine()) != null) {
                Trazo trazo = Trazo.fromString(linea);
                trazos.add(trazo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}