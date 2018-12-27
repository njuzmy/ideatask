package world;

import battlefield.Battlefield;
import creature.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Random;

public class Message {
    static public Battlefield battlefield;
    static private CalabashBrother cb;
    static private Soldier s;
    static private Scorpion sp;
    private int x, y;

    public Message(Battlefield bf) {
        battlefield = bf;
    }

    static public Soldier closestSoldier() {
        Soldier soldier = battlefield.getMS().getList().get(0);
        double distence = 2000;
        for (Soldier s : battlefield.getMS().getList()) {
            if (!s.dead) {
                double x = (Math.pow((double) (cb.getPosition().get_x() - s.getPosition().get_x()), 2) + Math.pow((double) (cb.getPosition().get_y() - s.getPosition().get_y()), 2));
                if (distence > x) {
                    distence = x;
                    soldier = s;
                }
            }
        }
        return soldier;
    }

    static public CalabashBrother closestCalabash() {
        CalabashBrother calabashBrother = battlefield.getCS().getList().get(0);
        double distence = 2000;
        for (CalabashBrother c : battlefield.getCS().getList()) {
            if (!c.dead) {
                double x = (Math.pow((double) (s.getPosition().get_x() - c.getPosition().get_x()), 2) + Math.pow((double) (s.getPosition().get_y() - c.getPosition().get_y()), 2));
                if (distence > x) {
                    distence = x;
                    calabashBrother = c;
                }
            }
        }
        return calabashBrother;
    }

