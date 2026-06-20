package org.gradle.wrapperupgrade;

import org.gradle.process.ExecOperations;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

final class ExecUtils {

    static void execGradleCmd(ExecOperations execOperations, Path workingDir, Object... args) {
        execCmd(execOperations, workingDir, gradleWrapperCommand(), args);
    }

    static void execMavenCmd(ExecOperations execOperations, Path workingDir, Object... args) {
        execCmd(execOperations, workingDir, mavenWrapperCommand(), args);
    }

    static void execGitCmd(ExecOperations execOperations, Path workingDir, Object... args) {
        execCmd(execOperations, workingDir, "git", args);
    }

    private static void execCmd(ExecOperations execOperations, Path workingDir, String cmd, Object... args) {
        List<Object> cmdLine = new LinkedList<>();
        cmdLine.add(cmd);
        cmdLine.addAll(Arrays.asList(args));
        execOperations.exec(
            execSpec -> {
                execSpec.workingDir(workingDir);
                execSpec.commandLine(cmdLine);
            });
    }

    static String gradleWrapperCommand() {
        return gradleWrapperCommand(System.getProperty("os.name", ""));
    }

    static String mavenWrapperCommand() {
        return mavenWrapperCommand(System.getProperty("os.name", ""));
    }

    static String gradleWrapperCommand(String osName) {
        return isWindows(osName) ? ".\\gradlew.bat" : "./gradlew";
    }

    static String mavenWrapperCommand(String osName) {
        return isWindows(osName) ? ".\\mvnw.cmd" : "./mvnw";
    }

    private static boolean isWindows(String osName) {
        return osName.toLowerCase(Locale.ROOT).contains("windows");
    }

    private ExecUtils() {
    }

}
