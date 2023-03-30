
### naver ngrinder --- 작성중....
* download : https://github.com/naver/ngrinder/releases

jdk 11
### rcontroller 3.8, jdk11
* set JAVA_HOME=X:\Program Files\Java\jdk-11_28
* "%JAVA_HOME%\bin\java" -jar ngrinder-controller-3.5.8.war --port=8300

### agent
* run_agent.bat
```text
set JAVA_HOME=X:\Program Files\Java\jdk-11_28
...
```
* run_agent_internal.bat
```text
set JAVA_HOME=X:\Program Files\Java\jdk-11_28
...
"%JAVA_HOME%\bin\java" -server -cp "lib/ngrinder-core-3.5.8.jar;lib/ngrinder-runime-3.5.8.jar;%CLASSPATH%" org.ngrinder.NGrinderAgentStarter --mode agent --command run %*

```
