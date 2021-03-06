#version 400 core

in vec2 passTextureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;

out vec4 out_color;

uniform vec3 lightColor;
uniform sampler2D texture2D;

void main(void){

    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    float nDotl = dot(unitNormal, unitLightVector);
    float brightness = max(nDotl, 0.25);
    vec3 diffuse = brightness * lightColor;

    out_color = vec4(diffuse, 1.0) * texture(texture2D, passTextureCoords);

}