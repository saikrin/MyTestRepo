$env:VersionMinor = %build.number%
$env:ServersEnv = "local`$257d1c0c-cb92-48fa-b074-1f1ed6e356e3"
$env:URL = "http://172.25.172.240:8888/mmc351/api/"


#UPLOAD
$UploadResultJson = .\curl.exe --% --basic -u admin:admin -F file=@..\..\target\umglogger-module-%VersionMinor%-SNAPSHOT.zip -F name=umglogger-module -F version=1.0.%VersionMinor% --header "Content-Type: multipart/form-data" %URL%repository
Write-Host $UploadResultJson
$UploadResultObject = $UploadResultJson | ConvertFrom-Json
$env:UploadedVersionEnv = $UploadResultObject.versionId

#CREATE DEPLOYMENT
$CreateDeploymentResultJson = curl --% --basic -u admin:admin -d "{\"name\" : \"umglogger-module%VersionMinor%\" ,\"servers\": [ \"%ServersEnv%\" ],\"applications\": [ \"%UploadedVersionEnv%\" ]}" --header "Content-Type: application/json" %URL%deployments
$CreateDeploymentObject = $CreateDeploymentResultJson | ConvertFrom-Json
Write-Host $CreateDeploymentResultJson
Write-Host $CreateDeploymentObject.id

#DEPLOY
$env:DeploymentIdEnv = $CreateDeploymentObject.id
$DeployCommand = curl --% --basic -u admin:admin -X POST http://172.25.172.240:8888/mmc351/api/deployments/%DeploymentIdEnv%/deploy
Write-Host $DeployCommand
invoke-expression $DeployCommand