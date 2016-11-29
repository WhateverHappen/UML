package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public abstract class NormalArrow extends Arrow{			///������ͨ��ͷ��
	
	///��ͷ��������
	protected int x1ForArrow, y1ForArrow;
	protected int x2ForArrow, y2ForArrow;

	public abstract void draw(Graphics2D g);
	
	protected void calAngleTwoPoint() {					///������ͨ��ͷ��������
		
		if(direction == 1 && (endX >= startX)) {			///ˮƽ������ָ���Ҳ�
			x1ForArrow = endX - 12;
			y1ForArrow = endY - 7;
			x2ForArrow = endX - 12;
			y2ForArrow = endY + 7;
		} else if(direction == 1 && (endX < startX)) {		///ˮƽ������ָ�����
			x1ForArrow = endX + 12;
			y1ForArrow = endY - 7;
			x2ForArrow = endX + 12;
			y2ForArrow = endY + 7;
		} else if(direction == 2 && (endY >= startY)){		///��ֱ������ָ����
			x1ForArrow = endX + 7;
			y1ForArrow = endY - 12;
			x2ForArrow = endX - 7;
			y2ForArrow = endY - 12;	
		} else if(direction == 2 && (endY < startY)) {		///��ֱ������ָ����
			x1ForArrow = endX + 7;
			y1ForArrow = endY + 12;
			x2ForArrow = endX - 7;
			y2ForArrow = endY + 12;
		}
	}
	
	protected void paintNormalArrow(Graphics2D g, Stroke dash){			///������ͷ�����߶�
		///���û���
		Stroke dash_default = new BasicStroke();
		g.setStroke(dash_default);
		///���Ƽ�ͷ
		g.drawLine(x1ForArrow, y1ForArrow, endX, endY);			///���ͷ
		g.drawLine(x2ForArrow, y2ForArrow, endX, endY);			///�Ҽ�ͷ
		g.setStroke(dash);				///���ü�ͷ���廭������
		setLinePosition();				///�����߶��е�
		g.drawLine(startX, startY, midX1, midY1);
		g.drawLine(midX1, midY1, midX2, midY2);
		g.drawLine(midX2, midY2, endX, endY);
	}

}
