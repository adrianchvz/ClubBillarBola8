package capaInterfaz.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TableHeader extends JLabel {

    public TableHeader(String text) {
        super(text, SwingConstants.CENTER);
        setOpaque(true);
        setBackground(Color.WHITE);
        setFont(new Font("sansserif", 1, 12));
        setForeground(new Color(10, 10, 10));
        setBorder(new EmptyBorder(10, 5, 10, 5));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.setColor(new Color(230, 230, 230));
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

}
