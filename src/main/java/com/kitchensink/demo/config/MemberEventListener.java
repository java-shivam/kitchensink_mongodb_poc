package com.kitchensink.demo.config;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kitchensink.demo.model.Member;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MemberEventListener {
	
	private final Logger log;

    public MemberEventListener(Logger log) {
        this.log = log;
    }

    @EventListener
    public void handleMemberRegisteredEvent(MemberRegisteredEvent event) {
        Member member = event.getMember();
        // Handle the event (e.g., log the event, send an email, etc.)
        log.info("[MemberEventListener]::Handling event for member registration: " + member.getName());
    }
}
