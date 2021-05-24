import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PalettePanel1 extends JPanel implements ActionListener, ChangeListener{
    ImageIcon penIcon    = new ImageIcon("./image/pen.jpg");
    ImageIcon eraserIcon = new ImageIcon("./image/eraser.jpg");
    ImageIcon colorIcon  = new ImageIcon("./image/color7.jpg");
    JButton penButton    = new JButton("      pen       ", penIcon);
    JButton eraserButton = new JButton("    eraser    ", eraserIcon);
    JButton colorButton  = new JButton("     color     ", colorIcon);   
    JSlider widthSlider    = new JSlider(0,30,3);
    JSlider opacitySliderP = new JSlider(0,100,100);
    JSlider opacitySliderE = new JSlider(0,100,100);
    int nowWidth = 3, nowOpaP = 100, nowOpaE = 100;
    JLabel widthLabel    = new JLabel("Width : " + nowWidth);
    JLabel opacityLabelP = new JLabel("Opacity [ Pen ] : " + nowOpaP);
    JLabel opacityLabelE = new JLabel("Opacity [ Eraser ] : " + nowOpaE);
    JLabel spaceLabel1 = new JLabel("\n");
    JLabel spaceLabel2 = new JLabel("\n");
    JLabel spaceLabel3 = new JLabel("\n");

    PalettePanel1(){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        add(penButton);
        penButton.addActionListener(this);
        penButton.setActionCommand("typeP");

        add(eraserButton); 
        eraserButton.addActionListener(this);
        eraserButton.setActionCommand("typeE");

        add(colorButton);
        colorButton.addActionListener(this);
        colorButton.setActionCommand("Chooser");

        add(spaceLabel1);
        add(widthLabel);
        widthSlider.setPreferredSize(new Dimension(100, 50));
        add(widthSlider);
        widthSlider.setPaintTicks(true);
        widthSlider.setMinorTickSpacing(5);
        widthSlider.setMajorTickSpacing(10);
        widthSlider.setPaintLabels(true);
        widthSlider.addChangeListener(this);
        add(spaceLabel2);

        add(opacityLabelP);
        opacitySliderP.setPreferredSize(new Dimension(100, 50));
        add(opacitySliderP);
        opacitySliderP.setPaintTicks(true);
        opacitySliderP.setMinorTickSpacing(5);
        opacitySliderP.setMajorTickSpacing(25);
        opacitySliderP.setPaintLabels(true);
        opacitySliderP.addChangeListener(this);
        add(spaceLabel3);

        add(opacityLabelE);
        opacitySliderE.setPreferredSize(new Dimension(100, 50));
        add(opacitySliderE);
        opacitySliderE.setPaintTicks(true);
        opacitySliderE.setMinorTickSpacing(5);
        opacitySliderE.setMajorTickSpacing(25);
        opacitySliderE.setPaintLabels(true);
        opacitySliderE.addChangeListener(this);
    }


	@Override
	public void actionPerformed(ActionEvent e) {	
        if(e.getActionCommand().equals("typeP")) {
			SimpleDraw.canvas.setPen();
		}
        else if(e.getActionCommand().equals("typeE")) {
			SimpleDraw.canvas.setEraser();
		}
        else if(e.getActionCommand().equals("Chooser")) {
			SimpleDraw.colorchooser = new JColorChooser();
			SimpleDraw.color = SimpleDraw.colorchooser.showDialog(this,"choose a color",Color.blue);
			SimpleDraw.canvas.setPenColor(SimpleDraw.color);
		}
    }

    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == widthSlider){
            nowWidth = widthSlider.getValue();
            SimpleDraw.canvas.setPenWidth(nowWidth);
            widthLabel.setText("Width : " + String.valueOf(nowWidth));
        }
        else if(e.getSource() == opacitySliderP){
            nowOpaP = opacitySliderP.getValue();
            SimpleDraw.canvas.setPenOpacity(nowOpaP);
            opacityLabelP.setText("Opacity [ Pen ] : " + String.valueOf(nowOpaP));
        }
        else if(e.getSource() == opacitySliderE){
            nowOpaE = opacitySliderE.getValue();
            SimpleDraw.canvas.setEraserOpacity(nowOpaE);
            opacityLabelE.setText("Opacity [ Eraser ] : " + String.valueOf(nowOpaE));
        }
    }
}