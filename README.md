# T1:

Start e1:

```bash
javac -cp t1/lib/junit-platform-console-standalone-1.9.1.jar -d t1/bin t1/src/a06/e1/*.java && java -jar t1/lib/junit-platform-console-standalone-1.9.1.jar -cp t1/bin --scan-classpath
```

Start e2:

```bash
javac -d t1/bin t1/src/a06/e2/*.java && java -cp t1/bin a06.e2.Test
```

# T2:

Start e1:

```bash
javac -cp t2/lib/junit-platform-console-standalone-1.9.1.jar -d t2/bin  t2/src/a02b/e1/*.java && java -jar t2/lib/junit-platform-console-standalone-1.9.1.jar -cp t2/bin --scan-classpath
```

Start e2:

```bash
javac -d t2/bin t2/src/a02b/e2/*.java && java -cp t2/bin a02b.e2.Test
```
