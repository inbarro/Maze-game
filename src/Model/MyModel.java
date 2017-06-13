package Model;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

/**
 * Created by ענבר on 12/06/2017.
 */
public class MyModel implements IModel {
    public MyModel() {
    }


    @Override
    public Maze generate(int rows, int columns) {
        MyMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(rows, columns);
       return maze;
    }
}
