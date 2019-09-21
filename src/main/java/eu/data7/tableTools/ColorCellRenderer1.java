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

public class ColorCellRenderer1 extends JTextField implements TableCellRenderer {

    int[][] index;
    int[][] index2;
    int[][] index3;
    boolean index3Zadano = false;
    boolean kanban = false;
    Color backColor;
    Color frontColor;

    public ColorCellRenderer1(int[][] index, int[][] index2, Color backColor, Color frontColor) {
        this.backColor = backColor;
        this.frontColor = frontColor;
        this.index = index;
        this.index2 = index2;
    }

    public ColorCellRenderer1(int[][] index, int[][] index2, int[][] index3, Color backColor, Color frontColor, boolean kanban) {
        this.backColor = backColor;
        this.frontColor = frontColor;
        this.index = index;
        this.index2 = index2;
        this.index3 = index3;
        this.kanban = true;
    }

    public ColorCellRenderer1(int[][] index, int[][] index2, int[][] index3, Color backColor, Color frontColor) {
        this.backColor = backColor;
        this.frontColor = frontColor;
        this.index = index;
        this.index2 = index2;
        this.index3 = index3;
        this.index3Zadano = true;
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
        if (this.index[row][column] == 1) {
            this.setBackground(backColor);
            this.setForeground(Color.BLACK);
            // new java.awt.Color(204,0,51));
        } else {
            if (index3Zadano == true) {
                if (this.index3[row][column] == 1) {
                    this.setBackground(new Color(237, 236, 236));
                    this.setForeground(Color.BLACK);
                    // new java.awt.Color(204,0,51));
                } else {
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.BLACK);
                }
            }
            else if(kanban == true ) {
                if (this.index3[row][column] == 1) {
                    this.setBackground(new Color(153,255,255));
                    this.setForeground(Color.BLACK);
                    // new java.awt.Color(204,0,51));
                } else {
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.BLACK);
                }

            } else {
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        }

        if (this.index2[row][column] == 1) {
            setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        } else if (this.index2[row][column] == 2) {
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        } else if (this.index2[row][column] == 3) {
            setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
