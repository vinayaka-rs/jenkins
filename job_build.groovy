import helpers.*

JobHelperBuild jobHelperbuild = new JobHelperBuild(this)
//Jobs for Realtime HBase cluster 
jobHelperbuild.alertsJob('properties.groovy')