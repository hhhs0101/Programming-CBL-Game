import java.util.*;

public class Arrow {


    long positSeed = System.currentTimeMillis();

    long typeSeed = (System.currentTimeMillis() * System.currentTimeMillis() - 123890);

    Random posRandom = new Random(positSeed);

    Random typeRandom = new Random(typeSeed);

    public void positionOfArrow() {

        int n = posRandom.nextInt(100);

        if (n > 74) {
            positionTop();
        } else if (n > 49) {
            positionBot();
        } else if (n > 24) {
            positionRight();
        } else {
            positionLeft();
        }

    }
    
    public boolean typeOfArrow(){
       
        int i = typeRandom.nextInt(100);
        int probaMagic = 80 - stageLevel;
        return i < probaMagic;
    }

}