    static public Position decideNext(int j) {
        Position position = new Position(0, 0);
        if (j == 1) {
            position.setPosition(cb.getPosition().get_x(),cb.getPosition().get_y());
            s = battlefield.getMS().aliveSoldier();
            //s = closestSoldier();
            Random t = new Random();
            int x = t.nextInt(2);
            if (x == 1) {
                if (s.getPosition().get_x() > cb.getPosition().get_x()) {
                    position.setPosition(cb.getPosition().get_x() + 1, cb.getPosition().get_y() + 1 - 1);
                } else if (s.getPosition().get_x() < cb.getPosition().get_x()) {
                    position.setPosition(cb.getPosition().get_x() - 1, cb.getPosition().get_y() + 1 - 1);
                } else {
                    if (s.getPosition().get_y() > cb.getPosition().get_y()) {
                        position.setPosition(cb.getPosition().get_x(), cb.getPosition().get_y() + 1);
                    } else if (s.getPosition().get_y() < cb.getPosition().get_y()) {
                        position.setPosition(cb.getPosition().get_x(), cb.getPosition().get_y() - 1);
                    }
                }
            } else {
                if (s.getPosition().get_y() > cb.getPosition().get_y()) {
                    position.setPosition(cb.getPosition().get_x(), cb.getPosition().get_y() + 1);
                } else if (s.getPosition().get_y() < cb.getPosition().get_y()) {
                    position.setPosition(cb.getPosition().get_x(), cb.getPosition().get_y() - 1);
                } else {
                    if (s.getPosition().get_x() > cb.getPosition().get_x()) {
                        position.setPosition(cb.getPosition().get_x() + 1, cb.getPosition().get_y());
                    } else if (s.getPosition().get_x() < cb.getPosition().get_x()) {
                        position.setPosition(cb.getPosition().get_x() - 1, cb.getPosition().get_y());
                    }
                }
            }
        } else if(j == 2) {
            cb = battlefield.getCS().aliveCalabash();
            position.setPosition(s.getPosition().get_x(),s.getPosition().get_y());
            Random t = new Random();
            int x = t.nextInt(2);
            if (x == 1) {
                if (s.getPosition().get_x() > cb.getPosition().get_x()) {
                    position.setPosition(s.getPosition().get_x() - 1, s.getPosition().get_y() + 1 - 1);
                } else if (s.getPosition().get_x() < cb.getPosition().get_x()) {
                    position.setPosition(s.getPosition().get_x() + 1, s.getPosition().get_y() + 1 - 1);
                } else if (s.getPosition().get_x() == cb.getPosition().get_x()) {
                    if (s.getPosition().get_y() > cb.getPosition().get_y()) {
                        position.setPosition(s.getPosition().get_x() + 1 - 1, s.getPosition().get_y() - 1);
                    } else if (s.getPosition().get_y() < cb.getPosition().get_y()) {
                        position.setPosition(s.getPosition().get_x() + 1 - 1, s.getPosition().get_y() + 1);
                    }
                }
            } else {
                if (s.getPosition().get_y() > cb.getPosition().get_y()) {
                    position.setPosition(s.getPosition().get_x() + 1 - 1, s.getPosition().get_y() - 1);
                } else if (s.getPosition().get_y() < cb.getPosition().get_y()) {
                    position.setPosition(s.getPosition().get_x() + 1 - 1, s.getPosition().get_y() + 1);
                } else if(s.getPosition().get_y() == cb.getPosition().get_y()){
                    if (s.getPosition().get_x() > cb.getPosition().get_x()) {
                        position.setPosition(s.getPosition().get_x() - 1, s.getPosition().get_y() + 1 - 1);
                    } else if (s.getPosition().get_x() < cb.getPosition().get_x()) {
                        position.setPosition(s.getPosition().get_x() + 1, s.getPosition().get_y() + 1 - 1);
                    }
                }
            }
        }else{
            position.setPosition(sp.getPosition().get_x(),sp.getPosition().get_y());
            cb = battlefield.getCS().aliveCalabash();
            Random t = new Random();
            int x = t.nextInt(2);
            if (x == 1) {
                if (sp.getPosition().get_x() > cb.getPosition().get_x()) {
                    position.setPosition(sp.getPosition().get_x() - 1, sp.getPosition().get_y() + 1 - 1);
                } else if (sp.getPosition().get_x() < cb.getPosition().get_x()) {
                    position.setPosition(sp.getPosition().get_x() + 1, sp.getPosition().get_y() + 1 - 1);
                } else if (sp.getPosition().get_x() == cb.getPosition().get_x()) {
                    if (sp.getPosition().get_y() > cb.getPosition().get_y()) {
                        position.setPosition(sp.getPosition().get_x() + 1 - 1, sp.getPosition().get_y() - 1);
                    } else if (s.getPosition().get_y() < cb.getPosition().get_y()) {
                        position.setPosition(sp.getPosition().get_x() + 1 - 1, sp.getPosition().get_y() + 1);
                    }
                }
                //System.out.println("here1");
            } else {
                if (sp.getPosition().get_y() > cb.getPosition().get_y()) {
                    position.setPosition(sp.getPosition().get_x() + 1 - 1, sp.getPosition().get_y() - 1);
                } else if (sp.getPosition().get_y() < cb.getPosition().get_y()) {
                    position.setPosition(sp.getPosition().get_x() + 1 - 1, sp.getPosition().get_y() + 1);
                } else if(sp.getPosition().get_y() == cb.getPosition().get_y()){
                    if (sp.getPosition().get_x() > cb.getPosition().get_x()) {
                        position.setPosition(sp.getPosition().get_x() - 1, sp.getPosition().get_y() + 1 - 1);
                    } else if (sp.getPosition().get_x() < cb.getPosition().get_x()) {
                        position.setPosition(sp.getPosition().get_x() + 1, sp.getPosition().get_y() + 1 - 1);
                    }
                }
                //System.out.println("here1");
            }
        }
        return position;
    }

    static synchronized boolean ifDead() {
        Random t = new Random();
        if (t.nextInt(4) == 1) {
            return true;
        } else
            return false;
    }

