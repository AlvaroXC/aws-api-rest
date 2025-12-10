package com.uady.aws_rest_project.service;

import com.uady.aws_rest_project.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class SnsService {

    private final SnsClient snsClient;

    @Value("${aws.sns.topic-arn}")
    private String topic;

    public SnsService(SnsClient snsClient){
        this.snsClient = snsClient;
    }

    public void sendGradesNotification(Student student){
        String message = String.format(
            "Hola %s %s,\n\n" +
                    "Aquí tienes tu reporte de calificaciones del SICEI.\n" +
                    "Matrícula: %s\n" +
                    "Promedio General: %.2f\n\n",
            student.getNombres(),
            student.getApellidos(),
            student.getMatricula(),
            student.getPromedio()
        );

        String subject = "Reporte de Calificaciones: " + student.getMatricula();

        PublishRequest request = PublishRequest.builder()
                .topicArn(topic)
                .message(message)
                .subject(subject)
                .build();

        PublishResponse response = snsClient.publish(request);
    }

}
