package world;

import battlefield.Battlefield;
import creature.Grandpa;
import creature.Snake;
import sample.Controller;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Open_World {
    private static final int BATTLEFIELD_ROW = 20;
    private static final int BATTLEFIELD_COLUMN = 20;
    private static ExecutorService executorService;
    private static Controller controller;
    public static String filename;
    public static void initBattle(Controller controller1) throws IOException {
        controller = controller1;
        Grandpa grandpa = new Grandpa("grandpa");
        Snake snake = new Snake("snake");             //两个阵营的头
        Calabash_Scheduler calabash_scheduler = new Calabash_Scheduler(grandpa);
        Monster_Scheduler monster_scheduler = new Monster_Scheduler(snake);    //两个阵营的调度者
        Battlefield battlefield = new Battlefield(BATTLEFIELD_ROW, BATTLEFIELD_COLUMN, calabash_scheduler, monster_scheduler);
        battlefield.clear();
        Message message = new Message(battlefield);
        executorService = Executors.newCachedThreadPool();

        calabash_scheduler.init_Soldiers();
        //calabash_scheduler.shuffle();
        //calabash_scheduler.display_name();
        //calabash_scheduler.sortbyRank();
        //calabash_scheduler.shuffle();
        //calabash_scheduler.display_color();
        //calabash_scheduler.sortbyColor();                //homework1

        monster_scheduler.init_Soldiers();
        //monster_scheduler.shuffle();
        //monster_scheduler.display();

        calabash_scheduler.setFormation("Line");          //指定队型

        //monster_scheduler.setFormation("Goose");
        monster_scheduler.set_random_Formation();           //随机队型，由IO实现
        for (int i = 0; i <= 6; i++) {
            String name = "hlw" + i;
            paintCreature(name, battlefield.getCS().getList().get(i).getPosition().get_x(), battlefield.getCS().getList().get(i).getPosition().get_y());
        }
        for (int i = 0; i < battlefield.getMS().getList().size() - 1; i++) {
            //System.out.println("i: "+ battlefield.getMS().getList().get(i).getPosition().get_x() + battlefield.getMS().getList().get(i).getPosition().get_y());
            paintCreature("soldier", battlefield.getMS().getList().get(i).getPosition().get_x(), battlefield.getMS().getList().get(i).getPosition().get_y());
        }

        paintCreature("scorpion",battlefield.getMS().getScorpion().getPosition().get_x(),battlefield.getMS().getScorpion().getPosition().get_y());
        paintCreature("grandpa",battlefield.getCS().getGrandpa().getPosition().get_x(),battlefield.getCS().getGrandpa().getPosition().get_y());

        //paintCreature();
        try{
            Document documented = DocumentHelper.createDocument();
            Element rootElement = documented.addElement("root");
            Element initElement = rootElement.addElement("init");
            for(int i = 0; i<=6;i++){
                Element temp = initElement.addElement("Calabash");
                temp.addAttribute("num","" + i);
                temp.addAttribute("name",battlefield.getCS().getList().get(i).getName());
                temp.addAttribute("x",""+battlefield.getCS().getList().get(i).getPosition().get_x());
                temp.addAttribute("y",""+battlefield.getCS().getList().get(i).getPosition().get_y());
            }
            for(int i = 0; i<battlefield.getMS().getList().size();i++){
                Element temp = initElement.addElement("Monster");
                temp.addAttribute("num","" + i);
                temp.addAttribute("name",battlefield.getMS().getList().get(i).getName());
                temp.addAttribute("x",""+battlefield.getMS().getList().get(i).getPosition().get_x());
                temp.addAttribute("y",""+battlefield.getMS().getList().get(i).getPosition().get_y());
            }

            Element temp = initElement.addElement("Scorpion");
            temp.addAttribute("name","scorpion");
            temp.addAttribute("x",""+battlefield.getMS().getScorpion().getPosition().get_x());
            temp.addAttribute("y",""+battlefield.getMS().getScorpion().getPosition().get_y());

            Element temp1 = initElement.addElement("Grandpa");
            temp1.addAttribute("name","grandpa");
            temp1.addAttribute("x",""+battlefield.getCS().getGrandpa().getPosition().get_x());
            temp1.addAttribute("y",""+battlefield.getCS().getGrandpa().getPosition().get_y());

            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String date = df.format(new Date());
            filename = System.getProperty("user.dir") + '/' + date + ".xml";
            File file = new File(filename);
            XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
            writer.write(documented);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found while init\n");
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println("UnsupportedEncoding while init\n");
        }
        catch (IOException e)
        {
            System.out.println("IO Exception while init\n");
        }
        //battlefield.clear();
        battlefield.setField_calabash();
        battlefield.setField_soldier();         //获取两队的信息包括位置等
        //battlefield.debugDisplay();
        // }

        // public static void RunGame()
        // {    //battlefield.display();

        for(int i = 0; i<calabash_scheduler.getList().size();i++){
            //System.out.println(i + " " + calabash_scheduler.getList().get(i).getPosition().get_x() + " " +calabash_scheduler.getList().get(i).getPosition().get_y());
            executorService.execute(new Thread(calabash_scheduler.getList().get(i)));
        }
        for(int i = 0; i<monster_scheduler.getList().size();i++){
            //System.out.println(i + " " + monster_scheduler.getList().get(i).getPosition().get_x() + "  " +monster_scheduler.getList().get(i).getPosition().get_y());
            executorService.execute(new Thread(monster_scheduler.getList().get(i)));
        }
        executorService.execute(new Thread(monster_scheduler.getScorpion()));
        executorService.execute(new Thread(calabash_scheduler.getGrandpa()));
        executorService.shutdown();

        //battlefield.display();
    }

    public static void paintCreature(String name,int x,int y){
        controller.paintUnitImage(name,x,y);
    }

    public static void clearLand(int x,int y){
        controller.clearUnit(x,y);
    }

    public static void paintRemains(String name,int x,int y){
        controller.paintUnitRemain(name,x,y);
    }

    public static void paintCalaWin(){
        controller.paintCalaWin();
    }

    public static void paintMonsterWin(){
        controller.paintMonsterWin();
    }
}

