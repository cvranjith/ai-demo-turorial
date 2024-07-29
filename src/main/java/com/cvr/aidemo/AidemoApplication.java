package com.cvr.aidemo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.cvr.aidemo.chatmodels.ChatModelFactory;
import com.cvr.aidemo.conversations.ChatMessage;
import com.cvr.aidemo.conversations.Conversation;
import com.cvr.aidemo.conversations.ConversationRepository;
import com.cvr.aidemo.profiles.Gender;
import com.cvr.aidemo.profiles.Profile;
//import com.cvr.aidemo.profiles.ProfileCreationService;
import com.cvr.aidemo.profiles.ProfileRepository;

//import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
//import org.springframework.ai.openai.chatModel;

//import org.springframework.ai.openai.OpenAiChatModel;
//import org.springframework.ai.openai.OpenAiChatClient;
//import org.springframework.ai.ollama.OllamaChatClient;
//import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;

@SpringBootApplication
public class AidemoApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	@Autowired
	//private OpenAiChatClient chatClient;
	//private OllamaChatClient chatClient;
	private OllamaChatModel ollamaChatModel;
	private OpenAiChatModel openAiChatModel;

	//@Autowired
	//private ProfileCreationService profileCreationService;


	public static void main(String[] args) {
		SpringApplication.run(AidemoApplication.class, args);
	}
	public void run(String... args) {
		System.out.println("My App Runs");
		/*
		Profile profile= new Profile
		("1", "Ranjith", "Mash", 40, "Alien", Gender.MALE, 
		"Hi Hello Artist", "foo.jpg", "INTF");
		profileRepository.save(profile);

		Profile profile2= new Profile
		("2", "CVR", "Mash", 45, "Mongolian", Gender.MALE, 
		"Hi Hello Mongolian Artist", "foo.jpg", "INTF");
		profileRepository.save(profile2);
		
		//profileCreationService.saveProfilesToDB();

		profileRepository.findAll().forEach(System.out::println);
		System.out.println("Finished Fetching Prof");

		Conversation conversation = new Conversation("1", profile.id(), 
		List.of(
			new ChatMessage("Hello Chat", profile.id(), LocalDateTime.now())
		));
		
		conversationRepository.save(conversation);
		conversationRepository.findAll().forEach(System.out::println);
		System.out.println("Finished Fetching Conv");
		*/
		try {
			String strUserMessae="Write a 4 liner poem about Singapore National Day";
			//String strSystemMessage="Please give me the output in JSON Array format, each time as a member in the array like [\"line1\",\"line2\",...] format that I can parse. Please do not put any description or instruction in the response for humans to understand, i want to parse your response directly using a JSON parser, so the response text should be a JSON array.";
			String strSystemMessage="";
			var userMessage= new UserMessage(strUserMessae);
			var systemMessage= new SystemMessage(strSystemMessage);


		//Prompt prompt = new Prompt("Write a 4 liner poem about Singapore National Day. ");
		Prompt prompt = new Prompt(List.of(systemMessage,userMessage));
		//ChatResponse response= chatClient.call(prompt);
		//ChatModel chatModel = chatModelFactory.getChatModel("ollama");
		ChatResponse response;
		String myStrChatModel="ollama";
		if ("openai".equalsIgnoreCase(myStrChatModel)) {
			response=openAiChatModel.call(prompt);
		} else {
			response=ollamaChatModel.call(prompt);
		}
		//ChatResponse response=chatModel.call(prompt);
		System.out.println(response.getResult());
		System.out.println("**************");
		System.out.println(response.getResult().getOutput().getContent());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}


	}
}
