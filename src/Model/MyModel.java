package Model;

import Client.*;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ענבר on 12/06/2017.
 */
public class MyModel implements IModel {

    @Override
    public Maze generate(int rows, int columns) {
        MyMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(rows, columns);
        return maze;
    }


    private void CommunicateWithServer_MazeGenerating(int n, int m) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int[] mazeDimensions = new int[]{n, m};
                        toServer.writeObject(mazeDimensions);
                        toServer.flush();
                        byte[] compressedMaze = (byte[]) ((byte[]) fromServer.readObject());
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[1000];
                        is.read(decompressedMaze);
                        Maze maze = new Maze(decompressedMaze);
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }
            });
        } catch (UnknownHostException var1) {
            var1.printStackTrace();
        }
    }




}
