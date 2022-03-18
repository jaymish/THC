node{
    def DOCKERHUB_REPO = "arif7866/weather-producer"
    def DOCKER_IMAGE_VERSION = ""

    stage("clean workspace") {

    }

    stage("git checkout") {
        checkout scm

        def GIT_COMMIT = sh(returnStdout: true, script: "git rev-parse HEAD").trim().take(7)
        DOCKER_IMAGE_VERSION = "${BUILD_NUMBER}-${GIT_COMMIT}"
    }
   stage('Run Container on Server'){
   try{

    sh "docker system prune"
    sh "docker-compose up --build"
   }
    catch (e) {
               error "Service update failed"
           }
      }
}
