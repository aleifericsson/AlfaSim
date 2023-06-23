/**
 * Write a description of class Stack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stack  
{
    // instance variables - replace the example below with your own
    int size;
    int pointer;
    Mass[] data;
    
    /**
     * Constructor for objects of class Stack
     */
    public Stack(int s_size)
    {
        size = s_size;
        pointer = 0;
        data = new Mass[size];
    }

    public void push(Mass mass)
    {
        if (!isFull())
        {
            data[pointer] = mass;
            pointer += 1;
        }
    }
    
    public Mass pop()
    {
        if (!isEmpty())
        {
            Mass temp = data[pointer-1];
            pointer -= 1;
            data[pointer] = null;
            return temp;
        }
        else
        {
            return null;
        }
    }
    
    public boolean isEmpty()
    {
        if (pointer == 0)
        {
            return true;    
        }
        else
        {
            return false;
        }
    }
    
    public boolean isFull()
    {
        if (pointer == size)
        {
            return true;    
        }
        else
        {
            return false;
        }
    }
    
    public Mass get(int index)
    {
        return data[index];
    }
    
    public int getPointer()
    {
        return pointer;
    }
    
    public void setEmpty()
    {
        while (!isEmpty())
        {
            pop();
        }
    }
}
