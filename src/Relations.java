import arrow.*;
import drawGraphics.*;

public class Relations {
     public static Boolean relation(Arrow arrow,Draw_Graphics dg){
    	 boolean rls=false;
    	 //箭头的开始位置与矩形相近，则二者相关
    	 if(arrow.startX>=dg.startX-3&&arrow.startX<=dg.endX+3&&
    	    arrow.startY>=dg.startY-3&&arrow.startY<=dg.endY+3)
    		 rls=true;
    	//箭头的结束位置与矩形相近，则二者相关
    	 if(arrow.endX+16>=dg.startX-3&&arrow.endX+16<=dg.endX+3&&
    	    arrow.endY+16>=dg.startY-3&&arrow.endY+16<=dg.endY+3)
    		 rls=true;
    	 return rls;
     }
}
