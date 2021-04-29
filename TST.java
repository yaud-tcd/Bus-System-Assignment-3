import java.util.ArrayList;
 
class TSTNode
{
    char data;
    boolean isEnd;
    TSTNode left, middle, right;

    public TSTNode(char data)
    {
        this.data = data;
        this.isEnd = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }        
}

class TST
{
    private TSTNode root;
    private ArrayList<String> al;

    public TST()
    {
        root = null;
    }
    
    public void insert(String word)
    {
        root = insert(root, word.toCharArray(), 0);
    }
   
    public TSTNode insert(TSTNode r, char[] word, int ptr)
    {
        if (r == null)
        {
        	 r = new TSTNode(word[ptr]);
        }
        if (word[ptr] < r.data)
        {
        	r.left = insert(r.left, word, ptr);
        }   
        else if (word[ptr] > r.data)
        {
        	r.right = insert(r.right, word, ptr);
        }    
        else
        {
            if (ptr + 1 < word.length)
            {
            	r.middle = insert(r.middle, word, ptr + 1);
            }   
            else
            {
            	r.isEnd = true;
            }     
        }
        return r;
    }

    private void traverse(TSTNode r, String str)
    {
        if (r != null)
        {
            traverse(r.left, str);
            str = str + r.data;
            if (r.isEnd)
            {
            	al.add(str);
            }
            traverse(r.middle, str);
            str = str.substring(0, str.length() - 1); 
            traverse(r.right, str);
        }
    }
    
    public String searchForStop(String user, ArrayList<Stop> arrayOfStops) 
    {
        al = new ArrayList<String>();
        traverse(root, "");
        Stop stop = null;
        String output = "";
        for (String element : al)
        {
            if (element.contains(user))
            {
            	for (int i = 0; i < arrayOfStops.size(); i++)
    	    	{
    	    		if (element.equals(arrayOfStops.get(i).getName().toUpperCase()))
    	    		{
    	    			stop = arrayOfStops.get(i);
    	    			break;
    	    		}
    	    	}
            	if (stop != null)
            	{
            		output = output + "\nStop Name: " + element + "\nStop ID: " + stop.getStopID() + "\nTrip IDs: " + stop.outputTripIDs() + "\n";
            	}
            }
        }
        if(output.equals(""))
        {
        	output = "\nNo stops found with this term.\n";
        }
	    return output;
    }
}