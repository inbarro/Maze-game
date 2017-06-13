package Model;

import Client.*;
import IO.MyDecompressorInputStream;
import View.MazeDisplayer;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ענבר on 12/06/2017.
 */
public class MyModel implements IModel {

    Maze m;

    @Override
    public Maze generate(int rows, int columns) {
        MyMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(rows, columns);
        return maze;
    }

}
