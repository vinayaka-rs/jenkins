import helpers.JobHelperBuild

def jobHelperbuild = new JobHelperBuild(this)

jobHelperbuild.alertsJob('properties.groovy')