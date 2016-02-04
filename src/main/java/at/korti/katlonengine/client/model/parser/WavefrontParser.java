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

    private final String objFile;
    private final Logger logger = KatlonEngine.logger;

    public WavefrontParser(String pathToObjFile) {
        this.objFile = pathToObjFile;
    }

    /**
     * Parse a new model of a .obj file.
     *
     * @return Parsed Model.
     */
    public Model parse() {
        BufferedReader reader;
        Model m = new Model();
        String line;
        try {
            reader = ResourceHelper.getBufferedReaderForFile(objFile);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            return m;
        }
        try {
            while ((line = reader.readLine()) != null) {
                String prefix = line.split(" ")[0];
                if (prefix.equals("#")) { //Comments should be ignored.
                    continue;
                } else if (prefix.equals("mtllib")) { //Materials are not supported and be ignored.
                    //Not supported
                } else if (prefix.equals("v")) {    //A vertices are parsed to a Vector3f.
                    m.getVertices().add(parseVector3f(line));
                } else if (prefix.equals("vt")) {   //Texture vertices are at the time ignored, should be at a later time supported.

                } else if (prefix.equals("vn")) {   //Normal vertices are parsed to a Vector3f.
                    m.getNormals().add(parseVector3f(line));
                } else if (prefix.equals("usemtl")) {   //Materials are not supported and be ignored.
                    //Not supported
                } else if (prefix.equals("f")) {    //Faces are parsed to a Face object that contain the indices of the vertices, normal vertices and texture vertices.
                    m.getFaces().add(parseFace(m.hasNormals(), line));
                } else {
                    throw new WaveFrontParseException("OBJ file contains a line which couldn't be parsed correctly: " + line);  //Throw a WaveFrontParseException if there is a line that is not supported.
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
        int[] vertexIndices = {Integer.parseInt(faceIndices[1].split("/")[0]) - 1,
                Integer.parseInt(faceIndices[2].split("/")[0]) - 1, Integer.parseInt(faceIndices[3].split("/")[0]) - 1};
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
