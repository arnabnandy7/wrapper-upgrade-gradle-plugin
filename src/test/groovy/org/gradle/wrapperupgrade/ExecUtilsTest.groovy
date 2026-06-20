package org.gradle.wrapperupgrade

import spock.lang.Specification

class ExecUtilsTest extends Specification {

    def "uses Windows wrapper scripts on Windows hosts"() {
        expect:
        ExecUtils.gradleWrapperCommand('Windows 11') == '.\\gradlew.bat'
        ExecUtils.mavenWrapperCommand('Windows 11') == '.\\mvnw.cmd'
    }

    def "uses POSIX wrapper scripts on non-Windows hosts"() {
        expect:
        ExecUtils.gradleWrapperCommand('Linux') == './gradlew'
        ExecUtils.mavenWrapperCommand('Mac OS X') == './mvnw'
    }

}
