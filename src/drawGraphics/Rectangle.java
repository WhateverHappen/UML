package drawGraphics;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Rectangle extends Draw_Graphics {		///以点(positionX,positionY)为左上角画图形
	
	///中间横线坐标
	protected int firstLineX1, firstLineY1;
	protected int firstLineX2, firstLineY2;
	protected int secondLineX1, secondLineY1;
	protected int secondLineX2, secondLineY2;
	///矩形长宽
	int width;
	int height;
	
	@Override
	public void draw(Graphics2D g) {					///画图
		// TODO Auto-generated method stub
		width = endX-startX;
		height = endY- startY;
		this.setfirstLine();
		this.setSecondLine();
		if(this.check == 1){
			Stroke dash = new BasicStroke(1f, BasicStroke.CAP_BUTT,			
	                BasicStroke.JOIN_ROUND, 3.5f, new float[] {15, 5},
	                0f);
			g.setStroke(dash);
		}else{
			Stroke dash = new BasicStroke();
			g.setStroke(dash);
		}
		g.drawRect(startX, startY, width, height);
		g.drawLine(firstLineX1, firstLineY1, firstLineX2, firstLineY2);
		g.drawLine(secondLineX1, secondLineY1, secondLineX2, secondLineY2);
	}

	@Override
	public void setState(int x, int y) {				///判断移动或是缩放设置状态
		// TODO Auto-generated method stub
		if(startX+5 < x && x < endX-5 && startY+5 < y && y < endY-5){		///中间 
			state = 1;
		}else if(startX-5 < x && x < startX+5 && startY-5 < y && y < startY+5){		///左上角
			state = 2;
		}else if(startX+5 < x && x < endX-5 && startY-5 < y && y < startY+5){		///中上部
			state = 3;
		}else if(endX-5 < x && x < endX+5 && startY-5 < y && y < startY+5){			///右上角
			state = 4;
		}else if(startX-5 < x && x < startX+5 && startY+5 < y && y < endY-5){		///左中部
			state = 5;
		}else if(endX-5 < x && x < endX+5 && startY+5 < y && y < endY-5){			///右中部
			state = 6;
		}else if(startX -5 < x && x < startX+5 && endY-5 < y && y < endY+5){		///左下角
			state = 7;
		}else if(startX+5 < x && x < endX-5 && endY-5 < y && y < endY+5){			///中下部
			state = 8;
		}else if(endX-5 < x && x < endX+5 && endY-5 < y && y< endY+5){				///右下角
			state = 9;
		}
	}

	@Override
	public void offSet(int x, int y) {					///缩放
		// TODO Auto-generated method stub
		switch(state){
		case 2:
			startX = x;
			startY = y;
			break;
		case 3:
			startY = y;
			break;
		case 4:
			startY = y;
			endX = x;
			break;
		case 5:
			startX = x;
			break;
		case 6:
			endX = x;
			break;
		case 7:
			startX = x;
			endY = y;
			break;
		case 8:
			endY = y;
			break;
		case 9:
			endX = x;
			endY = y;
			break;
		}
	}

	private void setfirstLine(){				///设置第一条线坐标
		this.firstLineX1 = startX;
		this.firstLineY1 = startY + height/5;		///五分之一处
		this.firstLineX2 = endX;
		this.firstLineY2 = startY + height/5;
	}
	
	private void setSecondLine(){				///设置第二条线坐标
		this.secondLineX1 = startX;
		this.secondLineY1 = startY + 2*height/5;		///五分之二处d
		this.secondLineX2 = endX;
		this.secondLineY2 = startY + 2*height/5;
	}

}
