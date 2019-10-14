job('Spring-boot-build') {
    scm {
        git {
            remote {
                url("https://github.com/vinayaka-rs/spring-boot-gradle.git")
                credentials("605abf89-a797-4b53-ba81-427f3b29a12d")
            }
            extensions {
                wipeOutWorkspace()
            }
            branch('master')
        }
    }
    steps {
        gradle('sonarqube')
        gradle('clean build')
    }

    publishers {
        downstream('Spring-boot-test', 'SUCCESS')
    }

}

job('Spring-boot-test') {
    scm {
        git {
            remote {
                url("https://github.com/vinayaka-rs/spring-boot-gradle.git")
                credentials("605abf89-a797-4b53-ba81-427f3b29a12d")
            }
            extensions {
                wipeOutWorkspace()
            }
            branch('master')
        }
    }
    steps {
        gradle('clean test')
    }
}