
# 🚝Tongue - Back-end
```git
⚡ 2024.03 ~ 2024.06
```

&nbsp;&nbsp; **Tongue:**
LLM을 활용한 여행 계획부터 기록, 공유까지 전반을 아우르는 통합 여행 플랫폼
<br />
- [Docker image, Github Actions CI/CD 구축, EC2 배포](https://github.com/dahyunko/Tongue/edit/master/README.md#1-docker-github-cicd-%EA%B5%AC%EC%B6%95-)
- [Spring Security & jwt 사용자 인증, 인가](https://github.com/dahyunko/Tongue/edit/master/README.md#2-spring-security--jwt-%EC%82%AC%EC%9A%A9%EC%9E%90-%EC%9D%B8%EC%A6%9D-%EC%9D%B8%EA%B0%80-)

## Using Stacks <br/>
```git
📌 Springboot, MyBatis, Maven, MYSQL, Docker, Github Actions
```
&nbsp;&nbsp;<strong>System Architecture & ERD</strong> <br />
<div align="center">
      <img src="https://github.com/dahyunko/Tongue/assets/101400650/4830c7db-6dd6-44e2-99c3-873d78855096" width="600" >
      <br/><strong>System Architecture</strong><br/></br>
      <img src="https://github.com/dahyunko/Tongue/assets/101400650/a91826de-d95c-4afb-baa5-f1da883e02d0" width="600" >
      <br/><strong>ERD</strong></br>
</div>

<br />

## 1. Docker, GitHub CI/CD 구축 <br/>
```git
📌 Docker, GitHub Actions, AWS
```
> springboot, Docker CICD 구축하여 AWS 배포 과정 : [dahyunko#2](https://github.com/dahyunko/Tongue/issues/2)

SpringBoot Maven 프로젝트를 docker 이미지로 DockerHub에 배포한 후 GitHub Actions maven.yml 파일에 build, depoly 코드를 설정하여 `master branch`에서 `git push`를 실행할 시 EC2 서버 접근하여 서버를 배포하였습니다.
- **CI/CD**
  - CI(Continuous Integration) : `master branch push`가 진행될 때마다 자동으로 빌드, 테스트가 실행되도록 설정하여 문제가 조기에 발견되도록 함
  - CD(Continuous Deployment) : CI가 완료되면 새로운 이미지를 자동으로 EC2에 배포

- **이루고자 한 목표**
  1. 배포 자동화
  2. 일관된 환경 유지
  3. 지속적인 통합 및 배포
  4. 확장성   


## 2. Spring Security & jwt 사용자 인증, 인가 <br/>
```git
📌 Spring Security, jwt
```
> Spring Security 설정을 구성하여 JWT를 사용한 인증 및 인가를 처리합니다.

다양한 경로에 대한 접근 권한을 설정하고, JWT 필터를 적용하여 세션 없이 보안 관리를 수행합니다. 또한, CORS 설정을 통해 혀용된 주소에 한하여 접근을 허용합니다. BCryptPasswordEncoder를 사용하여 비밀번호를 암호화하였습니다.

- **경로별 접근 권한 설정**
  ``` JAVA
        http.authorizeHttpRequests((auth)-> auth
              .requestMatchers("/user/**", "/login", "/").permitAll()
              .requestMatchers("/travel/**", "/user/**", "/board/**", "/mypage/**", "/magazine/**", "/magazine/comment/**").hasAuthority(String.valueOf(UserRole.USER))
              .requestMatchers("/travel/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
              .anyRequest().authenticated()
      );
  ```

- **CORS 설정**
  ``` JAVA
      @Bean
      public CorsConfigurationSource corsConfigurationSource() {
          CorsConfiguration configuration = new CorsConfiguration();
          configuration.addAllowedOriginPattern("*");
          configuration.addAllowedHeader("*");
          configuration.addAllowedMethod("*");
          configuration.setAllowCredentials(true);
          configuration.setMaxAge(3600L);
          configuration.setExposedHeaders(Collections.singletonList("Authorization"));
      
          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
          source.registerCorsConfiguration("/**", configuration);
          return source;
      }
  ```

