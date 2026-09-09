pipeline {

    agent any

    tools {
        jdk 'Java-25'
        maven 'Maven-3.9.16'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running Selenium TestNG tests...'
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'

            // Archive the ExtentReport HTML + failure screenshots so they
            // show up as downloadable artifacts on the build page, even
            // when the test stage fails.
            archiveArtifacts artifacts: 'test-output/**', allowEmptyArchive: true

            // If the HTML Publisher plugin is installed on this Jenkins,
            // this also renders a clickable "QA Automation Report" link
            // directly on the build page. If the plugin isn't installed,
            // remove this block — archiveArtifacts above is enough on its own.
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output',
                reportFiles: 'ExtentReport.html',
                reportName: 'QA Automation Report'
            ])
        }

        success {
            echo 'Automation tests PASSED successfully!'
        }

        failure {
            echo 'Automation tests FAILED. Please check the console output.'
        }
    }
}