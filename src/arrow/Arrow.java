package arrow;

import java.awt.Graphics2D;

public abstract class Arrow {
	
	///初始画图坐标
	public int startX=0;
	public int startY=0;
	public int endX=0, endY=0;
	///箭头中间点坐标,用于将线段从中间分开，画出折线
	protected int midX1, midY1;
	protected int midX2, midY2;
	///箭头、三角形、菱形边长
	protected static final int LENGTH = 14;
	///判断箭头指向，值为1为水平方向，值为2为竖直方向
	protected int direction;
	
	///判断箭头从上下或者是左右画出,上下值为1，左右值为2
	protected int dir;
	
	public abstract void draw(Graphics2D g);					///画图形
	
	public void setdir(int dir){
		this.dir = dir;
	}
	
	public void setStart(int x, int y){				///设定初始位置
		this.startX = x;
		this.startY = y;
	}
	
	public void setEnd(int x, int y){				///设定最终位置
		this.endX = x;
		this.endY = y;
	}
		
	protected void setLinePosition(){						///设置箭头中间点，将线段划分开
		/*if(this.dir == 1){									///线头由上下引出，y从中截断
			int midY = startY + (endY - startY) / 2;				
			midY1 = midY;
			midY2 = midY;
			midX1 = startX;
			midX2 = endX;
		}else if(dir == 2){									///线头由左右引出，x从中截断
			int midX = startX + (endX - startX) / 2;
			midX1 = midX;
			midX2 = midX;
			midY1 = startY;
			midY2 = endY;
		}*/
		
		if(Math.abs(endX - startX) >= Math.abs(endY - startY)){			///水平方向差值大于竖直方向，取水平方向中点
			int midX = startX + (endX - startX) / 2;
			midX1 = midX;
			midX2 = midX;
			midY1 = startY;
			midY2 = endY;
			direction = 1;			///箭头指向水平方向
		} else {								///竖直方向差值大于水平方向，取竖直方向中点
			int midY = startY + (endY - startY) / 2;				
			midY1 = midY;
			midY2 = midY;
			midX1 = startX;
			midX2 = endX;
			direction = 2;			///箭头指向竖直方向
		}
			
	}
	
}
