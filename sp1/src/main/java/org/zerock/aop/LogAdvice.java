package org.zerock.aop;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint; // @Around에서 원본 메서드 실행
import org.aspectj.lang.annotation.Around; // 메서드 전체 감싸기 Advice
import org.aspectj.lang.annotation.Aspect; // AOP Aspect 클래스 선언
import org.aspectj.lang.annotation.Before; // 메서드 실행 전 Advice
import org.springframework.stereotype.Component; // Spring 빈 등록
import lombok.extern.log4j.Log4j2; // 로깅 어노테이션

@Aspect // AOP(관점 지향 프로그래밍)에서 이 클래스가 공통 기능(관점)을 담고 있음을 표시
// AOP에서 이 클래스는 관점(Aspect)이 되고, 내부의 메서드들이 공통 관심 기능(Advice)이 됨
@Log4j2 // Lombok으로 log4j2 로거 자동 생성
@Component // Spring 컨테이너가 이 빈을 관리
public class LogAdvice {

  // org.zerock.service 패키지의 모든 public 메서드 실행 전 호출
  // execution(...) 구문 => Pointcut 표현식. 즉, AOP의 “대상”을 지정하는 방법
  // __ execution(...) 메서드 실행 시점을 기준으로 AOP를 적용하겠다는 뜻
  // __ * 리턴 타입 상관없음 (모든 타입)
  // __ org.zerock.service.* org.zerock.service 패키지 안의 모든 클래스
  // __ .*(..) 모든 메서드(이름 상관없고, 매개변수 어떤 것이든)
  @Before("execution(* org.zerock.service.*.*(..))")
  public void logParams(JoinPoint jp) { // jp: 실행되는 메서드 정보 담음
    log.info("--------------------------");
    log.info("logParams");

    Object[] params = jp.getArgs(); // 메서드 매개변수 배열 추출

    log.info(Arrays.toString(params)); // 매개변수 값들 출력

    Object target = jp.getTarget(); // 실제 호출 대상 객체(서비스 인스턴스)

    log.info(target); // target 객체 정보 출력 (toString)

    log.info("--------------------------");
  }


  // org.zerock.service 패키지의 모든 public 메서드를 완전히 감싸서 실행시간 측정
  @Around("execution(* org.zerock.service.*.*(..))")
  public Object logTime(ProceedingJoinPoint pjp) throws Throwable { // pjp: 원본 메서드 제어 가능
    log.info("--------------------------");
    log.info("logTimes");

    long start = System.currentTimeMillis(); // 실행 시작 시간 기록

    Object result = pjp.proceed(); // ★ 원본 메서드 실제 실행

    long end = System.currentTimeMillis(); // 실행 종료 시간 기록

    log.info("--------------------------");
    log.info("TIME : " + (end - start)); // 실행시간(ms) 출력

    return result;
  }



}
