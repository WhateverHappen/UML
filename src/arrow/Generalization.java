package arrow;

import java.awt.*;

public class Generalization extends ArrowWithTriangle{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		this.setLinePosition();							///�����߶��е�
		///���Ƽ�ͷ���壬���ηֿ����߶�
		g.drawLine(startX, startY, midX1, midY1);
		g.drawLine(midX1, midY1, midX2, midY2);
		g.drawLine(midX2, midY2, endX, endY);
		this.calAngleThreePoint();						///�����ͷ����
		Polygon po = creatPolygon();					///����
		g.drawPolygon(po);
	}

}
