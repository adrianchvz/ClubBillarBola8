package capaInterfaz.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        setBorder(null);

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                if (i1 == 4) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jTable, Object o, boolean selected, boolean bln1, int i, int i1) {
                Component com = super.getTableCellRendererComponent(jTable, o, selected, bln1, i, i1);
                com.setBackground(selected ? new Color(95, 144, 178) : Color.WHITE);
                com.setForeground(selected ? Color.BLACK : new Color(10, 10, 10));
                com.setFont(com.getFont().deriveFont(Font.BOLD));
                setHorizontalAlignment(JLabel.CENTER);
                // Establecer el borde de la celda como nulo (null) para eliminar los bordes blancos al seleccionar
                setBorder(selected ? null : BorderFactory.createEmptyBorder(1, 1, 1, 1));
                return com;
            }
        });
        // Desactivar el diseño de las celdas para que el borde de selección sea más uniforme
        setIntercellSpacing(new Dimension(0, 0));
    }

}
