package arrow;

import java.awt.Graphics2D;

public abstract class Arrow {
	
	///��ʼ��ͼ����
	public int startX=0;
	public int startY=0;
	public int endX=0, endY=0;
	///��ͷ�м������,���ڽ��߶δ��м�ֿ�����������
	protected int midX1, midY1;
	protected int midX2, midY2;
	///��ͷ�������Ρ����α߳�
	protected static final int LENGTH = 14;
	///�жϼ�ͷָ��ֵΪ1Ϊˮƽ����ֵΪ2Ϊ��ֱ����
	protected int direction;
	
	///�жϼ�ͷ�����»��������һ���,����ֵΪ1������ֵΪ2
	protected int dir;
	
	public abstract void draw(Graphics2D g);					///��ͼ��
	
	public void setdir(int dir){
		this.dir = dir;
	}
	
	public void setStart(int x, int y){				///�趨��ʼλ��
		this.startX = x;
		this.startY = y;
	}
	
	public void setEnd(int x, int y){				///�趨����λ��
		this.endX = x;
		this.endY = y;
	}
		
	protected void setLinePosition(){						///���ü�ͷ�м�㣬���߶λ��ֿ�
		/*if(this.dir == 1){									///��ͷ������������y���нض�
			int midY = startY + (endY - startY) / 2;				
			midY1 = midY;
			midY2 = midY;
			midX1 = startX;
			midX2 = endX;
		}else if(dir == 2){									///��ͷ������������x���нض�
			int midX = startX + (endX - startX) / 2;
			midX1 = midX;
			midX2 = midX;
			midY1 = startY;
			midY2 = endY;
		}*/
		
		if(Math.abs(endX - startX) >= Math.abs(endY - startY)){			///ˮƽ�����ֵ������ֱ����ȡˮƽ�����е�
			int midX = startX + (endX - startX) / 2;
			midX1 = midX;
			midX2 = midX;
			midY1 = startY;
			midY2 = endY;
			direction = 1;			///��ͷָ��ˮƽ����
		} else {								///��ֱ�����ֵ����ˮƽ����ȡ��ֱ�����е�
			int midY = startY + (endY - startY) / 2;				
			midY1 = midY;
			midY2 = midY;
			midX1 = startX;
			midX2 = endX;
			direction = 2;			///��ͷָ����ֱ����
		}
			
	}
	
}
