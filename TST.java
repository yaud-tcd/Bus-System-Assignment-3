
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
            r = new TSTNode(word[ptr]);
 
        if (word[ptr] < r.data)
            r.left = insert(r.left, word, ptr);
        else if (word[ptr] > r.data)
            r.right = insert(r.right, word, ptr);
        else
        {
            if (ptr + 1 < word.length)
                r.middle = insert(r.middle, word, ptr + 1);
            else
                r.isEnd = true;
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
                al.add(str);
 
            traverse(r.middle, str);
            str = str.substring(0, str.length() - 1);
 
            traverse(r.right, str);
        }
    }
    
    public String searchForStop(String user) {
    al = new ArrayList<String>();
    traverse(root, "");
    String Stops = "";
    for (String element : al){
        if (element.contains(user)){
            Stops = Stops + "\n" + element;
        }
    }
	return Stops;
}
}