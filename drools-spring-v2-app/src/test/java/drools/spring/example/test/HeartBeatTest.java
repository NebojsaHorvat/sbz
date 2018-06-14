package drools.spring.example.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import drools.spring.example.events.HeartBeatEvent;
import drools.spring.example.events.Misfires;

public class HeartBeatTest {

	 @Test
	 public void testHeartBeat() {
		 KieServices ks = KieServices.Factory.get();
		 KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		 KieScanner kScanner = ks.newKieScanner(kContainer);
		 kScanner.start(10000);
		 
		 KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
	     kbconf.setOption(EventProcessingOption.STREAM);
	     KieBase kbase = kContainer.newKieBase(kbconf);
		 
	     KieSessionConfiguration ksconf2 = ks.newKieSessionConfiguration();
	     ksconf2.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
	     KieSession ksession2 = kbase.newKieSession(ksconf2, null);
	     //ksession2.getAgenda().getAgendaGroup("heartBeat").setFocus();
	     Misfires misfires = new Misfires();
	     ksession2.setGlobal("misfires", misfires);
	     SessionPseudoClock clock = ksession2.getSessionClock();
	        for (int index = 0; index < 30; index++) {
	            HeartBeatEvent beep = new HeartBeatEvent();
	            ksession2.insert(beep);
	            clock.advanceTime(1, TimeUnit.SECONDS);
	            int ruleCount = ksession2.fireAllRules();
	        }
	        assertThat(misfires.count, equalTo(0));
	 }
	 
	 @Test
	 public void testHeartBeatToHigh() {
		 KieServices ks = KieServices.Factory.get();
		 KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		 KieScanner kScanner = ks.newKieScanner(kContainer);
		 kScanner.start(10000);
		 
		 KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
	     kbconf.setOption(EventProcessingOption.STREAM);
	     KieBase kbase = kContainer.newKieBase(kbconf);
		 
	     KieSessionConfiguration ksconf2 = ks.newKieSessionConfiguration();
	     ksconf2.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
	     KieSession ksession2 = kbase.newKieSession(ksconf2, null);
	     //ksession2.getAgenda().getAgendaGroup("heartBeat").setFocus();
	     
	     Misfires misfires = new Misfires();
	     ksession2.setGlobal("misfires", misfires);
	     SessionPseudoClock clock = ksession2.getSessionClock();
	        for (int index = 0; index < 30; index++) {
	            HeartBeatEvent beep = new HeartBeatEvent();
	            ksession2.insert(beep);
	            clock.advanceTime(200, TimeUnit.MILLISECONDS);
	            int ruleCount = ksession2.fireAllRules();
	        }
	        assertNotEquals(0, misfires.count);
	 }
}
