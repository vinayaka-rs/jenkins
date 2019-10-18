import helpers.JobHelperPublish

def jobHelperpublish = new JobHelperPublish(this)
//Jobs for Realtime HBase cluster 
jobHelperbuild.alertsJob('properties.groovy')