cd "D:\S4\MrNaina\Framework_Sarobidy\Framework"
jar cvf framework.jar *
copy "./framework.jar" "D:\S4\MrNaina\Framework_Sarobidy\TestFramework\WEB-INF\lib\"
cd "D:\S4\MrNaina\Framework_Sarobidy\TestFramework\WEB-INF\Classes"
javac -cp "D:\S4\MrNaina\Framework_Sarobidy\TestFramework\WEB-INF\lib\framework.jar" -d . *.java
cd "D:\S4\MrNaina\Framework_Sarobidy\TestFramework"
jar cvf "TestFramework.war" *
copy "TestFramework.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.0\webapps\"