    static synchronized public boolean calabash(CalabashBrother CB, Soldier soldier1, Scorpion scorpion1, int num) {
        if (num == 1) {
            cb = CB;
            //System.out.println(cb.getName() + "starts :" + cb.dead);
            if (cb.dead) {
                if(cb.getNo() == 1)
                    return true;
                //else
                return false;
            }
            if (battlefield.MsDeadAll()) {
                System.out.println("Calabash Win!");
                writeWinXML("Calabash");
                Open_World.paintCalaWin();
                return false;
            } else {
                Position p = decideNext(1);                    //下一步应该走到哪
                if (battlefield.is_empty(p)) {
                    Open_World.clearLand(cb.getPosition().get_x(), cb.getPosition().get_y());
                    String name = "hlw" + (cb.getNo() - 1);
                    writeMoveXML(cb.getPosition(),p,name);
                    CB.setPosition(p);                           //如果该位置是空的，那么葫芦娃就走到这里
                    Open_World.paintCreature(name, p.get_x(), p.get_y());
                } else {
                    if (!battlefield.isSoldier(p) && !battlefield.isScorpion(p))
                        return true;
                    else if (battlefield.isSoldier(p)) {
                        Soldier soldier = battlefield.getPositionSoldier(p);
                        if (!ifDead()) {
                            Open_World.clearLand(cb.getPosition().get_x(), cb.getPosition().get_y());
                            soldier.dead = true;
                            System.out.println(cb.getName() + "defeat" + soldier.getName());
                            String name = "hlw" + (cb.getNo() - 1);
                            writeMoveXML(cb.getPosition(),p,name);
                            writeDefeatXML(cb,soldier,soldier.getNum(),1,1);
                            cb.setPosition(p);                   //击败位置上的敌人后占领位置
                            Open_World.clearLand(p.get_x(), p.get_y());
                            Open_World.paintCreature(name, p.get_x(), p.get_y());
                            Open_World.paintRemains("soldier", p.get_x(), p.get_y());
                        } else {
                            Open_World.clearLand(cb.getPosition().get_x(), cb.getPosition().get_y());
                            cb.dead = true;
                            System.out.println(soldier.getName() + "defeat" + cb.getName());
                            String name = "hlw" + (cb.getNo() - 1);
                            writeMoveXML(cb.getPosition(),p,name);
                            writeDefeatXML(cb,soldier,soldier.getNum(),1,2);
                            cb.setPosition(p);
                            Open_World.paintRemains(name, p.get_x(), p.get_y());
                        }
                    } else if (battlefield.isScorpion(p)) {
                        Scorpion scorpion = battlefield.getPositionScorpion(p);
                        if (!ifDead()) {
                            Open_World.clearLand(cb.getPosition().get_x(), cb.getPosition().get_y());
                            scorpion.dead = true;
                            System.out.println(cb.getName() + "defeat" + scorpion.getName());
                            String name = "hlw" + (cb.getNo() - 1);
                            writeMoveXML(cb.getPosition(),p,name);
                            writeDefeatScopionXML(cb,scorpion,1,1,1);
                            cb.setPosition(p);                   //击败位置上的敌人后占领位置
                            Open_World.clearLand(p.get_x(), p.get_y());
                            Open_World.paintCreature(name, p.get_x(), p.get_y());
                            Open_World.paintRemains("scorpion", p.get_x(), p.get_y());
                        } else {
                            Open_World.clearLand(cb.getPosition().get_x(), cb.getPosition().get_y());
                            cb.dead = true;
                            System.out.println(scorpion.getName() + "defeat" + cb.getName());
                            String name = "hlw" + (cb.getNo() - 1);
                            writeMoveXML(cb.getPosition(),p,name);
                            writeDefeatScopionXML(cb,scorpion,1,1,2);
                            cb.setPosition(p);
                            Open_World.paintRemains(name, p.get_x(), p.get_y());
                        }
                    }
                }
            }
        } else if(num == 2){
            s = soldier1;
            //System.out.println(s.getName() + "starts :" + battlefield.CsDeadAll());
            if (s.dead) {
                return false;
            }
            if (battlefield.CsDeadAll()) {
                if(battlefield.getCS().getGrandpa().is_used()) {
                    System.out.println("Monster Win!");
                    writeWinXML("Monster");
                    Open_World.paintMonsterWin();
                    return false;
                }
                return true;
            } else {
                Position p = decideNext(2);                    //下一步应该走到哪
                if (battlefield.is_empty(p)) {
                    Open_World.clearLand(s.getPosition().get_x(), s.getPosition().get_y());
                    writeMoveXML(s.getPosition(),p,"soldier");
                    s.setPosition(p);                           //如果该位置是空的，那么小喽喽就走到这里
                    Open_World.paintCreature("soldier", p.get_x(), p.get_y());
                } else {
                    if (battlefield.isSoldier(p) || battlefield.isScorpion(p))
                        return true;
                    else if (!battlefield.isSoldier(p) && !battlefield.isScorpion(p)) {
                        CalabashBrother calabashBrother = battlefield.getPositionCala(p);
                        if (!ifDead()) {
                            Open_World.clearLand(s.getPosition().get_x(), s.getPosition().get_y());
                            System.out.println(calabashBrother.getName() + "defeat" + s.getName());
                            writeMoveXML(s.getPosition(),p,"soldier");
                            writeDefeatXML(calabashBrother,s,s.getNum(),2,1);
                            s.setPosition(p);
                            s.dead = true;
                            Open_World.paintRemains("soldier", p.get_x(), p.get_y());
                        } else {
                            Open_World.clearLand(s.getPosition().get_x(), s.getPosition().get_y());
                            calabashBrother.dead = true;
                            System.out.println(s.getName() + "defeat" + calabashBrother.getName());
                            writeMoveXML(s.getPosition(),p,"soldier");
                            writeDefeatXML(calabashBrother,s,s.getNum(),2,2);
                            s.setPosition(p);
                            Open_World.clearLand(p.get_x(),p.get_y());
                            Open_World.paintCreature("soldier", p.get_x(), p.get_y());
                            String name = "hlw" + (cb.getNo() - 1);
                            Open_World.paintRemains(name, p.get_x(), p.get_y());
                        }
                    }
                }
            }
        }
        else if(num == 3){
            sp = scorpion1;
            if(sp.dead)
                return false;
            if(battlefield.CsDeadAll()){
                if(battlefield.getCS().getGrandpa().is_used()) {
                    System.out.println("Monster Win!");
                    writeWinXML("Monster");
                    Open_World.paintMonsterWin();
                    return false;
                }else
                    return true;
            }else {
                Position p = decideNext(3);                    //下一步应该走到哪
                if (battlefield.is_empty(p)) {
                    Open_World.clearLand(sp.getPosition().get_x(), sp.getPosition().get_y());
                    writeMoveXML(sp.getPosition(), p, "scorpion");
                    sp.setPosition(p);                           //如果该位置是空的，那么小喽喽就走到这里
                    Open_World.paintCreature("scorpion", p.get_x(), p.get_y());
                } else {
                    if (battlefield.isSoldier(p))
                        return true;
                    else if (!battlefield.isSoldier(p)) {
                        CalabashBrother calabashBrother = battlefield.getPositionCala(p);
                        if (!ifDead()) {
                            Open_World.clearLand(sp.getPosition().get_x(), sp.getPosition().get_y());
                            System.out.println(calabashBrother.getName() + "defeat" + sp.getName());
                            writeMoveXML(sp.getPosition(), p, "soldier");
                            writeDefeatScopionXML(calabashBrother, sp, 1, 2, 1);
                            sp.setPosition(p);
                            sp.dead = true;
                            Open_World.paintRemains("scorpion", p.get_x(), p.get_y());
                        } else {
                            Open_World.clearLand(sp.getPosition().get_x(), sp.getPosition().get_y());
                            calabashBrother.dead = true;
                            System.out.println(sp.getName() + "defeat" + calabashBrother.getName());
                            writeMoveXML(sp.getPosition(), p, "scorpion");
                            writeDefeatScopionXML(calabashBrother, sp, 1, 2, 2);
                            sp.setPosition(p);
                            Open_World.clearLand(p.get_x(), p.get_y());
                            Open_World.paintCreature("scorpion", p.get_x(), p.get_y());
                            String name = "hlw" + (cb.getNo() - 1);
                            Open_World.paintRemains(name, p.get_x(), p.get_y());
                        }
                    }
                }
            }
        }
        else{
            if(battlefield.CsDeadAll()){
                if(!battlefield.getCS().getGrandpa().is_used()) {
                    battlefield.getCS().getList().get(0).dead = false;
                    System.out.println("grandpa start");
                    String name = "hlw0";
                    LiveXML(battlefield.getCS().getList().get(0).getPosition());
                    Open_World.clearLand(battlefield.getCS().getList().get(0).getPosition().get_x(), battlefield.getCS().getList().get(0).getPosition().get_y());
                    Open_World.paintCreature(name, battlefield.getCS().getList().get(0).getPosition().get_x(), battlefield.getCS().getList().get(0).getPosition().get_y());
                    writeMoveXML(battlefield.getCS().getList().get(0).getPosition(), battlefield.getCS().getList().get(0).getPosition(),name);
                    battlefield.getCS().getGrandpa().setskill();
                    battlefield.setField_calabash();
                    return false;
                }
            }
            return true;
        }
        battlefield.clear();
        battlefield.setField_calabash();
        battlefield.setField_soldier();
        return true;
    }

