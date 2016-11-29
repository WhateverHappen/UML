package arrow;

import java.awt.*;

public class Realization extends ArrowWithTriangle{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		///设置画笔	绘制直线
		Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,			
                BasicStroke.JOIN_ROUND, 3.5f, new float[] {15, 5},
                0f);
        g.setStroke(dash);
		this.setLinePosition();						///设置线段中点
		///绘制箭头主体，三段分开的线段
		g.drawLine(startX, startY, midX1, midY1);
		g.drawLine(midX1, midY1, midX2, midY2);
		g.drawLine(midX2, midY2, endX, endY);
		this.calAngleThreePoint();					///计算箭头坐标
		///设置菱形画笔类型，只设置了粗细，其余默认
		Stroke dash_default = new BasicStroke(2.5f);
		g.setStroke(dash_default);
		Polygon po = creatPolygon();				///菱形
		g.drawPolygon(po);
	}

}
