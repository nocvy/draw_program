import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class SimpleDraw extends JFrame implements ActionListener{

	public static DrawPanel canvas;
	PalettePanel1 palette1;
	PalettePanel2 palette2;
	JFileChooser fileChooser;
	public static JColorChooser colorchooser, colorchooserF;
	public static Color color, colorF;
	ImageIcon icon1 = new ImageIcon("./image/flowericon.png");
	ImageIcon icon2 = new ImageIcon("./image/cherryblossomicon.png");
	ImageIcon icon3 = new ImageIcon("./image/appleicon.png");
	ImageIcon icon4 = new ImageIcon("./image/orangeicon.png");
	ImageIcon icon5 = new ImageIcon("./image/carroticon.png");
	ImageIcon icon6 = new ImageIcon("./image/musicicon.png");
	ImageIcon icon7 = new ImageIcon("./image/diamondicon.png");
	ImageIcon icon8 = new ImageIcon("./image/topazicon.png");
	ImageIcon icon9 = new ImageIcon("./image/rubyicon.png");
	ImageIcon icon10 = new ImageIcon("./image/sunicon.png");
	ImageIcon icon11 = new ImageIcon("./image/rainicon.png");
	ImageIcon icon12 = new ImageIcon("./image/sealicon.png");
	ImageIcon icon13 = new ImageIcon("./image/anchoricon.png");
	ImageIcon icon14 = new ImageIcon("./image/flagicon.png");
	ImageIcon icon15 = new ImageIcon("./image/darumaicon.png");
    

	private void addMenuItem
	(JMenu targetMenu, String itemName, String actionName, ActionListener listener){
		JMenuItem menuItem = new JMenuItem(itemName);
		menuItem.setActionCommand(actionName);
		menuItem.addActionListener(listener);
		targetMenu.add(menuItem);
	}
	private void addMenuItemIcon
	(JMenu targetMenu, String itemName, String actionName, ImageIcon icon, ActionListener listener){
		JMenuItem menuItem = new JMenuItem(itemName, icon);
		menuItem.setActionCommand(actionName);
		menuItem.addActionListener(listener);
		targetMenu.add(menuItem);
	}
	
	private void initMenu(){
		JMenuBar menubar=new JMenuBar();

		JMenu menuFile = new JMenu("File");
		this.addMenuItem(menuFile,"New","New",this);
		this.addMenuItem(menuFile,"Open...","Open",this);
		this.addMenuItem(menuFile,"Save...","Save",this);
		menubar.add(menuFile);

		JMenu menuPen = new JMenu("Pen");
		this.addMenuItem(menuPen, "Color...", "Color", this);
		JMenu menuOpacityP = new JMenu("Opacity");
		this.addMenuItem(menuOpacityP, "100%", "OpaP100", this);
		this.addMenuItem(menuOpacityP, "75% ", "OpaP75 ", this);
		this.addMenuItem(menuOpacityP, "50% ", "OpaP50 ", this);
		this.addMenuItem(menuOpacityP, "25% ", "OpaP25 ", this);
		menuPen.add(menuOpacityP);
		JMenu menuTypeP = new JMenu("Type");
		this.addMenuItem(menuTypeP, "Normal   ", "typeP1", this);
		this.addMenuItem(menuTypeP, "Marker   ", "typeP2", this);
		this.addMenuItem(menuTypeP, "Smooth   ", "typeP3", this);
		this.addMenuItem(menuTypeP, "Dotted(●)", "typeP4", this);
		this.addMenuItem(menuTypeP, "Dotted(■)", "typeP5", this);
		this.addMenuItem(menuTypeP, "Dashed   ", "typeP6", this);
		menuPen.add(menuTypeP);
		menubar.add(menuPen);

		JMenu menuEraser = new JMenu("Eraser");
		JMenu menuOpacityE = new JMenu("Opacity");
		this.addMenuItem(menuOpacityE, "100%", "OpaE100", this);
		this.addMenuItem(menuOpacityE, "75% ", "OpaE75 ", this);
		this.addMenuItem(menuOpacityE, "50% ", "OpaE50 ", this);
		this.addMenuItem(menuOpacityE, "25% ", "OpaE25 ", this);
		menuEraser.add(menuOpacityE);
		JMenu menuTypeE = new JMenu("Type");
		this.addMenuItem(menuTypeE, "Normal   ", "typeE1", this);
		this.addMenuItem(menuTypeE, "Marker   ", "typeE2", this);
		this.addMenuItem(menuTypeE, "Smooth   ", "typeE3", this);
		this.addMenuItem(menuTypeE, "Dotted(●)", "typeE4", this);
		this.addMenuItem(menuTypeE, "Dotted(■)", "typeE5", this);
		this.addMenuItem(menuTypeE, "Dashed   ", "typeE6", this);
		menuEraser.add(menuTypeE);
		menubar.add(menuEraser);

		JMenu menuStamp = new JMenu("Stamp");
		JMenu menuTypeS = new JMenu("Type");
		this.addMenuItemIcon(menuTypeS, "flower       ", "typeS1", icon1, this);
		this.addMenuItemIcon(menuTypeS, "cherryblossom", "typeS2", icon2, this);
		this.addMenuItemIcon(menuTypeS, "apple        ", "typeS3", icon3, this);
		this.addMenuItemIcon(menuTypeS, "orange       ", "typeS4", icon4, this);
		this.addMenuItemIcon(menuTypeS, "carrot       ", "typeS5", icon5, this);
		this.addMenuItemIcon(menuTypeS, "music        ", "typeS6", icon6, this);
		this.addMenuItemIcon(menuTypeS, "diamond      ", "typeS7", icon7, this);
		this.addMenuItemIcon(menuTypeS, "topaz        ", "typeS8", icon8, this);
		this.addMenuItemIcon(menuTypeS, "ruby         ", "typeS9", icon9, this);
		this.addMenuItemIcon(menuTypeS, "sun          ", "typeS10", icon10, this);
		this.addMenuItemIcon(menuTypeS, "rain         ", "typeS11", icon11, this);
		this.addMenuItemIcon(menuTypeS, "seal         ", "typeS12", icon12, this);
		this.addMenuItemIcon(menuTypeS, "anchor       ", "typeS13", icon13, this);
		this.addMenuItemIcon(menuTypeS, "flag         ", "typeS14", icon14, this);
		this.addMenuItemIcon(menuTypeS, "daruma       ", "typeS15", icon15, this);
		menuStamp.add(menuTypeS);
		JMenu menuSize = new JMenu("Size");
		this.addMenuItem(menuSize,"small","small",this);
		this.addMenuItem(menuSize,"medium","medium",this);
		this.addMenuItem(menuSize,"big","big",this);
		menuStamp.add(menuSize);
		menubar.add(menuStamp);

		JMenu menuShape = new JMenu("Shape");
		JMenu menuTypeH = new JMenu("Type");
		this.addMenuItem(menuTypeH, "circle      ", "typeH1", this);
		this.addMenuItem(menuTypeH, "circle(fill)", "typeH2", this);
		this.addMenuItem(menuTypeH, "square      ", "typeH3", this);
		this.addMenuItem(menuTypeH, "square(fill)", "typeH4", this);
		menuShape.add(menuTypeH);
		menubar.add(menuShape);

		JMenu menuRuler = new JMenu("Ruler");
		JMenu menuTypeR = new JMenu("Type");
		this.addMenuItem(menuTypeR, "Normal   ", "typeR1", this);
		this.addMenuItem(menuTypeR, "Marker   ", "typeR2", this);
		this.addMenuItem(menuTypeR, "Smooth   ", "typeR3", this);
		this.addMenuItem(menuTypeR, "Dotted(●)", "typeR4", this);
		this.addMenuItem(menuTypeR, "Dotted(■)", "typeR5", this);
		this.addMenuItem(menuTypeR, "Dashed   ", "typeR6", this);		
		menuRuler.add(menuTypeR);
		menubar.add(menuRuler);

		JMenu menuFill = new JMenu("Fill");
		this.addMenuItem(menuFill,"Color...","Fill",this);
		menubar.add(menuFill);

		JMenuItem menuClear = new JMenuItem("Clear");
		menuClear.setActionCommand("Clear");
		menuClear.addActionListener(this);
		menubar.add(menuClear);

		this.setJMenuBar(menubar);
	}


	private void init(){
		this.setTitle("Simple Draw");
        this.setSize(1049, 690);
        canvas = new DrawPanel();
		palette1 = new PalettePanel1();
		palette2 = new PalettePanel2();
		Container container = this.getContentPane();
		container.add(palette1,BorderLayout.LINE_START);
		container.add(palette2,BorderLayout.LINE_END);
		container.add(canvas);
		canvas.setBackground(Color.white);
		//canvas.setPreferredSize(new Dimension(550, 550));
		fileChooser = new JFileChooser();
		this.initMenu();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.createBuffer(canvas.getWidth(),canvas.getHeight());
	}


	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("New")){
			int option = JOptionPane.showConfirmDialog
			(this, "保存してから新規ファイルを開きますか？","新規作成", JOptionPane.YES_NO_CANCEL_OPTION);
			if(option == JOptionPane.YES_OPTION){
				int returnVal = fileChooser.showSaveDialog(this);
	        	if (returnVal == JFileChooser.APPROVE_OPTION){
	        	canvas.saveFile(fileChooser.getSelectedFile());
	        	}
				canvas.createBuffer(canvas.getWidth(),canvas.getHeight());
				repaint();
			}
			else if(option == JOptionPane.NO_OPTION){
				canvas.createBuffer(canvas.getWidth(),canvas.getHeight());
				repaint();
			}
		}

		else if(e.getActionCommand().equals("Open")){
			int returnVal = fileChooser.showOpenDialog(this);
	        if (returnVal == JFileChooser.APPROVE_OPTION){
	        	canvas.openFile(fileChooser.getSelectedFile());
	        }
		}

		else if(e.getActionCommand().equals("Save")){
			int returnVal = fileChooser.showSaveDialog(this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	canvas.saveFile(fileChooser.getSelectedFile());
	        }
		}
		
		else if(e.getActionCommand().equals("Color")){
			colorchooser = new JColorChooser();
			color = colorchooser.showDialog(this,"choose a color",Color.blue);
			canvas.setPenColor(color);
		}

		else if(e.getActionCommand().equals("OpaP100"))	canvas.setPenOpacity(100);
		else if(e.getActionCommand().equals("OpaP75"))  canvas.setPenOpacity(75);
		else if(e.getActionCommand().equals("OpaP50"))  canvas.setPenOpacity(50);
		else if(e.getActionCommand().equals("OpaP25"))  canvas.setPenOpacity(25);
			

		else if(e.getActionCommand().equals("OpaE100")) canvas.setEraserOpacity(100);
		else if(e.getActionCommand().equals("OpaE75"))  canvas.setEraserOpacity(75);
		else if(e.getActionCommand().equals("OpaE50"))  canvas.setEraserOpacity(50);	
		else if(e.getActionCommand().equals("OpaE25"))  canvas.setEraserOpacity(25);
			
		// ペン 種類
		else if(e.getActionCommand().equals("typeP1")){
			canvas.setPen();
			DrawPanel.linetype = 0;
		}
		else if(e.getActionCommand().equals("typeP2")){
			canvas.setPen();
			DrawPanel.linetype = 1;
		}
		else if(e.getActionCommand().equals("typeP3")){
			canvas.setPen();
			DrawPanel.linetype = 2;
		}
		else if(e.getActionCommand().equals("typeP4")){
			canvas.setPen();
			DrawPanel.linetype = 3;
		}
		else if(e.getActionCommand().equals("typeP5")){
			canvas.setPen();
			DrawPanel.linetype = 4;
		}
		else if(e.getActionCommand().equals("typeP6")){
			canvas.setPen();
			DrawPanel.linetype = 5;
		}

		// 消しゴム 種類
		else if(e.getActionCommand().equals("typeE1")){
			canvas.setEraser();
			DrawPanel.linetype = 0;
		}
		else if(e.getActionCommand().equals("typeE2")){
			canvas.setEraser();
			DrawPanel.linetype = 1;
		}
		else if(e.getActionCommand().equals("typeE3")){
			canvas.setEraser();
			DrawPanel.linetype = 2;
		}
		else if(e.getActionCommand().equals("typeE4")){
			canvas.setEraser();
			DrawPanel.linetype = 3;
		}
		else if(e.getActionCommand().equals("typeE5")){
			canvas.setEraser();
			DrawPanel.linetype = 4;
		}
		else if(e.getActionCommand().equals("typeE6")){
			canvas.setEraser();
			DrawPanel.linetype = 5;
		} 

		// スタンプ 種類
		else if(e.getActionCommand().equals("typeS1")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 0;
		} 
		else if(e.getActionCommand().equals("typeS2")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 1;
		} 
		else if(e.getActionCommand().equals("typeS3")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 2;
		}
		else if(e.getActionCommand().equals("typeS4")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 3;
		} 
		else if(e.getActionCommand().equals("typeS5")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 4;
		} 
		else if(e.getActionCommand().equals("typeS6")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 5;
		} 
		else if(e.getActionCommand().equals("typeS7")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 6;
		} 
		else if(e.getActionCommand().equals("typeS8")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 7;
		} 
		else if(e.getActionCommand().equals("typeS9")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 8;
		}
		else if(e.getActionCommand().equals("typeS10")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 9;
		} 
		else if(e.getActionCommand().equals("typeS11")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 10;
		} 
		else if(e.getActionCommand().equals("typeS12")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 11;
		}
		else if(e.getActionCommand().equals("typeS13")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 12;
		} 
		else if(e.getActionCommand().equals("typeS14")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 13;
		} 
		else if(e.getActionCommand().equals("typeS15")){
			DrawPanel.flag = 2;
			DrawPanel.stamptype = 14;
		} 

		// スタンプ サイズ
		else if(e.getActionCommand().equals("small")){
			DrawPanel.flag = 2;
			DrawPanel.stampsize = 0;
		}
		else if(e.getActionCommand().equals("medium")){
			DrawPanel.flag = 2;
			DrawPanel.stampsize = 1;
		}
		else if(e.getActionCommand().equals("big")){
			DrawPanel.flag = 2;
			DrawPanel.stampsize = 2;
		}

		// 図形 種類
		else if(e.getActionCommand().equals("typeH1")){ 
			DrawPanel.tool = 2;
			DrawPanel.shapetype = 0;
		} 
		else if(e.getActionCommand().equals("typeH2")){
			DrawPanel.tool = 2;
			DrawPanel.shapetype = 1;
		} 
		else if(e.getActionCommand().equals("typeH3")){
			DrawPanel.tool = 2;
			DrawPanel.shapetype = 2;
		} 
		else if(e.getActionCommand().equals("typeH4")){
			DrawPanel.tool = 2;
			DrawPanel.shapetype = 3;
		} 

		// 定規 種類
		else if(e.getActionCommand().equals("typeR1")){
			DrawPanel.tool = 1;
			DrawPanel.linetype = 0;
		} 
		else if(e.getActionCommand().equals("typeR2")){
			DrawPanel.tool = 1;
			DrawPanel.linetype = 1;
		} 
		else if(e.getActionCommand().equals("typeR3")){
			DrawPanel.tool = 1;
			DrawPanel.linetype = 2;
		}
		else if(e.getActionCommand().equals("typeR4")){
			DrawPanel.tool = 1;
			DrawPanel.linetype = 3;
		} 
		else if(e.getActionCommand().equals("typeR5")){
			DrawPanel.tool = 1;
			DrawPanel.linetype = 4;
		} 
		else if(e.getActionCommand().equals("typeR6")){
			DrawPanel.tool = 1;
			DrawPanel.linetype = 5;
		}

		// バケツ
		else if(e.getActionCommand().equals("Fill")){
			colorchooserF = new JColorChooser();
			colorF = colorchooserF.showDialog(this,"choose a color",Color.blue);
			canvas.screenFill(colorF);
		}

		// クリア
		else if(e.getActionCommand().equals("Clear")){
			canvas.screenClear(canvas);
		}
	}
	
	public static void main(String[] args) {
		SimpleDraw frame = new SimpleDraw();
		frame.init();
	}
}