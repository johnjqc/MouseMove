package org.jsoft.mousemove;

import java.awt.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;


class SimulateMouseAction implements Runnable   {
	private Robot iRobot;

    @SuppressWarnings("unused")
    private SimulateMouseAction () throws AWTException {  
    	iRobot = new Robot(); 
    }

    public void run()   {
        System.out.println(new SimpleDateFormat("d/M/yy hh:mm").format(Calendar.getInstance().getTime()) +  
                "Mouse start");
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b  = a.getLocation();
        int x1 = (int)b.getX();
        int y1 = (int)b.getY();
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;
        x = (int) Math.round((Math.random()*scrSize.getWidth()));
        y = (int) Math.round((Math.random()*scrSize.getHeight())); 
        //moveMouse(x,y);
        iRobot.mouseMove(x, y);
        
        iRobot.mouseMove(x1, y1);
        //iRobot.mouseMove((int) scrSize.getWidth()/2, (int) scrSize.getHeight()/2);
        System.out.println(new SimpleDateFormat("d/M/yy hh:mm").format(Calendar.getInstance().getTime()) + 
                "Mouse end");
    }   
    
    public static void main(String[] args) throws Exception {
    	SimulateMouseAction ma = new SimulateMouseAction();
    	long start = System.currentTimeMillis();
    	PointerInfo a = MouseInfo.getPointerInfo();
        Point b  = a.getLocation();
        int x = (int)b.getX();
        int y = (int)b.getY();
        int x1 = x;
        int y1 = y;
        while(true) {
        	long actual = System.currentTimeMillis();
        	a = MouseInfo.getPointerInfo();
        	b  = a.getLocation();
    		x = (int)b.getX();
            y = (int)b.getY();
            if (x != x1) {
            	x1 = x;
            	start = actual;
            }
            if (y != y1) {
            	y1 = y;
            	start = actual;
            }
        	if ((actual - start) > 55000 ) {
        		if (x == x1 && y == y1) {
        			ma.run();
        		}
        		a = MouseInfo.getPointerInfo();
        		b  = a.getLocation();
        		x1 = (int)b.getX();
                y1 = (int)b.getY();
        		start = System.currentTimeMillis();
        	}
        }
    }


    /**
     *  Moves mouse cursor to given coordinates.
     *
     *  @param  x   X coordinate.
     *  @param  y   Y coordinate.
     */
    private void moveMouse(int x, int y)    {
        iRobot.mouseMove(x, y);
        try {
            Thread.sleep(10);
        } catch(InterruptedException ie)    {
                ie.printStackTrace();
        }
    }
}