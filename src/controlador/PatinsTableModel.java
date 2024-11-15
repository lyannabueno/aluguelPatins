package controlador;

import entidade.Patins;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PatinsTableModel extends AbstractTableModel {
    private List<Patins> patinsList;
    private final String[] columnNames = {"ID", "Número", "Modelo"};

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
            case 0 -> patins.getId();
            case 1 -> patins.getNumero();
            case 2 -> patins.getModelo(); // Retornando o modelo do patins
            default -> null;
        };
    }

    public void setPatinsList(List<Patins> patinsList) {
        this.patinsList = patinsList;
        fireTableDataChanged();
    }
}