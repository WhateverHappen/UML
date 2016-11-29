package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Association extends NormalArrow{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		this.setLinePosition();								///设置线段中点
		///线段主体画笔类型，只设置了画笔粗细，其余默认
		Stroke dash_default = new BasicStroke(2.5f);
		g.setStroke(dash_default);
		///绘制箭头主体，三段分开的线段
		g.drawLine(startX, startY, midX1, midY1);
		g.drawLine(midX1, midY1, midX2, midY2);
		g.drawLine(midX2, midY2, endX, endY);
	}

}
