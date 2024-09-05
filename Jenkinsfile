pipeline {
    agent any

    tools {
        maven 'Maven 4.0.0'
        jdk 'JDK 17'
    }

    stages {
        stage('Checkout') {
            steps {
                // Clona o repositório Git
                git branch: 'main', url: 'https://github.com/prandini-kaio/all-o-backend.git'
            }
        }

        stage('Build') {
            steps {
                // Compila o projeto usando Maven
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Executa os testes
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                // Executa o docker-compose no servidor remoto via SSH
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'deploy-server',
                            transfers: [
                                sshTransfer(
                                    sourceFiles: '**/*',  // Transfere todos os arquivos do projeto
                                    removePrefix: '',     // Mantém a estrutura de diretórios
                                    remoteDirectory: '/app/versions'
                                )
                            ],
                            execCommand: '''
                                cd /app/versions &&
                                docker-compose down &&
                                docker-compose up -d --build
                            '''
                        )
                    ]
                )
            }
        }
    }

    post {
        always {
            cleanWs() // Limpa o workspace após a execução
        }
        failure {
            mail to: 'dev-team@example.com',
                 subject: "Jenkins: Falha no build ${env.BUILD_NUMBER}",
                 body: "Pipeline ${env.JOB_NAME} falhou no estágio ${env.STAGE_NAME}. Verifique o Jenkins para mais detalhes."
        }
    }
}
