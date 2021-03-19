package mph.panels;

import mph.entities.MetaPixel;
import mph.entities.MetaPixelData;
import mph.util.DefaultColor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class MetaPixelPropertiesPanel extends OptionPanel {
    private JLabel param1Label, param2Label;
    private MetaPixelOptionPanel mpop;
    private JComboBox<MetaPixelData> mpdList;
    private JTextField param1Field, param2Field;
    private MetaPixel currentSelectedMP = new MetaPixel(0);

    public MetaPixelPropertiesPanel(MetaPixelOptionPanel metaPixelOptionPanel) {
        this.mpop = metaPixelOptionPanel;
        setPreferredSize(new Dimension(400, getHeight()));
        setBackground(DefaultColor.DARKER.getColor());
        setLayout(new GridLayout(5, 1));

        JPanel typePanel = new JPanel();
        typePanel.setBackground(DefaultColor.DARKER.getColor());
        typePanel.setLayout(new BorderLayout());
        JLabel typeLabel = new JLabel("Type: ");
        typeLabel.setBackground(DefaultColor.DARK.getColor());
        typeLabel.setForeground(DefaultColor.GREYPLE.getColor());
        typePanel.add(BorderLayout.WEST, typeLabel);
        mpdList = new JComboBox<MetaPixelData>();
        for (MetaPixelData mpd:MetaPixelData.getAll().values()) {
            mpdList.addItem(mpd);
        }
        mpdList.setBackground(DefaultColor.BLURPLE.getColor());
        mpdList.setForeground(DefaultColor.BLACK.getColor());
        mpdList.setActionCommand("Select");
        mpdList.addActionListener(this);
        typePanel.add(mpdList);

        add(typePanel);
        
        JPanel params = new JPanel();
        params.setPreferredSize(new Dimension(getWidth(), 30));
        params.setBackground(DefaultColor.DARKER.getColor());
        params.setLayout(new GridLayout(1, 4));
        param1Label = new JLabel(getParamString(1));
        param1Label.setBackground(DefaultColor.DARK.getColor());
        param1Label.setForeground(DefaultColor.GREYPLE.getColor());
        params.add(param1Label);
        param1Field = new JTextField();
        params.add(param1Field);
        param2Label = new JLabel(getParamString(2));
        param2Label.setBackground(DefaultColor.DARK.getColor());
        param2Label.setForeground(DefaultColor.GREYPLE.getColor());
        params.add(param2Label);
        param2Field = new JTextField();
        params.add(param2Field);

        add(params);

        JPanel options = new JPanel();
        options.setLayout(new GridLayout(1, 2));
        JButton addSelectedMetaPixel = new JButton("Update MetaPixel");
        addSelectedMetaPixel.setBackground(DefaultColor.DARK.getColor());
        addSelectedMetaPixel.setForeground(DefaultColor.GREYPLE.getColor());
        addSelectedMetaPixel.setActionCommand("Update");
        addSelectedMetaPixel.addActionListener(this);
        options.add(addSelectedMetaPixel);
        JButton removeSelectedMetaPixel = new JButton("Remove MetaPixel");
        removeSelectedMetaPixel.setBackground(DefaultColor.DARK.getColor());
        removeSelectedMetaPixel.setForeground(DefaultColor.GREYPLE.getColor());
        removeSelectedMetaPixel.setActionCommand("Remove");
        removeSelectedMetaPixel.addActionListener(this);
        options.add(removeSelectedMetaPixel);

        add(options);
    }

    public void setSelectedMetaPixel(MetaPixel metaPixel) {
        currentSelectedMP = metaPixel;
        mpdList.setSelectedItem(metaPixel.getMetaPixelData());
        param1Label.setText(getParamString(1));
        param1Field.setText(metaPixel.getParam1());
        param2Label.setText(getParamString(2));
        param2Field.setText(metaPixel.getParam2());
    }

    private String getParamString(int paramNumber) {
        MetaPixelData data = currentSelectedMP.getMetaPixelData();
        if (paramNumber == 1 && data.getParam1Description().startsWith("<html>")) {
            return data.getParam1Description();
        } else if (paramNumber == 2 && data.getParam2Description().startsWith("<html>")) {
            return data.getParam2Description();
        }
        if (data.getMetaPixelType() == MetaPixelData.MetaPixelType.BOOL) {
            return "Not required";
        } else if (data.getMetaPixelType() == MetaPixelData.MetaPixelType.SPECIAL) {
            if (paramNumber == 1) {
                return data.getParam1Description();
            } else {
                return data.getParam2Description();
            }
        } else if (paramNumber == 1) {
            return "<html> " + data.getParam1Description() + ":" +
                    "<br> max: " + data.getMax() + "<html>" +
                    "<br> min: " + (data.canGoNegative()?-data.getMax():0);
        } else if (paramNumber == 2 && data.hasMultipleParams()) {
            return "<html> " + data.getParam2Description() + ":" +
                    "<br> max: " + data.getMax() + "<html>" +
                    "<br> min: " + (data.canGoNegative()?-data.getMax():0)
                    ;
        } else {
            return "Not required";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Update")) {
            if(!param1Field.getText().isEmpty()) currentSelectedMP.setParam1(param1Field.getText());
            else currentSelectedMP.setParam1(String.valueOf(0));
            if(!param2Field.getText().isEmpty()) currentSelectedMP.setParam2(param2Field.getText());
            else currentSelectedMP.setParam2(String.valueOf(0));
            mpop.setRow(mpop.getCurrentlySelectedRow(), currentSelectedMP);
        }
        else if (e.getActionCommand().equals("Remove")) {
            mpop.setRow(mpop.getCurrentlySelectedRow(), new MetaPixel(0));
            setSelectedMetaPixel(new MetaPixel(0));
        }
        else if (e.getActionCommand().equals("Select")) {
            currentSelectedMP = new MetaPixel(((MetaPixelData)mpdList.getSelectedItem()).getId());
            param1Label.setText(getParamString(1));
            param2Label.setText(getParamString(2));
        }
    }
}
