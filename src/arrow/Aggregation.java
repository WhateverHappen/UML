package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;

public class Aggregation extends ArrowWithDiamond{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		///线段主体画笔类型，只设置了画笔粗细，其余默认
		Stroke dash_default = new BasicStroke(2.5f);
		g.setStroke(dash_default);
		this.calAngleTwoPoint();						///设置普通箭头坐标
		this.setLinePosition();							///设置线段中点
		this.paintNormalArrow(g, dash_default);			///绘制箭头
		this.calAngleFourPoint();						///计算菱形坐标
		Polygon po = this.creatPolygon();				///菱形
		g.drawPolygon(po);
	}

}