    static synchronized public boolean Scorpion(Scorpion scorpion1) {
        sp = scorpion1;
        if (battlefield.CsDeadAll()) {
            System.out.println("Monster Win!");
            return false;
        }
        else {
            Position p = decideNext(2);                    //下一步应该走到哪
            if (battlefield.is_empty(p)) {
                s.setPosition(p);                           //如果该位置是空的，那么葫芦娃就走到这里
            } else {
                if (battlefield.isSoldier(p))
                    return false;
                else if (!battlefield.isSoldier(p) && !battlefield.isScorpion(p)) {
                    CalabashBrother calabashBrother = battlefield.getPositionCala(p);
                    if (ifDead()) {
                        s.dead = true;
                        System.out.println(calabashBrother.getName() + "defeat" + s.getName());
                    } else {
                        calabashBrother.dead = true;
                        System.out.println(s.getName() + "defeat" + calabashBrother.getName());
                    }
                }
            }
        }
        return true;
    }

    private static void writeWinXML(String name) {
        try {
            SAXReader reader = new SAXReader();
            File file = new File(Open_World.filename);
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Element round = rootElement.addElement("end");
            round.addAttribute("winner", name);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(document);
        } catch (DocumentException e) {
            System.out.println("Document Exception while recording winner\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found while recording winner\n");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncoding while recording winner\n");
        } catch (IOException e) {
            System.out.println("IO Exception while recording winner\n");
        }
    }

