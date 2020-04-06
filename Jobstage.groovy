pipeline {
//pipelineJob('pipeline-stage') {
    agent any
    stages {
        stage('git') {
            steps {
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
            }
        }
        stage('build') {
            steps {
                gradle('clean build')
            }
        }
    }
//    job('Git') {
//        deliveryPipelineConfiguration('Git', 'git clone')
//        scm {
//            git {
//                remote {
//                    url("https://github.com/vinayaka-rs/spring-boot-gradle.git")
 //                   credentials("605abf89-a797-4b53-ba81-427f3b29a12d")
//                }
//                extensions {
//                    wipeOutWorkspace()
//                }
//                branch('master')
//            }
//        }
 //   }
//    job('Build') {
//        deliveryPipelineConfiguration('build')
//        steps {
//            gradle('clean build')
//        }
//    }
}
