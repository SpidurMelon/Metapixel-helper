package mph.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import mph.util.DefaultColor;

public class ViewOptionPanel extends OptionPanel {
    private MainDrawPanel mainDrawPanel;
    public ViewOptionPanel(MainDrawPanel mainDrawPanel) {
        this.mainDrawPanel = mainDrawPanel;

        setPreferredSize(new Dimension(200, 560));
        setBackground(DefaultColor.DARKER.getColor());

        setLayout(new GridLayout(20, 1));

        JRadioButton all = new JRadioButton("Show All");
        all.setBackground(DefaultColor.DARK.getColor());
        all.setForeground(DefaultColor.GREYPLE.getColor());
        all.setActionCommand("Show All");
        all.addActionListener(this);
        all.setSelected(true);

        JRadioButton hat = new JRadioButton("Show Hat");
        hat.setBackground(DefaultColor.DARK.getColor());
        hat.setForeground(DefaultColor.GREYPLE.getColor());
        hat.setActionCommand("Show Hat");
        hat.addActionListener(this);
        hat.setSelected(true);

        JRadioButton quack = new JRadioButton("Show Quack");
        quack.setBackground(DefaultColor.DARK.getColor());
        quack.setForeground(DefaultColor.GREYPLE.getColor());
        quack.setActionCommand("Show Quack");
        quack.addActionListener(this);
        quack.setSelected(true);

        JRadioButton cape = new JRadioButton("Show Cape");
        cape.setBackground(DefaultColor.DARK.getColor());
        cape.setForeground(DefaultColor.GREYPLE.getColor());
        cape.setActionCommand("Show Cape");
        cape.addActionListener(this);
        cape.setSelected(true);

        JRadioButton rock = new JRadioButton("Show Rock");
        rock.setBackground(DefaultColor.DARK.getColor());
        rock.setForeground(DefaultColor.GREYPLE.getColor());
        rock.setActionCommand("Show Rock");
        rock.addActionListener(this);
        rock.setSelected(true);

        JRadioButton particles = new JRadioButton("Show Particles");
        particles.setBackground(DefaultColor.DARK.getColor());
        particles.setForeground(DefaultColor.GREYPLE.getColor());
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
        image.setBackground(DefaultColor.DARK.getColor());
        image.setForeground(DefaultColor.GREYPLE.getColor());
        image.setActionCommand("Image");
        image.addActionListener(this);
        image.setSelected(true);

        JCheckBox helper = new JCheckBox("Toggle Helper");
        helper.setBackground(DefaultColor.DARK.getColor());
        helper.setForeground(DefaultColor.GREYPLE.getColor());
        helper.setActionCommand("Helper");
        helper.addActionListener(this);
        helper.setSelected(false);

        JCheckBox tongue = new JCheckBox("Toggle Tongue");
        tongue.setBackground(DefaultColor.DARK.getColor());
        tongue.setForeground(DefaultColor.GREYPLE.getColor());
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
