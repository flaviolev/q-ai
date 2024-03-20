package ch.zuehlke.qai.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Score {
    List<Submission> submissions;
    String message;
}
