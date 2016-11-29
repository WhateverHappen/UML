import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import arrow.*;
import javax.swing.*;
import drawGraphics.*;
import drawGraphics.Rectangle;

public class DrawPanel extends JPanel implements MouseListener,MouseMotionListener, ActionListener, KeyListener {
	JMenuItem fileCleanItem = new JMenuItem("清空"); 				///"文件"菜单 清空项
	JMenuItem fileExitItem = new JMenuItem("退出"); 				///"文件"菜单 退出项
	JMenuItem saveItem = new JMenuItem("保存");
	JMenuItem openItem = new JMenuItem("导入");
	
	JMenuItem arrow_default = new JMenuItem("正常箭头"); 			///"绘制"菜单 恢复正常
	JMenuItem paintRectangle = new JMenuItem("类"); 				///"绘制"菜单 类
	JMenuItem paintPackage = new JMenuItem("包"); 				///"绘制"菜单 包
	JMenuItem paintNote = new JMenuItem("注释"); 					///"绘制"菜单 注释
	
	JMenuItem paintGeneralization = new JMenuItem("泛化");		///"绘制"菜单	泛化
	JMenuItem paintRealization = new JMenuItem("实现");			///"绘制"菜单	实现
	JMenuItem paintAssociation = new JMenuItem("关联");			///"绘制"菜单	关联
	JMenuItem paintAggregation = new JMenuItem("聚合");			///"绘制"菜单	聚合
	JMenuItem paintComposition = new JMenuItem("组合");			///"绘制"菜单	组合
	JMenuItem paintDependency = new JMenuItem("依赖");			///"绘制"菜单	依赖
	
	JMenuItem left = new JMenuItem("左对齐");
	JMenuItem right = new JMenuItem("右对齐");
	JMenuItem level = new JMenuItem("水平居中");
	JMenuItem vertical = new JMenuItem("垂直居中");
	JMenuItem top = new JMenuItem("顶端对齐");
	JMenuItem bottom = new JMenuItem("底端对齐");
	
	JMenu fileMenu = new JMenu("文件"); 											///"文件"菜单
	JMenu paintMenu = new JMenu("图元"); 											///"绘制"菜单
	JMenu relationMenu = new JMenu("关系"); 										///"关系"菜单
	JMenu rankMenu = new JMenu("排列方式");										///"排列"菜单
	
	JMenuBar fileMenuBar = new JMenuBar(); 										///菜单栏
	ArrayList<Draw_Graphics> listDG = new ArrayList<Draw_Graphics>(); 			///储存矩阵
	ArrayList<Arrow> listArrow = new ArrayList<Arrow>();						///储存箭头
	
	FileOutputStream ft = null;
	FileInputStream in = null;
	ObjectOutputStream ot = null;
	ObjectInputStream oi = null;
	FileDialog fd,op;
	
	int style = 0; 																/// 保存画图类型，默认为正常箭头箭头
	boolean ctrl = false;														///是否按下ctrl键
	boolean delete = false;														///是否按下ctrl键

