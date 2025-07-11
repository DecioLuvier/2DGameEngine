package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import engine.actors.VisualActor;
import game.levels.Level_01;

public class Manager {
    private Random random;
    private Camera camera;
    private Level level;

    public Manager(JFrame frame) {
        this.random = new Random(12);
        this.camera = new Camera();
        this.level = new Level_01(this);
        this.level.onEnter();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                level.onReleasedKey(e);
            }
        });

        new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level.onTick();
                camera.draw(level.getActors(VisualActor.class));
            }
        }).start();

        frame.add(camera);
    }

    public Random getRandom() {
        return this.random;
    }

    public void setCurrentLevel(Level level) {
        this.level.onExit();
        this.level = level;
        this.level.onEnter();
    }
}
