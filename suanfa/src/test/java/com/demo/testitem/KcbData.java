package com.demo.testitem;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/12/27 23:01
 */
public class KcbData implements TableModel {
    private String[]  title ={"周一","周二","周三","周四","周五","周六","周日"};
    private String[][] data = new String[8][7];

    public KcbData() {
        for (int i = 0; i < data.length; i++) {
            for (int i1 = 0; i1 < data[i].length; i1++) {
                data[i][i1] = "";
            }
        }
    }

    @Override
    public int getRowCount() {
        return 8;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return title[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = (String)aValue;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
