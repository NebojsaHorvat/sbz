package drools.spring.example.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
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
import drools.spring.example.events.OxygenLvl;
import drools.spring.example.medicine.Message;

public class OxigenLvlTest {

	private KieSession createSessionWithPseudoClock() {
		 
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
	     
	     return ksession2;
	 }
	
	
	 @Test
	 public void testOxigetLvl() {
		 
	     KieSession ksession2 = createSessionWithPseudoClock();
	     
	     Misfires misfires = new Misfires();
	     ksession2.setGlobal("misfires", misfires);
	     Message customMessage = new Message();
	     ksession2.setGlobal("customMessage", customMessage);
	    
	     SessionPseudoClock clock = ksession2.getSessionClock();
	     int count = 60;
	        for (int index = 0; index < 30; index++) {
	        	OxygenLvl lvl = new OxygenLvl(count++);
	            ksession2.insert(lvl);
	            clock.advanceTime(1, TimeUnit.MINUTES);
	            int ruleCount = ksession2.fireAllRules();
	        }
	        assertThat(misfires.count, equalTo(0));
	 }
	 
	 @Test
	 public void testOxigetLvlNotWorking() {
		 
	     KieSession ksession2 = createSessionWithPseudoClock();
	     
	     Misfires misfires = new Misfires();
	     misfires.count = 0;
	     ksession2.setGlobal("misfires", misfires);
	     Message customMessage = new Message();
	     ksession2.setGlobal("customMessage", customMessage);
	    
	     int count = 70;
	     
	     count= 70;
	     SessionPseudoClock clock = ksession2.getSessionClock();
         for (int index = 0; index < 30; index++) {
        	 OxygenLvl lvl = new OxygenLvl(count--);
             ksession2.insert(lvl);
             clock.advanceTime(1, TimeUnit.MINUTES);
             int ruleCount = ksession2.fireAllRules();
         }
	     assertNotEquals(0, misfires.count);
	 }
	
}
