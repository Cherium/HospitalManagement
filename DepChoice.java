import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JComboBox;
import java.awt.Color;

public class DepChoice{
      private final JPanel contentPanel = new JPanel();
      private static Database dbase;

      public static void main (String[]args){
            DepChoice dep = new DepChoice(dbase);
      }

      //This is all under WIP
      public DepChoice(Database dbase){
            contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            //contentPanel.setLocationRelativeTo(null);
            contentPanel.setSize(400, 230);
            contentPanel.setVisible(true);

            //setLocationRelativeTo(null);
            //getContentPane().setLayout(new BorderLayout());
            //contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            //getContentPane().add(contentPanel, BorderLayout.CENTER);
      }
}
