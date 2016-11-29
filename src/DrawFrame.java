import javax.swing.*;

class DrawFrame extends JFrame {
	DrawPanel panel = new DrawPanel(this);

	public DrawFrame() {

		add(panel);
		setTitle("MyUML");
		setBounds(100, 100, 800, 600);
		setVisible(true);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}