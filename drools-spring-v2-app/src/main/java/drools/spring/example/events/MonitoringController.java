package drools.spring.example.events;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import drools.spring.example.disease.Disease;
import drools.spring.example.disease.DiseaseType;
import drools.spring.example.medicine.Message;
import drools.spring.example.symptom.SymptomType;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

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
	
//	@GetMapping("/heartBeat")
//	public ResponseEntity<Message> getAllSymptoms(){
//		
//		KieSession ksession2 = createSessionWithPseudoClock();
//	     	     
//	     Misfires misfires = new Misfires();
//	     ksession2.setGlobal("misfires", misfires);
//	     Message customMessage = new Message();
//	     ksession2.setGlobal("customMessage", customMessage);
//	     SessionPseudoClock clock = ksession2.getSessionClock();
//	        for (int index = 0; index < 30; index++) {
//	            HeartBeatEvent beep = new HeartBeatEvent();
//	            ksession2.insert(beep);
//	            clock.advanceTime(200, TimeUnit.MILLISECONDS);
//	            int ruleCount = ksession2.fireAllRules();
//	        }
//	        System.out.println(customMessage.message);
//	        
//	    return new ResponseEntity<Message>(customMessage,HttpStatus.OK);	
//	    
//	}
	
	@GetMapping("/heartBeat")
	public ResponseEntity<Message> getAllSymptoms(){
		
		 KieServices ks = KieServices.Factory.get();
		 KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		 KieScanner kScanner = ks.newKieScanner(kContainer);
		 kScanner.start(10000);
		 
		 KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
	     kbconf.setOption(EventProcessingOption.STREAM);
	     KieBase kbase = kContainer.newKieBase(kbconf);
		 
	     KieSessionConfiguration ksconf1 = ks.newKieSessionConfiguration();
         ksconf1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
         final KieSession ksession = kbase.newKieSession(ksconf1, null);
	     	     
         Misfires misfires = new Misfires();
	     ksession.setGlobal("misfires", misfires);
	     Message customMessage = new Message();
	     ksession.setGlobal("customMessage", customMessage);
         
         Thread t = new Thread() {
             @Override
             public void run() {
            	 for (int index = 0; index < 30; index++) {
     	            HeartBeatEvent beep = new HeartBeatEvent();
     	            ksession.insert(beep);
     	           try {
                       Thread.sleep(200);
                   } catch (InterruptedException e) {
                       //do nothing
                   }
     	        }
             }
         };
         t.setDaemon(true);
         t.start();
         try {
             Thread.sleep(200);
         } catch (InterruptedException e) {
             //do nothing
         }
         ksession.fireUntilHalt();
         
         Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(HeartBeatEvent.class));
	     System.out.println(customMessage.message);
	        
	    return new ResponseEntity<Message>(customMessage,HttpStatus.OK);	
	    
	}
	
	
	
	@GetMapping("/oxygen")
	public ResponseEntity<Message> getOxigen(){
		
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
	     System.out.println(customMessage.message);
	        
	     return new ResponseEntity<Message>(customMessage,HttpStatus.OK);	
	    
	}
	
	@GetMapping("/dialisa")
	public ResponseEntity<Message> getDialise(){
		
		KieSession ksession2 = createSessionWithPseudoClock();
	     
	     Misfires misfires = new Misfires();
	     ksession2.setGlobal("misfires", misfires);
	     Message customMessage = new Message();
	     ksession2.setGlobal("customMessage", customMessage);
	     
	     SessionPseudoClock clock = ksession2.getSessionClock();
	     for (int index = 0; index < 9; index++) {
	            Urination urin = new Urination(5);
	            ksession2.insert(urin);
	            clock.advanceTime(1, TimeUnit.HOURS);
	            int ruleCount = ksession2.fireAllRules();
	     } 
	     Disease disease = new Disease();
	     disease.setDiseaseType(DiseaseType.AKUTNA_BUBREZNA_BOLEST);
	     ksession2.insert(disease);
	     System.out.println("KRECE SRCE");
	     for (int index = 0; index < 15; index++) {
	            HeartBeatEvent beep = new HeartBeatEvent();
	            ksession2.insert(beep);
	            clock.advanceTime(1, TimeUnit.SECONDS);
	            int ruleCount = ksession2.fireAllRules();
	     }
	       
	     System.out.println(misfires.count);
	     System.out.println(customMessage.message);
	        
	     return new ResponseEntity<Message>(customMessage,HttpStatus.OK);	
	    
	}
}