    private static void LiveXML(Position p) {
        try {
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
        } catch (DocumentException e) {
            System.out.println("Document Exception while recording winner\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found while recording winner\n");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncoding while recording winner\n");
        } catch (IOException e) {
            System.out.println("IO Exception while recording winner\n");
        }
    }

    private static void writeDefeatScopionXML(CalabashBrother c,Scorpion s, int soldierIndex,int num, int num2) {            //num用来表示谁赢了，num1表示谁向谁发起了进攻
        try {
            SAXReader reader = new SAXReader();
            File file = new File(Open_World.filename);
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Element round = rootElement.addElement("defeat_s");
            if(num == 1 && num2 == 1) {    //葫芦娃向野怪发起进攻并获胜
                round.addAttribute("position_x", ""+s.getPosition().get_x());
                round.addAttribute("position_y",""+s.getPosition().get_y());
                round.addAttribute("win_id",""+(c.getNo() - 1));
                round.addAttribute("lose_id",""+soldierIndex);
                round.addAttribute("num1",""+num);
                round.addAttribute("num2",""+num2);
            }
            else if(num == 1 && num2 ==2){   //葫芦娃发起进攻但落败
                round.addAttribute("position_x", ""+s.getPosition().get_x());
                round.addAttribute("position_y",""+s.getPosition().get_y());
                round.addAttribute("win_id",""+soldierIndex);
                round.addAttribute("lose_id",""+(c.getNo() - 1));
                round.addAttribute("num1",""+num);
                round.addAttribute("num2",""+num2);
            }
            else if(num == 2 && num2 == 1){   //野怪发起进攻但落败
                round.addAttribute("position_x", ""+c.getPosition().get_x());
                round.addAttribute("position_y",""+c.getPosition().get_y());
                round.addAttribute("win_id",""+(c.getNo() - 1));
                round.addAttribute("lose_id",""+soldierIndex);
                round.addAttribute("num1",""+num );
                round.addAttribute("num2",""+num2);
            }
            else if(num ==2 && num2 == 2){                             //野怪发起进攻并胜利
                round.addAttribute("position_x", ""+c.getPosition().get_x());
                round.addAttribute("position_y",""+c.getPosition().get_y());
                round.addAttribute("win_id",""+soldierIndex);
                round.addAttribute("lose_id",""+(c.getNo() - 1));
                round.addAttribute("num1",""+num);
                round.addAttribute("num2",""+num2);
            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(document);
        } catch (DocumentException e) {
            System.out.println("Document Exception while recording winner\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found while recording winner\n");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncoding while recording winner\n");
        } catch (IOException e) {
            System.out.println("IO Exception while recording winner\n");
        }
    }

    private static void writeDefeatXML(CalabashBrother c,Soldier s, int soldierIndex,int num, int num2) {            //num用来表示谁赢了，num1表示谁向谁发起了进攻
        try {
            SAXReader reader = new SAXReader();
            File file = new File(Open_World.filename);
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Element round = rootElement.addElement("defeat");
            if(num == 1 && num2 == 1) {    //葫芦娃向野怪发起进攻并获胜
                round.addAttribute("position_x", ""+s.getPosition().get_x());
                round.addAttribute("position_y",""+s.getPosition().get_y());
                round.addAttribute("win_id",""+(c.getNo() - 1));
                round.addAttribute("lose_id",""+soldierIndex);
                round.addAttribute("num1",""+num);
                round.addAttribute("num2",""+num2);
            }
            else if(num == 1 && num2 ==2){   //葫芦娃发起进攻但落败
                round.addAttribute("position_x", ""+s.getPosition().get_x());
                round.addAttribute("position_y",""+s.getPosition().get_y());
                round.addAttribute("win_id",""+soldierIndex);
                round.addAttribute("lose_id",""+(c.getNo() - 1));
                round.addAttribute("num1",""+num);
                round.addAttribute("num2",""+num2);
            }
            else if(num == 2 && num2 == 1){   //野怪发起进攻但落败
                round.addAttribute("position_x", ""+c.getPosition().get_x());
                round.addAttribute("position_y",""+c.getPosition().get_y());
                round.addAttribute("win_id",""+(c.getNo() - 1));
                round.addAttribute("lose_id",""+soldierIndex);
                round.addAttribute("num1",""+num );
                round.addAttribute("num2",""+num2);
            }
            else if(num ==2 && num2 == 2){                             //野怪发起进攻并胜利
                round.addAttribute("position_x", ""+c.getPosition().get_x());
                round.addAttribute("position_y",""+c.getPosition().get_y());
                round.addAttribute("win_id",""+soldierIndex);
                round.addAttribute("lose_id",""+(c.getNo() - 1));
                round.addAttribute("num1",""+num);
                round.addAttribute("num2",""+num2);
            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(document);
        } catch (DocumentException e) {
            System.out.println("Document Exception while recording winner\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found while recording winner\n");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncoding while recording winner\n");
        } catch (IOException e) {
            System.out.println("IO Exception while recording winner\n");
        }
    }

    private static void writeMoveXML(Position old,Position newx,String name) {
        try {
            SAXReader reader = new SAXReader();
            File file = new File(Open_World.filename);
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Element round = rootElement.addElement("move");
            round.addAttribute("oldPosition_x", old.get_x() + "");
            round.addAttribute("oldPosition_y", old.get_y() + "");
            round.addAttribute("newPosition_x", newx.get_x() + "");
            round.addAttribute("newPosition_y", newx.get_y() + "");
            round.addAttribute("name",name);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(document);
        } catch (DocumentException e) {
            System.out.println("Document Exception while recording winner\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found while recording winner\n");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncoding while recording winner\n");
        } catch (IOException e) {
            System.out.println("IO Exception while recording winner\n");
        }
    }
}

