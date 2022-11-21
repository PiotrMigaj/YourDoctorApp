package pl.migibud.yourDoctor.exception;

import lombok.Data;

import java.util.List;

@Data
public class ErrorInfo {
	private final List<String> message;
}
