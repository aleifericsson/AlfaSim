import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ruler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ruler extends Actor
{
    /**
     * Act - do whatever the Ruler wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseDragged(this))
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        setLocation(mouse.getX(), mouse.getY());
    }
    if (Greenfoot.mouseDragEnded(this))
    {
        setLocation(getX(), getY());
    }
    }
}
