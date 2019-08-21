Feature: Test Developer.Salesforce.com for Sitetracker
Scenario: Login and Search Test

Given Launch "http://developer.salesforce.com"
When Login as User "ankitmadaan106@gmail.com" and password "sitetracker123"
When Search for "Writing Tests"
Then Assert "Writing Tests" is listed

When Navigate to "Writing Tests |" Page
When Click Link Testing Apex
Then Verify Testing Apex Page is Loaded

Then Logout


