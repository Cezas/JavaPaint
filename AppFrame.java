/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java2ddrawapp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.BoxLayout;

//import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Robert
 */
public class AppFrame extends JFrame implements ActionListener{
//declare components
    private final JButton undo, clear, cbut1, cbut2;
    private final JColorChooser palette;
    private final JComboBox<String> shapeSel;
    private final JCheckBox fill, gradient, dash;
    private final JTextField strokeWidth, strokeSpace;
    private final JPanel menu1, menu2;
    private final Canvas canvas;
    
    //private final JLabel coords;
    
    String[] shapes= {"Line", "Oval", "Rectangle"};//Shape[] shapes;
    
    public AppFrame(){
    //initialize components        
        super("Java2D Drawing");
        
        
        setLayout(new BoxLayout(getContentPane(), 3));//setLayout(new FlowLayout());

        
        undo=new JButton("Undo");
        clear=new JButton("Clear");
        cbut1=new JButton("1st Color");
        cbut2=new JButton("2nd Color");
        
        undo.addActionListener(this);
        clear.addActionListener(this);
        cbut1.addActionListener(this);
        cbut2.addActionListener(this);
        
        palette=new JColorChooser();
        
        shapeSel=new JComboBox(shapes);
        shapeSel.addItemListener(
        new ItemListener(){
         @Override
         public void itemStateChanged(ItemEvent event){
             if(event.getStateChange()==ItemEvent.SELECTED)
                canvas.setShapeDraw((String) shapeSel.getSelectedItem());
         }
        });
        
        fill=new JCheckBox("Filled");
        gradient=new JCheckBox("Use Gradient");
        dash=new JCheckBox("Dashed");
        
        fill.addActionListener(this);
        gradient.addActionListener(this);
        dash.addActionListener(this);
                
        
        strokeWidth=new JTextField();
        strokeSpace=new JTextField();
        
        strokeWidth.setColumns(2);
        strokeSpace.setColumns(2);
        
        strokeWidth.addActionListener(this);
        strokeSpace.addActionListener(this);
        
        menu1=new JPanel();
        menu2=new JPanel();
        
        canvas=new Canvas();
        //coords=new JLabel();
        
        //add components
        menu1.add(undo);
        menu1.add(clear);
        menu1.add(new JLabel("Shapes: "));
        menu1.add(shapeSel);
        menu1.add(fill);
        
        menu2.add(gradient);
        menu2.add(cbut1);
        menu2.add(cbut2);
        menu2.add(new JLabel("Line Width:"));
        menu2.add(strokeWidth);
        menu2.add(new JLabel("Dash Length:"));
        menu2.add(strokeSpace);
        menu2.add(dash);
        
        
        
        menu1.setMaximumSize(menu1.getPreferredSize());
        menu2.setMaximumSize(menu2.getPreferredSize());
        
        canvas.setShapeDraw(shapes[0]);
        
        add(menu1);
        add(menu2);
        add(canvas);
        //add(coords);
        //prepare for launch
        pack();
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @Override 
    public void actionPerformed(ActionEvent event){
    //buttons:NOTE Figure out to check if generic jbutton is pressed
        
        //undo and clear
        if(event.getSource()==undo || event.getSource()==clear){
            if(event.getSource()==undo)
                canvas.popBack();
            
            if(event.getSource()==clear)
                canvas.clearShapes();
        }
    
    
        //colorbuttons
        if(event.getSource()==cbut1 || event.getSource()==cbut2){
            Color inputClr=null;
            
            if(event.getSource()==cbut1){               
                canvas.setColor1(palette.showDialog(AppFrame.this, "Choose a color", inputClr));          
                if(canvas.getColor1()==null)
                    canvas.setColor1(canvas.getPrevColor1());
            }
            
            if(event.getSource()==cbut2){      
                canvas.setColor2(palette.showDialog(AppFrame.this, "Choose a color", inputClr));
                if(canvas.getColor2()==null)
                    canvas.setColor2(canvas.getPrevColor2());
            }
        }
        
    //textfield
        
        //thickness and spacing
        if(event.getSource()==strokeWidth || event.getSource()==strokeSpace){
            if(event.getSource()==strokeWidth){
                try{
                    canvas.setThicc(Integer.parseInt(strokeWidth.getText()));
                }
                catch(NumberFormatException e){
                    System.out.println("Setting size to 1 (no value inputted)");
                    strokeWidth.setText("1");
                }
            }
            
            if(event.getSource()==strokeSpace){
                try{
                    canvas.setSpace(Integer.parseInt(strokeSpace.getText()));
                }
                catch(NumberFormatException e){
                    System.out.println("Setting spacing to 1 (no value inputted)");
                    strokeSpace.setText("1");
                }
            }          
        }
        
    //checkbox
        if(event.getSource()==dash || event.getSource()==gradient || event.getSource()==fill){
            if(event.getSource()==dash)
                canvas.setDash(dash.getModel().isSelected());
            
            if(event.getSource()==gradient)
                canvas.setGrad(gradient.getModel().isSelected());
            
            if(event.getSource()==fill)
                canvas.setFill(fill.getModel().isSelected());
        }
    }
    
    
}
