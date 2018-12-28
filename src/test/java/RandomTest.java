import org.junit.Test;
import static org.junit.Assert.*;
import world.Monster_Scheduler;

public class RandomTest {
        @Test
        public void getRandomNumTest() {
            Monster_Scheduler test = new Monster_Scheduler();
            test.init_Soldiers();            //测试妖怪数量
            assertEquals(true,(test.getSoldiers_num()<25));
        }
}
