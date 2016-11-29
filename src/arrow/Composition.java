package arrow;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;

public class Composition extends ArrowWithDiamond{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		Stroke dash = new BasicStroke(2.5f);	///�߶����廭�����ͣ�ֻ�����˻��ʴ�ϸ������Ĭ��
		this.calAngleTwoPoint();				///������ͨ��ͷ����
		this.setLinePosition();					///�����߶��е�
		this.paintNormalArrow(g, dash);			///���Ƽ�ͷ
		this.calAngleFourPoint();				///������������
		Polygon po = this.creatPolygon();		///����
		g.drawPolygon(po);
	}

}
