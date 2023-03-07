import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.control.Label;
import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.paint.Color;

public class Main extends Application {
    private static Label countdownLabel = new Label();
    private static Label bombsLeft = new Label();
    public static Label totalFlags = new Label();
    private static int bombPercent = 10;
    public static int remainingSeconds = 10; //default
    private static int remainingSecondsReload = 120; //default
    public static int gridSize = 10;
    private static int availableTime = 120;
    public static Tile[][] grid;
    private static Stage main;
    private static VBox vbox = new VBox();

    static int numBombs, foundBombs, totalBombs = 10;

    private static int secondsPassed;

    public static SimpleIntegerProperty numberProperty;

    public static Timer timer;

    public static boolean gameOver = false;

    public static int y_super_bomb;

    public static int x_super_bomb;

    public static boolean super_bomb_flag=true;

    public static boolean s_b_flag = true;

    static Image mine = new Image("mine.png");

    static Image submarine = new Image("submarine.png");

    static Image seaMine = new Image("sea_mine.png");

    static boolean sound = true;

    @Override
    public void start(Stage stage) {

        grid = new Tile[gridSize][gridSize];

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
            }
        };

        timer = new Timer();

        timer.scheduleAtFixedRate(task, 1000, 1000);

        main = stage;

        main.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        main.getIcons().add(mine);
        main.setTitle("Minesweeper with a Twist! - NP");

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem about = new MenuItem("About");
        about.setOnAction(e -> {
            Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION,
                    "Created by Nicolas Pigadas. \n" + "nicolaspigadas14@gmail.com \n" + "version 1.0", ButtonType.CLOSE);
            aboutAlert.setTitle("About");
            aboutAlert.setHeaderText("Minesweeper");

            ((Stage) aboutAlert.getDialogPane().getScene().getWindow()).getIcons().add(mine);

            aboutAlert.showAndWait();
        });
        MenuItem help = new MenuItem("Information");
        help.setOnAction(e -> {
            Alert helpAlert = new Alert(Alert.AlertType.INFORMATION,
                    "Welcome to Minesweeper with a Twist!!\n\n"
                            + "It is like the classical game we all know and love, but with some differences in the scenario and the rules. \n\n"
                            + "Scenario\n"
                            + "In this game, you are in a submarine in the enemy's territory! \n\n"
                            + "Welp! The sea mine radar doesn't work anymore... Thankfully, we are equipped with a radar that shows the sea mines around each square (kilometer). \n\n"
                            + "You have to locate every sea mine and flag it in order to help our submarine devise its course and maneuver to safety! \n\n"
                            + "But beware... The enemy submarines are onto you! They are on your tail and about to catch you, be fast! \n\n"
                            + "Rules\n"
                            + "You win if the only tiles left are sea mines, not if you flag all the sea mines. \n\n"
            );
            helpAlert.setTitle("Info");
            helpAlert.setHeaderText("The Game");
            helpAlert.showAndWait();
        });
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> {
            Platform.exit();
        });
        menuFile.getItems().addAll(about, help, quit);

        Menu menuSize = new Menu("Size");
        MenuItem ten = new MenuItem("10x10");
        ten.setOnAction(e -> {
            gridSize = 10;
            reload();
        });
        MenuItem fifteen = new MenuItem("15x15");
        fifteen.setOnAction(e -> {
            gridSize = 15;
            reload();
        });
        MenuItem twenty = new MenuItem("20x20");
        twenty.setOnAction(e -> {
            gridSize = 20;
            reload();
        });
        menuSize.getItems().addAll(ten, fifteen, twenty);

        Menu menuDifficulty = new Menu("Difficulty");
        MenuItem easy = new MenuItem("Easy - 10% Bombs");
        easy.setOnAction(e -> {
            bombPercent = 10;
            reload();
        });
        MenuItem medium = new MenuItem("Medium - 15% Bombs");
        medium.setOnAction(e -> {
            bombPercent = 15;
            reload();
        });
        MenuItem hard = new MenuItem("Hard - 20% Bombs");
        hard.setOnAction(e -> {
            bombPercent = 20;
            reload();
        });
        menuDifficulty.getItems().addAll(easy, medium, hard);


        Menu Time = new Menu("Time");
        MenuItem secs_120 = new MenuItem("120 seconds");
        secs_120.setOnAction(e -> {
            remainingSeconds = 10;
            remainingSecondsReload = 10;
            reload();
        });
        MenuItem secs_180 = new MenuItem("180 seconds");
        secs_180.setOnAction(e -> {
            remainingSeconds = 180;
            remainingSecondsReload = 180;
            reload();
        });
        Time.getItems().addAll(secs_120, secs_180);

        Menu menuSound = new Menu("Sound");
        RadioMenuItem soundOn = new RadioMenuItem("On");
        soundOn.setOnAction(e -> {
            sound = true;
        });
        RadioMenuItem soundOff = new RadioMenuItem("Off");
        soundOff.setOnAction(e -> {
            sound = false;
        });
        ToggleGroup soundToggle = new ToggleGroup();
        soundToggle.getToggles().addAll(soundOn, soundOff);
        soundToggle.selectToggle(soundOn);

        menuSound.getItems().addAll(soundOn, soundOff);

        menuBar.getMenus().addAll(menuFile, menuSize, menuDifficulty, Time, menuSound);

        Timeline timeline = new Timeline();
        timeline.stop();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    remainingSeconds--;
                    if (remainingSeconds < 0) {
                        remainingSeconds = 0;
                        timeLimit();



                        //timeline.stop(); //took me 2h to find this bug omg
                        //remainingSeconds = remainingSecondsReload; // not working
                    } else {
                        countdownLabel.setText("Time left: " + remainingSeconds + " seconds");
                    }
                })
        );
        timeline.play();

        countdownLabel.setText("Time left: " + timeline + " seconds");

        bombsLeft.setText("Number of Sea Mines: " + totalBombs);

        totalFlags.setText("Flagged Positions: " + Tile.count_flags);

