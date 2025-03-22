import java.awt.Color;
import java.awt.Graphics;

public class Circulo extends Trazo {
    Punto centro;
    int radio;

    public Circulo(Color color, int grosor, Punto centro, int radio) {
        super(color, grosor);
        this.centro = centro;
        this.radio = radio;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.drawOval(centro.x - radio, centro.y - radio, 2 * radio, 2 * radio);
    }

    @Override
    public String toString() {

        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }
}