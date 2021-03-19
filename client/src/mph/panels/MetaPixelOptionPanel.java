package mph.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class MetaPixelOptionPanel extends OptionPanel implements ListSelectionListener, KeyListener {
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
        metaPixelTable.addKeyListener(this);
        metaPixelTable.setFillsViewportHeight(true);
        metaPixelTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        metaPixelTable.getSelectionModel().setSelectionInterval(0, 0);
        metaPixelTable.getSelectionModel().addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(metaPixelTable);
        scrollPane.setBackground(DefaultColor.DARK.getColor());
        scrollPane.setForeground(DefaultColor.GREYPLE.getColor());
        scrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()));
        add(scrollPane);
        for (int i = 0; i < metaPixelTable.getRowCount(); i++) {
            setRow(i, MetaPixel.fromColor(workingDuckImage.getPixel(DuckImage.WIDTH-1, i)));
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
        updateMPM(row);
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

    public void write() {
        workingDuckImage.write();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void updateMPM(int row) {
        MetaPixel currentMP = parseRow(row);
        String currentDescription = (String)metaPixelTable.getModel().getValueAt(row,0);
        if (currentMP != null) {
            MetaPixelData currentMPData = currentMP.getMetaPixelData();
            //metaPixelTable.setValueAt(currentMPData.getDescription(), row, 0);
            mpm.setMetaPixel(row, currentMP);
        } else {
            if (currentDescription != null) {
                metaPixelTable.setValueAt(null, row, 0);
            }
        }
        mpm.apply(workingDuckImage);
        mainDrawPanel.repaint();
    }

    public void updateMPM() {
        for (int i = 0; i < metaPixelTable.getRowCount(); i++) {
            updateMPM(i);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        metaPixelPropertiesPanel.setSelectedMetaPixel(getCurrentlySelected());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            setRow(getCurrentlySelectedRow(), new MetaPixel(0));
            metaPixelPropertiesPanel.setSelectedMetaPixel(new MetaPixel(0));
            updateMPM(getCurrentlySelectedRow());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
