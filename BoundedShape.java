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
/**
 *
 * @author Robert
 */
public class BoundedShape extends Shape{
    private boolean filled;
    private int upperX, upperY, length, height;
    
    
    public BoundedShape(){
        super();
    }
    
    public BoundedShape(int x1, int y1, int x2, int y2, 
            Color color1, Color color2,
            boolean grad, boolean dash, boolean fill,
            int thick, int spacing)
    {
     super(x1, y1, x2, y2, color1, color2, grad, dash, thick, spacing);
     filled=fill;
    }
    
    @Override
    public void draw(Graphics g){}
    }

