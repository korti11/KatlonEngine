package at.korti.katlonengine.client.model.parser;

import at.korti.katlonengine.KatlonEngine;
import at.korti.katlonengine.client.model.Face;
import at.korti.katlonengine.client.model.Model;
import at.korti.katlonengine.client.model.parser.exception.WaveFrontParseException;
import at.korti.katlonengine.util.helper.ResourceHelper;
import at.korti.katlonengine.util.vector.Vector3f;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Korti on 07.01.2016.
 */
public class WavefrontParser {

    private final String file;
    private final Logger logger = KatlonEngine.logger;

    public WavefrontParser(String pathToFile) {
        this.file = pathToFile;
    }

    public Model parse() {
        BufferedReader reader;
        Model m = new Model();
        String line;
        try {
            reader = ResourceHelper.getBufferedReaderForFile(file);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            return m;
        }
        try {
            while ((line = reader.readLine()) != null) {
                String prefix = line.split(" ")[0];
                if (prefix.equals("#")) {
                    continue;
                } else if (prefix.equals("v")) {
                    m.getVertices().add(parseVector3f(line));
                } else if (prefix.equals("vn")) {
                    m.getNormals().add(parseVector3f(line));
                } else if (prefix.equals("f")) {
                    m.getFaces().add(parseFace(m.hasNormals(), line));
                } else {
                    throw new WaveFrontParseException("OBJ file contains a line which couldn't be parsed correctly: " + line);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            return m;
        }
        return m;
    }

    private Vector3f parseVector3f(String line) {
        String[] xyz = line.split(" ");
        float x = Float.valueOf(xyz[1]);
        float y = Float.valueOf(xyz[2]);
        float z = Float.valueOf(xyz[3]);
        return new Vector3f(x, y, z);
    }

    private Face parseFace(boolean hasNormals, String line) {
        String[] faceIndices = line.split(" ");
        int[] vertexIndices = {Integer.parseInt(faceIndices[1].split("/")[0]),
                Integer.parseInt(faceIndices[2].split("/")[0]), Integer.parseInt(faceIndices[3].split("/")[0])};
        if (hasNormals) {
            int[] normalInices = new int[3];
            normalInices[0] = Integer.parseInt(faceIndices[1].split("/")[2]);
            normalInices[1] = Integer.parseInt(faceIndices[2].split("/")[2]);
            normalInices[2] = Integer.parseInt(faceIndices[3].split("/")[2]);
            return new Face(vertexIndices, normalInices);
        } else {
            return new Face(vertexIndices);
        }
    }

}