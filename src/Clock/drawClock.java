package Clock;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import java.util.Calendar;
import java.util.TimeZone;

public class drawClock {
    public void drawBackground(GraphicsContext gc) {
        int x = 100;
        int y = 100;
        int w = 400;
        int h = 400;
        int secondLine = 50;
        int hoursLine = 30;
        int minuteLine = 10;
        double r = w / 2;
        double x1;
        double y1;
        double x2;
        double y2;

        //rysuje kolo, tarcze zegara
        gc.setLineWidth(6);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x, y, w, h);

        //parametry do kresek na tarczy
        for (int i = 0; i < 60; i++) {

            double degrees = i * 6;     //mnoze razy 6, bo 360 stopni
            double radians = Math.toRadians(degrees);       //stopnie na raadiany

            //co kwadrans strzalka
            if (i % 15 == 0) {
                x1 = (Math.cos(radians) * (r - secondLine)) + 300;
                y1 = (Math.sin(radians) * (r - secondLine)) + 300;
            }
            //pomiedzy godzinowe minimalnie dluzsze
            else if (i % 5 == 0) {
                x1 = (Math.cos(radians) * (r - hoursLine)) + 300;
                y1 = (Math.sin(radians) * (r - hoursLine)) + 300;
            }
            //najkrotsze w kazdej minucie
            else {
                x1 = (Math.cos(radians) * (r - minuteLine)) + 300;
                y1 = (Math.sin(radians) * (r - minuteLine)) + 300;
            }

            x2 = (Math.cos(radians) * r) + 300;
            y2 = (Math.sin(radians) * r) + 300;

            //linie na tarczy
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(5);
            gc.strokeLine(x1, y1, x2, y2);
        }
    }

    public void drawHands(GraphicsContext gc) {
        double r = 200;
        double x = 300;
        double y = 300;
        double x12;     //1 to godziny
        double y12;
        double x22;     //2 to minuty
        double y22;
        double x32;     //3 to sekundy      drugi punkt na planszy
        double y32;
        int hourHandPadding = 100;
        int minuteHandPadding = 60;
        int secondsHandPadding = 20;

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        double hours = (double) ((calendar.get(Calendar.HOUR_OF_DAY)) * 30);    //przelicza godziny na stopnie
        double minute = (double) ((calendar.get(Calendar.MINUTE)) * 6);     //minuty
        double second = (double) ((calendar.get(Calendar.SECOND)) * 6);     //sekundy

        double hourRadians = Math.toRadians(hours) - (Math.PI / 2);     //[rzeuwa wszystkie katy do osi y
        double minuteRadians = Math.toRadians(minute) - (Math.PI / 2);
        double secondRadians = Math.toRadians(second) - (Math.PI / 2);

        //wyliczam polozenie wskazowek
        x12 = (Math.cos(hourRadians) * (r - hourHandPadding)) + 300;
        y12 = (Math.sin(hourRadians) * (r - hourHandPadding)) + 300;

        x22 = (Math.cos(minuteRadians) * (r - minuteHandPadding)) + 300;
        y22 = (Math.sin(minuteRadians) * (r - minuteHandPadding)) + 300;

        x32 = (Math.cos(secondRadians) * (r - secondsHandPadding)) + 300;
        y32 = (Math.sin(secondRadians) * (r - secondsHandPadding)) + 300;

        //rysuje wskazowki z parametrami podanymmi w nawiasach
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(7);
        gc.strokeLine(x, y, x12, y12);      //wskazowka godziny
        gc.strokeOval(x-4,y-4,8,8);     //punkt w centru
        gc.setLineWidth(4);
        gc.strokeLine(x, y, x22, y22);      //wskazowka minuty
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc.strokeLine(x, y, x32, y32);      //wskazowka sekundy
    }


}
