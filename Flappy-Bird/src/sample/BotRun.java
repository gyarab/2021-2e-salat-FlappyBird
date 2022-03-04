package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.CountDownLatch;

public class BotRun extends BotController {
    static boolean game;
    ImageView b;
    ImageView rTop;
    ImageView rDown;
    ImageView rTop2;
    ImageView rDown2;

    Pane pane;
    int rLayout = 800;
    int rLayout2 = 800;
    int random = (int) Math.floor(Math.random() * (450 - 300 + 1) + 300);
    int random2 = (int) Math.floor(Math.random() * (450 - 300 + 1) + 300);
    int birdH;
    double fall = 1.2;
    double jump = 2;
    int i = 0;
    int iter = 0;
    double speed = 1;
    int cJumpState = 0;


    public BotRun(ImageView b, boolean g, ImageView rT, ImageView rD, ImageView rT2, ImageView rD2, Pane p) throws InterruptedException {
        game = g;
        this.b = b;
        rTop = rT;
        rDown = rD;
        rTop2 = rT2;
        rDown2 = rD2;
        birdH = (int) b.getLayoutY();
        pane = p;
        GlobalVar.jump = 0;
        GlobalVar.score = 0;

    }

    public static void runAndWait(Runnable action) {
        //source: https://news.kynosarges.org/2014/05/01/simulating-platform-runandwait/
        if (action == null)
            throw new NullPointerException("action");

        if (Platform.isFxApplicationThread()) {
            action.run();
            return;
        }

        final CountDownLatch doneLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                doneLatch.countDown();
            }
        });

        try {
            doneLatch.await();
        } catch (InterruptedException e) {
        }
    }

    public Thread run() {
        Task<Void> task = new Task() {
            @Override
            protected Void call() throws Exception {
                while (game && Platform.isImplicitExit()) {
                    cJumpState = GlobalVar.jump;
                    long start1 = System.nanoTime();
                    rTop.setLayoutY(random - 600);
                    rDown.setLayoutY(random);
                    rTop2.setLayoutY(random2 - 600);
                    rDown2.setLayoutY(random2);
                    Bounds rT = rTop.getBoundsInParent();
                    Bounds rD = rDown.getBoundsInParent();
                    Bounds rT2 = rTop2.getBoundsInParent();
                    Bounds rD2 = rDown2.getBoundsInParent();
                    Bounds bird = b.getBoundsInParent();
                    if (rDown.getLayoutX() <= -75) {
                        rLayout = 800;
                        random = (int) Math.floor(Math.random() * (450 - 300 + 1) + 300);
                        GlobalVar.score++;
                    } else {
                        rLayout = (int) rDown.getLayoutX();
                    }
                    if (rDown2.getLayoutX() <= -75) {
                        rLayout2 = 800;
                        random2 = (int) Math.floor(Math.random() * (450 - 300 + 1) + 300);
                        GlobalVar.score++;
                    } else {
                        rLayout2 = (int) rDown2.getLayoutX();
                    }
                    if (b.getLayoutY() <= 0 || b.getLayoutY() >= 532) {
                        game = false;
                        System.out.println("game over");
                        pane.setVisible(true);
                        System.out.println("Bot: " + GlobalVar.score);

                    }
                    if (bird.intersects(rT) || bird.intersects(rD) || bird.intersects(rT2) || bird.intersects(rD2)) {
                        game = false;
                        System.out.println("game over PILLAR");
                        pane.setVisible(true);
                        System.out.println("Bot:" + GlobalVar.score);
                    }
                    long stop = System.nanoTime() - start1;
                    Thread.sleep(18 - stop / 1000000);
                    runAndWait(() -> {
                        if (GlobalVar.score % 2 == 0) {
                            if (i == 0 && b.getLayoutY() > rDown.getLayoutY() - 70) {
                                Jump.jump();
                            }
                        } else {
                            if (i == 0 && b.getLayoutY() > rDown2.getLayoutY() - 70) {
                                Jump.jump();
                            }

                        }
                        if (GlobalVar.jump >= 1) {
                            if (i <= 15) {
                                b.setLayoutY(b.getLayoutY() - 4.3 * jump);
                                jump -= 0.07;
                                i++;
                                fall = 1;
                                cJumpState += 1;
                            } else {
                                GlobalVar.jump = 0;
                            }
                        } else {
                            i = 0;
                            b.setLayoutY(b.getLayoutY() + 4.3 * fall);
                            fall += 0.063;
                            jump = 2;
                        }
                        rTop.setLayoutX(rLayout - 5 * speed);
                        rDown.setLayoutX(rLayout - 5 * speed);
                        iter++;
                        if (iter >= 60) {
                            rTop2.setLayoutX(rLayout2 - 5 * speed);
                            rDown2.setLayoutX(rLayout2 - 5 * speed);
                        }
                        speed += 0.00001;
                    });
                }
                return null;
            }
        };
        Thread t = new Thread(task);
        t.start();
        return t;
    }
}

