package view;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionPanel extends JPanel {
    public JButton cmdEdit;
    public JButton cmdDelete;

    public ActionPanel() {
        super(new FlowLayout(FlowLayout.CENTER, 5, 0));
        cmdEdit = new JButton("edit");
        cmdDelete = new JButton("del");
        
        add(cmdEdit);
        add(cmdDelete);
    }
}