package arrow;

import java.awt.Graphics2D;
import java.awt.Polygon;

public abstract class ArrowWithTriangle extends Arrow{

	protected int x1ForTriangle, y1ForTriangle;
	protected int x2ForTriangle, y2ForTriangle;
	protected int x3ForTriangle, y3ForTriangle;
	
	public abstract void draw(Graphics2D g);
	
	protected void calAngleThreePoint() {
	
		if(direction == 1 && (endX >= startX)) {			///水平方向且指向右侧
			x1ForTriangle = endX;
			y1ForTriangle = endY - 7;
			x2ForTriangle = endX;
			y2ForTriangle = endY + 7;
			x3ForTriangle = endX + 12;
			y3ForTriangle = endY;
		} else if(direction == 1 && (endX < startX)) {		///水平方向且指向左侧
			x1ForTriangle = endX;
			y1ForTriangle = endY - 7;
			x2ForTriangle = endX;
			y2ForTriangle = endY + 7;
			x3ForTriangle = endX - 12;
			y3ForTriangle = endY;
		} else if(direction == 2 && (endY >= startY)){		///竖直方向且指向下
			x1ForTriangle = endX + 7;
			y1ForTriangle = endY;
			x2ForTriangle = endX - 7;
			y2ForTriangle = endY;
			x3ForTriangle = endX;
			y3ForTriangle = endY + 12;
		} else if(direction == 2 && (endY < startY)) {		///竖直方向且指向上
			x1ForTriangle = endX + 7;
			y1ForTriangle = endY;
			x2ForTriangle = endX - 7;
			y2ForTriangle = endY;
			x3ForTriangle = endX;
			y3ForTriangle = endY - 12;
		}
	}
	
	protected Polygon creatPolygon(){
		Polygon po = new Polygon();					///平行四边形
		int x[] = {x1ForTriangle, x2ForTriangle, x3ForTriangle};
		int y[] = {y1ForTriangle, y2ForTriangle, y3ForTriangle};
		for(int i=0; i<3; i++){
			po.addPoint(x[i], y[i]);
		} 
		return po;
	}

}
