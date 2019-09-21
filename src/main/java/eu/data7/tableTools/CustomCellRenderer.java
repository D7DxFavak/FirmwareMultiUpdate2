/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.data7.tableTools;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CustomCellRenderer extends JButton implements TableCellRenderer {

    String text;
    public CustomCellRenderer(String text) {
        this.text = text;
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(text);
        return this;
    }
}



