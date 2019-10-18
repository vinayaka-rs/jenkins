import helpers.*

def jobHelperbuild = new JobHelperBuild(this)
//Jobs for Realtime HBase cluster 
jobHelperbuild.alertsJob('properties.groovy')