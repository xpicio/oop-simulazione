Start e1:

```bash
javac -cp lib/junit-platform-console-standalone-1.9.1.jar -d bin src/a02b/e1/*.java && java -jar lib/junit-platform-console-standalone-1.9.1.jar -cp bin --scan-classpath
```

Start e2:

```bash
javac -d bin src/a02b/e2/*.java && java -cp bin a02b.e2.Test
```
