import java.awt.Color;
import java.awt.Graphics;

public class Linea extends Trazo {
    Punto puntoInicial;
    Punto puntoFinal;

    public Linea(Color color, int grosor, Punto puntoInicial, Punto puntoFinal) {
        super(color, grosor);
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.drawLine(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y);
    }

    @Override
    public String toString() {
        return "Linea," + color.getRGB() + "," + grosor + "," + puntoInicial.x + "," + puntoInicial.y + ","
                + puntoFinal.x + "," + puntoFinal.y;
    }
}
