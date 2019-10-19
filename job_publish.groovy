import helpers.*

def jobHelperpublish = new JobHelperPublish(this)
//Jobs for Realtime HBase cluster 
jobHelperpublish.alertsJob('properties.groovy')