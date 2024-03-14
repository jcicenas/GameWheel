import java.util.ArrayList;
import java.util.HashSet;

public class GameWheel {
    // Main list of slices making up the wheel
    private ArrayList<Slice> slices; 
    // Position of currently selected slice on wheel
    private int currentPos;   
    //These color ArrayLists will be utilized in the sort and scramble
    private ArrayList<Slice> red = new ArrayList<Slice>();
    private ArrayList<Slice> blue = new ArrayList<Slice>();
    private ArrayList<Slice> black = new ArrayList<Slice>();
    private HashSet<Integer> redpos = new HashSet<>();
    private HashSet<Integer> blackpos = new HashSet<>();
    private HashSet<Integer> bluepos = new HashSet<>();
    //constructor
    public GameWheel(){
        this.slices = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            boolean even = false;
            if ((i % 2) == 0) {
                even = true;
            }

            if (i == 0 || i  == 5 || i == 10 || i == 15) {
                Slice s1 = new Slice("Black", (1000 * i));
                slices.add(s1);
                blackpos.add(i);
            }else if(even == true){
                Slice s2 = new Slice("Blue", (100 * i));
                slices.add(s2);
                bluepos.add(i);
            }else if(even == false){
                Slice s3 = new Slice("Red", (200 * i));
                slices.add(s3);
                redpos.add(i);
            }
        }
    }

    public String toString(){
        String s = "";
        for (int i = 0; i < 20; i++) {
             s = s + "\n" + (i + " - " + this.slices.get(i).toString());
        }
        return s;
    }
    public void split(){
        for (Slice s : this.slices) {
            if (s.getColor().equals("Blue")) {
                this.blue.add(s);
            }else if (s.getColor().equals("Black")) {
                this.black.add(s);
            }else if (s.getColor().equals("Red")) {
                this.red.add(s);
            }
        }
    }
    public void scramble(){
        //hashsets don't have static index values - makes this easy
        HashSet<Integer> clonedBluepos = new HashSet<>(bluepos);
        HashSet<Integer> clonedRedpos = new HashSet<>(redpos);
        HashSet<Integer> clonedBlackpos = new HashSet<>(blackpos);
        split();
        this.slices.clear();
        int x  = 0;
        for (Integer i : clonedBluepos) {
            this.slices.add(blue.get(x));
            x++;
        }
        x = 0;
        for (Integer i : clonedRedpos) {
            this.slices.add(red.get(x));
            x++;
        }
        x = 0;
        for (Integer i : clonedBlackpos) {
            this.slices.add(black.get(x));
            x++;
        }
    }
    public void sort(){
        split();
        this.slices.clear();
        sortingalg(this.red);
        sortingalg(this.blue);
        sortingalg(this.black);
        int redc = 0;
        int bluec = 0;
        int blackc = 0;
        for (int i = 0; i < 20; i++) {
            boolean even = false;
            if(i % 2 == 0){
                even = true;
            }
            if (i == 0 || i == 5 || i == 10 || i == 15) {
                this.slices.add(this.black.get(blackc));
                blackc++;
            }else if(even = true){
                this.slices.add(this.blue.get(bluec));
                bluec++;
            }else if(even = false){
                this.slices.add(this.red.get(redc));
                redc++;
            }
        }

    }

    public Slice spinWheel(){
        this.currentPos = 0 + (int)(Math.random() * ((19 - 0) + 1));
        return getSlice(this.currentPos);
    }

    public Slice getSlice(int i){
        if(i > 19 || i < 0){
            return this.slices.get(0);
        }else{
            return this.slices.get(i);
        }
    }

    private void sortingalg(ArrayList<Slice> tlist){
        for (int i = 0; i < tlist.size() - 1; i++) {
            for (int j = 1; j < tlist.size()-1; j++) {
                if(tlist.get(j).getPrizeAmount() > tlist.get(i).getPrizeAmount()){
                    Slice temp = tlist.get(j);
                    tlist.remove(j);
                    tlist.add(i, temp);
                }
            }
        }
    }


}
