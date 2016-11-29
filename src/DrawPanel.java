import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import arrow.*;
import javax.swing.*;
import drawGraphics.*;
import drawGraphics.Rectangle;

public class DrawPanel extends JPanel implements MouseListener,MouseMotionListener, ActionListener, KeyListener {
	JMenuItem fileCleanItem = new JMenuItem("���"); 				///"�ļ�"�˵� �����
	JMenuItem fileExitItem = new JMenuItem("�˳�"); 				///"�ļ�"�˵� �˳���
	JMenuItem saveItem = new JMenuItem("����");
	JMenuItem openItem = new JMenuItem("����");
	
	JMenuItem arrow_default = new JMenuItem("������ͷ"); 			///"����"�˵� �ָ�����
	JMenuItem paintRectangle = new JMenuItem("��"); 				///"����"�˵� ��
	JMenuItem paintPackage = new JMenuItem("��"); 				///"����"�˵� ��
	JMenuItem paintNote = new JMenuItem("ע��"); 					///"����"�˵� ע��
	
	JMenuItem paintGeneralization = new JMenuItem("����");		///"����"�˵�	����
	JMenuItem paintRealization = new JMenuItem("ʵ��");			///"����"�˵�	ʵ��
	JMenuItem paintAssociation = new JMenuItem("����");			///"����"�˵�	����
	JMenuItem paintAggregation = new JMenuItem("�ۺ�");			///"����"�˵�	�ۺ�
	JMenuItem paintComposition = new JMenuItem("���");			///"����"�˵�	���
	JMenuItem paintDependency = new JMenuItem("����");			///"����"�˵�	����
	
	JMenuItem left = new JMenuItem("�����");
	JMenuItem right = new JMenuItem("�Ҷ���");
	JMenuItem level = new JMenuItem("ˮƽ����");
	JMenuItem vertical = new JMenuItem("��ֱ����");
	JMenuItem top = new JMenuItem("���˶���");
	JMenuItem bottom = new JMenuItem("�׶˶���");
	
	JMenu fileMenu = new JMenu("�ļ�"); 											///"�ļ�"�˵�
	JMenu paintMenu = new JMenu("ͼԪ"); 											///"����"�˵�
	JMenu relationMenu = new JMenu("��ϵ"); 										///"��ϵ"�˵�
	JMenu rankMenu = new JMenu("���з�ʽ");										///"����"�˵�
	
	JMenuBar fileMenuBar = new JMenuBar(); 										///�˵���
	ArrayList<Draw_Graphics> listDG = new ArrayList<Draw_Graphics>(); 			///�������
	ArrayList<Arrow> listArrow = new ArrayList<Arrow>();						///�����ͷ
	
	FileOutputStream ft = null;
	FileInputStream in = null;
	ObjectOutputStream ot = null;
	ObjectInputStream oi = null;
	FileDialog fd,op;
	
	int style = 0; 																/// ���滭ͼ���ͣ�Ĭ��Ϊ������ͷ��ͷ
	boolean ctrl = false;														///�Ƿ���ctrl��
	boolean delete = false;														///�Ƿ���ctrl��

