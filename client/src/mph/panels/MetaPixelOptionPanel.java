package mph.panels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import mph.entities.DuckImage;
import mph.entities.MetaPixel;
import mph.entities.MetaPixelData;
import mph.util.DefaultColor;

public class MetaPixelOptionPanel extends OptionPanel implements TableModelListener {
    private MainDrawPanel mainDrawPanel;
    private DuckImage workingDuckImage;
    private JTable metaPixelTable;
    private int rowCount = 0;
    HashMap<Integer, MetaPixel> metaPixelMap = new HashMap<Integer, MetaPixel>();

    public MetaPixelOptionPanel(MainDrawPanel mainDrawPanel, DuckImage workingDuckImage) {
        this.mainDrawPanel = mainDrawPanel;
        this.workingDuckImage = workingDuckImage;



        String[] columnNames = {"Description",
                "Parameter 1",
                "Parameter 2"};

        metaPixelTable = new JTable(new Object[DuckImage.HEIGHT][3], columnNames);
        metaPixelTable.setBackground(DefaultColor.DARK.getColor());
        metaPixelTable.setForeground(Color.GRAY);
        metaPixelTable.getModel().addTableModelListener(this);

        JScrollPane scrollPane = new JScrollPane(metaPixelTable);
        metaPixelTable.setFillsViewportHeight(true);

        add(scrollPane);

        MetaPixel mp = new MetaPixel(1);
        addRow(mp);
    }

    public void addRow(MetaPixel metaPixel) {
        metaPixelMap.put(rowCount, metaPixel);
        metaPixelTable.getModel().setValueAt(metaPixel.getMetaPixelData().getId(), rowCount, 0);
        metaPixelTable.getModel().setValueAt(metaPixel.getParam1(), rowCount, 1);
        metaPixelTable.getModel().setValueAt(metaPixel.getParam2(), rowCount, 2);
        rowCount++;
    }

    public void removeRow(int row) {
        metaPixelMap.remove(row);
        metaPixelTable.remove(row);
        rowCount--;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void tableChanged(TableModelEvent e) {
        for (int i = 0; i < rowCount; i++) {
            MetaPixel metaPixel = metaPixelMap.get(i);
            metaPixel.setParam1((String)metaPixelTable.getModel().getValueAt(i, 1));
            metaPixel.setParam2((String)metaPixelTable.getModel().getValueAt(i, 2));
            if (metaPixel.getParam1() != null && metaPixel.getParam2() != null) {
                Color metaPixelColor = metaPixel.toColor();
                workingDuckImage.setPixel(DuckImage.WIDTH - 1, i, metaPixelColor);
                System.out.println("x: " + (DuckImage.WIDTH - 1) + ", y:" + i + ", color:" + metaPixelColor);
            }
        }
    }
}
