package com.uady.aws_rest_project.service;

import com.uady.aws_rest_project.model.Session;
import com.uady.aws_rest_project.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.time.Instant;
import java.util.UUID;

@Service
public class SessionService {

    private DynamoDbTable<Session> sessionTable;
    private final StudentService studentService;
    @Value("${aws.dynamodb.table-name}")
    private String tableName;

    public SessionService(DynamoDbEnhancedClient enhancedClient, StudentService studentService) {
        this.studentService = studentService;
        this.sessionTable = enhancedClient.table(tableName, TableSchema.fromBean(Session.class));
    }

    public String login(Integer studentId, String password) {

        Student student = studentService.findById(studentId);
        if (student == null || !student.getPassword().equals(password)) {
            throw new RuntimeException("Credenciales inválidas");
        }

        Session session = new Session();
        session.setId(UUID.randomUUID().toString());
        session.setAlumnoId(studentId);
        session.setFecha(Instant.now().getEpochSecond());
        session.setActive(true);

        String token = UUID.randomUUID().toString() + UUID.randomUUID().toString() +
                UUID.randomUUID().toString() + UUID.randomUUID().toString();
        session.setSessionString(token.replace("-", "").substring(0, 128));

        sessionTable.putItem(session);

        return session.getSessionString();
    }

    public boolean verifySession(String sessionString) {
        Session session = findBySessionString(sessionString);
        return session != null && session.getActive();
    }

    public void logout(String sessionString) {
        Session session = findBySessionString(sessionString);
        if (session != null) {
            session.setActive(false);
            sessionTable.updateItem(session);
        }
    }

    private Session findBySessionString(String sessionString) {
        try {
            ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder()
                    .consistentRead(true)
                    .build();

            return sessionTable.scan(scanRequest).items().stream()
                    .filter(s -> s.getSessionString().equals(sessionString))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

}
