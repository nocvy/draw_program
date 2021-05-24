import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

	int lastx, lasty, newx, newy, nowx, nowy, startx, starty, endx, endy;
	Color currentColor = Color.BLACK;
	Color previousColor = Color.BLACK;
	Color eraserColor = Color.WHITE;
	Float currentWidth = 3.0f;
	BufferedImage stampImage;
	BufferedImage bufferImage = null;
	Graphics2D bufferGraphics = null;
	public static int flag = 0, tool = 0, linetype = 0,
	stamptype = 0, stampsize = 1, shapetype = 0;

	DrawPanel(){
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void createBuffer(int width, int height){
		bufferImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
		bufferGraphics = bufferImage.createGraphics();
		bufferGraphics.setBackground(Color.white);
		bufferGraphics.clearRect(0, 0, width, height);
	}
	
	public void setPenColor(Color newColor){
		flag = 0;
		currentColor = newColor;
	}

	public void setPenWidth(float newWidth){
		currentWidth = newWidth;
	}

	public void setPen(){
		flag = 0;
		tool = 0;
	}

	public void setEraser(){
		flag = 1;
		tool = 0;
	}

	// ペン 不透明度
	public void setPenOpacity(int percent){
		flag = 0;
		int R, G, B, A;
		R = currentColor.getRed();
		G = currentColor.getGreen();
		B = currentColor.getBlue();
		A = 255 * percent / 100; // 不透明度の%を変換
		currentColor = new Color(R, G, B, A);
	}

	// 消しゴム 不透明度
	public void setEraserOpacity(int percent){
		flag = 1;
		int A = 255 * percent / 100; // 不透明度の%を変換
		eraserColor = new Color(255, 255, 255, A);
	}

	// バケツ
	public void screenFill(Color color){
		bufferGraphics.setColor(color);
		bufferGraphics.fillRect(0, 0, this.getWidth(),this.getHeight());
		repaint();
	}

	// クリア
	public void screenClear(DrawPanel canvas){
		int option = JOptionPane.showConfirmDialog
			(this, "全て消去しますか？","クリアボタン", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION){
				canvas.createBuffer(canvas.getWidth(),canvas.getHeight());
				repaint();
		}
	}

	// 画像読み込み
	public void openFile(File file){
		BufferedImage pictureImage;
		try {
			pictureImage = ImageIO.read(file);
		} catch(Exception e){
			System.out.println("Error: reading file="+file.getName());
			return;
		}
		this.createBuffer(pictureImage.getWidth(),pictureImage.getHeight());
		bufferGraphics.drawImage(pictureImage,0,0,this);
		repaint();
	}

	// 保存
	public void saveFile(File file){
		try {
			ImageIO.write(bufferImage, "jpg", file);
		} catch (Exception e) {
			System.out.println("Error: writing file="+file.getName());
			return;
		}
	}

	// アンチエイリアシング
	public void lineAntialiasing(boolean bool){
		if(bool) bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		else bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	// 描画
	public void drawLine(int x1, int y1, int x2, int y2){
		if(flag == 0) bufferGraphics.setColor(currentColor);
		else if(flag == 1) bufferGraphics.setColor(eraserColor);
		
		switch(linetype){
			case 0: // Normal
				lineAntialiasing(false);
				bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
				break;
			case 1: // Marker
				lineAntialiasing(false);
				bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
				break;
			case 2: // Smooth
				lineAntialiasing(true);
				bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
				break;
			case 3: // Dotted(●)
				lineAntialiasing(true);
				float dotCircle[] = {1.0f, currentWidth*2};
				bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10.0f, dotCircle, 1.1f));
				break;
			case 4: // Dotted(■)
				lineAntialiasing(true);
				float dotSquare[] = {1.0f, currentWidth*2};
				bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, dotSquare, 1.1f));
				break;
			case 5: // Dashed
				if(currentWidth <= 10){
					lineAntialiasing(true);
					float dash[] = {10.0f, currentWidth*2};
					bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, dash, 10.1f));
				}
				else if(currentWidth <= 20){
					lineAntialiasing(true);
					float dash[] = {20.0f, currentWidth*2};
					bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, dash, 20.1f));
				}
				else{
					lineAntialiasing(true);
					float dash[] = {40.0f, currentWidth*2};
					bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, dash, 40.1f));
				}
				break;
		}
		bufferGraphics.drawLine(x1, y1, x2, y2);
	   	repaint();
	}

	// スタンプ
	public void drawStamp(int x, int y){
		File file;
		if     (stamptype == 0)  file = new File("./image/flower.png");
		else if(stamptype == 1)  file = new File("./image/cherryblossom.png");
		else if(stamptype == 2)  file = new File("./image/apple.png");
		else if(stamptype == 3)  file = new File("./image/orange.png");
		else if(stamptype == 4)  file = new File("./image/carrot.png");
		else if(stamptype == 5)  file = new File("./image/music.png");
		else if(stamptype == 6)  file = new File("./image/diamond.png");
		else if(stamptype == 7)  file = new File("./image/topaz.png");
		else if(stamptype == 8)  file = new File("./image/ruby.png");
		else if(stamptype == 9)  file = new File("./image/sun.png");
		else if(stamptype == 10) file = new File("./image/rain.png");
		else if(stamptype == 11) file = new File("./image/seal.png");
		else if(stamptype == 12) file = new File("./image/anchor.png");
		else if(stamptype == 13) file = new File("./image/flag.png");
		else 					 file = new File("./image/daruma.png");

		try {
			stampImage = ImageIO.read(file);
		} catch(Exception e){
			System.out.println("Error: reading file="+file.getName());
			return;
		}

		int width = stampImage.getWidth();
		int height = stampImage.getHeight();

		if     (stampsize == 0) bufferGraphics.drawImage(stampImage, x-width/6, y-height/6, width/3, height/3, this);
		else if(stampsize == 1) bufferGraphics.drawImage(stampImage, x-width/3, y-height/3, width*2/3, height*2/3, this);
		else                    bufferGraphics.drawImage(stampImage, x-width/2, y-height/2, width, height, this);
		repaint();
	}

	// 図形
	public void drawShape(int x1, int y1, int x2, int y2){
		if(flag == 0) bufferGraphics.setColor(currentColor);
		else if(flag == 1) bufferGraphics.setColor(eraserColor);

		lineAntialiasing(true);
		bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));

		switch(shapetype){
			case 0: // circle
				if(x2-x1 >= 0 && y2-y1 >=0) bufferGraphics.drawOval(x1, y1, x2-x1, y2-y1);
				else if(x2-x1 >= 0 && y2-y1 <=0) bufferGraphics.drawOval(x1, y2, x2-x1, y1-y2);
				else if(x2-x1 <= 0 && y2-y1 >=0) bufferGraphics.drawOval(x2, y1, x1-x2, y2-y1);
				else if(x2-x1 <= 0 && y2-y1 <=0) bufferGraphics.drawOval(x2, y2, x1-x2, y1-y2);
				break;
			case 1: // circle(fill)
				if(x2-x1 >= 0 && y2-y1 >=0) bufferGraphics.fillOval(x1, y1, x2-x1, y2-y1);
				else if(x2-x1 >= 0 && y2-y1 <=0) bufferGraphics.fillOval(x1, y2, x2-x1, y1-y2);
				else if(x2-x1 <= 0 && y2-y1 >=0) bufferGraphics.fillOval(x2, y1, x1-x2, y2-y1);
				else if(x2-x1 <= 0 && y2-y1 <=0) bufferGraphics.fillOval(x2, y2, x1-x2, y1-y2);
				break;
			case 2: // square
				if(x2-x1 >= 0 && y2-y1 >=0) bufferGraphics.drawRect(x1, y1, x2-x1, y2-y1);
				else if(x2-x1 >= 0 && y2-y1 <=0) bufferGraphics.drawRect(x1, y2, x2-x1, y1-y2);
				else if(x2-x1 <= 0 && y2-y1 >=0) bufferGraphics.drawRect(x2, y1, x1-x2, y2-y1);
				else if(x2-x1 <= 0 && y2-y1 <=0) bufferGraphics.drawRect(x2, y2, x1-x2, y1-y2);
				break;
			case 3: // square(fill)
				if(x2-x1 >= 0 && y2-y1 >=0) bufferGraphics.fillRect(x1, y1, x2-x1, y2-y1);
				else if(x2-x1 >= 0 && y2-y1 <=0) bufferGraphics.fillRect(x1, y2, x2-x1, y1-y2);
				else if(x2-x1 <= 0 && y2-y1 >=0) bufferGraphics.fillRect(x2, y1, x1-x2, y2-y1);
				else if(x2-x1 <= 0 && y2-y1 <=0) bufferGraphics.fillRect(x2, y2, x1-x2, y1-y2);
				break;
		}
		repaint();
	}
		

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(null != bufferImage){
			g.drawImage(bufferImage, 0,0,this);
		}
	}

	@Override public void mouseClicked(MouseEvent e) {
		nowx = e.getX();
		nowy = e.getY();
		
		if(flag == 2) drawStamp(nowx, nowy);
    }
    @Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {
	 	lastx = e.getX();
	 	lasty = e.getY();

		startx = e.getX();
		starty = e.getY();
	}
	@Override public void mouseReleased(MouseEvent e) {
		endx = e.getX();
		endy = e.getY();
		
		if(tool == 1) drawLine(startx, starty, endx, endy);
		else if (tool == 2) drawShape(startx, starty, endx, endy);
	}
	@Override public void mouseMoved(MouseEvent e) {}
	@Override public void mouseDragged(MouseEvent e) {
		newx = e.getX();
        newy = e.getY();
		if(tool == 0) drawLine(lastx,lasty,newx,newy);
		lastx = newx;
		lasty = newy;
	}

}