package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;

public class Aggregation extends ArrowWithDiamond{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		///�߶����廭�����ͣ�ֻ�����˻��ʴ�ϸ������Ĭ��
		Stroke dash_default = new BasicStroke(2.5f);
		g.setStroke(dash_default);
		this.calAngleTwoPoint();						///������ͨ��ͷ����
		this.setLinePosition();							///�����߶��е�
		this.paintNormalArrow(g, dash_default);			///���Ƽ�ͷ
		this.calAngleFourPoint();						///������������
		Polygon po = this.creatPolygon();				///����
		g.drawPolygon(po);
	}

}
