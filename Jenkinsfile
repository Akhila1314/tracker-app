pipeline {
    agent any

    stages {
        stage('Build and Test') {
            steps {
                sh 'mvn -B clean package'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'cp target/tracker.jar /opt/tracker/tracker.jar'
                sh 'sudo /usr/bin/systemctl restart tracker'
            }
        }
    }
}
