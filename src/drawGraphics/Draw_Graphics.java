package drawGraphics;

import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

public abstract class Draw_Graphics implements Serializable{
	
	///设定鼠标移动前后位置，用于鼠标拖拽函数
	protected int moveStartX, moveStartY;
	protected int moveEndX, moveEndY;	
	protected int state = 0;		///用于判断鼠标与矩形框的关系
	///矩形左上角右下角两点坐标
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	public int check=0;				///加：判断是否被ctrl选中
	public abstract void setState(int x, int y);				///判断鼠标与矩形框的位置关系
	public abstract void draw(Graphics2D g);					///画图形
	public abstract void offSet(int x, int y);								///位移
	
	public int getState(){							///得到状态
		return this.state;
	}
	
	public void setMoveStart(int x, int y){			///取得鼠标移动初始位置
		this.moveStartX = x;
		this.moveStartY = y;
	}
	
	public void setMoveEnd(int x, int y){			///取得鼠标移动终止位置
		this.moveEndX = x;
		this.moveEndY = y;
	}
	
	public void move(){								///矩形坐标变更移动
		int offSetX = moveEndX - moveStartX;		///x方向位移
		int offSetY = moveEndY - moveStartY;		///y方向位移
		
		startX += offSetX;
		startY += offSetY;
		endX += offSetX;
		endY += offSetY;
	}
	
	public void setState(){							///设定state,用于将图形状态清零
		this.state = 0;
	}
	
	public void setStart(int x, int y){				///设定初始位置
		this.startX = x;
		this.startY = y;
	}
	
	public void setEnd(int x, int y){				///设定最终位置
		this.endX = x;
		this.endY = y;
	}
	
}
