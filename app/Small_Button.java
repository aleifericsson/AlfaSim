import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Small_Button extends Actor
{
    protected String name;
    protected int height;
    protected int width;
    protected int real_x;
    protected int real_y;
    protected String page;
    protected boolean pressable;

    public Small_Button(String b_name, int b_x, int b_y, int b_width, int b_height,String b_page)
    {
        name = b_name;
        height = b_height;
        width = b_width;
        real_x = b_x;
        real_y = b_y;
        page = b_page;
        pressable = false;
    }
    
    public void act()
    {
        checkPressable();
        checkPress();
        drawText();
    }
    
    public void checkPressable()
    {
        if (page == "main")
        {
            pressable = true;
        }
        else
        {
            pressable = false;
        }
    }
    
    public void checkPress()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse!=null && Greenfoot.mouseClicked(this) && pressable == true)
        {
            doSomething();
        }
        else if (mouse!=null && Greenfoot.mouseClicked(null) && pressable == true)
        {
            if (mouse.getX() >= real_x && mouse.getY() >= real_y && mouse.getX() <= real_x+width && mouse.getY() <= real_y+height)
            {
                doSomething();
            }
        }
    }
    
    public void doSomething()
    {
        try
        {
            int num = Integer.parseInt(name);
            if (MyWorld.mass_bank.pointer > num)
            {
                MyWorld.mass_stack.push(new Mass(MyWorld.mass_bank.get(num)));
                MyWorld.mass_stack.get(MyWorld.mass_stack.getPointer()-1).mount();
            }
        } catch(Exception f) 
        {
            if (name == "Done")
            {
                if (MyWorld.latest_true == "Set gravity")
                {
                    try 
                    {
                        double temp = Double.valueOf(MyWorld.value_text);
                        temp = temp * 100;
                        temp = Math.round(temp);
                        temp = temp/100;
                        if (temp <= 12 && temp >= 5)
                        {
                            MyWorld.gravity = temp;
                            MyWorld.latest_true = "";
                        }
                    } catch(Exception e) {}
                }
                else if (MyWorld.latest_true == "Set spring constant")
                {
                    try 
                    {
                        int temp = Integer.valueOf(MyWorld.value_text);
                        if (temp <= 200 && temp >= 50)
                        {
                            MyWorld.spring_constant = (int)temp;
                            MyWorld.latest_true = "";
                        }
                    } catch(Exception e) {}
                }
                else if (MyWorld.latest_true == "Create mass")
                {
                    try 
                    {
                        double temp = Double.valueOf(MyWorld.value_text);
                        if (temp <= 250 && temp >= 0)
                        {
                            MyWorld.latest_true = "";
                            MyWorld.mass_bank.enqueue(new Mass(temp,MyWorld.current_colour.toLowerCase(),false,MyWorld.mass_bank.getPointer(),MyWorld.name_text));
                        }
                    } catch(Exception e) {}
                }
            }
            else if (name=="Randomise")
            {
                if (MyWorld.latest_true == "Set gravity")
                {
                    Random r = new Random();
                    double temp = 5+r.nextInt(6);
                    double temp2 = r.nextDouble();
                    temp2 = temp2*100;
                    temp2 = Math.round(temp2);
                    temp = temp+(temp2/100);
                    String t = String.valueOf(temp);
                    // int num = t.indexOf(".");
                    // t = t.substring(0,num+3);
                    MyWorld.value_text = t + "RANDOM";
                }
                else if (MyWorld.latest_true == "Set spring constant")
                {
                    Random r = new Random();
                    int temp = 50+r.nextInt(150);
                    String t = String.valueOf(temp);
                    // int num = t.indexOf(".");
                    // t = t.substring(0,num+2);
                    MyWorld.value_text = t + "RANDOM";
                }
                else if (MyWorld.latest_true == "Create mass")
                {
                    Random r = new Random();
                    int temp = r.nextInt(250);
                    String t = String.valueOf(temp);
                    MyWorld.value_text = t + "RANDOM";
                    int temp2 = r.nextInt(5);
                    switch (temp2)
                    {
                        case 0:
                            MyWorld.current_colour = "Orange";
                            break;
                        case 1:
                            MyWorld.current_colour = "Pink";
                            break;
                        case 2:
                            MyWorld.current_colour = "Purple";
                            break;
                        case 3:
                            MyWorld.current_colour = "Blue";
                            break;
                        default:
                            MyWorld.current_colour = "Green";
                            break;
                    }
                }
            }
            else if (name == "Yes")
            {
                if (MyWorld.latest_true == "Remove mass from bank")
                {
                    Mass temp = MyWorld.mass_bank.dequeue();
                }
                if (MyWorld.latest_true == "Remove mass from diagram")
                {
                    Mass temp = MyWorld.mass_stack.pop();
                }
                MyWorld.latest_true = "";
            }
            else if (name == "No")
            {
                MyWorld.latest_true = "";
            }
            else if (name == "Save settings")
            {
                MyWorld.instruction = "SAVE";
            }
            else if (name == "Load settings")
            {
                MyWorld.instruction = "LOAD";
            }
        }
    }
    
    public int getX()
    {
        return real_x+(width/2);
    }
    
    public int getY()
    {
        return real_y+(height/2);
    }
    
    public void drawText() 
    {
        try
        {
            int temp = Integer.parseInt(name);
            setImage("pixel.png");
        } catch(Exception e) 
        {
            if (name == "Save settings" || name == "Load settings")
            {
                setImage("small_button.png");
                GreenfootImage textImage = new GreenfootImage(this.name, 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
                textImage.setFont(new Font("Arial", false, false, 25));
                getImage().drawImage(textImage, 110-((int)(this.name.length()*0.75*10)/2), 0);
            } else
            {
                setImage("smaller_button.png");
                GreenfootImage textImage = new GreenfootImage(this.name, 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
                textImage.setFont(new Font("Arial", false, false, 25));
                getImage().drawImage(textImage, 40-((int)(this.name.length()*0.75*10)/2), 0);
            }
            
        }
    }
  
    public void updateLocation()
    {
        
    }
}
