package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;

public class Composition extends ArrowWithDiamond{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		Stroke dash = new BasicStroke(2.5f);	///线段主体画笔类型，只设置了画笔粗细，其余默认
		this.calAngleTwoPoint();				///计算普通箭头坐标
		this.setLinePosition();					///设置线段中点
		this.paintNormalArrow(g, dash);			///绘制箭头
		this.calAngleFourPoint();				///计算菱形坐标
		Polygon po = this.creatPolygon();		///菱形
		g.drawPolygon(po);
	}

}
