package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Dependency extends NormalArrow{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		this.calAngleTwoPoint();				///计算箭头坐标
		///设置画笔	绘制直线
		Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,			
                BasicStroke.JOIN_ROUND, 3.5f, new float[] {15, 5},
                0f);
		this.paintNormalArrow(g, dash);
	}

}
