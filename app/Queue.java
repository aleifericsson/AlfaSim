/**
 * Write a description of class Queue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Queue  
{
    // instance variables - replace the example below with your own
    int size;
    int pointer;
    Mass[] data;

    /**
     * Constructor for objects of class Queue
     */
    public Queue(int q_size)
    {
        size = q_size;
        pointer = 0;
        data = new Mass[size];
    }

    public void enqueue(Mass mass)
    {
        if (!isFull())
        {
            data[pointer] = mass;
            pointer += 1;
        }
        else
        {
            Mass temp = dequeue();
            enqueue(mass);
        }
    }
    
    public Mass dequeue()
    {
        if (!isEmpty())
        {
            Mass temp = data[0];
            for (int i = 0; i < pointer-1; i++)
            {
                data[i] = data[i+1];
            }
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
    
    public void set(Queue queue)
    {
        data = queue.getQueue();
    }
    
    public int getPointer()
    {
        return pointer;
    }
    
    public Mass[] getQueue()
    {
        return data;
    }
    
    public void setEmpty()
    {
        while (!isEmpty())
        {
            dequeue();
        }
    }
}
