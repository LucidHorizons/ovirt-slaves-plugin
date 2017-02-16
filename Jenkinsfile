#!groovy

try {
    node {
	stage('Clean workspace') {
            /* Running on a fresh Docker instance makes this redundant, but just in
            * case the host isn't configured to give us a new Docker image for every
            * build, make sure we clean things before we do anything
            */
            deleteDir()
            sh 'ls -lah'
        }

	// Mark the code checkout 'stage'....
	stage('checkout') {
		// Get some code from a GitHub repository
		checkout scm
	}

	stage('test') {
		withMaven(
            		maven: 'M3', jdk: 'JDK8u121') {
			sh "mvn test"
		}
	}

	stage('build') {
		withMaven(maven: 'M3', jdk: 'JDK8u121') {
		
        		// Run the maven build
        		sh "mvn clean install"
		}
	}

	stage('package') {
		withMaven(maven: 'M3', jdk: 'JDK8u121') {
			sh "mvn package"
		}
	}

   	stage('archive') {
		archive 'target/*.hpi'
	}
    }
} catch (exc) {
	echo "Caught: ${exc}"

	String recipient = 'dan@lucidhorizons.com.au'

	mail subject: "${env.JOB_NAME} (${env.BUILD_NUMBER}) failed",
		body: "It appears that ${env.BUILD_URL} is failing, please have a look at it.",
		to: recipient
		replyTo: recipient
		from: 'jenkins@lucidhorizons.com.au'

	// Re-throw the exception so that the build knows it failed
	throw exc
}

