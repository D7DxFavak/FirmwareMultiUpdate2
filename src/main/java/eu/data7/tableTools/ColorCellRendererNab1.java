/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.data7.tableTools;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class ColorCellRendererNab1 extends JTextField implements TableCellRenderer {

    int[][] index;
    int[][] index2;
    int[][] index3;
    int[][] index4;
    int[][] index5;
    Color backColor;
    Color frontColor;

    public ColorCellRendererNab1(int[][] index, int[][] index2, int[][] index3, int[][] index4, int[][] index5, Color backColor, Color frontColor) {
        this.index = index;
        this.index2 = index2;
        this.index3 = index3;
        this.index4 = index4;
        this.index5 = index5;
        this.backColor = backColor;
        this.frontColor = frontColor;

    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
// component will actually be this.
        /*Component component = super.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, column);*/
        //System.out.println("TCR  " );

        // System.out.println("TCR index " + i);
        if (this.index[row][column] == 1) {
            this.setBackground(new java.awt.Color(254,219,150));
            this.setForeground(Color.BLACK);

        } else {
            if (this.index2[row][column] == 1) {
                this.setBackground(this.backColor);
                this.setForeground(Color.BLACK);

            } else {
                if (this.index3[row][column] == 1) {
                    this.setBackground(new java.awt.Color(255, 255, 204));
                    this.setForeground(Color.BLACK);

                } else {
                    if (this.index5[row][column] == 1) {
                        this.setBackground(new java.awt.Color(188, 247, 188));
                        this.setForeground(Color.BLACK);

                    } else {
                        this.setBackground(Color.WHITE);
                        this.setForeground(Color.BLACK);
                    }
                }
            }
        }


        if (isSelected == true) {
            this.setForeground(Color.WHITE);
            this.setFont(new java.awt.Font("Tahoma", 1, 11));
            this.setBackground(new java.awt.Color(102, 153, 255));
        } else {
            this.setForeground(Color.BLACK);
            this.setFont(new java.awt.Font("Tahoma", 0, 11));
        }

        if (this.index4[row][column] == 1) {
            setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        } else if (this.index4[row][column] == 2) {
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        } else if (this.index4[row][column] == 3) {
            setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        }
        // if(column == 5 ) {
        // this.setHorizontalTextPosition(SwingConstants.CENTER);
        // }
        setText((value == null) ? "" : value.toString());
        setBorder(new EmptyBorder(1, 2, 1, 2));
        return this;
    }

    public boolean isEditable() {
        return false;
    }
}
