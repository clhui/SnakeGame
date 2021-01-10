package tanchishe.com.hui;

import javax.swing.JFrame;

public class App {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Ã∞≥‘…ﬂ");
		frame.setSize(865, 745);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new GamePancel());
		frame.setVisible(true);
		
	}

}
