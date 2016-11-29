package drawGraphics;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Rectangle extends Draw_Graphics {		///�Ե�(positionX,positionY)Ϊ���Ͻǻ�ͼ��
	
	///�м��������
	protected int firstLineX1, firstLineY1;
	protected int firstLineX2, firstLineY2;
	protected int secondLineX1, secondLineY1;
	protected int secondLineX2, secondLineY2;
	///���γ���
	int width;
	int height;
	
	@Override
	public void draw(Graphics2D g) {					///��ͼ
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
	public void setState(int x, int y) {				///�ж��ƶ�������������״̬
		// TODO Auto-generated method stub
		if(startX+5 < x && x < endX-5 && startY+5 < y && y < endY-5){		///�м� 
			state = 1;
		}else if(startX-5 < x && x < startX+5 && startY-5 < y && y < startY+5){		///���Ͻ�
			state = 2;
		}else if(startX+5 < x && x < endX-5 && startY-5 < y && y < startY+5){		///���ϲ�
			state = 3;
		}else if(endX-5 < x && x < endX+5 && startY-5 < y && y < startY+5){			///���Ͻ�
			state = 4;
		}else if(startX-5 < x && x < startX+5 && startY+5 < y && y < endY-5){		///���в�
			state = 5;
		}else if(endX-5 < x && x < endX+5 && startY+5 < y && y < endY-5){			///���в�
			state = 6;
		}else if(startX -5 < x && x < startX+5 && endY-5 < y && y < endY+5){		///���½�
			state = 7;
		}else if(startX+5 < x && x < endX-5 && endY-5 < y && y < endY+5){			///���²�
			state = 8;
		}else if(endX-5 < x && x < endX+5 && endY-5 < y && y< endY+5){				///���½�
			state = 9;
		}
	}

	@Override
	public void offSet(int x, int y) {					///����
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

	private void setfirstLine(){				///���õ�һ��������
		this.firstLineX1 = startX;
		this.firstLineY1 = startY + height/5;		///���֮һ��
		this.firstLineX2 = endX;
		this.firstLineY2 = startY + height/5;
	}
	
	private void setSecondLine(){				///���õڶ���������
		this.secondLineX1 = startX;
		this.secondLineY1 = startY + 2*height/5;		///���֮����d
		this.secondLineX2 = endX;
		this.secondLineY2 = startY + 2*height/5;
	}

}
