package arrow;

import java.awt.*;

public class Realization extends ArrowWithTriangle{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		///���û���	����ֱ��
		Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,			
                BasicStroke.JOIN_ROUND, 3.5f, new float[] {15, 5},
                0f);
        g.setStroke(dash);
		this.setLinePosition();						///�����߶��е�
		///���Ƽ�ͷ���壬���ηֿ����߶�
		g.drawLine(startX, startY, midX1, midY1);
		g.drawLine(midX1, midY1, midX2, midY2);
		g.drawLine(midX2, midY2, endX, endY);
		this.calAngleThreePoint();					///�����ͷ����
		///�������λ������ͣ�ֻ�����˴�ϸ������Ĭ��
		Stroke dash_default = new BasicStroke(2.5f);
		g.setStroke(dash_default);
		Polygon po = creatPolygon();				///����
		g.drawPolygon(po);
	}

}
