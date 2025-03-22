import java.awt.Color;
import java.awt.Graphics;

public class Rectangulo extends Trazo {
    Punto puntoInicial;
    int ancho, alto;

    public Rectangulo(Color color, int grosor, Punto puntoInicial, int ancho, int alto) {
        super(color, grosor);
        this.puntoInicial = puntoInicial;
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.drawRect(puntoInicial.x, puntoInicial.y, ancho, alto);
        if (estaSeleccionado()) {
            g.setColor(Color.RED);
            g.drawRect(puntoInicial.x - 3, puntoInicial.y - 3, 6, 6);
            g.drawRect(puntoInicial.x + ancho - 3, puntoInicial.y - 3, 6, 6);
            g.drawRect(puntoInicial.x - 3, puntoInicial.y + alto - 3, 6, 6);
            g.drawRect(puntoInicial.x + ancho - 3, puntoInicial.y + alto - 3, 6, 6);
        }
    }

    @Override
    public String toString() {
        return "Rectangulo," + color.getRGB() + "," + grosor + "," + puntoInicial.x + "," + puntoInicial.y + "," + ancho
                + "," + alto;
    }
}