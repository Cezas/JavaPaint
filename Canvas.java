/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawapp;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author rjy5060
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
    
    private final ArrayList<Point> points=new ArrayList<>();
    private final ArrayList<Shape> shapes=new ArrayList<>();
    private int x, y, thickness, spacing; 
    private String ShapeToDrawName;
    private Shape drawShape;
    private Color toColor1, toColor2, prevColor1, prevColor2;
    private boolean grad, fill, dash;

   private final JLabel coords;
    
    public void setShapeDraw(String shape){
        ShapeToDrawName=shape;
    }
    
    public void setColor1(Color clr){
        prevColor1=toColor1;
        toColor1=clr;
    }
    public void setColor2(Color clr){
        prevColor2=toColor2;
        toColor2=clr;
    }
    public Color getColor1(){        
        return toColor1;
    }
    public Color getColor2(){
        return toColor2;
    }
     public Color getPrevColor1(){        
        return prevColor1;
    }
    public Color getPrevColor2(){
        return prevColor2;
    }
    
    
    public void setThicc(int val){
        thickness=val;
    }
    public void setSpace(int val){
        spacing=val;
    }
    public void setGrad(boolean val){
        grad=val;
    }
    public void setFill(boolean val){
        fill=val;
    }
    public void setDash(boolean val){
        dash=val;
    }
    
    public void popBack(){
        if(!shapes.isEmpty()){
            shapes.remove(shapes.size()-1);
            repaint();
        }
    }
    public void clearShapes(){
        shapes.clear();
        repaint();
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;
        for (int i=0; i<shapes.size(); i++)
             shapes.get(i).draw(g);     
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        /*if(ShapeToDraw.equals("Line"))
            shapes.add(new Line());
        repaint();
        System.out.println(ShapeToDraw);*/
    }
    
    @Override
    public void mousePressed(MouseEvent e){
       x=e.getX();
       y=e.getY();
       coords.setText(String.format("Clicked at [%d], [%d]", e.getX(),e.getY()));
        
        
       if(ShapeToDrawName.equals("Line"))
            drawShape=new Line(x,y,e.getX(),e.getY(), toColor1, toColor2, grad, dash, thickness, spacing);//Line s1=new Line();
       
       
       shapes.add(drawShape);
       
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
       coords.setText(String.format("Drawing "+ShapeToDrawName+" from [%d], [%d] to [%d], [%d]", x, y, e.getX(),e.getY()));
       shapes.get(shapes.size()-1).setCoord2(e.getX(), e.getY());
       repaint();    

    }
    
    @Override
    public void mouseReleased(MouseEvent e){
     coords.setText(String.format("Drew shape "+ShapeToDrawName
             + " from [%d], [%d] to [%d], [%d]", x, y, e.getX(),e.getY()));    
     //shapes.add(drawShape);
     shapes.get(shapes.size()-1).setCoord2(e.getX(), e.getY());
     repaint();
    }
    
    
    public void mouseMoved(MouseEvent e){
        coords.setText(String.format("(%d, %d)", e.getX(),e.getY()));
    }
    public void mouseEntered(MouseEvent e){
        
    }
    public void mouseExited(MouseEvent e){}
    
    public Canvas(){
        //canvas=new JPanel();
        coords=new JLabel();
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        coords.setMaximumSize(coords.getPreferredSize());
        //add(canvas, BorderLayout.CENTER);
        add(coords, BorderLayout.SOUTH);
        
           
    }
    
    
}
