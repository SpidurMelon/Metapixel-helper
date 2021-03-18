package mph.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import mph.entities.DuckImage;
import mph.entities.MetaPixel;
import mph.entities.MetaPixelData;
import mph.entities.MetaPixelManager;
import mph.util.DefaultColor;

public class MetaPixelOptionPanel extends OptionPanel implements TableModelListener {
    private MainDrawPanel mainDrawPanel;
    private DuckImage workingDuckImage;
    private JTable metaPixelTable;
    private MetaPixelManager mpm = new MetaPixelManager();;


    public MetaPixelOptionPanel(MainDrawPanel mainDrawPanel, DuckImage workingDuckImage) {
        this.mainDrawPanel = mainDrawPanel;
        this.workingDuckImage = workingDuckImage;

        setLayout(new BorderLayout());

        String[] columnNames = {"Description",
                "ID",
                "Parameter 1",
                "Parameter 2"};

        metaPixelTable = new JTable();
        metaPixelTable.setModel(new DefaultTableModel(new String[DuckImage.HEIGHT][4], columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0;
            }
        });
        metaPixelTable.setBackground(DefaultColor.DARK.getColor());
        metaPixelTable.setForeground(Color.GRAY);
        metaPixelTable.getModel().addTableModelListener(this);
        metaPixelTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(metaPixelTable);

        add(scrollPane);

        MetaPixel mp = new MetaPixel(1);
        setRow(0, mp);
    }

    public void setRow(int row, MetaPixel metaPixel) {
        metaPixelTable.getModel().setValueAt(metaPixel.getMetaPixelData().getDescription(), row, 0);
        metaPixelTable.getModel().setValueAt(String.valueOf(metaPixel.getMetaPixelData().getId()), row, 1);
        metaPixelTable.getModel().setValueAt(metaPixel.getParam1(), row, 2);
        metaPixelTable.getModel().setValueAt(metaPixel.getParam2(), row, 3);
    }

    public void removeRow(int row) {
        mpm.removeMetaPixel(row);
        metaPixelTable.remove(row);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void tableChanged(TableModelEvent e) {
        for (int i = 0; i < metaPixelTable.getRowCount(); i++) {
            String description = (String)metaPixelTable.getModel().getValueAt(i, 0);
            String id = (String)metaPixelTable.getModel().getValueAt(i, 1);
            String param1 = (String)metaPixelTable.getModel().getValueAt(i, 2);
            String param2 = (String)metaPixelTable.getModel().getValueAt(i, 3);
            if (id != null) {
                if (description == null || !description.equals(MetaPixelData.findById(Integer.parseInt(id)).getDescription())) {
                    metaPixelTable.setValueAt(MetaPixelData.findById(Integer.parseInt(id)).getDescription(), i, 0);
                }
                if (param1 != null && param2 != null) {
                    mpm.setMetaPixel(i, Integer.parseInt(id), param1, param2);
                }
            } else {
                if (description != null) {
                    metaPixelTable.setValueAt(null, i, 0);
                }
            }
        }
        mpm.apply(workingDuckImage);
        mainDrawPanel.repaint();
    }
}
