import java.util.HashMap;
import java.util.HashSet;

public class Game {
    
    public static void play(GameWheel g) {
        int totalAmount = 0;
        HashSet<String> list = new HashSet<>();
        
        for (int i = 1; i <= 3; i++) {
            String tempColor;
            int tempAmount;
            Slice tempSlice = g.spinWheel();
            tempAmount = tempSlice.getPrizeAmount();
            tempColor = tempSlice.getColor();
            totalAmount = totalAmount + tempAmount;
            list.add(tempColor);
            System.out.println("Spin " + i + " " + tempSlice.toString());
        }
        int counter = 0;
        String placeholder = "";
        for (String s : list) {
            counter++;
            placeholder = s;
        }

        if (counter > 1) {
            System.out.println("You won $" + totalAmount);
        }else{
            System.out.println(totalAmount * 2);
            System.out.println("All spots landed on were the same color " + placeholder + " doubling your money!");
        }

    }
}
