/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java2ddrawapp;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.abs;

/**
 *
 * @author Robert
 */
public abstract class Shape{
    protected int length;
    protected int height;
    private Color clr1, clr2;
    private int x1, y1, x2, y2, thicc, space;
    private boolean gradient, dashed;
    
    public abstract void draw(Graphics g);
    

    
    public void setCoord1(int val1, int val2){
       x1=val1;
       y1=val2;
    }
    
    public void setCoord2(int val1, int val2){
       x2=val1;
       y2=val2;
    }
      
    public int getX1(){
        return x1;
    }
    public int getY1(){
        return y1;
    }
    public int getX2(){
        return x2;
    }
    public int getY2(){
        return y2;
    }
    
    
    /*public void setClr1(Color clr){
        clr1=clr;
    }
    public void setClr2(Color clr){
        clr2=clr;
    }*/
    public Color getClr1(){
        return clr1;
    }
    public Color getClr2(){
        return clr2;
    }
    
    
    /*public void setGrad(boolean val){
        gradient=val;
    }
    public void setFill(boolean val){
        filled=val;
    }
    public void setDash(boolean val){
        dashed=val;
    }*/
    
    
    public boolean chkGrad(){
        return gradient;
    }
    public boolean chkDash(){
        return dashed;
    }
    
    public int getThicc(){
        return thicc;
    }
    public int getSpace(){
        return space;
    }
    
    
    
    public Shape(){
        setCoord1(0,0);
        setCoord2(0,0);
        clr1=Color.BLACK;
    }
    
    public Shape(int x1, int y1, int x2, int y2, 
            Color color1, Color color2,
            boolean grad, boolean dash,
            int thick, int spacing)
    {
        setCoord1(x1,y1);
        setCoord2(x2,y2);
        clr1=color1;
        clr2=color2;
        gradient=grad;
        dashed=dash;
        thicc=thick;
        space=spacing;

    }
    
    
}
