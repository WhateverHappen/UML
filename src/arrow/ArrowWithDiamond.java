package arrow;

import java.awt.Graphics2D;
import java.awt.Polygon;

public abstract class ArrowWithDiamond extends NormalArrow{
	
	///���ε��ĸ���
	protected int x1ForDiamond, y1ForDiamond;
	protected int x2ForDiamond, y2ForDiamond;
	protected int x3ForDiamond, y3ForDiamond;
	protected int x4ForDiamond, y4ForDiamond;

	public abstract void draw(Graphics2D g);
	
	protected void calAngleFourPoint(){			///���ô����μ�ͷ�������ε��ĸ���
		
		if(direction == 1 && (endX >= startX)) {			///ˮƽ������ָ���Ҳ�
			x1ForDiamond = startX;
			y1ForDiamond = startY;
			x2ForDiamond = startX - 12;
			y2ForDiamond = startY + 7;
			x3ForDiamond = startX - 24;
			y3ForDiamond = startY;
			x4ForDiamond = startX - 12;
			y4ForDiamond = startY - 7;
		} else if(direction == 1 && (endX < startX)) {		///ˮƽ������ָ�����
			x1ForDiamond = startX;
			y1ForDiamond = startY;
			x2ForDiamond = startX + 12;
			y2ForDiamond = startY + 7;
			x3ForDiamond = startX + 24;
			y3ForDiamond = startY;
			x4ForDiamond = startX + 12;
			y4ForDiamond = startY - 7;
		} else if(direction == 2 && (endY >= startY)){		///��ֱ������ָ����
			x1ForDiamond = startX;
			y1ForDiamond = startY;
			x2ForDiamond = startX + 7;
			y2ForDiamond = startY - 12;
			x3ForDiamond = startX;
			y3ForDiamond = startY - 24;
			x4ForDiamond = startX - 7;
			y4ForDiamond = startY - 12;	
		} else if(direction == 2 && (endY < startY)) {		///��ֱ������ָ����
			x1ForDiamond = startX;
			y1ForDiamond = startY;
			x2ForDiamond = startX + 7;
			y2ForDiamond = startY + 12;
			x3ForDiamond = startX;
			y3ForDiamond = startY + 24;
			x4ForDiamond = startX - 7;
			y4ForDiamond = startY + 12;	
		}
	}
	
	protected Polygon creatPolygon(){				///������ε��ĸ���
		Polygon po = new Polygon();
		int x[] = {x1ForDiamond, x2ForDiamond, x3ForDiamond, x4ForDiamond};
		int y[] = {y1ForDiamond, y2ForDiamond, y3ForDiamond, y4ForDiamond};
		for(int i=0; i<4; i++){
			po.addPoint(x[i], y[i]);
		} 
		return po;
	}
}
