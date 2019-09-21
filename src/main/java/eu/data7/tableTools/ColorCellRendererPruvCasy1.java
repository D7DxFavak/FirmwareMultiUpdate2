/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.data7.tableTools;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class ColorCellRendererPruvCasy1 extends JTextField implements TableCellRenderer {

    int[][] index;
    int[][] index2;
    int[][] index3;
    boolean index3Zadano = false;
    boolean kanban = false;
    Color backColor;
    Color frontColor;

    public ColorCellRendererPruvCasy1(Color backColor, Color frontColor) {
        this.backColor = backColor;
        this.frontColor = frontColor;       
    }
  

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
// component will actually be this.
       /* Component component = super.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, column);*/
        //System.out.println("TCR  " );

        // System.out.println("TCR index " + i);
        if (row == 0 && column == 1) {
            this.setBackground(new Color(188,247,188));
            this.setForeground(Color.BLACK);
            // new java.awt.Color(204,0,51));
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        if (isSelected == true) {
            this.setForeground(Color.WHITE);
            this.setFont(new java.awt.Font("Tahoma", 1, 11));
            this.setBackground(new java.awt.Color(102, 153, 255));
        } else {
            this.setFont(new java.awt.Font("Tahoma", 0, 11));
        }
        setBorder(new EmptyBorder(1, 2, 1, 2));
        setText((value == null) ? "" : value.toString());

        return this;
    }

    public boolean isEditable() {
        return false;
    }
}
