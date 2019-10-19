package helpers

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
class JobHelperPublish {
    private final DslFactory dslFactory

    JobHelperPublish(DslFactory d) {
        this.dslFactory = d
    }

    Job alertsJob(
            String propertyfilepath
    ) {
        def config = new ConfigSlurper().parse(new File(propertyfilepath).toURI().toURL())
        def job = dslFactory.job(config.job.pipelines.publish_job_name)
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
                gradle('clean build')
            }
        }
    }
}



