package helpers

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
class JobHelperBuild {
    private final DslFactory dslFactory

    JobHelperBuild(DslFactory d) {
        this.dslFactory = d
    }

    Job alertsJob(
            String propertyfilepath
    ) {
        def config = new ConfigSlurper().parse(new File(propertyfilepath))
        def job = dslFactory.job(config.job.pipelines.build_job_name)
        job.with {
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
                
                downstream(config.job.pipelines.test_job_name, 'SUCCESS')
            }

        }
    }
}