	public DrawPanel(DrawFrame df) {
		setBackground(Color.WHITE); 											///����ɫ
		setLayout(new BorderLayout()); 											///����
		///�����Ŀ
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
		// /��Ӽ���
		fileExitItem.addActionListener(this); // /��Ӽ���
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
		df.addKeyListener(this);// ��
		fd = new FileDialog(df,"����UML", FileDialog.SAVE);
		fd.setVisible(false);
		op=new FileDialog(df, "����UML", FileDialog.LOAD);
		op.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(fileCleanItem)) { 				///"�ļ�"�˵� �����
			listDG.clear();
			listArrow.clear();
			repaint();
		} else if (e.getSource().equals(saveItem)){
			save(listDG,listArrow);
		}else if (e.getSource().equals(openItem)){
			open();
		} else if (e.getSource().equals(fileExitItem)) { 		///"�ļ�"�˵� �˳���
			File_menu fm = new File_menu();
			fm.fileExit();
		} else if (e.getSource().equals(arrow_default)) {		 ///"����"�˵� ������ͷ
			style = 0;
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} else if (e.getSource().equals(paintRectangle)) { 		///"����"�˵� ����
			style = 10;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		} else if(e.getSource().equals(left)){					//�����
			for (int i = 0; i < listDG.size(); i++) {
				Draw_Graphics dg = listDG.get(i);
				if (dg.check == 1) { 							//�����Ctrlѡ�У����ƶ�
					dg.setEnd(10+dg.endX-dg.startX,30+dg.endY-dg.startY);
					dg.setStart(10,30);
					dg.check=0;							//�޸ı�ѡ�е�״̬
				}
			}	
		}if(e.getSource().equals(paintGeneralization)){			///"����"�˵�	����
			style = 1;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintRealization)){		///"����"�˵�	ʵ��
			style = 2;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintAssociation)){		///"����"�˵�	����
			style = 3;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintAggregation)){		///"����"�˵�	�ۺ�
			style = 4;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintComposition)){		///"����"�˵�	���
			style = 5;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else if(e.getSource().equals(paintDependency)){		///"����"�˵�	����
			style = 6;
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		repaint();
	}

	public void paint(Graphics g) { 									///��дpaint()����
		super.paint(g);
		draw((Graphics2D) g);
	}

	public void draw(Graphics2D g) { 									///��дdraw()����
		try {
			for (int i = 0; i < listDG.size(); i++) { 					///��֮ǰ�Ļ�����
				Draw_Graphics dgOld = listDG.get(i);
				dgOld.draw(g);
			}
			for(int i=0; i<listArrow.size(); i++){						///��֮ǰ�Ļ�����
				Arrow arrowOld = listArrow.get(i);
				arrowOld.draw(g);
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (style == 0 && listDG != null) {							///�϶��ж�
			for (int i = 0; i < listDG.size(); i++) { 				///����жϸ�ͼ��״̬
				Draw_Graphics dg = listDG.get(i);
				if (dg.getState() == 1) { 							///���λ���м䣬�ƶ�
					dg.setMoveEnd(e.getX(), e.getY());
					dg.move();
					dg.setMoveStart(e.getX(), e.getY());
				} else if (dg.getState() != 0) { 					///���λ�ڱ�Ե������
					dg.offSet(e.getX(), e.getY());
				}
			}
		} else if (style == 10) { 				///����
			int n = listDG.size();
			Draw_Graphics dg = listDG.get(n - 1);
			dg.setEnd(e.getX(), e.getY());
		}
		switch(style){				///���������״�ж�Ҫ��
		case 1:						///����
			Arrow arrow_gene = listArrow.get(listArrow.size()-1);
			arrow_gene.setEnd(e.getX(), e.getY());
			break;
		case 2:						///ʵ��
			Arrow arrow_real = listArrow.get(listArrow.size()-1);
			arrow_real.setEnd(e.getX(), e.getY());
			break;
		case 3:						///����
			Arrow arrow_asso = listArrow.get(listArrow.size()-1);
			arrow_asso.setEnd(e.getX(), e.getY());
			break;
		case 4:						///�ۺ�
			Arrow arrow_aggre = listArrow.get(listArrow.size()-1);
			arrow_aggre.setEnd(e.getX(), e.getY());
			break;
		case 5:						///���
			Arrow arrow_com = listArrow.get(listArrow.size()-1);
			arrow_com.setEnd(e.getX(), e.getY());
			break;
		case 6:						///����
			Arrow arrow_depen = listArrow.get(listArrow.size()-1);
			arrow_depen.setEnd(e.getX(), e.getY());
			break;
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (style) { 												///���������״�ж�Ҫ��
		case 0: 														///������ͷ
			if (listDG.size() != 0) {									///�粻Ϊ�գ�����ÿ�������״̬
				for (int i = 0; i < listDG.size(); i++) {
					Draw_Graphics dg = listDG.get(i);
					dg.setState(e.getX(), e.getY());
					if (dg.getState() == 1) { 							///���λ���м䣬
						if (delete) {									///��delete������ֱ��ɾ��
							for(int j=0; j<listArrow.size(); j++){		///������ͷ�Ƿ���������
								if(Relations.relation(listArrow.get(j),dg)){
									listArrow.remove(j);				///������Ƴ���ͷ
								}
							}
							listDG.remove(i);
						} else if (ctrl) {								///���ctrl�����£���ѡ�е�ͼ�񷵻�
							if(listDG.get(i).check == 0){
								listDG.get(i).check = 1;				///��ͼ�α�ctrlѡ��
							} else {
								listDG.get(i).check = 0;				///ȡ��ѡ��
							}
						} else {										///�������ƶ�
							dg.setMoveStart(e.getX(), e.getY());
						}
					} else if (dg.getState() != 0) { 					///���λ�ڱ�Ե������
						dg.offSet(e.getX(), e.getY());
					}
				}
			}
			break;
		case 10: 														///������
			Draw_Graphics dg_rectangle = new Rectangle(); 				///�½�һ������
			listDG.add(dg_rectangle); 									///����½�����
			dg_rectangle.setStart(e.getX(), e.getY());
			dg_rectangle.setEnd(e.getX(), e.getY());
			break;
		}
		switch(style){				///���������״�ж�Ҫ��
		case 1:						///����
			Arrow arrow_gene = new Generalization();
			listArrow.add(arrow_gene);
			arrow_gene.setStart(e.getX(), e.getY());
			arrow_gene.setEnd(e.getX(), e.getY());
			break;
		case 2:						///ʵ��
			Arrow arrow_real = new Realization();
			listArrow.add(arrow_real);
			arrow_real.setStart(e.getX(), e.getY());
			arrow_real.setEnd(e.getX(), e.getY());
			break;
		case 3:						///����
			Arrow arrow_asso = new Association();
			listArrow.add(arrow_asso);
			arrow_asso.setStart(e.getX(), e.getY());
			arrow_asso.setEnd(e.getX(), e.getY());
			break;
		case 4:						///�ۺ�
			Arrow arrow_aggre = new Aggregation();
			listArrow.add(arrow_aggre);
			arrow_aggre.setStart(e.getX(), e.getY());
			arrow_aggre.setEnd(e.getX(), e.getY());
			break;
		case 5:						///���
			Arrow arrow_com = new Composition();
			listArrow.add(arrow_com);
			arrow_com.setStart(e.getX(), e.getY());
			arrow_com.setEnd(e.getX(), e.getY());
			break;
		case 6:						///����
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
			for (int i = 0; i < listDG.size(); i++) { 					///�����о����state��ʼ��Ϊ0
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
		switch(style){				///���������״�ж�Ҫ��
		case 1:						///����
			Arrow arrow_gene = listArrow.get(listArrow.size()-1);
			arrow_gene.setEnd(e.getX(), e.getY());
			break;
		case 2:						///ʵ��
			Arrow arrow_real = listArrow.get(listArrow.size()-1);
			arrow_real.setEnd(e.getX(), e.getY());
			break;
		case 3:						///����
			Arrow arrow_asso = listArrow.get(listArrow.size()-1);
			arrow_asso.setEnd(e.getX(), e.getY());
			break;
		case 4:						///�ۺ�
			Arrow arrow_aggre = listArrow.get(listArrow.size()-1);
			arrow_aggre.setEnd(e.getX(), e.getY());
			break;
		case 5:						///���
			Arrow arrow_com = listArrow.get(listArrow.size()-1);
			arrow_com.setEnd(e.getX(), e.getY());
			break;
		case 6:						///����
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
		//����
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
