import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

class Tile extends StackPane {

    Button btn = new Button();
    boolean hasBomb;
    public boolean isSuperBomb;
    int numBombs = 0;
    Color color = null;
    private boolean flagged = false;
    public static int flagged_count = 0;
    public static int count_flags = 0;
    ArrayList<Tile> neighbours = new ArrayList<Tile>();
    private boolean active = true;

    public static int uncoveredTiles = 0;

    static Image flag = new Image("flag.png");
//    static Image wave = new Image("wave.png");


    Tile(int x, int y, boolean hasBomb) {
//        btn.setGraphic(new ImageView(wave));
        this.hasBomb = hasBomb;

        if (hasBomb) {
            Main.numBombs++;
        }

        btn.setMinHeight(35);
        btn.setMinWidth(35);

        btn.setOnMouseClicked(this::onClick);

        getChildren().addAll(btn);

        setTranslateX(x * 35);
        setTranslateY(y * 35);

    }

    private void onClick(MouseEvent e) {
        if (Main.sound) {
            AudioClip click = new AudioClip(Main.class.getResource("click.wav").toString());
            click.play();
        }

        // Left Click
        if (e.getButton() == MouseButton.PRIMARY) {
            if (!flagged) {
                uncoveredTiles++;
                btn.setBackground(null);
                btn.setDisable(true);
                active = false;

                if (hasBomb) {
                    Main.gameOver();
                } else {
                    // Blank
                    if (this.numBombs == 0) {
                        blankClick(this);
                    } else {
                        btn.setText(Integer.toString(numBombs));
                        btn.setTextFill(color);
                    }
                }
                if ((Main.gridSize*Main.gridSize-uncoveredTiles) == Main.numBombs) Main.win();
            }
        }
        // Right Click
        else {
            if (!flagged) {
                if(count_flags<10) {
                    flagged = true;

                    count_flags++;
                    //Main.numberProperty.set(count_flags);
                    Main.totalFlags.setText("Flagged Positions: " + Tile.count_flags);

                    flagged_count++;

                    btn.setGraphic(new ImageView(flag));
                    if (this.hasBomb) {
                        Main.foundBombs++;
                        // FLAG WIN
                        //if (Main.foundBombs == Main.numBombs) Main.win();
                    }
                    if (this.isSuperBomb && flagged_count < 4) {

//                    this.btn.setDisable(true);
//                    this.btn.setGraphic(Main.supermine);

                        for (int i = 0; i < Main.gridSize; i++) {
                            if (i == Main.y_super_bomb)
                                continue;

                            Tile columnTile = Main.grid[Main.x_super_bomb][i];

                            if (columnTile.hasBomb == true) {
                                Main.foundBombs++;
                                columnTile.btn.setGraphic(new ImageView(Main.mine));
                                columnTile.btn.setDisable(true);
                                continue;
                            }

                            columnTile.btn.setDisable(true);
                            columnTile.btn.setGraphic(null);
                            columnTile.btn.setText(Integer.toString(columnTile.numBombs));
                            columnTile.btn.setTextFill(columnTile.color);
                            columnTile.active = false;


                            uncoveredTiles++;
                        }


                        for (int i = 0; i < Main.gridSize; i++) {
                            if (i == Main.x_super_bomb)
                                continue;

                            Tile rowTile = Main.grid[i][Main.y_super_bomb];

                            if (rowTile.hasBomb == true) {
                                Main.foundBombs++;
                                rowTile.btn.setGraphic(new ImageView(Main.mine));
                                rowTile.btn.setDisable(true);
                                continue;
                            }

                            rowTile.btn.setDisable(true);
                            rowTile.btn.setGraphic(null);
                            rowTile.btn.setText(Integer.toString(rowTile.numBombs));
                            rowTile.btn.setTextFill(rowTile.color);
                            rowTile.active = false;


                            uncoveredTiles++;
                        }

                        // FLAG WIN
                        // if (Main.foundBombs == Main.numBombs) Main.win();
                    }
                }

            } else {
                if (hasBomb) {
                    Main.foundBombs--;
                }
                count_flags--;

                btn.setGraphic(null);
                flagged = false;
                //Main.numberProperty.set(count_flags);
                Main.totalFlags.setText("Flagged Positions: " + Tile.count_flags);
            }
        }
    }

    // Clicking blank tile
    private void blankClick(Tile tile) {
        for (int i = 0; i < tile.neighbours.size(); i++) {
            if (tile.neighbours.get(i).active) {
                tile.neighbours.get(i).btn.setDisable(true);
                tile.neighbours.get(i).btn.setGraphic(null);
                tile.neighbours.get(i).btn.setText(Integer.toString(tile.neighbours.get(i).numBombs));
                tile.neighbours.get(i).btn.setTextFill(tile.neighbours.get(i).color);
                tile.neighbours.get(i).active = false;
                uncoveredTiles++;
                if (tile.neighbours.get(i).numBombs == 0) {
                    blankClick(tile.neighbours.get(i));
                }

            }
        }
    }


}
