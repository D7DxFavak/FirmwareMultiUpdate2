/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.data7.tableTools;

import java.awt.Component;
import java.sql.Statement;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;
//import mikronis.MikronISApp;
//import mikronis.zamestnanecInfo;

public class ButtonEditor extends JButton implements TableCellEditor {

    private Statement databaze;
    
    public ButtonEditor(String text, Statement databaze) {
        super(text);
        this.databaze = databaze;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        //buttonPressed(table, row, column);
        return this;
    }

    public void cancelCellEditing() {
        System.out.println("Cancel");
    }

    public boolean stopCellEditing() {
        return true;
    }

    public Object getCellEditorValue() {
        return null;
    }

    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    public void addCellEditorListener(CellEditorListener l) {
    }

    public void removeCellEditorListener(CellEditorListener l) {
    }

    protected void fireCellEditing(ChangeEvent e){

    }
  
}

