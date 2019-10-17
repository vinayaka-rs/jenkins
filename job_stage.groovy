pipeline {
    stages {
        stage('Build And Deploy') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[url: 'https://github.com/vinayaka-rs/spring-boot-gradle.git',
                    credentialsId: '605abf89-a797-4b53-ba81-427f3b29a12d']],
                    poll: true,
                    interval: '* * * * *']
                    )
                
                gradle('clean build')
            }
 
        }
    }
}
