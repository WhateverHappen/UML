package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public abstract class NormalArrow extends Arrow{			///带有普通箭头的
	
	///箭头两边坐标
	protected int x1ForArrow, y1ForArrow;
	protected int x2ForArrow, y2ForArrow;

	public abstract void draw(Graphics2D g);
	
	protected void calAngleTwoPoint() {					///设置普通箭头的两个点
		
		if(direction == 1 && (endX >= startX)) {			///水平方向且指向右侧
			x1ForArrow = endX - 12;
			y1ForArrow = endY - 7;
			x2ForArrow = endX - 12;
			y2ForArrow = endY + 7;
		} else if(direction == 1 && (endX < startX)) {		///水平方向且指向左侧
			x1ForArrow = endX + 12;
			y1ForArrow = endY - 7;
			x2ForArrow = endX + 12;
			y2ForArrow = endY + 7;
		} else if(direction == 2 && (endY >= startY)){		///竖直方向且指向下
			x1ForArrow = endX + 7;
			y1ForArrow = endY - 12;
			x2ForArrow = endX - 7;
			y2ForArrow = endY - 12;	
		} else if(direction == 2 && (endY < startY)) {		///竖直方向且指向上
			x1ForArrow = endX + 7;
			y1ForArrow = endY + 12;
			x2ForArrow = endX - 7;
			y2ForArrow = endY + 12;
		}
	}
	
	protected void paintNormalArrow(Graphics2D g, Stroke dash){			///画出箭头主体线段
		///设置画笔
		Stroke dash_default = new BasicStroke();
		g.setStroke(dash_default);
		///绘制箭头
		g.drawLine(x1ForArrow, y1ForArrow, endX, endY);			///左箭头
		g.drawLine(x2ForArrow, y2ForArrow, endX, endY);			///右箭头
		g.setStroke(dash);				///设置箭头主体画笔类型
		setLinePosition();				///设置线段中点
		g.drawLine(startX, startY, midX1, midY1);
		g.drawLine(midX1, midY1, midX2, midY2);
		g.drawLine(midX2, midY2, endX, endY);
	}

}
