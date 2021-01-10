package tanchishe.com.hui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePancel extends JPanel implements KeyListener, ActionListener{
	//��
	List<SnakeBody> bodyList ;
	String fx  ;
	//�Ƿ�ʼ
	boolean isStart = false;
	//��С��λ
	final static int cell = 25;
	//��ʱ��
	Timer timer = new Timer(200, this);
	//�������
	SnakeBody food ; 
	
	int score = 0;
	Random random = new Random(System.currentTimeMillis());
	
	boolean isEnd ;
	
	public void init() {
		isStart = false;
		isEnd = false;
		bodyList = new ArrayList<SnakeBody>();
		fx = "r";
		for (int i = 0; i < 3; i++) {
			SnakeBody body = new SnakeBody(100-cell*i,100);
			bodyList.add(body);
		}
		score = 0;
		initFood();
		//������ʱ��
		timer.start();
	}
	public GamePancel() {
		init();
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	private void initFood() {

		food = new SnakeBody(25 + random.nextInt(32)*cell, 100 + random.nextInt(24)*cell);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		new ImageIcon("img/title.jpg").paintIcon(this, g, 0,0);
		g.fillRect(25, 100,800, 600);
		
		//����С��
		//��ͷ
		new ImageIcon("img/"+fx+".png").paintIcon(this, g, bodyList.get(0).x,bodyList.get(0).y);
		//����
		for (int i = 1; i < bodyList.size(); i++) {
			new ImageIcon("img/b.png").paintIcon(this, g, bodyList.get(i).x,bodyList.get(i).y);
		}
		new ImageIcon("img/food.png").paintIcon(this, g, food.x,food.y);
		if (!isStart) {
			g.setColor(Color.white);
			g.setFont(new Font("΢���ź�", Font.BOLD, 40));
			g.drawString("���ո����ʼ��Ϸ", 300, 300);
		}

		g.setColor(Color.blue);
		g.setFont(new Font("΢���ź�", Font.BOLD,20));
		g.drawString("����: "+score, 300, 30);
		
		if (isEnd) {
			g.setColor(Color.red);
			g.setFont(new Font("΢���ź�", Font.BOLD, 40));
			g.drawString("��Ϸ����,�÷�:"+score, 300, 400);
			g.setColor(Color.white);
			g.setFont(new Font("΢���ź�", Font.BOLD, 40));
			g.drawString("���ո�����¿�ʼ��Ϸ", 200, 300);
			new ImageIcon("img/zha.png").paintIcon(this, g, bodyList.get(0).x-10,bodyList.get(0).y-10);
			init();
		}
	}
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE) {
			isStart = !isStart;
		}
		if (isStart) {
			if (keyCode == KeyEvent.VK_UP && !fx.equals("d")) {
				fx = "u";
			}
			if (keyCode == KeyEvent.VK_DOWN&& !fx.equals("u")) {
				fx = "d";
			}
			if (keyCode == KeyEvent.VK_LEFT&& !fx.equals("r")) {
				fx = "l";
			}
			if (keyCode == KeyEvent.VK_RIGHT&& !fx.equals("l")) {
				fx = "r";
			}
		}
		
		repaint();
	}

	/**
	 * ���r��
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (isStart) {
			for (int i = bodyList.size()-1;i>0; i--) {
				bodyList.get(i).x = bodyList.get(i-1).x;
				bodyList.get(i).y = bodyList.get(i-1).y;
			}
			//����ͷ��λ��
			if (fx.equals("u")) {
				bodyList.get(0).y -= cell; 
				if (bodyList.get(0).y < 100) {
					bodyList.get(0).y = 675;
				}
			}
			if (fx.equals("d")) {
				bodyList.get(0).y += cell; 
				if (bodyList.get(0).y > 675) {
					bodyList.get(0).y = 100;
				}
			}
			if (fx.equals("l")) {
				bodyList.get(0).x -= cell;
				if (bodyList.get(0).x < 25) {
					bodyList.get(0).x = 800;
				}
			}
			if (fx.equals("r")) {
				bodyList.get(0).x += cell; 
				if (bodyList.get(0).x >800) {
					bodyList.get(0).x = 25;
				}
			}
			//�ж�ҧ���Լ�
			for (int i = bodyList.size()-1;i>0; i--) {

				if (bodyList.get(0).x == bodyList.get(i).x && bodyList.get(0).y == bodyList.get(i).y) {
					isEnd = true;
				}
			}
			if (bodyList.get(0).x == food.x && bodyList.get(0).y == food.y) {
				bodyList.add(food);
				score +=1;
				initFood();
			}
			repaint();
		}
		
	}
	
	
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
