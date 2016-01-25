package at.korti.katlonengine.client.model.parser;

import at.korti.katlonengine.KatlonEngine;
import at.korti.katlonengine.client.model.Model;
import at.korti.katlonengine.util.helper.ResourceHelper;
import at.korti.katlonengine.util.vector.Vector3f;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Korti on 19.01.2016.
 */
public class MaterialTemplateParser {

    private String file;
    private final Logger logger = KatlonEngine.logger;

    public MaterialTemplateParser(String pathToFile) {
        this.file = pathToFile;
    }

    public void parse(Model m) {
        BufferedReader reader;
        String line;
        try {
            reader = ResourceHelper.getBufferedReaderForFile(file);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            return;
        }
        try {

            String name = "";
            Vector3f  ambientColor = new Vector3f();
            Vector3f diffuseColor = new Vector3f();
            Vector3f specularColor = new Vector3f();
            float specularWeight = 0;
            boolean first = true;

            while ((line = reader.readLine()) != null) {
                String prefix = line.split(" ")[0];
                if (prefix.equals("#")) {
                    continue;
                } else if (prefix.equals("newmtl")) {
                    if (!first) {
                        m.getMaterials().put(name, new Model.Material(ambientColor, diffuseColor, specularColor, specularWeight));
                        name = "";
                        ambientColor = new Vector3f();
                        diffuseColor = new Vector3f();
                        specularColor = new Vector3f();
                        specularWeight = 0;
                    }
                    name = line.split(" ")[1];
                    first = false;
                } else if (prefix.equals("Ka")) {
                    ambientColor = parseVector3f(line);
                } else if (prefix.equals("Kd")) {
                    diffuseColor = parseVector3f(line);
                } else if (prefix.equals("Ks")) {
                    specularColor = parseVector3f(line);
                } else if (prefix.equals("Ns")) {
                    specularWeight = Float.valueOf(line.split(" ")[1]);
                }
            }
            m.getMaterials().put(name, new Model.Material(ambientColor, diffuseColor, specularColor, specularWeight));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Vector3f parseVector3f(String line) {
        String[] xyz = line.split(" ");
        float x = Float.valueOf(xyz[1]);
        float y = Float.valueOf(xyz[2]);
        float z = Float.valueOf(xyz[3]);
        return new Vector3f(x, y, z);
    }

}
