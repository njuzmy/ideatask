# ideatask

效果展示
===
开始
![Image text](https://github.com/njuzmy/image/blob/master/start.PNG)

结束（小喽喽获胜）
![Image text](https://github.com/njuzmy/image/blob/master/End.PNG)

主要设计理念
====
* Main函数是一个创世纪的过程，到目前为止，一共产生了排序调度者和战场调度者两个上帝；
* 葫芦娃受老爷爷指挥，蝎子精以及小喽喽受蛇精指挥，而所有一切都是继承一个生物类；
* 战场抽象为一个类，分别由老爷爷和蛇精排布双方阵形；
* 战斗本身由战争调度（BattleScheduler）者掌控；
* 战斗开始，葫芦娃呈现直线分布，蛇精则指定野怪则随机排布，使战斗更具观赏性；
* 战斗中老爷爷没有任何作战能力，但他能够在葫芦娃全部阵亡的情况下随机复活一个葫芦娃，而蛇精的基本作用就是排阵并为小喽喽添加战斗力；
* 当然在战斗过程中双方也不是一味往前冲的莽夫，葫芦娃和小喽喽并不是直冲冲的冲向对方阵营，而是似冲非冲，似退非退，因为在游戏设计中并不是将某个
葫芦娃指定某个小喽喽作为对手，这其中添加了随机算法使得每个葫芦娃只有在轮到他动的时候才能知道他这一轮的对手；这样一来战斗就会具有观赏性，结束
双方就像在打太极一样，以守为攻，以攻为守；另外，当有葫芦娃死之后，其他的葫芦娃本着为兄弟报仇的想法，战斗力会大增，就会大杀四方的场面；
* 考虑游戏的公平性，通过多次试验修改参数，使得葫芦娃和小喽喽获胜的概率基本相等
* 阵法方面，葫芦娃阵营以不变应万变，小喽喽方面，因为数量众多，不安排阵法的话容易在战场上出现人挤人的现象，也就是某个小喽喽会被四周的同伙挤得
无法移动的现象，这种情况是我们不希望出现的，所以为小喽喽安排了阵法，并且根据数量不同，会自动分配不同的阵法；

运行说明
===
项目打包生成jar文件可以在命令行下通过执行"java -jar XXX.jar"命令执行。

程序运行后按下键盘的"S"键位，可以开始一场新的战斗，会更具打斗日期自动生成一个文件记录本次战斗的数据；如果在程序运行后按下键盘上的"L"键位，就
会执行复盘功能，我们可以通过一个对话框选择之前存储的一场战斗数据载入战斗过程。

代码设计
===
world包
![Image text](https://github.com/njuzmy/image/blob/master/world.PNG)

creature包
![Image text](https://github.com/njuzmy/image/blob/master/creature.PNG)

面向对象机制
====
继承
===
* 考虑到蛇精，蝎子精，葫芦兄弟等具有某些相同的属性，如名字，位置，动作等等，所以创建一个基类Creature，便于后期添加各种操作手段；
```Java
 public class Creature{ }
 class Grandpa extends Creature{ }
 class Calabash extends Creature{ }
 class Soldier extends Creature{ }
 class Snaker extends Creature{ }
 class Crab extends Creature{ }
```

封装
===
BattleField类表示战场，对外隐藏战场大小，只对外提供InitField,PrintField等方法进行初始化战场以及输出战场信息的功能

静态成员的使用
===
因为整个程序背景中出现的生物都是唯一的，所以只支持一次创建，也就是说之后的所有操作都是在已有的成员对象上进行操作，所以在Creature类中定义了这些静态成员
使得在第一次调用Creature类时，这些静态成员就已经创建，后面就不需要再进行单独创建了。
```Java
    public static Calabash calabash[] = new Calabash[7];
    static Random p = new Random();
    public static int num = p.nextInt(15);
    public static Soldier soldier[] = new Soldier[num];
    public static Grandpa grand_pa = new Grandpa();
    public static Snaker snaker_ = new Snaker();
    public static Crab crab = new Crab();
```

泛型
===
```java
    private List<Soldier> soldiers = new ArrayList<Soldier>();
    private List<CalabashBrother> calabashbrothers = new ArrayList<CalabashBrother>();
```

注解
===
自定义一个注解类用来标记编程过程中需要特别注意的代码片段
```Java
   @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface Notice{
        String message();
        int getposition();
    }
```

文件IO
===
在生成战场阵型的时候，实际上是在外部文件中存了对应的阵型表示，所以需要某个阵型的时候只需要读取外部的文件即可：
```Java
   public String read() throws IOException{
        BufferedReader in1 = new BufferedReader(new FileReader(name));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s = in1.readLine()) != null){
            sb.append(s + '\n');
        }
        in1.close();
        return sb.toString();
    }

    public void setIn() throws IOException{
        in = new DataInputStream(new ByteArrayInputStream(read().getBytes()));
    }

    public char charFromInput() throws IOException{
        while(in.available() != 0)
            return (char)in.readByte();
        return '!';
    }
    ```
    另外，在记录战斗和复盘战斗的时候也添加了文件IO功能:
    ```Java
    SAXReader reader = new SAXReader();
            File file = new File(Open_World.filename);
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Element round = rootElement.addElement("live");
            round.addAttribute("position_x", ""+p.get_x());
            round.addAttribute("position_y", ""+p.get_y());
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(document);
            ```
   关于多线程
   ==
   在游戏中，除了每个生物具有一个线程之外，战场，回放的实现都是通过线程，每个生物体在自己的run函数里面都定义了下一步该走向何处，如果碰到敌人该如何根据
   能力值判断存活与否，相应的在该位置上应当画上谁的尸体；另外需要注意的是，因为战场是个灵临界区，所以同时只能将允许一个线程单独访问，所以会出现这样的一种
   可能，当某个生物线程进入临界区，它本身可能已经在其他的线程运行中被kill，所以在每个线程进入临界区时做的第一件事就是判断自己被kill了没有：
   ````Java
   if (cb.dead) {
                if(cb.getNo() == 1)
                    return true;
                //else
                return false;
            }
   ```
   线程终止方法，只有本身被kill或者己方阵营胜利：
   ```Java
   if (battlefield.MsDeadAll()) {
                System.out.println("Calabash Win!");
                writeWinXML("Calabash");
                Open_World.paintCalaWin();
                return false;
   ```
   
   战斗记录与回放
   ===
   在战场初始化的时候就会自动生成一个.xml文件，文件名是根据运行时的实时时间命名的，所以文件不会重名；
   新建文件后在之后的每一步里，都会记录葫芦娃或野怪的数值信息和状态信息：
   ```Java
   private static void writeWinXML(String name){}
   private static void LiveXML(Position p){}
   private static void writeDefeatScopionXML(CalabashBrother c,Scorpion s, int soldierIndex,int num, int num2){}
   private static void writeDefeatXML(CalabashBrother c,Soldier s, int soldierIndex,int num, int num2){}
   private static void writeMoveXML(Position old,Position newx,String name){}
   ```
   上述函数分别记录生物不同动作对应的写xml文件；
   ![Image text](https://github.com/njuzmy/image/blob/master/restore.PNG)
   
   结语
   ===
   通过这学期的Java课学习，我印象最深的就是万物都是对象，在面向对象的编程中，不断探索事物之间的联系，使其更真实的反应在代码中，此外，学到了很多关于
   java的新知识，学期初对java的认识仅仅停留在c++面向对象的认知，经过一个学期的学期，无论是从书上还是课上，使我对java有了一个新的认知。
     
   
