package world;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//import sample.Controller;
import sample.Controller;

import java.io.File;
import java.util.List;

public class Replay implements  Runnable{
    Controller controller;
    File file;

    public Replay(Controller c, File f){
        this.controller = c;
        this.file = f;
    }

    public void run(){
        try{
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for(int i = 0; i <elements.size();i++){
                if(elements.get(i).getName() == "init"){
                    List<Element> initElement = elements.get(i).elements();
                    for(int j = 0; j < initElement.size();j++){
                        if(initElement.get(j).getName() == "Calabash"){
                            String path = "hlw" + initElement.get(j).attributeValue("num");
                            controller.paintUnitImage(path,Integer.parseInt(initElement.get(j).attributeValue("x")),Integer.parseInt(initElement.get(j).attributeValue("y")));
                        }
                        else if(initElement.get(j).getName() == "Monster"){
                            String path = "soldier";
                            controller.paintUnitImage(path,Integer.parseInt(initElement.get(j).attributeValue("x")),Integer.parseInt(initElement.get(j).attributeValue("y")));
                        }
                        else if(initElement.get(j).getName() == "Scorpion"){
                            String path = "scorpion";
                            controller.paintUnitImage(path,Integer.parseInt(initElement.get(j).attributeValue("x")),Integer.parseInt(initElement.get(j).attributeValue("y")));
                        }
                        else if(initElement.get(j).getName() == "Grandpa"){
                            String path = "grandpa";
                            controller.paintUnitImage(path,Integer.parseInt(initElement.get(j).attributeValue("x")),Integer.parseInt(initElement.get(j).attributeValue("y")));
                        }
                        else{
                            System.out.println(initElement.get(j).getName());
                        }
                    }
                } else if (elements.get(i).getName() == "live") {
                    String name = "hlw0";
                    controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                    controller.paintUnitImage(name, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                } else if (elements.get(i).getName() == "end") {
                    //System.out.println(elements.get(i).attributeValue("winner") + " Win!");
                    String name = elements.get(i).attributeValue("winner");
                    if (name.contains("C")) {
                        //System.out.println(elements.get(i).attributeValue("winner") + " Win!");
                        controller.paintCalaWin();
                    }
                    else {
                        System.out.println(name);
                        controller.paintMonsterWin();
                    }
                } else if (elements.get(i).getName() == "move") {
                    controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("oldPosition_x")), Integer.parseInt(elements.get(i).attributeValue("oldPosition_y")));
                    String url = elements.get(i).attributeValue("name");
                    controller.paintUnitImage(url, Integer.parseInt(elements.get(i).attributeValue("newPosition_x")), Integer.parseInt(elements.get(i).attributeValue("newPosition_y")));
                    Thread.sleep(50);
                } else if (elements.get(i).getName() == "defeat") {
                    int num = Integer.parseInt(elements.get(i).attributeValue("num1"));
                    int num2 = Integer.parseInt(elements.get(i).attributeValue("num2"));
                    String calaName;
                    String monsterName = "soldier";
                    if (num == 1 && num2 == 1) {
                        calaName = "hlw" + elements.get(i).attributeValue("win_id");
                        controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitImage(calaName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitRemain(monsterName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                    } else if (num == 1 && num2 == 2) {
                        calaName = "hlw" + elements.get(i).attributeValue("lose_id");
                        controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitImage(monsterName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitRemain(calaName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                    } else if (num == 2 && num2 == 1) {
                        calaName = "hlw" + elements.get(i).attributeValue("win_id");
                        controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitImage(calaName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitRemain(monsterName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                    } else if (num == 2 && num2 == 2) {
                        calaName = "hlw" + elements.get(i).attributeValue("lose_id");
                        controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitImage(monsterName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitRemain(calaName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                    }
                    Thread.sleep(50);
                } else if (elements.get(i).getName() == "defeat_s") {
                    int num = Integer.parseInt(elements.get(i).attributeValue("num1"));
                    int num2 = Integer.parseInt(elements.get(i).attributeValue("num2"));
                    String calaName;
                    String monsterName = "scorpion";
                    if (num == 1 && num2 == 1) {
                        calaName = "hlw" + elements.get(i).attributeValue("win_id");
                        controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitImage(calaName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitRemain(monsterName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                    } else if (num == 1 && num2 == 2) {
                        calaName = "hlw" + elements.get(i).attributeValue("lose_id");
                        controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitImage(monsterName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitRemain(calaName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                    } else if (num == 2 && num2 == 1) {
                        calaName = "hlw" + elements.get(i).attributeValue("win_id");
                        controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitImage(calaName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitRemain(monsterName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                    } else if (num == 2 && num2 == 2) {
                        calaName = "hlw" + elements.get(i).attributeValue("lose_id");
                        controller.clearUnit(Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitImage(monsterName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                        controller.paintUnitRemain(calaName, Integer.parseInt(elements.get(i).attributeValue("position_x")), Integer.parseInt(elements.get(i).attributeValue("position_y")));
                    }
                    Thread.sleep(50);
                }
            }
        }
        catch (DocumentException e)
        {
            //controller.printText("Document Exception while loading game\n");
        }
        catch (InterruptedException e)
        {
            //controller.printText("Interrupted Exception while loading game\n");
        }
    }
}
