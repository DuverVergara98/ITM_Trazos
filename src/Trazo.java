import java.awt.Color;
import java.awt.Graphics;

public abstract class Trazo {
    Color color;
    int grosor;
    boolean seleccionado;

    public Trazo(Color color, int grosor) {
        this.color = color;
        this.grosor = grosor;
        this.seleccionado = false;
    }

    public abstract void dibujar(Graphics g);

    public void seleccionar() {
        this.seleccionado = true;
    }

    public void deseleccionar() {
        this.seleccionado = false;
    }

    public boolean estaSeleccionado() {
        return seleccionado;
    }

    public static Trazo fromString(String str) {
        String[] parts = str.split(",");
        String tipo = parts[0];
        Color color = new Color(Integer.parseInt(parts[1]));
        int grosor = Integer.parseInt(parts[2]);

        switch (tipo) {
            case "Linea":
                Punto inicio = new Punto(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                Punto fin = new Punto(Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                return new Linea(color, grosor, inicio, fin);
            case "Rectangulo":
                Punto esquina = new Punto(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                int ancho = Integer.parseInt(parts[5]);
                int alto = Integer.parseInt(parts[6]);
                return new Rectangulo(color, grosor, esquina, ancho, alto);
            case "Circulo":
                Punto centro = new Punto(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                int radio = Integer.parseInt(parts[5]);
                return new Circulo(color, grosor, centro, radio);
            default:
                throw new IllegalArgumentException("Tipo de trazo desconocido: " + tipo);
        }
    }

    @Override
    public abstract String toString();
}