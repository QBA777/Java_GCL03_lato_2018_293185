package Clock;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    drawClock clock = new drawClock();      //odpalam nowy zegar konstruktor drawClock

    public Canvas startClock() {
        Canvas canvas=new Canvas();
        canvas.setHeight(600);      //wymiary okna canvas, grafiki, tj zegar
        canvas.setWidth(600);
        GraphicsContext gc=canvas.getGraphicsContext2D();
        Timer timer = new Timer();      //tworze nowy timer
        timer.scheduleAtFixedRate(new TimerTask() {     //timer bez opoznienia odswieza zegar
            @Override
            public void run() {         //funkcja odpala sie co 1sekunde i odpala wszystkie funkcje
                gc.clearRect(0, 0, 700, 700);       //czysci plansze i wypisuje od nowa
                clock.drawBackground(gc);       //rysuje tarcze
                clock.drawHands(gc);        //rysuje wskazowki
            }
        },0, 1000);

        return canvas;

    }
}