//        // Bind the label text to a property
//        IntegerProperty numberProperty = new SimpleIntegerProperty(0);
//        // Update the property instead of the variable
//        numberProperty.set(Tile.count_flags);
//        totalFlags.textProperty().bind(numberProperty.asString("Flagged Positions: %d"));


//        numberProperty = new SimpleIntegerProperty(0);
//        numberProperty.asString("Flagged Positions: %d").addListener((observable, oldValue, newValue) -> {
//            //int count_flags = Tile.count_flags; // get the count of flags from the Tile object
//            totalFlags.setText("Positions Flagged " + Tile.count_flags);//count_flags);
//        });








        vbox.getChildren().addAll(menuBar, createContent(), countdownLabel, bombsLeft, totalFlags);

        Scene scene = new Scene(vbox);

        scene.getStylesheets().add("style.css");
        main.setScene(scene);
        main.setResizable(false);
        main.sizeToScene();
        main.show();
    }

    private static void reload() {

        grid = new Tile[gridSize][gridSize];

        secondsPassed = 0;
        Tile.flagged_count=0;
        Tile.count_flags=0;
        totalFlags.setText("Flagged Positions: " + Tile.count_flags);
//        numberProperty.set(0);
//        numberProperty = new SimpleIntegerProperty(0);
//        numberProperty.asString("Flagged Positions: %d").addListener((observable, oldValue, newValue) -> {
//            //int count_flags = Tile.count_flags; // get the count of flags from the Tile object
//            totalFlags.setText("Positions Flagged " + Tile.count_flags);//count_flags);
//        });

        super_bomb_flag = s_b_flag;
        Tile.uncoveredTiles = 0;
        gameOver = false;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
            };
        };

        timer.cancel();
        timer = new Timer();
        timer.schedule(task, 1000, 1000);

        Timeline timeline = new Timeline();

        remainingSeconds = remainingSecondsReload;

        vbox.getChildren().remove(1, 5);

        vbox.getChildren().addAll(createContent(), countdownLabel, bombsLeft, totalFlags);
        main.sizeToScene();
    }

    /**
     * Create all the tiles and assign bombs accordingly
     *
     * @return root - The playing field
     */
    private static Parent createContent() {

        // Reset to zero in case of new game.
        numBombs = 0;
        foundBombs = 0;

        Pane root = new Pane();
        root.setPrefSize(gridSize * 35, gridSize * 35);

        // Create all tile and buttons on the grid
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {

                Tile tile = new Tile(x, y, false);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        // Assign bombs randomly to tiles.
        for(int i = 0; i < gridSize*gridSize / bombPercent; i++){

            Random rand = new Random();

            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);

            if(super_bomb_flag==true) {
                grid[x][y].isSuperBomb = true;
                super_bomb_flag=false;
                y_super_bomb = y;
                x_super_bomb = x;
            }


            if(grid[x][y].hasBomb){
                if (i == 0) {
                    i = 0;
                } else {
                    i--;
                }
            }
            else{
                grid[x][y].hasBomb = true;
                numBombs++;
            }
        }

        // Add values to the tiles and set their colours accordingly.
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {

                int numNeighboursBomb = 0;

                ArrayList<Tile> neighbours = new ArrayList<Tile>();

                int[] neighboursLocs = new int[] { -1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1 };

                for (int i = 0; i < neighboursLocs.length; i++) {
                    int dx = neighboursLocs[i];
                    int dy = neighboursLocs[++i];

                    int newX = x + dx;
                    int newY = y + dy;

                    if (newX >= 0 && newX < gridSize && newY >= 0 && newY < gridSize) {
                        neighbours.add(grid[newX][newY]);
                        if (grid[newX][newY].hasBomb) {
                            numNeighboursBomb++;
                        }
                    }
                }

                grid[x][y].numBombs = numNeighboursBomb;
                grid[x][y].neighbours = neighbours;

                Color[] colors = { null, Color.BLUE, Color.GREEN, Color.RED, Color.DARKBLUE, Color.DARKRED, Color.CYAN,
                        Color.BLACK, Color.DARKGRAY };

                grid[x][y].color = colors[grid[x][y].numBombs];

            }
        }
        return root;
    }

    /**
     * Runs when a player left clicks a bomb. Reveals all bomb tiles and displays
     * message. Calls to reload the game.
     */
    public static void gameOver() {
        if (sound) {
            AudioClip explosion = new AudioClip(Main.class.getResource("explosion.wav").toString());
            explosion.play();
        }
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (grid[x][y].hasBomb) {
                    grid[x][y].btn.setGraphic(new ImageView(mine));
                    grid[x][y].btn.setDisable(true);
                }
            }
        }

        Alert gameOver = new Alert(AlertType.INFORMATION);
        ((Stage) gameOver.getDialogPane().getScene().getWindow()).getIcons().add(seaMine);
        gameOver.setTitle("Game Over!");
        gameOver.setGraphic(new ImageView(seaMine));
        gameOver.setHeaderText("Sea mine exploded!");
        gameOver.setContentText(
                "Oh no! Your submarine run into a sea mine and caused all the sea mines to explode!");
        gameOver.showAndWait();
        reload();

    }

    public static void timeLimit() {
        if (!gameOver && sound) {
            AudioClip timeoutSound = new AudioClip(Main.class.getResource("timeout.mp3").toString());
            timeoutSound.play();
        }
        if (!gameOver && Main.remainingSeconds == 0) {
            gameOver = true;
            for (int y = 0; y < Main.gridSize; y++) {
                for (int x = 0; x < Main.gridSize; x++) {
                    if (Main.grid[x][y].hasBomb) {
                        Main.grid[x][y].btn.setGraphic(new ImageView(Main.mine));
                    }
                    Main.grid[x][y].btn.setDisable(true);
                }
            }
            Alert gameOver2 = new Alert(AlertType.INFORMATION);
            ((Stage) gameOver2.getDialogPane().getScene().getWindow()).getIcons().add(mine);
            gameOver2.setTitle("Game Over!");
            gameOver2.setGraphic(new ImageView(submarine));
            gameOver2.setHeaderText("Caught by enemy submarines!");
            gameOver2.setContentText(
                    "Oh no! The enemy submarines caught up on you!"
            );
            //gameOver = true;
            Platform.runLater(() -> gameOver2.showAndWait());

            //gameOver2.showAndWait();

        }
    }


    /**
     * Player win. Displays message. Calls to reload the game.
     */
    public static void win() {

        if (sound) {
            AudioClip winSound = new AudioClip(Main.class.getResource("win.wav").toString());
            winSound.play();
        }

        Image winTrophy = new Image("winTrophy.jpeg");
        ImageView winTrophyView = new ImageView(winTrophy);
        winTrophyView.setSmooth(true);
        winTrophyView.setPreserveRatio(true);
        winTrophyView.setFitHeight(135);

        Alert win = new Alert(AlertType.CONFIRMATION);
        ((Stage) win.getDialogPane().getScene().getWindow()).getIcons().add(mine);
        win.setTitle("Win!");
        win.setGraphic(winTrophyView);
        win.setHeaderText("Congratulations, you and your submarine escaped!");
        win.setContentText("You found all the sea mines in " + secondsPassed + " seconds.");
        win.showAndWait();
        reload();
    }

    public static void main(String[] args) {
        launch(args);
    }

}