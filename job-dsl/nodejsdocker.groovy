job('NodeJS Docker example COURSE') {
    scm {
        git('git://github.com/YDFDarvin/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Serhii Andrukh')
            node / gitConfigEmail('serhii.andrukh@blood-punk.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('16.6.1') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('ydfd/docker-nodejs-example')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
