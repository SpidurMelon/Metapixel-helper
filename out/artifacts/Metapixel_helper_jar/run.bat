@ECHO OFF
set /p stuff=What is the name of your duck game skin image? [Example: Skin.png] (must be in this folder): 
java -jar "Metapixel helper.jar" %stuff%
PAUSE