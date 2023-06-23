import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spring here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spring extends Actor
{
    /**
     * Act - do whatever the Spring wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private double stretch_y;
    private double stretch_x;
    private double target_y;
    
    public Spring()
    {
        stretch_y = 0.5;
        stretch_x = 0.5;
        target_y = (MyWorld.extension+2)/4;
    }
    
    public void act()
    {
        updateStretch();
    }
    
    public void updateStretch()
    {
        int y = getY();
        int x = getX();
        
        target_y = (MyWorld.extension+2)/4;
        if (stretch_y < target_y-0.01)
        {
            stretch_y += 0.01;
            MyWorld.current_extension = stretch_y*4-2;
        }
        else if (stretch_y > target_y+0.01)
        {
            stretch_y -= 0.01;
            MyWorld.current_extension = stretch_y*4-2;
        }
        y =(int)((stretch_y)*100);
        
        setImage("spring2.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*stretch_x), (int)(image.getHeight()*stretch_y));
        setImage(image);
        setLocation(x,y);
        // GreenfootImage holder = new GreenfootImage("spring_holder.png");
        // getImage().drawImage(holder,0,0);
    }
    
    public double getStretch()
    {
        return stretch_y;
    }
}
