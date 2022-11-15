package Vista;
import javax.swing.*;

@SuppressWarnings("serial")
public class PanelIbero extends JPanel {
	public PanelIbero() {
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon("/home/im20fyl/Pictures/Ibero.png"));
		this.add(img);
	}
}
