package mph.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import mph.entities.DuckImage;
import mph.entities.MetaPixel;
import mph.entities.MetaPixelData;
import mph.entities.MetaPixelManager;
import mph.util.DefaultColor;

public class MetaPixelOptionPanel extends OptionPanel implements TableModelListener, ListSelectionListener {
    private MetaPixelPropertiesPanel metaPixelPropertiesPanel;
    private MainDrawPanel mainDrawPanel;
    private DuckImage workingDuckImage;
    private JTable metaPixelTable;
    private MetaPixelManager mpm = new MetaPixelManager();


    public MetaPixelOptionPanel(MainDrawPanel mainDrawPanel, DuckImage workingDuckImage) {
        this.mainDrawPanel = mainDrawPanel;
        this.workingDuckImage = workingDuckImage;
        setPreferredSize(new Dimension(400, getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(DefaultColor.DARKER.getColor());

        String[] columnNames = {"Description",
                "ID",
                "Parameter 1",
                "Parameter 2"};

        metaPixelTable = new JTable();
        metaPixelTable.setModel(new DefaultTableModel(new String[DuckImage.HEIGHT][4], columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        metaPixelTable.setBackground(DefaultColor.DARK.getColor());
        metaPixelTable.setForeground(DefaultColor.GREYPLE.getColor());
        metaPixelTable.setGridColor(DefaultColor.BLACK.getColor());
        metaPixelTable.getModel().addTableModelListener(this);
        metaPixelTable.setFillsViewportHeight(true);
        metaPixelTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        metaPixelTable.getSelectionModel().addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(metaPixelTable);
        scrollPane.setBackground(DefaultColor.DARK.getColor());
        scrollPane.setForeground(DefaultColor.GREYPLE.getColor());
        scrollPane.setPreferredSize(new Dimension(getWidth(), 700));
        add(scrollPane);

        MetaPixel mp = new MetaPixel(0);
        for (int i = 0; i < metaPixelTable.getRowCount(); i++) {
            setRow(i, mp);
        }
    }

    public MetaPixel parseRow(int row) {
        String id = (String)metaPixelTable.getModel().getValueAt(row, 1);
        String param1 = (String)metaPixelTable.getModel().getValueAt(row, 2);
        String param2 = (String)metaPixelTable.getModel().getValueAt(row, 3);
        if (id != null) {
            return new MetaPixel(Integer.parseInt(id), param1, param2);
        } else {
            return null;
        }
    }

    public void setMetaPixelPropertiesPanel(MetaPixelPropertiesPanel metaPixelPropertiesPanel) {
        this.metaPixelPropertiesPanel = metaPixelPropertiesPanel;
        metaPixelPropertiesPanel.setSelectedMetaPixel(getCurrentlySelected());
    }

    public void setRow(int row, MetaPixel metaPixel) {
        metaPixelTable.getModel().setValueAt(metaPixel.getMetaPixelData().getDescription(), row, 0);
        metaPixelTable.getModel().setValueAt(String.valueOf(metaPixel.getMetaPixelData().getId()), row, 1);
        metaPixelTable.getModel().setValueAt(metaPixel.getParam1(), row, 2);
        metaPixelTable.getModel().setValueAt(metaPixel.getParam2(), row, 3);
        mpm.setMetaPixel(row, metaPixel);
    }


    public void removeRow(int row) {
        mpm.removeMetaPixel(row);
        metaPixelTable.remove(row);
    }

    public int getFirstEmptyRow() {
        for (int y = 0; y < metaPixelTable.getRowCount(); y++) {
            if (metaPixelTable.getModel().getValueAt(y, 0) == null) {
                return y;
            }
        }
        return -1;
    }

    public MetaPixel getCurrentlySelected() {
        return mpm.getMetaPixel(metaPixelTable.getSelectedRow());
    }

    public int getCurrentlySelectedRow() {
        return metaPixelTable.getSelectedRow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void tableChanged(TableModelEvent e) {
        for (int i = 0; i < metaPixelTable.getRowCount(); i++) {
            MetaPixel currentMP = parseRow(i);
            String currentDescription = (String)metaPixelTable.getModel().getValueAt(i,0);
            if (currentMP != null) {
                MetaPixelData currentMPData = currentMP.getMetaPixelData();
                if (currentDescription == null || !currentDescription.equals(currentMPData.getDescription())) {
                    metaPixelTable.setValueAt(currentMPData.getDescription(), i, 0);
                }
                if (currentMP.getParam1() != null && currentMP.getParam2() != null) {
                    mpm.setMetaPixel(i, currentMP);
                }
            } else {
                if (currentDescription != null) {
                    metaPixelTable.setValueAt(null, i, 0);
                }
            }
        }
        mpm.apply(workingDuckImage);
        mainDrawPanel.repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        metaPixelPropertiesPanel.setSelectedMetaPixel(getCurrentlySelected());
    }
}
