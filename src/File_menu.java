
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class File_menu{

	JButton jbOk = new JButton("ȷ��");
	JButton jbCancel = new JButton("ȡ��");
	JFrame jf = new JFrame("��ʾ");
	
	File_menu(){
		jf.setLayout(new FlowLayout());
		jf.add(jbOk);
		jf.add(jbCancel);
		jf.setLocation(500, 300);
		jf.setResizable(false);
		jf.pack();
		jf.setVisible(true);
	}
	
	void fileExit(){
		
		jf.addWindowListener(new WindowAdapter(){				///���ڹر�
			public void windowClosing(WindowEvent e){
				jf.dispose();
			}
		});
		jbOk.addActionListener(new ButtonListener());
		jbCancel.addActionListener(new ButtonListener());
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){				///�����˳�
			if(e.getSource().equals(jbOk)){
				System.exit(0);
			}else if(e.getSource().equals(jbCancel)){			///�رյ����Ĵ���
				jf.dispose();
			}
		}
	}
}
