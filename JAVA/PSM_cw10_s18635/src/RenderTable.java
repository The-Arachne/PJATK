import java.awt.Color;
import java.awt.Component;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class RenderTable implements TableCellRenderer {
	JLabel l;
	ModelTable mod;
	public RenderTable(ModelTable t) {
		l=new JLabel();
		mod=t;
		l.setOpaque(true);
	}
	Random rand = new Random();
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
	    boolean isSelected, boolean hasFocus, int row, int column) {
		if(hasFocus&&isSelected){
			mod.setVal(row, column, !(Boolean)value);
		}
	  if ((Boolean) value) {
	    l.setBackground(new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
	  } else {
	    l.setBackground(Color.white);
	  }
	  return l;
	}
}
class ModelTable extends AbstractTableModel {
	private boolean[][] tmpMatryca;

	  public ModelTable(boolean[][] newMatryca) {
	    this.tmpMatryca = newMatryca;
	  }

	  @Override
	  public String getColumnName(int column) {
	    return null;
	  }

	  @Override
	  public int getRowCount() {
	    return tmpMatryca.length;
	  }

	  @Override
	  public int getColumnCount() {
	    return tmpMatryca[0].length;
	  }

	  @Override
	  public Object getValueAt(int rowIndex, int columnIndex) {
	    return tmpMatryca[rowIndex][columnIndex];
	  }

	  @Override
	  public Class<?> getColumnClass(int columnIndex) {
	    return Boolean.class;
	  }

	  public void setNewMatryca(boolean[][] newMatryca) {
	    this.tmpMatryca = newMatryca;
	  }
	  public void setVal(int rowIndex, int columnIndex,boolean val){
		  tmpMatryca[rowIndex][columnIndex]=val;
	  }
	}