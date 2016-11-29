
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class File_menu{

	JButton jbOk = new JButton("确定");
	JButton jbCancel = new JButton("取消");
	JFrame jf = new JFrame("提示");
	
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
		
		jf.addWindowListener(new WindowAdapter(){				///窗口关闭
			public void windowClosing(WindowEvent e){
				jf.dispose();
			}
		});
		jbOk.addActionListener(new ButtonListener());
		jbCancel.addActionListener(new ButtonListener());
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){				///整体退出
			if(e.getSource().equals(jbOk)){
				System.exit(0);
			}else if(e.getSource().equals(jbCancel)){			///关闭弹出的窗口
				jf.dispose();
			}
		}
	}
}
