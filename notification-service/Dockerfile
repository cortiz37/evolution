FROM openjdk:15-alpine
ADD build/libs/notification-service.jar /app.jar
CMD java $JAVA_OPTS -jar app.jar $APP_OPTS