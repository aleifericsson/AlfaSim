
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files


/**
 * Write a description of class myWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    public static double gravity = 9.81;
    public static int spring_constant = 100;
    public static double extension = 0;
    public static double current_extension = 0;
    public static double total_mass = 0;
    public static String latest_true = "";
    public static String current_typing = "";
    public static String current_colour = "";
    public static String value_text = "";
    public static String name_text = "";
    public static boolean colours_off = false;
    public static boolean mass_values_off = false;
    public static Queue mass_bank = new Queue(5);
    public static Stack mass_stack = new Stack(5);
    public static String instruction = "";
    
    public List<Mass> mass_bank2 = new ArrayList<Mass>();
    public List<Mass> mass_stack2 = new ArrayList<Mass>();
    public List<String> data = new ArrayList<String>();
    public Small_Button[] bank_buttons = new Small_Button[5];
    
    public Spring spring = new Spring();
    public Ruler ruler = new Ruler();
    public Checkbox hide_gravity = new Checkbox ("Gravity", 1010,30,250,30,"main");
    public Checkbox hide_spring_constant = new Checkbox ("Spring Constant", 1010,60,250,30,"main");
    public Checkbox hide_extension = new Checkbox ("Extension", 1010,90,250,30,"main");
    public Checkbox hide_mass_values = new Checkbox ("Mass Values", 1010,120,250,30,"main");
    public Checkbox hide_colours = new Checkbox ("Hide Colours", 1010,220,250,30,"main");
    public Checkbox hide_ruler = new Checkbox ("Hide Ruler", 1010,190,250,30,"main");
    public Checkbox set_gravity = new Checkbox ("Set gravity", 1010,320,250,30,"main");
    public Checkbox set_spring_constant = new Checkbox ("Set spring constant", 1010,350,250,30,"main");
    public Checkbox create_mass = new Checkbox ("Create mass", 1010,420,250,30,"main");
    public Checkbox remove_mass_from_diagram = new Checkbox ("Remove mass from diagram", 1010,450,250,30,"main");
    public Checkbox remove_mass_from_bank = new Checkbox ("Remove mass from bank", 1010,480,250,30,"main");
    // public Small_Button back = new Small_Button ("↩ Back", 0,0,120,30,"main");
    public Small_Button randomise = new Small_Button ("Randomise",1300,800,120,30,"main");
    public Small_Button done = new Small_Button ("Done",1300,800,120,30,"main");
    public Small_Button yes = new Small_Button ("Yes",1300,800,120,30,"main");
    public Small_Button no = new Small_Button ("No",1300,800,120,30,"main");
    public Small_Button save = new Small_Button("Save settings", 1010,550,250,30,"main");
    public Small_Button load = new Small_Button("Load settings", 1010,580,250,30,"main");
    public Textbox value = new Textbox("Value", 1300,800,250,30,"main");
    public Textbox name = new Textbox("Name", 1300,800,250,30,"main");
    public Thumbnail thumbnail = new Thumbnail();
    public Checkbox[] colours = new Checkbox[5]; 
    

        
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1300, 700, 1); 
        prepare();
        setPaintOrder(Thumbnail.class,Ruler.class,Mass.class);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    public void prepare()
    {
        createObjects();
    }
    
    public void act()
    {
        updateValues();
        updateBackground();
        drawPopUps();
        updateText();
        updateBank();
        updateStack();
        unpack();
    }
    
    public void createObjects()
    {
        addObject(hide_gravity, hide_gravity.getX(), hide_gravity.getY());
        addObject(hide_spring_constant, hide_spring_constant.getX(), hide_spring_constant.getY());
        addObject(hide_extension, hide_extension.getX(), hide_extension.getY());
        addObject(hide_mass_values, hide_mass_values.getX(), hide_mass_values.getY());
        addObject(hide_colours, hide_colours.getX(), hide_colours.getY());
        addObject(hide_ruler, hide_ruler.getX(), hide_ruler.getY());
        addObject(set_gravity, set_gravity.getX(), set_gravity.getY());
        addObject(set_spring_constant, set_spring_constant.getX(), set_spring_constant.getY());
        addObject(create_mass, create_mass.getX(), create_mass.getY());
        addObject(remove_mass_from_diagram, remove_mass_from_diagram.getX(), remove_mass_from_diagram.getY());
        addObject(remove_mass_from_bank, remove_mass_from_bank.getX(), remove_mass_from_bank.getY());
        // addObject(back, back.getX(), back.getY());
        addObject(randomise, randomise.getX(), randomise.getY());
        addObject(done, done.getX(), done.getY());
        addObject(yes, yes.getX(), yes.getY());
        addObject(no, no.getX(), no.getY());
        addObject(value, value.getX(), value.getY());
        addObject(name, name.getX(), name.getY());
        addObject(save, save.getX(), save.getY());
        addObject(load, load.getX(), load.getY());
        colours[0] = new Checkbox("Orange",1300,800,250,30,"main");
        colours[1] = new Checkbox("Blue",1300,800,250,30,"main");
        colours[2] = new Checkbox("Purple",1300,800,250,30,"main");
        colours[3] = new Checkbox("Green",1300,800,250,30,"main");
        colours[4] = new Checkbox("Pink",1300,800,250,30,"main");
        for (int i = 0; i < 5; i++)
        {
            addObject(colours[i], colours[i].getX(), colours[i].getY());
            bank_buttons[i] = new Small_Button(Integer.toString(i),10+200*i,606,180,90,"main");
            addObject(bank_buttons[i],bank_buttons[i].getX(),bank_buttons[i].getY());
        }
        addObject(spring,500,50);
        addObject(ruler,200,300);
        addObject(thumbnail,650,350);
        hide_ruler.setTrue();
    }
    
    public void updateBackground()
    {
        GreenfootImage textImage = new GreenfootImage("",25,new Color(0, 0, 0),new Color(0, 0, 0, 0));
        textImage.setFont(new Font("Arial", false, false, 25));
        if(hide_ruler.getPressed())
        {
            removeObject(ruler);
        }
        else
        {
            addObject(ruler,200,300);
        }
        textImage = new GreenfootImage("Hide values:", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, 1010, 0);
        textImage = new GreenfootImage("- - - - - - - - - - - - - - - - - - - - - - - - - -", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, 1010, 160);
        getBackground().drawImage(textImage, 1010, 260);
        getBackground().drawImage(textImage, 1010, 390);
        getBackground().drawImage(textImage, 1010, 520);
        textImage = new GreenfootImage("Change values:", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, 1010, 290);
        textImage = new GreenfootImage("© Alif Nafili 2022", 20, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, 1170, 680);
        GreenfootImage hook = new GreenfootImage("hook_overlay.png");
        getBackground().drawImage(hook, 450, (int)(200*spring.getStretch())-10);
        removeObject(thumbnail);
        
    }
    
    public void updateText()
    {
        GreenfootImage textImage = new GreenfootImage("",25,new Color(0, 0, 0),new Color(0, 0, 0, 0));
        if (!hide_gravity.getPressed())
        {
            String g = String.valueOf(gravity);
            // int num = g.indexOf(".");
            // g = g.substring(0,num+3);
            textImage = new GreenfootImage("Gravity: "+g+" m/s²", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        }
        else
        {
            textImage = new GreenfootImage("Gravity: ? m/s²", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        }
        textImage.setFont(new Font("Arial", false, false, 25));
        getBackground().drawImage(textImage, 750, 0);
        if (!hide_spring_constant.getPressed())
        {
            String s = String.valueOf(spring_constant);
            textImage = new GreenfootImage("Spring Constant: "+s+" N/m", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        }
        else
        {
            textImage = new GreenfootImage("Spring Constant: ? N/m", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        }
        getBackground().drawImage(textImage, 750, 30);
        if (!hide_extension.getPressed())
        {
            String e = String.valueOf(extension);
            // int num = e.indexOf(".");
            // e = e.substring(0,num+3);
            textImage = new GreenfootImage("Extension: "+e+" cm", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        }
        else
        {
            textImage = new GreenfootImage("Extension: ? cm", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        }
        getBackground().drawImage(textImage, 750, 60);
        if (!hide_mass_values.getPressed())
        {
            String m = String.valueOf(total_mass);
            // int num = m.indexOf(".");
            // m = m.substring(0,num+3);
            textImage = new GreenfootImage("Total mass: "+m+" kg", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        }
        else
        {
            textImage = new GreenfootImage("Total mass: ? kg", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        }
        getBackground().drawImage(textImage, 750, 90);
    }
    
    public void updateValues()
    {
        setBackground("background.png");
        total_mass = 0;
        for(int i = 0; i < mass_stack.getPointer(); i++)
        {
            total_mass += (mass_stack.get(i).getMass())/1000;
        }
        total_mass = total_mass * 100;
        total_mass = Math.round(total_mass);
        total_mass = total_mass / 100;
        colours_off = hide_colours.getPressed();
        mass_values_off = hide_mass_values.getPressed();
        extension = ((total_mass*gravity)/spring_constant)*10000;
        extension = Math.round(extension);
        extension = extension/100;
        if (extension >= 6d)
        {
            extension = 6d;
            GreenfootImage textImage = new GreenfootImage("⚠ Mass limit reached. The spring no longer follows Hooke's law.",25,new Color(255, 0, 0),new Color(0, 0, 0, 0));
            getBackground().drawImage(textImage, 300, 570);
        }
    }
    
    public void drawPopUps()
    {
        GreenfootImage textImage = new GreenfootImage("",25,new Color(0, 0, 0),new Color(0, 0, 0, 0));
        removeObject(randomise);
        removeObject(done);
        removeObject(yes);
        removeObject(no);
        removeObject(value);
        removeObject(name);
        for (int i = 0; i < 5; i++)
        {
            removeObject(colours[i]);
            colours[i].setFalse();
        }
        
        
        if (latest_true == "Set gravity")
        {
            set_spring_constant.setFalse();
            create_mass.setFalse();
            remove_mass_from_diagram.setFalse();
            remove_mass_from_bank.setFalse();
            GreenfootImage popup = new GreenfootImage("popup_small.png");
            textImage = new GreenfootImage("Set gravity:", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 0);
            textImage = new GreenfootImage("m/s²", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 255, 30);
            textImage = new GreenfootImage("Enter a value between 5 & 12", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 60);
            getBackground().drawImage(popup, 700, 300);
            addObject(randomise, 765, 400);
            addObject(done, 905, 400);
            addObject(value, 830,345);
        } else if(latest_true == "Set spring constant")
        {
            set_gravity.setFalse();
            create_mass.setFalse();
            remove_mass_from_diagram.setFalse();
            remove_mass_from_bank.setFalse();  
            GreenfootImage popup = new GreenfootImage("popup_small.png");
            textImage = new GreenfootImage("Set spring constant:", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 0);
            textImage = new GreenfootImage("N/m", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 255, 30);
            textImage = new GreenfootImage("Enter a value between 50 & 200", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 60);
            getBackground().drawImage(popup, 700, 300);
            addObject(randomise, 765, 400);
            addObject(done, 905, 400);
            addObject(value, 830,345);
        }
        else if(latest_true == "Create mass")
        {
            set_gravity.setFalse();
            set_spring_constant.setFalse();
            remove_mass_from_diagram.setFalse();
            remove_mass_from_bank.setFalse();
            GreenfootImage popup = new GreenfootImage("popup_big.png");
            textImage = new GreenfootImage("Create mass:", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 0);
            textImage = new GreenfootImage("g", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 255, 30);
            textImage = new GreenfootImage("Enter a value between 0 & 250", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 60);
            textImage = new GreenfootImage("Name:", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 90);
            textImage = new GreenfootImage("Colour:", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 150);
            getBackground().drawImage(popup, 700, 200);
            addObject(randomise, 765, 540);
            addObject(done, 905, 540);
            addObject(value,830,240);
            addObject(name,830,330);
            for (int i = 0; i < 5; i++)
            {
                addObject(colours[i], 830, 387+30*i);
                if (colours[i].name == current_colour)
                {
                    colours[i].setTrue();
                }
            }
            if (current_typing == "Value")
            {
                name.setNotTyping();
            }
            if (current_typing == "Name")
            {
                value.setNotTyping();
            }
        }else if(latest_true == "Remove mass from diagram")
        {
            set_gravity.setFalse();
            set_spring_constant.setFalse();
            create_mass.setFalse();
            remove_mass_from_bank.setFalse();
            GreenfootImage popup = new GreenfootImage("popup_small.png");
            textImage = new GreenfootImage("This deletes the top-most mass", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 0);
            textImage = new GreenfootImage("from the diagram.", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 25);
            textImage = new GreenfootImage("Are you sure?", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 55);
            getBackground().drawImage(popup, 700, 400);
            addObject(yes, 765, 500);
            addObject(no, 905, 500);
        } else if(latest_true == "Remove mass from bank")
        {
            set_gravity.setFalse();
            set_spring_constant.setFalse();
            create_mass.setFalse();
            remove_mass_from_diagram.setFalse();
            GreenfootImage popup = new GreenfootImage("popup_small.png");
            textImage = new GreenfootImage("This deletes the oldest mass", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 0);
            textImage = new GreenfootImage("from the mass bank.", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 25);
            textImage = new GreenfootImage("Are you sure?", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
            popup.drawImage(textImage, 5, 55);
            getBackground().drawImage(popup, 700, 400);
            addObject(yes, 765, 500);
            addObject(no, 905, 500);
        }
        
    }
    
    public void updateBank()
    {
        GreenfootImage mass_background = new GreenfootImage("mass_background.png");
        GreenfootImage textImage = new GreenfootImage("Empty", 25, new Color(255, 255, 255), new Color(0, 0, 0, 0));
        mass_background.drawImage(textImage, 60, 45);
        textImage = new GreenfootImage("Mass Bank", 25, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, 5, 570);
        for (int i = 0; i < 5; i++)
        {
            getBackground().drawImage(mass_background, 10+200*i, 606);
        }
        for (int i = 0; i < mass_bank2.size(); i++)
        {
            removeObject(mass_bank2.get(i));
        }
        for (int i = 0; i < mass_bank.getPointer(); i++)
        {
            mass_bank.get(i).setLocation(false,i);
            addObject(mass_bank.get(i),0,0);
            String temp = mass_bank.get(i).getName();
            textImage = new GreenfootImage(temp, 25, new Color(255, 255, 255), new Color(0, 0, 0, 0));
            getBackground().drawImage(textImage, 15+200*i+90-temp.length()*10,608);
            if (!mass_bank2.contains(mass_bank.get(i)))
            {
                mass_bank2.add(mass_bank.get(i));
            }
        }
    }
    
    public void updateStack()
    {
        for (int i = 0; i < mass_stack2.size(); i++)
        {
            removeObject(mass_stack2.get(i));
        }
        for (int i = 0; i < mass_stack.getPointer(); i++)
        {
            mass_stack.get(i).setLocation(true,i);
            addObject(mass_stack.get(i),0,0);
            if (!mass_stack2.contains(mass_stack.get(i)))
            {
                mass_stack2.add(mass_stack.get(i));
            }
        }
    }
    
    public void unpack()
    {
        if (instruction == "LOAD")
        {
            try {
                data.clear();
                File myObj = new File("save_data.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    data.add(myReader.nextLine());
                }
                myReader.close();
                gravity = Double.valueOf(data.get(0).substring(8,data.get(0).length()));
                spring_constant = Integer.valueOf(data.get(1).substring(16,data.get(1).length()));
                if (data.get(2).substring(13,data.get(2).length()).equals("false")) { hide_gravity.setFalse(); } else { hide_gravity.setTrue(); }
                if (data.get(3).substring(21,data.get(3).length()).equals("false")) { hide_spring_constant.setFalse(); } else { hide_spring_constant.setTrue(); }
                if (data.get(4).substring(15,data.get(4).length()).equals("false")) { hide_extension.setFalse(); } else { hide_extension.setTrue(); }
                if (data.get(5).substring(17,data.get(5).length()).equals("false")) { hide_mass_values.setFalse(); } else { hide_mass_values.setTrue(); }
                if (data.get(6).substring(11,data.get(6).length()).equals("false")) { hide_ruler.setFalse(); } else { hide_ruler.setTrue(); }
                if (data.get(7).substring(13,data.get(7).length()).equals("false")) { hide_colours.setFalse(); } else { hide_colours.setTrue(); }
                mass_bank.setEmpty();
                mass_stack.setEmpty();
                for(int i = 0; i < 5; i++)
                {
                    int index_a = data.get(8+i).indexOf(",");
                    int index_b = data.get(8+i).indexOf(";");
                    if (index_a == 7)
                    {
                        break;
                    }
                    else
                    {
                        double mass_mass = Double.valueOf(data.get(8+i).substring(7,index_a));
                        String mass_colour = data.get(8+i).substring(index_a+1,index_b);
                        String mass_name = data.get(8+i).substring(index_b+1,data.get(8+i).length());
                        mass_bank.enqueue(new Mass(mass_mass,mass_colour,false,i,mass_name));
                    }
                }
                for(int i = 0; i < 5; i++)
                {
                    int index_a = data.get(13+i).indexOf(",");
                    if (index_a == 9)
                    {
                        break;
                    }
                    else
                    {
                        double mass_mass = Double.valueOf(data.get(13+i).substring(9,index_a));
                        String mass_colour = data.get(13+i).substring(index_a+1,data.get(13+i).length());
                        mass_stack.push(new Mass(mass_mass,mass_colour,false,i,""));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else if (instruction == "SAVE")
        {
            try {
                File myObj = new File("save_data.txt"); 
                myObj.delete();
                myObj.createNewFile();
                FileWriter myWriter = new FileWriter("save_data.txt");
                myWriter.write("gravity:"+String.valueOf(gravity)+"\n");
                myWriter.write("spring_constant:"+String.valueOf(spring_constant)+"\n");
                myWriter.write("hide_gravity:"+String.valueOf(hide_gravity.getPressed())+"\n");
                myWriter.write("hide_spring_constant:"+String.valueOf(hide_spring_constant.getPressed())+"\n");
                myWriter.write("hide_extension:"+String.valueOf(hide_extension.getPressed())+"\n");
                myWriter.write("hide_mass_values:"+String.valueOf(hide_mass_values.getPressed())+"\n");
                myWriter.write("hide_ruler:"+String.valueOf(hide_ruler.getPressed())+"\n");
                myWriter.write("hide_colours:"+String.valueOf(hide_colours.getPressed())+"\n");
                for (int i = 0; i < mass_bank.getPointer(); i++)
                {
                    String mass_mass = String.valueOf(mass_bank.get(i).getMass());
                    String mass_colour = mass_bank.get(i).getColour();
                    String mass_name = mass_bank.get(i).getName();
                    myWriter.write("bank_"+String.valueOf(i+1)+":"+mass_mass+","+mass_colour+";"+mass_name+"\n");
                }
                for (int i = mass_bank.getPointer(); i < 5; i++)
                {
                    myWriter.write("bank_"+String.valueOf(i+1)+":,;"+"\n");
                }
                for (int i = 0; i < mass_stack.getPointer(); i++)
                {
                    String mass_mass = String.valueOf(mass_stack.get(i).getMass());
                    String mass_colour = mass_stack.get(i).getColour();
                    String mass_name = mass_stack.get(i).getName();
                    myWriter.write("spring_"+String.valueOf(i+1)+":"+mass_mass+","+mass_colour+"\n");
                }
                for (int i = mass_stack.getPointer(); i < 5; i++)
                {
                    myWriter.write("spring_"+String.valueOf(i+1)+":,"+"\n");
                }
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        instruction = "";
    }
}
