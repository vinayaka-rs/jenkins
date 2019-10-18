import helpers.JobHelperTest

def jobHelpertest = new JobHelperTest(this)
//Jobs for Realtime HBase cluster 
jobHelpertest.alertsJob('properties.groovy')