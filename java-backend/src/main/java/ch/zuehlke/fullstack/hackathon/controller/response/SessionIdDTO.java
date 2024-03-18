package ch.zuehlke.fullstack.hackathon.controller.response;

import java.util.UUID;

public record SessionIdDTO(
        UUID sessionId
) { }
