package study.java.helloworld.service;

import java.util.List;

import study.java.helloworld.model.HelloWorld;

public interface HelloWorldService {
	public void addHelloWorld(HelloWorld hw) throws Exception;
	
	public void editHelloWorld(HelloWorld helloworld) throws Exception;
	
	public void deleteHelloWorld(HelloWorld helloworld) throws Exception;
	
	public HelloWorld getHelloWorldItem(HelloWorld helloworld) throws Exception;
	
	public HelloWorld getHelloWorldItemLogin(HelloWorld helloworld) throws Exception;
	
	public List<HelloWorld> getHelloWorldList(HelloWorld helloworld) throws Exception;

}
