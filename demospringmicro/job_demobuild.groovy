def config = new ConfigSlurper().parse(readFileFromWorkspace('demospringmicro/properties.groovy'))


job('Demo-micro-service-build') {
    scm {
        git {
            remote {
                url(config.git.service.url)
                credentials(config.git.user.cred)
            }
            extensions {
                wipeOutWorkspace()
            }
            branch(config.git.service.branch)
        }
    }

    steps {
        gradle('clean build sonarqube')
    }

    publishers {
        mailer(config.email.user.mail, true, true) 
        extendedEmail { 
            recipientList(config.email.user.mail) 
            defaultSubject('$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!') 
            defaultContent('$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS') 
            contentType('text/html') 
            triggers { 
                failure { 
                    replyToList('$PROJECT_DEFAULT_REPLYTO') 
                    contentType('text/html') 
                    content('$PROJECT_DEFAULT_CONTENT') 
                    subject('$PROJECT_DEFAULT_SUBJECT') 
                    sendTo { 
                    recipientList() 
                    } 
                } 
                success { 
                    replyToList('$PROJECT_DEFAULT_REPLYTO') 
                    contentType('text/html') 
                    content('$PROJECT_DEFAULT_CONTENT') 
                    subject('$PROJECT_DEFAULT_SUBJECT') 
                    sendTo { 
                        recipientList() 
                    } 
                } 
            }
        }
        
        downstream('Demo-micro-service-test', 'SUCCESS')
    }

}

job('Demo-micro-service-test') {
    scm {
        git {
            remote {
                url(config.git.service.url)
                credentials(config.git.user.cred)
            }
            extensions {
                wipeOutWorkspace()
            }
            branch(config.git.service.branch)
        }
    }
    steps {
        gradle('clean test')
    }

    publishers {
        downstream('Demo-micro-service-publish', 'SUCCESS')
    }
}

job('Demo-micro-service-publish') {
    scm {
        git {
            remote {
                url(config.git.service.url)
                credentials(config.git.user.cred)
            }
            extensions {
                wipeOutWorkspace()
            }
            branch(config.git.service.branch)
        }
    }
    steps {
        gradle('clean build')
    }
}

