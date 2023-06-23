import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
 
/**
* Write a description of class Checkbox here.
*
 * @author (your name)
 * @version (a version number or a date)
*/

public class Checkbox extends Small_Button
{
    /**
     * Act - do whatever the Checkbox wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private boolean pressed;
    private boolean pressable;
    private int font_size;

    public Checkbox(String b_name, int b_x, int b_y, int b_width, int b_height,String b_page)
    {
        super(b_name, b_x, b_y, b_width, b_height, b_page);
        pressed = false;
        pressable = false;
        if (name == "Remove mass from diagram" || name == "Remove mass from bank")
        {
            font_size = 22;
        }
        else
        {
            font_size = 25;
        }
    }

    public void act()
    {
        checkPressable();
        checkPress();
        drawText();
    }   

    public void checkPress()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse!=null && Greenfoot.mouseClicked(this) && pressable == true)
        {
            doSomething();
        }       
    }
    
    public void checkPressable()
    {
        super.checkPressable();
    }

    public void doSomething()
    {
        if (pressed == false)
        {
            pressed = true;
            if (name == "Set gravity" || name == "Set spring constant" || name == "Create mass" || name == "Remove mass from diagram" || name == "Remove mass from bank")
            {
                MyWorld.latest_true = name;
            }
            if (name == "Blue" || name == "Orange" || name == "Purple" || name == "Green" || name == "Pink")
            {
                MyWorld.current_colour = name;
            }
        }
        else
        {
            pressed = false;
            if (name == "Set gravity" || name == "Set spring constant" || name == "Create mass" || name == "Remove mass from diagram" || name == "Remove mass from bank")
            {
                MyWorld.latest_true = "";
            }
            if (name == "Blue" || name == "Orange" || name == "Purple" || name == "Green" || name == "Pink")
            {
                MyWorld.current_colour = "";
            }
        }
    }

    public void drawText()
    {
        if (pressed == false)
        {
            if (name == "Set gravity" || name == "Set spring constant" || name == "Create mass" || name == "Remove mass from diagram" || name == "Remove mass from bank")
            {
                setImage("button_false.png");
            }
            else
            {
                setImage("checkbox_false.png");
            }
            GreenfootImage textImage = new GreenfootImage(this.name, font_size, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            textImage.setFont(new Font("Arial", false, false, font_size));
            getImage().drawImage(textImage, (int)(125-(this.name.length()*10)/2), 0);
        }
        else
        {
            if (name == "Set gravity" || name == "Set spring constant" || name == "Create mass" || name == "Remove mass from diagram" || name == "Remove mass from bank")
            {
                setImage("button_true.png");
            }
            else
            {
                setImage("checkbox_true.png");
            }
            GreenfootImage textImage = new GreenfootImage(this.name, font_size, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            textImage.setFont(new Font("Arial", false, false, font_size));
            getImage().drawImage(textImage, (int)(125-(this.name.length()*10)/2), 0);
        }
    }
    
    public boolean getPressed()
    {
        return pressed;
    }
    
    public void setTrue()
    {
        pressed = true;
    }
    
    public void setFalse()
    {
        pressed = false;
    }
}

