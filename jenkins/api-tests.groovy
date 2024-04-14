timeout(180) {
    node('maven-slave') {
        wrap([$class: 'BuildUser']) {
            onwerInfo = """<b>Owner:</b> ${env.BUID_USER}"""
            currentBuild.description = onwerInfo
        }
        stage('Checkout') {
            checkout scm
        }
        stage('Running REST autotest') {
            sh "mvn test"
        }
        stage('Publisher allure report') {
            allure([
                    includeProperties: false,
                    jdk              : '',
                    properties       : [],
                    reportBuildPolicy: 'ALWAYS',
                    results          : [[path: 'allure-results']]
            ])
        }
    }
}