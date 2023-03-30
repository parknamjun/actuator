
## prometheus actuator install

* pom.xml
```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <!-- for prometheus, /actuator/prometheus 추가됨-->
    <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-registry-prometheus</artifactId>
      <version>1.1.19</version><!-- bean 등록 에러가 없는 마지막 버전-->
    </dependency>
```

* application.properties, application_dev, local, real.properties에서 management 부분 삭제
```text
server.tomcat.mbeanregistry.enabled=true
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
management.endpoint.metrics.enabled=true
#management.endpoint.prometheus.enabled=true
#management.metrics.export.prometheus.enabled=true
management.server.port=9980 #VPC내부에서만 접근 가능하게 포트를 분리함
```

* java com.....api.config.SecurityConfig "/actuator/**" 추가
```java
  public void configure(WebSecurity web) {
  web.ignoring().antMatchers("/v2/api-docs"
      , "/swagger-resources"
      , "/swagger-resources/configuration/ui"
      , "/swagger-resources/configuration/security"
      , "/swagger-ui.html", "/health", "/version", "/api/v1.0/dids/**", "/actuator/**");
  }
```

* prometheus 정보확인
  * http://localhost:9980/actuator/prometheus


### prometheus install
* https://prometheus.io/download/
* prometheus.yml 모니터링 서버정보 추가
```yml
scrape_configs:
  - job_name: "MyPrometheus"
  metrics_path: '/actuator/prometheus'
  scrape_interval: 1s
  static_configs:
    - targets: ["localhost:9980"]
```
* 실행 : bin/prometheus
* 설정내용 확인 
  * http://localhost:9090/config
  * http://localhost:9090/targets
```text
...
scrape_configs:
- job_name: MyPrometheus
  honor_timestamps: true
  scrape_interval: 1s
  scrape_timeout: 1s
  metrics_path: /actuator/prometheus
  scheme: http
  follow_redirects: true
  enable_http2: true
  static_configs:
  - targets:
    - localhost:9980
```

### grafana install
* https://grafana.com/grafana/download
* 실행 : bin/grafana-server 
* 관리화면 : http://localhost:3000/
* ...



