package helpers

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
class JobHelperTest {
    private final DslFactory dslFactory

    JobHelperTest(DslFactory d) {
        this.dslFactory = d
    }

    Job alertsJob(
            String propertyfilepath
    ) {
        def config = new ConfigSlurper().parse(dslFactory.readFileFromWorkspace(propertyfilepath))
        def job = dslFactory.job(config.job.pipelines.test_job_name)
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
                gradle('clean test')
            }

            publishers {
                downstream(config.job.pipelines.publish_job_name, 'SUCCESS')
            }
        }
    }
}



