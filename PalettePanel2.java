import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;


public class PalettePanel2 extends JPanel implements ActionListener{
    ImageIcon stampIcon = new ImageIcon("./image/stamp.jpg");
    ImageIcon shapeIcon = new ImageIcon("./image/shape1.jpg");
    ImageIcon rulerIcon = new ImageIcon("./image/ruler1.jpg");
    ImageIcon fillIcon  = new ImageIcon("./image/fill7.png");
    ImageIcon clearIcon = new ImageIcon("./image/clear2.jpg");
    JButton stampButton = new JButton("    stamp    ", stampIcon);
    JButton shapeButton = new JButton("    shape    ", shapeIcon);
    JButton rulerButton = new JButton("     ruler     ", rulerIcon);
    JButton fillButton  = new JButton("       fill        ", fillIcon);
    JButton clearButton = new JButton("     clear     ", clearIcon);

    PalettePanel2(){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        stampButton.setPreferredSize(new Dimension(180, 100));
        add(stampButton);
        stampButton.addActionListener(this);
        stampButton.setActionCommand("typeS");

        add(shapeButton); 
        shapeButton.addActionListener(this);
        shapeButton.setActionCommand("typeH");

        add(rulerButton);
        rulerButton.addActionListener(this);
        rulerButton.setActionCommand("typeR");

        add(fillButton);
        fillButton.addActionListener(this);
        fillButton.setActionCommand("Fill");

        add(clearButton);
        clearButton.addActionListener(this);
        clearButton.setActionCommand("Clear");
    }


	@Override
	public void actionPerformed(ActionEvent e){	
        if(e.getActionCommand().equals("typeS")){
            DrawPanel.flag = 2;
		}
        else if(e.getActionCommand().equals("typeH")){
            DrawPanel.tool = 2;
		}
        else if(e.getActionCommand().equals("typeR")){
            DrawPanel.tool = 1;
		}
        else if(e.getActionCommand().equals("Fill")){
            SimpleDraw.colorchooserF = new JColorChooser();
			SimpleDraw.colorF = SimpleDraw.colorchooserF.showDialog(this,"choose a color",Color.blue);
			SimpleDraw.canvas.screenFill(SimpleDraw.colorF);
		}
        else if(e.getActionCommand().equals("Clear")){
			SimpleDraw.canvas.screenClear(SimpleDraw.canvas);
		}
    }
}
	