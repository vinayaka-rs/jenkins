nestedView('Spring Boot Microservice') {
    views {
        listView('Spring Boot Service Jobs') {
            jobs {
                regex(/Spring-.*/)
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
                lastDuration()
                buildButton()
            }
        }
        deliveryPipelineView("Spring Boot Service Build") {
            pipelineInstances(1)
            enableManualTriggers()
            showChangeLog()
            pipelines {
                component("Build/Test", "Spring-boot-build")
            }
        }
    }
}
nestedView('Demo Spring Microservice') {
    views {
        listView('Demo Spring Service Jobs') {
            jobs {
                regex(/Demo-micro-service-.*/)
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
                lastDuration()
                buildButton()
            }
        }
        deliveryPipelineView("Demo Spring Service Build") {
            pipelineInstances(1)
            enableManualTriggers()
            showChangeLog()
            pipelines {
                component("Build/Test", "Demo-micro-service-build")
            }
        }
    }
}