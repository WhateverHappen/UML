package drawGraphics;

import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

public abstract class Draw_Graphics implements Serializable{
	
	///�趨����ƶ�ǰ��λ�ã����������ק����
	protected int moveStartX, moveStartY;
	protected int moveEndX, moveEndY;	
	protected int state = 0;		///�����ж��������ο�Ĺ�ϵ
	///�������Ͻ����½���������
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	public int check=0;				///�ӣ��ж��Ƿ�ctrlѡ��
	public abstract void setState(int x, int y);				///�ж��������ο��λ�ù�ϵ
	public abstract void draw(Graphics2D g);					///��ͼ��
	public abstract void offSet(int x, int y);								///λ��
	
	public int getState(){							///�õ�״̬
		return this.state;
	}
	
	public void setMoveStart(int x, int y){			///ȡ������ƶ���ʼλ��
		this.moveStartX = x;
		this.moveStartY = y;
	}
	
	public void setMoveEnd(int x, int y){			///ȡ������ƶ���ֹλ��
		this.moveEndX = x;
		this.moveEndY = y;
	}
	
	public void move(){								///�����������ƶ�
		int offSetX = moveEndX - moveStartX;		///x����λ��
		int offSetY = moveEndY - moveStartY;		///y����λ��
		
		startX += offSetX;
		startY += offSetY;
		endX += offSetX;
		endY += offSetY;
	}
	
	public void setState(){							///�趨state,���ڽ�ͼ��״̬����
		this.state = 0;
	}
	
	public void setStart(int x, int y){				///�趨��ʼλ��
		this.startX = x;
		this.startY = y;
	}
	
	public void setEnd(int x, int y){				///�趨����λ��
		this.endX = x;
		this.endY = y;
	}
	
}
