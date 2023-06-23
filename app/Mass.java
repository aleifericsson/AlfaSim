import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
 
/**
* Write a description of class Mass here.
*
 * @author (your name)
 * @version (a version number or a date)
*/
public class Mass extends Actor
{
    private double mass;
    private String colour;
    private boolean on_spring;
    private int location;
    private String name;
   
    public Mass(double m_mass, String m_colour, Boolean m_on_spring, int m_location, String m_name)
    {
        colour = m_colour;
        mass = m_mass;
        on_spring = m_on_spring;
        location = m_location;
        if (colour == "")
        {
            setImage("mass_green.png");
        }
        else
        {
            setImage("mass_"+colour+".png");
        }
        name = m_name;
    }
    
    public Mass(Mass another)
    {
        colour = another.colour;
        mass = another.mass;
        on_spring = another.on_spring;
        location = another.location;
        if (colour == "")
        {
            setImage("mass_green.png");
        }
        else
        {
            setImage("mass_"+colour+".png");
        }
        name = another.name;
    }
    /**
     * Act - do whatever the Mass wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
   
    public void act()
    {
        drawValue();
        updateLocation();
    }   
    
    public void drawValue()
    {
        GreenfootImage textImage;
        if (!MyWorld.colours_off)
        {
            if (colour == "")
            {
                setImage("mass_green.png");
            }
            else
            {
                setImage("mass_"+colour+".png");
            }
        }
        else
        {
            setImage("mass_grey.png");
        }
        if (MyWorld.mass_values_off == true)
        {
            textImage = new GreenfootImage("?g", 25, new Color(255, 255, 255), new Color(0, 0, 0, 0));
        }
        else
        {
            String m = String.valueOf(mass);
            // int num = m.indexOf(".");
            // m = m.substring(0,num+3);
            textImage = new GreenfootImage(m+"g", 25, new Color(255, 255, 255), new Color(0, 0, 0, 0));
        }
        textImage.setFont(new Font("Arial", false, false, 25));
        getImage().drawImage(textImage, 50, 0);
    }
   
    public void setLocation(boolean m_on_spring, int m_location)
    {
        on_spring = m_on_spring;
        location = m_location;
    }
    
    public void updateLocation()
    {
        if (on_spring == true)
        {
            switch (location)
            {
                case 0:
                    setLocation(500,(int)(100+MyWorld.current_extension*50+134));
                    break;
                case 1:
                    setLocation(500,(int)(100+MyWorld.current_extension*50+104));
                    break;
                case 2:
                    setLocation(500,(int)(100+MyWorld.current_extension*50+74));
                    break;
                case 3:
                    setLocation(500,(int)(100+MyWorld.current_extension*50+44));
                    break;
                case 4:
                    setLocation(500,(int)(100+MyWorld.current_extension*50+14));
                    break;
                default:
                    setLocation(0,0);
                    break;
            }
        }
        else
        {
            switch (location)
            {
                case 0:
                    setLocation(100+200*location,663);
                    break;
                case 1:
                    setLocation(100+200*location,663);
                    break;
                case 2:
                    setLocation(100+200*location,663);
                    break;
                case 3:
                    setLocation(100+200*location,663);
                    break;
                case 4:
                    setLocation(100+200*location,663);
                    break;
                default:
                    setLocation(0,0);
                    break;
            }
        }
    }
   
    public boolean getOnSpring()
    {
        return on_spring;
    }
   
    public int getLocation()
    {
        return location;
    }
    
    public double getMass()
    {
        return mass;
    }
    
    public String getColour()
    {
        return colour;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void mount()
    {
        on_spring = true;
    }
    
    public void unmount()
    {
        on_spring = false;
    }
}