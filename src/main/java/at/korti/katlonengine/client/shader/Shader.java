package at.korti.katlonengine.client.shader;

import at.korti.katlonengine.KatlonEngine;
import at.korti.katlonengine.util.helper.ResourceHelper;
import at.korti.katlonengine.util.matrix.Matrix4f;
import at.korti.katlonengine.util.vector.Vector;
import at.korti.katlonengine.util.vector.Vector3f;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Korti on 09.01.2016.
 */
public abstract class Shader {

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    protected final Logger logger = KatlonEngine.logger;

    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    public Shader(String vertexFile, String fragmentFile) {
        this.vertexShaderID = loaderShader(vertexFile, GL_VERTEX_SHADER);
        this.fragmentShaderID = loaderShader(fragmentFile, GL_FRAGMENT_SHADER);
        this.programID = glCreateProgram();
        glAttachShader(programID, vertexShaderID);
        glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        glLinkProgram(programID);
        glValidateProgram(programID);
        getAllUniformLocations();
    }

    private int loaderShader(String file, int type) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader reader = ResourceHelper.getBufferedReaderForFile(file);
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            System.exit(-1);
        }

        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, shaderSource);
        glCompileShader(shaderID);
        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            logger.error(glGetShaderInfoLog(shaderID));
            logger.error("Couldn't compile shader!");
            logger.error("File: " + file);
            System.exit(-1);
        }
        return shaderID;
    }

    /**
     * Bind all attributes of the OpenGL attribute list, to use it as a in variable in the shader program.
     */
    protected abstract void bindAttributes();

    /**
     * Find all locations of the uniform variables.
     */
    protected abstract void getAllUniformLocations();

    /**
     * Bind a attribute to a variable of the shader program.
     *
     * @param attribute    ID of OpenGL attribute list.
     * @param variableName Name of the variable in the shader program.
     */
    protected void bindAttribute(int attribute, String variableName) {
        glBindAttribLocation(programID, attribute, variableName);
    }

    public void start() {
        glUseProgram(programID);
    }

    public void stop() {
        glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
        glDeleteProgram(programID);
    }

    protected int getUniformLocation(String uniformName) {
        return glGetUniformLocation(programID, uniformName);
    }

    //region Value load

    /**
     * Load a float value to a uniform variable in the shader program.
     * @param location Location id of the uniform variable.
     * @param value Value you want to load in.
     */
    protected void loadFloat(int location, float value) {
        glUniform1f(location, value);
    }

    /**
     * Load a int value to a uniform variable in the shader program.
     * @param location Location id of the uniform variable.
     * @param value Value you want to load in.
     */
    protected void loadInt(int location, int value) {
        glUniform1i(location, value);
    }

    /**
     * Load a boolean value to a uniform variable in the shader program.
     * @param location Location id of the uniform variable.
     * @param value Value you want to load in.
     */
    protected void loadBoolean(int location, boolean value) {
        float help = value ? 1 : 0;
        loadFloat(location, help);
    }

    /**
     * Load a vector value to a uniform variable in the shader program.
     * @param location Location id of the uniform variable.
     * @param vector Value you want to load in.
     */
    protected void loadVector(int location, Vector vector) {
        if (vector instanceof Vector3f) {
            Vector3f help = (Vector3f) vector;
            glUniform3f(location, help.x, help.y, help.z);
        }
    }

    /**
     * Load a matrix4f value to a uniform variable in the shader program.
     * @param location Location id of the uniform variable.
     * @param matrix Value you want to load in.
     */
    protected void loadMatrix4f(int location, Matrix4f matrix) {
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        glUniformMatrix4fv(location, false, matrixBuffer);
    }
    //endregion

}