	public DrawPanel(DrawFrame df) {
		setBackground(Color.WHITE); 											///背景色
		setLayout(new BorderLayout()); 											///布局
		///添加项目
		fileMenu.add(fileCleanItem);
		fileMenu.add(saveItem);
		fileMenu.add(openItem);
		fileMenu.add(fileExitItem);
		
		paintMenu.add(arrow_default);
		paintMenu.add(paintRectangle);
		paintMenu.add(paintPackage);
		paintMenu.add(paintNote);
		
		relationMenu.add(paintGeneralization);
		relationMenu.add(paintRealization);
		relationMenu.add(paintAssociation);
		relationMenu.add(paintAggregation);
		relationMenu.add(paintComposition);
		relationMenu.add(paintDependency);
		
		rankMenu.add(left);
		rankMenu.add(right);
		rankMenu.add(level);
		rankMenu.add(vertical);
		rankMenu.add(top);
		rankMenu.add(bottom);
		
		fileMenuBar.add(fileMenu);
		fileMenuBar.add(paintMenu);
		fileMenuBar.add(relationMenu);
		fileMenuBar.add(rankMenu);
		add(fileMenuBar, BorderLayout.NORTH);
		// /添加监听
		fileExitItem.addActionListener(this); // /添加监听
		fileCleanItem.addActionListener(this);
		saveItem.addActionListener(this);
		openItem.addActionListener(this);
		arrow_default.addActionListener(this);
		paintRectangle.addActionListener(this);
		paintPackage.addActionListener(this);
		paintNote.addActionListener(this);
		paintGeneralization.addActionListener(this);
		paintRealization.addActionListener(this);
		paintAssociation.addActionListener(this);
		paintAggregation.addActionListener(this);
		paintComposition.addActionListener(this);
		paintDependency.addActionListener(this);
		left.addActionListener(this);
		right.addActionListener(this);
		level.addActionListener(this);
		vertical.addActionListener(this);
		top.addActionListener(this);
		bottom.addActionListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		df.addKeyListener(this);// 加
		fd = new FileDialog(df,"保存UML", FileDialog.SAVE);
		fd.setVisible(false);
		op=new FileDialog(df, "导入UML", FileDialog.LOAD);
		op.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(fileCleanItem)) { 				///"文件"菜单 清空项
			listDG.clear();
			listArrow.clear();
			repaint();
		} else if (e.getSource().equals(saveItem)){
			save(listDG,listArrow);
		}else if (e.getSource().equals(openItem)){
			open();
		} else if (e.getSource().equals(fileExitItem)) { 		///"文件"菜单 退出项
			File_menu fm = new File_menu();
			fm.fileExit();
		} else if (e.getSource().equals(arrow_default)) {		 ///"绘制"菜单 正常箭头
			style = 0;
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} else if (e.getSource().equals(paintRectangle)) { 		///"绘制"菜单 矩形
			style = 10;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		} else if(e.getSource().equals(left)){					//左对齐
			for (int i = 0; i < listDG.size(); i++) {
				Draw_Graphics dg = listDG.get(i);
				if (dg.check == 1) { 							//如果被Ctrl选中，怎移动
					dg.setEnd(10+dg.endX-dg.startX,30+dg.endY-dg.startY);
					dg.setStart(10,30);
					dg.check=0;							//修改被选中的状态
				}
			}	
		}if(e.getSource().equals(paintGeneralization)){			///"绘制"菜单	泛化
			style = 1;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintRealization)){		///"绘制"菜单	实现
			style = 2;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintAssociation)){		///"绘制"菜单	关联
			style = 3;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintAggregation)){		///"绘制"菜单	聚合
			style = 4;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintComposition)){		///"绘制"菜单	组合
			style = 5;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintDependency)){		///"绘制"菜单	依赖
			style = 6;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		repaint();
	}

	public void paint(Graphics g) { 									///重写paint()方法
		super.paint(g);
		draw((Graphics2D) g);
	}

	public void draw(Graphics2D g) { 									///重写draw()方法
		try {
			for (int i = 0; i < listDG.size(); i++) { 					///将之前的画出来
				Draw_Graphics dgOld = listDG.get(i);
				dgOld.draw(g);
			}
			for(int i=0; i<listArrow.size(); i++){						///将之前的画出来
				Arrow arrowOld = listArrow.get(i);
				arrowOld.draw(g);
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (style == 0 && listDG != null) {							///拖动判断
			for (int i = 0; i < listDG.size(); i++) { 				///逐个判断各图形状态
				Draw_Graphics dg = listDG.get(i);
				if (dg.getState() == 1) { 							///鼠标位于中间，移动
					dg.setMoveEnd(e.getX(), e.getY());
					dg.move();
					dg.setMoveStart(e.getX(), e.getY());
				} else if (dg.getState() != 0) { 					///鼠标位于边缘，缩放
					dg.offSet(e.getX(), e.getY());
				}
			}
		} else if (style == 10) { 				///矩形
			int n = listDG.size();
			Draw_Graphics dg = listDG.get(n - 1);
			dg.setEnd(e.getX(), e.getY());
		}
		switch(style){				///根据鼠标形状判断要求
		case 1:						///泛化
			Arrow arrow_gene = listArrow.get(listArrow.size()-1);
			arrow_gene.setEnd(e.getX(), e.getY());
			break;
		case 2:						///实现
			Arrow arrow_real = listArrow.get(listArrow.size()-1);
			arrow_real.setEnd(e.getX(), e.getY());
			break;
		case 3:						///关联
			Arrow arrow_asso = listArrow.get(listArrow.size()-1);
			arrow_asso.setEnd(e.getX(), e.getY());
			break;
		case 4:						///聚合
			Arrow arrow_aggre = listArrow.get(listArrow.size()-1);
			arrow_aggre.setEnd(e.getX(), e.getY());
			break;
		case 5:						///组合
			Arrow arrow_com = listArrow.get(listArrow.size()-1);
			arrow_com.setEnd(e.getX(), e.getY());
			break;
		case 6:						///依赖
			Arrow arrow_depen = listArrow.get(listArrow.size()-1);
			arrow_depen.setEnd(e.getX(), e.getY());
			break;
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (style) { 												///根据鼠标形状判断要求
		case 0: 														///正常箭头
			if (listDG.size() != 0) {									///如不为空，设置每个矩阵的状态
				for (int i = 0; i < listDG.size(); i++) {
					Draw_Graphics dg = listDG.get(i);
					dg.setState(e.getX(), e.getY());
					if (dg.getState() == 1) { 							///鼠标位于中间，
						if (delete) {									///若delete被按下直接删除
							for(int j=0; j<listArrow.size(); j++){		///遍历箭头是否与矩形相关
								if(Relations.relation(listArrow.get(j),dg)){
									listArrow.remove(j);				///相关则移除箭头
								}
							}
							listDG.remove(i);
						} else if (ctrl) {								///如果ctrl被按下，将选中的图像返回
							if(listDG.get(i).check == 0){
								listDG.get(i).check = 1;				///该图形被ctrl选中
							} else {
								listDG.get(i).check = 0;				///取消选中
							}
						} else {										///否则是移动
							dg.setMoveStart(e.getX(), e.getY());
						}
					} else if (dg.getState() != 0) { 					///鼠标位于边缘，缩放
						dg.offSet(e.getX(), e.getY());
					}
				}
			}
			break;
		case 10: 														///画矩阵
			Draw_Graphics dg_rectangle = new Rectangle(); 				///新建一个矩形
			listDG.add(dg_rectangle); 									///添加新建矩阵
			dg_rectangle.setStart(e.getX(), e.getY());
			dg_rectangle.setEnd(e.getX(), e.getY());
			break;
		}
		switch(style){				///根据鼠标形状判断要求
		case 1:						///泛化
			Arrow arrow_gene = new Generalization();
			listArrow.add(arrow_gene);
			arrow_gene.setStart(e.getX(), e.getY());
			arrow_gene.setEnd(e.getX(), e.getY());
			break;
		case 2:						///实现
			Arrow arrow_real = new Realization();
			listArrow.add(arrow_real);
			arrow_real.setStart(e.getX(), e.getY());
			arrow_real.setEnd(e.getX(), e.getY());
			break;
		case 3:						///关联
			Arrow arrow_asso = new Association();
			listArrow.add(arrow_asso);
			arrow_asso.setStart(e.getX(), e.getY());
			arrow_asso.setEnd(e.getX(), e.getY());
			break;
		case 4:						///聚合
			Arrow arrow_aggre = new Aggregation();
			listArrow.add(arrow_aggre);
			arrow_aggre.setStart(e.getX(), e.getY());
			arrow_aggre.setEnd(e.getX(), e.getY());
			break;
		case 5:						///组合
			Arrow arrow_com = new Composition();
			listArrow.add(arrow_com);
			arrow_com.setStart(e.getX(), e.getY());
			arrow_com.setEnd(e.getX(), e.getY());
			break;
		case 6:						///依赖
			Arrow arrow_depen = new Dependency();
			listArrow.add(arrow_depen);
			arrow_depen.setStart(e.getX(), e.getY());
			arrow_depen.setEnd(e.getX(), e.getY());
			break;
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (style == 0) {
			for (int i = 0; i < listDG.size(); i++) { 					///将所有矩阵的state初始化为0
				Draw_Graphics dg = listDG.get(i);
				if (dg.getState() == 1) {
					dg.setMoveEnd(e.getX(), e.getY());
				}
				dg.setState();
			}
		} else if (style == 10) {
			Draw_Graphics dg = listDG.get(listDG.size() - 1);
			dg.setEnd(e.getX(), e.getY());
		}
		switch(style){				///根据鼠标形状判断要求
		case 1:						///泛化
			Arrow arrow_gene = listArrow.get(listArrow.size()-1);
			arrow_gene.setEnd(e.getX(), e.getY());
			break;
		case 2:						///实现
			Arrow arrow_real = listArrow.get(listArrow.size()-1);
			arrow_real.setEnd(e.getX(), e.getY());
			break;
		case 3:						///关联
			Arrow arrow_asso = listArrow.get(listArrow.size()-1);
			arrow_asso.setEnd(e.getX(), e.getY());
			break;
		case 4:						///聚合
			Arrow arrow_aggre = listArrow.get(listArrow.size()-1);
			arrow_aggre.setEnd(e.getX(), e.getY());
			break;
		case 5:						///组合
			Arrow arrow_com = listArrow.get(listArrow.size()-1);
			arrow_com.setEnd(e.getX(), e.getY());
			break;
		case 6:						///依赖
			Arrow arrow_depen = listArrow.get(listArrow.size()-1);
			arrow_depen.setEnd(e.getX(), e.getY());
			break;
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.isControlDown()) {
			ctrl = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			delete = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			ctrl = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			delete = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public void save(ArrayList<Draw_Graphics> lg,ArrayList<Arrow> la){
		//保存
		fd.setVisible(true);
		try {
			File fileout = new File(fd.getDirectory(),fd.getFile());
			ft = new FileOutputStream(fileout);//?
			ot = new ObjectOutputStream(ft);
			ArrayList<Object> objs=new ArrayList<Object>();
			for(int i=0;i<lg.size();i++){
				objs.add(lg.get(i));
			}
			for(int i=0;i<la.size();i++){
				objs.add(la.get(i));
			}
			ot.writeObject(objs);
			ot.close();
		} catch (IOException IOe) {
			IOe.printStackTrace();
			System.out.println("can not write object");
		}
	}
	public void open(){
		op.setVisible(true);
		File filein = new File(op.getDirectory(),op.getFile());
		try {
			in =new FileInputStream(filein);
			oi = new ObjectInputStream(in);
			ArrayList<Object> obj = (ArrayList<Object>) oi.readObject();
			for(int i=0;i<obj.size();i++){
				if(obj.get(i) instanceof Draw_Graphics){
					listDG.add((Draw_Graphics)obj.get(i));
				}else if(obj.get(i) instanceof Arrow){
					listArrow.add((Arrow)obj.get(i));
				}
			}
			repaint();
			oi.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
