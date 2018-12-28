import creature.CalabashBrother;
import world.Calabash_Scheduler;
import org.junit.Test;
import static org.junit.Assert.*;


public class HuluwaTest {              //测试葫芦娃排序以及按颜色排序
    @Test
    public void testSort(){
        CalabashBrother[] test = new CalabashBrother[7];
        test[0] = CalabashBrother.RED;
        test[1] = CalabashBrother.ORANGE;
        test[2] = CalabashBrother.YELLOW;
        test[3] = CalabashBrother.GREEN;
        test[4] = CalabashBrother.CYAN;
        test[5] = CalabashBrother.BLUE;
        test[6] = CalabashBrother.PURPLE;

        Calabash_Scheduler res = new Calabash_Scheduler();
        res.init_Soldiers();
        res.shuffle();
        res.sortbyRank();
        for(int i = 0;i<7;i++){
            assertEquals(test[i].getNo(),res.getList().get(i).getNo());
        }
    }

    @Test
    public void testColorSort(){
        CalabashBrother[] test = new CalabashBrother[7];
        test[0] = CalabashBrother.RED;
        test[1] = CalabashBrother.ORANGE;
        test[2] = CalabashBrother.YELLOW;
        test[3] = CalabashBrother.GREEN;
        test[4] = CalabashBrother.CYAN;
        test[5] = CalabashBrother.BLUE;
        test[6] = CalabashBrother.PURPLE;

        Calabash_Scheduler res = new Calabash_Scheduler();
        res.init_Soldiers();
        res.shuffle();
        res.sortbyColor();
        for(int i = 0;i<7;i++){
            assertEquals(test[i].getColour(),res.getList().get(i).getColour());
        }
    }
}
