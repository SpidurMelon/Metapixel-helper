package mph.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import mph.util.DefaultColor;

public class ViewOptionPanel extends OptionPanel {
    private MainDrawPanel mainDrawPanel;
    public ViewOptionPanel(MainDrawPanel mainDrawPanel) {
        this.mainDrawPanel = mainDrawPanel;

        setPreferredSize(new Dimension(200, 800));
        setBackground(DefaultColor.DARKER.getColor());

        setLayout(new GridLayout(20, 1));

        JRadioButton all = new JRadioButton("Show All");
        all.setBackground(DefaultColor.DARKER.getColor());
        all.setForeground(Color.gray);
        all.setActionCommand("Show All");
        all.addActionListener(this);
        all.setSelected(true);

        JRadioButton hat = new JRadioButton("Show Hat");
        hat.setBackground(DefaultColor.DARKER.getColor());
        hat.setForeground(Color.gray);
        hat.setActionCommand("Show Hat");
        hat.addActionListener(this);
        hat.setSelected(true);

        JRadioButton quack = new JRadioButton("Show Quack");
        quack.setBackground(DefaultColor.DARKER.getColor());
        quack.setForeground(Color.gray);
        quack.setActionCommand("Show Quack");
        quack.addActionListener(this);
        quack.setSelected(true);

        JRadioButton cape = new JRadioButton("Show Cape");
        cape.setBackground(DefaultColor.DARKER.getColor());
        cape.setForeground(Color.gray);
        cape.setActionCommand("Show Cape");
        cape.addActionListener(this);
        cape.setSelected(true);

        JRadioButton rock = new JRadioButton("Show Rock");
        rock.setBackground(DefaultColor.DARKER.getColor());
        rock.setForeground(Color.gray);
        rock.setActionCommand("Show Rock");
        rock.addActionListener(this);
        rock.setSelected(true);

        JRadioButton particles = new JRadioButton("Show Particles");
        particles.setBackground(DefaultColor.DARKER.getColor());
        particles.setForeground(Color.gray);
        particles.setActionCommand("Show Particles");
        particles.addActionListener(this);
        particles.setSelected(true);

        ButtonGroup showGroup = new ButtonGroup();
        showGroup.add(all);
        showGroup.add(hat);
        showGroup.add(quack);
        showGroup.add(cape);
        showGroup.add(rock);
        showGroup.add(particles);

        add(all);
        add(hat);
        add(quack);
        add(cape);
        add(rock);
        add(particles);

        JCheckBox image = new JCheckBox("Toggle Image");
        image.setBackground(DefaultColor.DARKER.getColor());
        image.setForeground(Color.gray);
        image.setActionCommand("Image");
        image.addActionListener(this);
        image.setSelected(true);

        JCheckBox helper = new JCheckBox("Toggle Helper");
        helper.setBackground(DefaultColor.DARKER.getColor());
        helper.setForeground(Color.gray);
        helper.setActionCommand("Helper");
        helper.addActionListener(this);
        helper.setSelected(false);

        JCheckBox tongue = new JCheckBox("Toggle Tongue");
        tongue.setBackground(DefaultColor.DARKER.getColor());
        tongue.setForeground(Color.gray);
        tongue.setActionCommand("Tongue");
        tongue.addActionListener(this);
        tongue.setSelected(false);

        add(image);
        add(tongue);
        add(helper);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //RadioButtons
        String action = e.getActionCommand();
        if (action.startsWith("Show")) {
            if (action.endsWith("All")) {
                mainDrawPanel.setCurrentView(MainDrawPanel.View.ALL);
            } else if (action.endsWith("Hat")) {
                mainDrawPanel.setCurrentView(MainDrawPanel.View.HAT);
            } else if (action.endsWith("Quack")) {
                mainDrawPanel.setCurrentView(MainDrawPanel.View.QUACK);
            } else if (action.endsWith("Cape")) {
                mainDrawPanel.setCurrentView(MainDrawPanel.View.CAPE);
            } else if (action.endsWith("Rock")) {
                mainDrawPanel.setCurrentView(MainDrawPanel.View.ROCK);
            } else if (action.endsWith("Particles")) {
                mainDrawPanel.setCurrentView(MainDrawPanel.View.PARTICLES);
            }
        }
        if (action.startsWith("Helper")) {
            mainDrawPanel.toggleHelper();
        }
        if (action.startsWith("Image")) {
            mainDrawPanel.toggleImage();
        }
        if (action.startsWith("Tongue")) {
            mainDrawPanel.toggleTongue();
        }
    }
}
