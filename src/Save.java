import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import arrow.*;
import drawGraphics.*;

public class Save extends Frame{
	
	FileOutputStream ft = null;
	FileInputStream in = null;
	ObjectOutputStream ot = null;
	ObjectInputStream oi = null;
	FileDialog fd,op;
	
	public Save(ArrayList<Draw_Graphics> lg,ArrayList<Arrow> la){
		//±£´æ
		fd = new FileDialog(this, "±£´æÍ¼»­", FileDialog.SAVE);
		fd.setVisible(true);
		try {
			File fileout = new File(fd.getDirectory(),fd.getFile());
			ft = new FileOutputStream(fileout);
			ot = new ObjectOutputStream(ft);
			ot.writeObject(lg);
			ot.writeObject(la);
			ot.close();
		} catch (IOException IOe) {
			System.out.println("can not write object");
		}
	}
}
