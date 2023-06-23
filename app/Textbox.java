import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Textbox extends TextDisplay
{
    protected String last_char;
    protected boolean pressable;
    protected boolean typing;
    protected boolean allow_keypress;
    private String text;
    
    public static String[] lowercase = new String[] { 
    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
    "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
    "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", 
    "5", "6", "7", "8", "9", "0", "-", "=", "[", "]", 
    ";", "'", ",", ".", "/", "`", "space", "backspace"};
    
    public static String[] uppercase = new String[] { 
    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
    "U", "V", "W", "X", "Y", "Z", "!", "@", "#", "$", 
    "%", "^", "&", "*", "(", ")", "_", "+", "{", "}", 
    ":", "\"", "<", ">", "?", "~", "space", "backspace"};
     
    public Textbox(String t_name, int t_x, int t_y, int t_width, int t_height, String t_page)
    {
        super(t_name, t_x, t_y, t_width, t_height, t_page);
        this.last_char = "";
        this.pressable = false;
        this.typing = false;
        this.text = "";
        this.allow_keypress = true;
    }
    
    public void act() 
    {
        checkPressable();
        checkPress();
        checkCase();
        updateArray();
        drawText();
        if (name == "Name")
        {
            MyWorld.name_text = text;
        }
        if (name == "Value")
        {
            if (MyWorld.value_text.contains("RANDOM"))
            {
                text = MyWorld.value_text;
                text = text.substring(0, text.length() - 6);
                MyWorld.value_text = text;
            }
            else
            {
                MyWorld.value_text = text;
            }
        }
    }
    
    public void checkPressable() {
    if (this.page == "main") {
      this.pressable = true;
    } else {
      this.pressable = false;
    } 
  }
  
  public void checkPress() {
    MouseInfo mouse = Greenfoot.getMouseInfo();
    if (mouse != null && Greenfoot.mouseClicked(this))
    {
      toggleTyping(); 
    }
  }
  
  public void toggleTyping() {
    if (this.typing == true) {
      this.typing = false;
      MyWorld.current_typing = "";
      this.setImage("textbox_unpressed.png");
    } else {
      this.typing = true;
      MyWorld.current_typing = this.name;
      this.setImage("textbox_pressed.png");
    } 
  }
  
  public void checkCase() {
    if (this.typing == true)
      if (this.allow_keypress == true) {
        for (int i = 0; i < lowercase.length; i++) {
          if (Greenfoot.isKeyDown(lowercase[i])) {
            if (Greenfoot.isKeyDown("shift")) {
              if (lowercase[i] == "space") {
                this.text += " ";
              } else if (lowercase[i] == "backspace") {
                if (this.text.length() != 0)
                  this.text = this.text.substring(0, this.text.length() - 1); 
              } else {
                this.text += lowercase[i];
              } 
              this.last_char = lowercase[i];
              break;
            } 
            if (lowercase[i] == "space") {
              this.text += " ";
            } else if (lowercase[i] == "backspace") {
              if (this.text.length() != 0)
                this.text = this.text.substring(0, this.text.length() - 1); 
            } else {
              this.text += lowercase[i];
            } 
            this.last_char = lowercase[i];
            break;
          } 
        } 
        this.allow_keypress = false;
      } else if (!Greenfoot.isKeyDown(this.last_char)) {
        this.allow_keypress = true;
      }  
  }
  
  public void updateArray() {
    if (this.text.length() >= this.chars) {
      this.text = this.text.substring(0,this.text.length()-1);
    } 
  }
  
  public void drawText() {
    if (typing == true)
    {
        setImage("textbox_pressed.png");
        GreenfootImage textImage = new GreenfootImage(this.text, this.font_size, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        textImage.setFont(new Font("Arial", false, false, this.font_size));
        getImage().drawImage(textImage, 5, 0);
    }
    else
    {
        setImage("textbox_unpressed.png");
        GreenfootImage textImage = new GreenfootImage(this.text, this.font_size, new Color(0, 0, 0), new Color(0, 0, 0, 0));
        textImage.setFont(new Font("Arial", false, false, this.font_size));
        getImage().drawImage(textImage, 5, 0);
    }
  }
  
    public int getInt()
    { 
        return Integer.parseInt(text);
    }
    
    public String getString()
    { 
        return text;
    }
    
    public boolean getTyping()
    {
        return typing;
    }
    
    public void setNotTyping()
    {
        typing = false;
    }
}
