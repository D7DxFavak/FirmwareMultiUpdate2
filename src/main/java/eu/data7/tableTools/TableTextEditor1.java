package eu.data7.tableTools;

//editor pro textove hodnoty v tabulkach
import java.awt.Component;
import java.util.EventObject;
import java.util.Enumeration;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TableTextEditor1 extends JTextField implements TableCellEditor
{

//  protected DvojiceRetezCislo dv;
  protected EventListenerList listenerList;
  protected ChangeEvent changeEvent;
  protected CellEditorListener ceListener;
  protected Object[] listeners;
  private int id;
  
  public TableTextEditor1()
  {
    super();
    changeEvent = new ChangeEvent(this);
    listenerList = new EventListenerList();

    this.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
      fireEditingStopped();
      }
    });
  }//konec konstruktoru1

  //omezene textove pole na pocet znaku
  public TableTextEditor1(int pocet) {
    super();
    this.setDocument(new DocumentLimited1(pocet));
    changeEvent = new ChangeEvent(this);
    listenerList = new EventListenerList();

    this.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
      fireEditingStopped();
      }
    });
  }//konec konstruktoru2

  
  public void addCellEditorListener(CellEditorListener listener) {
    listenerList.add(CellEditorListener.class, listener);
  } 

  public void removeCellEditorListener(CellEditorListener listener) {
    listenerList.remove(CellEditorListener.class, listener);
  } 

  protected void fireEditingStopped() {
 //   CellEditorListener listener;
    listeners = listenerList.getListenerList();
    for (int i = 0; i < listeners.length; i++) {
      if (listeners[i] == CellEditorListener.class)
      {
        ceListener = (CellEditorListener)listeners[i + 1];
        ceListener.editingStopped(changeEvent);
      }
    }
  }

  protected void fireEditingCanceled() {
 //   CellEditorListener listener;
    listeners = listenerList.getListenerList();
    for (int i = 0; i < listeners.length; i++) {
      if (listeners[i] == CellEditorListener.class) {
        ceListener = (CellEditorListener)listeners[i + 1];
        ceListener.editingCanceled(changeEvent);
      }
    }
  }//konec fireEditingCanceled

  public void cancelCellEditing() {
    fireEditingCanceled();
  }

  public boolean stopCellEditing() {
    fireEditingStopped();
    return true;
  }

  public boolean isCellEditable(EventObject event) {
    return true;
  } 

  public boolean shouldSelectCell(EventObject event) {
    return true;
  } 

  public Object getCellEditorValue() {
    return this.getText();
  }

  public Component getTableCellEditorComponent(JTable table, 
          Object value, boolean isSelected, int row, int column) {
    this.setText((String)value);
    return this;
  } //konec getTableCellEditorComponent

    
}//konec tridy TableTextEditor1
