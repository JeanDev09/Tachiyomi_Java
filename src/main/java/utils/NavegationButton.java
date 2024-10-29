package utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.geom.RoundRectangle2D;

public class NavegationButton extends JButton {

    private BufferedImage iconImage;

    public NavegationButton() {
        init();
    }

    public NavegationButton(String svgPath) {
        this();
        setButtonIcon(svgPath);
    }

    public NavegationButton(Color backgroundColor) {
        init();
    }

    private void init() {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    public void setButtonIcon(String svgPath) {
        BufferedImage svgImage = SVGUtils.renderSVGToImage(svgPath);
        if (svgImage != null) {
            BufferedImage roundedImage = createRoundedImage(svgImage);
            setIcon(new ImageIcon(roundedImage));
            setText("Biblioteca");
            setHorizontalTextPosition(SwingConstants.CENTER);
            setVerticalTextPosition(SwingConstants.BOTTOM);
        } else {
            setText("Error al cargar SVG");
        }
    }

    private BufferedImage createRoundedImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage roundedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, width, height); // Esto asegura que el fondo sea transparente
        g2d.setComposite(AlphaComposite.SrcOver);

        g2d.setClip(new RoundRectangle2D.Float(0, 0, width, height, 15, 15));
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return roundedImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (iconImage != null) {
            int iconX = (getWidth() - iconImage.getWidth()) / 2;
            int iconY = 0;
            g.drawImage(iconImage, iconX, iconY, this);
        }
    }

    public void setBackgroundColor(Color color) {
        setOpaque(false);
        repaint();
    }

}
