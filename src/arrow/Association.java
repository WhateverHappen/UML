package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Association extends NormalArrow{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		this.setLinePosition();								///�����߶��е�
		///�߶����廭�����ͣ�ֻ�����˻��ʴ�ϸ������Ĭ��
		Stroke dash_default = new BasicStroke(2.5f);
		g.setStroke(dash_default);
		///���Ƽ�ͷ���壬���ηֿ����߶�
		g.drawLine(startX, startY, midX1, midY1);
		g.drawLine(midX1, midY1, midX2, midY2);
		g.drawLine(midX2, midY2, endX, endY);
	}

}
