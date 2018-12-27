package sample;
import javafx.embed.swing.SwingFXUtils;
import world.Open_World;
import world.Replay;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class Controller {
    @FXML
    Canvas canvas;
    @FXML
    Canvas canvas2;
    @FXML
    Canvas canvas3;

    private GraphicsContext gc1;
    private GraphicsContext gc2;
    private GraphicsContext gc3;
    private Stage primaryStage;

    private double gapX,gapY;

    private void paintBackGround(){
        try {
            //File file = new File("../background2.jpg");

            //Image image = new Image("../background2.jpg");

            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background2.jpg"));
            gc2.drawImage(SwingFXUtils.toFXImage(image,null), 20, 0, canvas.getWidth(), canvas.getHeight());
            //gc2.drawImage(image, 20, 0, canvas.getWidth(), canvas.getHeight());
        }
        //catch (Exception e){}
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void init(Stage Stage){
        canvas.toFront();
        canvas3.toFront();
        gc3 = canvas3.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc1 = canvas.getGraphicsContext2D();
        gapX = canvas.getWidth()/20;
        gapY = canvas.getHeight()/20;
        primaryStage = Stage;
        paintBackGround();
    }

    public void clearUnit(int y,int x){
        gc1.clearRect(x*gapX,y*gapY,gapY,gapX);
    }

    public void paintCalaWin(){
        try {
            //File file = new File("../hlw_win.png");
            //System.out.println("here");
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/hlw_win.png"));
            //Image image = new Image(file.toURI().toURL().toString());

            gc3.drawImage(SwingFXUtils.toFXImage(image,null), 0, 0, canvas3.getWidth(), canvas3.getHeight());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void paintMonsterWin(){
        try {
            //System.out.println("here1");
            //File file = new File("../monster_win.png");
            //Image image = new Image(file.toURI().toURL().toString());
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/monster_win.png"));

            gc3.drawImage(SwingFXUtils.toFXImage(image,null), 0, 0, canvas3.getWidth(), canvas3.getHeight());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void initCanvas(){
        for(int i = 0;i<20;i++)
            for(int j = 0; j<20;j++)
                gc2.clearRect(i*gapX,j*gapY,gapY,gapX);
        gc3.clearRect(0,0,canvas3.getWidth(), canvas3.getHeight());
        paintBackGround();
    }
    public void paintUnitImage(String name,int y,int x){
        String url = "/"+name+".png";
        try{
            //File file = new File(url);
            //Image image = new Image(file.toURI().toURL().toString());
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(url));
            gc1.drawImage(SwingFXUtils.toFXImage(image,null),x*gapX,y*gapY,gapY ,gapX);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void paintUnitRemain(String name,int y,int x){
        String url = "/" + name + "_dead.png";
        try{
            //File file = new File(url);
            //Image image = new Image(file.toURI().toURL().toString());
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(url));
            gc2.drawImage(SwingFXUtils.toFXImage(image,null),x*gapX,y*gapY,gapY ,gapX);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void GameStart(){
        initCanvas();
        for(int i = 0;i<20;i++)
            for(int j = 0; j < 20; j++)
                clearUnit(i,j);
        try {
            Open_World.initBattle(this);
            //Open_World.RunGame();
        }catch (Exception e){}
    }

    public void LoadGame(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Game Record File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/main/java/info/"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML","*.xml"));
        File destFile = fileChooser.showOpenDialog(primaryStage);
        if(destFile.exists())
        {
            initCanvas();

            for(int i = 0;i < 20;i++)
                for(int j = 0;j < 20;j++)
                    clearUnit(i,j);

            Thread thread = new Thread(new Replay(this,destFile));
            thread.start();
        }
        else
            System.out.println("Error while choosing file\n");
    }
}
