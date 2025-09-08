pipeline {
    agent any
    
    // Pipeline configuration demonstrating Git repository URL configuration
    options {
        // Keep builds for 30 days
        buildDiscarder(logRotator(daysToKeepStr: '30'))
        // Timeout the build after 10 minutes
        timeout(time: 10, unit: 'MINUTES')
        // Disable concurrent builds
        disableConcurrentBuilds()
    }
    
    // Environment variables
    environment {
        // Define build file path for Ant
        ANT_BUILD_FILE = 'build.xml'
        JAVA_HOME = '/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home' // Adjust based on your Jenkins setup
        PATH = "${JAVA_HOME}/bin:${PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code from Git repository...'
                // This demonstrates Git repository URL configuration
                // In a real scenario, Jenkins would automatically checkout from the configured SCM
                // The repository URL would be configured in Jenkins job settings or pipeline SCM
                script {
                    echo "Repository URL would be configured in Jenkins SCM settings"
                    echo "Example: https://github.com/your-username/jenkins-pipeline-with-config.git"
                    echo "Current workspace: ${env.WORKSPACE}"
                }
                
                // Verify source files are present
                sh 'ls -la'
                sh 'find . -name "*.java" -o -name "*.xml"'
            }
        }
        
        stage('Environment Setup') {
            steps {
                echo 'Setting up build environment...'
                
                // Display Java version
                sh 'java -version || echo "Java not found in PATH"'
                
                sh 'export PATH=${PATH}:/opt/homebrew/Cellar/ant/1.10.15_1/libexec/bin/ant'

                // Display Ant version
                sh 'ant -version || echo "Ant not found in PATH"'
                
                // Verify build file exists
                script {
                    if (fileExists("${env.ANT_BUILD_FILE}")) {
                        echo "Build file found: ${env.ANT_BUILD_FILE}"
                    } else {
                        error "Build file not found: ${env.ANT_BUILD_FILE}"
                    }
                }
            }
        }
        
        stage('Clean') {
            steps {
                echo 'Cleaning previous build artifacts...'
                // Demonstrates Ant build file path configuration
                sh "ant -f ${env.ANT_BUILD_FILE} clean"
            }
        }
        
        stage('Compile') {
            steps {
                echo 'Compiling Java source code...'
                // Using configured build file path
                sh "ant -f ${env.ANT_BUILD_FILE} compile"
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Execute tests using Ant build file
                sh "ant -f ${env.ANT_BUILD_FILE} test"
            }
        }
        
        stage('Package') {
            steps {
                echo 'Creating JAR package...'
                // Create JAR using Ant build file
                sh "ant -f ${env.ANT_BUILD_FILE} jar"
                
                // Archive the artifacts
                archiveArtifacts artifacts: 'dist/lib/*.jar', fingerprint: true
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                script {
                    // In a real scenario, this would deploy to a server
                    echo "Application would be deployed to target environment"
                    echo "JAR files created:"
                    sh 'ls -la dist/lib/'
                }
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed!'
            // Clean workspace after build
            cleanWs()
        }
        success {
            echo 'Pipeline executed successfully!'
            // In a real scenario, you might send notifications here
            script {
                echo "Build successful for repository: ${env.GIT_URL ?: 'Local repository'}"
                echo "Build file used: ${env.ANT_BUILD_FILE}"
            }
        }
        failure {
            echo 'Pipeline execution failed!'
            // In a real scenario, you might send failure notifications here
        }
        unstable {
            echo 'Pipeline execution was unstable!'
        }
    }
}
