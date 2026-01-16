package com.enote.common;

public interface RestApiReponseMessage {
	
	 static String addSuccess(String modelName) {
		return modelName + "added successfully";
	}
	
	 static String updatedSuccess(String modelName) {
		return modelName + "updated successfully";
	}
	
	 static String deleteSuccess(String modelName) {
		return modelName + "deleted successfully";
	}
	
	 static String notFound(String modelName) {
		return modelName + "not found";
	}
	
	 static String fetchSuccess(String modelName) {
		return modelName + "found successfully";
	}

}
