import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class EditorDibujosGUI extends JFrame {
    Dibujo dibujo = new Dibujo();
    JPanel panelDibujo = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            dibujo.mostrarTrazos(g);
        }
    };
    JComboBox<String> comboTrazo = new JComboBox<>(new String[] { "Linea", "Rectangulo", "Circulo" });
    JButton btnGuardar = new JButton("Guardar");
    JButton btnCargar = new JButton("Cargar");
    JButton btnBorrarUltimo = new JButton("Borrar Último");

    public EditorDibujosGUI() {
        setTitle("Editor de Dibujos Vectoriales");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelDibujo.setBackground(Color.WHITE);
        panelDibujo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                agregarTrazo(e.getPoint());
            }
        });
        panelDibujo.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                actualizarTrazo(e.getPoint());
            }
        });
        add(panelDibujo, BorderLayout.CENTER);

        JPanel panelControl = new JPanel();
        panelControl.add(comboTrazo);
        panelControl.add(btnGuardar);
        panelControl.add(btnCargar);
        panelControl.add(btnBorrarUltimo);
        add(panelControl, BorderLayout.NORTH);

        btnGuardar.addActionListener(e -> guardarDibujo());
        btnCargar.addActionListener(e -> cargarDibujo());
        btnBorrarUltimo.addActionListener(e -> borrarUltimoTrazo());
    }

    private void agregarTrazo(Point punto) {
        String trazoSeleccionado = (String) comboTrazo.getSelectedItem();
        Color color = Color.BLACK;
        int grosor = 2;

        switch (trazoSeleccionado) {
            case "Linea":
                dibujo.agregarTrazo(new Linea(color, grosor, new Punto(punto.x, punto.y), new Punto(punto.x, punto.y)));
                break;
            case "Rectangulo":
                dibujo.agregarTrazo(new Rectangulo(color, grosor, new Punto(punto.x, punto.y), 0, 0));
                break;
            case "Circulo":
                dibujo.agregarTrazo(new Circulo(color, grosor, new Punto(punto.x, punto.y), 0));
                break;
        }
        panelDibujo.repaint();
    }

    private void actualizarTrazo(Point punto) {
        Trazo ultimoTrazo = dibujo.trazos.getLast();
        if (ultimoTrazo instanceof Linea) {
            ((Linea) ultimoTrazo).puntoFinal = new Punto(punto.x, punto.y);
        } else if (ultimoTrazo instanceof Rectangulo) {
            Rectangulo rect = (Rectangulo) ultimoTrazo;
            rect.ancho = punto.x - rect.puntoInicial.x;
            rect.alto = punto.y - rect.puntoInicial.y;
        } else if (ultimoTrazo instanceof Circulo) {
            Circulo circulo = (Circulo) ultimoTrazo;
            int radio = (int) Math
                    .sqrt(Math.pow(punto.x - circulo.centro.x, 2) + Math.pow(punto.y - circulo.centro.y, 2));
            circulo.radio = radio;
        }
        panelDibujo.repaint();
    }

    private void guardarDibujo() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            dibujo.guardarDibujo(file.getAbsolutePath());
        }
    }

    private void cargarDibujo() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            dibujo.cargarDibujo(file.getAbsolutePath());
            panelDibujo.repaint();
        }
    }

    private void borrarUltimoTrazo() {
        dibujo.borrarUltimoTrazo();
        panelDibujo.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EditorDibujosGUI().setVisible(true);
        });
    }
}