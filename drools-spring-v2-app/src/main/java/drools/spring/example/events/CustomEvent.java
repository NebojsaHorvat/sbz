package drools.spring.example.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("30m")
public class CustomEvent {

	public CustomEventType customEventType;
	
	public CustomEvent () {}
}
