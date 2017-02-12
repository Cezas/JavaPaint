/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java2ddrawapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
/**
 *
 * @author Robert
 */
public class Line extends Shape{
    public Line(){
        super();
    }
    
    public Line(int x1, int y1, int x2, int y2, 
            Color color1, Color color2,
            boolean grad, boolean dash,
    int thick, int spacing)
    {
        super(x1, y1, x2, y2, color1, color2, grad, dash, thick, spacing);
    }
    
    @Override
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        if(chkDash() && getSpace()!=0){
           float[] dashes={getSpace()};         
                g2d.setStroke(new BasicStroke(getThicc(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND
                ,10, dashes, 0));                 
        }
        else
            g2d.setStroke(new BasicStroke(getThicc(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        if(chkGrad()){
            if((getClr2()!=null && getClr1()!=null)){
                g2d.setPaint(new GradientPaint(getX1(), getY1(), getClr1(), getX2(), getY2(), getClr2(), true));
            }
            else if (getClr1()==null && getClr2()!=null){
                g2d.setPaint(getClr2());
            }
            else if (getClr1()!=null && getClr2()==null){
                g2d.setPaint(getClr1());
            } 
            else
                g2d.setPaint(Color.BLACK);
        }
        else{
            Color temp=getClr1();
            try{
                g2d.setPaint(temp);
            }catch(NullPointerException e){
                temp=getClr2();
            }
        }      
        g2d.drawLine(getX1(),getY1(), getX2(), getY2());
    }
}
