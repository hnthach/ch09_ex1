# Base image: Tomcat 9 + JDK 17
FROM tomcat:9.0-jdk17-temurin

# Set working directory
WORKDIR /usr/local/tomcat

# Xóa các webapp mặc định của Tomcat (ROOT, examples, docs)
RUN rm -rf webapps/*

# Copy file WAR của bạn vào Tomcat
# Đổi tên thành ROOT.war để chạy trực tiếp tại http://localhost:8080/
COPY ch09_ex1_download_war.war webapps/ROOT.war

# Mở cổng 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
