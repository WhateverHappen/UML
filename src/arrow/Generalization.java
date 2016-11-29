package arrow;

import java.awt.*;

public class Generalization extends ArrowWithTriangle{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		this.setLinePosition();							///设置线段中点
		///绘制箭头主体，三段分开的线段
		g.drawLine(startX, startY, midX1, midY1);
		g.drawLine(midX1, midY1, midX2, midY2);
		g.drawLine(midX2, midY2, endX, endY);
		this.calAngleThreePoint();						///计算箭头坐标
		Polygon po = creatPolygon();					///菱形
		g.drawPolygon(po);
	}

}
