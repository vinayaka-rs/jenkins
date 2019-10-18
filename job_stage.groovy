pipelineJob('pipeline-stage') {
//    agent any
    job('Git') {
        deliveryPipelineConfiguration('Git', 'git clone')
        steps {
            git branch: 'master', credentialsId: '605abf89-a797-4b53-ba81-427f3b29a12d', url: 'https://github.com/vinayaka-rs/spring-boot-gradle.git'
        }
    }
    job('Build') {
        deliveryPipelineConfiguration('build')
        steps {
            gradle('clean build')
        }
    }
}
