package aluguelpains;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PatinsTableModel extends AbstractTableModel {
    private List<Patins> patinsList;
    private final String[] columnNames = {"Número", "Disponível"};

    public PatinsTableModel(List<Patins> patinsList) {
        this.patinsList = patinsList;
    }

    @Override
    public int getRowCount() {
        return patinsList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Patins patins = patinsList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> patins.getNumero();
            case 1 -> patins.isDisponivel() ? "Sim" : "Não";
            default -> null;
        };
    }

    public void setPatinsList(List<Patins> patinsList) {
        this.patinsList = patinsList;
        fireTableDataChanged();
    }
}