package com.enote.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
	boolean success;
	String message;
	T data;

	public GenericResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

}
