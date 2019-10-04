job('example') {
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
        gradle('clean build')
    }

}