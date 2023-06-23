import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Font;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

public class TextDisplay extends Actor {
  protected String name;
  protected int height;
  protected int width;
  protected int real_x;
  protected int real_y; 
  private String text;
  protected String page;
  protected int lines;
  protected int chars;
  protected int font_size;
  
  public TextDisplay(String t_name, int t_x, int t_y, int t_width, int t_height, String t_page) {
    this.name = t_name;
    this.height = t_height;
    this.width = t_width;
    this.real_x = t_x;
    this.real_y = t_y;
    this.text = "";
    this.page = t_page;
    this.font_size = 25;
    this.lines = (int)Math.floor((this.height / this.font_size));
    this.chars = (int)Math.floor((this.width / this.font_size * 2));
  }
  
  public void act() {
    drawText();
  }
  
  public int getX() {
    return this.real_x + this.width / 2;
  }
  
  public int getY() {
    return this.real_y + this.height / 2;
  }
  
  public void drawText() {
    GreenfootImage textImage = new GreenfootImage(this.text, this.font_size, new Color(0, 0, 0), new Color(0, 0, 0, 0));
    textImage.setFont(new Font("Cascadia Mono", false, false, this.font_size));
    getImage().drawImage(textImage, 0, 0);
  }
}

