package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Dependency extends NormalArrow{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		this.calAngleTwoPoint();				///�����ͷ����
		///���û���	����ֱ��
		Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,			
                BasicStroke.JOIN_ROUND, 3.5f, new float[] {15, 5},
                0f);
		this.paintNormalArrow(g, dash);
	}

}
