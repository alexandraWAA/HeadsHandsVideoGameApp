package creatures;

public class Damage {

    private final int[] values;

    public int[] getValues() {
        return values;
    }

    public int getLength(){
        return values.length;
    }

    public Damage(int start, int end){
        int len = end - start + 1;
        values = new int[len];
        for (int i = 0; i < len; i++) {
            values[i] = start + i;
        }
    }

}
